package aramframework.com.cmm.security.securedobject.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aramframework.com.cmm.security.securedobject.SecuredObjectMapper;

import org.apache.ibatis.session.SqlSession;

@Repository
public class  SecuredObjectDAOImpl implements SecuredObjectMapper {
	
	@Autowired
	private SqlSession sqlSession;
 
	private static final String NS = "aramframework.com.cmm.security.securedobject.dao.impl.SecuredObjectMapper.";
 
	@Override
	public List<Map<String, Object>> getHierarchicalRoles() {
		return sqlSession.selectList(NS+"getHierarchicalRoles");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndUrl() {
		return sqlSession.selectList(NS+"getRolesAndUrl");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndMethod() {
		return sqlSession.selectList(NS+"getRolesAndMethod");
	}
	
	@Override
	public List<Map<String, Object>> getRolesAndPointcut() {
		return sqlSession.selectList(NS+"getRolesAndPointcut");
	}
	
	public List<Map<String, Object>> getRegexMatchedRequestMapping(String url) {
		return sqlSession.selectList(NS+"getRegexMatchedRequestMapping", url);
	}	
	
}	