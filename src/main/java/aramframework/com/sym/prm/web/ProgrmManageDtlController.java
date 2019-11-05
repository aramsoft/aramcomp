package aramframework.com.sym.prm.web;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.ems.domain.SndngMailVO;
import aramframework.com.cop.ems.service.SndngMailService;
import aramframework.com.sym.prm.domain.ProgrmManageDtlVO;
import aramframework.com.sym.prm.service.ProgrmManageDtlService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 프로그램목록 관리및 변경을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ProgrmManageDtlController {

	@Autowired
	private ProgrmManageDtlService progrmManageDtlService;

	@Autowired
	private SndngMailService sndngMailService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 프로그램변경요청목록 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@IncludedInfo(name = "프로그램변경요청관리", order = 6190, gid = 60)
	@RequestMapping(value = "/sym/prm/listProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String listProgramChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		progrmManageDtlVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", progrmManageDtlService.selectProgrmChangeRequstList(progrmManageDtlVO));
		int totCnt = progrmManageDtlService.selectProgrmChangeRequstListCnt(progrmManageDtlVO);

		progrmManageDtlVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstList");
	}

	/**
	 * 프로그램변경요청 화면을 호출한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	/* 프로그램변경요청등록 */
	@RequestMapping(value = "/sym/prm/registProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String registProgrmChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		progrmManageDtlVO.setRqestPersonId(loginVO.getId());
		
		return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstRegist");
	}

	/**
	 * 프로그램변경요청 을 등록한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	/* 프로그램변경요청등록 */
	@RequestMapping(value = "/sym/prm/insertProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String insertProgrmChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO,
			BindingResult bindingResult, 
			ModelMap model) {

		// beanValidator 처리
		beanValidator.validate(progrmManageDtlVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstRegist");
		}
		
		progrmManageDtlService.insertProgrmChangeRequst(progrmManageDtlVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, progrmManageDtlVO, "/sym/prm/listProgramChangeRequst.do");
	}

	/**
	 * 프로그램변경요청 수정화면을 호출한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/editProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String editProgramChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO,
			ModelMap model) {

		model.addAttribute(progrmManageDtlService.selectProgrmChangeRequst(progrmManageDtlVO));

		return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstEdit");
	}

	/**
	 * 프로그램변경 요청을 수정 한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/updateProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String updateProgrmChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// beanValidator 처리
		beanValidator.validate(progrmManageDtlVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (!progrmManageDtlVO.getRqestPersonId().equals(loginVO.getId())) {
			model.addAttribute("message", "수정이 실패하였습니다. 변경요청 수정은 변경요청자만 수정가능합니다.");
			return WebUtil.adjustViewName("/sym/prm/ProgramChangeRequstEdit");
		}
		
		progrmManageDtlService.updateProgrmChangeRequst(progrmManageDtlVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, progrmManageDtlVO, "/sym/prm/listProgramChangeRequst.do");
	}

	/**
	 * 프로그램변경 요청을 삭제 한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/deleteProgramChangeRequst.do")
	@Secured("ROLE_USER")
	public String deleteProgrmChangeRequst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		// progrmManageDtlVO.setRqesterPersonId(user.getId());
		progrmManageDtlService.deleteProgrmChangeRequst(progrmManageDtlVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, progrmManageDtlVO, "/sym/prm/listProgramChangeRequst.do");
	}

////////////////////////////////////////////////////////////////////////////////
	/**
	 * 프로그램변경 요청에 대한 처리 사항을 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@IncludedInfo(name = "프로그램변경요청처리", order = 6191, gid = 60)
	@RequestMapping(value = "/sym/prm/listProgramChangeProcess.do")
	@Secured("ROLE_ADMIN")
	public String listProgramChangeProcess(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		progrmManageDtlVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", progrmManageDtlService.selectChangeRequstProcessList(progrmManageDtlVO));
		int totCnt = progrmManageDtlService.selectChangeRequstProcessListCnt(progrmManageDtlVO);

		progrmManageDtlVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/prm/ProgramChangeProcessList");
	}

	/**
	 * 프로그램변경 요청에 대한 처리할 화면으로 이동한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/editProgramChangeProcess.do")
	@Secured("ROLE_ADMIN")
	public String editProgramChangeProcess(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO,
			ModelMap model) {
	
		progrmManageDtlVO = progrmManageDtlService.selectProgrmChangeRequst(progrmManageDtlVO);

		if (progrmManageDtlVO.getOpetrId() == null) {
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			progrmManageDtlVO.setOpetrId(loginVO.getId());
		}

		model.addAttribute(progrmManageDtlVO);
		
		return WebUtil.adjustViewName("/sym/prm/ProgramChangeProcessEdit");
	}

	/**
	 * 프로그램변경요청처리 내용을 수정 한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/updateProgramChangeProcess.do")
	@Secured("ROLE_ADMIN")
	public String updateProgramChangeProcess(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(progrmManageDtlVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/prm/ProgramChangeProcessEdit");
		}

		progrmManageDtlService.updateProgrmChangeRequstProcess(progrmManageDtlVO);

		boolean eMailResult = false;
		
		if( eMailResult ) {
			String tmpEmail = progrmManageDtlService.selectRqesterEmail(progrmManageDtlVO);
			String sTemp = null;
			if (progrmManageDtlVO.getProcessSttus().equals("A")) {
				sTemp = "신청중";
			} else if (progrmManageDtlVO.getProcessSttus().equals("P")) {
				sTemp = "진행중";
			} else if (progrmManageDtlVO.getProcessSttus().equals("R")) {
				sTemp = "반려";
			} else if (progrmManageDtlVO.getProcessSttus().equals("C")) {
				sTemp = "처리완료";
			}
			
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			// 프로그램 변경요청 사항을 이메일로 발송한다.(메일연동솔루션 활용)
			SndngMailVO sndngMailVO = new SndngMailVO();
			sndngMailVO.setDsptchPerson(loginVO.getId());
			sndngMailVO.setRecptnPerson(tmpEmail);
			sndngMailVO.setSj("프로그램변경요청  처리.");
			sndngMailVO.setEmailCn("프로그램 변경요청 사항이  " + sTemp + "(으)로 처리 되었습니다.");
			sndngMailVO.setAtchFileId(null);
			sndngMailService.insertSndngMail(sndngMailVO);
		}
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, progrmManageDtlVO, "/sym/prm/listProgramChangeProcess.do");
	}

	/**
	 * 프로그램변경요청처리를 삭제 한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	/* 프로그램변경요청처리 삭제 */
	@RequestMapping(value = "/sym/prm/deleteProgramChangeProcess.do")
	@Secured("ROLE_ADMIN")
	public String deleteProgramChangeProcess(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		progrmManageDtlService.deleteProgrmChangeRequst(progrmManageDtlVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, progrmManageDtlVO, "/sym/prm/listProgramChangeProcess.do");
	}

	/**
	 * 프로그램변경이력리스트를 조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@IncludedInfo(name = "프로그램변경이력", order = 6192, gid = 60)
	@RequestMapping(value = "/sym/prm/listProgramChgHst.do")
	@Secured("ROLE_USER")
	public String listProgramChgHst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		progrmManageDtlVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", progrmManageDtlService.selectProgrmChangeHistoryList(progrmManageDtlVO));
		int totCnt = progrmManageDtlService.selectProgrmChangeHistoryListCnt(progrmManageDtlVO);

		progrmManageDtlVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/prm/ProgramChgHstList");
	}

	/* 프로그램변경이력상세조회 */
	/**
	 * 프로그램변경이력을 상세조회한다.
	 * 
	 * @param progrmManageDtlVO
	 */
	@RequestMapping(value = "/sym/prm/detailProgramChgHst.do")
	@Secured("ROLE_USER")
	public String detailProgramChgHst(
			@ModelAttribute ProgrmManageDtlVO progrmManageDtlVO,
			ModelMap model) {

		model.addAttribute(progrmManageDtlService.selectProgrmChangeRequst(progrmManageDtlVO));
		
		return WebUtil.adjustViewName("/sym/prm/ProgramChgHstDetail");
	}

}