package aramframework.com.cop.com.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 승인정보를 관리하기 위한 서비스 인테페이스 클래스
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

public interface ConfirmService {

	/**
	 * 승인(탈퇴)요청에 대한 목록을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public List<EgovMap> selectConfirmRequestList(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 목록 총갯수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int selectConfirmRequestListCnt(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 상세내용을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public ConfirmHistoryVO selectSingleConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 등록을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void insertConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 승인(탈퇴)요청에 대한 확인을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public void updateConfirmRequest(ConfirmHistoryVO confirmHistoryVO);

	/**
	 * 현재 승인 요청된 건수를 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	public int countConfirmRequest(ConfirmHistoryVO confirmHistoryVO);
	
}
