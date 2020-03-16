package aramframework.com.cmm.config.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.impl.BasicTilesContainer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.cop.cmy.service.CommunityManageService;

/**
 * 타일스 생성을 위한 인터셉터 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class TilesInterceptor extends HandlerInterceptorAdapter {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private CommunityManageService cmmntyService;

	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param handler	Object
	 * @return			true/false
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

		String cmmntyId = (String) request.getAttribute("curTrgetId");
		String menuId = (String) request.getAttribute("curMenuNo");
		
		if (cmmntyId == null || !cmmntyId.startsWith("CMMNTY_") || "".equals(menuId)) {
			return;
		}
		
        CommunityVO communityVO = cmmntyService.getCommunityInfo(cmmntyId, menuId);
        modelAndView.addObject("targetVO", communityVO);
	
		// --------------------------------
		// 커뮤니티 사용자 정보
		// --------------------------------
        modelAndView.addObject("targetUserVO", cmmntyService.getCommunityUserInfo(cmmntyId));
		
        // --------------------------------
		// 메뉴 정보
		// --------------------------------
		String menuAlias = cmmntyService.getMenuInfo(communityVO, menuId, "menuAlias");
		if( "".equals(menuAlias) ) {
			menuAlias = communityVO.getTopMenuList().get(0).get("menuAlias").toString();
		}
		modelAndView.addObject("menuAlias", menuAlias);
	    
		// --------------------------------
		// 커뮤니티 템플릿 정보
		// --------------------------------
		String tmplatCours = cmmntyService.selectCmmntyTemplat(communityVO);
    	if ("".equals(tmplatCours) || tmplatCours == null) {
    		tmplatCours = "/WEB-INF/layouts/apps/appsDefault";
    	}

		ServletContext servletContext = request.getSession().getServletContext();
		ApplicationContext tilesAppContext = ServletUtil.getApplicationContext(servletContext);
		Request tilesRequest = new ServletRequest(tilesAppContext, request, response);
		BasicTilesContainer container = (BasicTilesContainer) TilesAccess.getContainer(tilesAppContext);
		AttributeContext attributeContext = container.getAttributeContext(tilesRequest);

		if (tmplatCours.indexOf("/WEB-INF/layouts") != -1) {
			attributeContext.setTemplateAttribute(new Attribute(tmplatCours+".jsp"));
		} else {
			attributeContext.setTemplateAttribute(new Attribute("/WEB-INF/jsp/"+tmplatCours+".jsp"));
		}
		
	}
	
}
