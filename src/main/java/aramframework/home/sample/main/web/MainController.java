package aramframework.home.sample.main.web;

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
public class MainController {

	@Autowired
    private BBSBoardService bbsBoardService; 
	
	@Resource(name = "forwardMap")
	private HashMap<String, String> forwardMap;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 메인 페이지 조회
	 */
	@RequestMapping(value = "/home/sample/main.do")
	public String getMgtMainPage(ModelMap model) {
		
		// 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
		BoardVO boardVO = new BoardVO();
		boardVO.setRecordPerPage(5);
		boardVO.setFirstIndex(0);

		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBbsAttrbCode("BBSA02");
		
		boardVO.setBoardMasterVO(boardMasterVO);
		
		boardVO.setBbsId("BBSMSTR_AAAAAAAAAAAA");
		model.addAttribute("notiList", bbsBoardService.selectBoardArticleList(boardVO));
		
		boardVO.setBbsId("BBSMSTR_BBBBBBBBBBBB");
		model.addAttribute("galList", bbsBoardService.selectBoardArticleList(boardVO));
		
		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------
		
		return "home/sample/MainView";
	}

    /**
	 * content page
	 */
	@RequestMapping(value="/home/sample/page/{pagelink}.do")
	public String moveToPageDirect(
			HttpServletRequest request, 
			@PathVariable String pagelink,
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		return "home/sample/page/" + pagelink;	// content 
	}

    /**
	 * content page
	 */
	@RequestMapping(value="/home/sample/include/{pagelink}.do")
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
		
		return "home/sample/include/" + pagelink;	// content 
	}

    /**
	 * home 게시판
	 */
	@RequestMapping(value="/home/sample/board/**")
	public String moveToBoardDirect(
			HttpServletRequest request, 
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		String requestUri = request.getRequestURI();
		String pagelink = requestUri.substring("/home/sample/board/".length());
		String forward = "/board/" + pagelink;

		LOG.debug("forward = "+ forward);
		request.setAttribute("jspPrefix",  "home/sample/");
		return "forward:" + forward;
	}

    /**
	 * component forward
	 */
	@RequestMapping(value="/home/sample/{command}.do")
	public String moveToCommandDirect(
			HttpServletRequest request, 
			@PathVariable String command,
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			request.setAttribute("menuNo",menuNo);
		}
		
		request.setAttribute("jspPrefix",  "home/sample/");
		String forward = forwardMap.get(command);
		if( forward != null && !"".equals(forward) ) {
			LOG.debug("forward = "+ forward);
			return "forward:" + forward; 
		}
		return "forward:" + "/" + command + ".do";	// content 
	}

}