package aramframework.com.cmm.config.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.sym.log.clg.domain.LoginLogVO;
import aramframework.com.sym.log.clg.service.LoginLogService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 로그인 로그생성을 위한 Advice 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LoginLogAdvice {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginLogService loginLogService;

	/**
	 * 로그인 로그정보를 생성한다. 
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void logLogin() throws Throwable {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String uniqId = "unknown";
		String ip = "";
		if( loginVO != null ) {
			uniqId = loginVO.getUniqId();
			ip = loginVO.getIp();
		}
//		if( "127.0.0.1".equals(ip) || "".equals(ip)) return;
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute login log!!");
		}
		
		LoginLogVO loginLogVO = new LoginLogVO();
		loginLogVO.setLoginId(uniqId);
		loginLogVO.setLoginIp(ip);
		loginLogVO.setLoginMthd("I"); // 로그인:I, 로그아웃:O
		loginLogVO.setErrOccrrAt("N");
		loginLogVO.setErrorCode("");
		loginLogService.logInsertLoginLog(loginLogVO);
	}

	/**
	 * 로그아웃 로그정보를 생성한다. 
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void logLogout() throws Throwable {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String uniqId = "";
		String ip = "";
		if( loginVO != null ) {
			uniqId = loginVO.getUniqId();
			ip = loginVO.getIp();
		}
//		if( "127.0.0.1".equals(ip) || "".equals(ip)) return;

		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute logout log!!");
		}
		
		LoginLogVO loginLogVO = new LoginLogVO();
		loginLogVO.setLoginId(uniqId);
		loginLogVO.setLoginIp(ip);
		loginLogVO.setLoginMthd("O"); // 로그인:I, 로그아웃:O
		loginLogVO.setErrOccrrAt("N");
		loginLogVO.setErrorCode("");
		loginLogService.logInsertLoginLog(loginLogVO);
	}

}
