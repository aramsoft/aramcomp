package aramframework.com.dam.mgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.dam.mgm.dao.KnoManagementMapper;
import aramframework.com.dam.mgm.domain.KnoManagementVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 지식정보에 대한 ServiceImpl 클래스를 정의한다.
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

@Service
public class KnoManagementService extends EgovAbstractServiceImpl {

	@Autowired
	private KnoManagementMapper knoManagementMapper;

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	public List<EgovMap> selectKnoManagementList(KnoManagementVO knoManagementVO) {
		return knoManagementMapper.selectKnoManagementList(knoManagementVO);
	}

	/**
	 * 지식정보 목록 총 갯수를 조회한다.
	 * 
	 * @param knoManagementVO
	 */
	public int selectKnoManagementListCnt(KnoManagementVO knoManagementVO) {
		return knoManagementMapper.selectKnoManagementListCnt(knoManagementVO);
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	public void selectKnoManagement(KnoManagementVO knoManagementVO) {
		KnoManagementVO resultVo = knoManagementMapper.selectKnoManagement(knoManagementVO);
		// searchVO 이전 
		resultVo.setSearchVO(knoManagementVO.getSearchVO()); 
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * 
	 * @param knoManagementVO
	 */
	public void insertKnoManagement(KnoManagementVO knoManagementVO) {
		knoManagementMapper.insertKnoManagement(knoManagementVO);
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * 
	 * @param knoManagementVO
	 */
	public void updateKnoManagement(KnoManagementVO knoManagementVO) {
		knoManagementMapper.updateKnoManagement(knoManagementVO);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * 
	 * @param knoManagementVO
	 */
	public void deleteKnoManagement(KnoManagementVO knoManagementVO) {
		knoManagementMapper.deleteKnoManagement(knoManagementVO);
	}

}