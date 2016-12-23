package aramframework.com.uss.ion.ntm.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ntm.domain.NoteManageVO;
import aramframework.com.uss.ion.ntm.service.NoteManageService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 쪽지 관리(보내기)를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NoteManageController {

	@Autowired
	private NoteManageService noteManageService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 쪽지 관리(보내기) 목록을 조회한다.
	 * 
	 * @param commandMap
	 * @param noteManageVO
	 */
	@IncludedInfo(name = "쪽지관리", order = 5250, gid = 50)
	@RequestMapping(value = "/uss/ion/ntm/registNote.do")
	@Secured("ROLE_USER")
	public String registNote(
			@CommandMap Map<String, Object> commandMap,
			@ModelAttribute NoteManageVO noteManageVO, 
			ModelMap model) {

		// 변수 설정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		// 삭제 모드로 실행시
		// 답변처리
		if (sCmd.equals("reply")) {
			model.addAttribute("cmd", sCmd);

			EgovMap mapNoteManage = noteManageService.selectNoteManage(noteManageVO);

			noteManageVO.setNoteSj("RE : " + (String) mapNoteManage.get("noteSj"));
			/*
			 * HTML EDIT 용 
			 * noteManage.setNoteCn(
			 * 		"<br><br><br><br><font color='green' size='2'>" +
			 * 		"[ 원 본 글 ]=================================================================<br>"
			 * 		+ "* 발 신 자 : " + (String)mapNoteManage.get("trnsmiterNm") + "(" + (String)mapNoteManage.get("trnsmiterNm") +")<br>" 
			 *      + "* 발신시각 : " + (String)mapNoteManage.get("trnsmiterPnttm") + "</font><br>" 
			 *      + (String)mapNoteManage.get("noteCn") 
			 * );
			 */
			noteManageVO.setNoteCn(
					"\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" 
				  + "[ 원 본 글 ]================================================================" + "\r\n" 
				  + "* 발 신 자 : " + (String) mapNoteManage.get("trnsmiterId") + "(" + (String) mapNoteManage.get("trnsmiterNm") + ")" + "\r\n"
				  + "* 발신시각 : " + (String) mapNoteManage.get("trnsmiterPnttm") + "\r\n" 
				  + (String) mapNoteManage.get("noteCn")
			);

			noteManageVO.setAtchFileId((String) mapNoteManage.get("atchFileId"));

			model.addAttribute("noteManageMap", mapNoteManage);
		} 

		return WebUtil.adjustViewName("/uss/ion/ntm/NoteManage");
	}

	/**
	 * 쪽지 관리(보내기) 목록을 조회한다.(POST형식)
	 * 
	 * @param multiRequest
	 * @param commandMap
	 * @param noteManageVO
	 */
	@RequestMapping(value = "/uss/ion/ntm/insertNote.do")
	public String insertNote(
			MultipartHttpServletRequest multiRequest, 
			@RequestParam String recptnEmpList,
			@RequestParam String recptnSeList,
			@ModelAttribute NoteManageVO noteManageVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(noteManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ntm/NoteManage");
		}

		// 첨부파일 관련 첨부파일ID 생성
		noteManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		noteManageVO.setFrstRegisterId(loginVO.getUniqId());

		// 쪽지등록
		noteManageService.insertNoteManage(noteManageVO, recptnEmpList, recptnSeList);

		model.addAttribute("message", "작성된 쪽지를 전송하였습니다!");
        return WebUtil.redirectJsp(model, "/uss/ion/nts/listNoteTrnsmit.do");
	}

	/**
	 * 쪽지 관리(보내기) 사용자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping(value = "/uss/ion/ntm/listNoteEmpPopup.do")
	public String listNoteEmpPopup(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		baseVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", noteManageService.selectNoteEmpList(baseVO));

		int totCnt = (Integer) noteManageService.selectNoteEmpListCnt(baseVO);
		baseVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/ntm/NoteEmpPopup");
	}

}
