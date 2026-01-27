package aramframework.cmm.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import aramframework.cmm.util.MessageHelper;
import aramframework.com.uat.uap.domain.LoginPolicyVO;
import aramframework.com.uat.uap.service.LoginPolicyService;
import aramframework.com.utl.sim.service.ClntInfo;

/**
 * 로그인 정책 체크 필터
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LoginPolicyFilter extends OncePerRequestFilter {

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
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
	throws IOException, ServletException {

		// get access Information
		String requestURL = request.getRequestURI();
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

}
