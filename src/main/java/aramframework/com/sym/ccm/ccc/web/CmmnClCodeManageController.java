package aramframework.com.sym.ccm.ccc.web;

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
import aramframework.com.sym.ccm.ccc.domain.CmmnClCodeVO;
import aramframework.com.sym.ccm.ccc.service.CmmnClCodeManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통분류코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CmmnClCodeManageController {
	
	@Autowired
	private CmmnClCodeManageService cmmnClCodeManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@IncludedInfo(name = "공통분류코드", order = 6000, gid = 60)
	@RequestMapping(value = "/sym/ccm/ccc/listCmmnClCode.do")
	public String listCmmnClCode(
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO,
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		cmmnClCodeVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmmnClCodeManageService.selectCmmnClCodeList(cmmnClCodeVO));
		int totCnt = cmmnClCodeManageService.selectCmmnClCodeListCnt(cmmnClCodeVO);

		cmmnClCodeVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/ccm/ccc/CmmnClCodeList";
	}

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/detailCmmnClCode.do")
	public String detailCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO, 
			ModelMap model) {
		
		model.addAttribute(cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCodeVO));

		return "com/sym/ccm/ccc/CmmnClCodeDetail";
	}

	/**
	 * 공통분류코드 등록 전처리 화면으로 이동한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/registCmmnClCode.do")
	@Secured("ROLE_ADMIN")
	public String registCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO, 
			ModelMap model) {

		return "com/sym/ccm/ccc/CmmnClCodeRegist";
	}

	/**
	 * 공통분류코드를 등록한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/insertCmmnClCode.do")
	@Secured("ROLE_ADMIN")
	public String insertCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cmmnClCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/ccm/ccc/CmmnClCodeRegist";
		}

		CmmnClCodeVO vo = cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCodeVO);
		if (vo != null) {
			model.addAttribute("message", "이미 등록된 분류코드가 존재합니다.");
			return "com/sym/ccm/ccc/CmmnClCodeRegist";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnClCodeVO.setFrstRegisterId(loginVO.getUserId());

		cmmnClCodeManageService.insertCmmnClCode(cmmnClCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sym/ccm/ccc/listCmmnClCode.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 공통분류코드를 수정 전처리 화면으로 이동한다
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/editCmmnClCode.do")
	@Secured("ROLE_ADMIN")
	public String editCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO, 
			ModelMap model) {
		
		model.addAttribute(cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCodeVO));

		return "com/sym/ccm/ccc/CmmnClCodeEdit";
	}

	/**
	 * 공통분류코드를 수정한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/updateCmmnClCode.do")
	@Secured("ROLE_ADMIN")
	public String updateCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cmmnClCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/ccm/ccc/CmmnClCodeEdit";
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnClCodeVO.setLastUpdusrId(loginVO.getUserId());

		cmmnClCodeManageService.updateCmmnClCode(cmmnClCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sym/ccm/ccc/listCmmnClCode.do");
	    return "com/cmm/redirect";
 	}

	/**
	 * 공통분류코드를 삭제한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/ccc/deleteCmmnClCode.do")
	@Secured("ROLE_ADMIN")
	public String deleteCmmnClCode(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CmmnClCodeVO cmmnClCodeVO,
			ModelMap model) {
		
		cmmnClCodeManageService.deleteCmmnClCode(cmmnClCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/ccm/ccc/listCmmnClCode.do");
	    return "com/cmm/redirect";
	}

}