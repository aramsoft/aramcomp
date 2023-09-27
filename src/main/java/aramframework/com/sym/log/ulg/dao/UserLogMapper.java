package aramframework.com.sym.log.ulg.dao;

import java.util.List;

import aramframework.com.sym.log.ulg.domain.UserLogVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자 로그관리를 위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface UserLogMapper  {

	/**
	 * 사용자 로그 목록을 조회한다.
	 * 
	 * @param userLogVO
	 */
	public List<EgovMap> selectUserLogInf(UserLogVO userLogVO);

	/**
	 * 사용자 로그 목록의 숫자를 조회한다.
	 * 
	 * @param userLogVO
	 */
	public int selectUserLogInfCnt(UserLogVO userLogVO);

	/**
	 * 사용자 로그정보를 조회한다.
	 * 
	 * @param userLogVO
	 */
	public UserLogVO selectUserLog(UserLogVO userLogVO);

	/**
	 * 사용자 로그정보를 생성한다.
	 * 
	 */
	public void logInsertUserLog();

}
