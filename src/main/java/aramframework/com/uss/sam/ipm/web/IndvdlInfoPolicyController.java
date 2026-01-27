package aramframework.com.uss.sam.ipm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.sam.ipm.domain.IndvdlInfoPolicyVO;
import aramframework.com.uss.sam.ipm.service.IndvdlInfoPolicyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개인정보보호정책를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class IndvdlInfoPolicyController {

	@Autowired
	private IndvdlInfoPolicyService indvdlInfoPolicyService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 개인정보보호정책 목록을 조회한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@IncludedInfo(name = "개인정보보호정책", order = 5060, gid = 50)
	@RequestMapping(value = "/uss/sam/ipm/listIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String listIndvdlInfoPolicy(
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		indvdlInfoPolicyVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", indvdlInfoPolicyService.selectIndvdlInfoPolicyList(indvdlInfoPolicyVO));
		int totCnt = (Integer) indvdlInfoPolicyService.selectIndvdlInfoPolicyListCnt(indvdlInfoPolicyVO);

		indvdlInfoPolicyVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/uss/sam/ipm/IndvdlInfoPolicyList";
	}

	/**
	 * 개인정보보호정책 목록을 상세조회 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/detailIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String detailIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO,
			ModelMap model) {

		model.addAttribute(indvdlInfoPolicyService.selectIndvdlInfoPolicyDetail(indvdlInfoPolicyVO));

		return "com/uss/sam/ipm/IndvdlInfoPolicyDetail";
	}

	/**
	 * 개인정보보호정책 목록을 등록하기 위한 전 처리
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/registIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String registIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO) {

		return "com/uss/sam/ipm/IndvdlInfoPolicyRegist";
	}

	/**
	 * 개인정보보호정책를 등록한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/insertIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String insertIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(indvdlInfoPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/ipm/IndvdlInfoPolicyRegist";
		}
		
		// 아이디 설정
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlInfoPolicyVO.setFrstRegisterId(loginVO.getUserId());
		
		// 저장
		indvdlInfoPolicyService.insertIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 개인정보보호정책 목록을수정하기 위한 전 처리
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/editIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String editIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO,
			ModelMap model) {

		model.addAttribute(indvdlInfoPolicyService.selectIndvdlInfoPolicyDetail(indvdlInfoPolicyVO));

		return "com/uss/sam/ipm/IndvdlInfoPolicyEdit";
	}

	/**
	 * 개인정보보호정책를 수정한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/updateIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String updateIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(indvdlInfoPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/sam/ipm/IndvdlInfoPolicyEdit";
		}
			// 아이디 설정
		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlInfoPolicyVO.setLastUpdusrId(loginVO.getUserId());

		indvdlInfoPolicyService.updateIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 개인정보보호정책를 삭제처리한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/deleteIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String deleteIndvdlInfoPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			ModelMap model) {

		indvdlInfoPolicyService.deleteIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	    return "com/cmm/redirect";
	}

}
