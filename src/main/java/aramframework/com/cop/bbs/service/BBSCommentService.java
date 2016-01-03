package aramframework.com.cop.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 댓글관리를 위한 서비스 인터페이스 클래스
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

public interface BBSCommentService {

	/**
	 * 댓글 사용 가능 여부를 확인한다.
	 * 
	 * @param bbsId
	 */
	public boolean canUseComment(String bbsId);

	/**
	 * 댓글에 대한 목록을 조회 한다.
	 * 
	 * @param commentVO
	 */
	public List<EgovMap> selectCommentList(CommentVO commentVO);

	/**
	 * 댓글에 대한 목록 총갯수을 조회 한다.
	 * 
	 * @param commentVO
	 */
	public int selectCommentListCnt(CommentVO commentVO);

	/**
	 * 댓글에 대한 내용을 조회한다.
	 * 
	 * @param commentVO
	 */
	public CommentVO selectComment(CommentVO commentVO);

	/**
	 * 댓글을 등록한다.
	 * 
	 * @param commentVO
	 */
	public void insertComment(CommentVO commentVO);

	/**
	 * 댓글에 대한 내용을 수정한다.
	 * 
	 * @param commentVO
	 */
	public void updateComment(CommentVO commentVO);

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param commentVO
	 */
	public void deleteComment(CommentVO commentVO);

	/**
	 * 댓글 패스워드를 가져온다.
	 * 
	 * @param commentVO
	 */
	public String getCommentPassword(CommentVO commentVO);
	
}
