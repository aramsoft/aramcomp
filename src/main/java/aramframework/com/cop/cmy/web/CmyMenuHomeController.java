package aramframework.com.cop.cmy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.cop.cmy.domain.MenuVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.uat.uia.domain.LoginVO;

/**
 * 커뮤니티 정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CmyMenuHomeController  {
 
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private CommunityManageService cmmntyService;

	@Autowired 
	private BBSMasterService bbsMasterService;

	@Autowired 
	private BBSBoardService boardService;

	/**
	 * 커뮤니티 홈페이지를 조회한다.
	 * 
	 * @param appId
	 */
	@RequestMapping(value="/apps/{alias}", method=RequestMethod.GET)
	public String directCmmntyHomePage(
			@PathVariable String alias,
			HttpServletRequest request) {

		String directUrl = request.getParameter("directUrl");
		if( directUrl == null || "".equals(directUrl)) {
			directUrl =  "/cop/cmy/CmmntyMainContents.do";
		}
		return cmmntyMainPageHandler(alias, "", directUrl);
	}

	/**
	 * 커뮤니티 메뉴페이지를 조회한다.
	 * 
	 * @param appId
	 * @param menuAlias
	 */
	@RequestMapping(value="/apps/{alias}/{menuNm}", method=RequestMethod.GET)
	public String directCmmntyHomeMenuPage(
			@PathVariable String alias,			
			@PathVariable String menuNm,
			HttpServletRequest request) {

		String directUrl = request.getParameter("directUrl");
		if( directUrl == null ) directUrl =  "";
		
		return cmmntyMainPageHandler(alias, menuNm, directUrl);
	}

	/**
	 * 커뮤니티 게시판  페이지를 조회한다.
	 * 
	 * @param bbsId
	 * @param cmmntyId
	 */
	@RequestMapping(value="/apps/{alias}/board/{bbsId}/list")
	public String directCmmntyBoard(
			@PathVariable String alias,
			@PathVariable String bbsId) {

		String directUrl = "/board/"+bbsId+"/list";
		return cmmntyMainPageHandler(alias, "", directUrl);
	}
	
	/**
	 * 커뮤니티 게시판  페이지를 조회한다.
	 * 
	 * @param cmmntyId
	 * @param bbsId
	 * @param nttId
	 */
	@RequestMapping(value="/apps/{alias}/board/{bbsId}/article/{nttId}")
	public String directCmmntyBbsPage(
			@PathVariable String alias,			
			@PathVariable String bbsId, 
			@PathVariable String nttId) {

		String directUrl = "/board/"+bbsId+"/article/"+nttId;
		return cmmntyMainPageHandler(alias, "", directUrl);
	}
	
	private String cmmntyMainPageHandler(
			String alias, 
			String menuNm, 
			String directUrl) {

        CommunityVO communityVO = cmmntyService.getCommunityLayoutInfo(alias, menuNm);
		if( communityVO == null ) {
			throw new RuntimeException("community is not found !!!");
		}

        // --------------------------------
		// 컨텐트 URL 정보
		// --------------------------------
		if( "".equals(menuNm) && communityVO.getTopMenuList().size() != 0 ) { // 초기 화면을 위해 필요
			menuNm = communityVO.getTopMenuList().get(0).getMenuNm();
		}
//		logger.debug("menuNm = " + menuNm);

		if( "".equals(directUrl) ) {
			MenuVO menuVO = cmmntyService.getMenuInfo(communityVO, menuNm);
			directUrl = menuVO.getContentUrl();
		}
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		requestAttributes.setAttribute("curTarget", alias, RequestAttributes.SCOPE_SESSION);
		requestAttributes.setAttribute("curMenuNm", menuNm, RequestAttributes.SCOPE_SESSION);

	   	return "forward:"+directUrl;
	}

	/**
	 * 커뮤니티 이미지 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@RequestMapping(value="/apps/{alias}/logo", method=RequestMethod.GET)
	public void directCmmntyLogo(
			@PathVariable String alias,			
			HttpServletResponse response) 
	throws Exception {
		
		CommunityVO communityVO = cmmntyService.getCommunityOnlyInfo(alias);
		byte[] img = communityVO.getCmmntyLogoImage();

		String type = "image/jpeg";

		response.setHeader("Content-Type", type);
		response.setHeader("Content-Length", "" + img.length);
		response.setHeader("Cache-Control", "max-age=86400");
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

    /**
	 * 커뮤니티 메인페이지의 기본 내용(게시판 4개 표시) 조회한다.
	 * 
	 * @param trgetId
	 */
	@RequestMapping("/cop/cmy/CmmntyMainContents.do")
	public String CmmntyMainContents(
			@RequestParam(value="target", required=false) String target,
			ModelMap model) {

		if( target == null || "".equals(target)) 
			target = WebUtil.getCurTarget();
		
        String cmmntyId = cmmntyService.getCommunityOnlyInfo(target).getCmmntyId();
		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setTrgetId(cmmntyId);

		List<BoardMasterVO> bbsList = bbsMasterService.selectAllBdMstrByTrget(boardMasterVO);
		BoardMasterVO bMasterVO[] = new BoardMasterVO[6];
		
		// 방명록 제외 처리
		for (int i = 0; i < bbsList.size(); i++) {
			if (bbsList.get(i).getBbsTyCode().equals(BBSBoardService.BBS_TYPE_VISIT)) {
				bbsList.remove(i);
			}
		}
		
		for (int i = 0; i < bbsList.size(); i++) {
			bMasterVO[i] = bbsList.get(i);
		}
		
		bbsList.clear();
		bbsList.add(bMasterVO[0]); // 공지게시판
		bbsList.add(bMasterVO[3]); // 자료실
		bbsList.add(bMasterVO[1]); // 갤러리
		bbsList.add(bMasterVO[2]); // 자유게시판
		
		model.addAttribute("bbsList", bbsList);

		// --------------------------------
		// 게시물 목록 정보 처리
		// --------------------------------
		BoardVO boardVO = null;
		ArrayList<Object> articleList = new ArrayList<Object>(); // Object => List<BoardVO>
		for (int i = 0; i < bbsList.size() && i < 5; i++) {
			boardMasterVO = bbsList.get(i);
			boardVO = new BoardVO();

			boardVO.setBbsId(boardMasterVO.getBbsId());
			boardVO.setBoardMasterVO(boardMasterVO);

			boardVO.setPageSize(5);
			boardVO.setFirstIndex(0);
			boardVO.setRecordPerPage(5);

			articleList.add(boardService.selectBoardArticleList(boardVO));
		}

		model.addAttribute("articleList", articleList);
		
		return "com/cop/cmy/CmmntyMainContents";
	}

	/**
	 * 미리보기 커뮤니티 메인페이지를 조회한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/previewCmmntyMainPage.do")
	public String previewCmmntyMainPage(
			CommunityVO communityVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		communityVO.setCmmntyNm("미리보기 커뮤니티");
		communityVO.setCmmntyIntrcn("미리보기를 위한 커뮤니티입니다.");
		communityVO.setUseAt("Y");
		communityVO.setFrstRegisterId(loginVO.getUserId()); // 본인

		CommunityUserVO communityUserVO = new CommunityUserVO();
		communityUserVO.setEmplyrId(loginVO.getUserId());
		communityUserVO.setEmplyrNm("관리자");

		model.addAttribute(communityUserVO);

		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		List<BoardMasterVO> bbsList = new ArrayList<BoardMasterVO>();

		BoardMasterVO boardMasterVO = null;

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsNm("방명록");
		bbsList.add(boardMasterVO);

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
		// //------------------------------

		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {
			model.addAttribute("isAuthenticated", "Y");
		} else {
			model.addAttribute("isAuthenticated", "N");
		}

		model.addAttribute("preview", "true");
		model.addAttribute("contentUrl", "/cop/cmy/previewCmmntyMainContents.do");
		
		model.addAttribute(communityVO);
		
		String tmplatCours = communityVO.getSearchKeyword();
		return tmplatCours;
	}

	/**
	 * 미리보기 커뮤니티 메인페이지의 기본 내용(게시판 4개 표시) 조회한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/previewCmmntyMainContents.do")
	public String previewCmmntyMainContents(
			CommunityVO communityVO, 
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
		ArrayList<Object> target = new ArrayList<Object>(); // Object => List<BoardVO>
		for (int i = 0; i < bbsList.size() && i < 4; i++) {
			target.add(null);
		}

		model.addAttribute("articleList", target);
		model.addAttribute("preview", "true");

		model.addAttribute(communityVO);

		return "com/cop/cmy/CmmntyMainContents";
	}

}
