package aramframework.com.cop.smt.mrm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.smt.mrm.domain.MemoReprtVO;

/**
 * 개요 - 메모보고에 대한 Service Interface를 정의한다.
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

public interface MemoReprtService {
	/**
	 * 보고자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectReportrList(SearchVO searchVO);

	/**
	 * 보고자 총횟수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectReportrListCnt(SearchVO searchVO);

	/**
	 * 사용자 직위명을 정보를 조회한다.
	 * 
	 * @param wrterId
	 */
	public String selectWrterClsfNm(String wrterId);

	/**
	 * 메모보고 목록을 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public List<EgovMap> selectMemoReprtList(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 총횟수를 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public int selectMemoReprtListCnt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보를 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보를 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	public void insertMemoReprt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprtDrctMatter(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보의 보고자 조회일시를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	public void readMemoReprt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보를 삭제한다.
	 * 
	 * @param memoReprtVO
	 */
	public void deleteMemoReprt(MemoReprtVO memoReprtVO);

}