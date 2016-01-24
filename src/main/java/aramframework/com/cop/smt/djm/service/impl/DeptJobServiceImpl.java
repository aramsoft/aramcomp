package aramframework.com.cop.smt.djm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cop.smt.djm.service.DeptJobBxVO;
import aramframework.com.cop.smt.djm.service.DeptJobVO;
import aramframework.com.cop.smt.djm.service.DeptJobService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요 - 부서업무에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("deptJobService")
public class DeptJobServiceImpl extends EgovAbstractServiceImpl implements DeptJobService {

	@Autowired 
	private DeptJobMapper deptJobMapper;	

	@Autowired 
	private EgovIdGnrService deptJobIdGnrService; 

	@Autowired 
	private EgovIdGnrService deptJobBxIdGnrService; 

	@Autowired 
	private FileMngUtil fileMngUtil; 

	/**
	 * 담당자 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectChargerList(SearchVO searchVO) {
		return  deptJobMapper.selectChargerList(searchVO);
	}

	/**
	 * 담당자 총갯수를 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectChargerListCnt(SearchVO searchVO) {
		return deptJobMapper.selectChargerListCnt(searchVO);
	}

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	public List<EgovMap> selectDeptList(SearchVO searchVO) {
		return  deptJobMapper.selectDeptList(searchVO);
	}

	/**
	 * 부서 총갯수을 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectDeptListCnt(SearchVO searchVO) {
		return deptJobMapper.selectDeptListCnt(searchVO);
	}

	/**
	 * 부서 정보를 조회한다.
	 * 
	 * @param String
	 */
	public String selectDept(String orgnztId) {
		return deptJobMapper.selectDept(orgnztId);
	}

	/**
	 * 부서업무함 목록 전체를 조회한다.
	 * 
	 */
	public List<EgovMap> selectDeptJobBxListAll() {
		return deptJobMapper.selectDeptJobBxListAll();
	}

	/**
	 * 부서업무함 목록을 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public List<EgovMap> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) {
		return deptJobMapper.selectDeptJobBxList(deptJobBxVO);
	}

	/**
	 * 부서업무함 총갯수을 조회한다.
	 * 
	 * @param deptJobBxVO
 	 */
	public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO) {
		return deptJobMapper.selectDeptJobBxListCnt(deptJobBxVO);
	}

	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * 
	 * @param deptJobBx
	 */
	public int selectDeptJobBxCheck(DeptJobBxVO deptJobBxVO) {
		return deptJobMapper.selectDeptJobBxCheck(deptJobBxVO);
	}

	/**
	 * 부서업무함을 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) {
		DeptJobBxVO resultVo = deptJobMapper.selectDeptJobBx(deptJobBxVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, deptJobBxVO); 
		return resultVo;
	}

	/**
	 * 등록시 부서업무함의 순서를 조회한다.
	 * 
	 * @param deptId
	 */
	public int selectDeptJobBxOrdr(String deptId) {
		return deptJobMapper.selectMaxDeptJobBxOrdr(deptId);
	}

	/**
	 * 부서업무함 정보를 등록한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void insertDeptJobBx(DeptJobBxVO deptJobBxVO) {
		try {
			deptJobBxVO.setDeptJobBxId(deptJobBxIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		if (deptJobMapper.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
			deptJobMapper.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobMapper.insertDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBx(DeptJobBxVO deptJobBxVO) {
		if (deptJobMapper.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
			deptJobMapper.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobMapper.updateDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	public boolean updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) {

		boolean changed = false;
		if (deptJobBxVO.getOrdrCnd().equals("up")) {
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() - 1);
			if (deptJobMapper.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
				deptJobMapper.updateDeptJobBxOrdrUp(deptJobBxVO);
				deptJobMapper.updateDeptJobBxOrdr(deptJobBxVO);

				changed = true;
			}
		} else if (deptJobBxVO.getOrdrCnd().equals("down")) {
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() + 1);
			if (deptJobMapper.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
				deptJobMapper.updateDeptJobBxOrdrDown(deptJobBxVO);
				deptJobMapper.updateDeptJobBxOrdr(deptJobBxVO);

				changed = true;
			}
		}

		return changed;
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param deptJobBxVO
	 */
	public void deleteDeptJobBx(DeptJobBxVO deptJobBxVO) {
		deptJobMapper.deleteDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무 목록을 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public List<EgovMap> selectDeptJobList(DeptJobVO deptJobVO) {
		return deptJobMapper.selectDeptJobList(deptJobVO);
	}

	/**
	 * 부서업무 총갯수를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public int selectDeptJobListCnt(DeptJobVO deptJobVO) {
		return deptJobMapper.selectDeptJobListCnt(deptJobVO);
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) {
		DeptJobVO resultVo = deptJobMapper.selectDeptJob(deptJobVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, deptJobVO); 
		return resultVo;
	}

	/**
	 * 부서업무를 등록한다.
	 * 
	 * @param deptJobVO
	 */
	public void insertDeptJob(DeptJobVO deptJobVO) {
		try {
			deptJobVO.setDeptJobId(deptJobIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		deptJobMapper.insertDeptJob(deptJobVO);
	}

	/**
	 * 부서업무를 수정한다.
	 * 
	 * @param deptJobVO
	 */
	public void updateDeptJob(DeptJobVO deptJobVO) {
		deptJobMapper.updateDeptJob(deptJobVO);
	}

	/**
	 * 부서업무를 삭제한다.
	 * 
	 * @param deptJobVO
	 */
	public void deleteDeptJob(DeptJobVO deptJobVO) {
		// 첨부파일 삭제 ....
		fileMngUtil.deleteMultiFile(deptJobVO.getAtchFileId());

		deptJobMapper.deleteDeptJob(deptJobVO);
	}

}