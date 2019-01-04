package aramframework.com.cop.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cop.bbs.dao.BBSAddedOptionsMapper;
import aramframework.com.cop.bbs.dao.BBSCommentMapper;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.CommentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 댓글관리를 위한 서비스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BBSCommentService extends EgovAbstractServiceImpl {

	@Autowired
	private BBSAddedOptionsMapper bbsAddedOptionsMapper;	

	@Autowired
	private BBSCommentMapper bbsCommentMapper;	

	@Autowired
	private EgovIdGnrService answerNoGnrService; 
		
	/**
	 * 댓글 사용 가능 여부를 확인한다.
	 * 
	 * @param bbsId
	 */
	public boolean canUseComment(String bbsId) {
		BoardMasterVO options = bbsAddedOptionsMapper.selectAddedOptionsInf(bbsId);
		if (options == null) {
			return false;
		}
		if (options.getCommentAt().equals("Y")) {
			return true;
		}
		return false;
	}

	/**
	 * 댓글에 대한 목록을 조회 한다.
	 * 
	 * @param commentVO
	 */
	public List<EgovMap> selectCommentList(CommentVO commentVO) {
		return bbsCommentMapper.selectCommentList(commentVO);
	}

	/**
	 * 댓글에 대한 목록 총갯수을 조회 한다.
	 * 
	 * @param commentVO
	 */
	public int selectCommentListCnt(CommentVO commentVO) {
		return bbsCommentMapper.selectCommentListCnt(commentVO);
	}

	/**
	 * 댓글에 대한 내용을 조회한다.
	 * 
	 * @param commentVO
	 */
	public CommentVO selectComment(CommentVO commentVO) {
		return bbsCommentMapper.selectComment(commentVO);
	}

	/**
	 * 댓글을 등록한다.
	 * 
	 * @param commentVO
	 */
	public void insertComment(CommentVO commentVO) {
		try {
			commentVO.setCommentNo(answerNoGnrService.getNextLongId() + "");
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		bbsCommentMapper.insertComment(commentVO);
	}

	/**
	 * 댓글에 대한 내용을 수정한다.
	 * 
	 * @param commentVO
	 */
	public void updateComment(CommentVO commentVO) {
		bbsCommentMapper.updateComment(commentVO);
	}

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param commentVO
	 */
	public void deleteComment(CommentVO commentVO) {
		bbsCommentMapper.deleteComment(commentVO);
	}

	/**
	 * 댓글 패스워드를 가져온다.
	 * 
	 * @param commentVO
	 */
	public String getCommentPassword(CommentVO commentVO) {
		return bbsCommentMapper.getCommentPassword(commentVO);
	}
	
}
