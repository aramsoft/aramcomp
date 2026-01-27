/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aramframework.cmm.security.userdetails.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.egovframe.rte.fdl.security.userdetails.EgovUserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import aramframework.cmm.security.securedobject.SecuredObjectDAO;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 사용자계정 정보를 DB에서 관리할수 있도록 구현한 클래스
 * 
 * <p><b>NOTE:</b> org.springframework.security.userdetails.jdbc.JdbcUserDetailsManager 를 확장하여 사용자 계정 정보를 DB에서 관리할 수 있도록 구현한 클래스이다.</p>
 * 
 * @author 실행환경 개발팀 윤성종
 * @since 2009.06.01
 * @version 1.0
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일		수정자				수정내용
 * ----------------------------------------------
 * 2009.06.01   윤성종             최초 생성
 * 2014.01.22   한성곤             Spring Security 3.2.X 업그레이드 적용
 * 2017.02.15   장동한             시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 * </pre>
 */
public class DaoUserDetailsManager implements UserDetailsManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoUserDetailsManager.class);

	private SecuredObjectDAO securedObjectDAO;
    private RoleHierarchy roleHierarchy = null;

    /**
     * Role Hierarchy를 지원한다.
     * 
     * @param roleHierarchy
     */
    public void setSecuredObjectDAO(SecuredObjectDAO securedObjectDAO) {
        this.securedObjectDAO = securedObjectDAO;
    }
    
   /**
     * Role Hierarchy를 지원한다.
     * 
     * @param roleHierarchy
     */
    public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    /**
     * JdbcDaoImpl 클래스의 loadUsersByUsername 메소드 재정의.
     * 사용자명(또는 ID)로 EgovUserDetails의 정보를 조회한다.
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     */
    @Override
    public EgovUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	List<Map<String, Object>> users = securedObjectDAO.loadUsersByUsername(username);
        if (users.size() == 0) {
        	LOGGER.debug("Query returned no results for user '{}'", username);
			throw new UsernameNotFoundException("Username not found");
//			throw new UsernameNotFoundException(messages.getMessage("EgovJdbcUserDetailsManager.notFound", new Object[] { username }, "Username {0} not found"));
        }

        Map<String, Object> obj = users.get(0);
        
		String strUserId = (String)obj.get("USER_ID");
		String strPassWord = (String)obj.get("PASSWORD");
		boolean boolEnabled = (Long)obj.get("ENABLED") == 1 ? true : false;

		String strUserNm = (String)obj.get("USER_NM");
		String strUserSe = (String)obj.get("USER_SE");
		String strUserEmail = (String)obj.get("USER_EMAIL");
		String strOrgnztId = (String)obj.get("ORGNZT_ID");
		/** 2010.06.30 *이용 *조직명 추가 */
		String strOrgnztNm = (String)obj.get("ORGNZT_NM");
		String strMblTelNo = (String)obj.get("MBTLNUM");

		// 세션 항목 설정
		LoginVO loginVO = new LoginVO();
		loginVO.setUserId(strUserId);
		loginVO.setPassword(strPassWord);
		loginVO.setName(strUserNm);
		loginVO.setUserSe(strUserSe);
		loginVO.setEmail(strUserEmail);
		loginVO.setOrgnztId(strOrgnztId);
		/** 2010.06.30 *이용 *조직명 추가 */
		loginVO.setOrgnztNm(strOrgnztNm);
		loginVO.setMblTelNo(strMblTelNo);

    	List<Map<String, Object>> results = securedObjectDAO.loadUserAuthorities(strUserId);
    	List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    	for(Map<String, Object> result : results) {
    		roles.add(new SimpleGrantedAuthority((String)result.get("AUTHORITY")));
    	}
        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
        dbAuthsSet.addAll(roles);
        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
        if (dbAuths.size() == 0) {
			throw new UsernameNotFoundException("User has no GrantedAuthority");
//			throw new UsernameNotFoundException(messages.getMessage("EgovJdbcUserDetailsManager.noAuthority", new Object[] { username }, "User {0} has no GrantedAuthority"));
        }

        // RoleHierarchyImpl 에서 저장한 Role Hierarchy 정보가 저장된다.
        Collection<? extends GrantedAuthority> authorities = roleHierarchy.getReachableGrantedAuthorities(dbAuths);

        // JdbcDaoImpl 클래스의 createUserDetails 메소드 재정의
        return new EgovUserDetails(strUserId, strPassWord, boolEnabled, true, true, true, authorities, loginVO);
    }

    /**
     * 인증된 사용자 이름으로 사용자정보(EgovUserDetails)를 가져온다.
     * 
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     */
    public EgovUserDetails getAuthenticatedUser() throws UsernameNotFoundException, DataAccessException {
        return loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
