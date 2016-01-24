package aramframework.com.uss.olh.qna.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olh.qna.service.QnaManageService;
import aramframework.com.uss.olh.qna.service.QnaManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * Q&A정보를 처리하는 비즈니스 구현 클래스
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

@Service("qnaManageService")
public class QnaManageServiceImpl extends EgovAbstractServiceImpl implements QnaManageService {

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
		QnaManageVO resultVo = qnaManageMapper.selectQnaListDetail(qnaManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qnaManageVO); 
		return resultVo;
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
		QnaManageVO resultVo = qnaManageMapper.selectQnaAnswerDetail(qnaManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, qnaManageVO); 
		return resultVo;
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
