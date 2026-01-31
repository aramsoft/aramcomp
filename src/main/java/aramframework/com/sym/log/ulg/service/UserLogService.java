package aramframework.com.sym.log.ulg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.log.ulg.dao.UserLogMapper;
import aramframework.com.sym.log.ulg.domain.UserLogVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자 로그관리를 위한 서비스 구현 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class UserLogService extends EgovAbstractServiceImpl {

	@Autowired
	private UserLogMapper userLogMapper;	

	/**
	 * 사용자 로그 목록을 조회한다.
	 * 
	 * @param userLogVO
	 */
	public List<EgovMap> selectUserLogInf(UserLogVO userLogVO) {
		return userLogMapper.selectUserLogInf(userLogVO);
	}

	/**
	 * 사용자 로그 총 갯수를 조회한다.
	 * 
	 * @param userLogVO
	 */
	public int selectUserLogInfCnt(UserLogVO userLogVO) {
		return userLogMapper.selectUserLogInfCnt(userLogVO);
	}

	/**
	 * 사용자 로그정보를 조회한다.
	 * 
	 * @param userLogVO
	 */
	public UserLogVO selectUserLog(UserLogVO userLog) {
		return userLogMapper.selectUserLog(userLog);
	}

	/**
	 * 사용자 로그정보를 생성한다.
	 * 
	 */
	public void logInsertUserLog() {
		userLogMapper.logInsertUserLog();
	}

}
