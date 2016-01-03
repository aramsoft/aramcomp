package aramframework.com.uss.ion.rsn.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS서비스를 처리하는 Service Class 구현
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

public interface RssService {

	/**
	 * RSS서비스 목록을 조회한다.
	 * 
	 * @param rssInfoVO
	 */
	public List<EgovMap> selectRssServiceList(RssInfoVO rssInfoVO);

	/**
	 * RSS서비스를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param rssInfoVO
	 */
	public int selectRssServiceListCnt(RssInfoVO rssInfoVO);

	/**
	 * RSS서비스를(을) 상세조회 한다.
	 * 
	 * @param rssInfoVO
	 */
	public RssInfoVO selectRssServiceDetail(RssInfoVO rssInfoVO);

	/**
	 * RSS서비스 테이블을 조회 한다.
	 * 
	 * @param rssInfoVO
	 */
	public List<EgovMap> selectRssServiceTable(RssInfoVO rssInfoVO);

}
