package aramframework.com.uss.ion.uas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.uas.domain.UserAbsnceVO;
import aramframework.com.uss.ion.uas.service.UserAbsnceService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 사용자부재에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 사용자부재에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 사용자부재의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class UserAbsnceController {

	@Autowired
	private UserAbsnceService userAbsnceService;

	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	@IncludedInfo(name = "사용자부재관리", order = 5280, gid = 50)
	@RequestMapping("/uss/ion/uas/listUserAbsnce.do")
	@Secured("ROLE_USER")
	public String listUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		userAbsnceVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userAbsnceService.selectUserAbsnceList(userAbsnceVO));
		int totCnt = userAbsnceService.selectUserAbsnceListCnt(userAbsnceVO);

		userAbsnceVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/uas/UserAbsnceList");
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/registUserAbsnce.do")
	public String registUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO) {

		userAbsnceService.selectUserAbsnce(userAbsnceVO);

		return WebUtil.adjustViewName("/uss/ion/uas/UserAbsnceRegist");
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/insertUserAbsnce.do")
	public String insertUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		userAbsnceVO.setLastUpdusrId(loginVO.getId());

		userAbsnceService.insertUserAbsnce(userAbsnceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/uas/listUserAbsnce.do");
	}

	/**
	 * 사용자부재정보를 수정화면으로 이동한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/editUserAbsnce.do")
	public String editUserAbsnce(
			UserAbsnceVO userAbsnceVO,
			ModelMap model) {

		model.addAttribute(userAbsnceService.selectUserAbsnce(userAbsnceVO));

		return WebUtil.adjustViewName("/uss/ion/uas/UserAbsnceEdit");
	}

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/updateUserAbsnce.do")
	public String updateUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		userAbsnceVO.setLastUpdusrId(loginVO.getId());

		userAbsnceService.updateUserAbsnce(userAbsnceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/uas/listUserAbsnce.do");
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/deleteUserAbsnce.do")
	public String deleteUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO,
			ModelMap model) {

		userAbsnceService.deleteUserAbsnce(userAbsnceVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/uas/listUserAbsnce.do");
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/deleteListUserAbsnce.do")
	public String deleteListUserAbsnce(
			@ModelAttribute UserAbsnceVO userAbsnceVO,
			@RequestParam String userIds, 
			ModelMap model) {

		userAbsnceService.deleteUserAbsnces(userIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/uas/listUserAbsnce.do");
	}

	/**
	 * MyPage에 사용자부재정보를 제공하기 위해 목록을 조회한다.
	 * 
	 * @param userAbsnceVO
	 */
	@RequestMapping("/uss/ion/uas/listUserAbsnceMainPage.do")
	public String selectUserAbsnceMainList(
			@ModelAttribute UserAbsnceVO userAbsnceVO, 
			ModelMap model) {

		userAbsnceVO.setSelAbsnceAt("A");

		userAbsnceVO.getSearchVO().setSizeAndOffset(5, 0);

		model.addAttribute("resultList", userAbsnceService.selectUserAbsnceList(userAbsnceVO));

		return WebUtil.adjustViewName("/uss/ion/uas/UserAbsnceMainPage");
	}
	
}
