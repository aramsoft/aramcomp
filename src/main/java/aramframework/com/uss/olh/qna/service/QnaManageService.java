package aramframework.com.uss.olh.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.olh.qna.dao.QnaManageMapper;
import aramframework.com.uss.olh.qna.domain.QnaManageVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * Q&A정보를 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class QnaManageService extends EgovAbstractServiceImpl {

	@Autowired
	private QnaManageMapper qnaManageMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService qnaManageIdGnrService;

	/**
	 * Q&A 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public List<EgovMap> selectQnaList(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaList(qnaManageVO);
	}

	/**
	 * Q&A 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaListCnt(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaListCnt(qnaManageVO);
	}

	/**
	 * Q&A 글을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public QnaManageVO selectQnaListDetail(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaListDetail(qnaManageVO);
	}

	/**
	 * Q&A 글을 수정한다.(조회수를 수정)
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaInqireCo(QnaManageVO qnaManageVO) {
		qnaManageMapper.updateQnaInqireCo(qnaManageVO);
	}

	/**
	 * Q&A 글을 등록한다.
	 * 
	 * @param qnaManageVO
	 */
	public void insertQnaCn(QnaManageVO qnaManageVO) {
		try {
			qnaManageVO.setQaId(qnaManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		qnaManageMapper.insertQnaCn(qnaManageVO);
	}

	/**
	 * 작성비밀번호를 확인한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaPasswordConfirmCnt(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaPasswordConfirmCnt(qnaManageVO);
	}

	/**
	 * Q&A 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaCn(QnaManageVO qnaManageVO) {
		qnaManageMapper.updateQnaCn(qnaManageVO);
	}

	/**
	 * Q&A 글을 삭제한다.
	 * 
	 * @param qnaManageVO
	 */
	public void deleteQnaCn(QnaManageVO qnaManageVO) {
		qnaManageMapper.deleteQnaCn(qnaManageVO);
	}

	/**
	 * Q&A 답변 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public List<EgovMap> selectQnaAnswerList(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaAnswerList(qnaManageVO);
	}

	/**
	 * Q&A 답변 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaAnswerListCnt(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaListCnt(qnaManageVO);
	}

	/**
	 * Q&A 답변 글을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public QnaManageVO selectQnaAnswerDetail(QnaManageVO qnaManageVO) {
		return qnaManageMapper.selectQnaAnswerDetail(qnaManageVO);
	}

	/**
	 * Q&A 답변 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaCnAnswer(QnaManageVO qnaManageVO) {
		qnaManageMapper.updateQnaCnAnswer(qnaManageVO);
	}

}
