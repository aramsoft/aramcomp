package aramframework.com.sym.mnu.stm.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.sym.mnu.stm.domain.SiteMapngVO;

/**
 * 사이트맵 조회에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface SiteMapngMapper {

	/**
	 * MapCreatId 조회
	 * 
	 * @param searchVO
	 */
	public String selectSiteMapngByMapCreatID(SearchVO searchVO);

	/**
	 * 사이트맵 조회
	 * 
	 * @param searchVO
	 */
	public SiteMapngVO selectSiteMapng(SearchVO searchVO);

}