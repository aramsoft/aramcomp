package aramframework.com.sym.sym.bak.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.sym.sym.bak.service.BackupResultVO;
import aramframework.com.sym.sym.bak.service.BackupResultService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 백업결과관리에 대한 ServiceImpl 클래스를 정의한다.
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

@Service("backupResultService")
public class BackupResultServiceImpl extends EgovAbstractServiceImpl implements BackupResultService {

	@Autowired
	private BackupResultMapper backupResultMapper;
	
	/**
	 * 백업결과의 목록을 조회 한다.
	 * 
	 * @param backupResultVO
	 */
	public List<BackupResultVO> selectBackupResultList(BackupResultVO backupResultVO) {
		return backupResultMapper.selectBackupResultList(backupResultVO);
	}

	/**
	 * 백업결과 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param backupResultVO
	 */
	public int selectBackupResultListCnt(BackupResultVO backupResultVO) {
		return backupResultMapper.selectBackupResultListCnt(backupResultVO);
	}

	/**
	 * 백업결과을 상세조회 한다.
	 * 
	 * @param backupResultVO
	 */
	public BackupResultVO selectBackupResult(BackupResultVO backupResultVO) {
		BackupResultVO resultVo = backupResultMapper.selectBackupResult(backupResultVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, backupResultVO); 
		return resultVo;
	}

	/**
	 * 백업결과을 삭제한다.
	 * 
	 * @param backupResultVO
	 */
	public void deleteBackupResult(BackupResultVO backupResultVO) {
		backupResultMapper.deleteBackupResult(backupResultVO);
	}

}