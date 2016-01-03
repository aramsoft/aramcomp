package aramframework.com.uss.ion.ans.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.ion.ans.service.AnnvrsryManageVO;
import aramframework.com.uss.ion.ans.service.AnnvrsryManageService;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 기념일관리에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("annvrsryManageService")
public class AnnvrsryManageServiceImpl extends EgovAbstractServiceImpl implements AnnvrsryManageService {

	@Resource(name = "annvrsryManageMapper")
	private AnnvrsryManageMapper annvrsryManageMapper;	

//	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	/** ID Generation */
	@Resource(name = "annvrsryManageIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public List<EgovMap> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO) {
		return annvrsryManageMapper.selectAnnvrsryManageList(annvrsryManageVO);
	}

	/**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public int selectAnnvrsryManageListCnt(AnnvrsryManageVO annvrsryManageVO) {
		return annvrsryManageMapper.selectAnnvrsryManageListCnt(annvrsryManageVO);
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
		AnnvrsryManageVO resultVo = annvrsryManageMapper.selectAnnvrsryManage(annvrsryManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, annvrsryManageVO); 
		return resultVo;
	}

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void insertAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
		try {
			annvrsryManageVO.setAnnId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		annvrsryManageMapper.insertAnnvrsryManage(annvrsryManageVO);
	}

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void updateAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
		annvrsryManageMapper.updateAnnvrsryManage(annvrsryManageVO);
	}

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public void deleteAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) {
		annvrsryManageMapper.deleteAnnvrsryManage(annvrsryManageVO);
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public List<EgovMap> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO) {
		return annvrsryManageMapper.selectAnnvrsryGdcc(annvrsryManageVO);
	}

	/**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	public int selectAnnvrsryManageDplctAt(AnnvrsryManageVO annvrsryManageVO) {
		return annvrsryManageMapper.selectAnnvrsryManageDplctAt(annvrsryManageVO);
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 * 
	 */
/*
	private long getDateCount(EgovMap egovMap) throws Exception {

		// 날짜 사이의 기간 산출 
		long resultDay = 0;
		Calendar to_day = Calendar.getInstance(); // Calendar객체를 생성합니다.
		Calendar target_day = Calendar.getInstance();

		String sAnnvrsryDe = null;

		sAnnvrsryDe = StringUtil.removeMinusChar((String)egovMap.get("annvrsryDe"));

		// 매년반복일 경우
		if ("1".equals((String)egovMap.get("reptitSe"))) {
			sAnnvrsryDe = Integer.toString(to_day.get(Calendar.YEAR)) + sAnnvrsryDe.substring(4, 6) + sAnnvrsryDe.substring(6, 8);
		}

		// 음력인 경우 양력으로 환산
		if ("2".equals((String)egovMap.get("cldrSe"))) {
			sAnnvrsryDe = DateUtil.toSolar(sAnnvrsryDe, 0);
		}

		if (sAnnvrsryDe != null && !sAnnvrsryDe.equals("")) {
			target_day.set(Integer.parseInt(sAnnvrsryDe.substring(0, 4)), Integer.parseInt(sAnnvrsryDe.substring(4, 6)) - 1,
					Integer.parseInt(sAnnvrsryDe.substring(6, 8)));
		} else {
			target_day.set(to_day.get(Calendar.YEAR), to_day.get(Calendar.MONTH) + 1, to_day.get(Calendar.DATE));
		}

		long resultTime = target_day.getTime().getTime() - to_day.getTime().getTime(); // 차이 구하기
		if (resultTime > 0) {
			resultDay = resultTime / (1000 * 60 * 60 * 24);// 일로 바꾸기
		} else
			resultDay = -1;
		// annvrsryManageVO.setAnnvrsryBeginDe(Long.toString(resultDay));
		return resultDay;
	}
*/
	/* ### 엑셀 일괄처리 프로세스 ### */

	/**
	 * 기념일정보 excel생성
	 * 
	 * @param inputStream
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryManageBnde(InputStream inputStream) {

		String sTempId = null; // 사용자ID
		String sTempNm = null; // 사용자명
		String sTempAnnvrsryDe = null; // 기념일자
		String sTempCldrSe = null; // 양/음 구분
		String sTempAnnvrsrySe = null; // 기념일구분
		String sTempAnnvrsryNm = null; // 기념일명
		String sTempReptitSe = null; // 반복여부

		List<AnnvrsryManageVO> list = new ArrayList<AnnvrsryManageVO>();
		try {
			Workbook hssfWB = excelZipService.loadWorkbook(inputStream);
			// 엑셀 파일 시트 갯수 확인 sheet = 1
			if (hssfWB.getNumberOfSheets() == 1) {
				Sheet annvrsrySheet = hssfWB.getSheetAt(0); // 기념일관리 시트 가져오기
//				HSSFRow annvrsryRow = annvrsrySheet.getRow(1); // 기념일 row 가져오기
//				int annvrsrySheetRowCnt = annvrsryRow.getPhysicalNumberOfCells(); // 기념일 cell Cnt
				int rowsCnt = annvrsrySheet.getPhysicalNumberOfRows(); // 행 갯수 가져오기

				// 사용자ID 기념일자 양/음 구분 기념일구분 기념일명
				for (int j = 1; j < rowsCnt; j++) { // row 루프
					AnnvrsryManageVO annvrsryManageVO = new AnnvrsryManageVO();
					Row row = annvrsrySheet.getRow(j); // row 가져오기
					if (row != null) {
//						int cells = row.getPhysicalNumberOfCells(); // cell 갯수 가져오기
						Cell cell = null;
						cell = row.getCell(0); // 사용자ID
						if (cell != null) {
							sTempId = cell.getRichStringCellValue().toString();
						}

						cell = row.getCell(1); // 사용자명
						if (cell != null) {
							sTempNm = cell.getRichStringCellValue().toString();
						}

						cell = row.getCell(2); // 기념일자
						if (cell != null) {
							sTempAnnvrsryDe = cell.getRichStringCellValue().toString();
						}

						cell = row.getCell(3); // 양/음구분
						if (cell != null) {
							sTempCldrSe = cell.getRichStringCellValue().toString();
						}

						cell = row.getCell(4); // 기념일구분
						if (cell != null) {
							sTempAnnvrsrySe = (String) cell.getStringCellValue();
						}
						cell = row.getCell(5); // 기념일명
						if (cell != null) {
							sTempAnnvrsryNm = cell.getRichStringCellValue().toString();
						}
						cell = row.getCell(6); // 반복여부
						if (cell != null) {
							sTempReptitSe = cell.getRichStringCellValue().toString();
						}
						annvrsryManageVO.setUsid(sTempId); // 당직자ID
						annvrsryManageVO.setUsNm(sTempNm); // 당직자명
						annvrsryManageVO.setAnnvrsrySe(sTempAnnvrsrySe);
						annvrsryManageMapper.selectAnnvrsryManageBnde(annvrsryManageVO);
						annvrsryManageVO.setAnnvrsryDe(DateUtil.formatDate(sTempAnnvrsryDe, "-"));
						annvrsryManageVO.setCldrSe(sTempCldrSe);
						annvrsryManageVO.setAnnvrsryNm(sTempAnnvrsryNm);
						annvrsryManageVO.setReptitSe(sTempReptitSe);
						list.add(annvrsryManageVO);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * 기념일정보를 일괄등록처리한다.
	 * 
	 * @param annvrsryManageVO
	 * @param checkedAnnvrsryManageForInsert
	 */
	public void insertAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO, String checkedAnnvrsryManageForInsert) {
		AnnvrsryManageVO annvrsryManage;

		String[] annvrsryManageValues = checkedAnnvrsryManageForInsert.split("[$]");
		String[] sTempAnnvrsryManage;
		String sTemp = null;

		if (checkedAnnvrsryManageForInsert != null && !checkedAnnvrsryManageForInsert.equals("")) {
			for (int i = 0; i < annvrsryManageValues.length; i++) {
				annvrsryManage = new AnnvrsryManageVO();
				sTemp = annvrsryManageValues[i];
				sTempAnnvrsryManage = sTemp.split(",");
				annvrsryManage.setUsid(sTempAnnvrsryManage[0]);

				annvrsryManage.setAnnvrsryDe(StringUtil.removeMinusChar(sTempAnnvrsryManage[1]));
				annvrsryManage.setCldrSe(sTempAnnvrsryManage[2]);
				annvrsryManage.setAnnvrsrySe(sTempAnnvrsryManage[3]);
				annvrsryManage.setAnnvrsryNm(sTempAnnvrsryManage[4]);
				if ("Y".equals(sTempAnnvrsryManage[5]))
					annvrsryManage.setReptitSe("1");
				annvrsryManage.setAnnvrsryBeginDe("7");
				annvrsryManage.setAnnvrsrySetup("Y");
				annvrsryManage.setMemo("기념일 일괄등록");

				try {
					annvrsryManage.setAnnId(idgenService.getNextStringId());
				} catch (FdlException e) {
					throw new RuntimeException(e);
				}

				annvrsryManage.setFrstRegisterId(annvrsryManageVO.getFrstRegisterId());
				annvrsryManageMapper.insertAnnvrsryManage(annvrsryManage);
			}
		}
	}

	/**
	 * 기념일관리 건수를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 * 
	 * public int selectAnnvrsryManageMonthCnt(AnnvrsryManageVO annvrsryManageVO) throws Exception { 
	 * 		return annvrsryManageDAO.selectAnnvrsryManageMonthCnt(annvrsryManageVO); 
	 * }
	 */

}
