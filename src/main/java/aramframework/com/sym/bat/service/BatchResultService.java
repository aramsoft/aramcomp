package aramframework.com.sym.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.bat.dao.BatchResultMapper;
import aramframework.com.sym.bat.domain.BatchResultVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 배치결과관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BatchResultService extends EgovAbstractServiceImpl {

	/**
	 * 배치결과DAO
	 */
	@Autowired
	private BatchResultMapper batchResultMapper;	

	/**
	 * 배치결과의 목록을 조회 한다.
	 * 
	 * @param batchResultVO
	 */
	public List<BatchResultVO> selectBatchResultList(BatchResultVO batchResultVO) {
		return  batchResultMapper.selectBatchResultList(batchResultVO);
	}

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchResultVO
	 */
	public int selectBatchResultListCnt(BatchResultVO batchResultVO) {
		return batchResultMapper.selectBatchResultListCnt(batchResultVO);
	}

	/**
	 * 배치결과을 상세조회 한다.
	 * 
	 * @param batchResultVO
	 */
	public BatchResultVO selectBatchResult(BatchResultVO batchResultVO) {
		return batchResultMapper.selectBatchResult(batchResultVO);
	}

	/**
	 * 배치결과을 삭제한다.
	 * 
	 * @param batchResultVO
	 */
	public void deleteBatchResult(BatchResultVO batchResultVO) {
		batchResultMapper.deleteBatchResult(batchResultVO);
	}

}