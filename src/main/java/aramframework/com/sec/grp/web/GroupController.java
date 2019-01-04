package aramframework.com.sec.grp.web;

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
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.grp.domain.GroupVO;
import aramframework.com.sec.grp.service.GroupService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 그룹관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;
		
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * 
	 * @param groupVO
	 */
	@IncludedInfo(name = "그룹관리", order = 2030, gid = 20)
	@RequestMapping(value = "/sec/grp/listGroup.do")
	@Secured("ROLE_ADMIN")
	public String listGroup(
			@ModelAttribute GroupVO groupVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		groupVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", groupService.selectGroupList(groupVO));
		int totCnt = groupService.selectGroupListCnt(groupVO);

		groupVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sec/grp/GroupList");
	}

	/**
	 * 그룹 등록화면 이동
	 * 
	 * @param groupVO
	 */
	@RequestMapping(value = "/sec/grp/registGroup.do")
	@Secured("ROLE_ADMIN")
	public String registGroup(
			@ModelAttribute GroupVO groupVO) {

		return WebUtil.adjustViewName("/sec/grp/GroupRegist");
	}

	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * 
	 * @param groupVO
	 */
	@RequestMapping(value = "/sec/grp/insertGroup.do")
	@Secured("ROLE_ADMIN")
	public String insertGroup(
			@ModelAttribute GroupVO groupVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(groupVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sec/grp/GroupRegist");
		} 

		groupService.insertGroup(groupVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroup.do");
	}

	/**
	 * 그룹 수정화면 이동
	 * 
	 * @param groupVO
	 */
	@RequestMapping(value = "/sec/grp/editGroup.do")
	@Secured("ROLE_ADMIN")
	public String editGroup(
			GroupVO groupVO,
			ModelMap model) {

		model.addAttribute(groupService.selectGroup(groupVO));
		
		return WebUtil.adjustViewName("/sec/grp/GroupEdit");
	}

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param groupVO
	 */
	@RequestMapping(value = "/sec/grp/updateGroup.do")
	@Secured("ROLE_ADMIN")
	public String updateGroup(
			@ModelAttribute GroupVO groupVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(groupVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sec/grp/GroupEdit");
		} 

		groupService.updateGroup(groupVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroup.do");
	}

	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param groupVO
	 */
	@RequestMapping(value = "/sec/grp/deleteGroup.do")
	@Secured("ROLE_ADMIN")
	public String deleteGroup(
			@ModelAttribute GroupVO groupVO, 
			ModelMap model) {

		groupService.deleteGroup(groupVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroup.do");
	}

	/**
	 * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param groupIds
	 */
	@RequestMapping(value = "/sec/grp/deleteListGroup.do")
	@Secured("ROLE_ADMIN")
	public String deleteListGroup(
			@RequestParam String groupIds, 
			ModelMap model) {

		groupService.deleteGroups(groupIds);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroup.do");
	}

	/**
	 * 그룹팝업 화면 
	 * 
	 * @param groupVO
	 */
	@RequestMapping("/sec/grp/listGroupPopup.do")
	public String listGroupSearch(
			@ModelAttribute GroupVO groupVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		groupVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", groupService.selectGroupList(groupVO));
		int totCnt = groupService.selectGroupListCnt(groupVO);

		groupVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sec/grp/GroupSearchPopup");
	}
	
}