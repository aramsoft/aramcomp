package aramframework.com.uss.ion.ecc.service;

import java.util.List;

import aramframework.com.uss.ion.ecc.domain.EventCmpgnVO;
import aramframework.com.uss.ion.ecc.domain.TnextrlHrInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행사/이벤트/캠페인을 처리하는 Service Class 구현
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

public interface EventCmpgnService {

	/**
	 * 행사/이벤트/캠페인를(을) 목록을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public List<EgovMap> selectEventCmpgnList(EventCmpgnVO eventCmpgnVO);

	/**
	 * 행사/이벤트/캠페인를(을) 전체 갯수를 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public int selectEventCmpgnListCnt(EventCmpgnVO eventCmpgnVO);

	/**
	 * 행사/이벤트/캠페인를(을) 상세조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	public EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO);

	/**
	 * 행사/이벤트/캠페인를(을) 입력한다.
	 * 
	 * @param eventCmpgnVO
	 */
	void insertEventCmpgn(EventCmpgnVO eventCmpgnVO);

	/**
	 * 행사/이벤트/캠페인를(을) 수정한다.
	 * 
	 * @param eventCmpgnVO
	 */
	void updateEventCmpgn(EventCmpgnVO eventCmpgnVO);

	/**
	 * 행사/이벤트/캠페인를(을) 삭제한다.
	 * 
	 * @param eventCmpgnVO
	 */
	void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO);

	/**
	 * 외부인사정보를(을) 목록을 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public List<EgovMap> selectTnextrlHrInfoList(TnextrlHrInfoVO tnextrlHrInfoVO);

	/**
	 * 외부인사정보를(을) 전체 갯수를 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public int selectTnextrlHrInfoListCnt(TnextrlHrInfoVO tnextrlHrInfoVO);

	/**
	 * 외부인사정보를(을) 상세조회 조회한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	public TnextrlHrInfoVO selectTnextrlHrInfoDetail(TnextrlHrInfoVO tnextrlHrInfoVO);

	/**
	 * 외부인사정보를(을) 등록한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	void insertTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO);

	/**
	 * 외부인사정보를(을) 수정한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	void updateTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO);

	/**
	 * 외부인사정보를(을) 삭제한다.
	 * 
	 * @param tnextrlHrInfoVO
	 */
	void deleteTnextrlHrInfo(TnextrlHrInfoVO tnextrlHrInfoVO);

}
