package aramframework.com.cop.clb.service;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 동호회 정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface ClubManageService {

	/**
	 * 동호회에 대한 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubInfs(ClubVO clubVO);

	/**
	 * 동호회에 대한 목록 총갯수을 조회한다.
	 * 
	 * @param clubVO
	 */
	public int selectClubInfsCnt(ClubVO clubVO);

		/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubInfsByTrgetId(ClubVO clubVO);

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록 총갯수을 조회한다.
	 * 
	 * @param clubVO
	 */
	public int selectClubInfsCntByTrgetId(ClubVO clubVO);

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubInfsByCmmntyId(ClubVO clubVO);

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록 총갯수을 조회한다.
	 * 
	 * @param clubVO
	 */
	public int selectClubInfsCntByCmmntyId(ClubVO clubVO);

	/**
	 * 커뮤니티에 사용되는 동호회 목록을 조회 한다(포틀릿 형식용).
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectClubListPortlet(SearchVO searchVO);

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회 한다(포틀릿 형식용).
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubListPortletByTrget(ClubVO clubVO);

	/**
	 * 동호회에 대한  정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubVO selectClubInf(ClubVO clubVO);

	/**
	 * 동호회 운영자 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubUserVO selectClubOperatorInf(ClubVO clubVO);

	/**
	 * 동호회에서 사용하는 게시판 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	public List<EgovMap> selectClubUseBBSInfs(ClubVO clubVO);

	/**
	 * 특정 동호회를 사용하는 커뮤니터 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public ClubVO selectCmmntyInfByClbId(ClubVO clubVO);

	/**
	 * 동호회 정보를 등록한다.
	 * 
	 * @param clubVO
	 */
	public void insertClubInf(ClubVO clubVO);

	/**
	 * 동호회 내용 및 사용자 정보를 수정한다.
	 * 
	 * @param clubVO
	 */
	public void updateClubInf(ClubVO clubVO);

	/**
	 * 동호회 정보를 삭제한다.
	 * 
	 * @param clubVO
	 */
	public void deleteClubInf(ClubVO clubVO);

	/**
	 * 동호회 템플릿 정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	public String selectClubTemplat(ClubVO clubVO);

	// 사용자 정보

	/**
	 * 동호회에 대한 특정 사용자 정보를 조회한다.
	 * 
	 * @param clubUserVO
	 */
	public ClubUserVO selectClubUserInf(ClubUserVO clubUserVO);

	/**
	 * 동호회 사용자 정보를 확인한다.
	 * 
	 * @param clubUserVO
	 */
	public String checkClubUserInf(ClubUserVO clubUserVO);

	/**
	 * 운영자 여부를 조회한다.
	 * 
	 * @param clubUserVO
	 * @return
	 */
	public boolean isOperator(ClubUserVO clubUserVO);

	/**
	 * 동호회 사용자 정보를 생성한다.
	 * 
	 * @param clubUserVO
	 */
	public String insertClubUserInf(ClubUserVO clubUserVO);

	/**
	 * 동호회에 대한 사용자 내용을 수정한다.
	 * 
	 * @param clubUserVO
	 */
	public void updateClubUserInf(ClubUserVO clubUserVO);

	/**
	 * 동호회 사용자 정보를 삭제한다.
	 * 
	 * @param clubUserVO
	 */
	public void deleteClubUserInf(ClubUserVO clubUserVO);

	/**
	 * 동호회 사용자 정보를 제거한다.
	 * 
	 * @param clubUserVO
	 */
	public void eraseClubUserInf(ClubUserVO clubUserVO);
	
}
