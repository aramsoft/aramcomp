package aramframework.com.sym.log.tlg.service.impl;

import java.util.List;

import aramframework.com.sym.log.tlg.service.TrsmrcvLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 송수신 로그관리를 위한 데이터 접근 클래스
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

@Mapper
public interface TrsmrcvLogMapper {

	/**
	 * 송수신 로그 목록을 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public List<EgovMap> selectTrsmrcvLogInf(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그 목록의 숫자를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public int selectTrsmrcvLogInfCnt(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그정보를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public TrsmrcvLogVO selectTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그를 기록한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public void logInsertTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO);

	/**
	 * 송수신 로그요약정보를 등록한다.
	 * 
	 */
	public void logInsertTrsmrcvLogSummary();

	/**
	 * 송수신 로그요약정보를 제거한다.
	 * 
	 */
	public void logDeleteTrsmrcvLogSummary();
	
}
