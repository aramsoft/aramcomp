package aramframework.com.sts.bst.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.sts.bst.service.BbsStatsService;
import aramframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 검색 컨트롤러 클래스
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

@Controller
public class BbsStatsController {

	@Autowired
	private BbsStatsService bbsStatsService;

	@Autowired
	private CmmUseService cmmUseService;

	/**
	 * 게시물 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	@IncludedInfo(name = "게시물통계", order = 3000, gid = 30)
	@RequestMapping(value = "/sts/bst/selectBbsStats.do")
	@Secured("ROLE_ADMIN")
	public String selectBbsStats(
			@ModelAttribute StatsVO statsVO, 
			ModelMap model) {

		// 세부통계구분 공통코드 목록 조회(게시판유형,속성에 대한 세부통계구분코드)
		cmmUseService.populateCmmCodeList("COM004", "COM004_bbsType");
		cmmUseService.populateCmmCodeList("COM005", "COM005_tmplatSe");
		cmmUseService.populateCmmCodeList("COM009", "COM009_bbsAttrb");

		if (statsVO.getFromDate() == null || "".equals(statsVO.getFromDate())) {
			statsVO.setTabKind("tab1");
			return "aramframework/com/sts/bst/BbsStats";
		}
		
		// 탭구분 : 생성글수(tab1), 총조회수(tab2), 평균조회수(tab3), 최고/최소조회수(tab4),
		// 최고게시자(tab5)
		List<StatsVO> bbsStatsList = null;
		List<StatsVO> bbsMaxStatsList = null;
		List<StatsVO> bbsMinStatsList = null;
		List<StatsVO> bbsMaxNtcrList = null;

		// 1. 생성글수(tab1)
		if ("tab1".equals(statsVO.getTabKind())) {
			// 생성글수 조회
			bbsStatsList = bbsStatsService.selectBbsCretCntStats(statsVO);
			// 그래프 길이 설정
			float iMaxUnit = 50.0f;
			for (int i = 0; i < bbsStatsList.size(); i++) {
				StatsVO sVO = (StatsVO) bbsStatsList.get(i);
				int iCnt = sVO.getStatsCo();
				if (iCnt > 10 && iCnt <= 100) {
					if (iMaxUnit > 5.0f) {
						iMaxUnit = 5.0f;
					}
				} else if (iCnt > 100 && iCnt <= 1000) {
					if (iMaxUnit > 0.5f) {
						iMaxUnit = 0.5f;
					}
				} else if (iCnt > 1000) {
					if (iMaxUnit > 0.05f) {
						iMaxUnit = 0.05f;
					}
				}
			}
			statsVO.setMaxUnit(iMaxUnit);
			// 결과 리턴
			model.addAttribute("bbsStatsList", bbsStatsList);

			// 2. 총조회수(tab2)
		} else if ("tab2".equals(statsVO.getTabKind())) {
			// 총조회수 조회
			bbsStatsList = bbsStatsService.selectBbsTotCntStats(statsVO);
			// 그래프 길이 설정
			float iMaxUnit = 50.0f;
			for (int i = 0; i < bbsStatsList.size(); i++) {
				StatsVO sVO = (StatsVO) bbsStatsList.get(i);
				int iCnt = sVO.getStatsCo();
				if (iCnt > 10 && iCnt <= 100) {
					if (iMaxUnit > 5.0f) {
						iMaxUnit = 5.0f;
					}
				} else if (iCnt > 100 && iCnt <= 1000) {
					if (iMaxUnit > 0.5f) {
						iMaxUnit = 0.5f;
					}
				} else if (iCnt > 1000) {
					if (iMaxUnit > 0.05f) {
						iMaxUnit = 0.05f;
					}
				}
			}
			statsVO.setMaxUnit(iMaxUnit);
			// 결과 리턴
			model.addAttribute("bbsStatsList", bbsStatsList);

			// 3. 평균조회수(tab3)
		} else if ("tab3".equals(statsVO.getTabKind())) {
			// 평균조회수 조회
			bbsStatsList = bbsStatsService.selectBbsAvgCntStats(statsVO);
			// 그래프 길이 설정
			float iMaxUnit = 50.0f;
			for (int i = 0; i < bbsStatsList.size(); i++) {
				StatsVO sVO = (StatsVO) bbsStatsList.get(i);
				int iCnt = (int) sVO.getAvrgInqireCo();
				sVO.setStatsCo(iCnt);
				if (iCnt > 10 && iCnt <= 100) {
					if (iMaxUnit > 5.0f) {
						iMaxUnit = 5.0f;
					}
				} else if (iCnt > 100 && iCnt <= 1000) {
					if (iMaxUnit > 0.5f) {
						iMaxUnit = 0.5f;
					}
				} else if (iCnt > 1000) {
					if (iMaxUnit > 0.05f) {
						iMaxUnit = 0.05f;
					}
				}
			}
			statsVO.setMaxUnit(iMaxUnit);
			// 결과 리턴
			model.addAttribute("bbsStatsList", bbsStatsList);

			// 4. 최고/최소조회수(tab4)
		} else if ("tab4".equals(statsVO.getTabKind())) {
			// 최고게시글 정보 조회
			bbsMaxStatsList = bbsStatsService.selectBbsMaxCntStats(statsVO);
			// 최소게시글 정보 조회
			bbsMinStatsList = bbsStatsService.selectBbsMinCntStats(statsVO);
			// 결과 리턴
			model.addAttribute("bbsMaxStatsList", bbsMaxStatsList);
			model.addAttribute("bbsMinStatsList", bbsMinStatsList);

			// 5. 최고게시자(tab5)
		} else if ("tab5".equals(statsVO.getTabKind())) {

			bbsMaxNtcrList = bbsStatsService.selectBbsMaxUserStats(statsVO);
			// 결과 리턴
			model.addAttribute("bbsMaxNtcrList", bbsMaxNtcrList);
		}

		return "aramframework/com/sts/bst/BbsStats";
	}
	
}