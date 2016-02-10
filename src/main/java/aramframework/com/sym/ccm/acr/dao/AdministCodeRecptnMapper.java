package aramframework.com.sym.ccm.acr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import aramframework.com.sym.ccm.acr.domain.AdministCodeRecptnVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 법정동코드에 대한 데이터 접근 클래스를 정의한다
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

@Repository("administCodeRecptnMapper")
public class AdministCodeRecptnMapper extends EgovAbstractMapper {

	final static String NAMESPACE = "aramframework.com.sym.ccm.acr.dao.AdministCodeRecptnMapper";

	/**
	 * 법정동코드수신 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO administCodeRecptnVO) {
		return selectList(NAMESPACE+".selectAdministCodeRecptnList", administCodeRecptnVO);
	}

	/**
	 * 법정동코드수신 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public int selectAdministCodeRecptnListCnt(AdministCodeRecptnVO administCodeRecptnVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectAdministCodeRecptnListCnt", administCodeRecptnVO);
	}

	/**
	 * 법정동코드 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO administCodeRecptnVO) {
		return selectList(NAMESPACE+".selectAdministCodeList", administCodeRecptnVO);
	}

	/**
	 * 법정동코드 총 갯수를 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public int selectAdministCodeListCnt(AdministCodeRecptnVO administCodeRecptnVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectAdministCodeListCnt", administCodeRecptnVO);
	}
	
	/**
	 * 법정동코드 상세내역을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public AdministCodeRecptnVO selectAdministCodeDetail(AdministCodeRecptnVO administCodeRecptnVO) {
		return (AdministCodeRecptnVO) selectOne(NAMESPACE+".selectAdministCodeDetail", administCodeRecptnVO);
	}

	/**
	 * 법정동코드수신을 처리한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public void insertAdministCodeRecptn(AdministCodeRecptnVO administCodeRecptnVO) {
		insert(NAMESPACE+".insertAdministCodeRecptn", administCodeRecptnVO);
	}

	/**
	 * 법정동코드를 등록한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public void insertAdministCode(AdministCodeRecptnVO administCodeRecptnVO) {
		AdministCodeRecptnVO beforeData = selectAdministCodeDetail(administCodeRecptnVO);

		if (beforeData.getAdministZoneCode().equals(administCodeRecptnVO.getAdministZoneCode())
				&& beforeData.getAdministZoneSe().equals(administCodeRecptnVO.getAdministZoneSe())) {
			// 기등록 자료
			administCodeRecptnVO.setProcessSe("10");
		} else {
			int rtnValue = update(NAMESPACE+".insertAdministCode", administCodeRecptnVO);
			if (rtnValue != 1) {
				// 등록 오류
				administCodeRecptnVO.setProcessSe("11");
			}
		}
		update(NAMESPACE+".updateAdministCodeRecptn", administCodeRecptnVO);
	}

	/**
	 * 법정동코드를 수정한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public void updateAdministCode(AdministCodeRecptnVO administCodeRecptnVO) {
		int rtnValue = update(NAMESPACE+".updateAdministCode", administCodeRecptnVO);
		if (rtnValue != 1) {
			// 변경 오류
			administCodeRecptnVO.setProcessSe("12");
		}
		update(NAMESPACE+".updateAdministCodeRecptn", administCodeRecptnVO);
	}

	/**
	 * 법정동코드를 삭제한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	public void deleteAdministCode(AdministCodeRecptnVO administCodeRecptnVO) {
		int rtnValue = update(NAMESPACE+".deleteAdministCode", administCodeRecptnVO);
		if (rtnValue != 1) {
			// 삭제 오류
			administCodeRecptnVO.setProcessSe("13");
		}
		update(NAMESPACE+".updateAdministCodeRecptn", administCodeRecptnVO);
	}

}
