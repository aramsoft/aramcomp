package aramframework.com.cop.smt.mrm.dao;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cop.smt.mrm.domain.MemoReprtVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 메모보고에 대한 DAO 클래스를 정의한다.
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
public interface MemoReprtMapper {

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
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 * 
	 * @param memoReprtVO
	 */
	public List<EgovMap> selectMemoReprtList(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	public int selectMemoReprtListCnt(MemoReprtVO memoReprtVO);

	/**
	 * 주어진 조건에 맞는 메모보고를 불러온다.
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
	 * 메모보고 정보를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprt(MemoReprtVO memoReprtVO);

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	public void updateMemoReprtDrctMatter(MemoReprtVO memoReprtVO);

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