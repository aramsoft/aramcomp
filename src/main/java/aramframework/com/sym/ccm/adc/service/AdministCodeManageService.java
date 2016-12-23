package aramframework.com.sym.ccm.adc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.ccm.adc.dao.AdministCodeManageMapper;
import aramframework.com.sym.ccm.adc.domain.AdministCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행정코드에 대한 서비스 구현클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class AdministCodeManageService extends EgovAbstractServiceImpl {

	@Autowired
	private AdministCodeManageMapper administCodeManageMapper;	

	/**
	 * 행정코드 목록을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public List<EgovMap> selectAdministCodeList(AdministCodeVO administCodeVO) {
		return administCodeManageMapper.selectAdministCodeList(administCodeVO);
	}

	/**
	 * 행정코드 총 갯수를 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public int selectAdministCodeListCnt(AdministCodeVO administCodeVO) {
		return administCodeManageMapper.selectAdministCodeListCnt(administCodeVO);
	}

	/**
	 * 행정코드 상세항목을 조회한다.
	 * 
	 * @param administCodeVO
	 */
	public AdministCodeVO selectAdministCodeDetail(AdministCodeVO administCodeVO) {
		AdministCodeVO resultVo = administCodeManageMapper.selectAdministCodeDetail(administCodeVO);
		// searchVO 이전 
		resultVo.setSearchVO(administCodeVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 행정코드를 등록한다.
	 * 
	 * @param administCodeVO
	 */
	public void insertAdministCode(AdministCodeVO administCodeVO) {
		administCodeManageMapper.insertAdministCode(administCodeVO);
	}

	/**
	 * 행정코드를 수정한다.
	 * 
	 * @param administCodeVO
	 */
	public void updateAdministCode(AdministCodeVO administCodeVO) {
		administCodeManageMapper.updateAdministCode(administCodeVO);
	}

	/**
	 * 행정코드를 삭제한다.
	 * 
	 * @param administCodeVO
	 */
	public void deleteAdministCode(AdministCodeVO administCodeVO) {
		administCodeManageMapper.deleteAdministCode(administCodeVO);
	}

}
