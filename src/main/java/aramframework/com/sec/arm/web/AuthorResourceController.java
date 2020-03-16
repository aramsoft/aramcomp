package aramframework.com.sec.arm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.arm.domain.AuthorResourceVO;
import aramframework.com.sec.arm.service.AuthorResourceService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class AuthorResourceController {

	@Autowired
	private AuthorResourceService authorResourceService;
		
	/**
	 * 권한별 할당된 롤 목록 조회
	 * 
	 * @param authorResourceVO
	 */
	@RequestMapping(value = "/sec/arm/listAuthorResource.do")
	@Secured("ROLE_ADMIN")
	public String listAuthorResource(
			@ModelAttribute AuthorResourceVO authorResourceVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		authorResourceVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", authorResourceService.selectAuthorResourceList(authorResourceVO));
		int totCnt = authorResourceService.selectAuthorResourceListCnt(authorResourceVO);

		authorResourceVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("sec/arm/AuthorResource");
	}

	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * 
	 * @param authorCode
	 * @param resourceCodes
	 * @param regYns
	 */
	@RequestMapping(value = "/sec/arm/insertAuthorResource.do")
	@Secured("ROLE_ADMIN")
	public String insertAuthorResource(
			@ModelAttribute AuthorResourceVO authorResourceVO, 
			@RequestParam String strRegYns, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] resourceCodes = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		resourceCodes = request.getParameterValues("uniqIds");

		String[] regYns = strRegYns.split(";");
		
		authorResourceService.insertAuthorResources(authorResourceVO.getAuthorCode(), resourceCodes, regYns);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, authorResourceVO, "/sec/arm/listAuthorResource.do");
	}
	
}