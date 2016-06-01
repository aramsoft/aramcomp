package aramframework.com.sym.sym.bak.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import aramframework.com.sym.sym.bak.domain.BackupOpertVO;
import aramframework.com.sym.sym.bak.domain.BackupSchdulDfkVO;

/**
 * 백업작업관리에 대한 DAO 클래스를 정의한다.
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

@Repository
public class BackupOpertMapper extends EgovAbstractMapper {

	final static String NAMESPACE = BackupOpertMapper.class.getName();

	/**
	 * 백업작업정보목록을 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	public List<BackupOpertVO> selectBackupOpertList(BackupOpertVO backupOpertVO) {
		List<BackupOpertVO> resultList = selectList(NAMESPACE+".selectBackupOpertList", backupOpertVO);

		for (int i = 0; i < resultList.size(); i++) {
			BackupOpertVO result = (BackupOpertVO) resultList.get(i);
			// 스케줄요일정보를 가져온다.
			List<BackupSchdulDfkVO> dfkSeList = selectList(NAMESPACE+".selectBackupSchdulDfkList", result.getBackupOpertId());
			String[] dfkSes = new String[dfkSeList.size()];
			for (int j = 0; j < dfkSeList.size(); j++) {
				dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
			}
			result.setExecutSchdulDfkSes(dfkSes);
			// 화면표시용 실행스케줄 속성을 만든다.
			result.makeExecutSchdul(dfkSeList);
		}
		return resultList;
	}

	/**
	 * 백업작업 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	public int selectBackupOpertListCnt(BackupOpertVO backupOpertVO) {
		return (Integer) getSqlSession().selectOne(NAMESPACE+".selectBackupOpertListCnt", backupOpertVO);
	}

	/**
	 * 백업작업정보를 상세조회 한다.
	 * 
	 * @param backupOpertVO
	 */
	public BackupOpertVO selectBackupOpert(BackupOpertVO backupOpertVO) {
		BackupOpertVO result = (BackupOpertVO) selectOne(NAMESPACE+".selectBackupOpert", backupOpertVO);

		// 스케줄요일정보를 가져온다.
		List<BackupSchdulDfkVO> dfkSeList = selectList(NAMESPACE+".selectBackupSchdulDfkList", backupOpertVO.getBackupOpertId());
		String[] dfkSes = new String[dfkSeList.size()];
		for (int j = 0; j < dfkSeList.size(); j++) {
			dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
		}
		result.setExecutSchdulDfkSes(dfkSes);
		// 화면표시용 실행스케줄 속성을 만든다.
		result.makeExecutSchdul(dfkSeList);

		return result;
	}

	/**
	 * 백업작업을 등록한다.
	 * 
	 * @param backupOpertVO
	 */
	public void insertBackupOpert(BackupOpertVO backupOpertVO) {
		// master 테이블 인서트
		insert(NAMESPACE+".insertBackupOpert", backupOpertVO);
		// slave 테이블 인서트
		if (backupOpertVO.getExecutSchdulDfkSes() != null && backupOpertVO.getExecutSchdulDfkSes().length != 0) {
			String backupOpertId = backupOpertVO.getBackupOpertId();
			String[] dfkSes = backupOpertVO.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BackupSchdulDfkVO backupSchdulDfkVO = new BackupSchdulDfkVO();
				backupSchdulDfkVO.setBackupOpertId(backupOpertId);
				backupSchdulDfkVO.setExecutSchdulDfkSe(dfkSes[i]);
				insert(NAMESPACE+".insertBackupSchdulDfk", backupSchdulDfkVO);
			}
		}
	}

	/**
	 * 백업작업정보를 수정한다.
	 * 
	 * @param backupOpertVO
	 */
	public void updateBackupOpert(BackupOpertVO backupOpertVO) {
		update(NAMESPACE+".updateBackupOpert", backupOpertVO);
		// slave 테이블 삭제
		delete(NAMESPACE+".deleteBackupSchdulDfk", backupOpertVO.getBackupOpertId());
		// slave 테이블 인서트
		if (backupOpertVO.getExecutSchdulDfkSes() != null && backupOpertVO.getExecutSchdulDfkSes().length != 0) {
			String backupOpertId = backupOpertVO.getBackupOpertId();
			String[] dfkSes = backupOpertVO.getExecutSchdulDfkSes();
			for (int i = 0; i < dfkSes.length; i++) {
				BackupSchdulDfkVO backupSchdulDfkVO = new BackupSchdulDfkVO();
				backupSchdulDfkVO.setBackupOpertId(backupOpertId);
				backupSchdulDfkVO.setExecutSchdulDfkSe(dfkSes[i]);
				insert(NAMESPACE+".insertBackupSchdulDfk", backupSchdulDfkVO);
			}
		}
	}

	/**
	 * 백업작업을 삭제한다.
	 * 
	 * @param backupOpertVO
	 */
	public void deleteBackupOpert(BackupOpertVO backupOpertVO) {
		// slave 테이블 삭제
		delete(NAMESPACE+".deleteBackupSchdulDfk", backupOpertVO.getBackupOpertId());
		// master 테이블 삭제
		delete(NAMESPACE+".deleteBackupOpert", backupOpertVO);
	}

}