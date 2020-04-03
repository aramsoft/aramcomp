package aramframework.com.cop.bbs.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시판의 이용정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BBSUseInfoController {

	@Autowired 
	private BBSUseInfoService bbsUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 게시판 사용정보 목록을 조회한다.
	 * 
	 * @param boardUseInfVO
	 */
	@IncludedInfo(name = "게시판사용정보", order = 4010, gid = 40)
	@RequestMapping("/cop/bbs/listBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String listBoardUseInf(
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		boardUseInfVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsUseService.selectBBSUseInfs(boardUseInfVO));
		int totCnt = bbsUseService.selectBBSUseInfsCnt(boardUseInfVO);

		boardUseInfVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		if (ComponentChecker.hasComponent("communityManageService")) {// 2011.09.15
			model.addAttribute("useCommunity", "true");
		}

		return "cop/bbs/BoardUseInfList";
	}

	/**
	 * 게시판 사용정보 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/bbs/registBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String registBoardUseInf(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			ModelMap model) {

		if (ComponentChecker.hasComponent("communityManageService")) {// 2011.09.15
			model.addAttribute("useCommunity", "true");
		}

		return "cop/bbs/BoardUseInfRegist";
	}

	/**
	 * 게시판 사용정보를 등록한다.
	 * 
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/bbs/insertBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String insertBoardUseInf(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardUseInfVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/bbs/BoardUseInfRegist";
		}

		String registSeCode = "";
		if ("CMMNTY".equals(boardUseInfVO.getTrgetType())) {
			registSeCode = "REGC06";	// Community
		} else {
			registSeCode = "REGC01";	// 시스템
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardUseInfVO.setUseAt("Y");
		boardUseInfVO.setFrstRegisterId(loginVO.getUserId());
		boardUseInfVO.setRegistSeCode(registSeCode);

		bbsUseService.insertBBSUseInf(boardUseInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, boardUseInfVO, "/cop/bbs/listBoardUseInf.do");
	}

	/**
	 * 게시판 사용정보 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/bbs/editBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String editBoardUseInf(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			HttpServletRequest request,
			ModelMap model) {
		
		boardUseInfVO = bbsUseService.selectBBSUseInf(boardUseInfVO);
		boardUseInfVO.setProvdUrl2(request.getContextPath() + "/board/" + boardUseInfVO.getPathId() + "/list");
			
		model.addAttribute(boardUseInfVO);
					
		return "cop/bbs/BoardUseInfEdit";
	}

	/**
	 * 게시판 사용정보를 수정한다.
	 * 
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/bbs/updateBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String updateBoardUseInf(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardUseInfVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/bbs/BoardUseInfEdit";
		}

		bbsUseService.updateBBSUseInf(boardUseInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, boardUseInfVO, "/cop/bbs/listBoardUseInf.do");
	}

	/**
	 * 게시판 사용 정보를 삭제한다.
	 * 
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/bbs/deleteBoardUseInf.do")
	@Secured("ROLE_ADMIN")
	public String deleteBoardUseInf(
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			ModelMap model) {

		bbsUseService.deleteBBSUseInf(boardUseInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, boardUseInfVO, "/cop/bbs/listBoardUseInf.do");
	}

}
