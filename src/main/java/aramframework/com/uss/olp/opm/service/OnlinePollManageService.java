package aramframework.com.uss.olp.opm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.olp.opm.dao.OnlinePollManageMapper;
import aramframework.com.uss.olp.opm.domain.OnlinePollItemVO;
import aramframework.com.uss.olp.opm.domain.OnlinePollManageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL관리를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class OnlinePollManageService extends EgovAbstractServiceImpl {

	@Autowired
	private OnlinePollManageMapper onlinePollManageMapper;	

	@Autowired
	private EgovIdGnrService onlinePollManageIdGnrService; 

	@Autowired
	private EgovIdGnrService onlinePollItemIdGnrService;
 
	/**
	 * 온라인POLL관리를(을) 목록을 조회 한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public List<EgovMap> selectOnlinePollManageList(OnlinePollManageVO onlinePollManageVO) {
		return onlinePollManageMapper.selectOnlinePollManageList(onlinePollManageVO);
	}

	/**
	 * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public int selectOnlinePollManageListCnt(OnlinePollManageVO onlinePollManageVO) {
		return (Integer) onlinePollManageMapper.selectOnlinePollManageListCnt(onlinePollManageVO);
	}

	/**
	 * 온라인POLL관리를(을) 상세조회 한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public OnlinePollManageVO selectOnlinePollManageDetail(OnlinePollManageVO onlinePollManageVO) {
		OnlinePollManageVO resultVo = onlinePollManageMapper.selectOnlinePollManageDetail(onlinePollManageVO);
		// searchVO 이전 
		resultVo.setSearchVO(onlinePollManageVO.getSearchVO()); 
		return resultVo;
	}

	/**
	 * 온라인POLL관리를(을) 등록한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void insertOnlinePollManage(OnlinePollManageVO onlinePollManageVO) {
		try {
			onlinePollManageVO.setPollId(onlinePollManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		onlinePollManageMapper.insertOnlinePollManage(onlinePollManageVO);
	}

	/**
	 * 온라인POLL관리를(을) 수정한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void updateOnlinePollManage(OnlinePollManageVO onlinePollManageVO) {
		onlinePollManageMapper.updateOnlinePollManage(onlinePollManageVO);
	}

	/**
	 * 온라인POLL관리를(을) 삭제한다.
	 * 
	 * @param onlinePollManageVO
	 */
	public void deleteOnlinePollManage(OnlinePollManageVO onlinePollManageVO) {
		onlinePollManageMapper.deleteOnlinePollResultAll(onlinePollManageVO);
		onlinePollManageMapper.deleteOnlinePollItemAll(onlinePollManageVO);
		onlinePollManageMapper.deleteOnlinePollManage(onlinePollManageVO);
	}

	/**
	 * 온라인POLL항목를(을) 조회한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public List<EgovMap> selectOnlinePollItemList(OnlinePollItemVO onlinePollItemVO) {
		return onlinePollManageMapper.selectOnlinePollItemList(onlinePollItemVO);
	}

	/**
	 * 온라인POLL항목를(을) 등록한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void insertOnlinePollItem(OnlinePollItemVO onlinePollItemVO) {
		try {
			onlinePollItemVO.setPollIemId(onlinePollItemIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		onlinePollManageMapper.insertOnlinePollItem(onlinePollItemVO);
	}

	/**
	 * 온라인POLL항목를(을) 수정한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void updateOnlinePollItem(OnlinePollItemVO onlinePollItemVO) {
		onlinePollManageMapper.updateOnlinePollItem(onlinePollItemVO);
	}

	/**
	 * 온라인POLL항목를(을) 삭제한다.
	 * 
	 * @param onlinePollItemVO
	 */
	public void deleteOnlinePollItem(OnlinePollItemVO onlinePollItemVO) {
		onlinePollManageMapper.deleteOnlinePollResultIemid(onlinePollItemVO);
		onlinePollManageMapper.deleteOnlinePollItem(onlinePollItemVO);
	}
	
}
