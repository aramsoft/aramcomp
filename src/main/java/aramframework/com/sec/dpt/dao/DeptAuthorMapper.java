package aramframework.com.sec.dpt.dao;

import java.util.List;

import aramframework.com.sec.dpt.domain.DeptAuthorVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서권한에 대한 DAO 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface DeptAuthorMapper {

	/**
	 * 부서별 할당된 권한목록 조회
	 * 
	 * @param deptAuthorVO
	 */
	public List<EgovMap> selectDeptAuthorList(DeptAuthorVO deptAuthorVO);

	/**
	 * 부서권한 목록조회 카운트를 반환한다
	 * 
	 * @param deptAuthorVO
	 */
	public int selectDeptAuthorListCnt(DeptAuthorVO deptAuthorVO);

	/**
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * 
	 * @param deptAuthorVO
	 */
	public void insertDeptAuthor(DeptAuthorVO deptAuthorVO);

	/**
	 * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param deptAuthorVO
	 */
	public void updateDeptAuthor(DeptAuthorVO deptAuthorVO);

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * 
	 * @param deptAuthorVO
	 */
	public void deleteDeptAuthor(DeptAuthorVO deptAuthorVO);

	/**
	 * 부서목록 조회
	 * 
	 * @param deptAuthorVO
	 */
	public List<EgovMap> selectDeptList(DeptAuthorVO deptAuthorVO);

	/**
	 * 부서목록 조회 카운트를 반환한다
	 * 
	 * @param deptAuthorVO
	 */
	public int selectDeptListCnt(DeptAuthorVO deptAuthorVO);
	
}