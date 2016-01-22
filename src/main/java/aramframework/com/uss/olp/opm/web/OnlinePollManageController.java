package aramframework.com.uss.olp.opm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.olp.opm.service.OnlinePollManageService;
import aramframework.com.uss.olp.opm.service.OnlinePollItemVO;
import aramframework.com.uss.olp.opm.service.OnlinePollManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인POLL관리를 처리하는 Controller Class 구현
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
public class OnlinePollManageController {

	@Autowired
	private OnlinePollManageService onlinePollManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 온라인POLL관리 목록을 조회한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@IncludedInfo(name = "온라인poll관리", order = 5160, gid = 50)
	@RequestMapping(value = "/uss/olp/opm/listOnlinePoll.do")
	@Secured("ROLE_USER")
	public String listOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		onlinePollManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", onlinePollManageService.selectOnlinePollManageList(onlinePollManageVO));

		int totCnt = (Integer) onlinePollManageService.selectOnlinePollManageListCnt(onlinePollManageVO);
		onlinePollManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollList");
	}

	/**
	 * 온라인POLL관리 목록을 상세조회한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/detailOnlinePoll.do")
	public String detailOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO) {

		onlinePollManageService.selectOnlinePollManageDetail(onlinePollManageVO);

		// POLL종류 설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollDetail");
	}

	/**
	 * 온라인POLL관리 등록화면으로 이동한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/registOnlinePoll.do")
	public String registOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO) {

		// POLL종류 Select박스 설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollRegist");
	}

	/**
	 * 온라인POLL관리를 등록한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/insertOnlinePoll.do")
	public String insertOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(onlinePollManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlinePollManageVO.setFrstRegisterId(loginVO.getUniqId());

		onlinePollManageService.insertOnlinePollManage(onlinePollManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePoll.do");
	}

	/**
	 * 온라인POLL관리 수정화면으로 이동한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/editOnlinePoll.do")
	public String editOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO) {

		// 게시물 정보 설정
		onlinePollManageService.selectOnlinePollManageDetail(onlinePollManageVO);

		// POLL종류 Select박스 설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollEdit");
	}

	/**
	 * 온라인POLL관리를 수정한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/updateOnlinePoll.do")
	public String updateOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(onlinePollManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlinePollManageVO.setLastUpdusrId(loginVO.getUniqId());

		onlinePollManageService.updateOnlinePollManage(onlinePollManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePoll.do");
	}

	/**
	 * 온라인POLL관리 목록을삭제한다.
	 * 
	 * @param onlinePollManageVO
	 */
	@RequestMapping(value = "/uss/olp/opm/deleteOnlinePoll.do")
	public String deleteOnlinePoll(
			@ModelAttribute OnlinePollManageVO onlinePollManageVO, 
			ModelMap model) {

		onlinePollManageService.deleteOnlinePollManage(onlinePollManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePoll.do");
	}

	/**
	 * 온라인POLL항목을조회한다.
	 * 
	 * @param onlinePollItemVO
	 */
	@RequestMapping(value = "/uss/olp/opm/listOnlinePollItem.do")
	public String listOnlinePollItem(
			@ModelAttribute OnlinePollItemVO onlinePollItemVO, 
			ModelMap model) {

		model.addAttribute("resultList", onlinePollManageService.selectOnlinePollItemList(onlinePollItemVO));

		return WebUtil.adjustViewName("/uss/olp/opm/OnlinePollItemList");
	}

	/**
	 * 온라인POLL항목을 등록한다.
	 * 
	 * @param onlinePollItemVO
	 */
	@RequestMapping(value = "/uss/olp/opm/insertOnlinePollItem.do")
	public String insertOnlinePollItem(
			@ModelAttribute OnlinePollItemVO onlinePollItemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlinePollItemVO.setFrstRegisterId(loginVO.getUniqId());

		onlinePollManageService.insertOnlinePollItem(onlinePollItemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePollItem.do?pollId="+onlinePollItemVO.getPollId());
	}

	/**
	 * 온라인POLL항목을 수정한다.
	 * 
	 * @param onlinePollItemVO
	 */
	@RequestMapping(value = "/uss/olp/opm/updateOnlinePollItem.do")
	public String updateOnlinePollItem(
			@ModelAttribute OnlinePollItemVO onlinePollItemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		onlinePollItemVO.setLastUpdusrId(loginVO.getUniqId());

		onlinePollManageService.updateOnlinePollItem(onlinePollItemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePollItem.do?pollId="+onlinePollItemVO.getPollId());
	}

	/**
	 * 온라인POLL항목을 삭제한다.
	 * 
	 * @param onlinePollItemVO
	 */
	@RequestMapping(value = "/uss/olp/opm/deleteOnlinePollItem.do")
	public String deleteOnlinePollItem(
			@ModelAttribute OnlinePollItemVO onlinePollItemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		onlinePollManageService.deleteOnlinePollItem(onlinePollItemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olp/opm/listOnlinePollItem.do?pollId="+onlinePollItemVO.getPollId());
	}

}
