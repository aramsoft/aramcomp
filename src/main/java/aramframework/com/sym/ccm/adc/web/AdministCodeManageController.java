package aramframework.com.sym.ccm.adc.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.ccm.adc.service.AdministCodeVO;
import aramframework.com.sym.ccm.adc.service.AdministCodeManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행정코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
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
public class AdministCodeManageController {

	@Resource(name = "administCodeManageService")
	private AdministCodeManageService administCodeManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행정코드 목록을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	@IncludedInfo(name = "행정코드관리", order = 6050, gid = 60)
	@RequestMapping(value = "/sym/ccm/adc/listAdministCode.do")
	public String listAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		administCodeVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", administCodeManageService.selectAdministCodeList(administCodeVO));

		int totCnt = administCodeManageService.selectAdministCodeListCnt(administCodeVO);
		administCodeVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeList");
	}

	/**
	 * 행정코드 상세항목을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/detailAdministCode.do")
	public String detailAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO) {
		
		administCodeManageService.selectAdministCodeDetail(administCodeVO);

		return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeDetail");
	}

	/**
	 * 공통분류코드 등록 전처리 화면으로 이동한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/registAdministCode.do")
	@Secured("ROLE_ADMIN")
	public String registAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO) {

		return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeRegist");
	}

	/**
	 * 행정코드를 등록한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/insertAdministCode.do")
	@Secured("ROLE_ADMIN")
	public String insertAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(administCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeRegist");
		}

		AdministCodeVO vo = administCodeManageService.selectAdministCodeDetail(administCodeVO);
		if (vo != null) {
			model.addAttribute("message", "이미 등록된 행정구역코드가 존재합니다.");
			return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		administCodeVO.setFrstRegisterId(loginVO.getUniqId());

		administCodeManageService.insertAdministCode(administCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/ccm/adc/listAdministCode.do");
	}

	/**
	 * 행정코드를 수정 전처리 화면으로 이동한다
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/editAdministCode.do")
	@Secured("ROLE_ADMIN")
	public String editAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO) {
		
		administCodeManageService.selectAdministCodeDetail(administCodeVO);

		return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeEdit");
	}

	/**
	 * 행정코드를 수정한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/updateAdministCode.do")
	@Secured("ROLE_ADMIN")
	public String updateAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(administCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodeEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		administCodeVO.setLastUpdusrId(loginVO.getUniqId());

		administCodeManageService.updateAdministCode(administCodeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/ccm/adc/listAdministCode.do");
	}

	/**
	 * 행정코드를 삭제한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/deleteAdministCode.do")
	@Secured("ROLE_ADMIN")
	public String deleteAdministCode(
			@ModelAttribute AdministCodeVO administCodeVO, 
			ModelMap model) {

		administCodeManageService.deleteAdministCode(administCodeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/ccm/adc/listAdministCode.do");
	}

	/**
	 * 행정코드 팝업 목록을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	@RequestMapping(value = "/sym/ccm/adc/listAdministCodePopup.do")
	public String listAdministCodePopup(
			@ModelAttribute AdministCodeVO administCodeVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		administCodeVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", administCodeManageService.selectAdministCodeList(administCodeVO));

		int totCnt = administCodeManageService.selectAdministCodeListCnt(administCodeVO);
		administCodeVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/adc/AdministCodePopup");
	}

}