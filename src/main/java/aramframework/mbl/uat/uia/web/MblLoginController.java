package aramframework.mbl.uat.uia.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.constant.Globals;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uat.uia.service.LoginService;

/*
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
import com.gpki.io.GPKIJspWriter;
import com.gpki.secureweb.GPKIKeyInfo;
import com.dsjdf.jdf.*;

import com.gpki.gpkiapi.GpkiApi;
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.cms.SignedData;
import com.gpki.gpkiapi.crypto.PrivateKey;
import com.gpki.gpkiapi.crypto.Random;
import com.gpki.gpkiapi.storage.*;
import com.gpki.gpkiapi.util.*;
import com.gpki.gpkiapi.exception.GpkiApiException;
*/

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
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
public class MblLoginController {

	@Autowired
    private LoginService loginService;
	
	/** log */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
	/**
	 * 로그인 화면으로 들어간다
	 * 
	 * @param loginVO 
	 */
    @RequestMapping(value="/uat/uia/egovLoginUsr.mdo")
	public String egovLoginUsr(
			@ModelAttribute LoginVO loginVO) {
    	
    	return "aramframework/mbl/uat/uia/LoginUsr";
	}
	
	/**
	 * PC로그인 화면으로 들어간다
	 * 
	 * @param loginVO
	 */
    @RequestMapping(value="/uat/uia/egovLoginUsrPC.do")
	public String egovLoginUsrPC(
			@ModelAttribute LoginVO loginVO) {
    	
    	return "aramframework/uat/uia/LoginUsr";
	}
    
    /**
	 * 일반(세션) 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
    @RequestMapping(value="/uat/uia/actionLogin.mdo")
    public String actionLogin(
    		@ModelAttribute LoginVO loginVO, 
    		HttpServletRequest request,
    		ModelMap model) {
    	
		if( loginVO.getId() == null || loginVO.getId().equals("") ) {
			throw new RuntimeException("userId not found");
		}
		if( loginVO.getPassword() == null || loginVO.getPassword().equals("") ) {
			throw new RuntimeException("password not found");
		}

		// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);
        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
        	// 2-1. 로그인 정보를 세션에 저장
        	request.getSession().setAttribute("loginVO", resultVO);
    		return "redirect:/uat/uia/actionMain.mdo";
        } else {
        	model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
        	return "aramframework/mbl/uat/uia/LoginUsr";
        }
    }   
    
    /**
	 * 일반(스프링 시큐리티) 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
    @RequestMapping(value="/uat/uia/actionSecurityLogin.mdo")
    public String actionSecurityLogin(
    		@ModelAttribute LoginVO loginVO, 
    		HttpServletRequest request,
    		ModelMap model) {

    	// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);
        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
        	// 2. 스프링 시큐리티를 이용한 로그인처리
        	// 관련 설정 파일인 context-security.xml 파일이 있어야 하며, web.xml에 필터가 등록되어 있어야 한다.
        	return "redirect:/j_spring_security_check?j_username=" + resultVO.getUserSe() + resultVO.getId() + "&j_password=" + resultVO.getUniqId();
        } else {
           	model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
        	return "aramframework/mbl/uat/uia/LoginUsr";
        }
    } 
    
    /**
	 * 로그인 후 메인화면으로 들어간다
	 * 
	 */
    @RequestMapping(value="/uat/uia/actionMain.mdo")
	public String actionMain(
			HttpServletRequest request, 
			ModelMap model) {
    	
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
        	return "aramframework/mbl/uat/uia/LoginUsr";
    	}
    	
    	/*
    	// 2. 메뉴조회
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		MenuManageVO menuManageVO = new MenuManageVO();
    	menuManageVO.setTmp_Id(loginVO.getId());
    	menuManageVO.setTmp_UserSe(loginVO.getUserSe());
    	menuManageVO.setTmp_Name(loginVO.getName());
    	menuManageVO.setTmp_Email(loginVO.getEmail());
    	menuManageVO.setTmp_OrgnztId(loginVO.getOrgnztId());
    	menuManageVO.setTmp_UniqId(loginVO.getUniqId());
    	List list_headmenu = menuManageService.selectMainMenuHead(menuManageVO);
		model.addAttribute("list_headmenu", list_headmenu);
    	*/
    	
		// 3. 메인 페이지 이동
		String main_page = Globals.MAIN_PAGE;
		
		LOG.debug("Globals.MAIN_PAGE > " +  Globals.MAIN_PAGE);
		LOG.debug("main_page_Mobile > " +  main_page);
		
		if (main_page.startsWith("/")) {
			return "forward:" + main_page;
		} else {
		    return main_page;
		}
		
		/*
		if (main_page != null && !main_page.equals("")) {
			
			// 3-1. 설정된 메인화면이 있는 경우
			return main_page;
			
		} else {
			
			// 3-2. 설정된 메인화면이 없는 경우
			if (user.getUserSe().equals("USR")) {
	    		return "EgovMainView";
	    	} else {
	    		return "EgovMainViewG";
	    	}
		}
		*/
	}
    
    /**
	 * 로그인 후 메인화면으로 들어간다
	 * 
	 */
    @RequestMapping(value="/uat/uia/actionMainPC.mdo")
	public String actionMainPC(
			HttpServletRequest request, 
			ModelMap model) {
    	
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", MessageHelper.getMessage("fail.common.login"));
        	return "aramframework/mbl/uat/uia/LoginUsr";
    	}
    	
    	/*
    	// 2. 메뉴조회
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		MenuManageVO menuManageVO = new MenuManageVO();
    	menuManageVO.setTmp_Id(loginVO.getId());
    	menuManageVO.setTmp_UserSe(loginVO.getUserSe());
    	menuManageVO.setTmp_Name(loginVO.getName());
    	menuManageVO.setTmp_Email(loginVO.getEmail());
    	menuManageVO.setTmp_OrgnztId(loginVO.getOrgnztId());
    	menuManageVO.setTmp_UniqId(loginVO.getUniqId());
    	List list_headmenu = menuManageService.selectMainMenuHead(menuManageVO);
		model.addAttribute("list_headmenu", list_headmenu);
    	*/
    	
		// 3. 메인 페이지 이동
		String main_page = Globals.MAIN_PAGE;
		
		LOG.debug("Globals.MAIN_PAGE > " +  Globals.MAIN_PAGE);
		LOG.debug("main_page_Mobile > " +  main_page);
		
		// 4. 접속 기기에 따라서 모바일용/일반웹용 처음 페이지를 다르게 호출한다.
    	String userAgent = request.getHeader("user-agent");
    	// Window에서 접속할 경우 PC버젼 홈으로 이동한다.
    	if(userAgent.contains("Windows NT")){
    		LOG.debug("main_page_PC > " +  main_page);
    		//main_page = "/sym/mnu/mpm/MainMenuHome.do";
    		main_page = "/index.do";
    	}
    	
		if (main_page.startsWith("/")) {
			return "forward:" + main_page;
		} else {
		    return main_page;
		}
		
		/*
		if (main_page != null && !main_page.equals("")) {
			
			// 3-1. 설정된 메인화면이 있는 경우
			return main_page;
			
		} else {
			
			// 3-2. 설정된 메인화면이 없는 경우
			if (user.getUserSe().equals("USR")) {
	    		return "EgovMainView";
	    	} else {
	    		return "EgovMainViewG";
	    	}
		}
		*/
	}
    
    /**
	 * 로그아웃한다.
	 * 
	 */
    @RequestMapping(value="/mbl/com/uat/uia/actionLogout.do")
	public String actionLogout(
			HttpServletRequest request) {
    	
    	/*String userIp = EgovClntInfo.getClntIP(request);
    	
    	// 1. Security 연동
    	return "redirect:/j_spring_security_logout";*/
    	
    	request.getSession().setAttribute("loginVO", null);
    	return "redirect:" + request.getRequestURI();
    }
    
}