package aramframework.com.sym.sym.bak.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sym.sym.bak.domain.BackupResultVO;

/**
 * 백업결과관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface BackupResultMapper {

	/**
	 * 백업결과정보목록을 조회한다.
	 * 
	 * @param backupResultVO
	 */
	public List<BackupResultVO> selectBackupResultList(BackupResultVO backupResultVO);

	/**
	 * 백업결과 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param backupResultVO
	 */
	public int selectBackupResultListCnt(BackupResultVO backupResultVO);

	/**
	 * 백업결과정보를 상세조회 한다.
	 * 
	 * @param backupResultVO
	 */
	public BackupResultVO selectBackupResult(BackupResultVO backupResultVO);

	/**
	 * 백업결과을 등록한다.
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

	/**
	 * 백업결과을 삭제한다.
	 * 
	 * @param backupResultVO
	 */
	public void deleteBackupResult(BackupResultVO backupResultVO);

}