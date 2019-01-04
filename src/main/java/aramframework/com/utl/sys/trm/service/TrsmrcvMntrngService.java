package aramframework.com.utl.sys.trm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.utl.sys.trm.dao.TrsmrcvMntrngMapper;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngLogVO;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 송수신모니터링관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class TrsmrcvMntrngService extends EgovAbstractServiceImpl {

	/**
	 * 송수신모니터링DAO
	 */
	@Autowired
	private TrsmrcvMntrngMapper trsmrcvMntrngMapper;	

	/**
	 * 송수신모니터링의 목록을 조회 한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public List<TrsmrcvMntrngVO> selectTrsmrcvMntrngList(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngList(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngListCnt(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링을 상세조회 한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public TrsmrcvMntrngVO selectTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrng(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링을 등록한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void insertTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		// 상태값을 초기치로 설정한다.
		trsmrcvMntrngVO.setMntrngSttus("01");
		trsmrcvMntrngMapper.insertTrsmrcvMntrng(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링정보를 수정한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void updateTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		trsmrcvMntrngMapper.updateTrsmrcvMntrng(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링을 삭제한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void deleteTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO) {
		trsmrcvMntrngMapper.deleteTrsmrcvMntrng(trsmrcvMntrngVO);
	}

	/**
	 * 송수신모니터링로그의 목록을 조회 한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public List<TrsmrcvMntrngLogVO> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLogList(trsmrcvMntrngLogVO);
	}

	/**
	 * 송수신모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLogListCnt(trsmrcvMntrngLogVO);
	}

	/**
	 * 송수신모니터링로그를 상세조회 한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public TrsmrcvMntrngLogVO selectTrsmrcvMntrngLog(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO) {
		return trsmrcvMntrngMapper.selectTrsmrcvMntrngLog(trsmrcvMntrngLogVO);
	}

	/**
	 * 송수신모니터링로그를 등록한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO) {
		// 상태값을 초기치로 설정한다.
		trsmrcvMntrngMapper.insertTrsmrcvMntrngLog(trsmrcvMntrngLogVO);
	}

	/**
	 * 연계정보 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCntcList(BaseVO baseVO) {
		return trsmrcvMntrngMapper.selectCntcList(baseVO);
	}

	/**
	 * 연계정보 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCntcListCnt(BaseVO baseVO) {
		return trsmrcvMntrngMapper.selectCntcListCnt(baseVO);
	}

}