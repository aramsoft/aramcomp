package aramframework.com.sym.ccm.cde.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.ccm.cca.domain.CmmnCodeVO;
import aramframework.com.sym.ccm.cca.service.CmmnCodeManageService;
import aramframework.com.sym.ccm.ccc.domain.CmmnClCodeVO;
import aramframework.com.sym.ccm.ccc.service.CmmnClCodeManageService;
import aramframework.com.sym.ccm.cde.domain.CmmnDetailCodeVO;
import aramframework.com.sym.ccm.cde.excel.ExcelCmmnDetailView;
import aramframework.com.sym.ccm.cde.service.CmmnDetailCodeManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통상세코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class CmmnDetailCodeManageController {

	@Autowired
	private CmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Autowired
	private CmmnClCodeManageService cmmnClCodeManageService;

	@Autowired
	private CmmnCodeManageService cmmnCodeManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@IncludedInfo(name = "공통상세코드", order = 6020, gid = 60)
	@RequestMapping(value = "/sym/ccm/cde/listCmmnDetailCode.do")
	public String listCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cmmnDetailCodeVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmmnDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO));
		int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListCnt(cmmnDetailCodeVO);

		cmmnDetailCodeVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeList");
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/detailCmmnDetailCode.do")
	public String detailCmmnDetailCode(
			CmmnDetailCodeVO cmmnDetailCodeVO,
			ModelMap model) {
		
		model.addAttribute(cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO));

		return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeDetail");
	}

	/**
	 * 공통상세코드 등록 전처리 화면으로 이동한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/registCmmnDetailCode.do")
	@Secured("ROLE_ADMIN")
	public String registCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO, 
			@RequestParam String clCode, 
			ModelMap model) {

		// class code list
		CmmnClCodeVO cmmnClCodeVO = new CmmnClCodeVO();
		cmmnClCodeVO.getSearchVO().setSizeAndOffset(999999, 0);
		List<EgovMap> cmmnClCodeList = cmmnClCodeManageService.selectCmmnClCodeList(cmmnClCodeVO);

		model.addAttribute("cmmnClCodeList", cmmnClCodeList);

		// common code list
		CmmnCodeVO cmmnCodeVO = new CmmnCodeVO();
		cmmnCodeVO.getSearchVO().setSizeAndOffset(999999, 0);

		cmmnCodeVO.getSearchVO().setSearchCondition("clCode");
		if ( clCode.equals("") ) {
			clCode = cmmnClCodeList.get(0).get("clCode").toString();
		}	
		cmmnCodeVO.getSearchVO().setSearchKeyword(clCode);

		model.addAttribute("cmmnCodeList", cmmnCodeManageService.selectCmmnCodeList(cmmnCodeVO));

		return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeRegist");
	}

	/**
	 * 공통상세코드를 등록한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/insertCmmnDetailCode.do")
	@Secured("ROLE_ADMIN")
	public String insertCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cmmnDetailCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeRegist");
		}

		CmmnDetailCodeVO vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		if (vo != null) {
			model.addAttribute("message", "이미 등록된 코드가 존재합니다.");
			return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeRegist");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnDetailCodeVO.setFrstRegisterId(loginVO.getUniqId());

		cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sym/ccm/cde/listCmmnDetailCode.do");
	}

	/**
	 * 공통상세코드를 수정 전처리 화면으로 이동한다
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/editCmmnDetailCode.do")
	@Secured("ROLE_ADMIN")
	public String editCmmnDetailCode(
			CmmnDetailCodeVO cmmnDetailCodeVO,
			ModelMap model) {
		
		model.addAttribute(cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO));

		return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeEdit");
	}

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/updateCmmnDetailCode.do")
	@Secured("ROLE_ADMIN")
	public String updateCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO, 
			BindingResult bindingResult, 
			ModelMap model) {
		
		beanValidator.validate(cmmnDetailCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/cde/CmmnDetailCodeEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnDetailCodeVO.setLastUpdusrId(loginVO.getUniqId());

		cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCodeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/sym/ccm/cde/listCmmnDetailCode.do");
	}

	/**
	 * 공통상세코드를 삭제한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/deleteCmmnDetailCode.do")
	@Secured("ROLE_ADMIN")
	public String deleteCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO, 
			ModelMap model) {
		
		cmmnDetailCodeManageService.deleteCmmnDetailCode(cmmnDetailCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sym/ccm/cde/listCmmnDetailCode.do");
	}

	/**
	 * 공통상세코드 목록을 엑셀 다운로드한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cde/excelCmmnDetailCode.do")
	public ModelAndView excelCmmnDetailCode(
			@ModelAttribute CmmnDetailCodeVO cmmnDetailCodeVO) {

		ModelAndView modelAndView = new ModelAndView(new ExcelCmmnDetailView());

		modelAndView.addObject("resultList", cmmnDetailCodeManageService.selectCmmnDetailCodeListExcel(cmmnDetailCodeVO));

		return modelAndView;
	}

}