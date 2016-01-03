package aramframework.com.uss.olp.opr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.uss.olp.opr.service.OnlinePollResultService;
import aramframework.com.uss.olp.opr.service.OnlinePollResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 온라인POLL결과를 처리하는 ServiceImpl Class 구현
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

@Service("onlinePollResultService")
public class OnlinePollResultServiceImpl extends EgovAbstractServiceImpl implements OnlinePollResultService {

	@Resource(name = "onlinePollResultMapper")
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
