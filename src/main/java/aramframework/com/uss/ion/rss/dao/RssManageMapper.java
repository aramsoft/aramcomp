package aramframework.com.uss.ion.rss.dao;

import java.util.List;

import aramframework.com.uss.ion.rss.domain.RssManageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS태그관리를 처리하는 Dao Class 구현
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
public interface RssManageMapper {

	/**
	 * RSS태그관리를(을) 목록을 한다.
	 * 
	 * @param rssManageVO
	 */
	public List<EgovMap> selectRssManageList(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param rssManageVO
	 */
	public int selectRssManageListCnt(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 상세조회 한다.
	 * 
	 * @param rssManageVO
	 */
	public RssManageVO selectRssManageDetail(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 등록한다.
	 * 
	 * @param rssManageVO
	 */
	public void insertRssManage(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 수정한다.
	 * 
	 * @param rssManageVO
	 */
	public void updateRssManage(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 삭제한다.
	 * 
	 * @param rssManageVO
	 */
	public void deleteRssManage(RssManageVO rssManageVO);

}
