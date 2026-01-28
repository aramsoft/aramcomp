package aramframework.cmm.config.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import aramframework.cmm.util.MessageHelper;
import aramframework.com.uat.uap.domain.LoginPolicyVO;
import aramframework.com.uat.uap.service.LoginPolicyService;
import aramframework.com.utl.sim.service.ClntInfo;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 로그인 정책 체크 필터
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LoginPolicyFilter implements Filter  {

	@Autowired 
	private LoginPolicyService loginPolicyService;
	
	private String loginURL;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	/**
	 * IP를 이용해 로그인을 제한하는 메서든
	 * 
	 * @param 	request		HttpServletRequest
	 * @param 	response	HttpServletResponse
	 * @param 	chain		FilterChain
	 * @exception 			IOException, ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {

		// get access Information
		String requestURL = ((HttpServletRequest) request).getRequestURI();
		if (requestURL.contains("/j_spring_security_check")) {

			String id = request.getParameter("id");
			String userSe = request.getParameter("userSe");
			String userIp = "";
	
			if (id == null || userSe == null) {
				request.setAttribute("message", MessageHelper.getMessage("fail.common.login.policy"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
				dispatcher.forward(request, response);
				return;
			}
	
			// 1. LoginVO를 DB로 부터 가져오는 과정
			try {
				// 접속IP
				userIp = ClntInfo.getClntIP((HttpServletRequest) request);
	
				boolean loginPolicyYn = true;
	
				LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
				loginPolicyVO.setEmplyrId(id);
				loginPolicyVO = loginPolicyService.selectLoginPolicy(loginPolicyVO);
	
				if (loginPolicyVO == null) {
					loginPolicyYn = true;
				} else {
					if (loginPolicyVO.getLmttAt().equals("Y")) {
						if (!userIp.equals(loginPolicyVO.getIpInfo())) {
							loginPolicyYn = false;
						}
					}
				}
	
				if (loginPolicyYn) {
					chain.doFilter(request, response);
	
				} else {
					request.setAttribute("message", MessageHelper.getMessage("fail.common.login.policy"));
					RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
					dispatcher.forward(request, response);
					return;
				}
	
			} catch (Exception e) {
				request.setAttribute("message", MessageHelper.getMessage("fail.common.login.policy"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
				dispatcher.forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
