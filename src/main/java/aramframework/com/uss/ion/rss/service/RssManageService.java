package aramframework.com.uss.ion.rss.service;

import java.util.List;
import java.util.Map;

import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.uss.ion.rss.domain.RssManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * RSS태그관리를 처리하는 Service Class 구현
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

public interface RssManageService {

	/**
	 * JDBC 테이블 목록을조회한다.
	 * 
	 */
	public List<ComCodeVO> selectRssManageTableList();

	/**
	 * JDBC 테이블 컬럼 목록을 조회한다.
	 * 
	 * @param map
	 */
	public List<ComCodeVO> selectRssManageTableColumnList(Map<String, String> map);

	/**
	 * RSS태그관리 목록을 조회한다.
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
	void insertRssManage(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 수정한다.
	 * 
	 * @param rssManageVO
	 */
	void updateRssManage(RssManageVO rssManageVO);

	/**
	 * RSS태그관리를(을) 삭제한다.
	 * 
	 * @param rssManageVO
	 */
	void deleteRssManage(RssManageVO rssManageVO);

}
