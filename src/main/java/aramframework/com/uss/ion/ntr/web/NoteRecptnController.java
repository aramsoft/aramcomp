package aramframework.com.uss.ion.ntr.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ntr.domain.NoteRecptnVO;
import aramframework.com.uss.ion.ntr.service.NoteRecptnService;
import aramframework.com.uss.ion.nts.domain.NoteTrnsmitVO;
import aramframework.com.uss.ion.nts.service.NoteTrnsmitService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 받은쪽지함관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class NoteRecptnController {

	@Autowired
	private NoteTrnsmitService noteTrnsmitService;

	@Autowired
	private NoteRecptnService noteRecptnService;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

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
			@CommandMap Map<String, Object> commandMap,
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 변수 설정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		// 삭제 모드로 실행시
		if (sCmd.equals("del")) {
			// 한개의 값으로 삭제가 넘어올때 처리
			if (commandMap.get("checkList") instanceof String) {
				String sCheckList = (String) commandMap.get("checkList");

				String[] sArrCheckListValue = sCheckList.split(",");

				log.debug("==================================EgovNoteRecptnList");
				log.debug("checkList" + sCheckList);
				log.debug("sArrCheckListValue[0]>" + sArrCheckListValue[0]);
				log.debug("sArrCheckListValue[1]>" + sArrCheckListValue[1]);

				noteRecptnVO.setFrstRegisterId(loginVO.getUniqId());
				noteRecptnVO.setLastUpdusrId(loginVO.getUniqId());
				noteRecptnVO.setNoteId(sArrCheckListValue[0]);
				noteRecptnVO.setNoteTrnsmitId(sArrCheckListValue[1]);
				noteRecptnVO.setNoteRecptnId(sArrCheckListValue[2]);

				noteRecptnService.deleteNoteRecptn(noteRecptnVO);
			}

			// 여러개의 값으로 삭제가 넘어올때 처리
			if (commandMap.get("checkList") instanceof String[]) {
				String[] sArrCheckList = (String[]) commandMap.get("checkList");
				// log.debug("sArrCheckList" + sArrCheckList);

				for (int i = 0; i < sArrCheckList.length; i++) {
					String[] sArrCheckListValue = sArrCheckList[i].split(",");

					noteRecptnVO.setFrstRegisterId(loginVO.getUniqId());
					noteRecptnVO.setLastUpdusrId(loginVO.getUniqId());
					noteRecptnVO.setNoteId(sArrCheckListValue[0]);
					noteRecptnVO.setNoteTrnsmitId(sArrCheckListValue[1]);
					noteRecptnVO.setNoteRecptnId(sArrCheckListValue[2]);

					noteRecptnService.deleteNoteRecptn(noteRecptnVO);
				}
			}
			// 삭제후 페이지 인덱스 설정
			noteRecptnVO.getSearchVO().setPageIndex(1);
		}

		// 수신자설정
		noteRecptnVO.setRcverId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		noteRecptnVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", noteRecptnService.selectNoteRecptnList(noteRecptnVO));
		int totCnt = (Integer) noteRecptnService.selectNoteRecptnListCnt(noteRecptnVO);

		noteRecptnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/ntr/NoteRecptnList");
	}

	/**
	 * 받은쪽지함관리 목록을 상세조회 조회한다.
	 * 
	 * @param cmd
	 * @param noteRecptnVO
	 */
	@RequestMapping(value = "/uss/ion/ntr/detailNoteRecptn.do")
	public String detailNoteRecptn(
			@ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO, 
			@RequestParam(value="cmd", required=false) String sCmd,
			ModelMap model) {

		if (sCmd.equals("del")) {
			noteRecptnService.deleteNoteRecptn(noteRecptnVO);
			return "redirect:/uss/ion/ntr/listNoteRecptn.do";
		} 

		// 로그인 객체 선언/아이디설정
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		noteRecptnVO.setFrstRegisterId(loginVO.getUniqId());
		noteRecptnVO.setLastUpdusrId(loginVO.getUniqId());

		model.addAttribute("noteRecptn", noteRecptnService.selectNoteRecptnDetail(noteRecptnVO));

		NoteTrnsmitVO noteTrnsmit = new NoteTrnsmitVO();
		noteTrnsmit.setNoteId(noteRecptnVO.getNoteId());

		model.addAttribute("resultRecptnEmp", noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmit));

		return WebUtil.adjustViewName("/uss/ion/ntr/NoteRecptnDetail");
	}

}
