package aramframework.com.sym.log.wlg.dao;

import java.util.List;

import aramframework.com.sym.log.wlg.domain.WebLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 웹 로그관리를 위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface WebLogMapper {

	/**
	 * 웹 로그 목록을 조회한다.
	 * 
	 * @param webLogVO
	 */
	public List<EgovMap> selectWebLogInf(WebLogVO webLogVO);

	/**
	 * 웹 로그 목록의 숫자를 조회한다.
	 * 
	 * @param webLogVO
	 */
	public int selectWebLogInfCnt(WebLogVO webLogVO);

	/**
	 * 웹 로그정보를 조회한다.
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
	 * 웹 로그요약정보를 등록한다.
	 * 
	 */
	public void logInsertWebLogSummary();

	/**
	 * 웹 로그요약정보를 제거한다.
	 * 
	 */
	public void logDeleteWebLogSummary();

}
