package aramframework.com.uat.sso.service.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

//import com.ksign.access.api.SSORspData;
//import com.ksign.access.api.SSOService;

import aramframework.com.cmm.LoginVO;
import aramframework.com.uat.sso.service.SSOService;
import aramframework.com.uat.uia.service.LoginService;

/**
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
@Service(value="egovSSOService")
public class KignSSOServiceImpl implements SSOService {
	
	/** loginService */
	@Resource(name = "loginService")
    private LoginService loginService;
	
//	private SSOService ssoService = SSOService.getInstance();

	/**
	 * SSO 통합 인증 서버에 인증여부를 확인 하는 메서드
	 * 
	 */
	public boolean hasTokenInSSOServer(
			ServletRequest request,
			ServletResponse response) 
	{
//		SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
//		String uid = rspData.getUID();
		String uid = "";
		
		if(uid == null || uid.equals("")){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * SSO 통합 인증 서버에 인증 토큰 생성을 요청하는 메서드
	 * 
	 */
	public void requestIssueToken(ServletRequest request, ServletResponse response) throws Exception {

/*		
    	String serverIp = InetAddress.getLocalHost().getHostAddress();
    	String userIp = ClntInfo.getClntIP((HttpServletRequest)request);
    	String clientPort = ":" + request.getServerPort();	
    	String rtrURL = ((HttpServletRequest)request).getRequestURI();
    	
//     	LoginVO loginVO = (LoginVO)((HttpServletRequest)request).getSession().getAttribute("loginVO");	

		ssoService.ssoReqIssueToken((HttpServletRequest)request, // 서블릿 요청 객체
			    (HttpServletResponse)response,		// 서블릿 응답 객체
                "form-based",						// 인증 방법
                loginVO.getUniqId(),				// 유니크아이디
                loginVO.getUserSe(),				// 아이디 식별정보
                "",									// SSN
                "http://" + serverIp + clientPort + rtrURL, // return url
                userIp,								// client ip
                serverIp);							// agent ip
*/
	}

	/**
	 * SSO 통합 인증 서버에 인증이 된 경우 인증 서버의 토큰을 활용하여 로컬 로그인을 처리하는 메서드
	 * 
	 */
	public void ssoLoginByServer(
			ServletRequest request,
			ServletResponse response) 
	throws Exception {
		
//		SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
		
		LoginVO loginVO = getLoginVO(request, response);
	
		//로컬 로그인 작성
		loginVO = loginService.actionLoginByEsntlId(loginVO);
		
		//((HttpServletRequest)request).getSession().setAttribute("uid", rspData.getUID());
		
		//스프링 시큐리티 로그인
		((HttpServletResponse)response).sendRedirect("/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());
		//((HttpServletRequest)request).getRequestDispatcher("/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId()).forward(request, response);
	}
	
	
	/**
	 * 토큰 정보를 바탕으로  loginVO 객체를 생성하는 메서드
	 * 
	 */
	public LoginVO getLoginVO(ServletRequest request, ServletResponse response){

//		SSORspData rspData = ssoService.ssoGetLoginData((HttpServletRequest)request);
		
		LoginVO loginVO = new LoginVO();
//		loginVO.setUniqId(rspData.getUID());
//		loginVO.setUserSe(rspData.getCN());
		
		return  loginVO;
	}
	
	/**
	 * SSO 인증 정보를 삭제하는 Global Logout을 처리한다.
	 * returnURL : Global Logout후 돌아가는 URL주소
	 * @throws IOException 
	 * 
	 */
	public void ssoLogout(ServletRequest request, ServletResponse response, String returnURL) throws IOException{
		((HttpServletResponse)response).sendRedirect("/exam/sso/globalLogout.do?returnURL=" + returnURL);
	}
	
}
