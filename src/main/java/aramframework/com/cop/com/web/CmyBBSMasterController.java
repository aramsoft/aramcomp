package aramframework.com.cop.com.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardUseInfVO;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSUseInfoService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티용 게시판의 이용정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CmyBBSMasterController {

	@Autowired
	private BBSMasterService bbsMasterService;

	@Autowired
	private BBSUseInfoService bbsUseInfoService; 

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired 
	private UserInfService userInfService; // 커뮤니티 사용자 확인

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	private void checkAuthorityManager() {
		if( userInfService.checkCommunityManager().equals("N") ){
			throw new RuntimeException("해당 관리자만 사용하실 수 있습니다.");
		}
	}

	/**
	 * 사용중인 게시판 속성 정보의 목록을 조회 한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/listBdMstrByTrget.do")
	public String listBdMstrByTrget(
			@ModelAttribute BoardMasterVO boardMasterVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		String trgetId = WebUtil.getCurTrgetId();
		
		if( boardMasterVO.getTrgetId() == null 
			|| boardMasterVO.getTrgetId().equals("") ) {
			boardMasterVO.setTrgetId(trgetId);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		boardMasterVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsMasterService.selectBdMstrListByTrget(boardMasterVO));
		int totCnt = bbsMasterService.selectBdMstrListCntByTrget(boardMasterVO);

		boardMasterVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("cop/com/BdMstrListByTrget");
	}

	/**
	 * 커뮤니티, 동호회에서 사용을 위한 게시판 마스터 등록 화면으로 이동한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/registBdMstrByTrget.do")
	@Secured("ROLE_USER")
	public String registBdMstrByTrget(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		cmmUseService.populateCmmCodeList("COM004", "COM004_bbsType");
		cmmUseService.populateCmmCodeList("COM009", "COM009_bbsAttrb");

		// ---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		// ---------------------------------

		if (ComponentChecker.hasComponent("bbsCommentService")) {
			model.addAttribute("useComment", "true");
		}
		if (ComponentChecker.hasComponent("bbsSatisfactionService")) {
			model.addAttribute("useSatisfaction", "true");
		}

		return WebUtil.adjustViewName("cop/com/BdMstrRegistByTrget");
	}

	/**
	 * 게시판 사용을 위한 신규 게시판 속성정보를 생성한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/insertBdMstrByTrget.do")
	@Secured("ROLE_USER")
	public String insertBdMstrByTrget(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardMasterVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("cop/com/BdMstrRegistByTrget");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setFrstRegisterId(loginVO.getUniqId());
		boardMasterVO.setUseAt("Y");
		boardMasterVO.setBbsUseFlag("Y");

		String targetId = boardMasterVO.getTrgetId();
		if (targetId.startsWith("CMMNTY_")) {
			boardMasterVO.setRegistSeCode("REGC06");
		} else {
			boardMasterVO.setTrgetId("SYSTEM_DEFAULT_BOARD");
			boardMasterVO.setRegistSeCode("REGC01");
		}

		bbsMasterService.insertBBSMastetInf(boardMasterVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, boardMasterVO, "/cop/com/listBdMstrByTrget.do");
	}

	/**
	 * 커뮤니티, 동호회에서 사용을 위한 게시판 마스터 수정 화면으로 이동한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/editBdMstrByTrget.do")
	@Secured("ROLE_USER")
	public String editBdMstrByTrget(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO, 
			@ModelAttribute BoardUseInfVO boardUseInfVO, 
			HttpServletRequest request,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

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

		boardUseInfVO = bbsUseInfoService.selectBBSUseInf(boardUseInfVO);

		// 시스템 사용 게시판의 경우 URL 표시
	    String bbsId = boardUseInfVO.getBbsId();
//		if ("SYSTEM_DEFAULT_BOARD".equals(vo.getTrgetId())) {
			if (boardUseInfVO.getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) { // 익명게시판
				boardUseInfVO.setProvdUrl2(request.getContextPath() 
						+ "/content/board/anonymous/" + WebUtil.getPathId(bbsId) + "/articles"); 
			} else {
				boardUseInfVO.setProvdUrl2(request.getContextPath() 
						+ "/content/board/" + WebUtil.getPathId(bbsId) + "/articles"); 
			}
//		}
		model.addAttribute(boardUseInfVO);
		
		return WebUtil.adjustViewName("cop/com/BdMstrEditByTrget");
	}

	/**
	 * 게시판 사용을 위한 게시판 속성정보를 수정한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/updateBdMstrByTrget.do")
	@Secured("ROLE_USER")
	public String updateBdMstrByTrget(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardMasterVO boardMasterVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardMasterVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("cop/com/BdMstrEditByTrget");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setLastUpdusrId(loginVO.getUniqId());
		boardMasterVO.setUseAt("Y");

		bbsMasterService.updateBBSMasterInf(boardMasterVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, boardMasterVO, "/cop/com/listBdMstrByTrget.do");
	}

	/**
	 * 등록된 게시판 속성정보를 삭제한다.
	 * 
	 * @param boardMasterVO
	 */
	@RequestMapping("/cop/com/deleteBdMstrByTrget.do")
	public String deleteBdMstrByTrget(
			@ModelAttribute BoardMasterVO boardMasterVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardMasterVO.setLastUpdusrId(loginVO.getUniqId());

		bbsMasterService.deleteBBSMasterInf(boardMasterVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, boardMasterVO, "/cop/com/listBdMstrByTrget.do");
	}

	/**
	 * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
	 * 
	 * @param boardMasterVO
	 * @param boardUseInfVO
	 */
	@RequestMapping("/cop/com/updateBoardUseInfByTrget.do")
	public String updateBoardUseInfByTrget(
			@ModelAttribute BoardMasterVO boardMasterVO,
			@ModelAttribute BoardUseInfVO boardUseInfVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardUseInfVO.setLastUpdusrId(loginVO.getUniqId());

		bbsUseInfoService.updateBBSUseInf(boardUseInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, boardMasterVO, "/cop/com/listBdMstrByTrget.do");
	}

}
