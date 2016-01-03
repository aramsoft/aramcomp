package aramframework.com.utl.sys.pxy.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.pxy.service.ProxySvcService;
import aramframework.com.utl.sys.pxy.service.ProxySvcLogVO;
import aramframework.com.utl.sys.pxy.service.ProxySvcVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 - 프록시서비스정보에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
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

@Service("proxySvcService")
public class ProxySvcServiceImpl extends EgovAbstractServiceImpl implements ProxySvcService {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	/** ID Generation */
	@Resource(name = "proxySvcIdGnrService")
	private EgovIdGnrService idgenService;

	/** ID Generation */
	@Resource(name = "proxyLogIdGnrService")
	private EgovIdGnrService idgenServiceLog;

	@Resource(name = "proxySvcMapper")
	private ProxySvcMapper proxySvcMapper;
	
	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public List<ProxySvcVO> selectProxySvcList(ProxySvcVO proxySvcVO) {
		return proxySvcMapper.selectProxySvcList(proxySvcVO);
	}

	/**
	 * 프록시서비스 목록 총 갯수를 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public int selectProxySvcListCnt(ProxySvcVO proxySvcVO) {
		return proxySvcMapper.selectProxySvcListCnt(proxySvcVO);
	}

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	public ProxySvcVO selectProxySvc(ProxySvcVO proxySvcVO) {
		ProxySvcVO resultVo = proxySvcMapper.selectProxySvc(proxySvcVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, proxySvcVO); 
		return resultVo;
	}

	/**
	 * 프록시서비스를 신규로 등록한다.
	 * 
	 * @param ProxySvcVO
	 */
	public ProxySvcVO insertProxySvc(ProxySvcVO proxySvcVO) {
		try {
			proxySvcVO.setProxyId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		proxySvcMapper.insertProxySvc(proxySvcVO);

		if (proxySvcVO.getSvcSttus().equals("01")) {
			proxySvcVO.setStrPreSvcSttus("02");
//			runProxyServer(proxySvcVO);
		}
		return proxySvcMapper.selectProxySvc(proxySvcVO);
	}

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 * 
	 * @param ProxySvcVO
	 */
	public void updateProxySvc(ProxySvcVO proxySvcVO) {
		proxySvcMapper.updateProxySvc(proxySvcVO);
//		runProxyServer(proxySvcVO);
	}

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 * 
	 * @param ProxySvcVO
	 */
	public void deleteProxySvc(ProxySvcVO proxySvcVO) {
		proxySvcMapper.deleteProxySvc(proxySvcVO);
	}

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public List<ProxySvcLogVO> selectProxySvcLogList(ProxySvcLogVO proxySvcLogVO) {
		return proxySvcMapper.selectProxySvcLogList(proxySvcLogVO);
	}

	/**
	 * 프록시로그 목록 총 갯수를 조회한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public int selectProxySvcLogListCnt(ProxySvcLogVO proxySvcLogVO) {
		return proxySvcMapper.selectProxySvcLogListCnt(proxySvcLogVO);
	}

	/**
	 * 프록시로그를 생성한다.
	 * 
	 * @param proxySvcLogVO
	 */
	public void insertProxySvcLog(ProxySvcLogVO proxySvcLogVO) {
		proxySvcMapper.insertProxySvcLog(proxySvcLogVO);
	}

	/**
	 * 프록시서버를 실행한다.
	 * 
	 * @param proxySvcVO
	 */
	public void runProxyServer(ProxySvcVO proxySvcVO) {
		try {
			if (!proxySvcVO.getStrPreSvcSttus().equals("01") 
					&& proxySvcVO.getSvcSttus().equals("01")) {
				ProxyServer proxyServer = new ProxyServer(proxySvcVO.getSvcIp(), proxySvcVO.getProxyIp(), Integer.parseInt(proxySvcVO.getProxyPort()),
						Integer.parseInt(proxySvcVO.getSvcPort()), proxySvcVO.getProxyId(), proxySvcMapper, idgenServiceLog);
				proxyServer.start();
			} else if (proxySvcVO.getStrPreSvcSttus().equals("01") 
					&& !proxySvcVO.getSvcSttus().equals("01")) {
				ProxyCommand proxyCommand = new ProxyCommand(proxySvcVO.getProxyIp(), Integer.parseInt(proxySvcVO.getProxyPort()));
				proxyCommand.runCommand("stop");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}