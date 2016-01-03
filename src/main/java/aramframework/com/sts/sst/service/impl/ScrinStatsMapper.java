package aramframework.com.sts.sst.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sts.com.StatsVO;

/**
 * 화면 통계 검색 DAO 클래스
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

@Mapper("scrinStatsMapper")
public interface ScrinStatsMapper {

	/**
	 * 화면 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectScrinStats(StatsVO statsVO);

}
