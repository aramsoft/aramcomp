package aramframework.com.sym.ccm.ccc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.ccm.ccc.domain.CmmnClCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 공통분류코드에 대한 서비스 구현클래스를 정의한다
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
public class CmmnClCodeManageService extends EgovAbstractServiceImpl {

	@Autowired
	private CmmnClCodeManageMapper cmmnClCodeManageMapper;	

	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public List<EgovMap> selectCmmnClCodeList(CmmnClCodeVO cmmnClCodeVO) {
		return cmmnClCodeManageMapper.selectCmmnClCodeList(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public int selectCmmnClCodeListCnt(CmmnClCodeVO cmmnClCodeVO) {
		return cmmnClCodeManageMapper.selectCmmnClCodeListCnt(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public CmmnClCodeVO selectCmmnClCodeDetail(CmmnClCodeVO cmmnClCodeVO) {
		CmmnClCodeVO resultVo = cmmnClCodeManageMapper.selectCmmnClCodeDetail(cmmnClCodeVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cmmnClCodeVO); 
		return resultVo;
	}

	/**
	 * 공통분류코드를 등록한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		cmmnClCodeManageMapper.insertCmmnClCode(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드를 수정한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		cmmnClCodeManageMapper.updateCmmnClCode(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드를 삭제한다.
	 * 
	 * @param cmmnClCodeVO
	 */
	public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		cmmnClCodeManageMapper.deleteCmmnClCode(cmmnClCodeVO);
	}
	
}
