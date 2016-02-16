package aramframework.com.uss.ion.isg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.isg.dao.IntnetSvcGuidanceMapper;
import aramframework.com.uss.ion.isg.domain.IntnetSvcGuidanceVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 인터넷서비스안내에 대한 ServiceImpl 클래스를 정의한다.
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
public class IntnetSvcGuidanceService extends EgovAbstractServiceImpl {

	@Autowired
	private IntnetSvcGuidanceMapper intnetSvcGuidanceMapper;	

	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService intnetSvcGuidanceIdGnrService;
 
	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public List<EgovMap> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		return intnetSvcGuidanceMapper.selectIntnetSvcGuidanceList(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내목록 총 갯수를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public int selectIntnetSvcGuidanceListCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		return intnetSvcGuidanceMapper.selectIntnetSvcGuidanceListCnt(intnetSvcGuidanceVO);
	}

	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		IntnetSvcGuidanceVO resultVo = intnetSvcGuidanceMapper.selectIntnetSvcGuidance(intnetSvcGuidanceVO);
		// searchVO 이전 
		resultVo.setSearchVO(intnetSvcGuidanceVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void insertIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		try {
			intnetSvcGuidanceVO.setIntnetSvcId(intnetSvcGuidanceIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		intnetSvcGuidanceMapper.insertIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void updateIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		intnetSvcGuidanceMapper.updateIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public void deleteIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		intnetSvcGuidanceMapper.deleteIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	public List<EgovMap> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO) {
		return intnetSvcGuidanceMapper.selectIntnetSvcGuidanceResult(intnetSvcGuidanceVO);
	}
	
}
