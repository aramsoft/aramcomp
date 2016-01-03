package aramframework.com.uat.sso.web;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

@Controller
public class KsignSSOController {

	@RequestMapping("/exam/sso/globalLogout.do")
	public void globalLogout(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("returnURL") String returnURL) 
	throws Exception{
		
    	String serverIp = InetAddress.getLocalHost().getHostAddress();
    	String clientPort = ":" + request.getServerPort();	
    	
    	response.sendRedirect("http://192.168.100.222:7070/egovsso/pmi-logout-url.html?pmi-logout-url=http://192.168.100.222:7070/egovsso/pmi-logout.html&returl=" + "http://" + serverIp + clientPort + returnURL);
	}
	
	@RequestMapping("/exam/sso/loginPostProcess.do")
	public void loginPostProcess(
			@RequestParam("returnurl")String returnUrl, 
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception{
		response.sendRedirect(returnUrl);
	}
}
