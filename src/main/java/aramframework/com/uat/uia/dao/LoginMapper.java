package aramframework.com.uat.uia.dao;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface LoginMapper {

	/**
	 * 2011.08.26 EsntlId를 이용한 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionLoginByEsntlId(LoginVO loginVO);

	/**
	 * 일반 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionLogin(LoginVO loginVO);

	/**
	 * 인증서 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	public LoginVO actionCrtfctLogin(LoginVO loginVO);

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param loginVO
	 */
	public LoginVO searchId(LoginVO loginVO);

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param loginVO
	 */
	public LoginVO searchPassword(LoginVO loginVO);

	/**
	 * 변경된 비밀번호를 저장한다.
	 * 
	 * @param loginVO
	 */
	public void updatePassword(LoginVO loginVO);
	
	/**
	 * 핸드폰주인을  찾는다.
	 * 
	 * @param mblTelNo
	 */
	public int searchMblTelNo(String mblTelNo);

}
