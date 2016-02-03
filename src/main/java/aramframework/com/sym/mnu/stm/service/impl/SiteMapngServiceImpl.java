package aramframework.com.sym.mnu.stm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import aramframework.com.cmm.SearchVO;
import aramframework.com.sym.mnu.stm.domain.SiteMapngVO;
import aramframework.com.sym.mnu.stm.service.SiteMapngService;

/**
 * 사이트맵 조회를 처리하는 비즈니스 구현 클래스를 정의한다.
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

@Service("siteMapngService")
public class SiteMapngServiceImpl extends EgovAbstractServiceImpl implements SiteMapngService {

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