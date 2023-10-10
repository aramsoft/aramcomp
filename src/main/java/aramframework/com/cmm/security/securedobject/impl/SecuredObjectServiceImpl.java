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
package aramframework.com.cmm.security.securedobject.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.egovframe.rte.fdl.security.config.SecurityConfig;
import org.egovframe.rte.fdl.security.securedobject.EgovSecuredObjectService;
import org.egovframe.rte.fdl.security.securedobject.impl.SelfRegexRequestMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import aramframework.com.cmm.security.securedobject.dao.SecuredObjectMapper;

/**
 * 보호객체 관리를 지원하는 구현 클래스
 * 
 * <p><b>NOTE:</b> Spring Security의 초기 데이터를 DB로 부터 조회하여
 * 보호된 자원 접근 권한을 지원, 제어 할 수 있도록 구현한 클래스이다.</p>
 * 
 * @author ByungHun Woo
 * @since 2009.06.01
 * @version 1.0
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일		수정자				수정내용
 * ----------------------------------------------
 * 2009.06.01	윤성종				최초 생성
 * 2014.01.22	한성곤				Spring Security 3.2.X 업그레이드 적용
 * </pre>
 */
public class SecuredObjectServiceImpl implements EgovSecuredObjectService, ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecuredObjectServiceImpl.class);
	
	private SecuredObjectMapper securedObjectMapper;
	
    private String requestMatcherType = "ant";	// default

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if (context.getBeanNamesForType(SecurityConfig.class).length > 0) {
			SecurityConfig config = context.getBean(SecurityConfig.class);
			if (config != null) {
				requestMatcherType = config.getRequestMatcherType();
			}
		}
	}

    public void setSecuredObjectMapper(SecuredObjectMapper securedObjectMapper) {
        this.securedObjectMapper = securedObjectMapper;
    }
    
    public void setRequestMatcherType(String requestMatcherType) {
    	this.requestMatcherType = requestMatcherType;
    }
    
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
		LinkedHashMap<Object, List<ConfigAttribute>> data = getRolesAndResources("url", requestMatcherType);
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		Set<Object> keys = data.keySet();
		for (Object key : keys) {
			ret.put((RequestMatcher) key, data.get(key));
		}
		return ret;
	}

	public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
		LinkedHashMap<Object, List<ConfigAttribute>> data = getRolesAndResources("method", null);
		LinkedHashMap<String, List<ConfigAttribute>> ret = new LinkedHashMap<String, List<ConfigAttribute>>();
		return getRolesAndPath(ret, data);
	}

	public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndPointcut() throws Exception {
		LinkedHashMap<Object, List<ConfigAttribute>> data = getRolesAndResources("pointcut", null);
		LinkedHashMap<String, List<ConfigAttribute>> ret = new LinkedHashMap<String, List<ConfigAttribute>>();
		return getRolesAndPath(ret, data);
	}

    /**
     * 리소스 유형에 대한 할당된 롤 정보를 가져온다.
     *
     * @param resourceType
     * @return
     * @throws Exception
     */
    private LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType, String requestMatcherType) throws Exception {

        LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap = new LinkedHashMap<Object, List<ConfigAttribute>>();

        List<Map<String, Object>> resultList;
        boolean isResourcesUrl = true;

        if ("method".equals(resourceType)) {
        	resultList = securedObjectMapper.getRolesAndMethod();
            isResourcesUrl = false;
        } else if ("pointcut".equals(resourceType)) {
           	resultList = securedObjectMapper.getRolesAndPointcut();
            isResourcesUrl = false;
        } else {
           	resultList = securedObjectMapper.getRolesAndUrl();
        }

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        String preResource = null;
        String presentResourceStr;
        Object presentResource;
        while (itr.hasNext()) {
            tempMap = itr.next();
            presentResourceStr = (String) tempMap.get(resourceType);
            // url 인 경우 RequestKey 형식의 key를 Map에 담아야 함
            if (isResourcesUrl) {
            	if (requestMatcherType.equalsIgnoreCase("regex")) {
            		presentResource = new SelfRegexRequestMatcher(presentResourceStr, null);
            	} else if (requestMatcherType.equalsIgnoreCase("ciRegex")) {
            		presentResource = new SelfRegexRequestMatcher(presentResourceStr, null, true);
            	} else {
            		presentResource = new AntPathRequestMatcher(presentResourceStr);
            	}
            } else {
            	presentResource = presentResourceStr;
            }

            List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

            // 이미 requestMap 에 해당 Resource 에 대한 Role 이 하나 이상 맵핑되어 있었던 경우,
            // sort_order 는 resource(Resource) 에 대해 매겨지므로 같은 Resource 에 대한 Role 맵핑은 연속으로 조회됨.
            // 해당 맵핑 Role List (SecurityConfig) 의 데이터를 재활용하여 새롭게 데이터 구축
            if (preResource != null && presentResourceStr.equals(preResource)) {
                List<ConfigAttribute> preAuthList = resourcesMap.get(presentResource);
                Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();
                while (preAuthItr.hasNext()) {
                	org.springframework.security.access.SecurityConfig tempConfig = (org.springframework.security.access.SecurityConfig) preAuthItr.next();
                    configList.add(tempConfig);
                }
            }

            configList.add(new org.springframework.security.access.SecurityConfig((String) tempMap.get("authority")));

            // 만약 동일한 Resource 에 대해 한개 이상의 Role 맵핑 추가인 경우
            // 이전 resourceKey 에 현재 새로 계산된 Role 맵핑 리스트로 덮어쓰게 됨.
            resourcesMap.put(presentResource, configList);

            // 이전 resource 비교위해 저장
            preResource = presentResourceStr;
        }

        return resourcesMap;
    }

	private LinkedHashMap<String, List<ConfigAttribute>> getRolesAndPath(LinkedHashMap<String, List<ConfigAttribute>> ret, LinkedHashMap<Object, List<ConfigAttribute>> data) {
		Set<Object> keys = data.keySet();
		for (Object key : keys) {
			ret.put((String) key, data.get(key));
		}
		return ret;
	}

	public String getHierarchicalRoles() throws Exception {
    	List<Map<String, Object>> resultList = securedObjectMapper.getHierarchicalRoles();

        Iterator<Map<String, Object>> itr = resultList.iterator();
        StringBuffer concatedRoles = new StringBuffer();
        Map<String, Object> tempMap;
        while (itr.hasNext()) {
            tempMap = itr.next();
            concatedRoles.append(tempMap.get("child"));
            concatedRoles.append(" > ");
            concatedRoles.append(tempMap.get("parent"));
            concatedRoles.append("\n");
        }
        return concatedRoles.toString();
	}

	public List<ConfigAttribute> getMatchedRequestMapping(String url) throws Exception {
        List<Map<String, Object>> resultList = securedObjectMapper.getRegexMatchedRequestMapping(url);

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();
        // 같은 Uri 에 대한 Role 맵핑이므로 무조건 configList 에 add 함
        while (itr.hasNext()) {
            tempMap = itr.next();
			configList.add(new org.springframework.security.access.SecurityConfig ((String) tempMap.get("authority")));
        }

        if (configList.size() > 0) {
        	LOGGER.debug("Request Uri : {}, matched Uri : {}, mapping Roles : {}", url, resultList.get(0).get("uri"), configList);
        }

        return configList;
	}

}
