package aramframework.com.cop.cmy.service;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.cmy.dao.CmyMenuManageMapper;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import aramframework.com.cop.cmy.excel.ExcelCmyMenuMapping;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 메뉴를 처리하는 비즈니스 구현 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class CmyMenuManageService extends EgovAbstractServiceImpl {

	@Autowired 
	private CmyMenuManageMapper cmyMenuManageMapper;	

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuManageList(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuManageList(communityMenuVO);
	}

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuManageListCnt(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuManageListCnt(communityMenuVO);
	}

	/**
	 * 메뉴목록을 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectMenuListExcel(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuListExcel(communityMenuVO);
	}

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedMenuNoForDel
	 */
	public void deleteMenuManageList(String trgetId, String[] delMenuNms) {

		CommunityMenuVO communityMenuVO = null;
		for (int i = 0; i < delMenuNms.length; i++) {
			communityMenuVO = new CommunityMenuVO();
			communityMenuVO.setTrgetId(trgetId);
			communityMenuVO.setMenuNm(delMenuNms[i]);
			cmyMenuManageMapper.deleteMenuManage(communityMenuVO);
		}
	}

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param communityMenuVO
	 */
	public CommunityMenuVO selectMenuManage(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuManage(communityMenuVO);
	}

	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param communityMenuVO
	 */
	public void insertMenuManage(CommunityMenuVO communityMenuVO) {
		cmyMenuManageMapper.insertMenuManage(communityMenuVO);
	}

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param communityMenuVO
	 */
	public void updateMenuManage(CommunityMenuVO communityMenuVO) {
		cmyMenuManageMapper.updateMenuManage(communityMenuVO);
	}

	/**
	 * 메뉴 정보를 삭제
	 * 
	 * @param communityMenuVO
	 */
	public void deleteMenuManage(CommunityMenuVO communityMenuVO) {
		cmyMenuManageMapper.deleteMenuManage(communityMenuVO);
	}

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public int selectMenuNmByPk(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuNmByPk(communityMenuVO);
	}

	/**
	 * 메뉴명으로부터 메뉴위치 조회
	 * 
	 * @param cmmntyId
	 * @param menuAlias
	 */
	public String selectMenuPosByMenuNm(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuPosByMenuNm(communityMenuVO);
	}
	
	/**
	 * 메뉴 엑셀파일을 등록 또는 수정한다.
	 * 
	 * @param file
	 * @param cmmntyId
	 */
	public void syncExcelMenu(CommunityMenuVO communityMenuVO, InputStream fis) throws Exception {

		ExcelCmyMenuMapping mapping = new ExcelCmyMenuMapping();
			
		Workbook workbook = new XSSFWorkbook(fis);
		int sheetNum = workbook.getNumberOfSheets();
		for (int k = 0; k < sheetNum; k++) {
			Sheet sheet = workbook.getSheetAt(k);
			int rows = sheet.getPhysicalNumberOfRows();
			if( rows == 0 ) continue;
			
			mapping.setCells(sheet.getRow(0));	// cells 수 설정
			for (int r = 1; r < rows; r++) {
    			Row row = sheet.getRow(r);
    			if (row != null) {
     				CommunityMenuVO vo = (CommunityMenuVO)mapping.mappingColumn(row);
     				vo.setTrgetId(communityMenuVO.getTrgetId());
 
     				CommunityMenuVO resultVO = cmyMenuManageMapper.selectMenuManage(vo);
    		    	if ( resultVO == null ) {
    		    		cmyMenuManageMapper.insertMenuManage(vo);
    		    	} else {
    		    		cmyMenuManageMapper.updateMenuManage(vo);
    		    	}	
    			}
			}
		}
		workbook.close();
	}

}