package aramframework.com.sym.sym.bak.service;

import java.util.List;

import aramframework.com.sym.sym.bak.domain.BackupOpertVO;
import aramframework.com.sym.sym.bak.domain.BackupResultVO;

/**
 * 백업작업관리에 대한 Service Interface를 정의한다.
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

public interface BackupOpertService {

	/**
	 * 백업작업 목록을 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	public List<BackupOpertVO> selectBackupOpertList(BackupOpertVO backupOpertVO);

	/**
	 * 백업작업 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	public int selectBackupOpertListCnt(BackupOpertVO backupOpertVO);

	/**
	 * 백업작업을 상세조회 한다.
	 * 
	 * @param backupOpertVO
	 */
	public BackupOpertVO selectBackupOpert(BackupOpertVO backupOpertVO);

	/**
	 * 백업작업을 등록한다.
	 * 
	 * @param backupOpertVO
	 */
	public void insertBackupOpert(BackupOpertVO backupOpertVO);

	/**
	 * 백업작업을 수정한다.
	 * 
	 * @param backupOpertVO
	 */
	public void updateBackupOpert(BackupOpertVO backupOpertVO);

	/**
	 * 백업작업을 삭제한다.
	 * 
	 * @param backupOpertVO
	 */
	public void deleteBackupOpert(BackupOpertVO backupOpertVO);

	/**
	 * 백업결과를 등록한다.
	 * 
	 * @param backupResultVO
	 */
	public void insertBackupResult(BackupResultVO backupResultVO);

	/**
	 * 백업결과정보를 수정한다.
	 * 
	 * @param backupResultVO
	 */
	public void updateBackupResult(BackupResultVO backupResultVO);

}