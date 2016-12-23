package aramframework.com.sym.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.bat.dao.BatchResultMapper;
import aramframework.com.sym.bat.dao.BatchSchdulMapper;
import aramframework.com.sym.bat.domain.BatchResultVO;
import aramframework.com.sym.bat.domain.BatchSchdulVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 배치스케줄관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BatchSchdulService extends EgovAbstractServiceImpl {

	@Autowired
	private BatchSchdulMapper batchSchdulMapper;	

	@Autowired
	private BatchResultMapper batchResultMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService batchSchdulIdGnrService; 

	/**
	 * 배치스케줄의 목록을 조회 한다.
	 * 
	 * @param batchSchdulVO
	 */
	public List<BatchSchdulVO> selectBatchSchdulList(BatchSchdulVO batchSchdulVO) {
		return  batchSchdulMapper.selectBatchSchdulList(batchSchdulVO);
	}

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	public int selectBatchSchdulListCnt(BatchSchdulVO batchSchdulVO) {
		return batchSchdulMapper.selectBatchSchdulListCnt(batchSchdulVO);
	}

	/**
	 * 배치스케줄을 상세조회 한다.
	 * 
	 * @param batchSchdulVO
	 */
	public BatchSchdulVO selectBatchSchdul(BatchSchdulVO batchSchdulVO) {
		BatchSchdulVO resultVo = batchSchdulMapper.selectBatchSchdul(batchSchdulVO);
		// searchVO 이전 
		resultVo.setSearchVO(batchSchdulVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 배치스케줄을 등록한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void insertBatchSchdul(BatchSchdulVO batchSchdulVO) {
		try {
			batchSchdulVO.setBatchSchdulId(batchSchdulIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		batchSchdulMapper.insertBatchSchdul(batchSchdulVO);
	}

	/**
	 * 배치스케줄정보를 수정한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void updateBatchSchdul(BatchSchdulVO batchSchdulVO) {
		batchSchdulMapper.updateBatchSchdul(batchSchdulVO);
	}

	/**
	 * 배치스케줄을 삭제한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void deleteBatchSchdul(BatchSchdulVO batchSchdulVO) {
		batchSchdulMapper.deleteBatchSchdul(batchSchdulVO);
	}

	/**
	 * 배치결과를 등록한다.
	 * 
	 * @param batchResultVO
	 */
	public void insertBatchResult(BatchResultVO batchResultVO) {
		batchResultMapper.insertBatchResult(batchResultVO);
	}

	/**
	 * 배치결과정보를 수정한다.
	 * 
	 * @param batchResultVO
	 */
	public void updateBatchResult(BatchResultVO batchResultVO) {
		batchResultMapper.updateBatchResult(batchResultVO);
	}

}