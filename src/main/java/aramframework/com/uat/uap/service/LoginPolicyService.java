package aramframework.com.uat.uap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uat.uap.dao.LoginPolicyMapper;
import aramframework.com.uat.uap.domain.LoginPolicyVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 로그인정책에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class LoginPolicyService extends EgovAbstractServiceImpl {

	@Autowired
	LoginPolicyMapper loginPolicyMapper;
	
	/**
	 * 로그인정책 목록을 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public List<EgovMap> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) {
		return loginPolicyMapper.selectLoginPolicyList(loginPolicyVO);
	}

	/**
	 * 로그인정책 목록 수를 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public int selectLoginPolicyListCnt(LoginPolicyVO loginPolicyVO) {
		return loginPolicyMapper.selectLoginPolicyListCnt(loginPolicyVO);
	}

	/**
	 * 로그인정책 목록의 상세정보를 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) {
		return loginPolicyMapper.selectLoginPolicy(loginPolicyVO);
	}

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void insertLoginPolicy(LoginPolicyVO loginPolicyVO) {
		loginPolicyMapper.insertLoginPolicy(loginPolicyVO);
	}

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void updateLoginPolicy(LoginPolicyVO loginPolicyVO) {
		loginPolicyMapper.updateLoginPolicy(loginPolicyVO);
	}

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void deleteLoginPolicy(LoginPolicyVO loginPolicyVO) {
		loginPolicyMapper.deleteLoginPolicy(loginPolicyVO);
	}

}
