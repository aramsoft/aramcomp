package aramframework.com.utl.sys.pxy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.pxy.domain.ProxySvcLogVO;
import aramframework.com.utl.sys.pxy.domain.ProxySvcVO;
import aramframework.com.utl.sys.pxy.service.ProxySvcService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 프록시서비스정보에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class ProxySvcController {

	@Autowired
	private ProxySvcService proxySvcService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	@IncludedInfo(name = "프록시서비스모니터링", order = 9070, gid = 90)
	@RequestMapping(value = "/utl/sys/pxy/listProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String listProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		proxySvcVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", proxySvcService.selectProxySvcList(proxySvcVO));

		int totCnt = proxySvcService.selectProxySvcListCnt(proxySvcVO);
		proxySvcVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcList");
	}

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/detailProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String detailProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO) {

		proxySvcService.selectProxySvc(proxySvcVO);

		return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcDetail");
	}

	/**
	 * 프록시서비스를 신규로 등록하는 화면으로 이동한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/registProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String registProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO) {

		cmmUseService.populateCmmCodeList("COM072", "COM072_svcSttus");

		return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcRegist");
	}

	/**
	 * 프록시서비스를 신규로 등록한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/insertProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String insertProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(proxySvcVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcRegist");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		proxySvcVO.setFrstRegisterId(loginVO.getId());

		proxySvcService.insertProxySvc(proxySvcVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/pxy/listProxySvc.do");
	}

	/**
	 * 기 등록된 프록시서비스를 수정하는 화면으로 이동한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/editProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String editProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO) {

		proxySvcService.selectProxySvc(proxySvcVO);

		cmmUseService.populateCmmCodeList("COM072", "COM072_svcSttus");

		return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcEdit");
	}

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/updateProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String updateProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(proxySvcVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/pxy/ProxySvcEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		proxySvcVO.setLastUpdusrId(loginVO.getId());

		proxySvcService.updateProxySvc(proxySvcVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/pxy/listProxySvc.do");
	}

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 * 
	 * @param proxySvcVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/deleteProxySvc.do")
	@Secured("ROLE_ADMIN")
	public String deleteProxySvc(
			@ModelAttribute ProxySvcVO proxySvcVO,
			ModelMap model) {

		proxySvcService.deleteProxySvc(proxySvcVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/pxy/listProxySvc.do");
	}

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 * 
	 * @param proxySvcLogVO
	 */
	@RequestMapping(value = "/utl/sys/pxy/listProxyLog.do")
	@Secured("ROLE_ADMIN")
	public String selectProxyLogList(
			@ModelAttribute ProxySvcLogVO proxySvcLogVO, 
			ModelMap model) {
		
		if (proxySvcLogVO.getStrStartDate() == null || proxySvcLogVO.getStrEndDate() == null) {
			proxySvcLogVO.setStrStartDate(DateUtil.addMonth(DateUtil.getToday(), -1));
			proxySvcLogVO.setStrEndDate(DateUtil.getToday());
		} else {
			proxySvcLogVO.setStrStartDate(StringUtil.removeMinusChar(proxySvcLogVO.getStrStartDate()));
			proxySvcLogVO.setStrEndDate(StringUtil.removeMinusChar(proxySvcLogVO.getStrEndDate()));
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		proxySvcLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", proxySvcService.selectProxySvcLogList(proxySvcLogVO));

		int totCnt = proxySvcService.selectProxySvcLogListCnt(proxySvcLogVO);
		proxySvcLogVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		proxySvcLogVO.setStrStartDate(StringUtil.addMinusChar(proxySvcLogVO.getStrStartDate()));
		proxySvcLogVO.setStrEndDate(StringUtil.addMinusChar(proxySvcLogVO.getStrEndDate()));

		return WebUtil.adjustViewName("/utl/sys/pxy/ProxyLogList");
	}

}