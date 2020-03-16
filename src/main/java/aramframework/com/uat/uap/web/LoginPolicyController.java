package aramframework.com.uat.uap.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uap.domain.LoginPolicyVO;
import aramframework.com.uat.uap.service.LoginPolicyService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 로그인정책에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인정책에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인정책의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class LoginPolicyController {

	@Autowired
	private LoginPolicyService loginPolicyService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 로그인정책 목록을 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	@IncludedInfo(name = "로그인정책관리", order = 1010, gid = 10)
	@RequestMapping("/uat/uap/listLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String listLoginPolicy(
			@ModelAttribute LoginPolicyVO loginPolicyVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		loginPolicyVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", loginPolicyService.selectLoginPolicyList(loginPolicyVO));
		int totCnt = loginPolicyService.selectLoginPolicyListCnt(loginPolicyVO);

		loginPolicyVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("uat/uap/LoginPolicyList");
	}

	/**
	 * 로그인정책 정보 등록화면으로 이동한다.
	 * 
	 * @param loginPolicyVO
	 */
	@RequestMapping("/uat/uap/registLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String registLoginPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute LoginPolicyVO loginPolicyVO, 
			ModelMap model)	{

		return WebUtil.adjustViewName("uat/uap/LoginPolicyRegist");
	}

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * 
	 * @param loginPolicyVO
	 */
	@RequestMapping("/uat/uap/insertLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String insertLoginPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute LoginPolicyVO loginPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(loginPolicyVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("uat/uap/LoginPolicyRegist");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginPolicyVO.setUserId(loginVO.getId());

		loginPolicyService.insertLoginPolicy(loginPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, loginPolicyVO, "/uat/uap/listLoginPolicy.do");
	}

	/**
	 * 로그인정책 정보수정화면으로 이동한다.
	 * 
	 * @param loginPolicyVO
	 */
	@RequestMapping("/uat/uap/editLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String editLoginPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute LoginPolicyVO loginPolicyVO,
			ModelMap model) {

		model.addAttribute(loginPolicyService.selectLoginPolicy(loginPolicyVO));

		return WebUtil.adjustViewName("uat/uap/LoginPolicyEdit");
	}

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * 
	 * @param loginPolicyVO
	 */
	@RequestMapping("/uat/uap/updateLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String updateLoginPolicy(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute LoginPolicyVO loginPolicyVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(loginPolicyVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("uat/uap/LoginPolicyEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginPolicyVO.setUserId(loginVO.getId());

		if( loginPolicyVO.getRegDate() == null || loginPolicyVO.getRegDate().equals("") ) {
			loginPolicyService.insertLoginPolicy(loginPolicyVO);
		} else {
			loginPolicyService.updateLoginPolicy(loginPolicyVO);
		}
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, loginPolicyVO, "/uat/uap/listLoginPolicy.do");
	}

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * 
	 * @param loginPolicyVO
	 */
	@RequestMapping("/uat/uap/deleteLoginPolicy.do")
	@Secured("ROLE_ADMIN")
	public String deleteLoginPolicy(
			@ModelAttribute LoginPolicyVO loginPolicyVO, 
			ModelMap model) {

		loginPolicyService.deleteLoginPolicy(loginPolicyVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, loginPolicyVO, "/uat/uap/listLoginPolicy.do");
	}

}
