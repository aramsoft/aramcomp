package aramframework.com.sym.log.slg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.log.slg.domain.SysHistoryVO;
import aramframework.com.sym.log.slg.service.SysHistoryService;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템 이력관리를 위한 서비스 구현 클래스
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

@Service("sysHistoryService")
public class SysHistoryServiceImpl extends EgovAbstractServiceImpl implements SysHistoryService {

	@Autowired
	private SysHistoryMapper sysHistoryMapper;
	
	@Autowired
	private FileMngUtil fileUtil;

	/**
	 * 시스템 이력정보 목록을 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public List<EgovMap> selectSysHistoryList(SysHistoryVO sysHistoryVO) {
		return sysHistoryMapper.selectSysHistoryList(sysHistoryVO);
	}

	/**
	 * 시스템 이력정보 총 갯수를 조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public int selectSysHistoryListCnt(SysHistoryVO sysHistoryVO) {
		return sysHistoryMapper.selectSysHistoryListCnt(sysHistoryVO);
	}

	/**
	 * 시스템 이력정보를 상세조회한다.
	 * 
	 * @param sysHistoryVO
	 */
	public SysHistoryVO selectSysHistory(SysHistoryVO sysHistoryVO) {
		SysHistoryVO resultVo = sysHistoryMapper.selectSysHistory(sysHistoryVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, sysHistoryVO); 
		return resultVo;
	}

	/**
	 * 시스템 이력정보를 등록한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void insertSysHistory(SysHistoryVO sysHistoryVO) {
		// String histId = "HIST_"+20091021144553005; yyyyMMddhhmmssSSS
		String histId = "HT_" + StringUtil.getTimeStamp();
		sysHistoryVO.setHistId(histId);
		sysHistoryMapper.insertSysHistory(sysHistoryVO);
	}

	/**
	 * 시스템 이력정보를 수정한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void updateSysHistory(SysHistoryVO sysHistoryVO) {
		sysHistoryMapper.updateSysHistory(sysHistoryVO);
	}

	/**
	 * 시스템 이력정보를 삭제한다.
	 * 
	 * @param sysHistoryVO
	 */
	public void deleteSysHistory(SysHistoryVO sysHistoryVO) {
		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(sysHistoryVO.getAtchFileId());

		sysHistoryMapper.deleteSysHistory(sysHistoryVO);
	}

}
