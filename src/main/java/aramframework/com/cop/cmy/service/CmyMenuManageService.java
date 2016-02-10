package aramframework.com.cop.cmy.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.cmy.dao.CmyMenuManageMapper;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 메뉴를 처리하는 비즈니스 구현 클래스를 정의한다.
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
public class CmyMenuManageService extends EgovAbstractServiceImpl {

	@Autowired 
	private CmyMenuManageMapper cmyMenuManageMapper;	

	@Autowired 
	private EgovExcelService excelCmyMenuService;

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
	public void deleteMenuManageList(String trgetId, String checkedMenuNoForDel) {

		String[] delMenuNo = checkedMenuNoForDel.split(",");

		if (delMenuNo == null || (delMenuNo.length == 0)) {
			egovLogger.debug("delMenuNo is empty, checkedMenuNoForDel = " + checkedMenuNoForDel);
			return;
		}
		
		CommunityMenuVO communityMenuVO = null;
		for (int i = 0; i < delMenuNo.length; i++) {
			communityMenuVO = new CommunityMenuVO();
			communityMenuVO.setTrgetId(trgetId);
			communityMenuVO.setMenuNo(Integer.parseInt(delMenuNo[i]));
			cmyMenuManageMapper.deleteMenuManage(communityMenuVO);
		}
	}

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param communityMenuVO
	 */
	public CommunityMenuVO selectMenuManage(CommunityMenuVO communityMenuVO) {
		CommunityMenuVO resultVo = cmyMenuManageMapper.selectMenuManage(communityMenuVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, communityMenuVO); 
		return resultVo;
	}

	private void checkMenuAlias(CommunityMenuVO communityMenuVO) {
		String menuAlias = communityMenuVO.getMenuAlias();
		if( menuAlias == null || "".equals(menuAlias) ) {
			communityMenuVO.setMenuAlias(String.valueOf(communityMenuVO.getMenuNo()));
		} else {
			try{
				Integer.parseInt(menuAlias);
				communityMenuVO.setMenuAlias(String.valueOf(communityMenuVO.getMenuNo()));
			} catch(Exception ex) {	}
		}
	}
	
	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param communityMenuVO
	 */
	public void insertMenuManage(CommunityMenuVO communityMenuVO) {
		checkMenuAlias(communityMenuVO);
		cmyMenuManageMapper.insertMenuManage(communityMenuVO);
	}

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param communityMenuVO
	 */
	public void updateMenuManage(CommunityMenuVO communityMenuVO) {
		checkMenuAlias(communityMenuVO);
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
	public int selectMenuNoByPk(CommunityMenuVO communityMenuVO) {
		return cmyMenuManageMapper.selectMenuNoByPk(communityMenuVO);
	}

	/**
	 * 메뉴별명으로부터 메뉴번호 조회
	 * 
	 * @param cmmntyId
	 * @param menuAlias
	 */
	public String selectMenuNoByMenuAlias(String cmmntyId, String menuAlias) {
		CommunityMenuVO communityMenuVO = new CommunityMenuVO();
		communityMenuVO.setTrgetId(cmmntyId);
		communityMenuVO.setMenuAlias(menuAlias);
		return cmyMenuManageMapper.selectMenuNoByMenuAlias(communityMenuVO);
	}
	
	/**
	 * 메뉴 엑셀파일을 등록한다.
	 * 
	 * @param file
	 * @param cmmntyId
	 */
	public void insertExcelMenu(InputStream file, String cmmntyId) {
		cmyMenuManageMapper.deleteMenuManageTrget(cmmntyId);
		try {
			String sqlId = "aramframework.com.cop.cmy.dao.CmyMenuManageMapper.insertMenuManage";
			excelCmyMenuService.uploadExcel(sqlId, file, 1, (long) 5000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

}