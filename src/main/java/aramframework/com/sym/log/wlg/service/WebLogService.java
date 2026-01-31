package aramframework.com.sym.log.wlg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.cmm.util.LogUtil;
import aramframework.com.sym.log.wlg.dao.WebLogMapper;
import aramframework.com.sym.log.wlg.domain.WebLogVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 웹 로그관리를 위한 서비스 구현 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class WebLogService extends EgovAbstractServiceImpl {

	protected static final Logger logger = LoggerFactory.getLogger(WebLogService.class);
	
	@Autowired
	private WebLogMapper webLogMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService webLogIdGnrService; 

	/**
	 * 웹 로그 목록을 조회한다.
	 * 
	 * @param webLogVO
	 */
	public List<EgovMap> selectWebLogInf(WebLogVO webLogVO) {
		return webLogMapper.selectWebLogInf(webLogVO);
	}

	/**
	 * 웹 로그 총 갯수를 조회한다.
	 * 
	 * @param webLogVO
	 */
	public int selectWebLogInfCnt(WebLogVO webLogVO) {
		return webLogMapper.selectWebLogInfCnt(webLogVO);
	}

	/**
	 * 웹 로그정보를 조회한다.
	 * 
	 * @param webLogVO
	 */
	public WebLogVO selectWebLog(WebLogVO webLogVO) {
		return webLogMapper.selectWebLog(webLogVO);
	}

	/**
	 * 웹 로그를 기록한다.
	 * 
	 * @param webLogVO
	 */
	public void logInsertWebLog(WebLogVO webLogVO) {
		try {
			webLogVO.setRequstId(webLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			LogUtil.logErrorMessage(logger, e, 20);
			throw new RuntimeException(e);
		}
		webLogMapper.logInsertWebLog(webLogVO);
	}

	/**
	 * 웹 로그정보를 요약한다.
	 * 
	 */
	public void logInsertWebLogSummary() {
		webLogMapper.logInsertWebLogSummary();
		webLogMapper.logDeleteWebLogSummary();
	}

}
