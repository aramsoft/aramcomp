package aramframework.com.uss.sam.ipm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.sam.ipm.dao.IndvdlInfoPolicyMapper;
import aramframework.com.uss.sam.ipm.domain.IndvdlInfoPolicyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개인정보보호정책를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class IndvdlInfoPolicyService extends EgovAbstractServiceImpl {

	@Autowired
	private IndvdlInfoPolicyMapper indvdlInfoPolicyMapper;
	
	@Autowired
	private EgovIdGnrService indvdlInfoPolicyIdGnrService; 

	/**
	 * 개인정보보호정책를(을) 목록을 조회 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public List<EgovMap> selectIndvdlInfoPolicyList(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		return indvdlInfoPolicyMapper.selectIndvdlInfoPolicyList(indvdlInfoPolicyVO);
	}

	/**
	 * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public int selectIndvdlInfoPolicyListCnt(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		return (Integer) indvdlInfoPolicyMapper.selectIndvdlInfoPolicyListCnt(indvdlInfoPolicyVO);
	}

	/**
	 * 개인정보보호정책를(을) 상세조회 한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public IndvdlInfoPolicyVO selectIndvdlInfoPolicyDetail(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		IndvdlInfoPolicyVO resultVo = indvdlInfoPolicyMapper.selectIndvdlInfoPolicyDetail(indvdlInfoPolicyVO);
		// searchVO 이전 
		resultVo.setSearchVO(indvdlInfoPolicyVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 개인정보보호정책를(을) 등록한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void insertIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		try {
			indvdlInfoPolicyVO.setIndvdlInfoId(indvdlInfoPolicyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		indvdlInfoPolicyMapper.insertIndvdlInfoPolicy(indvdlInfoPolicyVO);
	}

	/**
	 * 개인정보보호정책를(을) 수정한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void updateIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		indvdlInfoPolicyMapper.updateIndvdlInfoPolicy(indvdlInfoPolicyVO);
	}

	/**
	 * 개인정보보호정책를(을) 삭제한다.
	 * 
	 * @param indvdlInfoPolicyVO
	 */
	public void deleteIndvdlInfoPolicy(IndvdlInfoPolicyVO indvdlInfoPolicyVO) {
		indvdlInfoPolicyMapper.deleteIndvdlInfoPolicy(indvdlInfoPolicyVO);
	}

}
