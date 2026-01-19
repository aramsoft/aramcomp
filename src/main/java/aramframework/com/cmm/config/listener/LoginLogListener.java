package aramframework.com.cmm.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import org.egovframe.rte.fdl.security.userdetails.EgovUserDetails;
import aramframework.com.sym.log.clg.domain.LoginLogVO;
import aramframework.com.sym.log.clg.service.LoginLogService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 로그인 로그를 기록하기 위한 스프링 이벤트 리스너 클래스
 * (사용안함 대신 AOP에서 처리됨)
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LoginLogListener implements ApplicationListener<ApplicationEvent> {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

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

				String userId = "unknown";
				String ip = "";
				if( loginVO != null ) {
					userId = loginVO.getUserId();
					ip = loginVO.getIp();
				}

				if( "127.0.0.1".equals(ip)) return;
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("Execute login log!!");
				}
				
				LoginLogVO loginLogVO = new LoginLogVO();
				loginLogVO.setLoginId(userId);
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
