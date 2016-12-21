package aramframework.com.sts.rst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sts.rst.domain.ReprtStatsVO;
import aramframework.com.sts.rst.service.ReprtStatsService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 보고서통계에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 보고서통계에 대한 등록, 조회 기능을 제공한다.
 * - 보고서통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class ReprtStatsController {

	@Autowired
	private ReprtStatsService reprtStatsService;
	
	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 보고서 통계정보의 대상목록을 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	@IncludedInfo(name = "보고서통계", order = 3040, gid = 30)
	@RequestMapping("/sts/rst/listReprtStats.do")
	@Secured("ROLE_ADMIN")
	public String listReprtStats(
			@ModelAttribute ReprtStatsVO reprtStatsVO, 
			ModelMap model) {

		reprtStatsVO.getSearchVO().setRecordPerPage(5);

		PaginationInfo paginationInfo = new PaginationInfo();
		reprtStatsVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", reprtStatsService.selectReprtStatsList(reprtStatsVO));
		int totPageCnt = reprtStatsService.selectReprtStatsListCnt(reprtStatsVO);

		reprtStatsVO.getSearchVO().setTotalRecordCount(totPageCnt);
		paginationInfo.setTotalRecordCount(totPageCnt);
		model.addAttribute(paginationInfo);

		int totCnt = reprtStatsService.selectReprtStatsListBarListCnt(reprtStatsVO);
		if (totCnt > 10 && totCnt <= 100) {
			if (reprtStatsVO.getMaxUnit() > 5.0f) {
				reprtStatsVO.setMaxUnit(5.0f);
			}
		} else if (totCnt > 100 && totCnt <= 1000) {
			if (reprtStatsVO.getMaxUnit() > 0.5f) {
				reprtStatsVO.setMaxUnit(0.5f);
			}
		} else if (reprtStatsVO.getMaxUnit() > 1000) {
			if (reprtStatsVO.getMaxUnit() > 0.05f) {
				reprtStatsVO.setMaxUnit(0.05f);
			}
		}

		model.addAttribute("reprtStatsBarList", reprtStatsService.selectReprtStatsBarList(reprtStatsVO));
		model.addAttribute("reprtStatsByReprtTyList", reprtStatsService.selectReprtStatsByReprtTyList(reprtStatsVO));
		model.addAttribute("reprtStatsByReprtSttusList", reprtStatsService.selectReprtStatsByReprtSttusList(reprtStatsVO));

		cmmUseService.populateCmmCodeList("COM040", "COM040_reprtType");
		cmmUseService.populateCmmCodeList("COM042", "COM042_dateType");

		if( reprtStatsVO.getPmFromDate() == null || "".equals(reprtStatsVO.getPmFromDate()) ) {
			reprtStatsVO.setPmFromDate(DateUtil.addMonth(DateUtil.getToday(), -1));// 2011.09.19
		}
		if( reprtStatsVO.getPmToDate() == null || "".equals(reprtStatsVO.getPmToDate()) ) {
			reprtStatsVO.setPmToDate(DateUtil.getToday());// 2011.09.19
		}
		
		return WebUtil.adjustViewName("/sts/rst/ReprtStatsList");
	}

	/**
	 * 보고서 통계정보의 상세정보를 조회한다.
	 * 
	 * @param reprtStatsVO
	 */
	@RequestMapping("/sts/rst/detailReprtStats.do")
	@Secured("ROLE_ADMIN")
	public String detailReprtStats(
			@ModelAttribute ReprtStatsVO reprtStatsVO, 
			ModelMap model) {

		model.addAttribute("reprtStats", reprtStatsService.selectReprtStats(reprtStatsVO));

		return WebUtil.adjustViewName("/sts/rst/ReprtStatsDetail");
	}

	/**
	 * 보고서 통계정보의 등록화면으로 이동한다.
	 * 
	 * @param reprtStatsVO
	 */
	@RequestMapping("/sts/rst/registReprtStats.do")
	@Secured("ROLE_ADMIN")
	public String registReprtStats(
			@ModelAttribute ReprtStatsVO reprtStatsVO,
			ModelMap model) {

		cmmUseService.populateCmmCodeList("COM036", "COM036_reprtSttus");
		cmmUseService.populateCmmCodeList("COM040", "COM040_reprtType");

		return WebUtil.adjustViewName("/sts/rst/ReprtStatsRegist");
	}

	/**
	 * 보고서 통계정보를 등록한다.
	 * 
	 * @param reprtStatsVO
	 */
	@RequestMapping("/sts/rst/insertReprtStats.do")
	@Secured("ROLE_ADMIN")
	public String insertReprtStats(
			@ModelAttribute ReprtStatsVO reprtStatsVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(reprtStatsVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sts/rst/ReprtStatsRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		reprtStatsVO.setUserId(loginVO.getId());

		reprtStatsService.insertReprtStats(reprtStatsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sts/rst/listReprtStats.do");
	}

}
