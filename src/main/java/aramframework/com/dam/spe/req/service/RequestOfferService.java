package aramframework.com.dam.spe.req.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 지식정보제공/지식정보요청를 처리하는 Service Class 구현
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

public interface RequestOfferService {

	/**
	 * 삭제시 하위 답변 건수를 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferDelCnt(String ansParents); 

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * 
	 * @param speId
	 */
	public boolean selectRequestOfferSpeCheck(String speId);

	/**
	 * 지식정보제공/지식정보요청 목록을 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public List<EgovMap> selectRequestOfferList(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferListCnt(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 상세조회 한다.
	 * 
	 * @param requestOfferVO
	 */
	public RequestOfferVO selectRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 등록한다.
	 * 
	 * @param requestOfferVO
	 */
	void insertRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 등록한다.
	 * 
	 * @param requestOfferVO
	 */
	void replyRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 수정한다.
	 * 
	 * @param requestOfferVO
	 */
	void updateRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 삭제한다.
	 * 
	 * @param requestOfferVO
	 */
	void deleteRequestOffer(RequestOfferVO RequestOfferVO);

}
