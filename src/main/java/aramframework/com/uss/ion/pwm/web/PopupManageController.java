package aramframework.com.uss.ion.pwm.web;

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

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.pwm.domain.PopupManageVO;
import aramframework.com.uss.ion.pwm.service.PopupManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 팝업창에 대한 Controller를 정의한다.
 * 
 * 상세내용 - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다. 
 *         - 팝업창의 조회기능은 목록조회, 상세조회로, 사용자 화면 보기로 구분된다.
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
public class PopupManageController {

	@Autowired
	private PopupManageService popupManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 팝업창관리 목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	@IncludedInfo(name = "팝업창관리", order = 5210, gid = 50)
	@RequestMapping(value = "/uss/ion/pwm/listPopup.do")
	@Secured("ROLE_USER")
	public String listPopup(
			@ModelAttribute PopupManageVO popupManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		popupManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", popupManageService.selectPopupList(popupManageVO));

		int totCnt = (Integer) popupManageService.selectPopupListCnt(popupManageVO);
		popupManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/pwm/PopupList");
	}

	/**
	 * 통합링크관리 목록을 상세조회 조회한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/detailPopup.do")
	public String detailPopup(
			@ModelAttribute PopupManageVO popupManageVO) {

		// 상세정보 불러오기
		popupManageService.selectPopupDetail(popupManageVO);

		return WebUtil.adjustViewName("/uss/ion/pwm/PopupDetail");
	}

	// 팝업창시작일자(시)
	@ModelAttribute("ntceBgndeHH")
	public List<ComCodeVO> ntceBgndeHH() { return WebUtil.getTimeHH();}
	// 팝업창시작일자(분)
	@ModelAttribute("ntceBgndeMM")
	public List<ComCodeVO> ntceBgndeMM() { return WebUtil.getTimeMM();}
	// 팝업창종료일자(시)
	@ModelAttribute("ntceEnddeHH")
	public List<ComCodeVO> ntceEnddeHH() { return WebUtil.getTimeHH();}
	// 팝업창정료일자(분)
	@ModelAttribute("ntceEnddeMM")
	public List<ComCodeVO> ntceEnddeMM() { return WebUtil.getTimeMM();}

	/**
	 * 통합링크관리 등록화면으로 이동한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/registPopup.do")
	public String registPopup(
			@ModelAttribute PopupManageVO popupManageVO) {

		return WebUtil.adjustViewName("/uss/ion/pwm/PopupRegist");
	}

	/**
	 * 통합링크관리를 등록한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/insertPopup.do")
	public String insertPopup(
			@ModelAttribute PopupManageVO popupManageVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(popupManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/pwm/PopupRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		popupManageVO.setFrstRegisterId(loginVO.getUniqId());

		// 저장
		popupManageService.insertPopup(popupManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/pwm/listPopup.do");
	}

	/**
	 * 통합링크관리 수정화면으로 이동한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/editPopup.do")
	public String editPopup(
			@ModelAttribute PopupManageVO popupManageVO) {

		popupManageService.selectPopupDetail(popupManageVO);

		String sNtceBgnde = popupManageVO.getNtceBgnde();
		String sNtceEndde = popupManageVO.getNtceEndde();

		popupManageVO.setNtceBgndeHH(sNtceBgnde.substring(8, 10));
		popupManageVO.setNtceBgndeMM(sNtceBgnde.substring(10, 12));

		popupManageVO.setNtceEnddeHH(sNtceEndde.substring(8, 10));
		popupManageVO.setNtceEnddeMM(sNtceEndde.substring(10, 12));

		return WebUtil.adjustViewName("/uss/ion/pwm/PopupEdit");
	}

	/**
	 * 통합링크관리를 수정한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/updatePopup.do")
	public String updatePopup(
			@ModelAttribute PopupManageVO popupManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(popupManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/pwm/PopupEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		popupManageVO.setLastUpdusrId(loginVO.getUniqId());

		// 저장
		popupManageService.updatePopup(popupManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/pwm/listPopup.do");
	}

	/**
	 * 통합링크관리 목록을 삭제한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/deletePopup.do")
	public String deletePopup(
			@ModelAttribute PopupManageVO popupManageVO, 
			ModelMap model) {

		popupManageService.deletePopup(popupManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/pwm/listPopup.do");
	}

	/**
	 * 팝업창을 오픈 한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/openPopup.do")
	public String openPopup(
			@RequestParam String fileUrl, 
			@RequestParam String stopVewAt,
			@RequestParam String popupId, 
			ModelMap model) {

		model.addAttribute("stopVewAt", stopVewAt);
		model.addAttribute("popupId", popupId);

		return fileUrl;
	}

	/**
	 * 팝업창정보를 조회한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/ajaxPopupInfoJson.do")
	public ModelAndView egovPopupManageInfoAjax(
			@ModelAttribute PopupManageVO popupManageVO) {

		ModelAndView modelAndView = new ModelAndView("jsonView");

		popupManageService.selectPopupDetail(popupManageVO);

        return modelAndView;
	}

	/**
	 * 팝업창관리 메인 테스트 목록을 조회한다.
	 * 
	 * @param popupManageVO
	 */
	@RequestMapping(value = "/uss/ion/pwm/listPopupMainPage.do")
	public String listPopupMainPage(
			@ModelAttribute PopupManageVO popupManageVO, 
			ModelMap model) {

		popupManageVO.setRecordPerPage(5);
		popupManageVO.setFirstIndex(0);

		model.addAttribute("resultList", popupManageService.selectPopupMainList(popupManageVO));

		return WebUtil.adjustViewName("/uss/ion/pwm/PopupMainPage");
	}

}