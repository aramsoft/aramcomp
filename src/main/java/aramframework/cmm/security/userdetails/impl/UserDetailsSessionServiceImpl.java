package aramframework.cmm.security.userdetails.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import aramframework.cmm.security.userdetails.UserDetailsService;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  세션을 통해 UserDetail 정보를 가져오기 위한 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class UserDetailsSessionServiceImpl extends EgovAbstractServiceImpl implements UserDetailsService {

	public Object getAuthenticatedUser() {
		return RequestContextHolder.getRequestAttributes().getAttribute("loginVO", RequestAttributes.SCOPE_SESSION);
	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.
		List<String> listAuth = new ArrayList<String>();

		return listAuth;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {
			if (RequestContextHolder.getRequestAttributes().getAttribute("loginVO", RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
