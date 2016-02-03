package aramframework.com.sym.log.clg.service;

import java.util.List;

import aramframework.com.sym.log.clg.domain.LoginLogVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 로그인 로그관리를 위한 서비스 인터페이스
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

public interface LoginLogService {

	/**
	 * 로그인 로그 목록을 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public List<EgovMap> selectLoginLogInf(LoginLogVO loinLogVO);

	/**
	 * 로그인 로그 총 갯수를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public int selectLoginLogInfCnt(LoginLogVO loginLogVO);

	/**
	 * 로그인 로그를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public LoginLogVO selectLoginLog(LoginLogVO loinLogVO);

	/**
	 * 로그인 로그를 기록한다.
	 * 
	 * @param loginLogVO
	 */
	public void logInsertLoginLog(LoginLogVO loinLogVO);

}
