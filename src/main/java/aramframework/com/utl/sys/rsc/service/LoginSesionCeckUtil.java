package aramframework.com.utl.sys.rsc.service;

import org.springframework.stereotype.Component;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;

/**
 * 개요 - 로그인 세션정보체크 컴포넌트에 대한 util 클래스를 정의한다.
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

@Component("loginSesionCeckUtil")
public class LoginSesionCeckUtil {

	/**
	 * 로그인 후 이동할 처리화면을 세션에 등록한다.
	 * 
	 * @param url
	 */
	public void setLoginSession(String url) {
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginVO.setUrl("");
		loginVO.setUrl(url);
		new EgovUserDetails(loginVO.getId(), loginVO.getPassword(), true, loginVO);
	}

	/**
	 * 로그인 세션정보체크 화면 이동
	 * 
	 */
	public String checkLoginSessionView() {
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		return loginVO.getUrl();
	}

}
