package aramframework.cmm.security.userdetails.hierarchicalroles;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import aramframework.cmm.security.securedobject.SecuredObjectService;

/**
 * DB기반의 롤 계층정보를 지원하는 비즈니스 구현 클래스
 * 
 * <p><b>NOTE:</b> DB 기반의 Role 계층 관계 정보를 얻어 이를 참조하는 Bean 의 초기화 데이터로 제공한다.</p>
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
 * 2009.06.01   윤성종		        최초 생성
 * 2014.01.22   한성곤		        Spring Security 3.2.X 업그레이드 적용, 설정 간소화 처리 관련 변경
 * </pre>
 */
public class HierarchyStringsFactoryBean implements FactoryBean<String> {

	@Autowired 
	private SecuredObjectService securedObjectService;

    private String hierarchyStrings;
    
    public void init() throws Exception {
    	// 기본 처리
        hierarchyStrings = (String) securedObjectService.getHierarchicalRoles();
    }

    public String getObject() throws Exception {
        if (hierarchyStrings == null) {
            init();
        }
        return hierarchyStrings;
    }

    public Class<String> getObjectType() {
        return String.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
