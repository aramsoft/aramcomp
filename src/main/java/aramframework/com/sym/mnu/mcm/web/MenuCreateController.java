package aramframework.com.sym.mnu.mcm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.mnu.mcm.domain.MenuCreateVO;
import aramframework.com.sym.mnu.mcm.domain.MenuSiteMapVO;
import aramframework.com.sym.mnu.mcm.service.MenuCreateService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴목록 관리및 메뉴생성, 사이트맵 생성을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MenuCreateController {

	@Autowired
	private MenuCreateService menuCreateService;	

	/**
	 * *메뉴생성목록을 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	@IncludedInfo(name = "메뉴생성관리", order = 6150, gid = 60)
	@RequestMapping(value = "/sym/mnu/mcm/listMenuCreate.do")
	@Secured("ROLE_ADMIN")
	public String listMenuCreate(
			@ModelAttribute MenuCreateVO menuCreateVO, 
			ModelMap model) {

		// 내역 조회
		PaginationInfo paginationInfo = new PaginationInfo();
		menuCreateVO.fillPageInfo(paginationInfo);

		if (menuCreateVO.getSearchKeyword() != null && !menuCreateVO.getSearchKeyword().equals("")) {
			int IDcnt = menuCreateService.selectUsrByPk(menuCreateVO);
			if (IDcnt == 0) {
				model.addAttribute("message", MessageHelper.getMessage("info.nodata.msg"));
			} else {
				/* AuthorCode 검색 */
				menuCreateService.selectAuthorByUsr(menuCreateVO);
				menuCreateVO.setSearchKeyword(menuCreateVO.getAuthorCode());
			}
		}
		model.addAttribute("resultList", menuCreateService.selectMenuCreateList(menuCreateVO));
		int totCnt = menuCreateService.selectMenuCreateListCnt(menuCreateVO);

		menuCreateVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/mnu/mcm/MenuCreateList");
	}

	/* 메뉴생성 세부조회 */
	/**
	 * 메뉴생성 세부화면을 조회한다.
	 * 
	 * @param menuCreateVO
	 */
	@RequestMapping(value = "/sym/mnu/mcm/detailMenuCreate.do")
	@Secured("ROLE_ADMIN")
	public String detailMenuCreate(
			@ModelAttribute MenuCreateVO menuCreateVO, 
			ModelMap model) {
		
		model.addAttribute("list_menulist", menuCreateService.selectMenuCreateDetailList(menuCreateVO));

		return WebUtil.adjustViewName("/sym/mnu/mcm/MenuCreateDetail");
	}

	/**
	 * 메뉴생성처리 및 메뉴생성내역을 등록한다.
	 * 
	 * @param menuCreateVO
	 * @param checkedAuthorForInsert
	 * @param checkedMenuNoForInsert
	 */
	@RequestMapping("/sym/mnu/mcm/insertMenuCreate.do")
	@Secured("ROLE_ADMIN")
	public String insertMenuCreate(
			@ModelAttribute MenuCreateVO menuCreateVO, 
			@RequestParam String checkedAuthorForInsert,
			@RequestParam String checkedMenuNoForInsert, 
			ModelMap model) {

		String[] insertMenuNo = checkedMenuNoForInsert.split(",");
		if (insertMenuNo == null || (insertMenuNo.length == 0)) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
		} else {
			menuCreateService.insertMenuCreateList(checkedAuthorForInsert, checkedMenuNoForInsert);
			model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		}

        return WebUtil.redirectJsp(model, "/sym/mnu/mcm/detailMenuCreate.do?authorCode="+menuCreateVO.getAuthorCode());
	}

	/* 메뉴사이트맵 생성조회 */
	/**
	 * 메뉴사이트맵을 생성할 내용을 조회한다.
	 * 
	 * @param menuSiteMapVO
	 */
	@RequestMapping(value = "/sym/mnu/mcm/detailMenuCreateSiteMap.do")
	@Secured("ROLE_ADMIN")
	public String detailMenuCreatSiteMap(
			@ModelAttribute MenuSiteMapVO menuSiteMapVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		menuSiteMapVO.setCreatPersonId(loginVO.getId());

		model.addAttribute("list_menulist", menuCreateService.selectMenuCreateSiteMapList(menuSiteMapVO));

		return WebUtil.adjustViewName("/sym/mnu/mcm/MenuCreateSiteMap");
	}

	/**
	 * 메뉴사이트맵 생성처리 및 사이트맵을 등록한다.
	 * 
	 * @param menuSiteMapVO
	 * @param valueHtml
	 */
	@RequestMapping(value = "/sym/mnu/mcm/insertMenuCreateSiteMap.do")
	@Secured("ROLE_ADMIN")
	public String insertMenuCreateSiteMap(
			@ModelAttribute MenuSiteMapVO menuSiteMapVO, 
			@RequestParam String valueHtml,
			ModelMap model) {
		
		boolean chkCreat = false;

        String currPath = this.getClass().getResource("").getPath();
        String absolutePath = currPath.substring(0, currPath.lastIndexOf("WEB-INF"));
		menuSiteMapVO.setTmpRootPath(absolutePath);
		menuSiteMapVO.setBndeFilePath("/html/sitemap/");

		chkCreat = menuCreateService.createSiteMap(menuSiteMapVO, valueHtml);
		if (!chkCreat) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
		} else {
			model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		}
	
        return WebUtil.redirectJsp(model, "/sym/mnu/mcm/detailMenuCreate.do?authorCode="+menuSiteMapVO.getAuthorCode());
	}

}
