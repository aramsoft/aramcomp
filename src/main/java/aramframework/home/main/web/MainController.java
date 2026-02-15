package aramframework.home.main.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSBoardService;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * 
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
public class MainController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private BBSBoardService bbsBoardService; 
	
	@Resource(name = "forwardMap")
	private HashMap<String, String> forwardMap;

	/**
	 * 메인 페이지 조회
	 */
	@RequestMapping(value = "/home/main.do")
	public String getMgtMainPage(ModelMap model) {
		
		// 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
		BoardVO boardVO = new BoardVO();
		boardVO.setRecordPerPage(5);
		boardVO.setFirstIndex(0);

		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsAttrbCode("BBSA02");
		
		boardVO.setBoardMasterVO(boardMasterVO);
		
		boardVO.setBbsId("BBSMSTR_000000000007");
		model.addAttribute("notiList", bbsBoardService.selectBoardArticleList(boardVO));
		
		boardVO.setBbsId("BBSMSTR_000000000008");
		model.addAttribute("galList", bbsBoardService.selectBoardArticleList(boardVO));
		
		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------
		
		return "home/MainView";
	}

    /**
	 * content page
	 */
	@RequestMapping(value="/home/page/{pagelink}.do")
	public String moveToPageDirect(
			HttpServletRequest request, 
			@PathVariable String pagelink,
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		return "home/page/" + pagelink;	// content 
	}

    /**
	 * content page
	 */
	@RequestMapping(value="/home/include/{pagelink}.do")
	public String moveToIncludeDirect(
			HttpServletRequest request, 
			@PathVariable String pagelink,
			@RequestParam(value="menuNo", required=false) String menuNo, 
			ModelMap model) {
	
		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("role", "ROLE_ADMIN");
		}

		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		return "home/include/" + pagelink;	// content 
	}

    /**
	 * home 게시판
	 */
	@RequestMapping(value="/home/board/**")
	public String moveToBoardDirect(
			HttpServletRequest request, 
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		String requestUri = request.getRequestURI();
		String realContent = requestUri.substring("/home".length());

		request.setAttribute("jspPrefix",  "home");
		return "forward:" + realContent;
	}

    /**
	 * component forward
	 */
	@RequestMapping(value="/home/{command}.do")
	public String moveToCommandDirect(
			HttpServletRequest request, 
			@PathVariable String command,
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		request.setAttribute("jspPrefix",  "home");
		String realContent = forwardMap.get(command);
		if( realContent != null && !"".equals(realContent) ) {
			return "forward:" + realContent; 
		}
		return "forward:" + "/" + command + ".do";	// realContent 
	}

}