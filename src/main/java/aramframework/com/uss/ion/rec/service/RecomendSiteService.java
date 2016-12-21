package aramframework.com.uss.ion.rec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.rec.dao.RecomendSiteMapper;
import aramframework.com.uss.ion.rec.domain.RecomendSiteVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 추천사이트정보를 처리하는 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class RecomendSiteService extends EgovAbstractServiceImpl {

	@Autowired
	private RecomendSiteMapper recomendSiteMapper;		

	/** ID Generation */
	@Autowired
	private EgovIdGnrService recomendSiteIdGnrService; 

	/**
	 * 추천사이트 목록을 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	public List<EgovMap> selectRecomendSiteList(RecomendSiteVO recomendSiteVO) {
		return recomendSiteMapper.selectRecomendSiteList(recomendSiteVO);
	}

	/**
	 * 추천사이트 총 갯수를 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	public int selectRecomendSiteListCnt(RecomendSiteVO recomendSiteVO) {
		return recomendSiteMapper.selectRecomendSiteListCnt(recomendSiteVO);
	}

	/**
	 * 추천사이트 상세조회를 한다.
	 * 
	 * @param recomendSiteVO
	 */
	public RecomendSiteVO selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO) {
		RecomendSiteVO resultVo = recomendSiteMapper.selectRecomendSiteDetail(recomendSiteVO);
		// searchVO 이전 
		resultVo.setSearchVO(recomendSiteVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 추천사이트를 등록한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void insertRecomendSiteInfo(RecomendSiteVO recomendSiteVO) {
		try {
			recomendSiteVO.setRecomendSiteId(recomendSiteIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		recomendSiteMapper.insertRecomendSiteInfo(recomendSiteVO);
	}

	/**
	 * 추천사이트를 수정한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void updateRecomendSiteInfo(RecomendSiteVO recomendSiteVO) {
		recomendSiteMapper.updateRecomendSiteInfo(recomendSiteVO);
	}

	/**
	 * 추천사이트를 삭제한다.
	 * 
	 * @param recomendSiteVO
	 */
	public void deleteRecomendSiteInfo(RecomendSiteVO recomendSiteVO) {
		recomendSiteMapper.deleteRecomendSiteInfo(recomendSiteVO);
	}

}
