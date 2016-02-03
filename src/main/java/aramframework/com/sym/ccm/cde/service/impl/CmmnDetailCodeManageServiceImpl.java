package aramframework.com.sym.ccm.cde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.ccm.cde.domain.CmmnDetailCodeVO;
import aramframework.com.sym.ccm.cde.service.CmmnDetailCodeManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통상세코드에 대한 서비스 구현클래스를 정의한다
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

@Service("cmmnDetailCodeManageService")
public class CmmnDetailCodeManageServiceImpl extends EgovAbstractServiceImpl implements CmmnDetailCodeManageService {

	@Autowired
	private CmmnDetailCodeManageMapper cmmnDetailCodeManageMapper;	

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public List<EgovMap> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) {
		return cmmnDetailCodeManageMapper.selectCmmnDetailCodeList(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public int selectCmmnDetailCodeListCnt(CmmnDetailCodeVO cmmnDetailCodeVO) {
		return cmmnDetailCodeManageMapper.selectCmmnDetailCodeListCnt(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 엑셀목록을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public List<EgovMap> selectCmmnDetailCodeListExcel(CmmnDetailCodeVO cmmnDetailCodeVO) {
		return cmmnDetailCodeManageMapper.selectCmmnDetailCodeListExcel(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO) {
		CmmnDetailCodeVO resultVo = cmmnDetailCodeManageMapper.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cmmnDetailCodeVO); 
		return resultVo;
	}

	/**
	 * 공통상세코드를 등록한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) {
		cmmnDetailCodeManageMapper.insertCmmnDetailCode(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) {
		cmmnDetailCodeManageMapper.updateCmmnDetailCode(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 삭제한다.
	 * 
	 * @param cmmnDetailCodeVO
	 */
	public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) {
		cmmnDetailCodeManageMapper.deleteCmmnDetailCode(cmmnDetailCodeVO);
	}

}
