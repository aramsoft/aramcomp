package aramframework.com.uss.sam.cpy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.sam.cpy.domain.CpyrhtPrtcPolicyVO;
import aramframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 저작권보호정책내용을 처리하는 비즈니스 구현 클래스
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

@Service("cpyrhtPrtcPolicyService")
public class CpyrhtPrtcPolicyServiceImpl extends EgovAbstractServiceImpl implements CpyrhtPrtcPolicyService {

	@Autowired
	private CpyrhtPrtcPolicyMapper cpyrhtPrtcPolicyMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService cpyrhtPrtcPolicyIdGnrService; 

	/**
	 * 저작권보호정책 글 목록을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public List<EgovMap> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		return cpyrhtPrtcPolicyMapper.selectCpyrhtPrtcPolicyList(cpyrhtPrtcPolicyVO);
	}

	/**
	 * 저작권보호정책 글 총 갯수를 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public int selectCpyrhtPrtcPolicyListCnt(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		return cpyrhtPrtcPolicyMapper.selectCpyrhtPrtcPolicyListCnt(cpyrhtPrtcPolicyVO);
	}

	/**
	 * 저작권보호정책 글을 조회한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		CpyrhtPrtcPolicyVO resultVo = cpyrhtPrtcPolicyMapper.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, cpyrhtPrtcPolicyVO); 
		return resultVo;
	}

	/**
	 * 저작권보호정책 글을 등록한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void insertCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		try {
			cpyrhtPrtcPolicyVO.setCpyrhtId(cpyrhtPrtcPolicyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		cpyrhtPrtcPolicyMapper.insertCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);
	}

	/**
	 * 저작권보호정책 글을 수정한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void updateCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		cpyrhtPrtcPolicyMapper.updateCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);
	}

	/**
	 * 저작권보호정책 글을 삭제한다.
	 * 
	 * @param cpyrhtPrtcPolicyVO
	 */
	public void deleteCpyrhtPrtcPolicy(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO) {
		cpyrhtPrtcPolicyMapper.deleteCpyrhtPrtcPolicy(cpyrhtPrtcPolicyVO);
	}

}
