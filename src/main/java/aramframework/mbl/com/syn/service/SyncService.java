package aramframework.mbl.com.syn.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 동기화 서비스에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 동기화 서비스에 대한 등록, 삭제, 조회 기능을 제공한다.
 * - 동기화 서비스 조회기능은 목록, 상세조회로 구분된다.
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

public interface SyncService {
	
	/**
	 * 동기화 서비스 글 목록 조회
	 * 
	 * @param syncVO
	 */
	public List<EgovMap> selectSyncList(SyncVO syncVO);

	/**
	 * 동기화 서비스 글 목록 총갯수
	 * 
	 * @param syncVO
	 */
	public int selectSyncListCnt(SyncVO syncVO);

	/**
	 * 동기화 서비스글 상세 조회 
	 * 
	 * @param syncVO
	 */
	public SyncVO selectSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 글 등록 
	 * 
	 * @param syncVO
	 */
	public int insertSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 글 수정
	 * 
	 * @param syncVO
	 */
	public int updateSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 글 삭제
	 * 
	 * @param syncVO
	 */
	public int deleteSync(SyncVO syncVO);

	/**
	 * 동기화 서비스 글 '동기화'를 실행한다.
	 * 
	 * @param syncVO
	 */
	public int executeSync(SyncVO syncVO, String localData);
	
}
