package aramframework.com.uss.sam.cpy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.sam.cpy.dao.CpyrhtPrtcPolicyMapper;
import aramframework.com.uss.sam.cpy.domain.CpyrhtPrtcPolicyVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 저작권보호정책내용을 처리하는 비즈니스 구현 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class CpyrhtPrtcPolicyService extends EgovAbstractServiceImpl {

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
		return cpyrhtPrtcPolicyMapper.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO);
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
