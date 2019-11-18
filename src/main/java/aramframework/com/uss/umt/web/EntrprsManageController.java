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
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.grp.domain.GroupAuthorVO;
import aramframework.com.sec.grp.service.GroupAuthorService;
import aramframework.com.uss.umt.domain.EntrprsManageVO;
import aramframework.com.uss.umt.service.EntrprsManageService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 기업회원관련 요청을 비지니스 클래스로 전달하고 
 * 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class EntrprsManageController {

	@Autowired
	private EntrprsManageService entrprsManageService;

	@Autowired
	private GroupAuthorService groupAuthorService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** Log Info */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 기업회원목록을 조회한다. (pageing)
	 * 
	 * @param entrprsManageVO
	 */
	@IncludedInfo(name = "기업회원관리", order = 5020, gid = 50)
	@RequestMapping(value = "/uss/umt/listEntrprsMber.do")
	@Secured("ROLE_ADMIN")
	public String listEntrprsMber(
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		entrprsManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", entrprsManageService.selectEntrprsMberList(entrprsManageVO));
		int totCnt = entrprsManageService.selectEntrprsMberListCnt(entrprsManageVO);

		entrprsManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 기업회원상태코드목록
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		return WebUtil.adjustViewName("/uss/umt/EntrprsMberList");
	}

	/**
	 * 기업회원 약관확인 화면을 조회한다.
	 * 
	 * @param targetUrl
	 */
	@RequestMapping("/uss/umt/stplatEntrprsMberView.do")
	@Secured("ROLE_ANONYMOUS")
	public String stplatEntrprsMberView(
			@RequestParam(value="targetUrl", required=false) String targetUrl,
			ModelMap model) {

		log.debug("targetUrl = " + targetUrl);
		model.addAttribute("targetUrl", targetUrl);
		
		// 기업회원용 약관 아이디 설정
		String stplatId = "STPLAT_0000000000002";
		// 회원가입유형 설정-기업회원
		String sbscrbTy = "ENT";
		// 약관정보 조회
		model.addAttribute("stplatVO", entrprsManageService.selectStplat(stplatId)); // 약관정보포함
		model.addAttribute("sbscrbTy", sbscrbTy); // 회원가입유형포함

		return WebUtil.adjustViewName("/uss/umt/StplatCnfirm");
	}

	/**
	 * 기업회원가입신청 등록화면으로 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/sbscrbEntrprsMberView.do")
	@Secured("ROLE_ANONYMOUS")
	public String sbscrbEntrprsMberView(
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			HttpServletRequest request, 
			ModelMap model) {
		
		if (!"".equals(request.getParameter("realname"))) {
			model.addAttribute("applcntNm", request.getParameter("realname")); 
			// 실명인증된 이름 - 주민번호인증
			model.addAttribute("applcntIhidnum", request.getParameter("ihidnum")); 
			// 실명인증된 주민등록번호 - 주민번호 인증
		}
		if (!"".equals(request.getParameter("realName"))) {
			model.addAttribute("applcntNm", request.getParameter("realName")); 
			// 실명인증된 이름 - ipin인증
		}

		fill_common_code();

		entrprsManageVO.setEntrprsMberSttus("A");

		return WebUtil.adjustViewName("/uss/umt/EntrprsMberSbscrb");
	}

	/**
	 * 기업회원가입신청 등록처리후 로그인화면으로 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/sbscrbEntrprsMber.do")
	@Secured("ROLE_ANONYMOUS")
	public String sbscrbEntrprsMber(
			@ModelAttribute EntrprsManageVO entrprsManageVO,
			ModelMap model) {

		// 가입상태 초기화
		entrprsManageVO.setEntrprsMberSttus("A");

		// 기업회원가입신청 등록시 기업회원등록기능을 사용하여 등록한다.
		entrprsManageService.insertEntrprsMber(entrprsManageVO);

		GroupAuthorVO groupAuthorVO = new GroupAuthorVO();
		groupAuthorVO.setUniqId(entrprsManageVO.getUniqId());
		groupAuthorVO.setAuthorCode("ROLE_ANONYMOUS");
		groupAuthorVO.setMberTyCode("USR02");// 2011.08.04 수정 부분
		groupAuthorService.insertGroupAuthor(groupAuthorVO);
		
		String message = MessageHelper.getMessage("success.common.insert")
		   			   + "\n\n사용자 승인 후 사용하시기 바랍니다.";
		model.addAttribute("message", message);
		
		return WebUtil.adjustViewName("/uss/umt/SbscrbSuccess");
	}

	/**
	 * 기업회원 등록화면으로 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/registEntrprsMber.do")
	@Secured("ROLE_ADMIN")
	public String registEntrprsMber(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO,
			ModelMap model) {

		fill_common_code();
		
		return WebUtil.adjustViewName("/uss/umt/EntrprsMberRegist");
	}

	/**
	 * 기업회원등록처리후 목록화면으로 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/insertEntrprsMber.do")
	@Secured("ROLE_ADMIN")
	public String insertEntrprsMber(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(entrprsManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/umt/EntrprsMberRegist");
		} 
		
		entrprsManageService.insertEntrprsMber(entrprsManageVO);

		model.addAttribute("resultMsg", "success.common.insert");
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, entrprsManageVO, "/uss/umt/listEntrprsMber.do");
	}

	/**
	 * 기업회원정보 수정을 위해기업회원정보를 상세조회한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/editEntrprsMber.do")
	@Secured("ROLE_USER")
	public String editEntrprsMber(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			ModelMap model) {

		fill_common_code();
		
		model.addAttribute(entrprsManageService.selectEntrprsMber(entrprsManageVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		
		return WebUtil.adjustViewName("/uss/umt/EntrprsMberEdit");
	}

	/**
	 * 기업회원정보 수정후 상세조회 화면으로 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/updateEntrprsMber.do.do")
	@Secured("ROLE_USER")
	public String updateEntrprsMber(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(entrprsManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/umt/EntrprsMberEdit");
		} 

		entrprsManageService.updateEntrprsMber(entrprsManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, entrprsManageVO, "/uss/umt/editEntrprsMber.do?uniqId="+entrprsManageVO.getUniqId());
	}

	/**
	 * 기업회원암호 수정 화면 이동
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping(value = "/uss/umt/editEntrprsMberPassword.do")
	@Secured("ROLE_USER")
	public String editEntrprsMberPassword(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO,
			ModelMap model) {

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/uss/umt/EntrprsPassword");
	}

	/**
	 * 기업회원 암호 수정처리 후 상세조회화면 이동한다.
	 * 
	 * @param entrprsManageVO
	 */
	@RequestMapping(value = "/uss/umt/updateEntrprsMberPassword.do")
	@Secured("ROLE_USER")
	public String updateEntrprsMberPassword(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		String uniqId = request.getParameter("uniqId");

		boolean isCorrectPassword = false;
		String message = "";

		EntrprsManageVO resultVO = entrprsManageService.selectPassword(uniqId);
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
			entrprsManageVO.setPassword(FileScrty.encryptPassword(newPassword));
			entrprsManageService.updatePassword(entrprsManageVO);
			message = "success.common.update";
		} 
		
		model.addAttribute("message", MessageHelper.getMessage(message));
	    return WebUtil.redirectJsp(model, entrprsManageVO, "/uss/umt/editEntrprsMber.do?uniqId="+entrprsManageVO.getUniqId());
	}

	/**
	 * 기업회원정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel
	 * @param entrprsManageVO
	 */
	@RequestMapping("/uss/umt/deleteEntrprsMber.do")
	@Secured("ROLE_ADMIN")
	public String deleteEntrprsMber( 
			@ModelAttribute EntrprsManageVO entrprsManageVO, 
			ModelMap model) {

		entrprsManageService.deleteEntrprsMber(entrprsManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, entrprsManageVO, "/uss/umt/listEntrprsMber.do");
	}

	/**
	 * 조회 및 등록화면에서 보여줄 코드리스트를 가져온다..
	 * 
	 * @param model
	 *            화면모델
	 */
	private void fill_common_code()  {

		// 패스워드힌트목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM022", "COM022_passwordHint");

		// 사용자상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		// 기업구분코드를 코드정보로부터 조회 - COM026
		cmmUseService.populateCmmCodeList("COM026", "COM026_entrprsSe");

		// 업종코드를 코드정보로부터 조회 - COM027
		cmmUseService.populateCmmCodeList("COM027", "COM027_induty");
	}
	
}