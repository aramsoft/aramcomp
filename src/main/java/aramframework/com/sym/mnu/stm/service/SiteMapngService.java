package aramframework.com.sym.mnu.stm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.sym.mnu.stm.dao.SiteMapngMapper;
import aramframework.com.sym.mnu.stm.domain.SiteMapngVO;

/**
 * 사이트맵 조회를 처리하는 비즈니스 구현 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class SiteMapngService extends EgovAbstractServiceImpl {

	@Autowired
	private SiteMapngMapper siteMapngMapper;
	
	/**
	 * 사이트맵 조회
	 * 
	 * @param searchVO
	 */

	public SiteMapngVO selectSiteMapng(SearchVO searchVO) {
		searchVO.setSearchKeyword(siteMapngMapper.selectSiteMapngByMapCreatID(searchVO));
		return siteMapngMapper.selectSiteMapng(searchVO);
	}
	
}