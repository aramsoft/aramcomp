package aramframework.com.sts.bst.service;

import java.util.List;

import aramframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 검색 비즈니스 인터페이스 클래스
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

public interface BbsStatsService {

	/**
	 * 게시물 생성글수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsCretCntStats(StatsVO statsVO);

	/**
	 * 게시물 총조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsTotCntStats(StatsVO statsVO);

	/**
	 * 게시물 평균조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsAvgCntStats(StatsVO statsVO);

	/**
	 * 최고조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsMaxCntStats(StatsVO statsVO);

	/**
	 * 최소조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsMinCntStats(StatsVO statsVO);

	/**
	 * 게시물 최고게시자 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	List<StatsVO> selectBbsMaxUserStats(StatsVO statsVO);

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 */
	public void summaryBbsStats() ;
	
}
