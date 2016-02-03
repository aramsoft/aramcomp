package aramframework.com.cop.smt.wmr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.smt.wmr.domain.WikMnthngReprtVO;

/**
 * 개요 - 주간월간보고에 대한 Service Interface를 정의한다.
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

public interface WikMnthngReprtService {

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
	 * 사용자 직위명 정보를 조회한다.
	 * 
	 * @param wrterId
	 */
	public String selectWrterClsfNm(String wrterId);

	/**
	 * 주간월간보고 목록을 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public List<EgovMap> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 총횟수를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 등록한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void insertWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 수정한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void updateWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 승인한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void confirmWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void deleteWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

}