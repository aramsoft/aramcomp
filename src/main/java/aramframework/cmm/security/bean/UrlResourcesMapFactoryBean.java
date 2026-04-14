package aramframework.cmm.security.bean;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import aramframework.cmm.security.securedobject.SecuredObjectService;

/**
 * Bean의 초기화 데이터 제공 기능을 구현 클래스
 * 
 * <p><b>NOTE:</b> DB 기반의 보호자원 맵핑 정보를 얻어 이를 참조하는 Bean 의 초기화 데이터로 제공한다.</p>
 * 
 * @author marcos.sousa - reference http://forum.springframework.org/showthread.php?t=56615&highlight=database&page=2
 * @author ByungHun Woo
 * @since 2009.06.01
 * @version 1.0
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일		수정자				수정내용
 * ----------------------------------------------
 * 2009.06.01   윤성종             최초 생성
 * 2014.01.22   한성곤             Spring Security 3.2.X 업그레이드 적용
 * </pre>
 */
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

	@Autowired 
	private SecuredObjectService securedObjectService;

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourcesMap;

    public void init() throws Exception {
    	resourcesMap = securedObjectService.getRolesAndUrl();
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if (resourcesMap == null) {
            init();
        }
        return resourcesMap;
    }

    @SuppressWarnings("rawtypes")
	public Class<LinkedHashMap> getObjectType() {
        return LinkedHashMap.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
