package aramframework.mbl.com.mcg.service;

import java.util.List;

import aramframework.mbl.com.mcg.domain.ChartGraphVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 모바일 차트/그래프에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 차트/그래프 데이터에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 차트/그래프 데이터에 대한 조회기능은 목록, 상세조회로 구분된다.
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

public interface ChartGraphService {

    /**
     * 차트/그래프 데이터 목록을 조회하는 Service interface 메서드
     * 
     * @param chartGraphVO
     */
    public List<EgovMap> selectChartGraphList(ChartGraphVO chartGraphVO);

    /**
     * 차트/그래프 데이터의 총 갯수를 조회한다.
     * 
     * @param chartGraphVO
     */
    public int selectChartGraphListCnt(ChartGraphVO chartGraphVO);
    
    /**
     * 차트/그래프 데이터를 상세 조회하는 Service interface 메서드
     * 
     * @param chartGraphVO
      */
    public ChartGraphVO selectChartGraph(ChartGraphVO chartGraphVO);

    /**
     * 차트/그래프 데이터를 등록하는 Service interface 메서드
     * 
     * @param chartGraphVO
     */
    public void insertChartGraph(ChartGraphVO chartGraphVO);

    /**
     * 차트/그래프 데이터를 수정하는 Service interface 메서드
     * 
     * @param chartGraphVO
     */
    public void updateChartGraph(ChartGraphVO chartGraphVO);

    /**
     * 차트/그래프 데이터를 삭제하는 Service interface 메서드
     * 
     * @param chartGraphVO
      */
    public void deleteChartGraph(ChartGraphVO chartGraphVO);

}
