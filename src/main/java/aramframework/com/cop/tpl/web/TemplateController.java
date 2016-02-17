package aramframework.com.cop.tpl.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.tpl.domain.TemplateInfVO;
import aramframework.com.cop.tpl.service.TemplateService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 템플릿 관리를 위한 컨트롤러 클래스
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
public class TemplateController {

	@Autowired 
	private TemplateService tmplatService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 템플릿 목록을 조회한다.
	 * 
	 * @param templateInfVO
	 */
	@IncludedInfo(name = "템플릿관리", order = 4020, gid = 40)
	@RequestMapping("/cop/tpl/listTemplate.do")
	@Secured("ROLE_ADMIN")
	public String listTemplate(
			@ModelAttribute TemplateInfVO templateInfVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		templateInfVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", tmplatService.selectTemplateInfs(templateInfVO));
		int totCnt = tmplatService.selectTemplateInfsCnt(templateInfVO);

		templateInfVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/tpl/TemplateList");
	}

	/**
	 * 템플릿 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/registTemplate.do")
	@Secured("ROLE_ADMIN")
	public String registTemplate(
			@ModelAttribute TemplateInfVO templateInfVO) {

		// 템플릿구분
		cmmUseService.populateCmmCodeList("COM005", "COM005_tmplatSe");

		return WebUtil.adjustViewName("/cop/tpl/TemplateRegist");
	}

	/**
	 * 템플릿 정보를 등록한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/insertTemplate.do")
	@Secured("ROLE_ADMIN")
	public String insertTemplate(
			@ModelAttribute TemplateInfVO templateInfVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(templateInfVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/tpl/TemplateRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		templateInfVO.setFrstRegisterId(loginVO.getUniqId());

		tmplatService.insertTemplateInf(templateInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/tpl/listTemplate.do");
	}

	/**
	 * 템플릿 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/editTemplate.do")
	@Secured("ROLE_ADMIN")
	public String editTemplate(
			TemplateInfVO templateInfVO,
			ModelMap model) {

		model.addAttribute(tmplatService.selectTemplateInf(templateInfVO));

		// 템플릿구분
		cmmUseService.populateCmmCodeList("COM005", "COM005_tmplatSe");

		return WebUtil.adjustViewName("/cop/tpl/TemplateEdit");
	}

	/**
	 * 템플릿 정보를 수정한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/updateTemplate.do")
	@Secured("ROLE_ADMIN")
	public String updateTemplate(
			@ModelAttribute TemplateInfVO templateInfVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(templateInfVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/tpl/TemplateEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		templateInfVO.setLastUpdusrId(loginVO.getUniqId());

		tmplatService.updateTemplateInf(templateInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/tpl/listTemplate.do");
	}

	/**
	 * 템플릿 정보를 삭제한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/deleteTemplate.do")
	@Secured("ROLE_ADMIN")
	public String deleteTemplate(
			@ModelAttribute TemplateInfVO templateInfVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		templateInfVO.setLastUpdusrId(loginVO.getUniqId());

		tmplatService.deleteTemplateInf(templateInfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/tpl/listTemplate.do");
	}

	/**
	 * 팝업을 위한 템플릿 목록을 조회한다.
	 * 
	 * @param templateInfVO
	 */
	@RequestMapping("/cop/tpl/listTemplatePopup.do")
	public String listTemplatePopup(
			HttpServletRequest request, 
			@ModelAttribute("searchVO") TemplateInfVO templateInfVO, 
			ModelMap model) {

		String typeFlag = request.getParameter("typeFlag");

		if ("CMY".equals(typeFlag)) {
			templateInfVO.setTypeFlag(typeFlag);
			templateInfVO.setTmplatSeCode("TMPT02");
		} else {
			templateInfVO.setTypeFlag(typeFlag);
			templateInfVO.setTmplatSeCode("TMPT01");
		}
		model.addAttribute("typeFlag", typeFlag);

		PaginationInfo paginationInfo = new PaginationInfo();
		templateInfVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", tmplatService.selectTemplateInfs(templateInfVO));
		int totCnt = tmplatService.selectTemplateInfsCnt(templateInfVO);

		templateInfVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/tpl/TemplatePopup");
	}
	
}
