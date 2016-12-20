package aramframework.com.cop.bbs.dao;

import java.util.List;

import aramframework.com.cop.bbs.domain.BoardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시물 관리를 위한 데이터 접근 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface BBSBoardMapper  {

	/**
	 * 조건에 맞는 게시물 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<EgovMap> selectBoardArticleList(BoardVO boardVO);

	/**
	 * 조건에 맞는 게시물 목록에 대한 전체 건수를 조회 한다.
	 * 
	 * @param boardVO
	 */
	public int selectBoardArticleListCnt(BoardVO boardVO);

	/**
	 * 게시물 한 건에 대하여 상세 내용을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public BoardVO selectBoardArticle(BoardVO boardVO);

	/**
	 * 게시판에 게시물을 등록 한다.
	 * 
	 * @param boardVO
	 */
	public void insertBoardArticle(BoardVO boardVO);

	/**
	 * 게시판에 답변 게시물을 등록 한다.
	 * 
	 * @param boardVO
	 */
	public void replyBoardArticle(BoardVO boardVO);
	public Integer getParentThreadNo(BoardVO boardVO);
	public void updateOtherThreadNo(BoardVO boardVO);
	public void updateThreadNo(BoardVO boardVO);

	/**
	 * 게시물 한 건의 내용을 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateBoardArticle(BoardVO boardVO);

	/**
	 * 게시물에 대한 조회 건수를 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateRdcnt(BoardVO boardVO);

	/**
	 * 게시물 한 건을 삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void deleteBoardArticle(BoardVO boardVO);

	/**
	 * 게시물 한 건을 완전삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void eraseBoardArticle(BoardVO boardVO);
	public void eraseComment(BoardVO boardVO);
	public void eraseSatisfaction(BoardVO boardVO);
	public void eraseScrap(BoardVO boardVO);

	/**
	 * 방명록에 대한 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<BoardVO> selectGuestList(BoardVO boardVO);

	/**
	 * 방명록에 대한 목록 건수를 조회 한다.
	 * 
	 * @param boardVO
	 */
	public int selectGuestListCnt(BoardVO boardVO);

	/**
	 * 방명록에 대한 패스워드를 조회 한다.
	 * 
	 * @param boardVO
	 */
	public String getPasswordInf(BoardVO boardVO);
	
	/**
	 * 방명록 내용을 삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void deleteGuestList(BoardVO boardVO);

}
