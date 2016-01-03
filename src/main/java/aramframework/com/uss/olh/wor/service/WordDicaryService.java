package aramframework.com.uss.olh.wor.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 용어사전 처리를 위한 서비스 클래스
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

public interface WordDicaryService {

	/**
	 * 용어사전 목록을 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	List<EgovMap> selectWordDicaryList(WordDicaryVO wordDicaryVO);

	/**
	 * 글 총 갯수를 조회한다
	 * 
	 * @param wordDicaryVO
	 */
	int selectWordDicaryListCnt(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전 상세 조회를 한다.
	 * 
	 * @param wordDicaryVO
	 */
	WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전내용을 등록한다.
	 * 
	 * @param wordDicaryVO
	 */
	void insertWordDicary(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전내용을 수정한다.
	 * 
	 * @param wordDicaryVO
	 */
	void updateWordDicary(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전을 삭제한다.
	 * 
	 * @param wordDicaryVO
	 */
	void deleteWordDicary(WordDicaryVO wordDicaryVO);

}
