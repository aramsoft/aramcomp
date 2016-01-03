package aramframework.com.sym.log.lgm.service.impl;

import java.util.List;

import aramframework.com.sym.log.lgm.service.SysLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템 로그를 위한 데이터 접근 클래스
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

@Mapper("sysLogMapper")
public interface SysLogMapper {

	/**
	 * 시스템 로그정보 목록을 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public List<EgovMap> selectSysLogInf(SysLogVO sysLogVO);

	/**
	 * 시스템 로그정보 목록의 숫자를 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public int selectSysLogInfCnt(SysLogVO sysLogVO);

	/**
	 * 시스템 로그정보를 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public SysLogVO selectSysLog(SysLogVO sysLogVO);

	/**
	 * 시스템 로그정보를 생성한다.
	 * 
	 * @param sysLogVO
	 */
	public void logInsertSysLog(SysLogVO sysLogVO);

	/**
	 * 시스템 로그요약정보를 등록한다.
	 * 
	 */
	public void logInsertSysLogSummary();

	/**
	 * 시스템 로그요약정보를 제거한다.
	 * 
	 */
	public void logDeleteSysLogSummary();
	
}
