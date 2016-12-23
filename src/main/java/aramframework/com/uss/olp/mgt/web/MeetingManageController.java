package aramframework.com.uss.olp.mgt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olp.mgt.domain.MeetingManageVO;
import aramframework.com.uss.olp.mgt.service.MeetingManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 회의관리를 처리하기 위한 Controller 구현 Class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MeetingManageController {

	@Autowired
	private MeetingManageService meetingManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 부서목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/listMeetingDeptPopup.do")
	public String listMeetingDeptPopup(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		model.addAttribute("resultList", meetingManageService.selectDeptListPopup(baseVO));

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingDeptPopup");
	}

	/**
	 * 회원목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/listMeetingEmpLyrPopup.do")
	public String listMeetingEmpLyrPopup(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		model.addAttribute("resultList", meetingManageService.selectEmpLyrListPopup(baseVO));

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingEmpLyrPopup");
	}

	/**
	 * 회의정보 목록을 조회한다.
	 * 
	 * @param meetingManageVO
	 */
	@IncludedInfo(name = "회의관리", order = 5150, gid = 50)
	@RequestMapping(value = "/uss/olp/mgt/listMeeting.do")
	public String listMeeting(
			@ModelAttribute MeetingManageVO meetingManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		meetingManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", meetingManageService.selectMeetingManageList(meetingManageVO));
		int totCnt = (Integer) meetingManageService.selectMeetingManageListCnt(meetingManageVO);

		meetingManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingList");
	}

	/**
	 * 회의정보 목록을 상세조회 한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/detailMeeting.do")
	@Secured("ROLE_USER")
	public String detailMeeting(
			MeetingManageVO meetingManageVO,
			ModelMap model) {

		model.addAttribute(meetingManageService.selectMeetingManageDetail(meetingManageVO));

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingDetail");
	}

	/**
	 * 회의정보 등록화면으로 이동한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/registMeeting.do")
	@Secured("ROLE_USER")
	public String registMeeting(
			@ModelAttribute MeetingManageVO meetingManageVO) {

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingRegist");
	}

	/**
	 * 회의정보를 등록한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/insertMeeting.do")
	@Secured("ROLE_USER")
	public String insertMeeting(
			@ModelAttribute MeetingManageVO meetingManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(meetingManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/mgt/MeetingRegist");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		meetingManageVO.setFrstRegisterId(loginVO.getUniqId());

		meetingManageService.insertMeetingManage(meetingManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olp/mgt/listMeeting.do");
	}

	/**
	 * 회의정보 수정화면으로 이동한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/editMeeting.do")
	@Secured("ROLE_USER")
	public String editMeeting(
			MeetingManageVO meetingManageVO,
			ModelMap model) {

		model.addAttribute(meetingManageService.selectMeetingManageDetail(meetingManageVO));

		return WebUtil.adjustViewName("/uss/olp/mgt/MeetingEdit");
	}

	/**
	 * 회의정보를 수정한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/updateMeeting.do")
	@Secured("ROLE_USER")
	public String updateMeeting(
			@ModelAttribute MeetingManageVO meetingManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(meetingManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/mgt/MeetingEdit");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		meetingManageVO.setLastUpdusrId(loginVO.getUniqId());

		meetingManageService.updateMeetingManage(meetingManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olp/mgt/listMeeting.do");
	}

	/**
	 * 회의정보 목록을 삭제한다.
	 * 
	 * @param meetingManageVO
	 */
	@RequestMapping(value = "/uss/olp/mgt/deleteMeeting.do")
	@Secured("ROLE_USER")
	public String deleteMeeting(
			@ModelAttribute MeetingManageVO meetingManageVO, 
			ModelMap model) {

		meetingManageService.deleteMeetingManage(meetingManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olp/mgt/listMeeting.do");
	}

}
