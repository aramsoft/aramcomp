package aramframework.com.uss.olp.opp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.com.uss.olp.opp.domain.OnlinePollPartcptnVO;
import aramframework.com.uss.olp.opp.service.OnlinePollPartcptnService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL참여를 처리하는 ServiceImpl Class 구현
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

@Service("onlinePollPartcptnService")
public class OnlinePollPartcptnServiceImpl extends EgovAbstractServiceImpl implements OnlinePollPartcptnService {

	@Autowired
	private OnlinePollPartcptnMapper onlinePollPartcptnMapper;	

	@Autowired
	private EgovIdGnrService onlinePollResultIdGnrService; 

	/**
	 * 온라인POLL관리를(을) 목록을 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollManageList(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		return onlinePollPartcptnMapper.selectOnlinePollManageList(onlinePollPartcptnVO);
	}

	/**
	 * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public int selectOnlinePollManageListCnt(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		return (Integer) onlinePollPartcptnMapper.selectOnlinePollManageListCnt(onlinePollPartcptnVO);
	}

	/**
	 * 온라인POLL관리를(을) 상세조회 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public OnlinePollPartcptnVO selectOnlinePollManageDetail(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		OnlinePollPartcptnVO resultVo = onlinePollPartcptnMapper.selectOnlinePollManageDetail(onlinePollPartcptnVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, onlinePollPartcptnVO); 
		return resultVo;
	}

	/**
	 * 온라인POLL항목를(을) 상세조회 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollItemList(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		return onlinePollPartcptnMapper.selectOnlinePollItemList(onlinePollPartcptnVO);
	}

	/**
	 * 온라인POLL참여 여부를 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public int selectOnlinePollResult(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		return onlinePollPartcptnMapper.selectOnlinePollResult(onlinePollPartcptnVO);
	}

	/**
	 * 온라인POLL참여를(을) 등록한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public void insertOnlinePollResult(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		try {
			onlinePollPartcptnVO.setPollResultId(onlinePollResultIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		onlinePollPartcptnMapper.insertOnlinePollResult(onlinePollPartcptnVO);
	}

	/**
	 * 온라인POLL통계를(을) 목록을 한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	public List<EgovMap> selectOnlinePollManageStatistics(OnlinePollPartcptnVO onlinePollPartcptnVO) {
		return onlinePollPartcptnMapper.selectOnlinePollManageStatistics(onlinePollPartcptnVO);
	}

}
