package aramframework.com.sym.mnu.mpm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.domain.SearchVO;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.com.annotation.IncludedInfo;
import aramframework.com.sym.mnu.mpm.domain.MenuManageVO;
import aramframework.com.sym.mnu.mpm.service.MenuManageService;
import aramframework.com.sym.prm.service.ProgrmManageService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴목록 관리및 메뉴생성, 사이트맵 생성을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MenuManageController {

	/** EgovMenuManageService */
	@Autowired
	private MenuManageService menuManageService;

	/** EgovMenuManageService */
	@Autowired
	private ProgrmManageService progrmManageService;

	/** FileMngService */
//	@Autowired
	// private FileMngService fileMngService;

	/** FileMngUtil */
//	@Autowired
	// private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;
	/**
	 * 메뉴목록 리스트조회한다.
	 * 
	 * @param menuManageVO
	 */
	@IncludedInfo(name = "메뉴관리", order = 6141, gid = 60)
	@RequestMapping(value = "/sym/mnu/mpm/listMenu.do")
	@Secured("ROLE_ADMIN")
	public String listMenu(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		// 내역 조회
		PaginationInfo paginationInfo = new PaginationInfo();
		menuManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", menuManageService.selectMenuManageList(menuManageVO));
		int totCnt = menuManageService.selectMenuManageListCnt(menuManageVO);

		menuManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/mnu/mpm/MenuList";
	}

	/**
	 * 메뉴정보 등록화면으로 이동  한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/registMenu.do")
	@Secured("ROLE_ADMIN")
	public String registMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute MenuManageVO menuManageVO) {
		
		return "com/sym/mnu/mpm/MenuRegist";
	}

	/**
	 * 메뉴정보를 등록 한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/insertMenu.do")
	@Secured("ROLE_ADMIN")
	public String insertMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute MenuManageVO menuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(menuManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/mnu/mpm/MenuRegist";
		}

		if (menuManageService.selectMenuNoByPk(menuManageVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
			return "com/sym/mnu/mpm/MenuRegist";
		}
		
		if (progrmManageService.selectProgrmNMTotCnt(menuManageVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
			return "com/sym/mnu/mpm/MenuRegist";
		} 
		
		menuManageService.insertMenuManage(menuManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴정보 수정화면으로 이동  한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/editMenu.do")
	@Secured("ROLE_ADMIN")
	public String editMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		model.addAttribute(menuManageService.selectMenuManage(menuManageVO));

		return "com/sym/mnu/mpm/MenuEdit";
	}

	/**
	 * 메뉴정보를 수정 한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/updateMenu.do")
	@Secured("ROLE_ADMIN")
	public String updateMenu(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute MenuManageVO menuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(menuManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/mnu/mpm/MenuEdit";
		}
		
		if (progrmManageService.selectProgrmNMTotCnt(menuManageVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.update"));
			return "com/sym/mnu/mpm/MenuEdit";
		} 
		
		menuManageService.updateMenuManage(menuManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴정보를 삭제 한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/deleteMenu.do")
	@Secured("ROLE_ADMIN")
	public String deleteMenu(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {
		
		if (menuManageService.selectUpperMenuNoByPk(menuManageVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.delete.upperMenuExist"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
		    return "com/cmm/redirect";
		}

		menuManageService.deleteMenuManage(menuManageVO);

		String _MenuNm = "%";
		menuManageVO.setMenuNm(_MenuNm);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴목록 멀티 삭제한다.
	 * 
	 * @param menuManageVO
	 * @param checkedMenuNoForDel
	 */
	@RequestMapping(value="/sym/mnu/mpm/deleteListMenu.do")
	@Secured("ROLE_ADMIN")
	public String deleteListMenu(
			@ModelAttribute MenuManageVO menuManageVO, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] delMenuNos = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		delMenuNos = request.getParameterValues("uniqIds"); 

		menuManageVO.setMenuNo(Integer.parseInt(delMenuNos[0]));
		if (menuManageService.selectUpperMenuNoByPk(menuManageVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.delete.upperMenuExist"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
		    return "com/cmm/redirect";
		} 
		
		if (delMenuNos == null || (delMenuNos.length == 0)) {
			model.addAttribute("message",  MessageHelper.getMessage("fail.common.delete"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
		    return "com/cmm/redirect";
		} 

		menuManageService.deleteMenuManageList(delMenuNos);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenu.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴트리를 조회한다.
	 * 
	 * @param menuManageVO
	 */
	@IncludedInfo(name = "메뉴트리관리", order = 6140, gid = 60)
	@RequestMapping(value = "/sym/mnu/mpm/listMenuTree.do")
	@Secured("ROLE_ADMIN")
	public String listMenuTree(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model)  {

		model.addAttribute("list_menulist", menuManageService.selectMenuList());

		return "com/sym/mnu/mpm/MenuTreeList";
	}

	/**
	 * 메뉴트리의 메뉴정보를 등록한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/insertMenuTree.do")
	@Secured("ROLE_ADMIN")
	public String insertMenuTree(
			@ModelAttribute MenuManageVO menuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(menuManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/mnu/mpm/MenuTreeList";
		}

		if (menuManageService.selectMenuNoByPk(menuManageVO) != 0) {
			model.addAttribute("message", MessageHelper.getMessage("common.isExist.msg"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
		    return "com/cmm/redirect";
		}
		if (progrmManageService.selectProgrmNMTotCnt(menuManageVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
		    return "com/cmm/redirect";
		} 
		
		menuManageService.insertMenuManage(menuManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴트리의 메뉴정보를 수정한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/updateMenuTree.do")
	@Secured("ROLE_ADMIN")
	public String updateMenuTree(
			@ModelAttribute MenuManageVO menuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(menuManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
		    return "com/cmm/redirect";
		}
		
		if (progrmManageService.selectProgrmNMTotCnt(menuManageVO.getProgrmFileNm()) == 0) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.update"));
			model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
		    return "com/cmm/redirect";
		} 
		
		menuManageService.updateMenuManage(menuManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴트리의 메뉴정보를 삭제한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/deleteMenuTree.do")
	@Secured("ROLE_ADMIN")
	public String deleteMenuTree(
			@ModelAttribute MenuManageVO menuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		menuManageService.deleteMenuManage(menuManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/mnu/mpm/listMenuTree.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 메뉴트리의 메뉴정보를 이동 메뉴목록을 조회한다.
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/moveMenuTree.do")
	@Secured("ROLE_ADMIN")
	public String moveMenuTree(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		model.addAttribute("list_menulist", menuManageService.selectMenuList());

		return "com/sym/mnu/mpm/MenuMvmn";
	}

	/* ### 일괄처리 프로세스 ### */

	/**
	 * 메뉴생성 일괄삭제프로세스
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/deleteMenuBnde.do")
	@Secured("ROLE_ADMIN")
	public String deleteMenuBnde(
			@ModelAttribute MenuManageVO menuManageVO, 
			ModelMap model) {

		menuManageService.menuBndeAllDelete();

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "com/sym/mnu/mpm/MenuBndeRegist";
	}

	/**
	 * 메뉴일괄등록화면 호출 및 메뉴일괄등록처리 프로세스
	 * 
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/registMenuBnde.do")
	@Secured("ROLE_ADMIN")
	public String registMenuBnde(
			@ModelAttribute MenuManageVO menuManageVO) {

		return "com/sym/mnu/mpm/MenuBndeRegist";
	}
	
	/**
	 * 메뉴일괄등록화면 호출 및 메뉴일괄등록처리 프로세스
	 * 
	 * @param multiRequest
	 * @param menuManageVO
	 */
	@RequestMapping(value = "/sym/mnu/mpm/insertMenuBnde.do")
	@Secured("ROLE_ADMIN")
	public String insertMenuBnde(
			@ModelAttribute MenuManageVO menuManageVO, 
			MultipartHttpServletRequest multiRequest,
			ModelMap model)
	throws Exception {

		String message = "";
		
		for (MultipartFile file : multiRequest.getFileMap().values()) {
			if (!"".equals(file.getOriginalFilename())) {
				// 2011.10.07 업로드 파일에 대한 확장자를 체크
				if (file.getOriginalFilename().endsWith(".xls") 
						|| file.getOriginalFilename().endsWith(".xlsx")
						|| file.getOriginalFilename().endsWith(".XLS") 
						|| file.getOriginalFilename().endsWith(".XLSX")) {

/*
					if (menuManageService.menuBndeAllDelete()) {
						message = menuManageService.menuBndeRegist(menuManageVO, file.getInputStream());
					} else {
						message = MessageHelper.getMessage("fail.common.msg");
						menuManageVO.setTmpCmd("EgovMenuBndeRegist Error!!");
					}
*/					
				} else {
					model.addAttribute("message", "xls, xlsx 파일 타입만 등록이 가능합니다.");
					return "com/sym/mnu/mpm/MenuBndeRegist";
				}
			} 
		}
		
		model.addAttribute("message", message);
		return "com/sym/mnu/mpm/MenuBndeRegist";
	}
	
}