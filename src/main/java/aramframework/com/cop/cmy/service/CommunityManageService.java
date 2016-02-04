package aramframework.com.cop.cmy.service;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import aramframework.com.cop.cmy.domain.CommunityMenuVO;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface CommunityManageService {

	/**
	 * 모든 커뮤니티 목록을 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectAllCmmnty(CommunityVO communityVO);

	/**
	 * 포트릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCmmntyListPortlet(CommunityVO communityVO);

	/**
	 * 커뮤니티 정보 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCommunityInfs(SearchVO searchVO);

	/**
	 * 커뮤니티 정보 목록 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCommunityInfsCnt(SearchVO searchVO);

	/**
	 * 커뮤니티에 대한  정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public CommunityVO selectCommunityInf(CommunityVO communityVO);

	/**
	 * HomeUrl로 부터 커뮤니티 ID를 조회한다.
	 * 
	 * @param homeUrl
	 */
	public String selectCommntyHomeUrl(String homeUrl);

	/**
	 * 커뮤니티 관리자 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public CommunityUserVO selectCommunityManagerInf(CommunityVO communityVO);

	/**
	 * 커뮤니티 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityBBSUseInf(CommunityVO communityVO);

	/**
	 * 커뮤니티용 템플릿 경로명을 조회한다.
	 * 
	 * @param communityVO
	 */
	public String selectCmmntyTemplat(CommunityVO communityVO);

	/**
	 * 커뮤니티에 대한 정보를 등록한다.
	 * 
	 * @param communityVO
	 */
	public void insertCommunityInf(CommunityVO communityVO);

	/**
	 * 커뮤니티에 대한 게시판 사용정보를 등록한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void insertCommunityBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티 정보를 수정한다.
	 * 
	 * @param communityVO
	 */
	public void updateCommunityInf(CommunityVO communityVO);

	/**
	 * 커뮤니티 게시판 사용정보를 수정한다.
	 * 
	 * @param boardUseInfVO
	 */
	public void updateCommunityBBSUseInf(BoardUseInfVO boardUseInfVO);

	/**
	 * 커뮤니티에 대한 정보를 삭제한다.
	 * 
	 * @param communityVO
	 */
	public void deleteCommunityInf(CommunityVO communityVO);

	// 커뮤니티 메뉴
	/**
	 * 커뮤니티에 대한 일반 메뉴 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityTopMenuInfs(CommunityVO communityVO);

	/**
	 * 커뮤니티에 대한 관리자 메뉴 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	public List<EgovMap> selectCommunityMgrMenuInfs(CommunityVO communityVO);

	/**
	 * 커뮤니티에 대한 서브  메뉴 정보를 조회한다.
	 * 
	 * @param communityMenuVO
	 */
	public List<EgovMap> selectCommunitySubMenuInfs(CommunityMenuVO communityMenuVO);

	// 커뮤니티 사용자
	/**
	 * 커뮤니티에 대한 특정 사용자 정보를 조회한다.
	 * 
	 * @param communityUserVO
	 */
	public CommunityUserVO selectCommunityUserInf(CommunityUserVO communityUserVO);

	/**
	 * 커뮤니티 사용자 정보를 등록한다.
	 * 
	 * @param communityUserVO
	 */
	public String insertCommunityUserInf(CommunityUserVO communityUserVO);

	/**
	 * 커뮤니티 사용자 정보를 확인한다.
	 * 
	 * @param communityUserVO
	 */
	public String checkCommunityUserInf(CommunityUserVO communityUserVO);

	/**
	 * 관리자 여부를 확인한다.
	 * 
	 * @param communityUserVO
	 */
	public boolean isManager(CommunityUserVO communityUserVO);

	/**
	 * 커뮤니티 사용자 정보를 수정한다.
	 * 
	 * @param communityUserVO
	 */
	public void updateCommunityUserInf(CommunityUserVO communityUserVO);

	/**
	 * 커뮤니티 사용자정보를 삭제한다.
	 * 
	 * @param communityUserVO
	 */
	public void deleteCommunityUserInf(CommunityUserVO communityUserVO);

	/**
	 * 커뮤니티 사용자정보를 제거한다.
	 * 
	 * @param communityUserVO
	 */
	public void eraseCommunityUserInf(CommunityUserVO communityUserVO);

	// 캐쉬
	/**
	 * 캐쉬로부터 커뮤니티 정보 및 메뉴정보를 가져온다.
	 * 
	 * @param communityId
	 * @param menuId
	 */
	public CommunityVO getCommunityInfo(String communityId, String menuId);

	/**
	 * 캐쉬로부터 커뮤니티 정보를 가져온다.
	 * 
	 * @param communityId
	 */
	public CommunityVO getCommunityInfo(String communityId);

	/**
	 * 캐쉬로부터 커뮤니티 사용자정보를 가져온다.
	 * 
	 * @param communityId
	 */
	public CommunityUserVO getCommunityUserInfo(String communityId);
	
}
