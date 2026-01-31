package aramframework.com.uss.olh.qna.dao;

import java.util.List;

import aramframework.com.uss.olh.qna.domain.QnaManageVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * Q&A정보를 처리하는 DAO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface QnaManageMapper {

	/**
	 * Q&A 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public List<EgovMap> selectQnaList(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaListCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public QnaManageVO selectQnaListDetail(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 수정한다.(조회수를 수정)
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaInqireCo(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 등록한다.
	 * 
	 * @param qnaManageVO
	 */
	public void insertQnaCn(QnaManageVO qnaManageVO);

	/**
	 * 작성비밀번호를 확인한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaPasswordConfirmCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaCn(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 삭제한다.
	 * 
	 * @param qnaManageVO
	 */
	public void deleteQnaCn(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public List<EgovMap> selectQnaAnswerList(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public int selectQnaAnswerListCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	public QnaManageVO selectQnaAnswerDetail(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	public void updateQnaCnAnswer(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글을 삭제한다.
	 * 
	 * @param qnaManageVO
	 */
	public void deleteQnaCnAnswer(QnaManageVO qnaManageVO);

}
