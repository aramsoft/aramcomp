package aramframework.com.uss.sam.stp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.domain.SearchVO;
import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.com.annotation.IncludedInfo;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.sam.stp.domain.StplatManageVO;
import aramframework.com.uss.sam.stp.service.StplatManageService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 약관내용을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class StplatManageController {

	@Autowired
	private StplatManageService stplatManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 약관정보 목록을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	@IncludedInfo(name = "약관관리", order = 5040, gid = 50)
	@RequestMapping(value = "/uss/sam/stp/listStplat.do")
	@Secured("ROLE_ADMIN")
	public String listStplat(
			@ModelAttribute StplatManageVO stplatManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		stplatManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", stplatManageService.selectStplatList(stplatManageVO));
		int totCnt = stplatManageService.selectStplatListCnt(stplatManageVO);

		stplatManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/uss/sam/stp/StplatList";
	}

	/**
	 * 약관정보상세내용을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/detailStplat.do")
	@Secured("ROLE_ADMIN")
	public String detailStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO,
			ModelMap model) {

		model.addAttribute(stplatManageService.selectStplatDetail(stplatManageVO));

		return "com/uss/sam/stp/StplatDetail";
	}

	/**
	 * 약관정보를 등록하기 위한 전 처리
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/registStplat.do")
	@Secured("ROLE_ADMIN")
	public String registStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO, 
			ModelMap model) {

		return "com/uss/sam/stp/StplatRegist";
	}

	/**
	 * 약관정보를 등록한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/insertStplat.do")
	@Secured("ROLE_ADMIN")
	public String insertStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(stplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/stp/StplatRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		stplatManageVO.setFrstRegisterId(loginVO.getUserId()); // 최초등록자ID

		stplatManageService.insertStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/sam/stp/listStplat.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 약관정보를 수정하기 위한 전 처리
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/editStplat.do")
	@Secured("ROLE_ADMIN")
	public String editStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO,
			ModelMap model) {

		model.addAttribute(stplatManageService.selectStplatDetail(stplatManageVO));

		return "com/uss/sam/stp/StplatEdit";
	}

	/**
	 * 약관정보를 수정 처리한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/updateStplat.do")
	@Secured("ROLE_ADMIN")
	public String updateStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		// Validation
		beanValidator.validate(stplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/stp/StplatEdit";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		stplatManageVO.setLastUpdusrId(loginVO.getUserId()); // 최종수정자ID

		stplatManageService.updateStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/sam/stp/listStplat.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 약관정보를 삭제 처리한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/deleteStplat.do")
	@Secured("ROLE_ADMIN")
	public String deleteStplat(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute StplatManageVO stplatManageVO, 
			ModelMap model) {

		stplatManageService.deleteStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/sam/stp/listStplat.do");
	    return "com/cmm/redirect";
	}

}
