package aramframework.com.cop.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.CommentVO;
import aramframework.com.cop.bbs.service.BBSCommentService;
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

@Service("bbsCommentService")
public class BBSCommentServiceImpl extends EgovAbstractServiceImpl implements BBSCommentService {

	@Resource(name = "bbsAddedOptionsMapper")
	private BBSAddedOptionsMapper bbsAddedOptionsMapper;	

	@Resource(name = "bbsCommentMapper")
	private BBSCommentMapper bbsCommentMapper;	

	@Resource(name = "answerNoGnrService")
	private EgovIdGnrService idgenService;
		
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
		CommentVO resultVo = bbsCommentMapper.selectComment(commentVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, commentVO); 
		return resultVo;
	}

	/**
	 * 댓글을 등록한다.
	 * 
	 * @param commentVO
	 */
	public void insertComment(CommentVO commentVO) {
		try {
			commentVO.setCommentNo(idgenService.getNextLongId() + "");
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
