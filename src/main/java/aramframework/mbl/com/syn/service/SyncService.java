package aramframework.mbl.com.syn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.mbl.com.syn.dao.SyncMapper;
import aramframework.mbl.com.syn.domain.SyncVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 동기화 서비스에 대한 Service Interface를 구현한다.
 * 
 * 상세내용
 * - 동기화 서비스에 대한 등록, 삭제, 조회 기능을 제공한다.
 * - 동기화 서비스에 대한 조회기능은 목록, 상세조회로 구분된다.
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

@Service
public class SyncService extends EgovAbstractServiceImpl {
	
	@Autowired
    private SyncMapper syncMapper;	
	
	/**
	 * 동기화 서비스 글 목록 조회
	 * 
	 * @param syncVO
	 */
	public List<EgovMap> selectSyncList(SyncVO syncVO) {
		syncVO.setFetchRow(syncVO.getFetchRow() * 5);
		return syncMapper.selectSyncList(syncVO);
	}

	/**
	 * 동기화 서비스 글 총 갯수
	 * 
	 * @param syncVO
	 */
	public int selectSyncListCnt(SyncVO syncVO) {
		return syncMapper.selectSyncListCnt(syncVO);
	}
	
	/**
	 * 동기화 서비스글 상세 조회 
	 * 
	 * @param syncVO
	 */
	public SyncVO selectSync(SyncVO syncVO) {
		SyncVO resultVo = syncMapper.selectSync(syncVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, syncVO); 
		return resultVo;
	}
	
	/**
	 * 동기호 서비스 글 등록 
	 * 
	 * @param syncVO
	 */
	public int insertSync(SyncVO syncVO) {
		try {
			syncVO.setSn(syncMapper.selectSyncNewSn());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return syncMapper.insertSync(syncVO);
	}
	
	/**
	 * 동기화 서비스 글 수정
	 * 
	 * @param syncVO
	 */
	public int updateSync(SyncVO syncVO) {
		return syncMapper.updateSync(syncVO);
	}
	
	/**
	 * 동기화 서비스 글 삭제
	 * 
	 * @param syncVO
	 */
	public int deleteSync(SyncVO syncVO) {
		return syncMapper.deleteSync(syncVO);
	}

	/**
	 * 동기화 서비스 글 '동기화'를 실행
	 * 
	 * @param syncVO
	 */
	public int executeSync(SyncVO syncVO, String localData) {
		int rtn = 0;
		
		String[] localDataArr = localData.split("∀");
		for(int i=0; i<localDataArr.length; i++) {
			String[] syncDataArr = localDataArr[i].split("\\|");
			
			if("I".equals(syncDataArr[0].toString())) {
				syncVO.setSyncSj(syncDataArr[2]);
				syncVO.setSyncCn(syncDataArr[3]);
				rtn = insertSync(syncVO);
				
			} else if("U".equals(syncDataArr[0].toString())) {
				int updateSn = Integer.parseInt(syncDataArr[1]);

				syncVO.setSn(updateSn);
				syncVO.setSyncSj(syncDataArr[2]);
				syncVO.setSyncCn(syncDataArr[3]);
				rtn = updateSync(syncVO);
				
			} else if("D".equals(syncDataArr[0].toString())) {
				int deleteSn = Integer.parseInt(syncDataArr[1]);

				syncVO.setSn(deleteSn);
				rtn = deleteSync(syncVO);
			} 
		}
		
		return rtn;
	}
}
