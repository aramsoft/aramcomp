package aramframework.com.cop.clb.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.BoardVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.clb.service.ClubUserVO;
import aramframework.com.cop.clb.service.ClubVO;
import aramframework.com.cop.clb.service.ClubManageService;
import aramframework.com.cop.com.service.ConfirmHistoryVO;
import aramframework.com.cop.com.service.ConfirmService;
import aramframework.com.cop.com.service.UserInfService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 동호회 정보를 관리하기 위한 컨트롤러 클래스
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
public class ClubManageController {

	@Resource(name = "clubManageService")
	private ClubManageService clubService;

	@Resource(name = "confirmService")
	private ConfirmService confmService;

	@Resource(name = "bbsMasterService")
	private BBSMasterService bbsMasterService;

	@Resource(name = "bbsBoardService")
	private BBSBoardService boardService;
	
	@Resource(name = "userInfService")
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
	 * 포틀릿를 위하여 커뮤니티에 사용되는 동호회 목록을 조회 한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/clb/listClubPortlet.do")
	public String listClubPortlet(
			@ModelAttribute SearchVO searchVO, 
			ModelMap model) {
		
		model.addAttribute("resultList", clubService.selectClubListPortlet(searchVO));

		return WebUtil.adjustViewName("/cop/clb/ClubListPortlet");
	}

	/**
	 * 포틀릿를 위하여 특정 커뮤니티에 사용되는 동호회 목록을 조회 한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/listClubPortletByTrget.do")
	public String listClubPortletByTrget(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {

		model.addAttribute("resultList", clubService.selectClubListPortletByTrget(clubVO));

		return WebUtil.adjustViewName("/cop/clb/ClubListPortletByTrget");
	}

	/**
	 * 팝업을 위하여 동호회에 대한 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/listClubPopup.do")
	public String listClubPopup(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {

		clubVO.setUseAt("Y");

		PaginationInfo paginationInfo = new PaginationInfo();
		clubVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", clubService.selectClubInfs(clubVO));

		int totCnt = clubService.selectClubInfsCnt(clubVO);
		clubVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/clb/ClubListPopup");
	}

	/**
	 * 동호회에 대한 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/listClub.do")
	@Secured("ROLE_ADMIN")
	public String listClub(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		clubVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", clubService.selectClubInfs(clubVO));

		int totCnt = clubService.selectClubInfsCnt(clubVO);
		clubVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/clb/ClubList");
	}

	/**
	 * 동호회에 대한  정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/detailClub.do")
	@Secured("ROLE_USER")
	public String detailClub(
			HttpServletRequest request,
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) 
	throws Exception {
		
		checkAuthorityManager(); // server-side 권한 확인

		clubService.selectClubInf(clubVO);

		// -----------------------
		// 제공 URL
		// -----------------------
		clubVO.setProvdUrl(request.getContextPath() + "/cop/clb/ClubMainPage.do?clbId=" + clubVO.getClbId());
		clubVO.setProvdUrl2(request.getContextPath() + "/content/club/" + clubVO.getPathId());
		// //---------------------

		model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/cop/clb/ClubDetail");
	}

	/**
	 * 동호회 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/registClub.do")
	@Secured("ROLE_USER")
	public String registClub(
			@ModelAttribute ClubVO clubVO) {
		
		return WebUtil.adjustViewName("/cop/clb/ClubRegist");
	}

	/**
	 * 동호회 정보를 등록한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/insertClub.do")
	@Secured("ROLE_USER")
	public String insertClub(
			@ModelAttribute ClubVO clubVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(clubVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/clb/ClubRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubVO.setUseAt("Y");
		clubVO.setRegistSeCode("REGC03");	// 동호회 등록
		clubVO.setFrstRegisterId(loginVO.getUniqId());

		clubService.insertClubInf(clubVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/clb/listClub.do");
	}

	/**
	 * 동호회 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/editClub.do")
	@Secured("ROLE_USER")
	public String editClub(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {
		
		checkAuthorityManager(); // server-side 권한 확인

		clubService.selectClubInf(clubVO);

		model.addAttribute("operator", clubService.selectClubOperatorInf(clubVO));
		model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/cop/clb/ClubEdit");
	}

	/**
	 * 동호회에 대한 상세내용을 수정한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/updateClub.do")
	@Secured("ROLE_USER")
	public String updateClub(
			@ModelAttribute ClubVO clubVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(clubVO, bindingResult);
		if (bindingResult.hasErrors()) {

			model.addAttribute("operator", clubService.selectClubOperatorInf(clubVO));
			model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));

			return WebUtil.adjustViewName("/cop/clb/ClubEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubVO.setLastUpdusrId(loginVO.getUniqId());

		if ("Y".equals(clubVO.getUseAt())) {
			clubService.updateClubInf(clubVO);
		} else {
			clubService.deleteClubInf(clubVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/clb/listClub.do");
	}

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 */
	@RequestMapping("/cop/clb/listClubByCmmntyId.do")
	@Secured("ROLE_USER")
	public String listClubByCmmntyId(
			HttpServletRequest request, 
			ModelMap model) {

		ClubVO clubVO = new ClubVO();
		
		String cmmntyId = request.getParameter("param_cmmntyId");
		clubVO.setCmmntyId(cmmntyId);

		model.addAttribute("resultList", clubService.selectClubInfsByCmmntyId(clubVO));

		model.addAttribute("cmmntyId", cmmntyId);

		return "aramframework/com/cop/clb/ClubListByCmmntyId";
	}

	/**
	 * 동호회 가입 신청을 처리한다.
	 * 
	 * @param clubUserVO
	 */
	@RequestMapping("/cop/clb/insertClubUserBySelf.do")
	@Secured("ROLE_USER")
	public String insertClubUserBySelf(
			@ModelAttribute ClubUserVO clubUserVO, 
			ModelMap model) {

		String retVal = "";

		if ("".equals(clubUserVO.getOprtrAt())) {
			clubUserVO.setOprtrAt("N");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubUserVO.setUseAt("Y");
		clubUserVO.setFrstRegisterId(loginVO.getUniqId());
		clubUserVO.setEmplyrId(loginVO.getUniqId());

		// ---------------------------------------------
		// 승인요청 처리
		// ---------------------------------------------
		// retVal = clubService.insertClubUserInf(clubUserVO);
		retVal = clubService.checkClubUserInf(clubUserVO);

		if (!retVal.equals("EXIST")) {
			ClubVO clubVO = new ClubVO();
			clubVO.setClbId(clubUserVO.getClbId());

			ClubUserVO operator = clubService.selectClubOperatorInf(clubVO);

			ConfirmHistoryVO confirmHistoryVO = new ConfirmHistoryVO();

			confirmHistoryVO.setConfmRqesterId(loginVO.getUniqId()); // 요청자 ID
			confirmHistoryVO.setConfmerId(operator.getEmplyrId()); // 관리자
			confirmHistoryVO.setConfmTyCode("CF13"); // 동호회사용자등록
			confirmHistoryVO.setConfmSttusCode("AP01"); // 승인요청
			confirmHistoryVO.setOpertTyCode("WC01"); // 회원가입
			confirmHistoryVO.setOpertId(clubUserVO.getCmmntyId()); // 작업자 ID (동호회의 경우
														// 커뮤니티ID 지정)
			confirmHistoryVO.setTrgetJobTyCode("CLB"); // 대상작업구분
			confirmHistoryVO.setTrgetJobId(clubUserVO.getClbId()); // 대상작업 ID

			int cnt = confmService.countConfirmRequest(confirmHistoryVO);

			if (cnt == 0) {
				confmService.insertConfirmRequest(confirmHistoryVO);
			} else {
				retVal = "ING";
			}
		}
		// //-------------------------------------------

		model.addAttribute("returnMsg", retVal);

		return WebUtil.adjustViewName("/cop/clb/ClubMsg");
	}

	/**
	 * 동호회 탈퇴신청을 처리한다.
	 * 
	 * @param clubUserVO
	 */
	@RequestMapping("/cop/clb/deleteClubUserBySelf.do")
	@Secured("ROLE_USER")
	public String deleteClubUserBySelf(
			@ModelAttribute ClubUserVO clubUserVO, 
			ModelMap model) {

		String retVal = "DEL_REQ_SUCCESS";

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		// clubUser.setLastUpdusrId(loginVO.getUniqId());
		// clubUser.setEmplyrId(loginVO.getUniqId());
		// clubUser.setSecsnDe(EgovDateUtil.getToday());

		ConfirmHistoryVO confirmHistoryVO = new ConfirmHistoryVO();

		confirmHistoryVO.setConfmRqesterId(loginVO.getUniqId());
		confirmHistoryVO.setConfmerId(clubUserVO.getEmplyrId());
		confirmHistoryVO.setConfmTyCode("CF14"); // 006
		confirmHistoryVO.setConfmSttusCode("AP01"); // 007
		confirmHistoryVO.setOpertTyCode("WC03");
		confirmHistoryVO.setOpertId("");
		confirmHistoryVO.setTrgetJobTyCode("CLB");
		confirmHistoryVO.setTrgetJobId(clubUserVO.getClbId());

		confmService.insertConfirmRequest(confirmHistoryVO);

		// clubService.deleteClubUserInf(clubUser);

		model.addAttribute("returnMsg", retVal);

		return WebUtil.adjustViewName("/cop/clb/ClubMsg");
	}

	/**
	 * 동호회 초기화면에서 특정 동호회를 선택한다.
	 * 
	 * @param clbId
	 */
	@RequestMapping(value="/content/club/{clbId}", method=RequestMethod.GET)
	@Secured("ROLE_USER")
	public String directClubMainPage(
			@PathVariable String clbId,			
			ModelMap model) {

		clbId = WebUtil.getOriginalId(clbId, "CLB_");

		ClubVO clubVO = new ClubVO();
		model.addAttribute("clubVO", clubVO);
		clubVO.setClbId(clbId);
		
		model.addAttribute("contentUrl", "/cop/clb/ClubMainContents.do?clbId="+clubVO.getClbId());

		return clubMainPageHandler(clubVO, model);
	}

	/**
	 * 동호회 초기화면에서 특정 동호회를 선택한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param clbId
	 */
	@RequestMapping(value="/content/club/{clbId}/board/{bbsId}/article/{nttId}", method=RequestMethod.GET)
	@Secured("ROLE_USER")
	public String directClubMainPageArticle(
			@PathVariable String bbsId, 
			@PathVariable String nttId,			
			@PathVariable String clbId,			
			ModelMap model) {

		clbId = WebUtil.getOriginalId(clbId, "CLB_");
		bbsId = WebUtil.getOriginalId(bbsId, "BBSMSTR_");

		ClubVO clubVO = new ClubVO();
		model.addAttribute("clubVO", clubVO);
		clubVO.setClbId(clbId);
		
		model.addAttribute("contentUrl", "/content/board/"+bbsId+ "/article/"+nttId);

		return clubMainPageHandler(clubVO, model);
	}
	
	/**
	 * 동호회 초기화면에서 특정 동호회를 선택한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/ClubMainPage.do")
	@Secured("ROLE_USER")
	public String ClubyMainPage(
			@RequestParam(value="bbsId", required=false) String bbsId, 
			@RequestParam(value="nttId", required=false) String nttId, 
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {
		
		bbsId = WebUtil.getOriginalId(bbsId, "BBSMSTR_");

		if( nttId != null ) {
			model.addAttribute("contentUrl", "/content/board/"+bbsId+ "/article/"+nttId);
		} else if( bbsId != null ) {
			model.addAttribute("contentUrl", "/content/board/"+bbsId+ "/articles");
		} else {
			model.addAttribute("contentUrl", "/cop/clb/ClubMainContents.do?clbId="+clubVO.getClbId());
		}
		
		return clubMainPageHandler(clubVO, model);
	}

	private String clubMainPageHandler(ClubVO clubVO, ModelMap model) {

		clubService.selectClubInf(clubVO);

		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setTrgetId(clubVO.getClbId());
		
		model.addAttribute("bbsList",  bbsMasterService.selectAllBdMstrByTrget(boardMasterVO));

		// --------------------------------
		// 동호회 사용자 정보
		// --------------------------------
		ClubUserVO clubUserVO = new ClubUserVO();
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			clubUserVO.setClbId(clubVO.getClbId());
			clubUserVO.setEmplyrId(loginVO.getUniqId());
			
			clubService.selectClubUserInf(clubUserVO);

			model.addAttribute("isAuthenticated", "Y");
		} else {
			model.addAttribute("isAuthenticated", "N");
		}
		model.addAttribute("clubUserVO", clubUserVO);

		if( "Y".equals(clubUserVO.getOprtrAt())
			|| UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isOperator", "Y");
		} else {
			model.addAttribute("isOperator", "N");
		}
			
		String tmplatCours = clubService.selectClubTemplat(clubVO);
		if ("".equals(tmplatCours) || tmplatCours == null) {
			tmplatCours = "aramframework/com/cop/tpl/templet/ClbBaseTmpl";
		}
		return tmplatCours;
	}

	/**
	 * 동호회 초기화면에서에서 게시물 목록을 가져온다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/ClubMainContents.do")
	@Secured("ROLE_USER")
	public String ClubMainContents(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubVO.setEmplyrId(loginVO.getUniqId());

		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setTrgetId(clubVO.getClbId());
		List<BoardMasterVO> bbsList = bbsMasterService.selectAllBdMstrByTrget(boardMasterVO);

		// 방명록 제외 처리
		for (int i = 0; i < bbsList.size(); i++) {
			if (bbsList.get(i).getBbsTyCode().equals(BBSBoardService.BBS_TYPE_VISIT)) {
				bbsList.remove(i);
			}
		}
		model.addAttribute("bbsList", bbsList);

		// --------------------------------
		// 게시물 목록 정보 처리
		// --------------------------------
		BoardVO boardVO = null;
		List<Object> target = new ArrayList<Object>(); 
		for (int i = 0; i < bbsList.size() && i < 4; i++) {
			boardMasterVO = bbsList.get(i);
			boardVO = new BoardVO();

			boardVO.setBbsId(boardMasterVO.getBbsId());
			boardVO.setBoardMasterVO(boardMasterVO);

			boardVO.setPageSize(4);
			boardVO.setFirstIndex(0);
			boardVO.setRecordPerPage(4);

			target.add(boardService.selectBoardArticleList(boardVO));
		}

		model.addAttribute("articleList", target);

		return WebUtil.adjustViewName("/cop/clb/ClubMainContents");
	}

	/**
	 * 미리보기 처리.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/clb/previewClubMainPage.do")
	@Secured("ROLE_USER")
	public String preivewClubMainPage(
			@ModelAttribute SearchVO searchVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		ClubVO vo = new ClubVO();

		vo.setClbNm("미리보기 동호회");
		vo.setClbIntrcn("미리보기를 위한 동호회입니다.");
		vo.setUseAt("Y");
		vo.setFrstRegisterId(loginVO.getUniqId()); // 본인

		ClubUserVO clubUserVO = new ClubUserVO();

		clubUserVO.setEmplyrId(loginVO.getUniqId());
		clubUserVO.setEmplyrNm("관리자");

		model.addAttribute("clubVO", vo);
		model.addAttribute("clubUserVO", clubUserVO);

		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		List<BoardMasterVO> bbsList = new ArrayList<BoardMasterVO>();

		BoardMasterVO boardMasterVO = null;

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("공지게시판");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("자료실");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("방명록");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("갤러리");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("자유게시판");
		bbsList.add(boardMasterVO);

		model.addAttribute("bbsList", bbsList);
		// //------------------------------

		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {
			model.addAttribute("isAuthenticated", "Y");
		} else {
			model.addAttribute("isAuthenticated", "N");
		}

		model.addAttribute("preview", "true");

		model.addAttribute("contentUrl", "/cop/clb/previewClubMainContents.do");

		String tmplatCours = searchVO.getSearchKeyword();
		return tmplatCours;
	}

	/**
	 * 미리보기 콘텐츠 처리.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/clb/previewClubMainContents.do")
	public String previewClubMainContents(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {

		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		List<BoardMasterVO> bbsList = new ArrayList<BoardMasterVO>();

		BoardMasterVO boardMasterVO = null;

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("공지게시판");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("갤러리");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("자유게시판");
		bbsList.add(boardMasterVO);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("자료실");
		bbsList.add(boardMasterVO);

		model.addAttribute("bbsList", bbsList);

		// --------------------------------
		// 게시물 목록 정보 처리
		// --------------------------------
		List<Object> target = new ArrayList<Object>(); 
		for (int i = 0; i < bbsList.size() && i < 4; i++) {

			target.add(null);
		}

		model.addAttribute("articleList", target);

		model.addAttribute("preview", "true");

		return WebUtil.adjustViewName("/cop/clb/ClubMainContents");
	}

}
