package aramframework.com.sym.ccm.icr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import aramframework.com.sym.ccm.icr.domain.InsttCodeRecptnVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 기관코드에 대한 데이터 접근 클래스를 정의한다
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

@Repository("insttCodeRecptnMapper")
public class InsttCodeRecptnMapper extends EgovAbstractMapper  {

	final static String NAMESPACE = "aramframework.com.sym.ccm.icr.dao.InsttCodeRecptnMapper";
	/**
	 * 기관코드수신 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO insttCodeRecptnVO) {
		return selectList(NAMESPACE+".selectInsttCodeRecptnList", insttCodeRecptnVO);
	}

	/**
	 * 기관코드수신 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public int selectInsttCodeRecptnListCnt(InsttCodeRecptnVO insttCodeRecptnVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectInsttCodeRecptnListCnt", insttCodeRecptnVO);
	}

	/**
	 * 기관코드 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO insttCodeRecptnVO) {
		return selectList(NAMESPACE+".selectInsttCodeList", insttCodeRecptnVO);
	}

	/**
	 * 기관코드 총 갯수를 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public int selectInsttCodeListCnt(InsttCodeRecptnVO insttCodeRecptnVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectInsttCodeListCnt", insttCodeRecptnVO);
	}
	
	/**
	 * 기관코드 상세내역을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public InsttCodeRecptnVO selectInsttCodeDetail(InsttCodeRecptnVO insttCodeRecptnVO) {
		return (InsttCodeRecptnVO) selectOne(NAMESPACE+".selectInsttCodeDetail", insttCodeRecptnVO);
	}

	/**
	 * 기관코드수신을 처리한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public void insertInsttCodeRecptn(InsttCodeRecptnVO insttCodeRecptnVO) {
		insert(NAMESPACE+".insertInsttCodeRecptn", insttCodeRecptnVO);
	}

	/**
	 * 기관코드를 등록한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public void insertInsttCode(InsttCodeRecptnVO insttCodeRecptnVO) {
		InsttCodeRecptnVO beforeData = selectInsttCodeDetail(insttCodeRecptnVO);
		if (beforeData != null && beforeData.getInsttCode().equals(insttCodeRecptnVO.getInsttCode())) {// 2011.09.05
			// 기등록 자료
			insttCodeRecptnVO.setProcessSe("10");
		} else {
			int rtnValue = update(NAMESPACE+".insertInsttCode", insttCodeRecptnVO);
			if (rtnValue != 1) {
				// 등록 오류
				insttCodeRecptnVO.setProcessSe("11");
			}
		}
		update(NAMESPACE+".updateInsttCodeRecptn", insttCodeRecptnVO);
	}

	/**
	 * 기관코드를 수정한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public void updateInsttCode(InsttCodeRecptnVO insttCodeRecptnVO) {
		int rtnValue = update(NAMESPACE+".updateInsttCode", insttCodeRecptnVO);
		if (rtnValue != 1) {
			// 변경 오류
			insttCodeRecptnVO.setProcessSe("12");
		}
		update(NAMESPACE+".updateInsttCodeRecptn", insttCodeRecptnVO);
	}

	/**
	 * 기관코드를 삭제한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	public void deleteInsttCode(InsttCodeRecptnVO insttCodeRecptnVO) {
		int rtnValue = update(NAMESPACE+".deleteInsttCode", insttCodeRecptnVO);
		if (rtnValue != 1) {
			// 삭제 오류
			insttCodeRecptnVO.setProcessSe("13");
		}
		update(NAMESPACE+".updateInsttCodeRecptn", insttCodeRecptnVO);
	}

}
