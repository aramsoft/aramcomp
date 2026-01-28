package aramframework.cmm.config.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 일반 세션 정보를 Https 세션 정보로 이관 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class HttpsSessionFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {

		HttpsRequestWrapper httpsRequest = new HttpsRequestWrapper((HttpServletRequest)request);
		httpsRequest.setResponse((HttpServletResponse)response);
		chain.doFilter(httpsRequest, response);
	}

	public class HttpsRequestWrapper extends HttpServletRequestWrapper {
		private HttpServletResponse response = null;
		
	    public HttpsRequestWrapper(HttpServletRequest wrapper) {
	        super(wrapper);
	    }
	     
	    public void setResponse(HttpServletResponse response) {
	    	this.response = response;
	    }
	    
	    public HttpSession getSession() {
	    	HttpSession session = super.getSession();
	    	processSessionCookie(session);
	    	return session;
	    }
	    
	    public HttpSession getSession(boolean create) {
	    	HttpSession session = super.getSession(create);
	    	processSessionCookie(session);
	    	return session;
	    }
	    
	    private void processSessionCookie(HttpSession session) {
	    	if( null == response || null == session ) return;
	    	
	    	Object cookieOverWritten = getAttribute("COOKIE_OVERWRITTEN_FLAG");
	    	if( null == cookieOverWritten 
	    			&& isSecure() 
	    			&& isRequestedSessionIdFromCookie() 
	    			&& session.isNew()) {
	    		Cookie cookie = new Cookie("JSESSIONID", session.getId());
	    		cookie.setMaxAge(-1);
	    		String contextPath = getContextPath();
	    		if ( (contextPath != null )&&(contextPath.length()>0)) {
	    			cookie.setPath(contextPath);
	    		} else {
	    			cookie.setPath("/");
	    		}
	    		response.addCookie(cookie);
	    		setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
	    	}
	    }
	} 
	
}
