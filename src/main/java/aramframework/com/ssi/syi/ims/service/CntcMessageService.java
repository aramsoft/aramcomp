package aramframework.com.ssi.syi.ims.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계메시지에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CntcMessageService {

	/**
	 * 연계메시지 목록을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	List<CntcMessageVO> selectCntcMessageList(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	int selectCntcMessageListCnt(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	CntcMessageVO selectCntcMessageDetail(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 등록한다.
	 * 
	 * @param cntcMessageVO
	 */
	void insertCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 수정한다.
	 * 
	 * @param cntcMessageVO
	 */
	void updateCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 삭제한다.
	 * 
	 * @param cntcMessageVO
	 */
	void deleteCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	List<EgovMap> selectCntcMessageItemList(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지항목 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	int selectCntcMessageItemListCnt(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	CntcMessageItemVO selectCntcMessageItemDetail(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 등록한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	void insertCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 수정한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	void updateCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 삭제한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	void deleteCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

}
