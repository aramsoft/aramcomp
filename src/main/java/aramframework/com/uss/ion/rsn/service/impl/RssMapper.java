package aramframework.com.uss.ion.rsn.service.impl;

import java.util.HashMap;
import java.util.List;

import aramframework.com.uss.ion.rsn.service.RssInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS서비스를 처리하는 Dao Class 구현
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
public interface RssMapper {

	/**
	 * RSS서비스를(을) 목록을 한다.
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
	public List<HashMap<String, Object>> selectRssServiceTable(RssInfoVO rssInfoVO);

}
