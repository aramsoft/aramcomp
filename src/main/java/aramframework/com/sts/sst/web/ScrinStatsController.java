package aramframework.com.sts.sst.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.sts.com.StatsVO;
import aramframework.com.sts.sst.service.ScrinStatsService;
import aramframework.com.sym.mnu.mpm.service.MenuManageService;

/**
 * 화면 통계 검색 컨트롤러 클래스
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
public class ScrinStatsController {

	@Autowired
	private ScrinStatsService scrinStatsService;

	@Autowired
	private MenuManageService menuManageService;

	/**
	 * 화면 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	@IncludedInfo(name = "화면통계", order = 3030, gid = 30)
	@RequestMapping(value = "/sts/sst/selectScrinStats.do")
	@Secured("ROLE_ADMIN")
	public String selectUserStats(
			@ModelAttribute StatsVO statsVO, 
			ModelMap model) {

		// 트리메뉴 조회
		model.addAttribute("list_menulist", menuManageService.selectMenuList());

		if (statsVO.getFromDate() == null || "".equals(statsVO.getFromDate())) 
			return "aramframework/com/sts/sst/ScrinStats";

		List<StatsVO> scrinStats = scrinStatsService.selectScrinStats(statsVO);
		// 그래프에 표시될 이미지 길이를 결정한다.
		float iMaxUnit = 50.0f;
		for (int i = 0; i < scrinStats.size(); i++) {
			StatsVO sVo = (StatsVO) scrinStats.get(i);
			int iCnt = sVo.getStatsCo();
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

		model.addAttribute("scrinStats", scrinStats);

		return "aramframework/com/sts/sst/ScrinStats";
	}
	
}