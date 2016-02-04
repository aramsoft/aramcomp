package aramframework.com.uss.olh.wor.service;

import java.util.List;

import aramframework.com.uss.olh.wor.domain.WordDicaryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 용어사전을 처리하는 DAO 클래스
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
public interface WordDicaryMapper {

	/**
	 * 용어사전 목록을 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public List<EgovMap> selectWordDicaryList(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전 총 갯수를 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public int selectWordDicaryListCnt(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전을 상세조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	public WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전을 등록한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void insertWordDicary(WordDicaryVO wordDicaryVO);

	/**
	 * 용어사전을 수정한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void updateWordDicary(WordDicaryVO wordDicaryVO); 

	/**
	 * 용어사전을 삭제한다.
	 * 
	 * @param wordDicaryVO
	 */
	public void deleteWordDicary(WordDicaryVO wordDicaryVO);

}
