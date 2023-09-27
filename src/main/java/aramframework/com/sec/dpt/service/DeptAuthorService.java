package aramframework.com.sec.dpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.dpt.dao.DeptAuthorMapper;
import aramframework.com.sec.dpt.domain.DeptAuthorVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서권한에 관한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class DeptAuthorService extends EgovAbstractServiceImpl {

	@Autowired
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
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * 
	 * @param deptAuthorVO
	 */
	public void insertDeptAuthors(String[] userIds, String[] authorCodes, String[] regYns) {
		
		DeptAuthorVO daVO = new DeptAuthorVO();
		for (int i = 0; i < userIds.length; i++) {
			daVO.setUserId(userIds[i]);
			daVO.setAuthorCode(authorCodes[i]);
			if (regYns[i].equals("N"))
				deptAuthorMapper.insertDeptAuthor(daVO);
			else
				deptAuthorMapper.updateDeptAuthor(daVO);
		}
	}

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * 
	 * @param deptAuthorVO
	 */
	public void deleteDeptAuthors(String[] userIds) {
		DeptAuthorVO daVO = new DeptAuthorVO();
		for (int i = 0; i < userIds.length; i++) {
			daVO.setUserId(userIds[i]);
			deptAuthorMapper.deleteDeptAuthor(daVO);
		}
	}

}