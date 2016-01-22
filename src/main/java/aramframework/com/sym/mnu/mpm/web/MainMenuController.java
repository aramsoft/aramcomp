package aramframework.com.sym.mnu.mpm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.sym.mnu.mpm.service.MainMenuService;
import aramframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 메인메뉴 해당링크 처리를 하는 비즈니스 구현 클래스
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
public class MainMenuController {

	@Autowired
	private MainMenuService mainMenuService;
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/* ### 메인작업 ### */
	/**
	 * Head메뉴를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuHead.do")
	@Secured("ROLE_USER")
	public String selectMainMenuHead(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		menuManageVO.setTmpUniqId(loginVO.getUniqId());

		model.addAttribute("list_headmenu", mainMenuService.selectMainMenuHead(menuManageVO));
		if (!loginVO.getId().equals("")) {
			// 메인 페이지 이동
			// G일반 / E기업 / U업무
			if (loginVO.getUserSe().equals("USR")) {
				return "aramframework/com/main_head"; // "EgovMainViewG"; 일반사용자
			} else {
				return "aramframework/com/main_headG";
			}
		} else {
			// 오류 페이지 이동
			return "aramframework/com/cmm/error/egovError";
		}
	}

	/**
	 * Bottom메뉴를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuBottom.do")
	public String selectMainMenuBottom(
			@ModelAttribute MenuManageVO menuManageVO) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		if (!loginVO.getId().equals("")) {
			// "EgovMainViewG"; 일반사용자
			return "aramframework/com/main_bottom"; 
		} else {
			// 오류 페이지 이동
			return "aramframework/com/cmm/error/egovError";
		}
	}

	/**
	 * 좌측메뉴를 조회한다.
	 * 
	 * @param menuManageVO
	 * @param vStartP
	 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuLeft.do")
	@Secured("ROLE_USER")
	public String selectMainMenuLeft(
			@ModelAttribute MenuManageVO menuManageVO, 
			@RequestParam String vStartP, 
			ModelMap model) {

		int iMenuNo = Integer.parseInt(vStartP);
		menuManageVO.setMenuNo(iMenuNo);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		menuManageVO.setTmpUniqId(loginVO.getUniqId());

		model.addAttribute("list_menulist", mainMenuService.selectMainMenuLeft(menuManageVO));
		return "aramframework/com/main_left";
	}

	/**
	 * 우측화면을 조회한다.
	 * 
	 * @param vStartP
	 */
	/* Right Menu 조회 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuRight.do")
	@Secured("ROLE_USER")
	public String selectMainMenuRight(
			@RequestParam String vStartP) {
		
		int iMenuNo = Integer.parseInt(vStartP);
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		String forwardURL = mainMenuService.selectLastMenuURL(iMenuNo, loginVO.getUniqId());
		return "forward:" + forwardURL;
	}

	/**
	 * HOME 메인화면 조회한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuHome.do")
	@Secured("ROLE_USER")
	public String selectMainMenuHome(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		menuManageVO.setTmpUniqId(loginVO.getUniqId());

		model.addAttribute("list_headmenu", mainMenuService.selectMainMenuHead(menuManageVO));

		log.debug("## selectMainMenuHome ## getSUserSe 1: " + loginVO.getUserSe());
		log.debug("## selectMainMenuHome ## getSUserId 2: " + loginVO.getId());
		log.debug("## selectMainMenuHome ## getUniqId  2: " + loginVO.getUniqId());

		if (!loginVO.getId().equals("")) {
			// 메인 페이지 이동
			// G일반 / E기업 / U업무
			if (loginVO.getUserSe().equals("GNR")) {// 2011.09.07
				return "aramframework/com/MainHomeG"; // "EgovMainViewG";
															// 일반사용자
			} else if (loginVO.getUserSe().equals("USR")) {// 2011.09.07
				// return "aramframework/com/IndvdlpgeDetail";
				return "aramframework/com/MainHome";
			} else {
				// return "aramframework/com/MainView";//1차 사업 메인화면
				return "aramframework/com/MainHome2";// 2차 사업 메인화면
			}
		} else {
			// 오류 페이지 이동
			return "aramframework/com/cmm/error/egovError";
		}
	}
	
	/* Main Index 조회 */
	/**
	 * Main메뉴의 Index를 조회한다.
	 * 
	 * @param menuManageVO
	 * @param menuNo
	 * @param chkURL
	 */
	@RequestMapping(value = "/sym/mnu/mpm/MainMenuIndex.do")
	public String selectMainMenuIndex(
			@ModelAttribute MenuManageVO menuManageVO, 
			@RequestParam String menuNo,
			@RequestParam String chkURL) {

		int iMenuNo = Integer.parseInt(menuNo);
		menuManageVO.setMenuNo(iMenuNo);
		// menuManageVO.setTempValue(chkURL);

		return "aramframework/com/MainIndex";
	}

}