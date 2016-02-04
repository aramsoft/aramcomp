package aramframework.com.sym.ccm.cca.web;

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
import aramframework.com.sym.ccm.cca.domain.CmmnCodeVO;
import aramframework.com.sym.ccm.cca.service.CmmnCodeManageService;
import aramframework.com.sym.ccm.ccc.domain.CmmnClCodeVO;
import aramframework.com.sym.ccm.ccc.service.CmmnClCodeManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를  정의한다
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
public class CmmnCodeManageController {

	@Autowired
	private CmmnCodeManageService cmmnCodeManageService;

	@Autowired
	private CmmnClCodeManageService cmmnClCodeManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통코드 목록을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@IncludedInfo(name = "공통코드", order = 6010, gid = 60)
	@RequestMapping(value = "/sym/ccm/cca/listCmmnCode.do")
	public String listCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		cmmnCodeVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmmnCodeManageService.selectCmmnCodeList(cmmnCodeVO));

		int totCnt = cmmnCodeManageService.selectCmmnCodeListCnt(cmmnCodeVO);
		cmmnCodeVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeList");
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/detailCmmnCode.do")
	public String detailCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO) {

		cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);

		return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeDetail");
	}

	/**
	 * 공통분류코드 등록 전처리 화면으로 이동한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/registCmmnCode.do")
	@Secured("ROLE_ADMIN")
	public String registCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO, 
			ModelMap model) {

		CmmnClCodeVO cmmnClCodeVO = new CmmnClCodeVO();
		cmmnClCodeVO.setRecordPerPage(999999);
		cmmnClCodeVO.setFirstIndex(0);
		cmmnClCodeVO.setSearchCondition("CodeList");

		model.addAttribute("cmmnClCode", cmmnClCodeManageService.selectCmmnClCodeList(cmmnClCodeVO));

		return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeRegist");
	}

	/**
	 * 공통코드를 등록한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/insertCmmnCode.do")
	@Secured("ROLE_ADMIN")
	public String insertCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(cmmnCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnCodeVO.setFrstRegisterId(loginVO.getUniqId());

		cmmnCodeManageService.insertCmmnCode(cmmnCodeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/ccm/cca/listCmmnCode.do");
	}

	/**
	 * 공통분류코드를 수정 전처리 화면으로 이동한다
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/editCmmnCode.do")
	@Secured("ROLE_ADMIN")
	public String editCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO) {
		
		cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);

		return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeEdit");
	}

	/**
	 * 공통코드를 수정한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/updateCmmnCode.do")
	@Secured("ROLE_ADMIN")
	public String updateCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(cmmnCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/cca/CmmnCodeEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cmmnCodeVO.setLastUpdusrId(loginVO.getUniqId());

		cmmnCodeManageService.updateCmmnCode(cmmnCodeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/ccm/cca/listCmmnCode.do");
	}

	/**
	 * 공통코드를 삭제한다.
	 * 
	 * @param cmmnCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/cca/deleteCmmnCode.do")
	@Secured("ROLE_ADMIN")
	public String deleteCmmnCode(
			@ModelAttribute CmmnCodeVO cmmnCodeVO, 
			ModelMap model) {

		cmmnCodeManageService.deleteCmmnCode(cmmnCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/ccm/cca/listCmmnCode.do");
	}

}