package aramframework.com.sym.log.tlg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.log.tlg.domain.TrsmrcvLogVO;
import aramframework.com.sym.log.tlg.service.TrsmrcvLogService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 송수신 로그관리를 위한 서비스 구현 클래스
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

@Service("trsmrcvLogService")
public class TrsmrcvLogServiceImpl extends EgovAbstractServiceImpl implements TrsmrcvLogService {

	@Autowired
	private TrsmrcvLogMapper trsmrcvLogMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService trsmrcvLogIdGnrService; 

	/**
	 * 송수신 로그정보 목록을 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public List<EgovMap> selectTrsmrcvLogInf(TrsmrcvLogVO trsmrcvLogVO) {
		return trsmrcvLogMapper.selectTrsmrcvLogInf(trsmrcvLogVO);
	}

	/**
	 * 송수신 로그정보 총 갯수를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public int selectTrsmrcvLogInfCnt(TrsmrcvLogVO trsmrcvLogVO) {
		return trsmrcvLogMapper.selectTrsmrcvLogInfCnt(trsmrcvLogVO);
	}

	/**
	 * 송수신 로그정보를 조회한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public TrsmrcvLogVO selectTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO) {
		TrsmrcvLogVO resultVo = trsmrcvLogMapper.selectTrsmrcvLog(trsmrcvLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, trsmrcvLogVO); 
		return resultVo;
	}

	/**
	 * 송수신 로그정보를 생성한다.
	 * 
	 * @param trsmrcvLogVO
	 */
	public void logInsertTrsmrcvLog(TrsmrcvLogVO trsmrcvLogVO) {
		try {
			trsmrcvLogVO.setRequstId(trsmrcvLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		trsmrcvLogMapper.logInsertTrsmrcvLog(trsmrcvLogVO);
	}

	/**
	 * 송수신 로그정보를 요약한다.
	 * 
	 */
	public void logInsertTrsmrcvLogSummary() {
		trsmrcvLogMapper.logInsertTrsmrcvLogSummary();
		trsmrcvLogMapper.logDeleteTrsmrcvLogSummary();
	}

}
