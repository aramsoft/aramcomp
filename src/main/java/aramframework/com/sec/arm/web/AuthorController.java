package aramframework.com.sec.arm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.constant.Globals;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.sec.arm.domain.AuthorVO;
import aramframework.com.sec.arm.service.AuthorService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;
		
//	@Autowired
//	private ReloadableDefaultFilterInvocationDefinitionSource filterSource;
	
//	@Autowired
//	private ReloadableMapBasedMethodDefinitionSource methodSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 권한 목록을 조회한다
	 * 
	 * @param authorVO
	 */
	@IncludedInfo(name = "권한관리", order = 2010, gid = 20)
	@RequestMapping(value = "/sec/arm/listAuthor.do")
	@Secured("ROLE_ADMIN")
	public String listAuthor(
			@ModelAttribute AuthorVO authorVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		authorVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", authorService.selectAuthorList(authorVO));
		int totCnt = authorService.selectAuthorListCnt(authorVO);

		authorVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

        String authorResourceReload = Globals.AUTHOR_RESOURCE_RELOAD;       
		model.addAttribute("authorResourceReload", authorResourceReload);
		
		return "com/sec/arm/AuthorList";
	}

	/**
	 * 권한 등록화면 이동
	 * 
	 * @param authorVO
	 */
	@RequestMapping("/sec/arm/registAuthor.do")
	@Secured("ROLE_ADMIN")
	public String registAuthorManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			ModelMap model) {
		
		return "com/sec/arm/AuthorRegist";
	}

	/**
	 * 권한 세부정보를 등록한다.
	 * 
	 * @param authorVO
	 */
	@RequestMapping(value = "/sec/arm/insertAuthor.do")
	@Secured("ROLE_ADMIN")
	public String insertAuthorManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(authorVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/arm/AuthorRegist";
		} 
		
		authorService.insertAuthor(authorVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sec/arm/listAuthor.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 권한 수정화면 이동
	 * 
	 * @param authorVO
	 */
	@RequestMapping(value = "/sec/arm/editAuthor.do")
	@Secured("ROLE_ADMIN")
	public String editAuthorManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO,
			ModelMap model) {

		model.addAttribute(authorService.selectAuthor(authorVO));
		
		return "com/sec/arm/AuthorEdit";
	}

	/**
	 * 권한 세부정보를 수정한다.
	 * 
	 * @param authorVO
	 */
	@RequestMapping(value = "/sec/arm/updateAuthor.do")
	@Secured("ROLE_ADMIN")
	public String updateAuthor(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(authorVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/arm/AuthorEdit";
		} 
		
		authorService.updateAuthor(authorVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sec/arm/listAuthor.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 권한 세부정보를 삭제한다.
	 * 
	 * @param authorVO
	 */
	@RequestMapping(value = "/sec/arm/deleteAuthor.do")
	@Secured("ROLE_ADMIN")
	public String deleteAuthorManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			ModelMap model) {

		authorService.deleteAuthor(authorVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/arm/listAuthor.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 권한목록을 삭제한다.
	 * 
	 * @param authorCodes
	 */
	@RequestMapping(value = "/sec/arm/deleteListAuthor.do")
	@Secured("ROLE_ADMIN")
	public String deleteListAuthor(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] authorCodes = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		authorCodes = request.getParameterValues("uniqIds");

		authorService.deleteAuthors(authorCodes);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/arm/listAuthor.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 권한 세부정보 캐쉬맵을 재설정한다.
	 * 
	 * @param authorVO
	 */
	@RequestMapping(value = "/sec/arm/clearCacheAuthor.do")
	@Secured("ROLE_ADMIN")
	public String clearCacheAuthor(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute AuthorVO authorVO, 
			ModelMap model) {

        String authorResourceReload = Globals.AUTHOR_RESOURCE_RELOAD;       
        if( "true".equals(authorResourceReload) ) {
//        	filterSource.reloadRequestMap();
//       	methodSource.reloadMethodMap();
			model.addAttribute("message", "캐쉬가 재설정되었습니다!!!");
        }
        else {
			model.addAttribute("message", "캐쉬가 재설정되지 않았습니다.!!!");
        }

		model.addAttribute("redirectURL", "/sec/arm/listAuthor.do");
	    return "com/cmm/redirect";
 	}

	/**
	 * 권한제한 화면 이동
	 * 
	 */
	@RequestMapping("/sec/arm/accessDenied.do")
	public String accessDenied() {
//		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
//		LOG.error("Access Denied !!! " + loginVO.getUserId() + "@" + loginVO.getIp());
		return "com/sec/accessDenied";
	}
	
}
