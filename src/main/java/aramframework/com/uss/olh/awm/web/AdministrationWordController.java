package aramframework.com.uss.olh.awm.web;

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
import aramframework.com.uss.olh.awm.service.AdministrationWordVO;
import aramframework.com.uss.olh.awm.service.AdministrationWordService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행정전문용어사전관리를 처리하는 Controller Class 구현
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
public class AdministrationWordController {

	@Autowired
	private AdministrationWordService administrationWordService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행정전문용어사전관리 목록을 조회한다.
	 * 
	 * @param administrationWordVO
	 */
	@IncludedInfo(name = "행정전문용어사전관리", order = 5111, gid = 50)
	@RequestMapping(value = "/uss/olh/awm/listAdministrationWord.do")
	@Secured("ROLE_USER")
	public String listAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		administrationWordVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", administrationWordService.selectAdministrationWordList(administrationWordVO));

		int totCnt = (Integer) administrationWordService.selectAdministrationWordListCnt(administrationWordVO);
		administrationWordVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordList");
	}

	/**
	 * 행정전문용어사전관리 목록을 상세조회한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/detailAdministrationWord.do")
	public String detailAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO) {

		administrationWordService.selectAdministrationWordDetail(administrationWordVO);

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordDetail");
	}

	/**
	 * 행정전문용어사전관리 등록화면으로 이동한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/registAdministrationWord.do")
	public String egistAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO) {

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordRegist");
	}

	/**
	 * 행정전문용어사전관리를 등록한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/insertAdministrationWord.do")
	public String insertAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(administrationWordVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		administrationWordVO.setFrstRegisterId(loginVO.getUniqId());

		administrationWordService.insertAdministrationWord(administrationWordVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olh/awm/listAdministrationWord.do");
	}

	/**
	 * 행정전문용어사전관리 수정화면으로 이동한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/editAdministrationWord.do")
	public String editAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO) {

		administrationWordService.selectAdministrationWordDetail(administrationWordVO);

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordEdit");
	}

	/**
	 * 행정전문용어사전관리를 수정한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/updateAdministrationWord.do")
	public String updateAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(administrationWordVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		administrationWordVO.setLastUpdusrId(loginVO.getUniqId());

		administrationWordService.updateAdministrationWord(administrationWordVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olh/awm/listAdministrationWord.do");
	}

	/**
	 * 행정전문용어사전관리 목록을 삭제한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/deleteAdministrationWord.do")
	public String deleteAdministrationWord(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			ModelMap model) {

		administrationWordService.deleteAdministrationWord(administrationWordVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olh/awm/listAdministrationWord.do");
	}

	/**
	 * 행정전문용어사전 목록을 조회한다.
	 * 
	 * @param administrationWordVO
	 */
	@IncludedInfo(name = "행정전문용어사전", order = 5110, gid = 50)
	@RequestMapping(value = "/uss/olh/awm/listAdministrationWordUser.do")
	public String listAdministrationUserWord(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		administrationWordVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", administrationWordService.selectAdministrationWordList(administrationWordVO));

		int totCnt = (Integer) administrationWordService.selectAdministrationWordListCnt(administrationWordVO);
		administrationWordVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordUserList");
	}

	/**
	 * 행정전문용어사전 목록을 상세조회 조회한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/detailAdministrationWordUser.do")
	public String detailAdministrationUserWord(
			@ModelAttribute AdministrationWordVO administrationWordVO) {

		administrationWordService.selectAdministrationWordDetail(administrationWordVO);

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordUserDetail");
	}

	/**
	 * 행정전문용어사전관리 메인 목록을 조회한다.
	 * 
	 * @param administrationWordVO
	 */
	@RequestMapping(value = "/uss/olh/awm/listAdministrationWordMainPage.do")
	public String listAdministrationWordMainPage(
			@ModelAttribute AdministrationWordVO administrationWordVO, 
			ModelMap model) {

		administrationWordVO.setRecordPerPage(5);
		administrationWordVO.setFirstIndex(0);

		model.addAttribute("resultList", administrationWordService.selectAdministrationWordList(administrationWordVO));

		return WebUtil.adjustViewName("/uss/olh/awm/AdministrationWordMainPage");
	}
	
}
