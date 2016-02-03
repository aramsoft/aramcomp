package aramframework.com.cop.bbs.service;

import java.util.List;

import aramframework.com.cop.bbs.domain.BoardVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 게시물 관리를 위한 서비스 인터페이스 클래스
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

public interface BBSBoardService {

	static public String BBS_TYPE_BOARD 	= "BBST01";	//일반게시판
	static public String BBS_TYPE_ANONYMOUS = "BBST02";	//익명게시판
	static public String BBS_TYPE_NOTICE 	= "BBST03";	//공지게시판
	static public String BBS_TYPE_VISIT 	= "BBST04";	//방명록

	static public String BBS_ATTRB_LIMIT 	= "BBSA01";	//유효게시판
	static public String BBS_ATTRB_GALARY 	= "BBSA02";	//갤러리
	static public String BBS_ATTRB_GENERAL 	= "BBSA03";	//일반게시판
	
	/**
	 * 조건에 맞는 게시물 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<EgovMap> selectBoardArticleList(BoardVO boardVO);

	/**
	 * 게시물 총갯수을 조회한다.
	 * 
	 * @param boardVO
	 */
	public int selectBoardArticleListCnt(BoardVO boardVO);

	/**
	 * 게시물 대하여 상세 내용을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public BoardVO selectBoardArticle(BoardVO boardVO);

	/**
	 * 게시물에 대한 조회 건수를 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateRdcnt(BoardVO boardVO);

	/**
	 * 게시판에 게시물 또는 답변 게시물을 등록 한다.
	 * 
	 * @param boardVO
	 */
	public void insertBoardArticle(BoardVO boardVO);

	/**
	 * 게시물 한 건의 내용을 수정 한다.
	 * 
	 * @param boardVO
	 */
	public void updateBoardArticle(BoardVO boardVO);

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

	/**
	 * 방명록에 대한 목록을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public List<BoardVO> selectGuestList(BoardVO boardVO);

	/**
	 * 방명록에 대한 목록 총갯수을 조회 한다.
	 * 
	 * @param boardVO
	 */
	public int selectGuestListCnt(BoardVO boardVO);

	/**
	 * 방명록 내용을 삭제 한다.
	 * 
	 * @param boardVO
	 */
	public void deleteGuestList(BoardVO boardVO);

	/**
	 * 방명록에 대한 패스워드를 조회 한다.
	 * 
	 * @param boardVO
	 */
	public String getPasswordInf(BoardVO boardVO);

}
