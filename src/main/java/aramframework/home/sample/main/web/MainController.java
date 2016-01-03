package aramframework.home.sample.main.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cop.bbs.service.BoardMasterVO;
import aramframework.com.cop.bbs.service.BoardVO;
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

	@Resource(name = "bbsBoardService")
    private BBSBoardService boardService;
	
	@Resource(name = "forwardMap")
	private HashMap<String, String> forwardMap;

	protected static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	/**
	 * 템플릿 메인 페이지 조회
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
		model.addAttribute("notiList", boardService.selectBoardArticleList(boardVO));
		
		boardVO.setBbsId("BBSMSTR_BBBBBBBBBBBB");
		model.addAttribute("galList", boardService.selectBoardArticleList(boardVO));
		
		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------
		
		return "home/sample/MainView";
	}

    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/home/sample/**")
	public String moveToPageDirect(
			HttpServletRequest request, 
			HttpSession session, 
			@RequestParam(value="menuNo", required=false) String menuNo) {
	
		String requestUri = request.getRequestURI();
		String link = requestUri.substring("/home/sample/".length());

		String cmd = "";	
		int index = link.indexOf(".do");
		if( index != -1 ) {
			cmd = link.substring(0, index);
		}

//		LOG.debug("requestUri = "+ requestUri + ", link = " + link + ", menuNo = " + menuNo);

		// 선택된 메뉴정보를 세션으로 등록한다.
		if (menuNo!=null && !menuNo.equals("")){
			session.setAttribute("menuNo",menuNo);
		}
		
		String forward = forwardMap.get(cmd);
		if( link.startsWith("content/board") ) {
			forward = "/" + link;
		}
		if( forward != null && !"".equals(forward) ) {
			request.setAttribute("jspPrefix",  "home/sample");
			if( index != -1 ) {
				forward = forward + link.substring(index);
			}
			LOG.debug("forward = "+ forward);
			return "forward:" + forward;
		}
		return "home/sample/content/" + cmd;	// content 
	}

}