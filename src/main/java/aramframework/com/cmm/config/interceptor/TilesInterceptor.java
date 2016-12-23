package aramframework.com.cmm.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 타일스 생성을 위한 인터셉터 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class TilesInterceptor extends HandlerInterceptorAdapter {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest
	 *            request, HttpServletResponse response, Object handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) 
	throws Exception {

		String curTrgetId = request.getParameter("curTrgetId");
		if( curTrgetId != null && !"".equals(curTrgetId) ) {
			request.setAttribute("curTrgetId", curTrgetId);
		}
		
		String curMenuNo = request.getParameter("curMenuNo");
		if( curMenuNo != null && !"".equals(curMenuNo)) {
			request.setAttribute("curMenuNo", curMenuNo);
		}
		
		String requestUrl = request.getRequestURL().toString();
		String requestUri = request.getRequestURI();
		String contextUrl = requestUrl.substring(0, requestUrl.indexOf(requestUri));
		request.setAttribute("contextUrl", contextUrl);
		return true;
	}
	
}
