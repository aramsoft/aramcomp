package aramframework.com.uss.ion.ntr.web;

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
import aramframework.com.uss.ion.ntr.domain.NoteRecptnVO;
import aramframework.com.uss.ion.ntr.service.NoteRecptnService;
import aramframework.com.uss.ion.nts.domain.NoteTrnsmitVO;
import aramframework.com.uss.ion.nts.service.NoteTrnsmitService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 받은쪽지함관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NoteRecptnController {

	@Autowired
	private NoteTrnsmitService noteTrnsmitService;

	@Autowired
	private NoteRecptnService noteRecptnService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 받은쪽지함관리 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteRecptnVO
	 */
	@IncludedInfo(name = "받은쪽지함관리", order = 5251, gid = 50)
	@RequestMapping(value = "/uss/ion/ntr/listNoteRecptn.do")
	@Secured("ROLE_USER")
	public String listNoteRecptn(
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 수신자설정
		noteRecptnVO.setRcverId(loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		noteRecptnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", noteRecptnService.selectNoteRecptnList(noteRecptnVO));
		int totCnt = (Integer) noteRecptnService.selectNoteRecptnListCnt(noteRecptnVO);

		noteRecptnVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/uss/ion/ntr/NoteRecptnList";
	}

	/**
	 * 받은쪽지함관리 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteRecptnVO
	 */
	@RequestMapping(value = "/uss/ion/ntr/deleteListNoteRecptn.do")
	@Secured("ROLE_USER")
	public String deleteListNoteRecptn(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			HttpServletRequest request, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

    	String[] uniqIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		uniqIds = request.getParameterValues("uniqIds");

		for (int i = 0; i < uniqIds.length; i++) {
			String[] id = uniqIds[i].split("-");
			noteRecptnVO.setFrstRegisterId(loginVO.getUserId());
			noteRecptnVO.setLastUpdusrId(loginVO.getUserId());
			noteRecptnVO.setNoteId(id[0]);
			noteRecptnVO.setNoteTrnsmitId(id[1]);
			noteRecptnVO.setNoteRecptnId(id[2]);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/ntr/listNoteRecptn.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 받은쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param cmd
	 * @param noteRecptnVO
	 */
	@RequestMapping(value = "/uss/ion/ntr/detailNoteRecptn.do")
	public String detailNoteRecptn(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			ModelMap model) {

		// 로그인 객체 선언/아이디설정
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		noteRecptnVO.setFrstRegisterId(loginVO.getUserId());
		noteRecptnVO.setLastUpdusrId(loginVO.getUserId());

		model.addAttribute("noteRecptn", noteRecptnService.selectNoteRecptnDetail(noteRecptnVO));

		NoteTrnsmitVO noteTrnsmit = new NoteTrnsmitVO();
		noteTrnsmit.setNoteId(noteRecptnVO.getNoteId());

		model.addAttribute("resultRecptnEmp", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmit));

		return "com/uss/ion/ntr/NoteRecptnDetail";
	}

	/**
	 * 받은쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param cmd
	 * @param noteRecptnVO
	 */
	@RequestMapping(value = "/uss/ion/ntr/deleteNoteRecptn.do")
	public String deleteNoteRecptn(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			ModelMap model) {

		noteRecptnService.deleteNoteRecptn(noteRecptnVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/ion/ntr/listNoteRecptn.do");
	    return "com/cmm/redirect";
	}

}
