package aramframework.com.sym.mnu.bmm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.mnu.bmm.dao.BkmkMenuManageMapper;
import aramframework.com.sym.mnu.bmm.domain.BkmkMenuManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 바로가기메뉴를 관리하는 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class BkmkMenuManageService extends EgovAbstractServiceImpl {

	@Autowired
	private BkmkMenuManageMapper bkmkMenuManageMapper;	 

	/**
	 * 바로가기메뉴관리 정보의 전체목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO) {
		return  bkmkMenuManageMapper.selectBkmkMenuManageList(bkmkMenuManageVO);
	}

	/**
	 * 바로가기메뉴관리 정보의 전체목록 총갯수을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO) {
		return bkmkMenuManageMapper.selectBkmkMenuManageListCnt(bkmkMenuManageVO);
	}

	/**
	 * 바로가기메뉴관리 정보를 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public BkmkMenuManageVO selectBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO) {
		BkmkMenuManageVO resultVo = bkmkMenuManageMapper.selectBkmkMenuManage(bkmkMenuManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(bkmkMenuManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 바로가기메뉴관리 정보를 등록한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void insertBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO) {
		bkmkMenuManageMapper.insertBkmkMenuManage(bkmkMenuManageVO);
	}

	/**
	 * 바로가기메뉴관리 정보를 삭제한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public void deleteBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO) {
		bkmkMenuManageMapper.deleteBkmkMenuManage(bkmkMenuManageVO);
	}

	/**
	 * 등록할 메뉴정보 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO) {
		return  bkmkMenuManageMapper.selectBkmkMenuList(bkmkMenuManageVO);
	}

	/**
	 * 등록할 메뉴정보 총갯수을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO) {
		return bkmkMenuManageMapper.selectBkmkMenuListCnt(bkmkMenuManageVO);
	}
	
	/**
	 * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public List<EgovMap> selectBkmkPreviewList(BkmkMenuManageVO bkmkMenuManageVO) {
		return bkmkMenuManageMapper.selectBkmkPreview(bkmkMenuManageVO);
	}

	/**
	 * 선택된 메뉴의 URL 을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	public String selectUrl(BkmkMenuManageVO bkmkMenuManageVO) {
		return bkmkMenuManageMapper.selectUrl(bkmkMenuManageVO);
	}
	
}
