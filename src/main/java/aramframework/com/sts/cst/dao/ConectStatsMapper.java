package aramframework.com.sts.cst.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sts.com.StatsVO;

/**
 * 접속 통계 검색 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Mapper
public interface ConectStatsMapper {

	/**
	 * 접속 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectConectStats(StatsVO statsVO);

}	
	

