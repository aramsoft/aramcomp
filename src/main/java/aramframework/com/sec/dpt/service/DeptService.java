package aramframework.com.sec.dpt.service;

import java.util.List;


import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서관리 Interface.
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

public interface DeptService {

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * 
	 * @param depteVO
	 */
	public List<EgovMap> selectDeptList(DeptVO deptVO);

	/**
	 * 부서목록 총 갯수를 조회한다.
	 * 
	 * @param depteVO
	 */
	public int selectDeptListCnt(DeptVO deptVO);

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * 
	 * @param depteVO
	 */
	public DeptVO selectDept(DeptVO deptVO);

	/**
	 * 부서정보를 신규로 등록한다.
	 * 
	 * @param depteVO
	 */
	public void insertDept(DeptVO deptVO);

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * 
	 * @param depteVO
	 */
	public void updateDept(DeptVO deptVO);

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * 
	 * @param depteVO
	 */
	public void deleteDept(DeptVO deptVO);
	
}
