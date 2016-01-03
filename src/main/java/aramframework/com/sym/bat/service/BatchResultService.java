package aramframework.com.sym.bat.service;

import java.util.List;

/**
 * 배치결과관리에 대한 Service Interface를 정의한다.
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

public interface BatchResultService {

	/**
	 * 배치결과 목록을 조회한다.
	 * 
	 * @param batchResultVO
	 */
	public List<BatchResultVO> selectBatchResultList(BatchResultVO batchResultVO);

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchResultVO
	 */
	public int selectBatchResultListCnt(BatchResultVO batchResultVO);

	/**
	 * 배치결과을 상세조회 한다.
	 * 
	 * @param batchResultVO
	 */
	public BatchResultVO selectBatchResult(BatchResultVO batchResultVO);

	/**
	 * 배치결과을 삭제한다.
	 * 
	 * @param batchResultVO
	 */
	public void deleteBatchResult(BatchResultVO batchResultVO);

}