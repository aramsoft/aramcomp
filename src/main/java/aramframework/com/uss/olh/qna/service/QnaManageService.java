package aramframework.com.uss.olh.qna.service;

import java.util.List;

import aramframework.com.uss.olh.qna.domain.QnaManageVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * Q&A를 처리하는 서비스 클래스
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

public interface QnaManageService {

	/**
	 * Q&A 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	List<EgovMap> selectQnaList(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	int selectQnaListCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	QnaManageVO selectQnaListDetail(QnaManageVO qnaManageVO);

	/**
	 * Q&A 조회수를 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	void updateQnaInqireCo(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 등록한다.
	 * 
	 * @param qnaManageVO
	 */
	void insertQnaCn(QnaManageVO qnaManageVO);

	/**
	 * Q&A 작성비밀번호를 확인한다.
	 * 
	 * @param qnaManageVO
	 */
	int selectQnaPasswordConfirmCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	void updateQnaCn(QnaManageVO qnaManageVO);

	/**
	 * Q&A 글을 삭제한다.
	 * 
	 * @param qnaManageVO
	 */
	void deleteQnaCn(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글 목록을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	List<EgovMap> selectQnaAnswerList(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글 총 갯수를 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	int selectQnaAnswerListCnt(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글을 조회한다.
	 * 
	 * @param qnaManageVO
	 */
	QnaManageVO selectQnaAnswerDetail(QnaManageVO qnaManageVO);

	/**
	 * Q&A 답변 글을 수정한다.
	 * 
	 * @param qnaManageVO
	 */
	void updateQnaCnAnswer(QnaManageVO qnaManageVO);

}
