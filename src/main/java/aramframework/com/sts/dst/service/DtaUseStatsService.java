package aramframework.com.sts.dst.service;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

/**
 * - 자료이용현황 통계에 대한 Service Interface를 정의한다.
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

public interface DtaUseStatsService {

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsListCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsListBarListCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 *            - 자료이용현황 VO
	 * @return dtaUseStatsVO - 자료이용현황 VO
	 */
	public List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsCnt(DtaUseStatsVO dtaUseStatsVO);

	/**
	 * 자료이용현황 정보를 생성한다.
	 * 
	 * @param jp
	 *            - AOP의 pointcut을 위한 JoinPoint
	 * @param commandMap
	 *            - 자료이용현황 model
	 */
	public void insertDtaUseStats(JoinPoint jp, Map<String, Object> commandMap);

}
