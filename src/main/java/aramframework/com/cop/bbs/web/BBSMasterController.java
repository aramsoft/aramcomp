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
import aramframework.com.cmm.com.service.CmmUseService;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시판 속성관리를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BBSMasterController {

	@Autowired
	private BBSMasterService bbsMasterService;

	@Autowired
	private BBSUseInfoService bbsUseInfoService;
	
	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 게시판 마스터 선택 팝업을 위한 목록을 조회한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/listBoardMasterPopup.do")
	public String listBoardMasterPopup(
			@ModelAttribute BoardMasterVO boardMasterVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		boardMasterVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsMasterService.selectNotUsedBdMstrList(boardMasterVO));
		int totCnt = bbsMasterService.selectNotUsedBdMstrListCnt(boardMasterVO);

		boardMasterVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute(paginationInfo);

		return "com/cop/bbs/BoardMasterPopup";
	}

	/**
	 * 게시판 마스터 목록을 조회한다.
	 * 
	 * @param boardMasterVO
	 */
	@IncludedInfo(name = "게시판관리", order = 4000, gid = 40)
	@RequestMapping("/cop/bbs/listBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String listBoardMaster(
			@ModelAttribute BoardMasterVO boardMasterVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		boardMasterVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsMasterService.selectBBSMasterInfs(boardMasterVO));
		int totCnt = bbsMasterService.selectBBSMasterInfsCnt(boardMasterVO);

		boardMasterVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		if (ComponentChecker.hasComponent("communityManageService")) {// 2011.09.15
			model.addAttribute("useCommunity", "true");
		}

		return "com/cop/bbs/BoardMasterList";
	}

	/**
	 * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/registBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String registBoardMaster(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			ModelMap model) {

		cmmUseService.populateCmmCodeList("COM004", "COM004_bbsType");
		cmmUseService.populateCmmCodeList("COM009", "COM009_bbsAttrb");

		// ---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		// ---------------------------------
		// String flag = AramProperties.getProperty("Globals.addedOptions");
		// if (flag != null && flag.trim().equalsIgnoreCase("true")) {
		// 		model.addAttribute("addedOptions", "true");
		// }
		// //-------------------------------

		// ---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		// ---------------------------------

		if (ComponentChecker.hasComponent("bbsCommentService")) {
			model.addAttribute("useComment", "true");
		}
		if (ComponentChecker.hasComponent("bbsSatisfactionService")) {
			model.addAttribute("useSatisfaction", "true");
		}

		return "com/cop/bbs/BoardMasterRegist";
	}

	/**
	 * 신규 게시판 마스터 정보를 등록한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/insertBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String insertBoardMaster(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardMasterVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/bbs/BoardMasterRegist";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setFrstRegisterId(loginVO.getUserId());
		boardMasterVO.setUseAt("Y");
		boardMasterVO.setBbsUseFlag("N");	//not yet bbs 

		bbsMasterService.insertBBSMastetInf(boardMasterVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/bbs/listBoardMaster.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 게시판 마스터 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/editBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String editBoardMaster(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			HttpServletRequest request,
			ModelMap model) {
	
		model.addAttribute(bbsMasterService.selectBBSMasterInf(boardMasterVO));

		// ---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		// ---------------------------------

		if (ComponentChecker.hasComponent("bbsCommentService")) {
			model.addAttribute("useComment", "true");
		}
		if (ComponentChecker.hasComponent("bbsSatisfactionService")) {
			model.addAttribute("useSatisfaction", "true");
		}

		if ( bbsUseInfoService.existBBSUseInf(boardUseInfVO) != 0 ) {
			boardUseInfVO = bbsUseInfoService.selectBBSUseInf(boardUseInfVO);
	
			// 시스템 사용 게시판의 경우 URL 표시
			if ("SYSTEM_DEFAULT_BOARD".equals(boardUseInfVO.getTrgetId())) {
				boardUseInfVO.setProvdUrl2(request.getContextPath() + "/board/" + boardUseInfVO.getPathId() + "/list");
			}
			model.addAttribute(boardUseInfVO);
		}
		
		return "com/cop/bbs/BoardMasterEdit";
	}

	/**
	 * 게시판 마스터 정보를 수정한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/updateBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String updateBoardMaster(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardMasterVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/bbs/BoardMasterEdit";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setLastUpdusrId(loginVO.getUserId());

		bbsMasterService.updateBBSMasterInf(boardMasterVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/bbs/listBoardMaster.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 게시판 마스터 정보를 삭제한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/bbs/deleteBoardMaster.do")
	@Secured("ROLE_ADMIN")
	public String deleteBoardMaster(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setLastUpdusrId(loginVO.getUserId());

		bbsMasterService.deleteBBSMasterInf(boardMasterVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/bbs/listBoardMaster.do");
	    return "com/cmm/redirect";
	}

}
