package aramframework.com.cmm.com.web;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 공통유틸리티성 작업을 위한 Controller
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ComUtlController {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@RequestMapping("/check-url.do")
	@ResponseBody
	public String checkUrl() {
	    StringBuilder sb = new StringBuilder();
	    handlerMapping.getHandlerMethods().keySet().forEach(info -> {
	        // 경로 조건이 PathPatterns인지 Patterns인지 구분하여 가져옴
	        // Set<String> patterns = info.getDirectPaths(); 
	        // 만약 위 메서드가 없다면 아래 방식 사용
	        Set<String> patterns = info.getPatternValues(); 
	        Set<String> sortedSet = new TreeSet<>(patterns); 
	        sb.append(sortedSet.toString()).append("<br/>");
	    });
	    return sb.toString();
	}	
   /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/PageLink.do")
	public String moveToPage(
			HttpServletRequest request,
			@RequestParam("link") String link, 
			@RequestParam(value="menuNo", required=false) String menuNo)
	{

		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (link==null || link.equals("")){
			link="com/cmm/error/egovError";
		}else{
			if(link.indexOf(",")>-1){
			    link=link.substring(0,link.indexOf(","));	// import access시 필요
			}
		}
		
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		return link;
	}

	/**
	 * validato rule dynamic Javascript
	 */
	@RequestMapping("/validator.do")
	public String validate() {
		return "com/cmm/validator";
	}

}