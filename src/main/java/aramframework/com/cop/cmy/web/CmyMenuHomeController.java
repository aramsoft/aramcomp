package aramframework.com.cop.cmy.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.impl.BasicTilesContainer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;
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

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.cop.cmy.service.CmyMenuManageService;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 커뮤니티 정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CmyMenuHomeController  {
 
	@Autowired 
	private CommunityManageService cmmntyService;

	@Autowired 
	private CmyMenuManageService cmyMeunService;

	@Autowired 
	private BBSMasterService bbsMasterService;

	@Autowired 
	private BBSBoardService boardService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 커뮤니티 홈페이지를 조회한다.
	 * 
	 * @param appId
	 */
	@RequestMapping(value="/apps/{appId}", method=RequestMethod.GET)
	public String directCmmntyHomePage(
			@PathVariable String appId) {

		String cmmntyId = cmmntyService.selectCommntyHomeUrl("/apps/"+appId);
		if( cmmntyId == null ) {
			throw new RuntimeException("cmmntyId is not found !!!");
		}

		return cmmntyMainPageHandler(cmmntyId, "", "");
	}

	/**
	 * 커뮤니티 메뉴페이지를 조회한다.
	 * 
	 * @param appId
	 * @param menuAlias
	 */
	@RequestMapping(value="/apps/{appId}/{menuAlias}", method=RequestMethod.GET)
	public String directCmmntyHomeMenuPage(
			@PathVariable String appId,			
			@PathVariable String menuAlias,
			HttpServletRequest request) { 

		LOG.debug("request URI = " + request.getRequestURI());
		
		String cmmntyId = cmmntyService.selectCommntyHomeUrl("/apps/"+appId);
		if( cmmntyId == null ) {
			throw new RuntimeException("cmmntyId is not found !!!");
		}
		
		String menuId = cmyMeunService.selectMenuNoByMenuAlias(cmmntyId, menuAlias);
		if( menuId == null ) {
			throw new RuntimeException("menuId is not found !!!");
		}
		
		return cmmntyMainPageHandler(cmmntyId, menuId, "");
	}

	/**
	 * 커뮤니티 메인페이지를 조회한다.
	 * 
	 * @param cmmntyId
	 */
	@RequestMapping(value="/content/apps/{cmmntyId}", method=RequestMethod.GET)
	public String directCmmntyMainPage(
			@PathVariable String cmmntyId) {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		return cmmntyMainPageHandler(cmmntyId, "", "");
	}

	/**
	 * 커뮤니티 게시판  페이지를 조회한다.
	 * 
	 * @param bbsId
	 * @param cmmntyId
	 */
	@RequestMapping(value="/content/apps/{cmmntyId}/board/{bbsId}/articles")
	public String directCmmntyBoard(
			@PathVariable String bbsId, 
			@PathVariable String cmmntyId) {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		String contentUrl = "/content/board/"+bbsId+"/articles";

		return cmmntyMainPageHandler(cmmntyId, "", contentUrl);
	}
	
	/**
	 * 커뮤니티 게시판  페이지를 조회한다.
	 * 
	 * @param cmmntyId
	 * @param bbsId
	 * @param nttId
	 */
	@RequestMapping(value="/content/apps/{cmmntyId}/board/{bbsId}/article/{nttId}")
	public String directCmmntyBbsPage(
			@PathVariable String cmmntyId,			
			@PathVariable String bbsId, 
			@PathVariable String nttId) {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		String contentUrl = "/content/board/"+bbsId+"/article/"+nttId;

		return cmmntyMainPageHandler(cmmntyId, "", contentUrl);
	}
	
	/**
	 * 커뮤니티 메뉴페이지를 조회한다.
	 * 
	 * @param cmmntyId
	 * @param menuId
	 */
	@RequestMapping(value="/content/apps/{cmmntyId}/menu/{menuId}")
	public String directCmmntyMainPage(
			HttpServletRequest request, 
			@PathVariable String cmmntyId,			
			@PathVariable String menuId) {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		return cmmntyMainPageHandler(cmmntyId, menuId, "");
	}

	private String cmmntyMainPageHandler(
			String cmmntyId, 
			String menuId, 
			String contentUrl) {

		if( cmmntyId == null || cmmntyId.equals("") ) {
			throw new RuntimeException("cmmntyId not found");
		}
		
        CommunityVO communityVO = cmmntyService.getCommunityInfo(cmmntyId, menuId);

        // --------------------------------
		// 컨텐트 URL 정보
		// --------------------------------
		if( "".equals(menuId) && communityVO.getTopMenuList().size() != 0 ) {
			menuId = communityVO.getTopMenuList().get(0).get("menuNo").toString();
		}
		
		if( "".equals(contentUrl) ) {
			contentUrl = getMenuInfo(communityVO, menuId, "chkURL");
			if( "".equals(contentUrl) ) {
				contentUrl =  "/cop/cmy/CmmntyMainContents.do";
			}
		}
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		requestAttributes.setAttribute("curTrgetId", communityVO.getCmmntyId(), RequestAttributes.SCOPE_REQUEST);
		requestAttributes.setAttribute("curMenuNo", menuId, RequestAttributes.SCOPE_REQUEST);

	   	return "forward:"+contentUrl;
	}

    /**
	 * 커뮤니티 타일 페이지로 이동한다.
	 * 
	 */
	@RequestMapping("/cop/cmy/CmmntyTilesPage.do")
	public String CmmntyTilesPage(
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) 
	throws Exception {

		String jspPage = (String) request.getAttribute("jspPage");
		String cmmntyId = (String) request.getAttribute("curTrgetId");
		String menuId = (String) request.getAttribute("curMenuNo");
		
		if (!cmmntyId.startsWith("CMMNTY_") || "".equals(menuId)) {
			return jspPage;
		}
		
        CommunityVO communityVO = cmmntyService.getCommunityInfo(cmmntyId, menuId);
        model.addAttribute("targetVO", communityVO);
	
		// --------------------------------
		// 커뮤니티 사용자 정보
		// --------------------------------
		model.addAttribute("targetUserVO", cmmntyService.getCommunityUserInfo(cmmntyId));
		
        // --------------------------------
		// 메뉴 정보
		// --------------------------------
		String menuAlias = getMenuInfo(communityVO, menuId, "menuAlias");
		if( "".equals(menuAlias) ) {
			menuAlias = communityVO.getTopMenuList().get(0).get("menuAlias").toString();
		}
		model.addAttribute("menuAlias", menuAlias);
	    
		// --------------------------------
		// 커뮤니티 템플릿 정보
		// --------------------------------
		String tmplatCours = cmmntyService.selectCmmntyTemplat(communityVO);
    	if ("".equals(tmplatCours) || tmplatCours == null) {
    		tmplatCours = "/WEB-INF/layouts/apps/appsDefault";
    	}

		ServletContext servletContext = request.getSession().getServletContext();
		ApplicationContext tilesAppContext = ServletUtil.getApplicationContext(servletContext);
		Request tilesRequest = new ServletRequest(tilesAppContext, request, response);
		BasicTilesContainer container = (BasicTilesContainer) TilesAccess.getContainer(tilesAppContext);
		AttributeContext attributeContext = container.getAttributeContext(tilesRequest);

/*    	
		TilesContainer container = ServletUtil.getCurrentContainer(request,	request.getSession().getServletContext());
		AttributeContext attributeContext = container.startContext(request, response);
*/		
		if (tmplatCours.indexOf("/WEB-INF/layouts") != -1) {
			attributeContext.setTemplateAttribute(new Attribute(tmplatCours+".jsp"));
		} else {
			attributeContext.setTemplateAttribute(new Attribute("/WEB-INF/jsp/"+tmplatCours+".jsp"));
		}
		
	    return jspPage;
	}

	private String getMenuInfo(CommunityVO communityVO, String menuId, String target) {
	
		if( menuId == null || menuId.equals("") ) return "";
		
		int menuNo = Integer.parseInt(menuId);

		// check topMenuList
		List<EgovMap> menuList = communityVO.getTopMenuList();
		String content = getContent(menuNo, menuList, target);
		if( !"".equals(content) ) {
			return content;
		} 
		
		// check mgrMenuList
		menuList = communityVO.getMgrMenuList();
		content = getContent(menuNo, menuList, target);
		if( !"".equals(content) ) {
			return content;
		} 

		// check subMenuList
		menuList = communityVO.getSubMenuList();
		content = getContent(menuNo, menuList, target);
		if( !"".equals(content) ) {
			return content;
		} 
		
		return "";
	}
	
	private String getContent(int menuNo, List<EgovMap> menuList, String target) {

		int i = 0;
		for (; i < menuList.size(); i++) {
			if( menuNo == ((BigDecimal)menuList.get(i).get("menuNo")).intValue()) {
				break;
			}
		}
		if( i < menuList.size()) {
			return (String)menuList.get(i).get(target);
		} 
		return "";
	}
	
	/**
	 * 커뮤니티 이미지 를 가져온다.
	 * 
	 * @param cmmntyId
	 */
	@RequestMapping(value="/content/apps/{cmmntyId}/logo", method=RequestMethod.GET)
	public void directCmmntyLogo(
			@PathVariable String cmmntyId,			
			HttpServletResponse response) 
	throws Exception {

		cmmntyId = WebUtil.getOriginalId(cmmntyId, "CMMNTY_");

		CommunityVO communityVO = cmmntyService.getCommunityInfo(cmmntyId);
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
			@RequestParam(value="trgetId", required=false) String trgetId,
			ModelMap model) {

		if( trgetId == null || "".equals(trgetId)) 
			trgetId = WebUtil.getCurTrgetId();
		
		// --------------------------------
		// 게시판 목록 정보 처리
		// --------------------------------
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setTrgetId(trgetId);

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
		ArrayList<Object> target = new ArrayList<Object>(); // Object => List<BoardVO>
		for (int i = 0; i < bbsList.size() && i < 5; i++) {
			boardMasterVO = bbsList.get(i);
			boardVO = new BoardVO();

			boardVO.setBbsId(boardMasterVO.getBbsId());
			boardVO.setBoardMasterVO(boardMasterVO);

			boardVO.setPageSize(5);
			boardVO.setFirstIndex(0);
			boardVO.setRecordPerPage(5);

			target.add(boardService.selectBoardArticleList(boardVO));
		}

		model.addAttribute("articleList", target);
		
		return WebUtil.adjustViewName("/cop/cmy/CmmntyMainContents");
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
		communityVO.setFrstRegisterId(loginVO.getUniqId()); // 본인

		CommunityUserVO communityUserVO = new CommunityUserVO();
		communityUserVO.setEmplyrId(loginVO.getUniqId());
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

		return WebUtil.adjustViewName("/cop/cmy/CmmntyMainContents");
	}

}
