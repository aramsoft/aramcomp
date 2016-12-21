package aramframework.com.sym.log.lgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.log.lgm.dao.SysLogMapper;
import aramframework.com.sym.log.lgm.domain.SysLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 시스템 로그관리를 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class SysLogService extends EgovAbstractServiceImpl {

	@Autowired
	private SysLogMapper sysLogMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService sysLogIdGnrService; 

	/**
	 * 시스템 로그정보 목록을 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public List<EgovMap> selectSysLogInf(SysLogVO sysLogVO) {
		return sysLogMapper.selectSysLogInf(sysLogVO);
	}

	/**
	 * 시스템 로그정보 총 갯수를 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public int selectSysLogInfCnt(SysLogVO sysLogVO) {
		return sysLogMapper.selectSysLogInfCnt(sysLogVO);
	}

	/**
	 * 시스템 로그정보를 조회한다.
	 * 
	 * @param sysLogVO
	 */
	public SysLogVO selectSysLog(SysLogVO sysLogVO) {
		SysLogVO resultVo = sysLogMapper.selectSysLog(sysLogVO);
		// searchVO 이전 
		resultVo.setSearchVO(sysLogVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * 
	 * @param sysLogVO
	 */
	public void logInsertSysLog(SysLogVO sysLogVO) {
		try {
			sysLogVO.setRequstId(sysLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		sysLogMapper.logInsertSysLog(sysLogVO);
	}

	/**
	 * 시스템 로그정보를 요약한다.
	 * 
	 */
	public void logInsertSysLogSummary() {
		sysLogMapper.logInsertSysLogSummary();
		sysLogMapper.logDeleteSysLogSummary();
	}

}
