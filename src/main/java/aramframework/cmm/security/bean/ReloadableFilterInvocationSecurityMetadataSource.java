package aramframework.cmm.security.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import aramframework.cmm.security.securedobject.SecuredObjectService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MetadataSource 처리.
 * 
 * @author 실행환경 개발팀 윤성종
 * @since 2009.06.01
 * @version 1.0
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일		수정자				수정내용
 * ----------------------------------------------
 * 2009.06.01   윤성종		        최초 생성
 * 2014.01.22   한성곤		        Spring Security 3.2.X 업그레이드 적용
 * </pre>
 */
public class ReloadableFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReloadableFilterInvocationSecurityMetadataSource.class);

	@Autowired 
	private SecuredObjectService securedObjectService;

	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	public ReloadableFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        this.requestMap = requestMap;
    }

	@Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

	@Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = (HttpServletRequest) ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

	@Override
   public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

	public void reload() throws Exception {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedMap = securedObjectService.getRolesAndUrl();
        Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadedMap.entrySet().iterator();
        // 이전 데이터 삭제
        requestMap.clear();
        while (iterator.hasNext()) {
        	Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
            requestMap.put(entry.getKey(), entry.getValue());
        }
    	LOGGER.info("Secured Url Resources - Role Mappings reloaded at Runtime!");
    }

}
