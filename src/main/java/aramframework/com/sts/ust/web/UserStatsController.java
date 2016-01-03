package aramframework.com.sts.ust.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.sts.com.StatsVO;
import aramframework.com.sts.ust.service.UserStatsService;

/**
 * 사용자 통계 검색 컨트롤러 클래스
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
public class UserStatsController {

	@Resource(name = "userStatsService")
	private UserStatsService userStatsService;

	@Resource(name = "cmmUseService")
	private CmmUseService cmmUseService;

	/**
	 * 사용자 통계를 조회한다
	 * 
	 * @param statsVO
	 */
	@IncludedInfo(name = "사용자통계", order = 3010, gid = 30)
	@RequestMapping(value = "/sts/ust/selectUserStats.do")
	@Secured("ROLE_ADMIN")
	public String selectUserStats(
			@ModelAttribute StatsVO statsVO, 
			ModelMap model) {

		// 세부통계구분 공통코드 목록 조회(회원유형,상태,성별에 대한 세부통계구분코드)
		cmmUseService.populateCmmCodeList("COM012", "COM012_mberType");
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");

		if (statsVO.getFromDate() == null || "".equals(statsVO.getFromDate())) 
			return "aramframework/com/sts/ust/UserStats";

		List<StatsVO> userStats = userStatsService.selectUserStats(statsVO);
		// 그래프에 표시될 이미지 길이를 결정한다.
		float iMaxUnit = 50.0f;
		for (int i = 0; i < userStats.size(); i++) {
			StatsVO sVo = (StatsVO) userStats.get(i);
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

		model.addAttribute("userStats", userStats);

		return "aramframework/com/sts/ust/UserStats";
	}
	
}