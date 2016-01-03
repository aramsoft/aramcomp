package aramframework.mbl.com.syn.service.impl;

import java.util.List;

import aramframework.mbl.com.syn.service.SyncVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 동기화 서비스에 대한 DB상의 접근, 변경을 처리한다.
 * 
 * 상세내용
 * - 동기화 서비스에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 동기화 서비스에 대한 조회기능은 목록, 상세조회로 구분된다.
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

@Mapper("syncMapper")
public interface SyncMapper {
	
	/**
	 * 동기화 서비스 신규 일련번호 조회
	 * 
	 */
	public int selectSyncNewSn() throws Exception;
	
	/**
	 * 동기화 서비스 목록 조회
	 * 
	 * @param syncVO
	 */
	public List<EgovMap> selectSyncList(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 글 총 갯수 조회
	 * 
	 * @param syncVO
	 */
	public int selectSyncListCnt(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 상세 조회
	 * 
	 * @param syncVO
	 */
	public SyncVO selectSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 등록
	 * 
	 * @param syncVO
	 */
	public int insertSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 수정
	 * 
	 * @param syncVO
	 */
	public int updateSync(SyncVO syncVO);
	
	/**
	 * 동기화 서비스 삭제
	 * 
	 * @param syncVO
	 */
	public int deleteSync(SyncVO syncVO);

}
