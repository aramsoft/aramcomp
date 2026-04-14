package aramframework.cmm.security.bean;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;

import aramframework.cmm.security.securedobject.SecuredObjectService;

/**
 * Bean의 초기화 데이터 제공 기능을 구현 클래스
 * @since 2014.01.22
 * @version 3.0
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일		수정자				수정내용
 * ----------------------------------------------
 * 2014.01.22   한성곤             Spring Security 3.2.X 업그레이드 적용 (기존 ResourcesMapFactoryBean  분리)
 * </pre>
 */
public class MethodResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private String resourceType;

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

	@Autowired 
    private SecuredObjectService securedObjectService;

    private LinkedHashMap<String, List<ConfigAttribute>> resourcesMap;

    public void init() throws Exception {
        if ("method".equals(resourceType)) {
            resourcesMap = securedObjectService.getRolesAndMethod();
        } else if ("pointcut".equals(resourceType)) {
            resourcesMap = securedObjectService.getRolesAndPointcut();
        } else {
            throw new Exception("resourceType must be 'method' or 'pointcut'");
        }
    }

    public LinkedHashMap<String, List<ConfigAttribute>> getObject() throws Exception {
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

