package aramframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import aramframework.com.cmm.LoginVO;
import aramframework.com.uat.sso.service.SSOService;
import aramframework.com.uat.uia.service.LoginService;

/**
 * 
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

public class SSOLoginFilter implements Filter {

	private FilterConfig config;

	protected final static Logger LOG = LoggerFactory.getLogger(SSOLoginFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		SSOService egovSSOService = null;
		try {
			egovSSOService = (SSOService) act.getBean("egovSSOService");
			if (egovSSOService == null) {// 2011.10.27 보안점검 3차 후속조치
				LOG.error("Fail to create 'EgovSSOService' object");
				chain.doFilter(request, response);
				return;
			}
		} catch (NoSuchBeanDefinitionException ex) {
			LOG.error("No SSO ServiceImpl Class!");
		}

		LoginService loginService = (LoginService) act.getBean("loginService");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String isLocallyAuthenticated = (String) session.getAttribute("isLocallyAuthenticated");
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");
		boolean isSSOLoggedOn = false;

		if (isLocallyAuthenticated != null && (isLocallyAuthenticated.equals("true"))) {
			if (isRemotelyAuthenticated == null) {

				try {
					// sso서버에 토큰 생성
					egovSSOService.requestIssueToken(request, response);
					// 로컬 인증 적용 여부 완료를 세션에 저장
					session.setAttribute("isLocallyAuthenticated", "true");
					// sso 인증 완료 여부를 세션에 저장
					session.setAttribute("isRemotelyAuthenticated", "true");

				} catch (Exception ex) {
					session.setAttribute("isRemotelyAuthenticated", "fail");
					LOG.debug("SSO Authentication fail : " + ex.getMessage());
				}

			}
		} else if (isLocallyAuthenticated == null) {
			if (isRemotelyAuthenticated == null) {
				// sso서버에 토큰이 존재하는지 체크함
				isSSOLoggedOn = egovSSOService.hasTokenInSSOServer(httpRequest, response);
				if (isSSOLoggedOn) {
					// 서버에 토큰이 존재할 경우 로컬 인증을 위해 isRemotelyAuthenticated true로 변경
					session.setAttribute("isRemotelyAuthenticated", "true");

					// 로컬 DB인증을 위한 loginVO 객체를 세션에 저장
					session.setAttribute("loginVOForDBAuthentication", egovSSOService.getLoginVO(request, response));
				}
			}
		}

		chain.doFilter(request, response);

		isLocallyAuthenticated = (String) session.getAttribute("isLocallyAuthenticated");
		isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		if (isLocallyAuthenticated != null && isLocallyAuthenticated.equals("true")) {
			if (isRemotelyAuthenticated == null) {

			}
		} else if (isLocallyAuthenticated == null) {
			if (isRemotelyAuthenticated == null) {

			} else if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {

				try {
					// 세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);
					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
						// 세션 로그인
						session.setAttribute("loginVO", loginVO);

						// 로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");
					} else {
						LOG.debug("Local authentication by sso is failed");
					}

				} catch (Exception ex) {
					// DB인증 예외가 발생할 경우 로그를 남기고 로컬인증을 시키지 않고 그대로 진행함.
					LOG.debug("Local authentication by sso is failed : " + ex.getMessage());
				}

			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;

	}

}
