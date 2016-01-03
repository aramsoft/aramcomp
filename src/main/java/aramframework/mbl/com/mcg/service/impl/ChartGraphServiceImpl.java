package aramframework.mbl.com.mcg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import aramframework.com.cmm.util.BeanUtil;
import aramframework.mbl.com.mcg.service.ChartGraphVO;
import aramframework.mbl.com.mcg.service.ChartGraphService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 모바일 차트/그래프에 대한 Service Interface를 구현한다.
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

@Service("chartGraphService")
public class ChartGraphServiceImpl extends EgovAbstractServiceImpl implements ChartGraphService {

    /**
     * ChartGraphDAO
     */
    @Resource(name = "chartGraphMapper")
    private ChartGraphMapper chartGraphMapper;
    
    /** ID Generation */
    @Resource(name = "chartGraphIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 차트/그래프 데이터 목록 조회 관련 비즈니스 구현 메서드
     * 
     * @param chartGraphVO
     */
    public List<EgovMap> selectChartGraphList(ChartGraphVO chartGraphVO) {
        return chartGraphMapper.selectChartGraphList(chartGraphVO);
    }

    /**
     * 차트/그래프 데이터의 총 갯수를 조회한다.
     * 
     * @param chartGraphVO
     */
    public int selectChartGraphListCnt(ChartGraphVO chartGraphVO) {
        return chartGraphMapper.selectChartGraphListCnt(chartGraphVO);
    }
    
    /**
     * 차트/그래프 데이터 상세 조회 관련 비즈니스 구현 메서드
     * 
     * @param chartGraphVO
     */
    public ChartGraphVO selectChartGraph(ChartGraphVO chartGraphVO) {
    	ChartGraphVO resultVo = chartGraphMapper.selectChartGraph(chartGraphVO);
		// deep copy
		BeanUtil.copyPropertiesCore(resultVo, chartGraphVO); 
		return resultVo;
    }

   /**
     * 차트/그래프 데이터 등록 관련 비즈니스 구현 메서드
     * 
     * @param chartGraphVO
     */
    public void insertChartGraph(ChartGraphVO chartGraphVO) {
        try {
			chartGraphVO.setSn(idgenService.getNextIntegerId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
        chartGraphMapper.insertChartGraph(chartGraphVO);
    }

    /**
     * 차트/그래프 데이터 수정 관련 비즈니스 구현 메서드
     * 
     * @param chartGraphVO
     */
    public void updateChartGraph(ChartGraphVO chartGraphVO) {
        chartGraphMapper.updateChartGraph(chartGraphVO);
    }

    /**
     * 차트/그래프 데이터 삭제 관련 비즈니스 구현 메서드
     * 
     * @param chartGraphVO
     */
    public void deleteChartGraph(ChartGraphVO chartGraphVO) {
        chartGraphMapper.deleteChartGraph(chartGraphVO);
    }

}
