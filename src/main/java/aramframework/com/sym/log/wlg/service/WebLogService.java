package aramframework.com.sym.log.wlg.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 웹 로그관리를 위한 서비스 인터페이스
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

public interface WebLogService {

	/**
	 * 웹 로그 목록을 조회한다.
	 * 
	 * @param webLogVO
	 */
	public List<EgovMap> selectWebLogInf(WebLogVO webLogVO);

	/**
	 * 웹 로그 총 갯수를 조회한다.
	 * 
	 * @param webLogVO
	 */
	public int selectWebLogInfCnt(WebLogVO webLogVO);

	/**
	 * 웹 로그를 조회한다.
	 * 
	 * @param webLogVO
	 */
	public WebLogVO selectWebLog(WebLogVO webLogVO);

	/**
	 * 웹 로그를 기록한다.
	 * 
	 * @param webLogVO
	 */
	public void logInsertWebLog(WebLogVO webLogVO);

	/**
	 * 웹 로그정보를 요약한다.
	 * 
	 */
	public void logInsertWebLogSummary();

}
