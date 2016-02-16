package aramframework.com.uss.ion.ulm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.ulm.dao.UnityLinkMapper;
import aramframework.com.uss.ion.ulm.domain.UnityLinkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 통합링크관리를 처리하는 ServiceImpl Class 구현
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
public class UnityLinkService extends EgovAbstractServiceImpl {

	@Autowired
	private UnityLinkMapper unityLinkMapper;	

	@Autowired
	private EgovIdGnrService unityLinkIdGnrService; 

	/**
	 * 통합링크관리 메인 셈플 목록을 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	public List<EgovMap> selectUnityLinkSample(UnityLinkVO unityLinkVO) {
		return unityLinkMapper.selectUnityLinkSample(unityLinkVO);
	}

	/**
	 * 통합링크관리를(을) 목록을 조회 한다.
	 * 
	 * @param unityLinkVO
	 */
	public List<EgovMap> selectUnityLinkList(UnityLinkVO unityLinkVO) {
		return unityLinkMapper.selectUnityLinkList(unityLinkVO);
	}

	/**
	 * 통합링크관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	public int selectUnityLinkListCnt(UnityLinkVO unityLinkVO) {
		return (Integer) unityLinkMapper.selectUnityLinkListCnt(unityLinkVO);
	}

	/**
	 * 통합링크관리를(을) 상세조회 한다.
	 * 
	 * @param unityLinkVO
	 */
	public UnityLinkVO selectUnityLinkDetail(UnityLinkVO unityLinkVO) {
		UnityLinkVO resultVo =  unityLinkMapper.selectUnityLinkDetail(unityLinkVO);
		// searchVO 이전 
		resultVo.setSearchVO(unityLinkVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 
	 * @param unityLinkVO
	 */
	public void insertUnityLink(UnityLinkVO unityLinkVO) {
		try {
			unityLinkVO.setUnityLinkId(unityLinkIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		unityLinkMapper.insertUnityLink(unityLinkVO);
	}

	/**
	 * 통합링크관리를(을) 수정한다.
	 * 
	 * @param unityLinkVO
	 */
	public void updateUnityLink(UnityLinkVO unityLinkVO) {
		unityLinkMapper.updateUnityLink(unityLinkVO);
	}

	/**
	 * 통합링크관리를(을) 삭제한다.
	 * 
	 * @param unityLinkVO
	 */
	public void deleteUnityLink(UnityLinkVO unityLinkVO) {
		unityLinkMapper.deleteUnityLink(unityLinkVO);
	}

}
