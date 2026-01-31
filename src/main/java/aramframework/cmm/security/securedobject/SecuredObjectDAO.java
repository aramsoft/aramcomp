package aramframework.cmm.security.securedobject;

import java.util.List;
import java.util.Map;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public interface SecuredObjectDAO {

	public List<Map<String, Object>> loadUsersByUsername(String username);
	public List<Map<String, Object>> loadUserAuthorities(String username);
	
	public List<Map<String, Object>> getHierarchicalRoles();
	public List<Map<String, Object>> getRolesAndUrl();
	public List<Map<String, Object>> getRolesAndMethod();
	public List<Map<String, Object>> getRolesAndPointcut();
	public List<Map<String, Object>> getRegexMatchedRequestMapping(String url);

}
