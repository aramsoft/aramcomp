package aramframework.com.sym.bat.dao;

import java.util.List;

import aramframework.com.sym.bat.domain.BatchOpertVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 배치작업관리에 대한 DAO 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BatchOpertMapper {

	/**
	 * 배치작업정보목록을 조회한다.
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
	 * 배치작업정보를 상세조회 한다.
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
	 * 배치작업정보를 수정한다.
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