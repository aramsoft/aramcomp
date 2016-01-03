package aramframework.com.cop.smt.sim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cop.smt.sim.service.SchdulManageService;
import aramframework.com.cop.smt.sim.service.SchdulManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일정관리를 처리하는 ServiceImpl Class 구현
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

@Service("schdulManageService")
public class SchdulManageServiceImpl extends EgovAbstractServiceImpl implements SchdulManageService {

	@Resource(name = "schdulManageMapper")
	private SchdulManageMapper schdulManageMapper;	

	@Resource(name = "schdulManageIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name="fileMngUtil")
	private FileMngUtil fileUtil;

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param searchVO
	 * 
	 */
	public List<EgovMap> selectEmplyrList(SearchVO searchVO) {
		return schdulManageMapper.selectEmplyrList(searchVO);
	}

	/**
	 * 사용자 총횟수를 조회한다.
	 * 
	 * @param searchVO
	 * 
	 */
	public int selectEmplyrListCnt(SearchVO searchVO) {
		return schdulManageMapper.selectEmplyrListCnt(searchVO);
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
	 * @param searchVO
	 */
	public List<EgovMap> selectSchdulManageList(SearchVO searchVO) {
		return schdulManageMapper.selectSchdulManageList(searchVO);
	}

	/**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 */
	public int selectSchdulManageListCnt(SearchVO searchVO) {
		return (Integer) schdulManageMapper.selectSchdulManageListCnt(searchVO);
	}

	/**
	 * 일정를(을) 상세조회 한다.
	 * 
	 * @param schdulManageVO
	 */
	public SchdulManageVO selectSchdulManageDetail(SchdulManageVO schdulManageVO) {
		SchdulManageVO resultVo =schdulManageMapper.selectSchdulManageDetail(schdulManageVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, schdulManageVO); 
		return resultVo;
	}

	/**
	 * 일정를(을) 등록한다.
	 * 
	 * @param schdulManageVO
	 */
	public void insertSchdulManage(SchdulManageVO schdulManageVO) {
		try {
			schdulManageVO.setSchdulId(idgenService.getNextStringId());
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
