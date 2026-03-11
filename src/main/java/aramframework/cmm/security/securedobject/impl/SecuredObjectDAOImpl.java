package aramframework.cmm.security.securedobject.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aramframework.cmm.security.securedobject.SecuredObjectDAO;

import org.apache.ibatis.session.SqlSession;

@Repository
public class  SecuredObjectDAOImpl implements SecuredObjectDAO {
	
	static final String NAMESPACE = "aramframework.cmm.security.securedobject.dao.impl.SecuredObjectMapper.";
 
	@Autowired
	private SqlSession sqlSession;
 
	@Override
	public List<Map<String, Object>> loadUsersByUsername(String username){
		return sqlSession.selectList(NAMESPACE+"loadUsersByUsername", username);
	}
	
	@Override
	public List<Map<String, Object>> loadUserAuthorities(String username) {
		return sqlSession.selectList(NAMESPACE+"loadUserAuthorities", username);
	}
	
	@Override
	public List<Map<String, Object>> getHierarchicalRoles() {
		return sqlSession.selectList(NAMESPACE+"getHierarchicalRoles");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndUrl() {
		return sqlSession.selectList(NAMESPACE+"getRolesAndUrl");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndMethod() {
		return sqlSession.selectList(NAMESPACE+"getRolesAndMethod");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndPointcut() {
		return sqlSession.selectList(NAMESPACE+"getRolesAndPointcut");
	}
	
	public List<Map<String, Object>> getRegexMatchedRequestMapping(String url) {
		return sqlSession.selectList(NAMESPACE+"getRegexMatchedRequestMapping", url);
	}	
	
}	