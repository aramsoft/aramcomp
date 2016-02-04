package aramframework.com.uss.olp.cns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olp.cns.domain.CnsltManageVO;
import aramframework.com.uss.olp.cns.service.CnsltManageService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 상담내용을 처리하는 컨트롤러 클래스
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
public class CnsltManageController {

	@Autowired
	private CnsltManageService cnsltManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 상담정보 목록을 조회한다. (pageing)
	 * 
	 * @param cnsltManageVO
	 */
	@IncludedInfo(name = "상담관리", order = 5130, gid = 50)
	@RequestMapping(value = "/uss/olp/cns/listCnslt.do")
	public String listCnslt(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cnsltManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cnsltManageService.selectCnsltList(cnsltManageVO));

		int totCnt = cnsltManageService.selectCnsltListCnt(cnsltManageVO);
		cnsltManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 인증여부 체크
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltList");
	}

	/**
	 * 상담정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/detailCnslt.do")
	public String detailCnslt(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltDetail");
	}

	/**
	 * Q&A 조회수를 수정처리 한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/updateCnsltInqireCo.do")
	public String updateCnsltInqireCo(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		cnsltManageService.updateCnsltInqireCo(cnsltManageVO);

		return "forward:/uss/olp/cns/detailCnslt.do";
	}

	/**
	 * 로그인이나 실명확인 처리를 한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/LoginRealnmChoice.do")
	public String LoginRealnmChoice(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltLoginRealnmChoice");
	}

	/**
	 * 상담정보를 등록하기 위한 전 처리(인증체크)
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/registCnslt.do")
	public String registCnslt(
			@ModelAttribute CnsltManageVO cnsltManageVO)  {

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setWrterNm(loginVO.getName()); // 작성자명
			cnsltManageVO.setEmailAdres(loginVO.getEmail()); // email 주소
		}
		
		return WebUtil.adjustViewName("/uss/olp/cns/CnsltRegist");
	}

	/**
	 * 상담정보를 등록한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/insertCnslt.do")
	public String insertCnslt(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(cnsltManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/cns/CnsltRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		cnsltManageVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "CNSLT_"));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		} else {
			cnsltManageVO.setFrstRegisterId("USRCNFRM_00000000000"); // guest
		}

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		cnsltManageService.insertCnsltDtls(cnsltManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olp/cns/listCnslt.do");
	}

	/**
	 * 작성 비밀번호를 확인하기 위한 전 처리
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/CnsltPasswordPopup.do")
	public String CnsltPasswordConfirmView(
			@ModelAttribute CnsltManageVO cnsltManageVO) { 
		
		return WebUtil.adjustViewName("/uss/olp/cns/CnsltPasswordPopup");
	}

	/**
	 * 작성 비밀번호를 확인한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/CnsltPasswordConfirm.do")
	public String CnsltPasswordConfirm(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) 
	throws Exception {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		// 작성비밀번호가 비었을 경우
		if (writngPassword == null || "".equals(writngPassword) ) { 
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olp/cns/detailCnslt.do";
		}

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		int searchCnt = cnsltManageService.selectCnsltPasswordConfirmCnt(cnsltManageVO);

		if (searchCnt <= 0) { // 작성비밀번호가 틀린경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olp/cns/detailCnslt.do";
		}
		
		cnsltManageVO.setWritngPassword(writngPassword);
		// 상담정보를 수정할 수 있는 화면으로 이동.
		return "forward:/uss/olp/cns/editCnslt.do";
	}

	/**
	 * 상담정보를 수정하기 위한 전 처리(비밀번호 복호화)
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/editCnslt.do")
	public String editCnslt(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		cnsltManageVO.setWritngPassword(writngPassword);

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltEdit");
	}

	/**
	 * 상담정보를 수정처리한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/updateCnslt.do")
	public String updateCnslt(
			MultipartHttpServletRequest multiRequest,
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// Validation
		beanValidator.validate(cnsltManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/cns/CnsltEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = cnsltManageVO.getAtchFileId();
		cnsltManageVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "CNSLT_", atchFileId));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}
		
		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		cnsltManageService.updateCnsltDtls(cnsltManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olp/cns/listCnslt.do");
	}

	/**
	 * 삭제을 위해 작성 비밀번호를 확인한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/CnsltPasswordConfirmDel.do")
	public String CnsltPasswordConfirmDel(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) 
	throws Exception {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		if (writngPassword == null || writngPassword.equals("") ) { // 작성비밀번호가없는경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olp/cns/detailCnslt.do";
		}
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

		int searchCnt = cnsltManageService.selectCnsltPasswordConfirmCnt(cnsltManageVO);

		if (searchCnt <= 0) { // 작성비밀번호가 틀린경우
			model.addAttribute("passwordConfirmAt", "N"); 	
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olp/cns/detailCnslt.do";
		}
		
		// Q&A를 삭제
		return "forward:/uss/olp/cns/deleteCnslt.do";
	}

	/**
	 * 상담정보를 삭제처리한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cns/deleteCnslt.do")
	public String deleteCnslt(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) {

		cnsltManageService.deleteCnsltDtls(cnsltManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olp/cns/listCnslt.do");
	}

	/**
	 * 상담답변정보 목록을 조회한다. (pageing)
	 * 
	 * @param cnsltManageVO
	 */
	@IncludedInfo(name = "상담답변관리", order = 5131, gid = 50)
	@RequestMapping(value = "/uss/olp/cnm/listCnsltAnswer.do")
	@Secured("ROLE_USER")
	public String listCnsltAnswer(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cnsltManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cnsltManageService.selectCnsltAnswerList(cnsltManageVO));

		int totCnt = cnsltManageService.selectCnsltAnswerListCnt(cnsltManageVO);
		cnsltManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltAnswerList");
	}

	/**
	 * 상담답변정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cnm/detailCnsltAnswer.do")
	@Secured("ROLE_USER")
	public String detailCnsltAnswer(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltAnswerDetail");
	}

	/**
	 * 상담답변정보를 수정하기 위한 전 처리(공통코드처리)
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cnm/editCnsltAnswer.do")
	@Secured("ROLE_USER")
	public String editCnsltAnswer(
			@ModelAttribute CnsltManageVO cnsltManageVO) {

		cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM028", "COM028_qnaProcessSttus");

		return WebUtil.adjustViewName("/uss/olp/cns/CnsltAnswerEdit");
	}

	/**
	 * 상담답변정보를 수정처리한다.
	 * 
	 * @param cnsltManageVO
	 */
	@RequestMapping("/uss/olp/cnm/updateCnsltAnswer.do")
	@Secured("ROLE_USER")
	public String updateCnsltAnswer(
			@ModelAttribute CnsltManageVO cnsltManageVO, 
			ModelMap model) {

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cnsltManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		cnsltManageService.updateCnsltDtlsAnswer(cnsltManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olp/cnm/listCnsltAnswer.do");
	}

}
