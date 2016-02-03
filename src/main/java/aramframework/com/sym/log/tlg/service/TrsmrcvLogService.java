package aramframework.com.sym.log.tlg.service;

import java.util.List;

import aramframework.com.sym.log.tlg.domain.TrsmrcvLogVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 송수신 로그관리를 위한 서비스 인터페이스
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

public interface TrsmrcvLogService {

	/**
	 * 송수신 로그 목록을 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public List<EgovMap> selectTrsmrcvLogInf(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그정보 총 갯수를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public int selectTrsmrcvLogInfCnt(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public TrsmrcvLogVO selectTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그정보를 생성한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public void logInsertTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그정보를 요약한다.
	 * 
	 */
	public void logInsertTrsmrcvLogSummary();

}
