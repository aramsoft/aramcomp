package aramframework.com.dam.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.dam.app.dao.KnoAppraisalMapper;
import aramframework.com.dam.app.domain.KnoAppraisalVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식정보평가에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class KnoAppraisalService extends EgovAbstractServiceImpl {

	@Autowired
	private KnoAppraisalMapper knoAppraisalMapper;

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public List<EgovMap> selectKnoAppraisalList(KnoAppraisalVO knoAppraisalVO) {
		return knoAppraisalMapper.selectKnoAppraisalList(knoAppraisalVO);
	}

	/**
	 * 지식정보평가 목록 총 갯수를 조회한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public int selectKnoAppraisalListCnt(KnoAppraisalVO knoAppraisalVO) {
		return knoAppraisalMapper.selectKnoAppraisalListCnt(knoAppraisalVO);
	}

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public KnoAppraisalVO selectKnoAppraisal(KnoAppraisalVO knoAppraisalVO) {
		KnoAppraisalVO resultVo = knoAppraisalMapper.selectKnoAppraisal(knoAppraisalVO);
		// searchVO 이전 
		resultVo.setSearchVO(knoAppraisalVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void insertKnoAppraisal(KnoAppraisalVO knoAppraisalVO) {
		knoAppraisalMapper.insertKnoAppraisal(knoAppraisalVO);
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void updateKnoAppraisal(KnoAppraisalVO knoAppraisalVO) {
		knoAppraisalMapper.updateKnoAppraisal(knoAppraisalVO);
	}

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * 
	 * @param knoAppraisalVO
	 */
	public void deleteKnoAppraisal(KnoAppraisalVO knoAppraisalVO) {
		knoAppraisalMapper.deleteKnoAppraisal(knoAppraisalVO);
	}

}