package aramframework.com.sym.log.lgm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.sym.log.lgm.domain.SysLogVO;
import aramframework.com.sym.log.lgm.service.SysLogService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시스템 로그정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 시스템 로그 목록 조회
	 * 
	 * @param sysLogVO
	 */
	@IncludedInfo(name = "시스템 로그조회", order = 6090, gid = 60)
	@RequestMapping(value = "/sym/log/lgm/listSysLog.do")
	@Secured("ROLE_ADMIN")
	public String listSysLog(
			@ModelAttribute SysLogVO sysLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		sysLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", sysLogService.selectSysLogInf(sysLogVO));
		int totCnt = sysLogService.selectSysLogInfCnt(sysLogVO);

		sysLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/log/lgm/SysLogList";
	}

	/**
	 * 시스템 로그 상세 조회
	 * 
	 * @param sysLogVO
	 */
	@RequestMapping(value = "/sym/log/lgm/detailSysLog.do")
	@Secured("ROLE_ADMIN")
	public String detailSysLog(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SysLogVO sysLogVO,
			ModelMap model) {

		model.addAttribute(sysLogService.selectSysLog(sysLogVO));

		return "com/sym/log/lgm/SysLogDetail";
	}

}
