package aramframework.com.uss.ion.sit.service;

import java.util.List;

import aramframework.com.uss.ion.sit.domain.SiteManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 사이트정보를 처리하는 클래스
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

public interface SiteManageService {

	/**
	 * 사이트목록을 조회한다.
	 * 
	 * @param siteManageVO
	 */
	List<EgovMap> selectSiteList(SiteManageVO siteManageVO);

	/**
	 * 사이트정보 총 갯수를 조회한다.
	 * 
	 * @param siteManageVO
	 */
	int selectSiteListCnt(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 상세조회한다.
	 * 
	 * @param siteManageVO
	 */
	SiteManageVO selectSiteDetail(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 등록한다.
	 * 
	 * @param siteManageVO
	 */
	void insertSiteInfo(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 수정한다.
	 * 
	 * @param siteManageVO
	 */
	void updateSiteInfo(SiteManageVO siteManageVO);

	/**
	 * 사이트정보를 삭제한다.
	 * 
	 * @param siteManageVO
	 */
	void deleteSiteInfo(SiteManageVO siteManageVO);

}
