package aramframework.com.cop.bbs.service.impl;

import java.util.List;

import aramframework.com.cop.bbs.service.CommentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 댓글관리를 위한 데이터 접근 클래스
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
public interface BBSCommentMapper {

	/**
	 * 댓글에 대한 목록을 조회 한다.
	 * 
	 * @param commentVO
	 */
	public List<EgovMap> selectCommentList(CommentVO commentVO);

	/**
	 * 댓글에 대한 목록 건수를 조회 한다.
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
	 * 댓글에 대한 패스워드를 조회 한다.
	 * 
	 * @param commentVO
	 */
	public String getCommentPassword(CommentVO commentVO);
	
}
