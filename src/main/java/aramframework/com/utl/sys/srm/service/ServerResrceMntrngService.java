package aramframework.com.utl.sys.srm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.srm.domain.ServerResrceMntrngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 - 서버자원모니터링에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
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
public class ServerResrceMntrngService extends EgovAbstractServiceImpl {

	@Autowired
	private ServerResrceMntrngMapper serverResrceMntrngMapper;

	/** ID Generation */
	@Autowired
	private EgovIdGnrService serverResrceMntrngLogIdGnrService; 

	/**
	 * 서버자원모티너링 대상서버의 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public List<ServerResrceMntrngVO> selectMntrngServerList(ServerResrceMntrngVO serverResrceMntrngVO) {
		return serverResrceMntrngMapper.selectMntrngServerList(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모티너링 대상서버 목록 총 갯수를 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public int selectMntrngServerListCnt(ServerResrceMntrngVO serverResrceMntrngVO) {
		return serverResrceMntrngMapper.selectMntrngServerListCnt(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public List<ServerResrceMntrngVO> selectServerResrceMntrngList(ServerResrceMntrngVO serverResrceMntrngVO) {
		return serverResrceMntrngMapper.selectServerResrceMntrngList(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링의 로그정보 목록 총 갯수를 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public int selectServerResrceMntrngListCnt(ServerResrceMntrngVO serverResrceMntrngVO) {
		return serverResrceMntrngMapper.selectServerResrceMntrngListCnt(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public ServerResrceMntrngVO selectServerResrceMntrng(ServerResrceMntrngVO serverResrceMntrngVO) {
		ServerResrceMntrngVO resultVo = serverResrceMntrngMapper.selectServerResrceMntrng(serverResrceMntrngVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, serverResrceMntrngVO); 
		return resultVo;
	}

	/**
	 * 서버자원모니터링 로그정보를 신규로 등록한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	public void insertServerResrceMntrng(ServerResrceMntrngVO serverResrceMntrngVO) {
		try {
			serverResrceMntrngVO.setLogId(serverResrceMntrngLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		serverResrceMntrngMapper.insertServerResrceMntrng(serverResrceMntrngVO);
	}

}