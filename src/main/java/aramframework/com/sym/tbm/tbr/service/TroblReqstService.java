package aramframework.com.sym.tbm.tbr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 장애신청정보에 대한 Service Interface를 정의한다
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

public interface TroblReqstService {

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public List<EgovMap> selectTroblReqstList(TroblReqstVO troblReqstVO);

	/**
	 * 장애요청목록 총 갯수를 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public int selectTroblReqstListCnt(TroblReqstVO troblReqstVO);

	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO);

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * 
	 * @param troblReqstVO
	 */
	public void insertTroblReqst(TroblReqstVO troblReqstVO);

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * 
	 * @param troblReqstVO
	 */
	public void updateTroblReqst(TroblReqstVO troblReqstVO);

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * 
	 * @param troblReqstVO
	 */
	public void deleteTroblReqst(TroblReqstVO troblReqstVO);

	/**
	 * 장애처리를 요청한다.
	 * 
	 * @param troblReqstVO
	 */
	public void requstTroblReqst(TroblReqstVO troblReqstVO);

}