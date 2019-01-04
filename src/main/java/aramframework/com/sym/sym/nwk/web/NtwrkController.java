package aramframework.com.sym.sym.nwk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.sym.nwk.domain.NtwrkVO;
import aramframework.com.sym.sym.nwk.service.NtwrkService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 네트워크관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 네트워크관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 네트워크관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NtwrkController {

	@Autowired
	private NtwrkService ntwrkService;

	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 네트워크정보를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	@IncludedInfo(name = "네트워크관리", order = 6240, gid = 60)
	@RequestMapping(value = "/sym/sym/nwk/listNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String listNtwrk(
			@ModelAttribute NtwrkVO ntwrkVO, 
			ModelMap model) {

		if (ntwrkVO.getStrManageIem() == null)
			ntwrkVO.setStrManageIem("00");

		PaginationInfo paginationInfo = new PaginationInfo();
		ntwrkVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ntwrkService.selectNtwrkList(ntwrkVO));
		int totCnt = ntwrkService.selectNtwrkListCnt(ntwrkVO);

		ntwrkVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 관리항목코드
		cmmUseService.populateCmmCodeList("COM067", "COM067_manageIem");

		return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkList");
	}

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/detailNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String detailNtwrk(
			NtwrkVO ntwrkVO,
			ModelMap model) {

		model.addAttribute(ntwrkService.selectNtwrk(ntwrkVO));
		
		return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkDetail");
	}

	/**
	 * 네트워크정보 등록 화면으로 이동한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/registNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String registNtwrk(
			@ModelAttribute NtwrkVO ntwrkVO) {

		// 관리항목코드
		cmmUseService.populateCmmCodeList("COM067", "COM067_manageIem");

		return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkRegist");
	}

	/**
	 * 네트워크정보를 신규로 등록한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/insertNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String insertNtwrk(
			@ModelAttribute NtwrkVO ntwrkVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ntwrkVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkRegist");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ntwrkVO.setFrstRegisterId(loginVO.getId());

		ntwrkService.insertNtwrk(ntwrkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/sym/nwk/listNtwrk.do");
	}

	/**
	 * 네트워크정보 수정 화면으로 이동한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/editNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String editNtwrk(
			NtwrkVO ntwrkVO,
			ModelMap model) {

		model.addAttribute(ntwrkService.selectNtwrk(ntwrkVO));
		
		// 관리항목코드
		cmmUseService.populateCmmCodeList("COM067", "COM067_manageIem");

		return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkEdit");
	}

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/updateNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String updateNtwrk(
			@ModelAttribute NtwrkVO ntwrkVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ntwrkVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/nwk/NtwrkEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ntwrkVO.setLastUpdusrId(loginVO.getId());

		ntwrkService.updateNtwrk(ntwrkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/sym/nwk/listNtwrk.do");
	}

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * 
	 * @param ntwrkVO
	 */
	@RequestMapping(value = "/sym/sym/nwk/deleteNtwrk.do")
	@Secured("ROLE_ADMIN")
	public String deleteNtwrk(
			@ModelAttribute NtwrkVO ntwrkVO, 
			ModelMap model) {

		ntwrkService.deleteNtwrk(ntwrkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/sym/nwk/listNtwrk.do");
	}

}