package aramframework.com.cop.com.dao;

import java.util.List;

import aramframework.com.cop.com.domain.UserInfVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 협업 활용 사용자 정보 조회를 위한 데이터 접근 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
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
