package aramframework.com.sts.cst.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.sts.com.StatsVO;
import aramframework.com.sts.cst.service.ConectStatsService;

/**
 * 접속 통계 검색 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class ConectStatsController {

	@Autowired
	private ConectStatsService conectStatsService;

	@Autowired
	private CmmUseService cmmUseService;

	/**
	 * 접속 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	@IncludedInfo(name = "접속통계", order = 3020, gid = 30)
	@RequestMapping(value = "/sts/cst/selectConectStats.do")
	@Secured("ROLE_ADMIN")
	public String selectUserStats(
			@ModelAttribute StatsVO statsVO, 
			ModelMap model) {

		if (statsVO.getFromDate() == null || "".equals(statsVO.getFromDate())) 
			return "aramframework/com/sts/cst/ConectStats";
		
		List<StatsVO> conectStats = conectStatsService.selectConectStats(statsVO);

		// 1. 서비스별
		if ("SERVICE".equals(statsVO.getStatsKind())) {
			model.addAttribute("conectStats", conectStats);
			// 2. 개인별
		} else {
			// 그래프에 표시될 이미지 길이를 결정한다.
			float iMaxUnit = 50.0f;
			for (int i = 0; i < conectStats.size(); i++) {
				StatsVO vo = (StatsVO) conectStats.get(i);
				int iCnt = vo.getStatsCo();
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
			model.addAttribute("conectStats", conectStats);
		}
		
		return "aramframework/com/sts/cst/ConectStats";
	}
	
}