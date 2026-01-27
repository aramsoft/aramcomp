package aramframework.com.sec.dpt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.sec.dpt.domain.DeptVO;
import aramframework.com.sec.dpt.service.DeptService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 부서관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * 
	 * @param deptVO
	 */
	@IncludedInfo(name = "부서관리", order = 2040, gid = 20)
	@RequestMapping(value = "/sec/dpt/listDept.do")
	@Secured("ROLE_ADMIN")
	public String listDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		deptVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptService.selectDeptList(deptVO));
		int totCnt = deptService.selectDeptListCnt(deptVO);

		deptVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sec/dpt/DeptList";
	}

	/**
	 * 부서등록 화면으로 이동한다.
	 * 
	 * @param deptVO
	 */
	@RequestMapping(value = "/sec/dpt/registDept.do")
	@Secured("ROLE_ADMIN")
	public String registDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO, 
			ModelMap model) {

		return "com/sec/dpt/DeptRegist";
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * 
	 * @param deptVO
	 */
	@RequestMapping(value = "/sec/dpt/insertDept.do")
	@Secured("ROLE_ADMIN")
	public String insertDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(deptVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/dpt/DeptRegist";
		} 
		
		deptService.insertDept(deptVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sec/dpt/listDept.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 등록된 부서의 수정화면으로 이동한다.
	 * 
	 * @param deptVO
	 */
	@RequestMapping(value = "/sec/dpt/editDept.do")
	@Secured("ROLE_ADMIN")
	public String editDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO,
			ModelMap model) {

		model.addAttribute(deptService.selectDept(deptVO));
		
		return "com/sec/dpt/DeptEdit";
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * 
	 * @param deptVO
	 */
	@RequestMapping(value = "/sec/dpt/updateDept.do")
	@Secured("ROLE_ADMIN")
	public String updateDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(deptVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/sec/dpt/DeptEdit";
		} 
		
		deptService.updateDept(deptVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sec/dpt/listDept.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * 
	 * @param deptVO
	 */
	@RequestMapping(value = "/sec/dpt/deleteDept.do")
	@Secured("ROLE_ADMIN")
	public String deleteDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DeptVO deptVO, 
			ModelMap model) {

		deptService.deleteDept(deptVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/dpt/listDept.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 기 등록된 부서정보목록을 일괄 삭제한다.
	 * 
	 * @param orgnztIds
	 */
	@RequestMapping(value = "/sec/dpt/deleteListDept.do")
	@Secured("ROLE_ADMIN")
	public String deleteListDept(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@RequestParam String orgnztIds, 
			ModelMap model) {

		deptService.deleteDepts(orgnztIds);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sec/dpt/listDept.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 부서조회 팝업 
	 * 
	 * @param deptAuthorVO
	 */
	@RequestMapping("/sec/dpt/listDeptPopup.do")
	public String listDeptSearch(
			@ModelAttribute DeptVO deptVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		deptVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptService.selectDeptList(deptVO));

		int totCnt = deptService.selectDeptListCnt(deptVO);
		deptVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "com/sec/dpt/DeptSearchPopup";
	}
	
}
