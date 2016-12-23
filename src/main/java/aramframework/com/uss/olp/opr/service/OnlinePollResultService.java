package aramframework.com.uss.olp.opr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.uss.olp.opr.dao.OnlinePollResultMapper;
import aramframework.com.uss.olp.opr.domain.OnlinePollResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL결과를 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class OnlinePollResultService extends EgovAbstractServiceImpl {

	@Autowired
	private OnlinePollResultMapper onlinePollResultMapper;	

	/**
	 * 온라인POLL결과를(을) 목록을 한다.
	 * 
	 * @param onlinePollResultVO
	 */
	public List<EgovMap> selectOnlinePollResultList(OnlinePollResultVO onlinePollResultVO) {
		return onlinePollResultMapper.selectOnlinePollResultList(onlinePollResultVO);
	}

	/**
	 * 온라인POLL결과를(을) 삭제 한다.
	 * 
	 * @param onlinePollResultVO
	 */
	public void deleteOnlinePollResult(OnlinePollResultVO onlinePollResultVO) {
		onlinePollResultMapper.deleteOnlinePollResult(onlinePollResultVO);
	}
	
}
