package aramframework.com.sts.rst.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import aramframework.com.sts.rst.service.ReprtStatsVO;

/**
 * 개요
 * - 보고서통계에 대한 DAO 클래스를 정의한다.
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

@Mapper("reprtStatsMapper")
public interface ReprtStatsMapper {

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

	/**
	 * 보고서 통계정보를 수정한다.
	 * 
	 * @param reprtStatsVO
	 */
	public void updateReprtStats(ReprtStatsVO reprtStatsVO);

	/**
	 * 보고서 통계정보를 삭제한다.
	 * 
	 * @param reprtStatsVO
	 */
	public void deleteReprtStats(ReprtStatsVO reprtStatsVO);

}
