package aramframework.com.sym.mnu.mpm.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.mnu.mpm.service.MenuManageService;
import aramframework.com.sym.mnu.mpm.service.MenuManageVO;
import aramframework.com.sym.prm.service.ProgrmManageVO;
import aramframework.com.sym.prm.service.impl.ProgrmManageMapper;
import aramframework.com.sym.prm.service.impl.ProgrmManageDtlMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Service
public class MenuManageServiceImpl extends EgovAbstractServiceImpl implements MenuManageService {

	@Autowired
	private MenuManageMapper menuManageMapper;
	
	@Autowired
	private ProgrmManageMapper progrmManageMapper;

	@Autowired
	private ProgrmManageDtlMapper progrmManageDtlMapper;
	
	//	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @param menuManageVO
	 */
	public List<EgovMap> selectMenuManageList(MenuManageVO menuManageVO) {
		return menuManageMapper.selectMenuManageList(menuManageVO);
	}

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	public int selectMenuManageListCnt(MenuManageVO menuManageVO) {
		return menuManageMapper.selectMenuManageListCnt(menuManageVO);
	}

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedMenuNoForDel
	 */
	public void deleteMenuManageList(String checkedMenuNoForDel) {
		MenuManageVO menuManageVO = null;

		String[] delMenuNo = checkedMenuNoForDel.split(",");

		if (delMenuNo == null || (delMenuNo.length == 0)) {
			throw new RuntimeException("String Split Error!");
		}
		for (int i = 0; i < delMenuNo.length; i++) {
			menuManageVO = new MenuManageVO();
			menuManageVO.setMenuNo(Integer.parseInt(delMenuNo[i]));
			menuManageMapper.deleteMenuManage(menuManageVO);
		}
	}

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param menuManageVO
	 */
	public MenuManageVO selectMenuManage(MenuManageVO menuManageVO) {
		MenuManageVO resultVo = menuManageMapper.selectMenuManage(menuManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, menuManageVO); 
		return resultVo;
	}

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	public int selectMenuNoByPk(MenuManageVO menuManageVO) {
		return menuManageMapper.selectMenuNoByPk(menuManageVO);
	}

	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param menuManageVO
	 */
	public void insertMenuManage(MenuManageVO menuManageVO) {
		menuManageMapper.insertMenuManage(menuManageVO);
	}

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param menuManageVO
	 */
	public void updateMenuManage(MenuManageVO menuManageVO) {
		menuManageMapper.updateMenuManage(menuManageVO);
	}

	/**
	 * 메뉴 정보를 삭제
	 * 
	 * @param menuManageVO
	 */
	public void deleteMenuManage(MenuManageVO menuManageVO) {
		menuManageMapper.deleteMenuManage(menuManageVO);
	}

	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * 
	 * @param menuManageVO
	 */
	public int selectUpperMenuNoByPk(MenuManageVO menuManageVO) {
		return menuManageMapper.selectUpperMenuNoByPk(menuManageVO);
	}

	/**
	 * 메뉴 목록을 조회(메뉴트리 조회)
	 * 
	 */
	public List<EgovMap> selectMenuList() {
		return menuManageMapper.selectMenuList();
	}

	/* ### 일괄처리 프로세스 ### */
	/**
	 * 메뉴일괄초기화 프로세스 메뉴목록테이블, 프로그램 목록테이블 전체 삭제
	 * 
	 */
	public boolean menuBndeAllDelete() {
		if (!deleteAllProgrmDtls()) {
			return false;
		} // 프로그램변경요청 테이블
		if (!deleteAllMenuList()) {
			return false;
		} // 메뉴정보 테이블
		if (!deleteAllProgrm()) {
			return false;
		} // 프로그램목록 테이블
		return true;
	}

	/**
	 * 메뉴일괄등록 프로세스
	 * 
	 * @param menuManageVO
	 * @param inputStream
	 */
	public String menuBndeRegist(MenuManageVO menuManageVO, InputStream inputStream) {

		String message = bndeRegist(inputStream);
		String sMessage = null;

		switch (Integer.parseInt(message)) {
		case 99:
			egovLogger.debug("프로그램목록/메뉴정보테이블 데이타 존재오류 - 초기화 하신 후 다시 처리하세요.");
			sMessage = "프로그램목록/메뉴정보테이블 데이타 존재오류 - 초기화 하신 후 다시 처리하세요.";
			break;
		case 90:
			egovLogger.debug("파일존재하지 않음.");
			sMessage = "파일존재하지 않음.";
			break;
		case 91:
			egovLogger.debug("프로그램시트의 cell 갯수 오류.");
			sMessage = "프로그램시트의 cell 갯수 오류.";
			break;
		case 92:
			egovLogger.debug("메뉴정보시트의 cell 갯수 오류.");
			sMessage = "메뉴정보시트의 cell 갯수 오류.";
			break;
		case 93:
			egovLogger.debug("엑셀 시트갯수 오류.");
			sMessage = "엑셀 시트갯수 오류.";
			break;
		case 95:
			egovLogger.debug("메뉴정보 입력시 에러.");
			sMessage = "메뉴정보 입력시 에러.";
			break;
		case 96:
			egovLogger.debug("프로그램목록입력시 에러.");
			sMessage = "프로그램목록입력시 에러.";
			break;
		default:
			egovLogger.debug("일괄배치처리 완료.");
			sMessage = "일괄배치처리 완료.";
			break;
		}
		egovLogger.debug(message);
		return sMessage;
	}

	/**
	 * 메뉴목록_프로그램목록 일괄생성
	 * 
	 * @param inputStream
	 */
	private String bndeRegist(InputStream inputStream) {
		boolean success = false;
		String requestValue = null;
		int progrmSheetRowCnt = 0;
		int menuSheetRowCnt = 0;
		try {
			/*
			 * 오류 메세지 정보 
			 * message = "99"; //프로그램목록테이블 데이타 존재오류. 
			 * message = "99"; //메뉴정보테이블 데이타 존재오류. 
			 * message = "90"; //파일존재하지 않음. 
			 * message = "91"; //프로그램시트의 cell 갯수 오류 
			 * message = "92"; //메뉴정보시트의 cell 갯수 오류
			 * message = "93"; //엑셀 시트갯수 오류 
			 * message = "95"; //메뉴정보 입력시 에러 
			 * message = "96"; //프로그램목록입력시 에러 
			 * message = "0";  //일괄배치처리 완료
			 */

			if (progrmManageMapper.selectProgrmListCntAll() > 0) {
				return requestValue = "99";
			} // 프로그램목록테이블 데이타 존재오류.
			if (menuManageMapper.selectMenuListCnt() > 0) {
				return requestValue = "99";
			} // 메뉴정보테이블 데이타 존재오류.

			
			Workbook hssfWB = excelZipService.loadWorkbook(inputStream);
			// 엑셀 파일 시트 갯수 확인 sheet = 2 첫번째시트 = 프로그램목록 두번째시트 = 메뉴목록
			if (hssfWB.getNumberOfSheets() == 2) {
				Sheet progrmSheet = hssfWB.getSheetAt(0); // 프로그램목록 시트 가져오기
				Sheet menuSheet = hssfWB.getSheetAt(1); // 메뉴정보 시트 가져오기
				Row progrmRow = progrmSheet.getRow(1); // 프로그램 row 가져오기
				Row menuRow = menuSheet.getRow(1); // 메뉴정보 row 가져오기
				progrmSheetRowCnt = progrmRow.getPhysicalNumberOfCells(); // 프로그램 cell Cnt
				menuSheetRowCnt = menuRow.getPhysicalNumberOfCells(); // 메뉴정보 cell Cnt

				// 프로그램 시트 파일 데이타 검증 cell = 5개
				if (progrmSheetRowCnt != 5) {
					return requestValue = "91"; // 프로그램시트의 cell 갯수 오류
				}

				// 메뉴목록 시트 파일 데이타 검증 cell = 8개
				if (menuSheetRowCnt != 8) {
					return requestValue = "92"; // 메뉴정보시트의 cell 갯수 오류
				}

				/* sheet1번 = 프로그램목록 , sheet2번 = 메뉴정보 */
				success = progrmRegist(progrmSheet);
				if (success) {
					success = menuRegist(menuSheet);
					if (success) {
						return requestValue = "0"; // 일괄배치처리 완료
					} else {
						deleteAllProgrmDtls();
						deleteAllProgrm();
						deleteAllMenuList();
						return requestValue = "95"; // 메뉴정보 입력시 에러
					}
				} else {
					deleteAllProgrmDtls();
					deleteAllProgrm();
					return requestValue = "96"; // 프로그램목록입력시 에러
				}
			} else {
				return requestValue = "93"; // 엑셀 시트갯수 오류
			}

		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}
		return requestValue;
	}

	/**
	 * 프로그램목록 일괄등록
	 * 
	 * @param progrmSheet
	 */
	private boolean progrmRegist(Sheet progrmSheet) {
		int count = 0;
		boolean success = false;
		try {
			int rows = progrmSheet.getPhysicalNumberOfRows(); // 행 갯수 가져오기
			for (int j = 1; j < rows; j++) { // row 루프
				ProgrmManageVO vo = new ProgrmManageVO();
				Row row = progrmSheet.getRow(j); // row 가져오기
				if (row != null) {
//					int cells = row.getPhysicalNumberOfCells(); // cell 갯수 가져오기

					Cell cell = null;
					cell = row.getCell(0); // 프로그램명
					if (cell != null) {
						vo.setProgrmFileNm("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(1); // 프로그램한글명
					if (cell != null) {
						vo.setProgrmKoreanNm("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(2); // 프로그램저장경로
					if (cell != null) {
						vo.setProgrmStrePath("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(3); // 프로그램 URL
					if (cell != null) {
						vo.setURL("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(4); // 프로그램설명
					if (cell != null) {
						vo.setProgrmDc("" + cell.getRichStringCellValue());
					}
				}
				if (insertProgrm(vo)) {
					count++;
				}
			}
			if (count == rows - 1) {
				success = true;
			} else {
				success = false;
			}
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}
		return success;
	}

	/**
	 * 메뉴정보 일괄등록
	 * 
	 * @param menuSheet
	 */
	private boolean menuRegist(Sheet menuSheet) {
		boolean success = false;
		int count = 0;
		try {
			int rows = menuSheet.getPhysicalNumberOfRows(); // 행 갯수 가져오기
			for (int j = 1; j < rows; j++) { // row 루프
				MenuManageVO vo = new MenuManageVO();
				Row row = menuSheet.getRow(j); // row 가져오기
				if (row != null) {
//					int cells = row.getPhysicalNumberOfCells(); // cell 갯수 가져오기

					Cell cell = null;
					cell = row.getCell(0); // 메뉴번호
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setMenuNo(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(1); // 메뉴순서
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setMenuOrdr(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(2); // 메뉴명
					if (cell != null) {
						vo.setMenuNm("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(3); // 상위메뉴번호
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setUpperMenuId(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(4); // 메뉴설명
					if (cell != null) {
						vo.setMenuDc("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(5); // 관련이미지경로
					if (cell != null) {
						vo.setRelateImagePath("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(6); // 관련이미지명
					if (cell != null) {
						vo.setRelateImageNm("" + cell.getRichStringCellValue());
					}
					cell = row.getCell(7); // 프로그램파일명
					if (cell != null) {
						vo.setProgrmFileNm("" + cell.getRichStringCellValue());
					}
				}
				if (insertMenuManageBind(vo)) {
					count++;
				}
			}
			if (count == rows - 1) {
				success = true;
			} else {
				success = false;
			}
		} catch (Exception e) {
			egovLogger.error("Exception:  " + e.getClass().getName());
			egovLogger.error("Exception  Message:  " + e.getMessage());
		}
		return success;
	}

	/**
	 * 프로그램 정보를 등록
	 * 
	 * @param progrmManageVO
	 */
	private boolean insertProgrm(ProgrmManageVO progrmManageVO) {
		progrmManageMapper.insertProgrm(progrmManageVO);
		return true;
	}

	/**
	 * 메뉴정보를 일괄 등록
	 * 
	 * @param menuManageVO
	 */
	private boolean insertMenuManageBind(MenuManageVO menuManageVO) {
		menuManageMapper.insertMenuManage(menuManageVO);
		return true;
	}

	/**
	 * 프로그램 정보 전체데이타 초기화
	 * 
	 */
	private boolean deleteAllProgrm() {
		try {
			progrmManageMapper.deleteAllProgrm();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * 프로그램변경내역 정보 전체데이타 초기화
	 * 
	 */
	private boolean deleteAllProgrmDtls() {
		try {
			progrmManageDtlMapper.deleteAllProgrmDtls();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	/**
	 * 메뉴정보 전체데이타 초기화
	 * 
	 */
	private boolean deleteAllMenuList() {
		try {
			menuManageMapper.deleteAllMenuList();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}