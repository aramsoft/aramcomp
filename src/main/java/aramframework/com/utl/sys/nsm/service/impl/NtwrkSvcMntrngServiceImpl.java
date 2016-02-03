package aramframework.com.utl.sys.nsm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngLogVO;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngVO;
import aramframework.com.utl.sys.nsm.service.NtwrkSvcMntrngService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요 네트워크서비스 모니터링대상에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 네트워크서비스 모니터링대상의 조회기능은  목록조회, 상세조회로 구분된다.
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

@Service("ntwrkSvcMntrngService")
public class NtwrkSvcMntrngServiceImpl extends EgovAbstractServiceImpl implements NtwrkSvcMntrngService {

	@Autowired
	private NtwrkSvcMntrngMapper ntwrkSvcMntrngMapper;	

	@Autowired
	private EgovIdGnrService ntwrkSvcMntrngLogIdGnrService;
 
	/**
	 * 네트워크서비스 모니터링대상 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public List<NtwrkSvcMntrngVO> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngListCnt(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngListCnt(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		NtwrkSvcMntrngVO resultVo = ntwrkSvcMntrngMapper.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, ntwrkSvcMntrngVO); 
		return resultVo;
	}

	/**
	 * 네트워크서비스 모니터링대상을 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngMapper.updateNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 등록한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngVO.setMntrngSttus("01");
		ntwrkSvcMntrngMapper.insertNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 등록하기 위한 중복 조회를 수행한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngCheck(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 삭제한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngMapper.deleteNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngMapper.updateNtwrkSvcMntrngSttus(ntwrkSvcMntrngVO);

		NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO = new NtwrkSvcMntrngLogVO();
		try {
			ntwrkSvcMntrngLogVO.setLogId(ntwrkSvcMntrngLogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		ntwrkSvcMntrngLogVO.setSysIp(ntwrkSvcMntrngVO.getSysIp());
		ntwrkSvcMntrngLogVO.setSysPort(ntwrkSvcMntrngVO.getSysPort());
		ntwrkSvcMntrngLogVO.setSysNm(ntwrkSvcMntrngVO.getSysNm());
		ntwrkSvcMntrngLogVO.setMntrngSttus(ntwrkSvcMntrngVO.getMntrngSttus());
		ntwrkSvcMntrngLogVO.setLogInfo(ntwrkSvcMntrngVO.getLogInfo());
		ntwrkSvcMntrngLogVO.setCreatDt(ntwrkSvcMntrngVO.getCreatDt());
		ntwrkSvcMntrngLogVO.setFrstRegisterId(ntwrkSvcMntrngVO.getLastUpdusrId());
		insertNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
	}

	/**
	 * 네트워크서비스 모니터링대상 로그 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public List<NtwrkSvcMntrngLogVO> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLogList(ntwrkSvcMntrngLogVO);
	}

	/**
	 * 네트워크서비스 모니터링대상 로그 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public int selectNtwrkSvcMntrngLogListCnt(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLogListCnt(ntwrkSvcMntrngLogVO);
	}

	/**
	 * 네트워크서비스 모니터링 로그를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		NtwrkSvcMntrngLogVO resultVo = ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, ntwrkSvcMntrngLogVO); 
		return resultVo;
	}

	/**
	 * 네트워크서비스 모니터링 로그를 등록한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		ntwrkSvcMntrngMapper.insertNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
	}
	
}