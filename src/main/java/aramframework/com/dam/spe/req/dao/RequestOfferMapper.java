package aramframework.com.dam.spe.req.dao;

import java.util.List;

import aramframework.com.dam.spe.req.domain.RequestOfferVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 지식정보제공/지식정보요청를 처리하는 Dao Class 구현
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface RequestOfferMapper {

	/**
	 * 삭제시 하위 답변 건수를 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferDelCnt(String ansParents);

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	public int selectRequestOfferSpeCnt(String speId);

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
	public void insertRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 댓글을 등록한다.
	 * 
	 * @param requestOfferVO
	 */
	public void replyRequestOffer(RequestOfferVO RequestOfferVO);
	public int getParentThreadNo(RequestOfferVO RequestOfferVO);
	public void updateThreadNo(RequestOfferVO RequestOfferVO);
	public void updateOtherThreadNo(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 수정한다.
	 * 
	 * @param requestOfferVO
	 */
	public void updateRequestOffer(RequestOfferVO RequestOfferVO);

	/**
	 * 지식정보제공/지식정보요청를(을) 삭제한다.
	 * 
	 * @param requestOfferVO
	 */
	public void deleteRequestOffer(RequestOfferVO RequestOfferVO);

}
