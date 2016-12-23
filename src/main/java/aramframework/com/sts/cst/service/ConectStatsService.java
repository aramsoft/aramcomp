package aramframework.com.sts.cst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sts.com.StatsVO;
import aramframework.com.sts.cst.dao.ConectStatsMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 접속 통계 검색 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Service
public class ConectStatsService extends EgovAbstractServiceImpl {

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
