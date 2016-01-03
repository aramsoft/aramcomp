package aramframework.com.sym.bat.service;

import java.util.List;

/**
 * 배치스케줄관리에 대한 Service Interface를 정의한다.
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

public interface BatchSchdulService {

	/**
	 * 배치스케줄 목록을 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	public List<BatchSchdulVO> selectBatchSchdulList(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	public int selectBatchSchdulListCnt(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치스케줄을 상세조회 한다.
	 * 
	 * @param batchSchdulVO
	 */
	public BatchSchdulVO selectBatchSchdul(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치스케줄을 등록한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void insertBatchSchdul(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치스케줄을 수정한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void updateBatchSchdul(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치스케줄을 삭제한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void deleteBatchSchdul(BatchSchdulVO batchSchdulVO);

	/**
	 * 배치결과를 등록한다.
	 * 
	 * @param batchResultVO
	 */
	public void insertBatchResult(BatchResultVO batchResultVO);

	/**
	 * 배치결과정보를 수정한다.
	 * 
	 * @param batchResultVO
	 */
	public void updateBatchResult(BatchResultVO batchResultVO);

}