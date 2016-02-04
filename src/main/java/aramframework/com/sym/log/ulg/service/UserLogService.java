package aramframework.com.sym.log.ulg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.log.ulg.domain.UserLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사용자 로그관리를 위한 서비스 구현 클래스
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
		UserLogVO resultVo = userLogMapper.selectUserLog(userLog);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, userLog); 
		return resultVo;
	}

	/**
	 * 사용자 로그정보를 생성한다.
	 * 
	 */
	public void logInsertUserLog() {
		userLogMapper.logInsertUserLog();
	}

}
