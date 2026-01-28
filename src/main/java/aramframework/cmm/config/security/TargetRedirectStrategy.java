package aramframework.cmm.config.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Simple implementation of <tt>RedirectStrategy</tt> which is the default used throughout the framework.
 *
 * @author Luke Taylor
 * @since 3.0
 */
public class TargetRedirectStrategy implements RedirectStrategy {

	@Autowired
	SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;
	
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private boolean contextRelative;

    /**
     * Redirects the response to the supplied URL.
     * <p>
     * If <tt>contextRelative</tt> is set, the redirect value will be the value after the request context path. Note
     * that this will result in the loss of protocol information (HTTP or HTTPS), so will cause problems if a
     * redirect is being performed to change to HTTPS, for example.
     */
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
        redirectUrl = response.encodeRedirectURL(redirectUrl);

		String requestUrl = null;
		String targetUrlParameter = authenticationSuccessHandler.getTargetUrlParameter();
        if (targetUrlParameter != null  ) {
        	requestUrl = request.getParameter(targetUrlParameter);

            if (StringUtils.hasText(requestUrl)) {
            	if(redirectUrl.indexOf("?") == -1) {	
            		redirectUrl = redirectUrl + "?" + targetUrlParameter + "=" + requestUrl;
            	} else {
            		redirectUrl = redirectUrl + "&" + targetUrlParameter + "=" + requestUrl;
            	}
            }
        }
        
       if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting to '" + redirectUrl + "'");
        }

        response.sendRedirect(redirectUrl);
    }

    private String calculateRedirectUrl(String contextPath, String url) {
        if (!UrlUtils.isAbsoluteUrl(url)) {
            if (contextRelative) {
                return url;
            } else {
                return contextPath + url;
            }
        }

        // Full URL, including http(s)://

        if (!contextRelative) {
            return url;
        }

        // Calculate the relative URL from the fully qualified URL, minus the last
        // occurrence of the scheme and base context.
        url = url.substring(url.lastIndexOf("://") + 3); // strip off scheme
        url = url.substring(url.indexOf(contextPath) + contextPath.length());

        if (url.length() > 1 && url.charAt(0) == '/') {
            url = url.substring(1);
        }

        return url;
    }

    /**
     * If <tt>true</tt>, causes any redirection URLs to be calculated minus the protocol
     * and context path (defaults to <tt>false</tt>).
     */
    public void setContextRelative(boolean useRelativeContext) {
        this.contextRelative = useRelativeContext;
    }

}
