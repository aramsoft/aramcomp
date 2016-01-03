package aramframework.com.sym.mnu.stm.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.cmm.SearchVO;
import aramframework.com.sym.mnu.stm.service.SiteMapngVO;

/**
 * 사이트맵 조회에 대한 DAO 클래스를 정의한다.
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

@Mapper("siteMapngMapper")
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