package aramframework.com.sym.log.lgm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.log.lgm.domain.SysLogVO;
import aramframework.com.sym.log.lgm.service.SysLogService;
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

@Service("sysLogService")
public class SysLogServiceImpl extends EgovAbstractServiceImpl implements SysLogService {

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
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, sysLogVO); 
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
