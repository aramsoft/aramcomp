package aramframework.com.sts.bst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 검색 DAO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Repository
public class BbsStatsMapper extends EgovAbstractMapper {

	final static String NAMESPACE = BbsStatsMapper.class.getName();

	/**
	 * 게시물 생성글수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsCretCntStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsCretCntStats", statsVO);
	}

	/**
	 * 게시물 총조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsTotCntStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsTotCntStats", statsVO);
	}

	/**
	 * 게시물 평균조회수 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsAvgCntStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsAvgCntStats", statsVO);
	}

	/**
	 * 최고조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMaxCntStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsMaxCntStats", statsVO);
	}

	/**
	 * 최소조회 게시물 통계정보를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMinCntStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsMinCntStats", statsVO);
	}

	/**
	 * 게시물 최고게시자 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	public List<StatsVO> selectBbsMaxUserStats(StatsVO statsVO) {
		return selectList(NAMESPACE+".selectBbsMaxUserStats", statsVO);
	}

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * 
	 */
	public void summaryBbsStats() {

		StatsVO parVO = new StatsVO();

		StatsVO sumVO = null;
		StatsVO resultVO = null;

		// 게시판 유형별
		// 1. 일반게시판
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM004");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_BOARD);
		parVO.setStatsKind("COM004");
		parVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_BOARD);
		// 1-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 1-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 1-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 1-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 1-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 1-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 1-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 1-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 2. 익명게시판
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM004");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_ANONYMOUS);
		parVO.setStatsKind("COM004");
		parVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_ANONYMOUS);
		// 2-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 2-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 2-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 2-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 2-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 2-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 2-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 2-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 3. 공지사항
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM004");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_NOTICE);
		parVO.setStatsKind("COM004");
		parVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_NOTICE);
		// 3-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 3-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 3-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 3-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 3-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 3-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 3-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 3-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 4. 방명록
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM004");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_VISIT);
		parVO.setStatsKind("COM004");
		parVO.setDetailStatsKind(BBSBoardService.BBS_TYPE_VISIT);
		// 4-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 4-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 4-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 4-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 4-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 4-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 4-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 4-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 게시판 속성별
		// 1. 유효게시판
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM009");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_LIMIT);
		parVO.setStatsKind("COM009");
		parVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_LIMIT);
		// 1-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 1-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 1-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 1-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 1-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 1-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 1-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 1-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 2. 갤러리
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM009");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_GALARY);
		parVO.setStatsKind("COM009");
		parVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_GALARY);
		// 2-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 2-1. 생성글수
			resultVO = (StatsVO) selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 2-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 2-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 2-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 2-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 2-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 2-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 3. 일반
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM009");
		sumVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_GENERAL);
		parVO.setStatsKind("COM009");
		parVO.setDetailStatsKind(BBSBoardService.BBS_ATTRB_GENERAL);
		// 3-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 3-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 3-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 3-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 3-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 3-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 3-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 3-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 게시판 템플릿별
		// 1. 게시판
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM005");
		sumVO.setDetailStatsKind("TMPT01");
		parVO.setStatsKind("COM005");
		parVO.setDetailStatsKind("TMPT01");
		// 1-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 1-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 1-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 1-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 1-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 1-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 1-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 1-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 2. 커뮤니티
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM005");
		sumVO.setDetailStatsKind("TMPT02");
		parVO.setStatsKind("COM005");
		parVO.setDetailStatsKind("TMPT02");
		// 2-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 2-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 2-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 2-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 2-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 2-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 2-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 2-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}

		// 3. 동호회
		sumVO = new StatsVO();
		sumVO.setStatsKind("COM005");
		sumVO.setDetailStatsKind("TMPT03");
		parVO.setStatsKind("COM005");
		parVO.setDetailStatsKind("TMPT03");
		// 3-0. 집계 여부 조회
		resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsSummary", parVO);
		if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
			// 3-1. 생성글수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsCreatCo", parVO);
			if (resultVO != null)
				sumVO.setCreatCo(resultVO.getCreatCo());
			else
				sumVO.setCreatCo(0);
			// 3-2. 총조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTotInqireCo", parVO);
			if (resultVO != null)
				sumVO.setTotInqireCo(resultVO.getTotInqireCo());
			else
				sumVO.setTotInqireCo(0);
			// 3-3. 평균조회수
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsAvrgInqireCo", parVO);
			if (resultVO != null)
				sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
			else
				sumVO.setAvrgInqireCo(0);
			// 3-4. 최고조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMxmmInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
				sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
			else
				sumVO.setMxmmInqireBbsId("");
			// 3-5. 최소조회게시물ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsMummInqireBbsId", parVO);
			if (resultVO != null && resultVO.getMummInqireBbsId() != null)
				sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
			else
				sumVO.setMummInqireBbsId("");
			// 3-6. 최고게시자ID
			resultVO = (StatsVO) selectOne(NAMESPACE+".selectBbsTopNtcepersonId", parVO);
			if (resultVO != null && resultVO.getTopNtcepersonId() != null)
				sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
			else
				sumVO.setTopNtcepersonId("");

			// 3-7. 집계 등록
			insert(NAMESPACE+".summaryBbsStats", sumVO);
		}
	}
}
