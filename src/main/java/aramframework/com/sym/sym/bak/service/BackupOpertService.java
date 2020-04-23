package aramframework.com.sym.sym.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sym.sym.bak.dao.BackupOpertMapper;
import aramframework.com.sym.sym.bak.dao.BackupResultMapper;
import aramframework.com.sym.sym.bak.domain.BackupOpertVO;
import aramframework.com.sym.sym.bak.domain.BackupResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 백업작업관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class BackupOpertService extends EgovAbstractServiceImpl {

	@Autowired
	private BackupOpertMapper backupOpertMapper;
	
	@Autowired
	private BackupResultMapper backupResultMapper;	

	/** ID Generation */
	@Autowired
	private EgovIdGnrService backupOpertIdGnrService; 

	/**
	 * 백업작업의 목록을 조회 한다.
	 * 
	 * @param backupOpertVO
	 */
	public List<BackupOpertVO> selectBackupOpertList(BackupOpertVO backupOpertVO) {
		return backupOpertMapper.selectBackupOpertList(backupOpertVO);
	}

	/**
	 * 백업작업 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	public int selectBackupOpertListCnt(BackupOpertVO backupOpertVO) {
		return backupOpertMapper.selectBackupOpertListCnt(backupOpertVO);
	}

	/**
	 * 백업작업을 상세조회 한다.
	 * 
	 * @param backupOpertVO
	 */
	public BackupOpertVO selectBackupOpert(BackupOpertVO backupOpertVO) {
		return backupOpertMapper.selectBackupOpert(backupOpertVO);
	}

	/**
	 * 백업작업을 등록한다.
	 * 
	 * @param backupOpertVO
	 */
	public void insertBackupOpert(BackupOpertVO backupOpertVO) {
		try {
			backupOpertVO.setBackupOpertId(backupOpertIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		backupOpertMapper.insertBackupOpert(backupOpertVO);
	}

	/**
	 * 백업작업정보를 수정한다.
	 * 
	 * @param backupOpertVO
	 */
	public void updateBackupOpert(BackupOpertVO backupOpertVO) {
		backupOpertMapper.updateBackupOpert(backupOpertVO);
	}

	/**
	 * 백업작업을 삭제한다.
	 * 
	 * @param backupOpertVO
	 */
	public void deleteBackupOpert(BackupOpertVO backupOpertVO) {
		backupOpertMapper.deleteBackupOpert(backupOpertVO);
	}

	/**
	 * 백업결과를 등록한다.
	 * 
	 * @param backupResultVO
	 */
	public void insertBackupResult(BackupResultVO backupResultVO) {
		backupResultMapper.insertBackupResult(backupResultVO);
	}

	/**
	 * 백업결과정보를 수정한다.
	 * 
	 * @param backupResultVO
	 */
	public void updateBackupResult(BackupResultVO backupResultVO) {
		backupResultMapper.updateBackupResult(backupResultVO);
	}

}