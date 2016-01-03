package aramframework.mbl.com.rns.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 실시간 공지 서비스에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 실시간 공지 서비스에 대한 등록, 삭제, 조회 기능을 제공한다.
 * - 실시간 공지 서비스 조회기능은 목록, 상세조회로 구분된다.
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

public interface RealtimeNoticeService {
	
	/**
	 * [관리자] 실시간공지서비스 목록 조회
	 * 
	 * @param searchVO
	 * @return
	 */
	public List<EgovMap> selectRealtimeNoticeList(RealtimeNoticeVO realtimeNoticeVO);
	
	/**
	 * [관리자/사용자] 실시간공지서비스 상세조회 / 실시간공지 서비스 내용 
	 * 
	 * @param searchVO
	 * @return
	 */
	public RealtimeNoticeVO selectRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO);
	
	/**
	 * [관리자] 실시간공지서비스 등록
	 * 
	 * @param realtimeNotice
	 */
	public int insertRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO);
	
	/**
	 * [관리자] 실시간공지서비스 삭제
	 * 
	 * @param searchVO
	 */
	public int deleteRealtimeNotice(RealtimeNoticeVO realtimeNoticeVO);

	/**
	 * 실시간공지서비스 메시지 조회
	 * 
	 * @param searchVO
	 * @return
	 */
	public RealtimeNoticeVO selectRealtimeNoticeMsg();
	
}
