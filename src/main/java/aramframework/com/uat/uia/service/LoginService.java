package aramframework.com.uat.uia.service;

import aramframework.com.cmm.LoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
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

public interface LoginService {

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
	LoginVO actionLogin(LoginVO loginVO);

	/**
	 * 인증서 로그인을 처리한다
	 * 
	 * @param loginVO
	 */
	LoginVO actionCrtfctLogin(LoginVO loginVO);

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param loginVO
	 */
	LoginVO searchId(LoginVO loginVO);

	/**
	 * 비밀번호를 찾는다.
	 * 
	 * @param loginVO
	 */
	boolean searchPassword(LoginVO loginVO);
	
	/**
	 * 핸드폰주인을  찾는다.
	 * 
	 * @param mblTelNo
	 */
	int searchMblTelNo(String mblTelNo);

}
