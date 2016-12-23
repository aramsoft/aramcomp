package aramframework.com.utl.sys.nsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.utl.sys.nsm.dao.NtwrkSvcMntrngMapper;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngLogVO;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngVO;
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
 */
@Service
public class NtwrkSvcMntrngService extends EgovAbstractServiceImpl {

	@Autowired
	private NtwrkSvcMntrngMapper ntwrkSvcMntrngMapper;	

	@Autowired
	private EgovIdGnrService ntwrkSvcMntrngLogIdGnrService;
 
	/**
	 * 네트워크서비스 모니터링대상 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 * @return					List
	 */
	public List<NtwrkSvcMntrngVO> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 * @return					총 갯수
	 */
	public int selectNtwrkSvcMntrngListCnt(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngListCnt(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 * @return					NtwrkSvcMntrngVO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		NtwrkSvcMntrngVO resultVo = ntwrkSvcMntrngMapper.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);
		// searchVO 이전 
		resultVo.setSearchVO(ntwrkSvcMntrngVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 네트워크서비스 모니터링대상을 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngMapper.updateNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 등록한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngVO.setMntrngSttus("01");
		ntwrkSvcMntrngMapper.insertNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 등록하기 위한 중복 조회를 수행한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 * @return					int
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngCheck(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링대상을 삭제한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {
		ntwrkSvcMntrngMapper.deleteNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO	NtwrkSvcMntrngVO
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
	 * @param ntwrkSvcMntrngLogVO	NtwrkSvcMntrngLogVO
	 * @return						List
	 */
	public List<NtwrkSvcMntrngLogVO> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLogList(ntwrkSvcMntrngLogVO);
	}

	/**
	 * 네트워크서비스 모니터링대상 로그 총 갯수를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO	NtwrkSvcMntrngLogVO
	 * @return						총 갯수
	 */
	public int selectNtwrkSvcMntrngLogListCnt(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		return ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLogListCnt(ntwrkSvcMntrngLogVO);
	}

	/**
	 * 네트워크서비스 모니터링 로그를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO	NtwrkSvcMntrngLogVO
	 * @return						NtwrkSvcMntrngLogVO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		NtwrkSvcMntrngLogVO resultVo = ntwrkSvcMntrngMapper.selectNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
		// searchVO 이전 
		resultVo.setSearchVO(ntwrkSvcMntrngLogVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 네트워크서비스 모니터링 로그를 등록한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO	NtwrkSvcMntrngLogVO
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) {
		ntwrkSvcMntrngMapper.insertNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
	}
	
}