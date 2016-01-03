package aramframework.com.sym.sym.srv.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.sym.srv.service.ServerService;
import aramframework.com.sym.sym.srv.service.ServerEqpmnRelateVO;
import aramframework.com.sym.sym.srv.service.ServerEqpmnVO;
import aramframework.com.sym.sym.srv.service.ServerVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 서버정보에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("serverService")
public class ServerServiceImpl extends EgovAbstractServiceImpl implements ServerService {

	@Resource(name = "serverMapper")
	private ServerMapper serverMapper;
	
	/** ID Generation */
	@Resource(name = "serverEqpmnIdGnrService")
	private EgovIdGnrService idgenServiceServerEqpmn;

	/** ID Generation */
	@Resource(name = "serverIdGnrService")
	private EgovIdGnrService idegenServiceServer;

	/**
	 * 서버장비를 관리하기 위해 등록된 서버장비목록을 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public List<EgovMap> selectServerEqpmnList(ServerEqpmnVO serverEqpmnVO) {
		return serverMapper.selectServerEqpmnList(serverEqpmnVO);
	}

	/**
	 * 서버장비목록 총 갯수를 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public int selectServerEqpmnListCnt(ServerEqpmnVO serverEqpmnVO) {
		return serverMapper.selectServerEqpmnListCnt(serverEqpmnVO);
	}

	/**
	 * 등록된 서버장비의 상세정보를 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public ServerEqpmnVO selectServerEqpmn(ServerEqpmnVO serverEqpmnVO) {
		ServerEqpmnVO resultVo = serverMapper.selectServerEqpmn(serverEqpmnVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, serverEqpmnVO); 
		return resultVo;
	}

	/**
	 * 서버장비정보를 신규로 등록한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void insertServerEqpmn(ServerEqpmnVO serverEqpmnVO) {
		try {
			serverEqpmnVO.setServerEqpmnId(idgenServiceServerEqpmn.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		serverMapper.insertServerEqpmn(serverEqpmnVO);
	}

	/**
	 * 기 등록된 서버장비정보를 수정한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void updateServerEqpmn(ServerEqpmnVO serverEqpmnVO) {
		serverMapper.updateServerEqpmn(serverEqpmnVO);
	}

	/**
	 * 기 등록된 서버장비정보를 삭제한다.
	 * 
	 * @param serverEqpmnVO
	 */
	public void deleteServerEqpmn(ServerEqpmnVO serverEqpmnVO) {
		serverMapper.deleteServerEqpmn(serverEqpmnVO);
	}

	/**
	 * 서버정보를 관리하기 위해 등록된 서버목록을 조회한다.
	 * 
	 * @param serverVO
	 */
	public List<EgovMap> selectServerList(ServerVO serverVO) {
		return serverMapper.selectServerList(serverVO);
	}

	/**
	 * 서버목록 총 갯수를 조회한다.
	 * 
	 * @param serverVO
	 */
	public int selectServerListCnt(ServerVO serverVO) {
		return serverMapper.selectServerListCnt(serverVO);
	}

	/**
	 * 등록된 서버의 상세정보를 조회한다.
	 * 
	 * @param serverVO
	 */
	public ServerVO selectServer(ServerVO serverVO) {
		ServerVO resultVo = serverMapper.selectServer(serverVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, serverVO); 
		return resultVo;
	}

	/**
	 * 등록된 서버의 상세정보중 서버장비목록을 조회한다.
	 * 
	 * @param serverVO
	 */
	public List<EgovMap> selectServerEqpmnRelateDetail(ServerVO serverVO) {
		return serverMapper.selectServerEqpmnRelateDetail(serverVO);
	}

	/**
	 * 서버에 등록된 서버장비목록의 카운트를 조회한다.
	 * 
	 * @param serverVO
	 */
	public int selectServerEqpmnRelateDetailTotCnt(ServerVO serverVO) {
		return serverMapper.selectServerEqpmnRelateDetailTotCnt(serverVO);
	}

	/**
	 * 서버정보를 신규로 등록한다.
	 * 
	 * @param serverVO
	 */
	public void insertServer(ServerVO serverVO) {
		try {
			serverVO.setServerId(idegenServiceServer.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		serverMapper.insertServer(serverVO);
	}

	/**
	 * 기 등록된 서버정보를 수정한다.
	 * 
	 * @param serverVO
	 */
	public void updateServer(ServerVO serverVO) {
		serverMapper.updateServer(serverVO);
	}

	/**
	 * 기 등록된 서버정보를 삭제한다.
	 * 
	 * @param serverVO
	 */
	public void deleteServer(ServerVO serverVO) {
		serverMapper.deleteServer(serverVO);
	}

	/**
	 * 서버장비관계정보를 관리하기 위해 대상 서버목록을 조회한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public List<EgovMap> selectServerEqpmnRelateList(ServerEqpmnRelateVO serverEqpmnRelateVO) {
		return serverMapper.selectServerEqpmnRelateList(serverEqpmnRelateVO);
	}

	/**
	 * 서버장비관계 대상 목록 총 갯수를 조회한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public int selectServerEqpmnRelateListCnt(ServerEqpmnRelateVO serverEqpmnRelateVO) {
		return serverMapper.selectServerEqpmnRelateListCnt(serverEqpmnRelateVO);
	}

	/**
	 * 서버장비관계정보를 신규로 등록한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public void insertServerEqpmnRelate(ServerEqpmnRelateVO serverEqpmnRelateVO) {
		serverMapper.insertServerEqpmnRelate(serverEqpmnRelateVO);
	}

	/**
	 * 기 등록된 서버장비관계정보를 삭제한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	public void deleteServerEqpmnRelate(ServerEqpmnRelateVO serverEqpmnRelateVO) {
		serverMapper.deleteServerEqpmnRelate(serverEqpmnRelateVO);
	}

}