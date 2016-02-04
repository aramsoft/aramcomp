package aramframework.com.cop.com.service;

import java.util.List;

import aramframework.com.cop.com.domain.UserInfVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 협업 활용 사용자 정보 조회를 위한 데이터 접근 클래스
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

@Mapper
public interface UserInfMapper {

	/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 * 
	 * @param userInfVO
	 */
	public List<EgovMap> selectUserList(UserInfVO userInfVO);

	/**
	 * 사용자 정보에 대한 목록 전체 건수를 조회한다.
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
	 * 커뮤니티 사용자 목록에 대한 전체 건수를 조회한다.
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
	 * 커뮤니티 관리자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param userInfVO
	 */
	public int selectCmmntyMngrListCnt(UserInfVO userInfVO);

}
