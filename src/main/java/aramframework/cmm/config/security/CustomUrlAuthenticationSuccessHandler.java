package aramframework.cmm.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 로그인 실패시 처리하는 함수(SimpleUrlAuthenticationSuccessHandler 코드 수정)
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CustomUrlAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler 
	implements AuthenticationSuccessHandler {

    public CustomUrlAuthenticationSuccessHandler() {}

    /**
     * Constructor which sets the <tt>defaultTargetUrl</tt> property of the base class.
     * @param defaultTargetUrl the URL to which the user should be redirected on successful authentication.
     */
    public CustomUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    /**
     * Calls the parent class {@code handle()} method to forward or redirect to the target URL, and
     * then calls {@code clearAuthenticationAttributes()} to remove any leftover session data.
     */
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginVO.setIp(request.getRemoteAddr());

		// 세션 로그인
        HttpSession session = request.getSession(false);
		session.setAttribute("loginVO", loginVO);
		
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
    public String getTargetUrlParameter() {
        return super.getTargetUrlParameter();
    }
   
}
