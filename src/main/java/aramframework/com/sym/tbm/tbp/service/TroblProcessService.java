package aramframework.com.sym.tbm.tbp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.tbm.tbp.dao.TroblProcessMapper;
import aramframework.com.sym.tbm.tbp.domain.TroblProcessVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 장애처리결과 관리정보에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class TroblProcessService extends EgovAbstractServiceImpl {

	@Autowired
	private TroblProcessMapper troblProcessMapper;	

	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * 
	 * @param troblProcessVO 
	 */
	public List<EgovMap> selectTroblProcessList(TroblProcessVO troblProcessVO) {
		return troblProcessMapper.selectTroblProcessList(troblProcessVO);
	}

	/**
	 * 장애처리목록 총 갯수를 조회한다.
	 * 
	 * @param troblProcessVO
	 */
	public int selectTroblProcessListCnt(TroblProcessVO troblProcessVO) {
		return troblProcessMapper.selectTroblProcessListCnt(troblProcessVO);
	}

	/**
	 * 등록된 장애처리의 상세정보를 조회한다.
	 * 
	 * @param troblProcessVO
	 */
	public TroblProcessVO selectTroblProcess(TroblProcessVO troblProcessVO) {
		TroblProcessVO resultVo = troblProcessMapper.selectTroblProcess(troblProcessVO);
		// searchVO 이전 
		resultVo.setSearchVO(troblProcessVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * 
	 * @param troblProcessVO
	 */
	public void insertTroblProcess(TroblProcessVO troblProcessVO) {
		troblProcessMapper.insertTroblProcess(troblProcessVO);
	}

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * 
	 * @param troblProcessVO
	 */
	public void deleteTroblProcess(TroblProcessVO troblProcessVO) {
		troblProcessMapper.deleteTroblProcess(troblProcessVO);
	}

}