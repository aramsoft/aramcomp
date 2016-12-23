package aramframework.com.uss.ion.wik.bmk.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import aramframework.com.uss.ion.wik.bmk.domain.WikiBookmarkVO;

/**
 * 위키북마크를 처리하는 Dao Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface WikiBookmarkMapper {

	/**
	 * 위키북마크 목록을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public List<EgovMap> selectWikiBookmarkList(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 중복을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkDuplicationCnt(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public int selectWikiBookmarkListCnt(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 사용자 아이디를 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	public String selectWikiBookmarkEmpUniqId(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 등록한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	void insertWikiBookmark(WikiBookmarkVO wikiBookmarkVO);

	/**
	 * 위키북마크를(을) 수정한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	void deleteWikiBookmark(WikiBookmarkVO wikiBookmarkVO);
	
}
