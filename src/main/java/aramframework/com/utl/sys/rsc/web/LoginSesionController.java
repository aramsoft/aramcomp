package aramframework.com.utl.sys.rsc.web;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.utl.sys.rsc.service.LoginSesionCeckUtil;

/**
 * 개요 - 로그인 세션정보체크 컴포넌트에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 로그인 세션정보체크에 대한 기능을 제공한다.
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
public class LoginSesionController {

	@Resource(name = "egovLoginSesionCeckUtil")
	private LoginSesionCeckUtil egovLoginSesionCeckUtil;

	/**
	 * 로그인 세션정보체크 화면 이동
	 * 
	 */
	@IncludedInfo(name = "로그인세션정보체크", order = 9010, gid = 90)
	@RequestMapping(value = "/utl/sys/rsc/loginSessionView.do")
	@Secured("ROLE_ADMIN")
	public String checkLoginSessionView() {
		return "aramframework/com/utl/sys/rsc/LoginSesionCheck";
	}

	/**
	 * 로그인 후 이동할 처리화면을 세션에 등록한다.
	 * 
	 * @param url
	 */
	@RequestMapping(value = "/utl/sys/rsc/setLoginSession.do")
	@Secured("ROLE_ADMIN")
	public String setLoginSession(@RequestParam("url") String url) {
		egovLoginSesionCeckUtil.setLoginSession(url);
		return "forward:/utl/sys/rsc/loginSessionView.do";
	}

	/**
	 * 로그인 세션정보체크
	 * 
	 */
	@RequestMapping(value = "/utl/sys/rsc/checkLloginSession.do")
	@Secured("ROLE_ADMIN")
	public String checkLoginSession() {
		egovLoginSesionCeckUtil.checkLoginSessionView();
		return "aramframework/com/utl/sys/rsc/LoginSesionCheck";
	}

}