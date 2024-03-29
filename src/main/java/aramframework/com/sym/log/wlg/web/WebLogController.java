package aramframework.com.sym.log.wlg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.sym.log.wlg.domain.WebLogVO;
import aramframework.com.sym.log.wlg.service.WebLogService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 웹 로그정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class WebLogController {

	@Autowired
	private WebLogService webLogService;

	/**
	 * 웹 로그 목록 조회
	 * 
	 * @param webLog
	 */
	@IncludedInfo(name = "웹 로그조회", order = 6080, gid = 60)
	@RequestMapping(value = "/sym/log/wlg/listWebLog.do")
	@Secured("ROLE_ADMIN")
	public String listWebLog(
			@ModelAttribute WebLogVO webLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		webLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", webLogService.selectWebLogInf(webLogVO));
		int totCnt = webLogService.selectWebLogInfCnt(webLogVO);

		webLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return "com/sym/log/wlg/WebLogList";
	}

	/**
	 * 웹 로그 상세 조회
	 * 
	 * @param webLog
	 */
	@RequestMapping(value = "/sym/log/wlg/detailWebLog.do")
	@Secured("ROLE_ADMIN")
	public String detailWebLog(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute WebLogVO webLogVO,
			ModelMap model) {

		model.addAttribute(webLogService.selectWebLog(webLogVO));

		return "com/sym/log/wlg/WebLogDetail";
	}

}
