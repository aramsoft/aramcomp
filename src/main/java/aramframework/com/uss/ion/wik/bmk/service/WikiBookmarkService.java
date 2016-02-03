package aramframework.com.uss.ion.wik.bmk.service;

import java.util.List;

import aramframework.com.uss.ion.wik.bmk.domain.WikiBookmarkVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 위키북마크를 처리하는 Service Class 구현
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

public interface WikiBookmarkService {

	/**
	 * 위키북마크 목록을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public List<EgovMap> selectWikiBookmarkList(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkListCnt(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 중복을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkDuplicationCnt(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 등록한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	void insertWikiBookmark(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 삭제한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	void deleteWikiBookmark(WikiBookmarkVO wikiBookmarkVO);

}
