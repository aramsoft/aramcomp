package aramframework.com.uat.uap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uat.uap.domain.LoginPolicyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 로그인정책에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
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
		LoginPolicyVO resultVo = loginPolicyMapper.selectLoginPolicy(loginPolicyVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, loginPolicyVO); 
		return resultVo;
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
