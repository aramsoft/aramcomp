package aramframework.com.sts.dst.service.impl;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.sts.dst.domain.DtaUseStatsVO;
import aramframework.com.sts.dst.service.DtaUseStatsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 자료이용현황 통계에 대한 ServiceImpl를 정의한다.
 *
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * - 게시판에서 다운로드한 통계만 적용된다.(게시판이 아닌경우는 통계에서 제외함)
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

@Service("dtaUseStatsService")
public class DtaUseStatsServiceImpl extends EgovAbstractServiceImpl implements DtaUseStatsService {

	@Autowired
	private DtaUseStatsMapper dtaUseStatsMapper;	

	@Autowired
	private EgovIdGnrService dtaUseStatsIdGnrService; 

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStatsList(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsListCnt(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStatsListCnt(dtaUseStatsVO);
	}

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStatsBarList(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsListBarListCnt(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStatsListBarListCnt(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStats(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	public int selectDtaUseStatsCnt(DtaUseStatsVO dtaUseStatsVO) {
		return dtaUseStatsMapper.selectDtaUseStatsCnt(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 정보를 생성한다.
	 * 
	 * @param jp
	 *            - AOP의 pointcut을 위한 JoinPoint
	 * @param commandMap
	 *            - 자료이용현황 model
	 */
	public void insertDtaUseStats(JoinPoint jp, Map<String, Object> commandMap) {

		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String uniqId = "unknown";
		if( loginVO != null ) {
			uniqId = loginVO.getUniqId();
		}

		DtaUseStatsVO dtaUseStats = new DtaUseStatsVO(); // 2011.08.23 수정 부분
		dtaUseStats.setAtchFileId(atchFileId);
		dtaUseStats.setFileSn(fileSn);

		dtaUseStats = dtaUseStatsMapper.selectInsertDtaUseStats(dtaUseStats);

		// 2011.09.29 게시판외 다운로드시 에러발생(dtaUseStats 값이 null)을 방지
		if (dtaUseStats != null) {
			DtaUseStatsVO vo = new DtaUseStatsVO(); // 2011.08.23 수정 부분
			try {
				vo.setDtaUseStatsId(dtaUseStatsIdGnrService.getNextStringId());
			} catch (FdlException e) {
				throw new RuntimeException(e);
			}
			vo.setBbsId(dtaUseStats.getBbsId());
			vo.setNttId(dtaUseStats.getNttId());
			vo.setAtchFileId(atchFileId);
			vo.setFileSn(fileSn);
			vo.setUserId(uniqId);

			dtaUseStatsMapper.insertDtaUseStats(vo);
		}
	}

}
