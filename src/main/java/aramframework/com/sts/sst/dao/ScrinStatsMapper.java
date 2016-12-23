package aramframework.com.sts.sst.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sts.com.StatsVO;

/**
 * 화면 통계 검색 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface ScrinStatsMapper {

	/**
	 * 화면 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectScrinStats(StatsVO statsVO);

}
