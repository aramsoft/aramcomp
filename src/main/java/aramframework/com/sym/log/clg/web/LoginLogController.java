package aramframework.com.sym.log.clg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.sym.log.clg.domain.LoginLogVO;
import aramframework.com.sym.log.clg.service.LoginLogService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 접속로그정보를 관리하기 위한 컨트롤러 클래스 
 *  
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class LoginLogController {

	@Autowired
	private LoginLogService loginLogService;

	/**
	 * 로그인 로그 목록 조회
	 * 
	 * @param loginLogVO
	 */
	@IncludedInfo(name = "로그인 로그조회", order = 6100, gid = 60)
	@RequestMapping(value = "/sym/log/clg/listLoginLog.do")
	@Secured("ROLE_ADMIN")
	public String listLoginLog(
			@ModelAttribute LoginLogVO loginLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		loginLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", loginLogService.selectLoginLogInf(loginLogVO));
		int totCnt = loginLogService.selectLoginLogInfCnt(loginLogVO);

		loginLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/log/clg/LoginLogList";
	}

	/**
	 * 로그인 로그 상세 조회
	 * 
	 * @param loginLogVO
	 */
	@RequestMapping(value = "/sym/log/clg/detailLoginLog.do")
	@Secured("ROLE_ADMIN")
	public String detailLoginLog(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute LoginLogVO loginLogVO,
			ModelMap model) {

		model.addAttribute(loginLogService.selectLoginLog(loginLogVO));

		return "com/sym/log/clg/LoginLogDetail";
	}

}
