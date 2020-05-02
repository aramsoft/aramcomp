package aramframework.com.uss.ion.evt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.evt.domain.EventAtdrnVO;
import aramframework.com.uss.ion.evt.domain.EventManageVO;
import aramframework.com.uss.ion.evt.service.EventManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 행사관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 행사관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class EventManageController {

	@Autowired
	private EventManageService eventManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행사관리정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * 
	 * @param eventManageVO
	 */
	@IncludedInfo(name = "행사신청관리", order = 5320, gid = 50)
	@RequestMapping(value = "/uss/ion/evt/listEventReqst.do")
	@Secured("ROLE_USER")
	public String listEventReqst(
			@ModelAttribute EventManageVO eventManageVO, 
			ModelMap model) {

		eventManageVO.setSearchKeyword(eventManageVO.getSearchYear() + eventManageVO.getSearchMonth());

		PaginationInfo paginationInfo = new PaginationInfo();
		eventManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventManageService.selectEventManageList(eventManageVO));
		int totCnt = eventManageService.selectEventManageListCnt(eventManageVO);

		eventManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 행사년월
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}
		model.addAttribute("yearList", yearList);
		// 행사구분
		cmmUseService.populateCmmCodeList("COM053", "COM053_eventSe");

		return "com/uss/ion/evt/EventReqstList";
	}

	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/detailEventReqst.do")
	public String detailEgovEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO, 
			@RequestParam(value="popup", required=false) String popup,
			ModelMap model) {

		model.addAttribute(eventManageService.selectEventManage(eventManageVO));

		if ("true".equals(popup)) {
			model.addAttribute("check_popup", "Y");
		}
		
		return "com/uss/ion/evt/EventReqstDetail";
	}

	/**
	 * 행사관리 등록 화면으로 이동한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/registEventReqst.do")
	public String registEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO) {

		cmmUseService.populateCmmCodeList("COM053", "COM053_eventSe");

		return "com/uss/ion/evt/EventReqstRegist";
	}

	/**
	 * 행사관리정보를 신규로 등록한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/insertEventReqst.do")
	public String insertEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(eventManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/uss/ion/evt/EventReqstRegist";
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventManageVO.setFrstRegisterId(loginVO.getUserId());

		eventManageService.insertEventManage(eventManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventReqst.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 행사관리 수정 화면으로 이동한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/editEventReqst.do")
	public String editEgovEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO, 
			ModelMap model) {

		model.addAttribute(eventManageService.selectEventManage(eventManageVO));

		return "com/uss/ion/evt/EventReqstEdit";
	}

	/**
	 * 기 등록된 행사관리정보를 수정한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/updateEventReqst.do")
	public String updateEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(eventManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/uss/ion/evt/EventReqstEdit";
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventManageVO.setLastUpdusrId(loginVO.getUserId());

		eventManageService.updateEventManage(eventManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventReqst.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 기 등록된 행사관리정보를 삭제한다.
	 * 
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/deleteEventReqst.do")
	public String deleteEventReqst(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventManageVO eventManageVO, 
			ModelMap model) {

		eventManageService.deleteEventManage(eventManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventReqst.do");
	    return "com/cmm/redirect";
	}

	/** 행사접수관리 **/
	/**
	 * 행사접수관리정보를 관리하기 위해 등록된 행사접수관리 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	@IncludedInfo(name = "행사접수관리", order = 5321, gid = 50)
	@RequestMapping(value = "/uss/ion/evt/listEventRcrpt.do")
	@Secured("ROLE_USER")
	public String listEventRcrpt(
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventAtdrnVO.setApplcntId(loginVO.getUserId());// 사용자UniqID
		eventAtdrnVO.setSearchKeyword(eventAtdrnVO.getSearchYear() + eventAtdrnVO.getSearchMonth());

		PaginationInfo paginationInfo = new PaginationInfo();
		eventAtdrnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventManageService.selectEventAtdrnList(eventAtdrnVO));
		int totCnt = eventManageService.selectEventAtdrnListCnt(eventAtdrnVO);

		eventAtdrnVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 행사년월
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}
		model.addAttribute("yearList", yearList);

		// 행사구분
		cmmUseService.populateCmmCodeList("COM053", "COM053_eventSe");

		return "com/uss/ion/evt/EventRceptList";
	}

	/**
	 * 등록된 행사접수관리의 상세정보를 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	@RequestMapping(value = "/uss/ion/evt/detailEventRcrpt.do")
	public String detailEventRcrpt(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			@ModelAttribute EventManageVO eventManageVO,
			ModelMap model) {

		model.addAttribute(eventManageService.selectEventAtdrn(eventAtdrnVO));
		model.addAttribute(eventManageService.selectEventManage(eventManageVO));

		return "com/uss/ion/evt/EventRceptDetail";
	}

	/**
	 * 행사접수관리 등록 화면으로 이동한다.
	 * 
	 * @param eventAtdrnVO
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/registEventRcrpt.do")
	public String registEventRcrpt(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			@ModelAttribute EventManageVO eventManageVO) {

		eventManageService.selectEventManage(eventManageVO);
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventAtdrnVO.setApplcntId(loginVO.getUserId());
		eventAtdrnVO.setApplcntNm(loginVO.getName());
		eventAtdrnVO.setOrgnztNm(loginVO.getOrgnztNm());
		eventAtdrnVO.setEventId(eventManageVO.getEventId());

		return "com/uss/ion/evt/EventRceptRegist";
	}

	/**
	 * 행사접수관리정보를 신규로 등록한다.
	 * 
	 * @param eventAtdrnVO
	 * @param eventManageVO
	 */
	@RequestMapping(value = "/uss/ion/evt/insertEventRcrpt.do")
	public String insertEventRcrpt(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			@ModelAttribute EventManageVO eventManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(eventAtdrnVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return "com/uss/ion/evt/EventRceptRegist";
		} 
		
		eventManageService.selectEventManage(eventManageVO);

		if (eventManageVO.getGarden() <= eventManageService.selectEventReqstAtdrnListCnt(eventAtdrnVO)) {
			model.addAttribute("errMessage", "정원초과");
			return "com/uss/ion/evt/EventRceptRegist";
		}
		
		java.util.Calendar cal = java.util.Calendar.getInstance();

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
		int iDate = cal.get(java.util.Calendar.DATE);

		// 검색 설정
		String sSearchDate = "";
		sSearchDate += Integer.toString(iYear);
		sSearchDate += Integer.toString(iMonth + 1).length() == 1 ? "0" + Integer.toString(iMonth + 1) : Integer.toString(iMonth + 1);
		sSearchDate += Integer.toString(iDate);
		eventAtdrnVO.setReqstDe(sSearchDate);// 신청일자

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventAtdrnVO.setFrstRegisterId(loginVO.getUserId());

		eventManageService.insertEventAtdrn(eventAtdrnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventRcrpt.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 기 등록된 행사접수관리정보를 취소한다.
	 * 
	 * @param eventAtdrnVO
	 */
	@RequestMapping(value = "/uss/ion/evt/deleteEventRcrpt.do")
	public String deleteEventRcrpt(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			ModelMap model) {

		eventManageService.deleteEventAtdrn(eventAtdrnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventRcrpt.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	@IncludedInfo(name = "행사접수승인관리", order = 5322, gid = 50)
	@RequestMapping(value = "/uss/ion/evt/listEventRceptConfm.do")
	@Secured("ROLE_USER")
	public String listEventRceptConfm(
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventAtdrnVO.setSanctnerId(loginVO.getUserId());// 승인권자UniqID

		eventAtdrnVO.setSearchKeyword(eventAtdrnVO.getSearchYear() + eventAtdrnVO.getSearchMonth());

		PaginationInfo paginationInfo = new PaginationInfo();
		eventAtdrnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventManageService.selectEventRceptConfmList(eventAtdrnVO));
		int totCnt = eventManageService.selectEventRceptConfmListCnt(eventAtdrnVO);

		eventAtdrnVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 행사년월
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}
		model.addAttribute("yearList", yearList);
		// 행사구분
		cmmUseService.populateCmmCodeList("COM053", "COM053_eventSe");

		return "com/uss/ion/evt/EventRceptConfm";
	}

	/**
	 * 기 등록된 행사접수관리정보를 승인/반려처리한다.
	 * 
	 * @param eventAtdrnVO
	 */
	@RequestMapping(value = "/uss/ion/evt/updateEventRceptConfm.do")
	public String updateEventRceptConfm(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			@RequestParam String checkedEventRceptForConfm,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		eventAtdrnVO.setSanctnerId(loginVO.getUserId());
		eventAtdrnVO.setFrstRegisterId(loginVO.getUserId());

		eventManageService.updateEventAtdrn(eventAtdrnVO, checkedEventRceptForConfm);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/ion/evt/listEventRceptConfm.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 행사접수자 정보 목록을 조회한다.
	 * 
	 * @param eventAtdrnVO
	 */
	// @IncludedInfo(name="행사참가요청자목록", order = 942)
	@RequestMapping(value = "/uss/ion/evt/listEventReqstAtdrn.do")
	@Secured("ROLE_USER")
	public String listEventReqstAtdrn(
			@ModelAttribute EventAtdrnVO eventAtdrnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		eventAtdrnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventManageService.selectEventReqstAtdrnList(eventAtdrnVO));
		int totCnt = eventManageService.selectEventReqstAtdrnListCnt(eventAtdrnVO);

		eventAtdrnVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/uss/ion/evt/EventReqstAtdrnList";
	}

}
