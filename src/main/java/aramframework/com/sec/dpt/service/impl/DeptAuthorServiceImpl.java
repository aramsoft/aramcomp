package aramframework.com.sec.dpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.sec.dpt.service.DeptAuthorService;
import aramframework.com.sec.dpt.service.DeptAuthorVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서권한에 관한 ServiceImpl 클래스를 정의한다.
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

@Service("deptAuthorService")
public class DeptAuthorServiceImpl extends EgovAbstractServiceImpl implements DeptAuthorService {

	@Resource(name = "deptAuthorMapper")
	private DeptAuthorMapper deptAuthorMapper;

	/**
	 * 부서별 할당된 권한목록 조회
	 * 
	 * @param deptAuthorVO
	 */
	public List<EgovMap> selectDeptAuthorList(DeptAuthorVO deptAuthorVO) {
		return deptAuthorMapper.selectDeptAuthorList(deptAuthorVO);
	}

	/**
	 * 부서권한 목록조회 카운트를 반환한다
	 * 
	 * @param deptAuthorVO
	 */
	public int selectDeptAuthorListCnt(DeptAuthorVO deptAuthorVO) {
		return deptAuthorMapper.selectDeptAuthorListCnt(deptAuthorVO);
	}

	/**
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * 
	 * @param deptAuthorVO
	 */
	public void insertDeptAuthor(DeptAuthorVO deptAuthorVO) {
		deptAuthorMapper.insertDeptAuthor(deptAuthorVO);
	}

	/**
	 * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * 
	 * @param deptAuthorVO
	 */
	public void updateDeptAuthor(DeptAuthorVO deptAuthorVO) {
		deptAuthorMapper.updateDeptAuthor(deptAuthorVO);
	}

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * 
	 * @param deptAuthorVO
	 */
	public void deleteDeptAuthor(DeptAuthorVO deptAuthorVO) {
		deptAuthorMapper.deleteDeptAuthor(deptAuthorVO);
	}

	/**
	 * 부서목록 조회
	 * 
	 * @param deptAuthorVO
	 */
	public List<EgovMap> selectDeptList(DeptAuthorVO deptAuthorVO) {
		return deptAuthorMapper.selectDeptList(deptAuthorVO);
	}

	/**
	 * 부서 목록조회 카운트를 반환한다
	 * 
	 * @param deptAuthorVO
	 */
	public int selectDeptListCnt(DeptAuthorVO deptAuthorVO) {
		return deptAuthorMapper.selectDeptListCnt(deptAuthorVO);
	}
	
}