package aramframework.com.sec.dpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sec.dpt.dao.DeptMapper;
import aramframework.com.sec.dpt.domain.DeptVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 부서관리에 관한 비지니스클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class DeptService extends EgovAbstractServiceImpl {

	@Autowired
	private DeptMapper deptMapper;
	
	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService deptIdGnrService; 

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * 
	 * @param deptVO
	 */
	public List<EgovMap> selectDeptList(DeptVO deptVO) {
		return deptMapper.selectDeptList(deptVO);
	}

	/**
	 * 부서목록 총 갯수를 조회한다.
	 * 
	 * @param deptVO
	 */
	public int selectDeptListCnt(DeptVO deptVO) {
		return deptMapper.selectDeptListCnt(deptVO);
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * 
	 * @param deptVO
	 */
	public DeptVO selectDept(DeptVO deptVO) {
		return deptMapper.selectDept(deptVO);
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * 
	 * @param deptVO
	 */
	public void insertDept(DeptVO deptVO) {
		try {
			deptVO.setOrgnztId(deptIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		deptMapper.insertDept(deptVO);
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * 
	 * @param deptVO
	 */
	public void updateDept(DeptVO deptVO) {
		deptMapper.updateDept(deptVO);
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * 
	 * @param deptVO
	 */
	public void deleteDept(DeptVO deptVO) {
		deptMapper.deleteDept(deptVO);
	}
	
	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * 
	 * @param deptVO
	 */
	public void deleteDepts(String orgnztIds) {
		String[] strOrgnztIds = orgnztIds.split(";");
		DeptVO dmVO = new DeptVO();
		for (int i = 0; i < strOrgnztIds.length; i++) {
			dmVO.setOrgnztId(strOrgnztIds[i]);
			deptMapper.deleteDept(dmVO);
		}
	}
	
}
