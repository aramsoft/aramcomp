package aramframework.com.uss.sam.cpy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.sam.cpy.domain.CpyrhtPrtcPolicyVO;
import aramframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 저작권보호정책내용을 처리하는 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CpyrhtPrtcPolicyController {

	@Autowired
	private CpyrhtPrtcPolicyService cpyrhtPrtcPolicyService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 저작권보호정책 목록을 조회한다. (pageing)
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@IncludedInfo(name = "저작권보호정책", order = 5050, gid = 50)
	@RequestMapping(value = "/uss/sam/cpy/listCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String listCpyrhtPrtcPolicy(
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cpyrhtPrtcPolicyVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyList(cpyrhtPrtcPolicyVO));
		int totCnt = cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyListCnt(cpyrhtPrtcPolicyVO);

		cpyrhtPrtcPolicyVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/uss/sam/cpy/CpyrhtPrtcPolicyList";
	}

	/**
	 * 저작권보호정책 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/detailCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String detailCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
			ModelMap model) {

		model.addAttribute(cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO));

		return "com/uss/sam/cpy/CpyrhtPrtcPolicyDetail";
	}

	/**
	 * 저작권보호정책를 등록하기 위한 전 처리
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/registCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String registCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO, 
			ModelMap model) {

		return "com/uss/sam/cpy/CpyrhtPrtcPolicyRegist";
	}

	/**
	 * 저작권보호정책를 등록한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/insertCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String insertCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cpyrhtPrtcPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/cpy/CpyrhtPrtcPolicyRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cpyrhtPrtcPolicyVO.setFrstRegisterId(loginVO.getUserId()); // 최초등록자ID

		cpyrhtPrtcPolicyService.insertCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/sam/cpy/listCpyrhtPrtcPolicy.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 저작권보호정책를 수정하기 위한 전 처리
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/editCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String editCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
			ModelMap model) {

		model.addAttribute(cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO));

		return "com/uss/sam/cpy/CpyrhtPrtcPolicyEdit";
	}

	/**
	 * 저작권보호정책를 수정처리한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/updateCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String updateCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// Validation
		beanValidator.validate(cpyrhtPrtcPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/cpy/CpyrhtPrtcPolicyEdit";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cpyrhtPrtcPolicyVO.setLastUpdusrId(loginVO.getUserId()); // 최종수정자ID

		cpyrhtPrtcPolicyService.updateCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/sam/cpy/listCpyrhtPrtcPolicy.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 저작권보호정책를 삭제처리한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	@RequestMapping("/uss/sam/cpy/deleteCpyrhtPrtcPolicy.do")
	@Secured("ROLE_ADMIN")
	public String deleteCpyrhtPrtcPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO, 
			ModelMap model) {

		cpyrhtPrtcPolicyService.deleteCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/sam/cpy/listCpyrhtPrtcPolicy.do");
	    return "com/cmm/redirect";
	}

}
