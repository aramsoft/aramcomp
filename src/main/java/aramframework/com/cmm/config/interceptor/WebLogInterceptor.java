package aramframework.com.cmm.config.interceptor;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.sym.log.wlg.domain.WebLogVO;
import aramframework.com.sym.log.wlg.service.WebLogService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 웹 로그 생성을 위한 인터셉터 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class WebLogInterceptor extends HandlerInterceptorAdapter {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebLogService webLogService;

	private Set<String> passURL;

	public void setPassURL(Set<String> passURL) {
		this.passURL = passURL;
	}

	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param request		HttpServletRequest
	 * @param response		HttpServletResponse
	 * @param handler		Object
	 * @param modelAndView	ModelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			ModelAndView modelAndView) 
	throws Exception {

		String requestIP = request.getRemoteAddr();
		
		if( "127.0.0.1".equals(requestIP)) return;
		
		String requestURI = URLDecoder.decode(request.getRequestURI(), "UTF-8"); // 요청 URI
		boolean isPassURL = false;

		for (Iterator<String> it = this.passURL.iterator(); it.hasNext();) {
			String urlPattern = request.getContextPath() + (String) it.next();

			if (Pattern.matches(urlPattern, requestURI)) {// 정규표현식을 이용해서 요청
															// URI가 허용된 URL에  맞는지 점검함.
				isPassURL = true;
			}
		}

		if( isPassURL ) return;
		
		/* Authenticated */
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String uniqId = "unknown";
		if( loginVO != null ) {
			uniqId = loginVO.getUniqId();
		}

		WebLogVO webLogVO = new WebLogVO();
		webLogVO.setUrl(requestURI);
		webLogVO.setRqesterId(uniqId);
		webLogVO.setRqesterIp(requestIP);

		webLogService.logInsertWebLog(webLogVO);
	}
	
}
