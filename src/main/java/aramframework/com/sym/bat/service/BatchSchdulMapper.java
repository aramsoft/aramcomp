package aramframework.com.sym.bat.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import aramframework.com.sym.bat.domain.BatchSchdulDfkVO;
import aramframework.com.sym.bat.domain.BatchSchdulVO;

/**
 * 배치스케줄관리에 대한 DAO 클래스를 정의한다.
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

@Repository
public class BatchSchdulMapper extends EgovAbstractMapper {

	final static String NAMESPACE = "aramframework.com.sym.bat.service.BatchSchdulMapper";
	/**
	 * 배치스케줄정보목록을 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	public List<BatchSchdulVO> selectBatchSchdulList(BatchSchdulVO batchSchdulVO) {
		List<BatchSchdulVO> resultList = selectList(NAMESPACE+".selectBatchSchdulList", batchSchdulVO);

		for (int i = 0; i < resultList.size(); i++) {
			BatchSchdulVO result = (BatchSchdulVO) resultList.get(i);
			// 스케줄요일정보를 가져온다.
			List<BatchSchdulDfkVO> dfkSeList = selectList(NAMESPACE+".selectBatchSchdulDfkList", result.getBatchSchdulId());
			String[] dfkSes = new String[dfkSeList.size()];
			for (int j = 0; j < dfkSeList.size(); j++) {
				dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
			}
			result.setExecutSchdulDfkSes(dfkSes);
			// 화면표시용 실행스케줄 속성을 만든다.
			result.makeExecutSchdul(dfkSeList);
		}
		return resultList;
	}

	/**
	 * 배치스케줄 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	public int selectBatchSchdulListCnt(BatchSchdulVO batchSchdulVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectBatchSchdulListCnt", batchSchdulVO);
	}

	/**
	 * 배치스케줄정보를 상세조회 한다.
	 * 
	 * @param batchSchdulVO
	 */
	public BatchSchdulVO selectBatchSchdul(BatchSchdulVO batchSchdulVO) {
		BatchSchdulVO result = (BatchSchdulVO) selectOne(NAMESPACE+".selectBatchSchdul", batchSchdulVO);

		// 스케줄요일정보를 가져온다.
		List<BatchSchdulDfkVO> dfkSeList = selectList(NAMESPACE+".selectBatchSchdulDfkList", batchSchdulVO.getBatchSchdulId());
		String[] dfkSes = new String[dfkSeList.size()];
		for (int j = 0; j < dfkSeList.size(); j++) {
			dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
		}
		result.setExecutSchdulDfkSes(dfkSes);
		// 화면표시용 실행스케줄 속성을 만든다.
		result.makeExecutSchdul(dfkSeList);

		return result;
	}

	/**
	 * 배치스케줄을 등록한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void insertBatchSchdul(BatchSchdulVO batchSchdulVO) {
		// master 테이블 인서트
		insert(NAMESPACE+".insertBatchSchdul", batchSchdulVO);
		// slave 테이블 인서트
		if (batchSchdulVO.getExecutSchdulDfkSes() != null && batchSchdulVO.getExecutSchdulDfkSes().length != 0) {
			String batchSchdulId = batchSchdulVO.getBatchSchdulId();
			String[] dfkSes = batchSchdulVO.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BatchSchdulDfkVO batchSchdulDfkVO = new BatchSchdulDfkVO();
				batchSchdulDfkVO.setBatchSchdulId(batchSchdulId);
				batchSchdulDfkVO.setExecutSchdulDfkSe(dfkSes[i]);
				insert(NAMESPACE+".insertBatchSchdulDfk", batchSchdulDfkVO);
			}
		}
	}

	/**
	 * 배치스케줄정보를 수정한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void updateBatchSchdul(BatchSchdulVO batchSchdulVO) {
		update(NAMESPACE+".updateBatchSchdul", batchSchdulVO);
		// slave 테이블 삭제
		delete(NAMESPACE+".deleteBatchSchdulDfk", batchSchdulVO.getBatchSchdulId());
		// slave 테이블 인서트
		if (batchSchdulVO.getExecutSchdulDfkSes() != null && batchSchdulVO.getExecutSchdulDfkSes().length != 0) {
			String batchSchdulId = batchSchdulVO.getBatchSchdulId();
			String[] dfkSes = batchSchdulVO.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BatchSchdulDfkVO batchSchdulDfkVO = new BatchSchdulDfkVO();
				batchSchdulDfkVO.setBatchSchdulId(batchSchdulId);
				batchSchdulDfkVO.setExecutSchdulDfkSe(dfkSes[i]);
				insert(NAMESPACE+".insertBatchSchdulDfk", batchSchdulDfkVO);
			}
		}
	}

	/**
	 * 배치스케줄을 삭제한다.
	 * 
	 * @param batchSchdulVO
	 */
	public void deleteBatchSchdul(BatchSchdulVO batchSchdulVO) {
		// slave 테이블 삭제
		delete(NAMESPACE+".deleteBatchSchdulDfk", batchSchdulVO.getBatchSchdulId());
		// master 테이블 삭제
		delete(NAMESPACE+".deleteBatchSchdul", batchSchdulVO);
	}

}