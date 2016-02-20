package aramframework.com.uss.sam.ipm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.sam.ipm.domain.IndvdlInfoPolicyVO;
import aramframework.com.uss.sam.ipm.service.IndvdlInfoPolicyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개인정보보호정책를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
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
		indvdlInfoPolicyVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", indvdlInfoPolicyService.selectIndvdlInfoPolicyList(indvdlInfoPolicyVO));
		int totCnt = (Integer) indvdlInfoPolicyService.selectIndvdlInfoPolicyListCnt(indvdlInfoPolicyVO);

		indvdlInfoPolicyVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyList");
	}

	/**
	 * 개인정보보호정책 목록을 상세조회 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/detailIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String detailIndvdlInfoPolicy(
			IndvdlInfoPolicyVO indvdlInfoPolicyVO,
			ModelMap model) {

		model.addAttribute(indvdlInfoPolicyService.selectIndvdlInfoPolicyDetail(indvdlInfoPolicyVO));

		return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyDetail");
	}

	/**
	 * 개인정보보호정책 목록을 등록하기 위한 전 처리
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/registIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String registIndvdlInfoPolicy(
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO) {

		return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyRegist");
	}

	/**
	 * 개인정보보호정책를 등록한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/insertIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String insertIndvdlInfoPolicy(
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(indvdlInfoPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyRegist");
		}
		
		// 아이디 설정
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlInfoPolicyVO.setFrstRegisterId(loginVO.getUniqId());
		
		// 저장
		indvdlInfoPolicyService.insertIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	}

	/**
	 * 개인정보보호정책 목록을수정하기 위한 전 처리
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/editIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String editIndvdlInfoPolicy(
			IndvdlInfoPolicyVO indvdlInfoPolicyVO,
			ModelMap model) {

		model.addAttribute(indvdlInfoPolicyService.selectIndvdlInfoPolicyDetail(indvdlInfoPolicyVO));

		return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyEdit");
	}

	/**
	 * 개인정보보호정책를 수정한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/updateIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String updateIndvdlInfoPolicy(
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(indvdlInfoPolicyVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/sam/ipm/IndvdlInfoPolicyEdit");
		}
			// 아이디 설정
		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlInfoPolicyVO.setLastUpdusrId(loginVO.getUniqId());

		indvdlInfoPolicyService.updateIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	}

	/**
	 * 개인정보보호정책를 삭제처리한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	@RequestMapping(value = "/uss/sam/ipm/deleteIndvdlInfoPolicy.do")
	@Secured("ROLE_ADMIN")
	public String deleteIndvdlInfoPolicy(
			@ModelAttribute IndvdlInfoPolicyVO indvdlInfoPolicyVO, 
			ModelMap model) {

		indvdlInfoPolicyService.deleteIndvdlInfoPolicy(indvdlInfoPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/sam/ipm/listIndvdlInfoPolicy.do");
	}

}
