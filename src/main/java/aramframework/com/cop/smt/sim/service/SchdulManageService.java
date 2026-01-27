package aramframework.com.cop.smt.sim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.cmm.util.FileMngUtil;
import aramframework.com.cmm.com.domain.BaseVO;
import aramframework.com.cop.smt.sim.dao.SchdulManageMapper;
import aramframework.com.cop.smt.sim.domain.SchdulManageVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * 일정관리를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class SchdulManageService extends EgovAbstractServiceImpl {

	@Autowired 
	private SchdulManageMapper schdulManageMapper;	

	@Autowired 
	private EgovIdGnrService schdulManageIdGnrService; 

	@Autowired 
	private FileMngUtil fileUtil;

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param baseVO
	 * 
	 */
	public List<EgovMap> selectEmplyrList(BaseVO baseVO) {
		return schdulManageMapper.selectEmplyrList(baseVO);
	}

	/**
	 * 사용자 총횟수를 조회한다.
	 * 
	 * @param baseVO
	 * 
	 */
	public int selectEmplyrListCnt(BaseVO baseVO) {
		return schdulManageMapper.selectEmplyrListCnt(baseVO);
	}

	/**
	 * 메인페이지/일정관리조회
	 * 
	 * @param schdulManageVO
	 */
	public List<EgovMap> selectSchdulManageMainList(SchdulManageVO schdulManageVO) {
		return schdulManageMapper.selectSchdulManageMainList(schdulManageVO);
	}

	/**
	 * 일정 목록을 Map(map)형식으로 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	public List<EgovMap> selectSchdulManageRetrieve(SchdulManageVO schdulManageVO) {
		return schdulManageMapper.selectSchdulManageRetrieve(schdulManageVO);
	}

	/**
	 * 일정 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	public List<EgovMap> selectSchdulManageList(SchdulManageVO schdulManageVO) {
		return schdulManageMapper.selectSchdulManageList(schdulManageVO);
	}

	/**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	public int selectSchdulManageListCnt(SchdulManageVO schdulManageVO) {
		return (Integer) schdulManageMapper.selectSchdulManageListCnt(schdulManageVO);
	}

	/**
	 * 일정를(을) 상세조회 한다.
	 * 
	 * @param schdulManageVO
	 */
	public SchdulManageVO selectSchdulManageDetail(SchdulManageVO schdulManageVO) {
		return schdulManageMapper.selectSchdulManageDetail(schdulManageVO);
	}

	/**
	 * 일정를(을) 등록한다.
	 * 
	 * @param schdulManageVO
	 */
	public void insertSchdulManage(SchdulManageVO schdulManageVO) {
		try {
			schdulManageVO.setSchdulId(schdulManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		schdulManageMapper.insertSchdulManage(schdulManageVO);
	}

	/**
	 * 일정를(을) 수정한다.
	 * 
	 * @param schdulManageVO
	 */
	public void updateSchdulManage(SchdulManageVO schdulManageVO) {
		schdulManageMapper.updateSchdulManage(schdulManageVO);
	}

	/**
	 * 일정를(을) 삭제한다.
	 * 
	 * @param schdulManageVO
	 */
	public void deleteSchdulManage(SchdulManageVO schdulManageVO) {

		// 첨부파일 삭제 ....
		fileUtil.deleteMultiFile(schdulManageVO.getAtchFileId());

		schdulManageMapper.deleteDiaryManage(schdulManageVO);
		schdulManageMapper.deleteSchdulManage(schdulManageVO);
	}
}
