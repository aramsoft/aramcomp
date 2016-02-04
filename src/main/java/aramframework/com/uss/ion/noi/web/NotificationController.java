package aramframework.com.uss.ion.noi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.noi.domain.NotificationVO;
import aramframework.com.uss.ion.noi.service.NotificationService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 정보알림이 서비스 컨트롤러 클래스
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
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 정보알림이에 대한 목록을 조회한다.
	 * 
	 * @param notificationVO
	 */
	@IncludedInfo(name = "정보알림이", order = 5220, gid = 50)
	@RequestMapping("/uss/ion/noi/listNotification.do")
	@Secured("ROLE_USER")
	public String listNotification(
			@ModelAttribute NotificationVO notificationVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		notificationVO.fillPageInfo(paginationInfo);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		notificationVO.setUniqId(loginVO.getUniqId());

		model.addAttribute("resultList", notificationService.selectNotificationInfs(notificationVO));

		int totCnt = notificationService.selectNotificationInfsCnt(notificationVO);
		notificationVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/noi/NotificationList");
	}

	/**
	 * 정보알림이에 대한 상세정보를 조회한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/detailNotification.do")
	public String detailNotification(
			@ModelAttribute NotificationVO notificationVO, 
			ModelMap model) {

		notificationService.selectNotificationInf(notificationVO);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("sessionUniqId", loginVO.getUniqId());

		return WebUtil.adjustViewName("/uss/ion/noi/NotificationDetail");
	}

	/**
	 * 신규 정보알림이 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/registNotification.do")
	public String registNotification(
			@ModelAttribute NotificationVO notificationVO) {

		return WebUtil.adjustViewName("/uss/ion/noi/NotificationRegist");
	}

	/**
	 * 신규 정보알림이 정보를 등록한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/insertNotification.do")
	public String insertNotification(
			@ModelAttribute NotificationVO notificationVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(notificationVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/noi/NotificationRegist");
		}

		if (!notificationService.checkNotification(notificationVO)) {
			model.addAttribute("message", MessageHelper.getMessage("uss.ion.noi.alertNtfcTime"));
			return WebUtil.adjustViewName("/uss/ion/noi/NotificationRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		notificationVO.setFrstRegisterId(loginVO.getUniqId());

		notificationService.insertNotificationInf(notificationVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/noi/listNotification.do");
	}

	/**
	 * 정보알림이 수정을 위해 수정페이지로 이동한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/editNotification.do")
	public String editNotification(
			@ModelAttribute NotificationVO notificationVO) {
		
		notificationService.selectNotificationInf(notificationVO);

		return WebUtil.adjustViewName("/uss/ion/noi/NotificationEdit");
	}

	/**
	 * 정보알림이 정보를 수정한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/updateNotification.do")
	public String updateNotification(
			@ModelAttribute NotificationVO notificationVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(notificationVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/noi/NotificationEdit");
		}

		if (!notificationService.checkNotification(notificationVO)) {
			model.addAttribute("message", MessageHelper.getMessage("uss.ion.noi.alertNtfcTime"));
			return WebUtil.adjustViewName("/uss/ion/noi/NotificationEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		notificationVO.setLastUpdusrId(loginVO.getUniqId());

		notificationService.updateNotifictionInf(notificationVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/noi/listNotification.do");
	}

	/**
	 * 정보알림이 정보를 삭제한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/deleteNotification.do")
	public String deleteNotification(
			@ModelAttribute NotificationVO notificationVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		notificationVO.setLastUpdusrId(loginVO.getUniqId());

		notificationService.deleteNotifictionInf(notificationVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/noi/listNotification.do");
	}

	/**
	 * 정보알림이 표시를 조회한다.
	 * 
	 * @param notificationVO
	 */
	@RequestMapping("/uss/ion/noi/getNotifications.do")
	public String getNotifications(
			@ModelAttribute NotificationVO notificationVO, 
			ModelMap model) {
		
		model.addAttribute("resultList", notificationService.selectNotificationData());

		return WebUtil.adjustViewName("/uss/ion/noi/NotificationData");
	}
	
}
