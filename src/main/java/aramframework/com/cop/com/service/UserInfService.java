package aramframework.com.cop.com.service;

import java.util.List;

import aramframework.com.cop.com.domain.UserInfVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 협업 기능에서 사용자 정보를 관리하기 위한 서비스 인터페이스 클래스
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

public interface UserInfService {

	/**
	 * 커뮤니티  및 동호회 사용자 권한을 확인한다.
	 * 
	 * @param defaultYN
	 */
	public String checkCommunityUser(String defaultYN);

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	public String checkCommunityManager();

		/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectUserList(UserInfVO userInfVO);

	/**
	 * 사용자 정보에 대한 목록 총갯수를 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectUserListCnt(UserInfVO userInfVO);

	/**
	 * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectAllCmmntyUser(UserInfVO userInfVO);

	/**
	 * 커뮤니티 사용자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectCmmntyUserList(UserInfVO userInfVO);

	/**
	 * 커뮤니티 사용자 목록 총갯수을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectCmmntyUserListCnt(UserInfVO userInfVO);

	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectCmmntyMngrList(UserInfVO userInfVO);

	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectCmmntyMngrListCnt(UserInfVO userInfVO);

}	