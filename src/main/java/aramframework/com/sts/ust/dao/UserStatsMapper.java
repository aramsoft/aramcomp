package aramframework.com.sts.ust.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sts.com.StatsVO;

/**
 * 사용자 통계 검색 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface UserStatsMapper {

	/**
	 * 사용자 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectUserStats(StatsVO statsVO);

	/**
	 * 사용자 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 */
	public void summaryUserStats();
	
}
