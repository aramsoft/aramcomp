package aramframework.com.uss.ion.nts.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.nts.domain.NoteTrnsmitVO;
import aramframework.com.uss.ion.nts.service.NoteTrnsmitService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 보낸쪽지함관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NoteTrnsmitController {

	@Autowired
	private NoteTrnsmitService noteTrnsmitService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 보낸쪽지함관리 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteTrnsmitVO
	 */
	@IncludedInfo(name = "보낸쪽지함관리", order = 5252, gid = 50)
	@RequestMapping(value = "/uss/ion/nts/listNoteTrnsmit.do")
	@Secured("ROLE_USER")
	public String listNoteTrnsmit(
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 발신자설정
		noteTrnsmitVO.setTrnsmiterId(loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		noteTrnsmitVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", noteTrnsmitService.selectNoteTrnsmitList(noteTrnsmitVO));

		int totCnt = (Integer) noteTrnsmitService.selectNoteTrnsmitListCnt(noteTrnsmitVO);
		noteTrnsmitVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "com/uss/ion/nts/NoteTrnsmitList";
	}

	/**
	 * 보낸쪽지함관리 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteTrnsmitVO
	 */
	@RequestMapping(value = "/uss/ion/nts/deleteListNoteTrnsmit.do")
	@Secured("ROLE_USER")
	public String deleteListNoteTrnsmit(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
			HttpServletRequest request, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

    	String[] uniqIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		uniqIds = request.getParameterValues("uniqIds");

		for (int i = 0; i < uniqIds.length; i++) {
			String[] id = uniqIds[i].split("-");
			noteTrnsmitVO.setFrstRegisterId(loginVO.getUserId());
			noteTrnsmitVO.setLastUpdusrId(loginVO.getUserId());
			noteTrnsmitVO.setNoteId(id[0]);
			noteTrnsmitVO.setNoteTrnsmitId(id[1]);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/nts/listNoteTrnsmit.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 보낸쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/detailNoteTrnsmit.do")
	public String detailNoteTrnsmit(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			ModelMap model)  {

		model.addAttribute("noteTrnsmit", noteTrnsmitService.selectNoteTrnsmitDetail(noteTrnsmitVO));
		model.addAttribute("resultRecptnEmp", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO));
		
		return "com/uss/ion/nts/NoteTrnsmitDetail";
	}

	/**
	 * 보낸쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/deleteNoteTrnsmit.do")
	public String deleteNoteTrnsmit(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			ModelMap model)  {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		noteTrnsmitVO.setFrstRegisterId(loginVO.getUserId());
		noteTrnsmitVO.setLastUpdusrId(loginVO.getUserId());

		noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/nts/listNoteTrnsmit.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 수신자목록을 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/confirmNoteTrnsmit.do")
	public String confirmNoteTrnsmit(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			ModelMap model) {

		model.addAttribute("resultList", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO));

		return "com/uss/ion/nts/NoteTrnsmitCnfirm";
	}

	/**
	 * 수신자목록을 조회한다.
	 * 
	 * @param noteTrnsmitVO
	 * @param sCmd
	 */
	@RequestMapping(value = "/uss/ion/nts/deleteNoteConfirm.do")
	public String deleteNoteConfirm (
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NoteTrnsmitVO noteTrnsmitVO, 
			ModelMap model) {

		noteTrnsmitService.deleteNoteRecptn(noteTrnsmitVO);

		model.addAttribute("resultList", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO));

		return "com/uss/ion/nts/NoteTrnsmitCnfirm";
	}

}
