package aramframework.com.uss.umt.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.grp.service.GroupAuthorService;
import aramframework.com.sec.grp.service.GroupAuthorVO;
import aramframework.com.uss.umt.service.MberManageService;
import aramframework.com.uss.umt.service.MberManageVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일반회원관련 요청을 비지니스 클래스로 전달하고
 * 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
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
public class MberManageController {

	@Autowired
	private MberManageService mberManageService;

	@Autowired
	private GroupAuthorService groupAuthorService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** Log Info */
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 일반회원목록을 조회한다. (pageing)
	 * 
	 * @param mberManageVO
	 */
	@IncludedInfo(name = "일반회원관리", order = 5010, gid = 50)
	@RequestMapping(value = "/uss/umt/listMber.do")
	@Secured("ROLE_ADMIN")
	public String listMber(
			@ModelAttribute MberManageVO mberManageVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		mberManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", mberManageService.selectMberList(mberManageVO));

		int totCnt = mberManageService.selectMberListCnt(mberManageVO);
		mberManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 일반회원 상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		return WebUtil.adjustViewName("/uss/umt/MberList");
	}

	/**
	 * 일반회원 약관확인
	 * 
	 */
	@RequestMapping("/uss/umt/stplatMberView.do")
	@Secured("ROLE_ANONYMOUS")
	public String stplatMberView(
			@RequestParam(value="targetUrl", required=false) String targetUrl,
			ModelMap model) {

		log.debug("targetUrl = " + targetUrl);
		model.addAttribute("targetUrl", targetUrl);
		
		// 일반회원용 약관 아이디 설정
		String stplatId = "STPLAT_0000000000001";
		// 회원가입유형 설정-일반회원
		String sbscrbTy = "GNR";
		// 약관정보 조회
		model.addAttribute("stplatVO", mberManageService.selectStplat(stplatId)); // 약관정보 포함
		model.addAttribute("sbscrbTy", sbscrbTy); // 회원가입유형 포함

		return WebUtil.adjustViewName("/uss/umt/StplatCnfirm");
	}

	/**
	 * 일반회원가입신청 등록화면으로 이동한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/sbscrbMberView.do")
	@Secured("ROLE_ANONYMOUS")
	public String sbscrbMberView(
			HttpServletRequest request, 
			@ModelAttribute MberManageVO mberManageVO,
			ModelMap model) {
		
		if (!"".equals(request.getParameter("realname"))) {
			model.addAttribute("mberNm", request.getParameter("realname")); 
			// 실명인증된 이름 - 주민번호 인증
			model.addAttribute("ihidnum", request.getParameter("ihidnum")); 
			// 실명인증된 주민등록번호 - 주민번호 인증
		}
		if (!"".equals(request.getParameter("realName"))) {
			model.addAttribute("mberNm", request.getParameter("realName")); 
			// 실명인증된 이름 - ipin인증
		}

		fill_common_code();

		mberManageVO.setMberSttus("A");

		return WebUtil.adjustViewName("/uss/umt/MberSbscrb");
	}

	/**
	 * 일반회원가입신청등록처리후로그인화면으로 이동한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/sbscrbMber.do")
	@Secured("ROLE_ANONYMOUS")
	public String sbscrbMber(
			@ModelAttribute MberManageVO mberManageVO,
			ModelMap model) {

		// 가입상태 초기화
//		mberManageVO.setMberSttus("A");		// 가입 신청
		mberManageVO.setMberSttus("P");     // 가입 승인
		
		// 일반회원가입신청 등록시 일반회원등록기능을 사용하여 등록한다.
		mberManageService.insertMber(mberManageVO);

		GroupAuthorVO groupAuthorVO = new GroupAuthorVO();
		groupAuthorVO.setUniqId(mberManageVO.getUniqId());
		groupAuthorVO.setAuthorCode("ROLE_USER");
		groupAuthorVO.setMberTyCode("USR01");// 2011.08.04 수정 부분
		groupAuthorService.insertGroupAuthor(groupAuthorVO);

		String message = MessageHelper.getMessage("success.common.insert")
					   + "\n\n로그인 후 사용하시기 바랍니다.";
		model.addAttribute("message", message);
		
		return WebUtil.adjustViewName("/uss/umt/SbscrbSuccess");
	}

	/**
	 * 일반회원등록화면으로 이동한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/registMber.do")
	@Secured("ROLE_ADMIN")
	public String registMber(
			@ModelAttribute MberManageVO mberManageVO) {

		fill_common_code();

		return WebUtil.adjustViewName("/uss/umt/MberRegist");
	}

	/**
	 * 일반회원등록처리후 목록화면으로 이동한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/insertMber.do")
	@Secured("ROLE_ADMIN")
	public String insertMber(
			@ModelAttribute MberManageVO mberManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mberManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/umt/MberRegist");
		} 
		
		mberManageService.insertMber(mberManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/umt/listMber.do");
	}

	/**
	 * 일반회원정보 수정을 위해 일반회원정보를 상세조회한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/editMber.do")
	@Secured("ROLE_USER")
	public String editMber(
			@ModelAttribute MberManageVO mberManageVO, 
			ModelMap model) {

		fill_common_code();
		
		mberManageService.selectMber(mberManageVO);

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/uss/umt/MberEdit");
	}

	/**
	 * 일반회원정보 수정후 상세조회 화면으로 이동한다.
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping("/uss/umt/updateMber.do")
	@Secured("ROLE_USER")
	public String updateMber(
			@ModelAttribute MberManageVO mberManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mberManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/umt/MberEdit");
		} 
		
		mberManageService.updateMber(mberManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/umt/editMber.do?uniqId="+mberManageVO.getUniqId());
	}

	/**
	 * 일반회원 암호 수정 화면 이동
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping(value = "/uss/umt/editMberPassword.do")
	@Secured("ROLE_USER")
	public String editMberPassword(
			@ModelAttribute MberManageVO mberManageVO, 
			ModelMap model) {
		
		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/uss/umt/MberPassword");
	}

	/**
	 * 일반회원 암호 수정처리 후 상세조회화면 이동
	 * 
	 * @param mberManageVO
	 */
	@RequestMapping(value = "/uss/umt/updateMberPassword.do")
	@Secured("ROLE_USER")
	public String updateMberPassword(
			HttpServletRequest request, 
			@ModelAttribute MberManageVO mberManageVO, 
			ModelMap model) 
	throws Exception {
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		String uniqId = request.getParameter("uniqId");

		boolean isCorrectPassword = false;
		String message = "";

		MberManageVO resultVO = mberManageService.selectPassword(uniqId);
		// 패스워드 암호화
		String encryptPass = FileScrty.encryptPassword(oldPassword);
		if (encryptPass.equals(resultVO.getPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				message = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			message = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			mberManageVO.setPassword(FileScrty.encryptPassword(newPassword));
			mberManageService.updatePassword(mberManageVO);
			message = "success.common.update";
		} 
		
		model.addAttribute("message", MessageHelper.getMessage(message));
	    return WebUtil.redirectJsp(model, "/uss/umt/editMber.do?uniqId="+mberManageVO.getUniqId());
	}

	/**
	 * 일반회원정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/deleteMber.do")
	@Secured("ROLE_ADMIN")
	public String deleteMber(
			@RequestParam String checkedIdForDel, 
			@ModelAttribute MberManageVO mberManageVO, 
			ModelMap model) {

		mberManageService.deleteMber(checkedIdForDel);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/umt/listMber.do");
	}

	/**
	 * 조회 및 등록화면에서 보여줄 코드리스트를 가져온다..
	 */
	private void fill_common_code() {

		// 패스워드힌트목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM022", "COM022_passwordHint");

		// 성별구분코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");

		// 사용자상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

	}

}	