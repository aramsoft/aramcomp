package aramframework.com.sec.rmt.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.domain.SearchVO;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.code.service.CmmUseService;
import aramframework.com.cmm.com.annotation.IncludedInfo;
import aramframework.com.sec.rmt.domain.ResourceVO;
import aramframework.com.sec.rmt.service.ResourceService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 자원관리에 관한 controller 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
		
	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 자원정보 목록 조회
	 * 
	 * @param groupVO
	 */
	@IncludedInfo(name = "접근자원관리", order = 2020, gid = 20)
	@RequestMapping(value = "/sec/rmt/listResource.do")
	@Secured("ROLE_ADMIN")
	public String listResource(
			@ModelAttribute ResourceVO resourceVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		resourceVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", resourceService.selectResourceList(resourceVO));
		int totCnt = resourceService.selectResourceListCnt(resourceVO);

		resourceVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sec/rmt/ResourceList";
	}

	/**
	 * 자원정보 등록화면 이동
	 * 
	 * @param resourceVO
	 */
	@RequestMapping("/sec/rmt/registResource.do")
	@Secured("ROLE_ADMIN")
	public String registResource(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			ModelMap model) {

		cmmUseService.populateCmmCodeList("COM029", "COM029_resourceType");

		return "com/sec/rmt/ResourceRegist";
	}

	/**
	 * 자원정보 등록
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/insertResource.do")
	@Secured("ROLE_ADMIN")
	public String insertResource(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(resourceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/rmt/ResourceRegist";
		} 
		
		resourceService.insertResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sec/rmt/listResource.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 자원정보 수정화면 이동
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/editResource.do")
	@Secured("ROLE_ADMIN")
	public String editResource(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO,
			ModelMap model) {

		model.addAttribute(resourceService.selectResource(resourceVO));

		cmmUseService.populateCmmCodeList("COM029", "COM029_resourceType");

		return "com/sec/rmt/ResourceEdit";
	}

	/**
	 * 자원정보 수정
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/updateResource.do")
	@Secured("ROLE_ADMIN")
	public String updateResourceManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(resourceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/rmt/ResourceEdit";
		} 
		
		resourceService.updateResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sec/rmt/listResource.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 자원정보 삭제
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/deleteResource.do")
	@Secured("ROLE_ADMIN")
	public String deleteResource(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			ModelMap model) {

		resourceService.deleteResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/rmt/listResource.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 불필요한 자원정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param resourceCodes
	 */
	@RequestMapping(value = "/sec/rmt/deleteListResource.do")
	@Secured("ROLE_ADMIN")
	public String deleteListResources(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] resourceCodes = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		resourceCodes = request.getParameterValues("uniqIds"); 

		resourceService.deleteResources(resourceCodes);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/rmt/listResource.do");
	    return "com/cmm/redirect";
	}

}