package aramframework.com.ssi.syi.ims.service.impl;

import java.util.List;

import aramframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import aramframework.com.ssi.syi.ims.service.CntcMessageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 연계메시지에 대한 데이터 접근 클래스를 정의한다
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

@Mapper("cntcMessageMapper")
public interface CntcMessageMapper {

	/**
	 * 연계메시지 목록을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public List<CntcMessageVO> selectCntcMessageList(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public int selectCntcMessageListCnt(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * 
	 * @param cntcMessageVO
	 */
	public CntcMessageVO selectCntcMessageDetail(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 등록한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void insertCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 수정한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void updateCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지를 삭제한다.
	 * 
	 * @param cntcMessageVO
	 */
	public void deleteCntcMessage(CntcMessageVO cntcMessageVO);

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public List<EgovMap> selectCntcMessageItemList(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지항목 총 갯수를 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public int selectCntcMessageItemListCnt(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public CntcMessageItemVO selectCntcMessageItemDetail(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 등록한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void insertCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 수정한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void updateCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

	/**
	 * 연계메시지 항목을 삭제한다.
	 * 
	 * @param cntcMessageItemVO
	 */
	public void deleteCntcMessageItem(CntcMessageItemVO cntcMessageItemVO);

}
