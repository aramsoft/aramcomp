package aramframework.com.utl.sys.trm.service.impl;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngLogVO;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 송수신모니터링관리에 대한 DAO 클래스를 정의한다.
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

@Mapper
public interface TrsmrcvMntrngMapper {

	/**
	 * 송수신모니터링정보목록을 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public List<TrsmrcvMntrngVO> selectTrsmrcvMntrngList(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public int selectTrsmrcvMntrngListCnt(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링정보를 상세조회 한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public TrsmrcvMntrngVO selectTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링을 등록한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void insertTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링정보를 수정한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void updateTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링을 삭제한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	public void deleteTrsmrcvMntrng(TrsmrcvMntrngVO trsmrcvMntrngVO);

	/**
	 * 송수신모니터링로그정보목록을 조회한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public List<TrsmrcvMntrngLogVO> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO);

	/**
	 * 송수신모니터링로그 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO);

	/**
	 * 송수신모니터링로그정보를 상세조회 한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public TrsmrcvMntrngLogVO selectTrsmrcvMntrngLog(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO);

	/**
	 * 송수신모니터링로그를 등록한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	public void insertTrsmrcvMntrngLog(TrsmrcvMntrngLogVO trsmrcvMntrngLogVO);

	/**
	 * 연계정보목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectCntcList(SearchVO searchVO);

	/**
	 * 연계정보 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectCntcListCnt(SearchVO searchVO);

}