package aramframework.cmm.security.userdetails;

import java.util.List;

/**
 * UserDetails Helper 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class UserDetailsHelper {

	static UserDetailsService userDetailsService;

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		UserDetailsHelper.userDetailsService = userDetailsService;
	}

	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * 
	 * @return 	사용자 ValueObject
	 */
	public static Object getAuthenticatedUser() {
		return userDetailsService.getAuthenticatedUser();
	}

	/**
	 * 인증된 사용자의 권한 정보를 가져온다.
	 * 
	 * @return 	사용자 권한정보 목록
	 */
	public static List<String> getAuthorities() {
		return userDetailsService.getAuthorities();
	}

	/**
	 * 인증된 사용자 여부를 체크한다.
	 * 
	 * @return 인증된 사용자 여부(TRUE / FALSE)
	 */
	public static Boolean isAuthenticated() {
		return userDetailsService.isAuthenticated();
	}
	
}
