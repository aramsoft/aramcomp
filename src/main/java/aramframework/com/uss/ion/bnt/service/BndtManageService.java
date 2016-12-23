package aramframework.com.uss.ion.bnt.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.bnt.dao.BndtManageMapper;
import aramframework.com.uss.ion.bnt.domain.BndtCeckManageVO;
import aramframework.com.uss.ion.bnt.domain.BndtDiaryVO;
import aramframework.com.uss.ion.bnt.domain.BndtManageVO;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 당직관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BndtManageService extends EgovAbstractServiceImpl {

//	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	@Autowired
	private BndtManageMapper bndtManageMapper;	

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) {
		return bndtManageMapper.selectBndtManageList(bndtManageVO);
	}

	/**
	 * 당직관리목록 총 갯수를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtManageListCnt(BndtManageVO bndtManageVO) {
		return bndtManageMapper.selectBndtManageListCnt(bndtManageVO);
	}

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO) {
		BndtManageVO resultVo = bndtManageMapper.selectBndtManage(bndtManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(bndtManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 * 
	 * @param bndtManageVO
	 */
	public void insertBndtManage(BndtManageVO bndtManageVO) {
		bndtManageMapper.insertBndtManage(bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * 
	 * @param bndtManageVO
	 */
	public void updtBndtManage(BndtManageVO bndtManageVO) {
		bndtManageMapper.updtBndtManage(bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * 
	 * @param bndtManageVO
	 */
	public void deleteBndtManage(BndtManageVO bndtManageVO) {
		bndtManageMapper.deleteBndtManage(bndtManageVO);
	}

	/**
	 * 당직일지 갯수를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtDiaryTotCnt(BndtManageVO bndtManageVO) {
		return bndtManageMapper.selectBndtDiaryTotCnt(bndtManageVO);
	}

	/***** 당직 체크관리 *****/

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public List<EgovMap> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) {
		return bndtManageMapper.selectBndtCeckManageList(bndtCeckManageVO);
	}

	/**
	 * 당직체크관리목록 총 갯수를 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public int selectBndtCeckManageListCnt(BndtCeckManageVO bndtCeckManageVO) {
		return bndtManageMapper.selectBndtCeckManageListCnt(bndtCeckManageVO);
	}

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		BndtCeckManageVO resultVo = bndtManageMapper.selectBndtCeckManage(bndtCeckManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(bndtCeckManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void insertBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		bndtManageMapper.insertBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void updtBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		bndtManageMapper.updtBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public void deleteBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		bndtManageMapper.deleteBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 당직체크 중복여부 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	public int selectBndtCeckManageDplctAt(BndtCeckManageVO bndtCeckManageVO) {
		return bndtManageMapper.selectBndtCeckManageDplctAt(bndtCeckManageVO);
	}

	/***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * 
	 * @param bndtDiaryVO
	 */
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO) {
		return bndtManageMapper.selectBndtDiary(bndtDiaryVO);
	}

	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 * 
	 * @param bndtDiaryVO
	 * @param diaryForInsert
	 */
	public void insertBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForInsert) {

		String[] bndtDiaryValues = diaryForInsert.split("[@]");
		String[] sTempBndtDiary;
		String sTemp = null;

		BndtDiaryVO bndtDiaryTemp;
		for (int i = 0; i < bndtDiaryValues.length; i++) {
			bndtDiaryTemp = new BndtDiaryVO();
			sTemp = bndtDiaryValues[i];
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiaryVO.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiaryVO.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setFrstRegisterId(bndtDiaryVO.getFrstRegisterId());

			bndtManageMapper.insertBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 * 
	 * @param bndtDiaryVO
	 * @param diaryForUpdt
	 */
	public void updtBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForUpdt) {

		String[] bndtDiaryValues = diaryForUpdt.split("[@]");
		String[] sTempBndtDiary;
		String sTemp = null;

		BndtDiaryVO bndtDiaryTemp;
		for (int i = 0; i < bndtDiaryValues.length; i++) {
			bndtDiaryTemp = new BndtDiaryVO();
			sTemp = bndtDiaryValues[i];
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiaryVO.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiaryVO.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setLastUpdusrId(bndtDiaryVO.getLastUpdusrId());

			bndtManageMapper.updtBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 * 
	 * @param bndtDiaryVO
	 */
	public void deleteBndtDiary(BndtDiaryVO bndtDiaryVO) {
		bndtManageMapper.deleteBndtDiary(bndtDiaryVO);
	}

	/* ### 엑셀 일괄처리 프로세스 ### */

	/**
	 * 당직자 excel생성
	 * 
	 * @param inputStream
	 */
	public List<BndtManageVO> selectBndtManageBnde(InputStream inputStream) {
		String sTempNm = null;
		String sTempId = null;

		List<BndtManageVO> list = new ArrayList<BndtManageVO>();
		try {
			String sBndtDe = null;
			Workbook hssfWB = excelZipService.loadWorkbook(inputStream);
			// 엑셀 파일 시트 갯수 확인 sheet = 1
			if (hssfWB.getNumberOfSheets() == 1) {
				Sheet bndtSheet = hssfWB.getSheetAt(0); // 당직자 시트 가져오기
//				Row bndtRow = bndtSheet.getRow(1); // 당직자 row 가져오기
//				int bndtSheetRowCnt = bndtRow.getPhysicalNumberOfCells(); // 당직자 cell Cnt
				int rowsCnt = bndtSheet.getPhysicalNumberOfRows(); // 행 갯수 가져오기

				for (int j = 1; j < rowsCnt; j++) { // row 루프
					BndtManageVO bndtManageVO = new BndtManageVO();
					Row row = bndtSheet.getRow(j); // row 가져오기
					if (row != null) {
//						int cells = row.getPhysicalNumberOfCells(); // cell 갯수 가져오기
						Cell cell = null;
						cell = row.getCell(0); // 당직일자
						if (cell != null) {
							sBndtDe = cell.getRichStringCellValue().toString();
						}
						cell = row.getCell(1); // 당직자ID
						if (cell != null) {
							sTempId = cell.getRichStringCellValue().toString();
						}
						cell = row.getCell(2); // 당직자명
						if (cell != null) {
							sTempNm = cell.getRichStringCellValue().toString();
						}
						bndtManageVO.setTempBndtNm(sTempNm); // 당직자ID
						bndtManageVO.setTempBndtId(sTempId); // 당직자명
						bndtManageVO = bndtManageMapper.selectBndtManageBnde(bndtManageVO);
						bndtManageVO.setBndtDe(sBndtDe);
						bndtManageVO.setDateWeek(getDateWeekInt(sBndtDe));
						bndtManageVO.setTempBndtWeek(getDateWeekString(sBndtDe));
						list.add(bndtManageVO);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * 당직정보를 일괄등록처리한다.
	 * 
	 * @param bndtManageVO
	 * @param checkedBndtManageForInsert
	 */
	public void insertBndtManageBnde(BndtManageVO bndtManageVO, String checkedBndtManageForInsert) {
		String[] bndtManageValues = checkedBndtManageForInsert.split("[$]");
		String[] sTempBndtManage;
		String sTemp = null;

		BndtManageVO bndtManage;
		if (checkedBndtManageForInsert != null && !checkedBndtManageForInsert.equals("")) {
			for (int i = 0; i < bndtManageValues.length; i++) {
				bndtManage = new BndtManageVO();
				sTemp = bndtManageValues[i];
				sTempBndtManage = sTemp.split(",");
				bndtManage.setBndtDe(sTempBndtManage[0]);
				bndtManage.setBndtId(sTempBndtManage[1]);
				bndtManage.setRemark("당직일괄등록");
				bndtManage.setFrstRegisterId(bndtManageVO.getFrstRegisterId());

				bndtManageMapper.insertBndtManage(bndtManage);
			}
		}
	}

	/**
	 * 당직관리 건수를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO) {
		return bndtManageMapper.selectBndtManageMonthCnt(bndtManageVO);
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 * 
	 * @param sDate
	 */
	private int getDateWeekInt(String sDate) throws Exception {

		String sDayOfWeek = null;
		int iWeek = 0;
		sDayOfWeek = StringUtil.removeMinusChar(sDate);
		Calendar target_day = Calendar.getInstance();
		target_day.set(Integer.parseInt(sDayOfWeek.substring(0, 4)), Integer.parseInt(sDayOfWeek.substring(4, 6)) - 1,
				Integer.parseInt(sDayOfWeek.substring(6, 8)));
		iWeek = target_day.get(Calendar.DAY_OF_WEEK);
		return iWeek;
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 * 
	 * @param sDate
	 */
	private String getDateWeekString(String sDate) {

		String sDayOfWeek = null;
		String sDayOfWeekReturnValue = null;
		sDayOfWeek = StringUtil.removeMinusChar(sDate);
		String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
		Calendar target_day = new GregorianCalendar();
		target_day.set(Integer.parseInt(sDayOfWeek.substring(0, 4)), Integer.parseInt(sDayOfWeek.substring(4, 6)) - 1,
				Integer.parseInt(sDayOfWeek.substring(6, 8)));

		sDayOfWeekReturnValue = DateUtil.formatDate(sDayOfWeek, "-") + " " + dayOfWeek[target_day.get(Calendar.DAY_OF_WEEK) - 1];

		return sDayOfWeekReturnValue;
	}
	
}
