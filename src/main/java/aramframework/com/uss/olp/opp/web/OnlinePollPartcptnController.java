package aramframework.com.uss.olp.opp.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.olp.opp.service.OnlinePollPartcptnService;
import aramframework.com.uss.olp.opp.service.OnlinePollPartcptnVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인POLL참여를 처리하는 Controller Class 구현
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
public class OnlinePollPartcptnController {

	@Resource(name = "onlinePollPartcptnService")
	private OnlinePollPartcptnService onlinePollPartcptnService;

	@Resource(name = "cmmUseService")
	private CmmUseService cmmUseService;

	/**
	 * 온라인POLL참여 목록을 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	@RequestMapping(value = "/uss/olp/opp/listOnlinePollPartcptnMainPage.do")
	public String listOnlinePollPartcptnMainPage(
			@ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
			ModelMap model) {

		onlinePollPartcptnVO.setRecordPerPage(5);
		onlinePollPartcptnVO.setFirstIndex(0);

		model.addAttribute("resultList", onlinePollPartcptnService.selectOnlinePollManageList(onlinePollPartcptnVO));

		return WebUtil.adjustViewName("/uss/olp/opp/OnlinePollPartcptnMainPage");
	}

	/**
	 * 온라인POLL참여 목록을 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	@IncludedInfo(name = "온라인poll참여", order = 5161, gid = 50)
	@RequestMapping(value = "/uss/olp/opp/listOnlinePollPartcptn.do")
	public String listOnlinePollPartcptn(
			@ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		onlinePollPartcptnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", onlinePollPartcptnService.selectOnlinePollManageList(onlinePollPartcptnVO));

		int totCnt = (Integer) onlinePollPartcptnService.selectOnlinePollManageListCnt(onlinePollPartcptnVO);
		onlinePollPartcptnVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/opp/OnlinePollPartcptnList");
	}

	/**
	 * 온라인POLL참여 등록화면으로 이동한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	@RequestMapping(value = "/uss/olp/opp/registOnlinePollPartcptn.do")
	@Secured("ROLE_USER")
	public String registOnlinePollPartcptn(
			@ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
			ModelMap model) {

		onlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptnVO);
			
		// POLL종류 설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");
	// 온라인POLL항목 정보 설정
		model.addAttribute("pollItemList", onlinePollPartcptnService.selectOnlinePollItemList(onlinePollPartcptnVO));

		return WebUtil.adjustViewName("/uss/olp/opp/OnlinePollPartcptnRegist");
	}

	/**
	 * 온라인POLL참여를 등록한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	@RequestMapping(value = "/uss/olp/opp/insertOnlinePollPartcptn.do")
	@Secured("ROLE_USER")
	public String insertOnlinePollPartcptn(
			@ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlinePollPartcptnVO.setFrstRegisterId(loginVO.getUniqId());

		// 투표여부 체크
		if (onlinePollPartcptnService.selectOnlinePollResult(onlinePollPartcptnVO) != 0) {

			model.addAttribute("reusltScript", "한 온라인POLL엔 한번만 투표 가능합니다. ");
			return "forward:/uss/olp/opp/listOnlinePollPartcptn.do";
		}

		onlinePollPartcptnService.insertOnlinePollResult(onlinePollPartcptnVO);

		model.addAttribute("message", "온라인POLL참여에 응해주셔서 감사합니다!");
        return WebUtil.redirectJsp(model, "/uss/olp/opp/listOnlinePollPartcptn.do");
	}

	/**
	 * 온라인POLL관리 통계를 조회한다.
	 * 
	 * @param onlinePollPartcptnVO
	 */
	@RequestMapping(value = "/uss/olp/opp/statisticsOnlinePollPartcptn.do")
	public String statisticsOnlinePollPartcptn(
			HttpServletRequest request, 
			@ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
			ModelMap model) {

		// 온라인POLL관리 정보 설정
		onlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptnVO);
		
		// POLL종류 설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		// 온라인POLL항목 정보 설정
		model.addAttribute("pollItemList", onlinePollPartcptnService.selectOnlinePollItemList(onlinePollPartcptnVO));
		// 온라인POLL결과 정보 설정
		model.addAttribute("statisticsList", onlinePollPartcptnService.selectOnlinePollManageStatistics(onlinePollPartcptnVO));

		// 이전 주소
		model.addAttribute("returnUrl", request.getParameter("returnUrl") );

		return WebUtil.adjustViewName("/uss/olp/opp/OnlinePollPartcptnStatistics");
	}

}
