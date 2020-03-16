package aramframework.com.cmm.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 공통유틸리티성 작업을 위한 Controller
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ComUtlController {

    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
/*	
	@RequestMapping(value="/page/**", method=RequestMethod.GET)
	public String moveToPageDirect(
			HttpServletRequest request, 
			HttpSession session, 
			@RequestParam(value="menuNo", required=false) String menuNo)
	{
		String requestUri = request.getRequestURI();
		String link = requestUri.substring("/page/".length());
//		LOG.debug("requestUri = "+ requestUri + ", link = " + link + ", menuNo = " + menuNo);

		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (link==null || link.equals("")){
			link="cmm/error/egovError";
		}
		
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			session.setAttribute("menuNo",menuNo);
		}
		return link;
	}
*/
    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/PageLink.do")
	public String moveToPage(
			HttpSession session, 
			@RequestParam("link") String linkPage, 
			@RequestParam(value="menuNo", required=false) String menuNo)
	{
		String link = linkPage;
//		LOG.debug("link = " + link + ", menuNo = " + menuNo);

		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkPage==null || linkPage.equals("")){
			link="cmm/error/egovError";
		}else{
			if(link.indexOf(",")>-1){
			    link=link.substring(0,link.indexOf(","));	// import access시 필요
			}
		}
		
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			session.setAttribute("menuNo",menuNo);
		}
		return link;
	}

    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/PageLink.action")
	public String moveToPage_action(@RequestParam("link") String linkPage){

		String link = linkPage;
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkPage==null || linkPage.equals("")){
			link="cmm/error/egovError";
		}
		return link;
	}

	/**
	 * validato rule dynamic Javascript
	 */
	@RequestMapping("/validator.do")
	public String validate() {
		return "cmm/validator";
	}

}