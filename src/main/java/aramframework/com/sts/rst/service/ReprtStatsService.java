package aramframework.com.sts.rst.service;

import java.util.List;

/**
 * 개요
 * - 보고서통계에 대한 Service Interface를 정의한다.
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

public interface ReprtStatsService {

	/**
	 * 보고서 통계정보의 대상목록을 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsList(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서통계목록 페이징 총 갯수를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public int selectReprtStatsListCnt(ReprtStatsVO reprtStatsVO);

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsBarList(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서통계목록 총 갯수를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public int selectReprtStatsListBarListCnt(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서유형별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsByReprtTyList(ReprtStatsVO reprtStatsVO);

	/**
	 * 진행상태별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsByReprtSttusList(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서 통계정보의 상세정보를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStats(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서 통계정보를 생성한 뒤 저장한다.
	 * 
	 * @param reprtStatsVO
	 */
	public void insertReprtStats(ReprtStatsVO reprtStatsVO);

}
