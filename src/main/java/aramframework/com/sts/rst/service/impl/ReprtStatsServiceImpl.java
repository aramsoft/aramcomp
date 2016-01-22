package aramframework.com.sts.rst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.sts.rst.service.ReprtStatsService;
import aramframework.com.sts.rst.service.ReprtStatsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 보고서통계에 대한 ServiceImpl 클래스를 정의한다.
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

@Service
public class ReprtStatsServiceImpl extends EgovAbstractServiceImpl implements ReprtStatsService {

	@Autowired
	private ReprtStatsMapper reprtStatsMapper;	

	/** Message ID Generation */
	@Autowired
	private EgovIdGnrService reprtStatsIdGnrService; 

	/**
	 * 보고서 통계정보의 대상목록을 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsList(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsList(reprtStatsVO);
	}

	/**
	 * 보고서통계목록 페이징 총 갯수를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public int selectReprtStatsListCnt(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsListCnt(reprtStatsVO);
	}

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsBarList(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsBarList(reprtStatsVO);
	}

	/**
	 * 보고서통계목록 총 갯수를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public int selectReprtStatsListBarListCnt(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsListBarListCnt(reprtStatsVO);
	}

	/**
	 * 보고서유형별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsByReprtTyList(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsByReprtTyList(reprtStatsVO);
	}

	/**
	 * 진행상태별 통계정보를 그래프로 표현한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStatsByReprtSttusList(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStatsByReprtSttusList(reprtStatsVO);
	}
	
	/**
	 * 보고서 통계정보의 상세정보를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	public List<ReprtStatsVO> selectReprtStats(ReprtStatsVO reprtStatsVO) {
		return reprtStatsMapper.selectReprtStats(reprtStatsVO);
	}

	/**
	 * 보고서 통계정보를 생성한 뒤 저장한다.
	 * 
	 * @param reprtStatsVO
	 */
	public void insertReprtStats(ReprtStatsVO reprtStatsVO) {
		try {
			reprtStatsVO.setReprtId(reprtStatsIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new RuntimeException(e);
		}
		reprtStatsMapper.insertReprtStats(reprtStatsVO);
	}

}
