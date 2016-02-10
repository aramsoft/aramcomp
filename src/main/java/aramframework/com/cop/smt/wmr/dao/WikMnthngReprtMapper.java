package aramframework.com.cop.smt.wmr.dao;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cop.smt.wmr.domain.WikMnthngReprtVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 주간월간보고에 대한 ServiceImpl 클래스를 정의한다.
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
public interface WikMnthngReprtMapper {

	/**
	 * 주어진 조건에 맞는 보고자를 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectReportrList(SearchVO searchVO);

	/**
	 * 보고자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectReportrListCnt(SearchVO searchVO);

	/**
	 * 주어진 조건에 맞는 직위명을 불러온다.
	 * 
	 * @param wrterId
	 */
	public String selectWrterClsfNm(String wrterId);

	/**
	 * 주어진 조건에 맞는 주간월간보고를 불러온다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public List<EgovMap> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주어진 조건에 맞는 주간월간보고 을 불러온다.
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
	 * 주간월간보고 정보를 삭제한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void deleteWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	/**
	 * 주간월간보고 정보를 승인한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	public void confirmWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

}