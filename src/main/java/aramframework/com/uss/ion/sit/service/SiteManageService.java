package aramframework.com.uss.ion.sit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.ion.sit.dao.SiteManageMapper;
import aramframework.com.uss.ion.sit.domain.SiteManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사이트정보를 처리하는 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class SiteManageService extends EgovAbstractServiceImpl {

	@Autowired
	private SiteManageMapper siteManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService siteManageIdGnrService; 

	/**
	 * 사이트정보 목록을 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public List<EgovMap> selectSiteList(SiteManageVO siteManageVO) {
		return siteManageMapper.selectSiteList(siteManageVO);
	}

	/**
	 * 사이트정보 총 갯수를 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public int selectSiteListCnt(SiteManageVO siteManageVO) {
		return siteManageMapper.selectSiteListCnt(siteManageVO);
	}

	/**
	 * 사이트상세정보를 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public SiteManageVO selectSiteDetail(SiteManageVO siteManageVO) {
		SiteManageVO resultVo = siteManageMapper.selectSiteDetail(siteManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(siteManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 사이트정보를 등록한다.
	 * 
	 * @param siteManageVO
	 */
	public void insertSiteInfo(SiteManageVO siteManageVO) {
		try {
			siteManageVO.setSiteId(siteManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		siteManageMapper.insertSiteInfo(siteManageVO);
	}

	/**
	 * 사이트정보를 수정한다.
	 * 
	 * @param siteManageVO
	 */
	public void updateSiteInfo(SiteManageVO siteManageVO) {
		siteManageMapper.updateSiteInfo(siteManageVO);
	}

	/**
	 * 사이트정보를 삭제한다.
	 * 
	 * @param siteManageVO
	 */
	public void deleteSiteInfo(SiteManageVO siteManageVO) {
		siteManageMapper.deleteSiteInfo(siteManageVO);
	}

}
