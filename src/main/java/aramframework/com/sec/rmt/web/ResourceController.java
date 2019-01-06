package aramframework.com.sec.rmt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.rmt.domain.ResourceVO;
import aramframework.com.sec.rmt.service.ResourceService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 자원관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
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

		return WebUtil.adjustViewName("/sec/rmt/ResourceList");
	}

	/**
	 * 자원정보 등록화면 이동
	 * 
	 * @param resourceVO
	 */
	@RequestMapping("/sec/rmt/registResource.do")
	@Secured("ROLE_ADMIN")
	public String registResource(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO) {

		cmmUseService.populateCmmCodeList("COM029", "COM029_resourceType");

		return WebUtil.adjustViewName("/sec/rmt/ResourceRegist");
	}

	/**
	 * 자원정보 등록
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/insertResource.do")
	@Secured("ROLE_ADMIN")
	public String insertResource(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(resourceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sec/rmt/ResourceRegist");
		} 
		
		resourceService.insertResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sec/rmt/listResource.do");
	}

	/**
	 * 자원정보 수정화면 이동
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/editResource.do")
	@Secured("ROLE_ADMIN")
	public String editResource(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO,
			ModelMap model) {

		model.addAttribute(resourceService.selectResource(resourceVO));

		cmmUseService.populateCmmCodeList("COM029", "COM029_resourceType");

		return WebUtil.adjustViewName("/sec/rmt/ResourceEdit");
	}

	/**
	 * 자원정보 수정
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/updateResource.do")
	@Secured("ROLE_ADMIN")
	public String updateResourceManage(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(resourceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sec/rmt/ResourceEdit");
		} 
		
		resourceService.updateResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sec/rmt/listResource.do");
	}

	/**
	 * 자원정보 삭제
	 * 
	 * @param resourceVO
	 */
	@RequestMapping(value = "/sec/rmt/deleteResource.do")
	@Secured("ROLE_ADMIN")
	public String deleteResource(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute ResourceVO resourceVO, 
			ModelMap model) {

		resourceService.deleteResource(resourceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sec/rmt/listResource.do");
	}

	/**
	 * 불필요한 자원정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param resourceCodes
	 */
	@RequestMapping(value = "/sec/rmt/deleteListResource.do")
	@Secured("ROLE_ADMIN")
	public String deleteListResources(
			@RequestParam String resourceCodes, 
			@ModelAttribute SearchVO searchVO,
			ModelMap model) {

		resourceService.deleteResources(resourceCodes);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sec/rmt/listResource.do");
	}

}