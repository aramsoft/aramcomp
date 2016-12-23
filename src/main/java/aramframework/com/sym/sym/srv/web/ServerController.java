package aramframework.com.sym.sym.srv.web;


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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.sym.srv.domain.ServerEqpmnRelateVO;
import aramframework.com.sym.sym.srv.domain.ServerEqpmnVO;
import aramframework.com.sym.sym.srv.domain.ServerVO;
import aramframework.com.sym.sym.srv.service.ServerService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 서버정보관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 서버정보관리에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다. 
 *         - 서버정보관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ServerController {

	@Autowired
	private ServerService serverService;

	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 서버장비를 관리하기 위해 등록된 서버장비목록을 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@IncludedInfo(name = "서버정보관리", order = 6250, gid = 60)
	@RequestMapping(value = "/sym/sym/srv/listServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String listServerEqpmn(
			@ModelAttribute ServerEqpmnVO serverEqpmnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		serverEqpmnVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", serverService.selectServerEqpmnList(serverEqpmnVO));
		int totCnt = serverService.selectServerEqpmnListCnt(serverEqpmnVO);

		serverEqpmnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnList");
	}

	/**
	 * 등록된 서버장비의 상세정보를 조회한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/detailServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String detailServerEqpmn(
			ServerEqpmnVO serverEqpmnVO, 
			ModelMap model) {
		
		model.addAttribute(serverService.selectServerEqpmn(serverEqpmnVO));
		
		return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnDetail");
	}

	/**
	 * 서버장비정보 등록 화면으로 이동한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/registServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String registServerEqpmn(
			@ModelAttribute ServerEqpmnVO serverEqpmnVO) {

		return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnRegist");
	}

	/**
	 * 서버장비정보를 신규로 등록한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/insertServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String insertServerEqpmn(
			@ModelAttribute ServerEqpmnVO serverEqpmnVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(serverEqpmnVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		serverEqpmnVO.setFrstRegisterId(loginVO.getId());

		serverService.insertServerEqpmn(serverEqpmnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServerEqpmn.do");
	}

	/**
	 * 서버장비정보 수정 화면으로 이동한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/editServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String editServerEqpmn(
			ServerEqpmnVO serverEqpmnVO, 
			ModelMap model) {
		
		model.addAttribute(serverService.selectServerEqpmn(serverEqpmnVO));
		
		return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnEdit");
	}

	/**
	 * 기 등록된 서버장비정보를 수정한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/updateServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String updateServerEqpmn(
			@ModelAttribute ServerEqpmnVO serverEqpmnVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(serverEqpmnVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnEdit");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		serverEqpmnVO.setLastUpdusrId(loginVO.getId());

		serverService.updateServerEqpmn(serverEqpmnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServerEqpmn.do");
	}

	/**
	 * 기 등록된 서버장비정보를 삭제한다.
	 * 
	 * @param serverEqpmnVO
	 */
	@RequestMapping(value = "/sym/sym/srv/deleteServerEqpmn.do")
	@Secured("ROLE_ADMIN")
	public String deleteServerEqpmn(
			@ModelAttribute ServerEqpmnVO serverEqpmnVO,
			ModelMap model) {
		
		serverService.deleteServerEqpmn(serverEqpmnVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServerEqpmn.do");
	}

	/**
	 * 서버정보를 관리하기 위해 등록된 서버목록을 조회한다.
	 * 
	 * @param serverVO
	 */
	@IncludedInfo(name = "서버(S/W)목록", order = 6251, gid = 60)
	@RequestMapping(value = "/sym/sym/srv/listServer.do")
	@Secured("ROLE_ADMIN")
	public String listServer(
			@ModelAttribute ServerVO serverVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		serverVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", serverService.selectServerList(serverVO));
		int totCnt = serverService.selectServerListCnt(serverVO);

		serverVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/sym/srv/ServerList");
	}

	/**
	 * 등록된 서버의 상세정보를 조회한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/detailServer.do")
	@Secured("ROLE_ADMIN")
	public String detailServer(
			ServerVO serverVO, 
			ModelMap model) {

		model.addAttribute(serverService.selectServer(serverVO));
		
		model.addAttribute("serverEqpmnRelateDetailList", serverService.selectServerEqpmnRelateDetail(serverVO));
		model.addAttribute("serverEqpmnRelateDetailCount", serverService.selectServerEqpmnRelateDetailTotCnt(serverVO));

		return WebUtil.adjustViewName("/sym/sym/srv/ServerDetail");
	}

	/**
	 * 서버정보 등록 화면으로 이동한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/registServer.do")
	@Secured("ROLE_ADMIN")
	public String registServer(
			@ModelAttribute ServerVO serverVO) {

		cmmUseService.populateCmmCodeList("COM064", "COM064_serverKnd");

		return WebUtil.adjustViewName("/sym/sym/srv/ServerRegist");
	}

	/**
	 * 서버정보를 신규로 등록한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/insertServer.do")
	@Secured("ROLE_ADMIN")
	public String insertServer(
			@ModelAttribute ServerVO serverVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(serverVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/srv/ServerRegist");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		serverVO.setFrstRegisterId(loginVO.getId());

		serverService.insertServer(serverVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServer.do");
	}

	/**
	 * 서버정보 수정 화면으로 이동한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/editServer.do")
	@Secured("ROLE_ADMIN")
	public String editServer(
			ServerVO serverVO,
			ModelMap model) {

		serverService.selectServer(serverVO);

		cmmUseService.populateCmmCodeList("COM064", "COM064_serverKnd");

		return WebUtil.adjustViewName("/sym/sym/srv/ServerEdit");
	}

	/**
	 * 기 등록된 서버정보를 수정한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/updateServer.do")
	@Secured("ROLE_ADMIN")
	public String updateServer(
			@ModelAttribute ServerVO serverVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(serverVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/sym/srv/ServerEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		serverVO.setLastUpdusrId(loginVO.getId());

		serverService.updateServer(serverVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServer.do");
	}

	/**
	 * 기 등록된 서버정보를 삭제한다.
	 * 
	 * @param serverVO
	 */
	@RequestMapping(value = "/sym/sym/srv/deleteServer.do")
	@Secured("ROLE_ADMIN")
	public String deleteServer(
			@ModelAttribute ServerVO serverVO, 
			ModelMap model) {

		serverService.deleteServer(serverVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServer.do");
	}

	/**
	 * 서버장비관계정보를 관리하기 위해 대상 서버장비목록을 조회한다.
	 * 
	 * @param serverEqpmnRelateVO
	 */
	@RequestMapping(value = "/sym/sym/srv/listServerEqpmnRelate.do")
	@Secured("ROLE_ADMIN")
	public String listServerEqpmnRelate(
			@ModelAttribute ServerEqpmnRelateVO serverEqpmnRelateVO, 
			ModelMap model)  {

		PaginationInfo paginationInfo = new PaginationInfo();
		serverEqpmnRelateVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", serverService.selectServerEqpmnRelateList(serverEqpmnRelateVO));
		int totCnt = serverService.selectServerEqpmnRelateListCnt(serverEqpmnRelateVO);

		serverEqpmnRelateVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		ServerVO serverVO = new ServerVO();
		serverVO.setServerId(serverEqpmnRelateVO.getServerId());
		model.addAttribute("server", serverService.selectServer(serverVO));

		return WebUtil.adjustViewName("/sym/sym/srv/ServerEqpmnRelateRegist");
	}

	/**
	 * 서버장비관계정보를 등록 또는 삭제처리한다.
	 * 
	 * @param serverId
	 * @param serverEqpmnIds
	 * @param regYns
	 * @param serverEqpmnRelateVO
	 */
	@RequestMapping(value = "/sym/sym/srv/updateServerEqpmnRelate.do")
	@Secured("ROLE_ADMIN")
	public String updateServerEqpmnRelate(
			@ModelAttribute ServerEqpmnRelateVO serverEqpmnRelateVO, 
			@RequestParam("serverId") String serverId, 
			@RequestParam("serverEqpmnIds") String serverEqpmnIds,
			@RequestParam("regYns") String regYns, 
			ModelMap model) {

		serverService.updateServerEqpmnRelate(serverId, serverEqpmnIds, regYns);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/sym/srv/listServerEqpmnRelate.do?serverId="+serverId);
	}

}