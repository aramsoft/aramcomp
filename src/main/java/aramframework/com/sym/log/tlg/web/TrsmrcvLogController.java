package aramframework.com.sym.log.tlg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.log.tlg.domain.TrsmrcvLogVO;
import aramframework.com.sym.log.tlg.service.TrsmrcvLogService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 송수신 로그정보를 관리하기 위한 컨트롤러 클래스
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
public class TrsmrcvLogController {

	@Autowired
	private TrsmrcvLogService trsmrcvLogService;

	/**
	 * 송수신 로그 목록 조회
	 * 
	 * @param trsmrcvLogVO
	 */
	@IncludedInfo(name = "송수신 로그관리", order = 6120, gid = 60)
	@RequestMapping(value = "/sym/log/tlg/listTrsmrcvLog.do")
	@Secured("ROLE_ADMIN")
	public String listTrsmrcvLog(
			@ModelAttribute TrsmrcvLogVO trsmrcvLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		trsmrcvLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", trsmrcvLogService.selectTrsmrcvLogInf(trsmrcvLogVO));

		int totCnt = trsmrcvLogService.selectTrsmrcvLogInfCnt(trsmrcvLogVO);
		trsmrcvLogVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sym/log/tlg/TrsmrcvLogList");
	}

	/**
	 * 송수신 로그 상세 조회
	 * 
	 * @param trsmrcvLogVO
	 */
	@RequestMapping(value = "/sym/log/tlg/detailTrsmrcvLog.do")
	@Secured("ROLE_ADMIN")
	public String detailTrsmrcvLog(
			@ModelAttribute TrsmrcvLogVO trsmrcvLogVO) {

		trsmrcvLogService.selectTrsmrcvLog(trsmrcvLogVO);

		return WebUtil.adjustViewName("/sym/log/tlg/TrsmrcvLogDetail");
	}

	/**
	 * 송수신 로그 테스트 화면
	 * 
	 * @param trsmrcvLogVO
	 */
	@RequestMapping(value = "/sym/log/tlg/registTrsmrcvLog.do")
	@Secured("ROLE_ADMIN")
	public String registTrsmrcvLog(
			@ModelAttribute TrsmrcvLogVO trsmrcvLogVO) {

		return WebUtil.adjustViewName("/sym/log/tlg/TrsmrcvLogRegist");
	}

	/**
	 * 송수신 로그 테스트
	 * 
	 * @param trsmrcvLogVO
	 */
	@RequestMapping(value = "/sym/log/tlg/insertTrsmrcvLog.do")
	@Secured("ROLE_ADMIN")
	public String insertTrsmrcvLog(
			@ModelAttribute TrsmrcvLogVO trsmrcvLogVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		trsmrcvLogVO.setRqesterId(loginVO.getUniqId());

		trsmrcvLogService.logInsertTrsmrcvLog(trsmrcvLogVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/log/tlg/listTrsmrcvLog.do");
	}

}
