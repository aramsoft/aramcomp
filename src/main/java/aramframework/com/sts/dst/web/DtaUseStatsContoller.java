package aramframework.com.sts.dst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sts.dst.domain.DtaUseStatsVO;
import aramframework.com.sts.dst.service.DtaUseStatsService;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 자료이용현황 통계에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class DtaUseStatsContoller {

	@Autowired
	private DtaUseStatsService dtaUseStatsService;

	@Autowired
	private CmmUseService cmmUseService;
	
	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	@IncludedInfo(name = "자료이용현황통계", order = 3050, gid = 30)
	@RequestMapping("/sts/dst/listDtaUseStats.do")
	@Secured("ROLE_ADMIN")
	public String listDtaUseStats(
			@ModelAttribute DtaUseStatsVO dtaUseStatsVO,
			ModelMap model) {

		dtaUseStatsVO.getSearchVO().setRecordPerPage(5);

		PaginationInfo paginationInfo = new PaginationInfo();
		dtaUseStatsVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", dtaUseStatsService.selectDtaUseStatsList(dtaUseStatsVO));
		int totPageCnt = dtaUseStatsService.selectDtaUseStatsListCnt(dtaUseStatsVO);

		dtaUseStatsVO.getSearchVO().setTotalRecordCount(totPageCnt);
		paginationInfo.setTotalRecordCount(totPageCnt);

		model.addAttribute(paginationInfo);

		int totCnt = dtaUseStatsService.selectDtaUseStatsListBarListCnt(dtaUseStatsVO);
		if (totCnt > 10 && totCnt <= 100) {
			if (dtaUseStatsVO.getMaxUnit() > 5.0f) {
				dtaUseStatsVO.setMaxUnit(5.0f);
			}
		} else if (totCnt > 100 && totCnt <= 1000) {
			if (dtaUseStatsVO.getMaxUnit() > 0.5f) {
				dtaUseStatsVO.setMaxUnit(0.5f);
			}
		} else if (dtaUseStatsVO.getMaxUnit() > 1000) {
			if (dtaUseStatsVO.getMaxUnit() > 0.05f) {
				dtaUseStatsVO.setMaxUnit(0.05f);
			}
		}

		model.addAttribute("dtaUseStatsBarList", dtaUseStatsService.selectDtaUseStatsBarList(dtaUseStatsVO));

		cmmUseService.populateCmmCodeList("COM042", "COM042_dateType");

		if( dtaUseStatsVO.getPmFromDate() == null || "".equals(dtaUseStatsVO.getPmFromDate()) ) {
			dtaUseStatsVO.setPmFromDate(DateUtil.addMonth(DateUtil.getToday(), -1));// 2011.09.19
		}
		if( dtaUseStatsVO.getPmToDate() == null || "".equals(dtaUseStatsVO.getPmToDate()) ) {
			dtaUseStatsVO.setPmToDate(DateUtil.getToday());// 2011.09.19
		}
		
		return WebUtil.adjustViewName("/sts/dst/DtaUseStatsList");
	}

	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * 
	 * @param dtaUseStatsVO
	 */
	@RequestMapping("/sts/dst/detailDtaUseStats.do")
	@Secured("ROLE_ADMIN")
	public String detailDtaUseStats(
			@ModelAttribute DtaUseStatsVO dtaUseStatsVO, 
			ModelMap model)  {

		model.addAttribute("dtaUseStatsList", dtaUseStatsService.selectDtaUseStats(dtaUseStatsVO));

		return WebUtil.adjustViewName("/sts/dst/DtaUseStatsDetail");
	}

}
