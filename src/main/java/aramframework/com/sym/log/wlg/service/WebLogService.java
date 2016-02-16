package aramframework.com.sym.log.wlg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.log.wlg.dao.WebLogMapper;
import aramframework.com.sym.log.wlg.domain.WebLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 웹 로그관리를 위한 서비스 구현 클래스
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

@Service
public class WebLogService extends EgovAbstractServiceImpl {

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
		WebLogVO resultVo = webLogMapper.selectWebLog(webLogVO);
		// searchVO 이전 
		resultVo.setSearchVO(webLogVO.getSearchVO()); 
		return resultVo;
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
