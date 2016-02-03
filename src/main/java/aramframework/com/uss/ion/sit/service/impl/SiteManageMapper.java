package aramframework.com.uss.ion.sit.service.impl;

import java.util.List;

import aramframework.com.uss.ion.sit.domain.SiteManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사이트정보를 처리하는 DAO 클래스
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

@Mapper
public interface SiteManageMapper {

	/**
	 * 사이트정보 목록을 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public List<EgovMap> selectSiteList(SiteManageVO siteManageVO);

	/**
	 * 사이트정보 총 갯수를 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public int selectSiteListCnt(SiteManageVO siteManageVO);

	/**
	 * 사이트 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param siteManageVO
	 */
	public SiteManageVO selectSiteDetail(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 등록한다.
	 * 
	 * @param siteManageVO
	 */
	public void insertSiteInfo(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 수정한다.
	 * 
	 * @param siteManageVO
	 */
	public void updateSiteInfo(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 삭제한다.
	 * 
	 * @param siteManageVO
	 */
	public void deleteSiteInfo(SiteManageVO siteManageVO);

}
