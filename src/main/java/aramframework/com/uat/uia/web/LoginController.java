package aramframework.com.uat.uia.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cop.ems.domain.SndngMailVO;
import aramframework.cmm.config.security.SimpleUrlAuthenticationSuccessHandler;
import aramframework.cmm.constant.Globals;
import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.com.service.CmmUseService;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uat.uia.service.LoginService;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private CmmUseService cmmUseService;

	/** EgovSndngMailService */
//	@Autowired
//	private SndngMailService sndngMailService;

	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;
	
    /** log */
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();

	/**
	 * 로그인 화면으로 들어간다
	 * 
	 * @param targetUrl
	 * @param loginVO
	 */ 
	@RequestMapping(value = "/uat/uia/loginUsr.do")
	public String loginUsrView(
			@ModelAttribute LoginVO loginVO, 
			@RequestParam(value="targetUrl", required=false) String targetUrl,
			HttpServletRequest request, 
			HttpServletResponse response,
			ModelMap model) {

		if (ComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		String requestUrl = null;
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if ( targetUrl != null ) {
    		model.addAttribute("targetUrl", targetUrl);
    		LOG.debug("targetUrl = " + targetUrl);
    		
        	requestUrl = targetUrl;
        } else if( savedRequest != null ) {
        	requestUrl = savedRequest.getRedirectUrl();
        	LOG.debug("savedRequestUrl = " + requestUrl);

        } else  {
        	requestUrl = request.getRequestURI();
        }
        
       	return "com/uat/uia/LoginUsr";
	}

	/**
	 * 일반(세션) 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	@RequestMapping(value = "/uat/uia/actionLogin.do")
	public String actionLogin(
			@ModelAttribute LoginVO loginVO, 
			ModelMap model) {

		LOG.debug("execute actionLogin !!!" );
		if( loginVO.getUserId() == null || loginVO.getUserId().equals("") ) {
			throw new RuntimeException("userId not found");
		}
		if( loginVO.getPassword() == null || loginVO.getPassword().equals("") ) {
			throw new RuntimeException("password not found");
		}
		
		// 1. 일반 로그인 처리
		LoginVO resultVO = loginService.actionLogin(loginVO);
		if (resultVO != null && resultVO.getUserId() != null && !resultVO.getUserId().equals("")) {
			return "forward:/uat/uia/actionMain.do";
		} else {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
			return "com/uat/uia/LoginUsr";
		}
	}

	/**
	 * 로그인 후 메인화면으로 들어간다
	 * 
	 */
	@RequestMapping(value = "/uat/uia/actionMain.do")
	public String actionMain(
			HttpServletRequest request, 
			HttpServletResponse response,
			ModelMap model) {

		LOG.debug("start action Main");
        // 1. Spring Security 사용자권한 처리
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
			return "com/uat/uia/LoginUsr";
		}
		
		String requestUrl = null;

		String targetUrlParameter = authenticationSuccessHandler.getTargetUrlParameter();
        if (targetUrlParameter != null  ) {
        	requestUrl = request.getParameter(targetUrlParameter);

            if (StringUtils.hasText(requestUrl)) {
            	LOG.debug("Found targetUrlParameter in request: " + requestUrl);
               	return "redirect:" + requestUrl;
            }
        }
        
		SavedRequest savedRequest = requestCache.getRequest(request, response);
        if( savedRequest != null ) {
        	requestUrl = savedRequest.getRedirectUrl();
            if (StringUtils.hasText(requestUrl)) {
            	LOG.debug("savedRequestUrl = " + requestUrl);
            	return "redirect:" + requestUrl;
            }
        } 

        return Globals.MAIN_PAGE;
	}

	/**
	 * 로그아웃한다.
	 * 
	 * @return targetUrl
	 */
	@RequestMapping(value = "/uat/uia/actionLogout.do")
	public String actionLogout(
			HttpServletRequest request, 
			@RequestParam(value="targetUrl", required=false) String targetUrl,
			ModelMap model) {
		
	   	request.getSession().setAttribute("loginVO", null);

	   	String logout_url = "redirect:/j_spring_security_logout"; 
	   	if( targetUrl != null ) {
	   		logout_url += "?targetUrl="+targetUrl;
	   	}
	   	return logout_url;
	}

	/**
	 * 로그아웃 성공 후 처리한다.
	 * 
	 */
	@RequestMapping(value = "/uat/uia/actionLogoutSuccess.do")
	public String actionLogoutSuccess() {
    	return "redirect:/index.jsp";
	}

	/**
	 * 아이디/비밀번호 찾기 화면으로 들어간다
	 * 
	 */
	@RequestMapping(value = "/uat/uia/searchIdPassword.do")
	public String searchIdPassword() {

		// 1. 비밀번호 힌트 공통코드 조회
		cmmUseService.populateCmmCodeList("COM022", "COM022_passwordHint");

		return "com/uat/uia/IdPasswordSearch";
	}

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param loginVO
	 */
	@RequestMapping(value = "/uat/uia/searchId.do")
	public String searchId(
			@ModelAttribute LoginVO loginVO, 
			ModelMap model) {

		// 1. 아이디 찾기
		loginVO.setName(loginVO.getName().replaceAll(" ", ""));
		LoginVO resultVO = loginService.searchId(loginVO);

		if (resultVO != null && resultVO.getUserId() != null && !resultVO.getUserId().equals("")) {
			model.addAttribute("resultInfo", "아이디는 " + resultVO.getUserId() + " 입니다.");
		} else {
			model.addAttribute("resultInfo", MessageHelper.getMessage("fail.common.idsearch"));
		}
		return "com/uat/uia/IdPasswordResult";
	}

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param loginVO
	 */
	@RequestMapping(value = "/uat/uia/searchPassword.do")
	public String searchPassword(
			@ModelAttribute LoginVO loginVO, 
			ModelMap model) {

		// 1. 비밀번호 찾기
		String newpasswd = loginService.searchPassword(loginVO);


		// 2. 결과 리턴
		if (!"".equals(newpasswd) ) {
			// 2. 임시 비밀번호를 이메일 발송한다.(메일연동솔루션 활용)
			SndngMailVO sndngMailVO = new SndngMailVO();
			sndngMailVO.setDsptchPerson("admin");
			sndngMailVO.setRecptnPerson(loginVO.getEmail());
			sndngMailVO.setSj("[ARAM] 임시 비밀번호를 발송했습니다.");
			sndngMailVO.setEmailCn("임시 비밀번호는 " + newpasswd + " 입니다. 로그인후 수정하여 주시기 바랍니다.");
			sndngMailVO.setAtchFileId("");
			// 메일 전송
//			sndngMailService.insertSndngMail(sndngMailVO);
//			sndngMailService.sndngMail(sndngMailVO);
//			model.addAttribute("resultInfo", "임시 비밀번호를 등록하신 메일로 발송하였습니다.");
			model.addAttribute("resultInfo", "임시 비밀번호는" + newpasswd + " 입니다. 로그인후 수정하여 주시기 바랍니다.");
		} else {
			model.addAttribute("resultInfo", MessageHelper.getMessage("fail.common.pwsearch"));
		}
		return "com/uat/uia/IdPasswordResult";
	}

	/**
	 * 활성화돤 사용자를 찾는다
	 * 
	 */
	@RequestMapping(value = "/uat/uia/listActiveUsers.do")
	@Secured("ROLE_ADMIN")
	public String listActiveUsers(ModelMap model) {
		Map<Object, Date> lastActivityDates = new HashMap<Object, Date>();
		for(Object principal: sessionRegistry.getAllPrincipals()) {
			for(SessionInformation session: sessionRegistry.getAllSessions(principal, true)) {
				lastActivityDates.put(principal, session.getLastRequest());
			}
		}
		model.addAttribute("activeUsers", lastActivityDates);
		return "com/uat/uia/ListActiveUsers";
	}
	
}