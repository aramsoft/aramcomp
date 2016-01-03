package aramframework.com.cmm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import aramframework.com.cmm.service.UserDetailsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import egovframework.rte.fdl.string.EgovObjectUtil;

public class UserDetailsSecurityServiceImpl extends EgovAbstractServiceImpl implements UserDetailsService {

	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * 
	 * @return Object - 사용자 ValueObject
	 */
    public Object getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (EgovObjectUtil.isNull(authentication)) {
//       	LOG.debug("## authentication object is null!!");
            return null;
        }

        if (authentication.getPrincipal() instanceof EgovUserDetails) {
        	EgovUserDetails details = (EgovUserDetails) authentication.getPrincipal();
//       	LOG.debug("## EgovUserDetailsHelper.getAuthenticatedUser : AuthenticatedUser is {}", details.getUsername());
	        return details.getEgovUserVO();
        } else {
        	return null;
        }
    }

	/**
	 * 인증된 사용자의 권한 정보를 가져온다. 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B,
	 * ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED,
	 * IS_AUTHENTICATED_ANONYMOUSLY]
	 * 
	 * @return List - 사용자 권한정보 목록
	 */
    public List<String> getAuthorities() {
        List<String> listAuth = new ArrayList<String>();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (EgovObjectUtil.isNull(authentication)) {
//        	LOG.debug("## authentication object is null!!");
            return null;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Iterator<? extends GrantedAuthority> iter = authorities.iterator();

        while(iter.hasNext()) {
        	GrantedAuthority auth = iter.next();
        	listAuth.add(auth.getAuthority());
//       	LOG.debug("## EgovUserDetailsHelper.getAuthorities : Authority is {}", auth.getAuthority());
        }

        return listAuth;
    }

	/**
	 * 인증된 사용자 여부를 체크한다.
	 * 
	 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
	 */

    public Boolean isAuthenticated() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (EgovObjectUtil.isNull(authentication)) {
//        	LOG.debug("## authentication object is null!!");
            return Boolean.FALSE;
        }

        String username = authentication.getName();
        if (username.equals("anonymousUser")) {		// 기존 2.0.8의 경우 'roleAnonymous'
//        	LOG.debug("## username is {}", username);
            return Boolean.FALSE;
        }

        Object principal = authentication.getPrincipal();

        return (Boolean.valueOf(!EgovObjectUtil.isNull(principal)));
    }

}
