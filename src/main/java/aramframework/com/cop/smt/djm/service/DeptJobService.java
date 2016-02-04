package aramframework.com.cop.smt.djm.service;

import java.util.List;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cop.smt.djm.domain.DeptJobBxVO;
import aramframework.com.cop.smt.djm.domain.DeptJobVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 부서업무에 대한 Service Interface를 정의한다.
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

public interface DeptJobService {

	/**
	 * 담당자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectChargerList(SearchVO searchVO);

	/**
	 * 담당자 총갯수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectChargerListCnt(SearchVO searchVO);

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptList(SearchVO searchVO);

	/**
	 * 부서 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectDeptListCnt(SearchVO searchVO);

	/**
	 * 부서 정보를 조회한다.
	 * 
	 * @param orgnztId
	 */
	public String selectDept(String orgnztId);

	/**
	 * 부서업무함 목록 전체를 조회한다.
	 * 
	 */
	public List<EgovMap> selectDeptJobBxListAll();

	/**
	 * 부서업무함 목록을 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public List<EgovMap> selectDeptJobBxList(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 총갯수을 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxCheck(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 등록시 부서업무함의 순서를 조회한다.
	 * 
	 * @param deptId
	 */
	public int selectDeptJobBxOrdr(String deptId);

	/**
	 * 부서업무함 정보를 등록한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void insertDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public boolean updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void deleteDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무 목록을 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public List<EgovMap> selectDeptJobList(DeptJobVO deptJobVO);

	/**
	 * 부서업무 총갯수를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public int selectDeptJobListCnt(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 등록한다.
	 * 
	 * @param deptJobVO
	 * @return
	 */
	public void insertDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 수정한다.
	 * 
	 * @param deptJobVO
	 * @return
	 */
	public void updateDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 삭제한다.
	 * 
	 * @param deptJobVO
	 * @return
	 */
	public void deleteDeptJob(DeptJobVO deptJobVO);

}