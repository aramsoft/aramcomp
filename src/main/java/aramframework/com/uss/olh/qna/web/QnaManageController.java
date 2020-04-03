package aramframework.com.uss.olh.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olh.qna.domain.QnaManageVO;
import aramframework.com.uss.olh.qna.service.QnaManageService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * Q&A를 처리하는 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class QnaManageController {

	@Autowired
	private QnaManageService qnaManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * Q&A정보 목록을 조회한다. (pageing)
	 * 
	 * @param qnaManageVO
	 */
	@IncludedInfo(name = "Q&A관리", order = 5100, gid = 50)
	@RequestMapping(value = "/uss/olh/qna/listQna.do")
	public String listQna(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qnaManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qnaManageService.selectQnaList(qnaManageVO));
		int totCnt = qnaManageService.selectQnaListCnt(qnaManageVO);

		qnaManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 인증여부 체크
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}

		return "uss/olh/qna/QnaList";
	}

	/**
	 * Q&A정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/detailQna.do")
	public String detailQna(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO,
			ModelMap model) {

		model.addAttribute(qnaManageService.selectQnaListDetail(qnaManageVO));

		return "uss/olh/qna/QnaDetail";
	}

	/**
	 * Q&A 조회수를 수정처리한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/updateQnaInqireCo.do")
	public String updateQnaInqireCo(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		qnaManageService.updateQnaInqireCo(qnaManageVO);

		return "forward:/uss/olh/qna/detailQna.do";
	}

	/**
	 * 로그인/실명확인 처리
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/LoginRealnmChoice.do")
	public String LoginRealnmChoice(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {
		
		return "uss/olh/qna/QnaLoginRealnmChoice";
	}

	/**
	 * Q&A정보를 등록하기 위한 전 처리(인증체크)
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/registQna.do")
	public String registQna(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setWrterNm(loginVO.getName()); // 작성자명
			qnaManageVO.setEmailAdres(loginVO.getEmail()); // email 주소
		}
		
		return "uss/olh/qna/QnaRegist";
	}

	/**
	 * Q&A정보를 등록한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/insertQna.do")
	public String insertQnaCn(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(qnaManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "uss/olh/qna/QnaRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setFrstRegisterId(loginVO.getUserId()); // 최초등록자ID
		} else {
			qnaManageVO.setFrstRegisterId("USRCNFRM_00000000000"); // guest
		}
		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		qnaManageService.insertQnaCn(qnaManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, qnaManageVO, "/uss/olh/qna/listQna.do");
	}

	/**
	 * 작성 비밀번호를 확인하기 위한 전 처리
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/QnaPasswordPopup.do")
	public String QnaPasswordConfirmView(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {
		
		return "uss/olh/qna/QnaPasswordPopup";
	}

	/**
	 * 수정을 위해 작성 비밀번호를 확인한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/QnaPasswordConfirm.do")
	public String QnaPasswordConfirm(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) 
	throws Exception {

		String writngPassword = qnaManageVO.getWritngPassword();

		// 작성비밀번호가 비었을 경우
		if (writngPassword == null || "".equals(writngPassword) ) { 
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/detailQna.do";
		}

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);

		if (searchCnt <= 0) { // 작성 비밀번호가 틀린 경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/detailQna.do";
		}	
		
		qnaManageVO.setWritngPassword(writngPassword);
		// Q&A를 수정할 수 있는 화면으로 이동.
		return "forward:/uss/olh/qna/editQna.do";
	}

	/**
	 * Q&A정보를 수정하기 위한 전 처리(비밀번호 암호화)
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/editQna.do")
	public String editQna(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO,
			ModelMap model) {

		qnaManageVO = qnaManageService.selectQnaListDetail(qnaManageVO);
		
		// 작성비밀번호 임시 저장
		String writngPassword = qnaManageVO.getWritngPassword();
		qnaManageVO.setWritngPassword(writngPassword);

		model.addAttribute(qnaManageVO);

		return "uss/olh/qna/QnaEdit";
	}

	/**
	 * Q&A정보를 수정처리한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/updateQna.do")
	public String updateQna(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		// Validation
		beanValidator.validate(qnaManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "uss/olh/qna/QnaEdit";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setLastUpdusrId(loginVO.getUserId()); // 최종수정자ID
		}

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		qnaManageService.updateQnaCn(qnaManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, qnaManageVO, "/uss/olh/qna/listQna.do");
	}

	/**
	 * 삭제을 위해 작성 비밀번호를 확인한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/QnaPasswordConfirmDel.do")
	public String QnaPasswordConfirmDel(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) 
	throws Exception {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		if (writngPassword == null || writngPassword.equals("") ) { // 작성비밀번호가없는경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/detailQna.do";
		}
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);

		if (searchCnt <= 0) { // 작성비밀번호가 틀린경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/detailQna.do";
		}
		
		// Q&A를 삭제
		return "forward:/uss/olh/qna/deleteQna.do";
	}

	/**
	 * Q&A정보를 삭제처리한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qna/deleteQna.do")
	public String deleteQna(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		qnaManageService.deleteQnaCn(qnaManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, qnaManageVO, "/uss/olh/qna/listQna.do");
	}

	/**
	 * Q&A답변정보 목록을 조회한다. (pageing)
	 * 
	 * @param qnaManageVO
	 */
	@IncludedInfo(name = "Q&A답변관리", order = 5101, gid = 50)
	@RequestMapping(value = "/uss/olh/qnm/listQnaAnswer.do")
	@Secured("ROLE_USER")
	public String listQnaAnswer(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qnaManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qnaManageService.selectQnaAnswerList(qnaManageVO));
		int totCnt = qnaManageService.selectQnaAnswerListCnt(qnaManageVO);

		qnaManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "uss/olh/qna/QnaAnswerList";
	}

	/**
	 * Q&A답변정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qnm/detailQnaAnswer.do")
	@Secured("ROLE_USER")
	public String detailQnaAnswer(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute QnaManageVO qnaManageVO,
			ModelMap model) {

		model.addAttribute(qnaManageService.selectQnaAnswerDetail(qnaManageVO));

		return "uss/olh/qna/QnaAnswerDetail";
	}

	/**
	 * Q&A답변정보를 수정하기 위한 전 처리(공통코드 처리)
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qnm/editQnaAnswer.do")
	@Secured("ROLE_USER")
	public String editQnaAnswer(
			@ModelAttribute QnaManageVO qnaManageVO,
			ModelMap model) {

		model.addAttribute(qnaManageService.selectQnaAnswerDetail(qnaManageVO));

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM028", "COM028_qnaProcessSttus");

		return "uss/olh/qna/QnaAnswerEdit";
	}

	/**
	 * Q&A답변정보를 수정처리한다.
	 * 
	 * @param qnaManageVO
	 */
	@RequestMapping("/uss/olh/qnm/updateQnaAnswer.do")
	@Secured("ROLE_USER")
	public String updateQnaAnswer(
			@ModelAttribute QnaManageVO qnaManageVO, 
			ModelMap model) {

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qnaManageVO.setLastUpdusrId(loginVO.getUserId()); // 최종수정자ID

		qnaManageService.updateQnaCnAnswer(qnaManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, qnaManageVO, "/uss/olh/qnm/listQnaAnswer.do");
	}

}
