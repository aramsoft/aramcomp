package aramframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import aramframework.com.uat.sso.service.SSOService;

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

public class SSOLogoutFilter implements Filter {
	private FilterConfig config;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		SSOService egovSSOService = (SSOService) act.getBean("egovSSOService");

		String returnURL = ((HttpServletRequest)request).getRequestURI();
		
		((HttpServletRequest) request).getSession().setAttribute("loginVO", null);
		egovSSOService.ssoLogout(request, response, ((HttpServletRequest) request).getContextPath() + returnURL);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;

	}
}
