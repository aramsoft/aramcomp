package aramframework.com.sts.cst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sts.com.StatsVO;
import aramframework.com.sts.cst.service.ConectStatsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 접속 통계 검색 비즈니스 구현 클래스
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

@Service("conectStatsService")
public class ConectStatsServiceImpl extends EgovAbstractServiceImpl implements ConectStatsService {

	@Autowired
	private ConectStatsMapper conectStatsMapper;
	
	/**
	 * 접속 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectConectStats(StatsVO statsVO) {
		return conectStatsMapper.selectConectStats(statsVO);
	}
}
