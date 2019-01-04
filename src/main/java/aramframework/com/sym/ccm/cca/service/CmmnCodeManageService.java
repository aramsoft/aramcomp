package aramframework.com.sym.ccm.cca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.ccm.cca.dao.CmmnCodeManageMapper;
import aramframework.com.sym.ccm.cca.domain.CmmnCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통코드에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class CmmnCodeManageService extends EgovAbstractServiceImpl {

	@Autowired
	private CmmnCodeManageMapper cmmnCodeManageMapper;

	/**
	 * 공통코드 목록을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public List<EgovMap> selectCmmnCodeList(CmmnCodeVO cmmnCodeVO) {
		return cmmnCodeManageMapper.selectCmmnCodeList(cmmnCodeVO);
	}

	/**
	 * 공통코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public int selectCmmnCodeListCnt(CmmnCodeVO cmmnCodeVO) {
		return cmmnCodeManageMapper.selectCmmnCodeListCnt(cmmnCodeVO);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCodeVO) {
		return cmmnCodeManageMapper.selectCmmnCodeDetail(cmmnCodeVO);
	}

	/**
	 * 공통코드를 등록한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void insertCmmnCode(CmmnCodeVO cmmnCodeVO) {
		cmmnCodeManageMapper.insertCmmnCode(cmmnCodeVO);
	}

	/**
	 * 공통코드를 수정한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void updateCmmnCode(CmmnCodeVO cmmnCodeVO) {
		cmmnCodeManageMapper.updateCmmnCode(cmmnCodeVO);
	}

	/**
	 * 공통코드를 삭제한다.
	 * 
	 * @param cmmnCodeVO
	 */
	public void deleteCmmnCode(CmmnCodeVO cmmnCodeVO) {
		cmmnCodeManageMapper.deleteCmmnCode(cmmnCodeVO);
	}

}
