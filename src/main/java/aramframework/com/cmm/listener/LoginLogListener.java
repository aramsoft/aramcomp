package aramframework.com.cmm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import aramframework.com.cmm.LoginVO;
import aramframework.com.sym.log.clg.service.LoginLogService;
import aramframework.com.sym.log.clg.service.LoginLogVO;

public class LoginLogListener implements ApplicationListener<ApplicationEvent> {

	protected static final Logger LOG = LoggerFactory.getLogger(LoginLogListener.class);

	@Autowired 
	private LoginLogService loginLogService;

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AuthenticationSuccessEvent) { 
			try { 
				AuthenticationSuccessEvent success = (AuthenticationSuccessEvent) event; 
				Authentication auth = success.getAuthentication(); 
//				LOG.debug("LoginLog auth = " + auth.toString());

				Object principal = auth.getPrincipal();
				if (!(principal instanceof EgovUserDetails) ) {
					return;
				} 
				
				EgovUserDetails details = (EgovUserDetails) principal;
				LoginVO loginVO = (LoginVO) details.getEgovUserVO();

				String uniqId = "unknown";
				String ip = "";
				if( loginVO != null ) {
					uniqId = loginVO.getUniqId();
					ip = loginVO.getIp();
				}

				if( "127.0.0.1".equals(ip)) return;
				
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

			} catch(Exception Ex) {
				LOG.error("Error !! " + Ex.getMessage());
			}
		}
	}

}
