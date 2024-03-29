package aramframework.com.uat.uap.dao;

import java.util.List;

import aramframework.com.uat.uap.domain.LoginPolicyVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 로그인정책에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface LoginPolicyMapper {

	/**
	 * 로그인정책 목록을 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public List<EgovMap> selectLoginPolicyList(LoginPolicyVO loginPolicyVO);

	/**
	 * 로그인정책 목록 수를 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public int selectLoginPolicyListCnt(LoginPolicyVO loginPolicyVO);

	/**
	 * 로그인정책 목록의 상세정보를 조회한다.
	 * 
	 * @param loginPolicyVO
	 */
	public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO);

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void insertLoginPolicy(LoginPolicyVO loginPolicyVO);
	
	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void updateLoginPolicy(LoginPolicyVO loginPolicyVO);

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * 
	 * @param loginPolicyVO
	 */
	public void deleteLoginPolicy(LoginPolicyVO loginPolicyVO);

}
