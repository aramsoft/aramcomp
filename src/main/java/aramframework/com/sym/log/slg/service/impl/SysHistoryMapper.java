package aramframework.com.sym.log.slg.service.impl;

import java.util.List;

import aramframework.com.sym.log.slg.service.SysHistoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템 이력정보를 관리하기 위한 데이터 처리 클래스
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

@Mapper("sysHistoryMapper")
public interface SysHistoryMapper {

	/**
	 * 시스템 이력정보 목록을 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public List<EgovMap> selectSysHistoryList(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보 목록의 글 갯수를 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public int selectSysHistoryListCnt(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public SysHistoryVO selectSysHistory(SysHistoryVO sysHistoryVO);

	/**
	 * 시스템 이력정보를 생성한다.
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
