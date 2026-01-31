package aramframework.com.sym.bat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.bat.dao.BatchOpertMapper;
import aramframework.com.sym.bat.domain.BatchOpertVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 배치작업관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BatchOpertService extends EgovAbstractServiceImpl {

	/**
	 * 배치작업DAO
	 */
	@Autowired
	private BatchOpertMapper batchOpertMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService batchOpertIdGnrService; 

	/**
	 * 배치작업의 목록을 조회 한다.
	 * 
	 * @param batchOpertVO
	 */
	public List<EgovMap> selectBatchOpertList(BatchOpertVO batchOpertVO) {
		return batchOpertMapper.selectBatchOpertList(batchOpertVO);
	}

	/**
	 * 배치작업 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchOpertVO
	 */
	public int selectBatchOpertListCnt(BatchOpertVO batchOpertVO) {
		return batchOpertMapper.selectBatchOpertListCnt(batchOpertVO);
	}

	/**
	 * 배치작업을 상세조회 한다.
	 * 
	 * @param batchOpertVO
	 */
	public BatchOpertVO selectBatchOpert(BatchOpertVO batchOpertVO) {
		return batchOpertMapper.selectBatchOpert(batchOpertVO);
	}

	/**
	 * 배치작업을 등록한다.
	 * 
	 * @param batchOpertVO
	 */
	public void insertBatchOpert(BatchOpertVO batchOpertVO) {
		try {
			batchOpertVO.setBatchOpertId(batchOpertIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		batchOpertMapper.insertBatchOpert(batchOpertVO);
	}

	/**
	 * 배치작업정보를 수정한다.
	 * 
	 * @param batchOpertVO
	 */
	public void updateBatchOpert(BatchOpertVO batchOpertVO) {
		batchOpertMapper.updateBatchOpert(batchOpertVO);
	}

	/**
	 * 배치작업을 삭제한다.
	 * 
	 * @param batchOpertVO
	 */
	public void deleteBatchOpert(BatchOpertVO batchOpertVO) {
		batchOpertMapper.deleteBatchOpert(batchOpertVO);
	}

}