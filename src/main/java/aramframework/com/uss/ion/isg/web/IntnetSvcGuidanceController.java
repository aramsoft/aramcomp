package aramframework.com.uss.ion.isg.web;

import java.util.List;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.isg.domain.IntnetSvcGuidanceVO;
import aramframework.com.uss.ion.isg.service.IntnetSvcGuidanceService;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 인터넷서비스안내에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class IntnetSvcGuidanceController {

	@Autowired
	private IntnetSvcGuidanceService intnetSvcGuidanceService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@IncludedInfo(name = "인터넷서비스안내및관리", order = 5290, gid = 50)
	@RequestMapping("/uss/ion/isg/listIntnetSvcGuidance.do")
	@Secured("ROLE_USER")
	public String listIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		intnetSvcGuidanceVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", intnetSvcGuidanceService.selectIntnetSvcGuidanceList(intnetSvcGuidanceVO));
		int totCnt = intnetSvcGuidanceService.selectIntnetSvcGuidanceListCnt(intnetSvcGuidanceVO);

		intnetSvcGuidanceVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceList");
	}

	/**
	 * 인터넷서비스안내정보를 신규 등록을 위해 등록화면으로 이동한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@RequestMapping("/uss/ion/isg/registIntnetSvcGuidance.do")
	public String registIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO) {

		return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceRegist");
	}

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@RequestMapping("/uss/ion/isg/insertIntnetSvcGuidance.do")
	public String insertIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(intnetSvcGuidanceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		intnetSvcGuidanceVO.setUserId(loginVO.getId());

		intnetSvcGuidanceService.insertIntnetSvcGuidance(intnetSvcGuidanceVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/isg/listIntnetSvcGuidance.do");
	}

	/**
	 * 인터넷서비스안내정보 수정화면으로 이동한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@RequestMapping("/uss/ion/isg/editIntnetSvcGuidance.do")
	public String editIntnetSvcGuidance(
			IntnetSvcGuidanceVO intnetSvcGuidanceVO,
			ModelMap model) {

		model.addAttribute(intnetSvcGuidanceService.selectIntnetSvcGuidance(intnetSvcGuidanceVO));

		return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceEdit");
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@RequestMapping("/uss/ion/isg/updateIntnetSvcGuidance.do")
	public String updateIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(intnetSvcGuidanceVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		intnetSvcGuidanceVO.setUserId(loginVO.getId());

		intnetSvcGuidanceService.updateIntnetSvcGuidance(intnetSvcGuidanceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/isg/listIntnetSvcGuidance.do");
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@RequestMapping("/uss/ion/isg/deleteIntnetSvcGuidance.do")
	public String deleteIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO, 
			ModelMap model) {

		intnetSvcGuidanceService.deleteIntnetSvcGuidance(intnetSvcGuidanceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/isg/listIntnetSvcGuidance.do");
	}

	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * 
	 * @param intnetSvcGuidanceVO
	 */
	@IncludedInfo(name = "인터넷서비스안내 결과", order = 5291, gid = 50)
	@RequestMapping("/uss/ion/isg/viewIntnetSvcGuidance.do")
	public String viewIntnetSvcGuidance(
			@ModelAttribute IntnetSvcGuidanceVO intnetSvcGuidanceVO, 
			ModelMap model) {

		List<EgovMap> resultList = intnetSvcGuidanceService.selectIntnetSvcGuidanceResult(intnetSvcGuidanceVO);

		for (int i = 0; i < resultList.size(); i++) {
			resultList.get(i).put("intnetSvcDc", 
					(StringUtil.getHtmlStrCnvr(resultList.get(i).get("intnetSvcDc").toString())));
		}

		model.addAttribute("resultList", resultList);

		return WebUtil.adjustViewName("/uss/ion/isg/IntnetSvcGuidanceView");
	}
	
}
