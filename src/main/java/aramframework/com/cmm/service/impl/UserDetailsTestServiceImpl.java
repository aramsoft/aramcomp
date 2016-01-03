package aramframework.com.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.service.UserDetailsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  테스트용 User Detail Service 클래스
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

public class UserDetailsTestServiceImpl extends EgovAbstractServiceImpl implements UserDetailsService {

	public Object getAuthenticatedUser() {

		LoginVO loginVO = new LoginVO();
		loginVO.setId("TEST1");
		loginVO.setPassword("a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=");
		loginVO.setUserSe("USR");
		loginVO.setEmail("a3@aa.aa");
		loginVO.setName("더미사용자");
		loginVO.setOrgnztId("ORGNZT_0000000000000");
		loginVO.setUniqId("USRCNFRM_00000000000");
		return loginVO;

		// return RequestContextHolder.getRequestAttributes().getAttribute("loginVO", RequestAttributes.SCOPE_SESSION);
	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.

		List<String> listAuth = new ArrayList<String>();
		listAuth.add("IS_AUTHENTICATED_ANONYMOUSLY");
		listAuth.add("IS_AUTHENTICATED_FULLY");
		listAuth.add("IS_AUTHENTICATED_REMEMBERED");
		listAuth.add("ROLE_ADMIN");
		listAuth.add("ROLE_ANONYMOUS");
		listAuth.add("ROLE_RESTRICTED");
		listAuth.add("ROLE_USER");

		return listAuth;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		/*
		 * if (RequestContextHolder.getRequestAttributes() == null) { 
		 * 		return false; 
		 *  } else {
		 * 
		 * 		if (RequestContextHolder.getRequestAttributes().getAttribute(
		 * 			"loginVO", RequestAttributes.SCOPE_SESSION) == null) { 
		 * 			return false;
		 * 		} else { 
		 * 			return true; 
		 * 		} 
		 *  }
		 */

		return true;
	}

}