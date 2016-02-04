package aramframework.com.cmm.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.util.MessageHelper;
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

public class CustomLoginFilter extends OncePerRequestFilter {

	@Autowired 
	private LoginService loginService;

	private String loginURL;

	protected final static Logger LOG = LoggerFactory.getLogger(CustomLoginFilter.class);

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {

		String requestURL = request.getRequestURI();
		if (requestURL.contains("/j_spring_security_check")) {

			LOG.debug("Login Filter start!!!");

			HttpSession session = request.getSession();
			String password = request.getParameter("password");
			// 2011.10.11 보안점검 후속 조치(Password 검증)
			if (password == null || password.equals("") || password.length() < 8 || password.length() > 20) {
				request.setAttribute("message", MessageHelper.getMessage("fail.common.login.password"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
				dispatcher.forward(request, response);
				return;
			}

			LoginVO loginVO = new LoginVO();

			loginVO.setId(request.getParameter("id"));
			loginVO.setPassword(password);
			loginVO.setUserSe(request.getParameter("userSe"));

			try {
				// 사용자 입력 id, password로 DB 인증을 실행함
				loginVO = loginService.actionLogin(loginVO);

				if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
					// login이 성공해서 사용자 정보가 있는 경우
					// 세션 로그인
					session.setAttribute("loginVO", loginVO);

			        SpringRequestWrapper springRequest = new SpringRequestWrapper((HttpServletRequest)request);
			        springRequest.setUsername(loginVO.getUserSe() + loginVO.getId());
			        springRequest.setPassword(loginVO.getUniqId());
			        chain.doFilter((HttpServletRequestWrapper)springRequest, response);
					return;
				} else {
					// 사용자 정보가 없는 경우 로그인 화면으로 redirect 시킴
					request.setAttribute("message", MessageHelper.getMessage("fail.common.login"));
					RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
					dispatcher.forward(request, response);
					return;
				}
			} catch (Exception ex) {
				// DB인증 예외가 발생할 경우 로그인 화면으로 redirect 시킴
				LOG.error("Login Exception : " + ex.getCause());
				request.setAttribute("message", MessageHelper.getMessage("fail.common.login"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(getLoginURL());
				dispatcher.forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public class SpringRequestWrapper extends HttpServletRequestWrapper {
		private String username;
		private String password;
		
	    public SpringRequestWrapper(HttpServletRequest wrapper) {
	        super(wrapper);
	    }
	     
	    public void setUsername(String username) {
	    	this.username = username;
	    }
	    public void setPassword(String password) {
	    	this.password = password;
	    }
	    
	    public String getParameter(String name) {
	        if ( name.equals("j_username") ) {
	        	return this.username;
	        }	
	        if( name.equals("j_password") ) {
		        return this.password;
	        } 
	        return super.getParameter(name);
	    }
	    
	}     
}