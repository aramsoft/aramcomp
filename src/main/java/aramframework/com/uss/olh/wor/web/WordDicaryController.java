package aramframework.com.uss.olh.wor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olh.wor.domain.WordDicaryVO;
import aramframework.com.uss.olh.wor.service.WordDicaryService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 용어사전을 처리하는 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class WordDicaryController {

	@Autowired
	private WordDicaryService wordDicaryService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 용어사전목록을 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	@IncludedInfo(name = "용어사전", order = 5080, gid = 50)
	@RequestMapping(value = "/uss/olh/wor/listWordDicary.do")
	public String listWordDicary(
			@ModelAttribute WordDicaryVO wordDicaryVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		wordDicaryVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", wordDicaryService.selectWordDicaryList(wordDicaryVO));
		int totCnt = wordDicaryService.selectWordDicaryListCnt(wordDicaryVO);

		wordDicaryVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryList");
	}

	/**
	 * 용어사전 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/detailWordDicary.do")
	public String detailWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO,
			ModelMap model) {

		model.addAttribute(wordDicaryService.selectWordDicaryDetail(wordDicaryVO));

		return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryDetail");
	}

	/**
	 * 용어사전정보를 등록하기 위한 화면
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/registWordDicary.do")
	@Secured("ROLE_USER")
	public String registWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO) {

		return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryRegist");
	}

	/**
	 * 용어사전정보를 등록한다.
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/insertWordDicary.do")
	@Secured("ROLE_USER")
	public String insertWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(wordDicaryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wordDicaryVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		wordDicaryService.insertWordDicary(wordDicaryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olh/wor/listWordDicary.do");
	}

	/**
	 * 용어사전정보를 수정하기 위한  화면
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/editWordDicary.do")
	@Secured("ROLE_USER")
	public String editWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO,
			ModelMap model) {

		model.addAttribute(wordDicaryService.selectWordDicaryDetail(wordDicaryVO));

		return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryEdit");
	}

	/**
	 * 용어사전정보를 수정한다.
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/updateWordDicary.do")
	@Secured("ROLE_USER")
	public String updateWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// Validation
		beanValidator.validate(wordDicaryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/wor/WordDicaryEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wordDicaryVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		wordDicaryService.updateWordDicary(wordDicaryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olh/wor/listWordDicary.do");
	}

	/**
	 * 용어사전정보를 삭제한다.
	 * 
	 * @param wordDicaryVO
	 */
	@RequestMapping("/uss/olh/wor/deleteWordDicary.do")
	@Secured("ROLE_USER")
	public String deleteWordDicary(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute WordDicaryVO wordDicaryVO, 
			ModelMap model) {

		wordDicaryService.deleteWordDicary(wordDicaryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olh/wor/listWordDicary.do");
	}

}
