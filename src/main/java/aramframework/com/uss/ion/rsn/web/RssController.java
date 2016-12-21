package aramframework.com.uss.ion.rsn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.rsn.domain.RssInfoVO;
import aramframework.com.uss.ion.rsn.service.RssService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * RSS서비스를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class RssController {

	@Autowired
	private RssService rssService;

	/**
	 * RSS서비스 목록을 조회한다.
	 * 
	 * @param rssInfoVO
	 */
	@IncludedInfo(name = "RSS서비스", order = 5231, gid = 50)
	@RequestMapping(value = "/uss/ion/rsn/listRssService.do")
	public String listRssService(
			@ModelAttribute RssInfoVO rssInfoVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		rssInfoVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", rssService.selectRssServiceList(rssInfoVO));
		int totCnt = (Integer) rssService.selectRssServiceListCnt(rssInfoVO);

		rssInfoVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/rsn/RssServiceList");
	}

	/**
	 * RSS서비스 목록을 상세조회 조회한다.
	 * 
	 * @param rssInfoVO
	 */
	@RequestMapping(value = "/uss/ion/rsn/detailRssService.do")
	public String detailRssService(
			RssInfoVO rssInfoVO, 
			ModelMap model) {
		
		if( rssInfoVO.getRssId() == null || rssInfoVO.getRssId().equals("") ) {
			throw new RuntimeException("rssId is not found !!!");
		}
		
		model.addAttribute(rssService.selectRssServiceDetail(rssInfoVO));

		model.addAttribute("mapRssInfoList", rssService.selectRssServiceTable(rssInfoVO));

		return WebUtil.adjustViewName("/uss/ion/rsn/RssService");
	}

}
