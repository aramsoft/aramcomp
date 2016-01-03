package aramframework.com.uss.olp.cns.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 상담내용을 처리하는 비즈니스 구현 클래스
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

public interface CnsltManageService {


	/**
	 * 상담내용 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	List<EgovMap> selectCnsltList(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	int selectCnsltListCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	CnsltManageVO selectCnsltListDetail(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * 
	 * @param cnsltManageVO
	 */
	void updateCnsltInqireCo(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 등록한다.
	 * 
	 * @param cnsltManageVO
	 */
	void insertCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 작성비밀번호를 확인한다.
	 * 
	 * @param cnsltManageVO
	 */
	int selectCnsltPasswordConfirmCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	void updateCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 상담내용 글을 삭제한다.
	 * 
	 * @param cnsltManageVO
	 */
	void deleteCnsltDtls(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글 목록을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	List<EgovMap> selectCnsltAnswerList(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글 총 갯수를 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	int selectCnsltAnswerListCnt(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글을 조회한다.
	 * 
	 * @param cnsltManageVO
	 */
	CnsltManageVO selectCnsltAnswerDetail(CnsltManageVO cnsltManageVO);

	/**
	 * 상담답변 글을 수정한다.
	 * 
	 * @param cnsltManageVO
	 */
	void updateCnsltDtlsAnswer(CnsltManageVO cnsltManageVO);

}
