package aramframework.mbl.com.mcg.service.impl;

import java.util.List;

import aramframework.mbl.com.mcg.domain.ChartGraphVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 차트/그래프 데이터에 대한 DB상의 접근, 변경을 처리한다.
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

@Mapper
public interface ChartGraphMapper {

    /**
     * 차트/그래프 데이터 목록 DB 조회 메서드
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
     * 차트/그래프 데이터 상세 DB 조회 메서드
     * 
     * @param chartGraphVO
     */
    public ChartGraphVO selectChartGraph(ChartGraphVO chartGraphVO);
 
    /**
     * 차트/그래프 데이터 DB 등록 메서드
     * 
     * @param chartGraphVO
     */
    public void insertChartGraph(ChartGraphVO chartGraphVO);

   /**
     * 차트/그래프 데이터 DB 수정 메서드
     * 
     * @param chartGraphVO
     */
    public void updateChartGraph(ChartGraphVO chartGraphVO);

    /**
     * 차트/그래프 데이터 DB 삭제 메서드
     * 
     * @param chartGraphVO
     */
    public void deleteChartGraph(ChartGraphVO chartGraphVO);
 
}
