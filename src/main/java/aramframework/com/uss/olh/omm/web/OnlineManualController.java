package aramframework.com.uss.olh.omm.web;

import javax.servlet.http.HttpServletRequest;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olh.omm.domain.OnlineManualVO;
import aramframework.com.uss.olh.omm.service.OnlineManualService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인메뉴얼를 처리하는 Controller Class 구현
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
public class OnlineManualController {

	@Autowired
	private OnlineManualService onlineManualService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사용자 온라인메뉴얼 조회한다.
	 * 
	 */
	@RequestMapping(value = "/uss/olh/omm/setOnlineManual.do")
	public String setOnlineManual(
			HttpServletRequest request, 
			ModelMap model) {
		
		model.addAttribute("onMnlId", request.getParameter("onMnlId"));
		return "aramframework/com/uss/olh/omm/OnlineManual";
	}

	/**
	 * 사용자 온라인메뉴얼 목록을 조회한다.
	 * 
	 * @param onlineManualVO
	 */
	@IncludedInfo(name = "사용자온라인매뉴얼", order = 5121, gid = 50)
	@RequestMapping(value = "/uss/olh/omm/listOnlineManualUser.do")
	public String listOnlineManualUser(
			@ModelAttribute OnlineManualVO onlineManualVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		onlineManualVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", onlineManualService.selectOnlineManualList(onlineManualVO));
		int totCnt = (Integer) onlineManualService.selectOnlineManualListCnt(onlineManualVO);

		onlineManualVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualUserList");
	}

	/**
	 * 사용자 온라인메뉴얼 목록을 상세조회한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/detailOnlineManualUser.do")
	public String detailOnlineManualUser(
			OnlineManualVO onlineManualVO,
			ModelMap model) {

		model.addAttribute(onlineManualService.selectOnlineManualDetail(onlineManualVO));

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualUserDetail");
	}

	/**
	 * 온라인메뉴얼 목록을 조회한다.
	 * 
	 * @param onlineManualVO
	 */
	@IncludedInfo(name = "온라인매뉴얼관리", order = 5120, gid = 50)
	@RequestMapping(value = "/uss/olh/omm/listOnlineManual.do")
	@Secured("ROLE_USER")
	public String listOnlineManual(
			@ModelAttribute OnlineManualVO onlineManualVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		onlineManualVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", onlineManualService.selectOnlineManualList(onlineManualVO));
		int totCnt = (Integer) onlineManualService.selectOnlineManualListCnt(onlineManualVO);

		onlineManualVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualList");
	}

	/**
	 * 온라인메뉴얼 목록을 상세조회한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/detailOnlineManual.do")
	public String detailOnlineManual(
			OnlineManualVO onlineManualVO,
			ModelMap model) {

		model.addAttribute(onlineManualService.selectOnlineManualDetail(onlineManualVO));

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualDetail");
	}

	/**
	 * 온라인메뉴얼 등록화면으로 이동한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/registOnlineManual.do")
	public String registOnlineManual(
			@ModelAttribute OnlineManualVO onlineManualVO) {

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualRegist");
	}

	/**
	 * 온라인메뉴얼를 등록한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/insertOnlineManual.do")
	public String insertOnlineManual(
			@ModelAttribute OnlineManualVO onlineManualVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(onlineManualVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualRegist");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlineManualVO.setFrstRegisterId(loginVO.getUniqId());

		// 저장
		onlineManualService.insertOnlineManual(onlineManualVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olh/omm/listOnlineManual.do");
	}

	/**
	 * 온라인메뉴얼 수정화면으로 이동한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/editOnlineManual.do")
	public String editOnlineManual(
			OnlineManualVO onlineManualVO,
			ModelMap model) {

		model.addAttribute(onlineManualService.selectOnlineManualDetail(onlineManualVO));

		// 온라인메뉴얼 구분 설정
		cmmUseService.populateCmmCodeList("COM041", "COM041_onlineMnlSe");

		return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualEdit");
	}

	/**
	 * 온라인메뉴얼를 수정한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/updateOnlineManual.do")
	public String updateOnlineManual(
			@ModelAttribute OnlineManualVO onlineManualVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(onlineManualVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/omm/OnlineManualEdit");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlineManualVO.setLastUpdusrId(loginVO.getUniqId());

		onlineManualService.updateOnlineManual(onlineManualVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olh/omm/listOnlineManual.do");
	}

	/**
	 * 온라인메뉴얼 을 삭제한다.
	 * 
	 * @param onlineManualVO
	 */
	@RequestMapping(value = "/uss/olh/omm/deleteOnlineManual.do")
	public String deleteOnlineManual(
			@ModelAttribute OnlineManualVO onlineManualVO, 
			ModelMap model) {

		onlineManualService.deleteOnlineManual(onlineManualVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olh/omm/listOnlineManual.do");
	}

}
