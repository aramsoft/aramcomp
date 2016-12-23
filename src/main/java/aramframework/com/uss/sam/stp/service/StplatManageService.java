package aramframework.com.uss.sam.stp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.sam.stp.dao.StplatManageMapper;
import aramframework.com.uss.sam.stp.domain.StplatManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 약관내용을 처리하는 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class StplatManageService extends EgovAbstractServiceImpl {

	@Autowired
	private StplatManageMapper stplatManageMapper;
	
	/** ID Generation */
	@Autowired
	private EgovIdGnrService stplatManageIdGnrService; 

	/**
	 * 약관정보 글 목록을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public List<EgovMap> selectStplatList(StplatManageVO stplatManageVO)  {
		return stplatManageMapper.selectStplatList(stplatManageVO);
	}

	/**
	 * 약관정보 글 총 갯수를 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public int selectStplatListCnt(StplatManageVO stplatManageVO) {
		return stplatManageMapper.selectStplatListCnt(stplatManageVO);
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	public StplatManageVO selectStplatDetail(StplatManageVO stplatManageVO)  {
		StplatManageVO resultVo = stplatManageMapper.selectStplatDetail(stplatManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(stplatManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 약관정보 글을 등록한다.
	 * 
	 * @param stplatManageVO
	 */
	public void insertStplat(StplatManageVO stplatManageVO) {
		try {
			stplatManageVO.setUseStplatId(stplatManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		stplatManageMapper.insertStplat(stplatManageVO);
	}

	/**
	 * 약관정보 글을 수정한다.
	 * 
	 * @param stplatManageVO
	 */
	public void updateStplat(StplatManageVO stplatManageVO) {
		stplatManageMapper.updateStplat(stplatManageVO);
	}

	/**
	 * 약관정보 글을 삭제한다.
	 * 
	 * @param stplatManageVO
	 */
	public void deleteStplat(StplatManageVO stplatManageVO)  {
		stplatManageMapper.deleteStplat(stplatManageVO);
	}

}
