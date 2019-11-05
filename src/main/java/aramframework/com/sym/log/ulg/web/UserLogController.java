package aramframework.com.sym.log.ulg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.log.ulg.domain.UserLogVO;
import aramframework.com.sym.log.ulg.service.UserLogService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 사용자 로그정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class UserLogController {

	@Autowired
	private UserLogService userLogService;

	/**
	 * 사용자 로그 목록 조회
	 * 
	 * @param userLogVO
	 */
	@IncludedInfo(name = "사용자 로그조회", order = 6110, gid = 60)
	@RequestMapping(value = "/sym/log/ulg/listUserLog.do")
	@Secured("ROLE_ADMIN")
	public String listUserLog(
			@ModelAttribute UserLogVO userLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		userLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userLogService.selectUserLogInf(userLogVO));
		int totCnt = userLogService.selectUserLogInfCnt(userLogVO);

		userLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/log/ulg/UserLogList");
	}

	/**
	 * 사용자 로그 상세 조회
	 * 
	 * @param userLogVO
	 */
	@RequestMapping(value = "/sym/log/ulg/detailUserLog.do")
	@Secured("ROLE_ADMIN")
	public String detailUserLog(
			@ModelAttribute UserLogVO userLogVO,
			ModelMap model) {

		model.addAttribute(userLogService.selectUserLog(userLogVO));

		return WebUtil.adjustViewName("/sym/log/ulg/UserLogDetail");
	}

}
