package aramframework.com.sym.mnu.bmm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.mnu.bmm.domain.BkmkMenuManageVO;
import aramframework.com.sym.mnu.bmm.service.BkmkMenuManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 바로가기메뉴관리 정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BkmkMenuManageController {

	@Autowired
	private BkmkMenuManageService bkmkMenuManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 바로가기메뉴관리 정보에 대한 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	@IncludedInfo(name = "바로가기메뉴관리", order = 6170, gid = 60)
	@RequestMapping("/sym/mnu/bmm/listBkmkMenu.do")
	@Secured("ROLE_ADMIN")
	public String listBkmkMenu(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		bkmkMenuManageVO.fillPageInfo(paginationInfo);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bkmkMenuManageVO.setUserId(loginVO.getId());
		
		model.addAttribute("resultList", bkmkMenuManageService.selectBkmkMenuManageList(bkmkMenuManageVO));
		int totCnt = bkmkMenuManageService.selectBkmkMenuManageListCnt(bkmkMenuManageVO);

		bkmkMenuManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);
		model.addAttribute("uniqId", loginVO.getUniqId());

		return WebUtil.adjustViewName("/sym/mnu/bmm/BkmkMenuList");
	}

	/**
	 * 바로가기메뉴관리 정보를 삭제한다.
	 * 
	 * @param bkmkMenuManageVO
	 * @param checkMenuIds
	 */
	@RequestMapping("/sym/mnu/bmm/deleteListBkmkMenu.do")
	@Secured("ROLE_ADMIN")
	public String deleteListBkmkMenu(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO, 
			@RequestParam String checkMenuIds,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		String[] temp = checkMenuIds.split(",");
		BkmkMenuManageVO bmmVO = new BkmkMenuManageVO();
		for (int i = 0; i < temp.length; i++) {
			bmmVO.setMenuId(temp[i]);
			bmmVO.setUserId(loginVO.getId());
			bkmkMenuManageService.deleteBkmkMenuManage(bmmVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, bkmkMenuManageVO, "/sym/mnu/bmm/listBkmkMenu.do");
	}

	/**
	 * 바로가기메뉴관리 등록화면으로 이동한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	@RequestMapping("/sym/mnu/bmm/registBkmkMenu.do")
	@Secured("ROLE_ADMIN")
	public String addBkmkMenuManage(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO,
			ModelMap model) {

		if (!bkmkMenuManageVO.getMenuId().equals("")) {
			bkmkMenuManageVO.setProgrmStrePath(bkmkMenuManageService.selectUrl(bkmkMenuManageVO));
		}
		
		return WebUtil.adjustViewName("/sym/mnu/bmm/BkmkMenuRegist");
	}

	/**
	 * 바로가기메뉴관리 정보를 등록한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	@RequestMapping("/sym/mnu/bmm/insertBkmkMenu.do")
	@Secured("ROLE_ADMIN")
	public String insertBkmkMenu(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(bkmkMenuManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/mnu/bmm/BkmkMenuRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bkmkMenuManageVO.setUserId(loginVO.getId());
		
		bkmkMenuManageService.insertBkmkMenuManage(bkmkMenuManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, bkmkMenuManageVO, "/sym/mnu/bmm/listBkmkMenu.do");
	}

	/**
	 * 메뉴정보 목록을 조회한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	@RequestMapping(value="/sym/mnu/bmm/listBkmkMenuPopup.do")
	@Secured("ROLE_ADMIN")
	public String listBkmkMenuPopup(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bkmkMenuManageVO.setUserId(loginVO.getId());

		PaginationInfo paginationInfo = new PaginationInfo();
		bkmkMenuManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bkmkMenuManageService.selectBkmkMenuList(bkmkMenuManageVO));
		int totCnt = bkmkMenuManageService.selectBkmkMenuListCnt(bkmkMenuManageVO);

		bkmkMenuManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/mnu/bmm/BkmkMenuPopup");
	}

	/**
	 * 바로가기메뉴관리 미리보기 화면으로 이동한다.
	 * 
	 * @param bkmkMenuManageVO
	 */
	@RequestMapping(value="/sym/mnu/bmm/previewBkmkMenuPopup.do")
	public String previewBkmkMenuPopup(
			@ModelAttribute BkmkMenuManageVO bkmkMenuManageVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		bkmkMenuManageVO.setSizeAndOffset(10, 0);
		bkmkMenuManageVO.setUserId(loginVO.getId());

		model.addAttribute("list_menulist", bkmkMenuManageService.selectBkmkMenuManageList(bkmkMenuManageVO));

		return WebUtil.adjustViewName("/sym/mnu/bmm/BkmkMenuPreview");
	}
	
}
