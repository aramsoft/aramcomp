package aramframework.com.sym.bat.service;

import java.util.List;

import aramframework.com.sym.bat.domain.BatchOpertVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 배치작업관리에 대한 Service Interface를 정의한다.
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

public interface BatchOpertService {

	/**
	 * 배치작업 목록을 조회한다.
	 * 
	 * @param batchOpertVO
	 */
	public List<EgovMap> selectBatchOpertList(BatchOpertVO batchOpertVO);

	/**
	 * 배치작업 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchOpertVO
	 */
	public int selectBatchOpertListCnt(BatchOpertVO batchOpertVO);

	/**
	 * 배치작업을 상세조회 한다.
	 * 
	 * @param batchOpertVO
	 */
	public BatchOpertVO selectBatchOpert(BatchOpertVO batchOpertVO);

	/**
	 * 배치작업을 등록한다.
	 * 
	 * @param batchOpertVO
	 */
	public void insertBatchOpert(BatchOpertVO batchOpertVO);

	/**
	 * 배치작업을 수정한다.
	 * 
	 * @param batchOpertVO
	 */
	public void updateBatchOpert(BatchOpertVO batchOpertVO);

	/**
	 * 배치작업을 삭제한다.
	 * 
	 * @param batchOpertVO
	 */
	public void deleteBatchOpert(BatchOpertVO batchOpertVO);

}