package aramframework.com.sym.log.clg.service.impl;

import java.util.List;

import aramframework.com.sym.log.clg.service.LoginLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 로그인 로그관리를 위한 데이터 접근 클래스
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

@Mapper("loginLogMapper")
public interface LoginLogMapper {

	/**
	 * 로그인 로그를 목록을 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public List<EgovMap> selectLoginLogInf(LoginLogVO loginLogVO);

	/**
	 * 로그인 로그목록의 숫자를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public int selectLoginLogInfCnt(LoginLogVO loginLogVO);

	/**
	 * 로그인 로그를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public LoginLogVO selectLoginLog(LoginLogVO loginLogVO);

	/**
	 * 로그인 로그를 기록한다.
	 * 
	 * @param loginLogVO
	 */
	public void logInsertLoginLog(LoginLogVO loginLogVO);

}
