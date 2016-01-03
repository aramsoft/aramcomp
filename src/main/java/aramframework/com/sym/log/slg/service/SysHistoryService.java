package aramframework.com.sym.log.slg.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템 처리 이력관리를 위한 서비스 인터페이스
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

public interface SysHistoryService {

	/**
	 * 시스템 이력정보 목록을 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public List<EgovMap> selectSysHistoryList(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보 총 갯수를 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public int selectSysHistoryListCnt(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 상세조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public SysHistoryVO selectSysHistory(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 등록한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void insertSysHistory(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 수정한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void updateSysHistory(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 삭제한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void deleteSysHistory(SysHistoryVO sysHistoryVO);

}
