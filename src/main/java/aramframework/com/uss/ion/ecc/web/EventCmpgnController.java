package aramframework.com.uss.ion.ecc.web;

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
import aramframework.com.uss.ion.ecc.domain.EventCmpgnVO;
import aramframework.com.uss.ion.ecc.domain.TnextrlHrInfoVO;
import aramframework.com.uss.ion.ecc.service.EventCmpgnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행사/이벤트/캠페인을 처리하는 Controller Class 구현
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
public class EventCmpgnController {

	@Autowired
	private EventCmpgnService eventCmpgnService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행사/이벤트/캠페인 목록 팝업을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/listEventCmpgnPopup.do")
	public String listEventCmpgnPopup(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		eventCmpgnVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventCmpgnService.selectEventCmpgnList(eventCmpgnVO));
		int totCnt = (Integer) eventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);

		eventCmpgnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnListPopup");
	}

	/**
	 * 행사/이벤트/캠페인 목록을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@IncludedInfo(name = "행사/이벤트/캠페인", order = 5200, gid = 50)
	@RequestMapping(value = "/uss/ion/ecc/listEventCmpgn.do")
	@Secured("ROLE_USER")
	public String listEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		eventCmpgnVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventCmpgnService.selectEventCmpgnList(eventCmpgnVO));
		int totCnt = (Integer) eventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);

		eventCmpgnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnList");
	}

	/**
	 * 행사/이벤트/캠페인 상세조회 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/detailEventCmpgn.do")
	public String detailEventCmpgn(
			EventCmpgnVO eventCmpgnVO,
			ModelMap model) {

		model.addAttribute(eventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO));

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnDetail");
	}

	/**
	 * 행사/이벤트/캠페인을 등록하는 화면으로 이동한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/registEventCmpgn.do")
	public String registEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String) loginVO.getUserSe());

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnRegist");
	}

	/**
	 * 행사/이벤트/캠페인을 등록 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/insertEventCmpgn.do")
	public String insertEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 서버 validate 체크
		beanValidator.validate(eventCmpgnVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("SUserSe", (String) loginVO.getUserSe());
			return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnRegist");
		}
		// 아이디 설정
		eventCmpgnVO.setFrstRegisterId(loginVO.getUniqId());

		eventCmpgnService.insertEventCmpgn(eventCmpgnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listEventCmpgn.do");
	}

	/**
	 * 행사/이벤트/캠페인을 수정하는 화면으로 이동한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/editEventCmpgn.do")
	public String editEventCmpgn(
			EventCmpgnVO eventCmpgnVO,
			ModelMap model) {

		model.addAttribute(eventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String) loginVO.getUserSe());

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnEdit");
	}

	/**
	 * 행사/이벤트/캠페인을 수정 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/updateEventCmpgn.do")
	public String updateEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 서버 validate 체크
		beanValidator.validate(eventCmpgnVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("SUserSe", (String) loginVO.getUserSe());
			return WebUtil.adjustViewName("/uss/ion/ecc/EventCmpgnEdit");
		}
		// 아이디 설정
		eventCmpgnVO.setLastUpdusrId(loginVO.getUniqId());

		eventCmpgnService.updateEventCmpgn(eventCmpgnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listEventCmpgn.do");
	}

	/**
	 * 행사/이벤트/캠페인삭제 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/deleteEventCmpgn.do")
	public String deleteEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			ModelMap model) {

		eventCmpgnService.deleteEventCmpgn(eventCmpgnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listEventCmpgn.do");
	}

	/**
	 * 외부인사정보 목록을 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@IncludedInfo(name = "외부인사정보", order = 5201, gid = 50)
	@RequestMapping(value = "/uss/ion/ecc/listTnextrlHrInfo.do")
	@Secured("ROLE_USER")
	public String listTnextrlHrInfo(
			@ModelAttribute TnextrlHrInfoVO tnextrlHrInfoVO, 
			ModelMap model) {

		// 행사/이벤트/캠페인에서 넘어오면 자동검색 설정
		if (tnextrlHrInfoVO.getSearchMode().equals("Y")) {
			tnextrlHrInfoVO.getSearchVO().setSearchCondition("EVENT_ID");
			tnextrlHrInfoVO.getSearchVO().setSearchKeyword(tnextrlHrInfoVO.getEventId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		tnextrlHrInfoVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", eventCmpgnService.selectTnextrlHrInfoList(tnextrlHrInfoVO));
		int totCnt = (Integer) eventCmpgnService.selectTnextrlHrInfoListCnt(tnextrlHrInfoVO);

		tnextrlHrInfoVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");

		return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoList");
	}

	/**
	 * 외부인사정보 상세조회 한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/detailTnextrlHrInfo.do")
	public String detailTnextrlHrInfo(
			TnextrlHrInfoVO tnextrlHrInfoVO,
			ModelMap model) {

		model.addAttribute(eventCmpgnService.selectTnextrlHrInfoDetail(tnextrlHrInfoVO));

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoDetail");
	}

	/**
	 * 외부인사정보을 등록하는 화면으로 이동한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/registTnextrlHrInfo.do")
	public String registTnextrlHrInfo(
			@ModelAttribute TnextrlHrInfoVO tnextrlHrInfoVO) {

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoRegist");
	}

	/**
	 * 외부인사정보을 등록 한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/insertTnextrlHrInfo.do")
	public String insertTnextrlHrInfo(
			@ModelAttribute TnextrlHrInfoVO tnextrlHrInfoVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(tnextrlHrInfoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		tnextrlHrInfoVO.setFrstRegisterId(loginVO.getUniqId());

		eventCmpgnService.insertTnextrlHrInfo(tnextrlHrInfoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listTnextrlHrInfo.do");
	}

	/**
	 * 외부인사정보을 수정하는 화면으로 이동한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/editTnextrlHrInfo.do")
	public String editTnextrlHrInfo(
			TnextrlHrInfoVO tnextrlHrInfoVO,
			ModelMap model) {

		model.addAttribute(eventCmpgnService.selectTnextrlHrInfoDetail(tnextrlHrInfoVO));

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoEdit");
	}

	/**
	 * 외부인사정보을 수정 한다
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/updateTnextrlHrInfo.do")
	public String updateTnextrlHrInfo(
			@ModelAttribute TnextrlHrInfoVO tnextrlHrInfoVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(tnextrlHrInfoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ecc/TnextrlHrInfoEdit");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		tnextrlHrInfoVO.setLastUpdusrId(loginVO.getUniqId());

		eventCmpgnService.updateTnextrlHrInfo(tnextrlHrInfoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listTnextrlHrInfo.do");
	}

	/**
	 * 외부인사정보 삭제한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	@RequestMapping(value = "/uss/ion/ecc/deleteTnextrlHrInfo.do")
	public String deleteTnextrlHrInfo(
			@ModelAttribute TnextrlHrInfoVO tnextrlHrInfoVO, 
			ModelMap model) {

		eventCmpgnService.deleteTnextrlHrInfo(tnextrlHrInfoVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/ecc/listTnextrlHrInfo.do");
	}

}
