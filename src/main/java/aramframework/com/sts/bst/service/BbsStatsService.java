package aramframework.com.sts.bst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sts.bst.dao.BbsStatsMapper;
import aramframework.com.sts.com.StatsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시물 통계 검색 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Service
public class BbsStatsService extends EgovAbstractServiceImpl {

	@Autowired
	private BbsStatsMapper bbsStatsMapper;
	
	/**
	 * 게시물 생성글수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsCretCntStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsCretCntStats(statsVO);
	}

	/**
	 * 게시물 총조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsTotCntStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsTotCntStats(statsVO);
	}

	/**
	 * 게시물 평균조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsAvgCntStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsAvgCntStats(statsVO);
	}

	/**
	 * 최고조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMaxCntStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsMaxCntStats(statsVO);
	}

	/**
	 * 최소조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMinCntStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsMinCntStats(statsVO);
	}

	/**
	 * 게시물 최고게시자 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMaxUserStats(StatsVO statsVO) {
		return bbsStatsMapper.selectBbsMaxUserStats(statsVO);
	}

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 */
	public void summaryBbsStats() {
		bbsStatsMapper.summaryBbsStats();
	}
	
}
