package aramframework.com.sym.log.clg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.log.clg.domain.LoginLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 로그인 로그관리를 위한 서비스 구현 클래스
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
public class LoginLogService extends EgovAbstractServiceImpl {

	@Autowired
	private LoginLogMapper loginLogMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService loginLogIdGnrService; 
	
	/**
	 * 로그인 로그 목록을 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public List<EgovMap> selectLoginLogInf(LoginLogVO loginLogVO) {
		return loginLogMapper.selectLoginLogInf(loginLogVO);
	}

	/**
	 * 로그인 로그 총 갯수를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public int selectLoginLogInfCnt(LoginLogVO loginLogVO) {
		return loginLogMapper.selectLoginLogInfCnt(loginLogVO);
	}

	/**
	 * 로그인 로그를 조회한다.
	 * 
	 * @param loginLogVO
	 */
	public LoginLogVO selectLoginLog(LoginLogVO loginLogVO) {
		LoginLogVO resultVo = loginLogMapper.selectLoginLog(loginLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, loginLogVO); 
		return resultVo;
	}

	/**
	 * 로그인 로그를 기록한다.
	 * 
	 * @param loginLogVO
	 */
	public void logInsertLoginLog(LoginLogVO loginLogVO) {
		try {
			loginLogVO.setLogId(loginLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		loginLogMapper.logInsertLoginLog(loginLogVO);
	}

}
