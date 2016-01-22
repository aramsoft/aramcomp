package aramframework.com.cop.smt.djm.service.impl;

import java.util.List;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cop.smt.djm.service.DeptJobBxVO;
import aramframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 부서업무에 대한 DAO 클래스를 정의한다.
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
public interface DeptJobMapper {

	/**
	 * 주어진 조건에 맞는 담당자를 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectChargerList(SearchVO searchVO);

	/**
	 * 담당자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectChargerListCnt(SearchVO searchVO);

	/**
	 * 주어진 조건에 맞는 부서를 불러온다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptList(SearchVO searchVO);

	/**
	 * 부서 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectDeptListCnt(SearchVO searchVO);

	/**
	 * 주어진 조건에 맞는 부서를 불러온다.
	 * 
	 * @param orgnztId
	 */
	public String selectDept(String orgnztId);

	/**
	 * 주어진 조건에 따른 부서업무함 목록 전체를 불러온다.
	 * 
	 */
	public List<EgovMap> selectDeptJobBxListAll();

	/**
	 * 주어진 조건에 따른 부서업무함 목록을 불러온다.
	 * 
	 * @param deptJobBxVO
	 */
	public List<EgovMap> selectDeptJobBxList(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO);

	/**
	 * 주어진 조건에 맞는 부서업무함을 불러온다.
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxCheck(DeptJobBxVO deptJobBxVO);

	/**
	 * 등록시 부서업무함의 표시순서를 조회한다.
	 * 
	 * @param deptId
	 */
	public int selectMaxDeptJobBxOrdr(String deptId);

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
	 * 부서업무함의 표시순서가 중복되는지를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxOrdr(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보의 표시순서를 수정한다. (표시순서 증가)
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBxOrdrUp(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보의 표시순서를 수정한다. (표시순서 감소)
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBxOrdrDown(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO);

	/**
	 * 주어진 조건에 만족하는 전체 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBxOrdrAll(DeptJobBxVO deptJobBxVO);

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void deleteDeptJobBx(DeptJobBxVO deptJobBxVO);

	/**
	 * 주어진 조건에 따른 부서업무 목록을 불러온다.
	 * 
	 * @param deptJobVO
	 */
	public List<EgovMap> selectDeptJobList(DeptJobVO deptJobVO);

	/**
	 * 부서업무 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public int selectDeptJobListCnt(DeptJobVO deptJobVO);

	/**
	 * 주어진 조건에 맞는 부서업무를 불러온다.
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 등록한다.
	 * 
	 * @param deptJobVO
	 */
	public void insertDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 수정한다.
	 * 
	 * @param deptJobVO
	 */
	public void updateDeptJob(DeptJobVO deptJobVO);

	/**
	 * 부서업무 정보를 삭제한다.
	 * 
	 * @param deptJobVO
	 */
	public void deleteDeptJob(DeptJobVO deptJobVO); 

}