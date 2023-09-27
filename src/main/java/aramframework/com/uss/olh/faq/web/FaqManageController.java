package aramframework.com.uss.olh.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olh.faq.domain.FaqManageVO;
import aramframework.com.uss.olh.faq.service.FaqManageService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * FAQ내용을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class FaqManageController {

	@Autowired
	private FaqManageService faqManageService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * FAQ 목록을 조회한다.
	 * 
	 * @param faqManageVO
	 */
	@IncludedInfo(name = "FAQ관리", order = 5090, gid = 50)
	@RequestMapping(value = "/uss/olh/faq/listFaq.do")
	public String listFaq(
			@ModelAttribute FaqManageVO faqManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		faqManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", faqManageService.selectFaqList(faqManageVO));
		int totCnt = faqManageService.selectFaqListCnt(faqManageVO);

		faqManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return "com/uss/olh/faq/FaqList";
	}

	/**
	 * FAQ 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping("/uss/olh/faq/detailFaq.do")
	public String detailFaq(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO,
			ModelMap model) {

		model.addAttribute(faqManageService.selectFaqListDetail(faqManageVO));

		return "com/uss/olh/faq/FaqDetail";
	}

	/**
	 * FAQ를 등록하기 위한 전 처리
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping("/uss/olh/faq/registFaq.do")
	@Secured("ROLE_USER")
	public String registFaq(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO, 
			ModelMap model) {

		return "com/uss/olh/faq/FaqRegist";
	}

	/**
	 * FAQ를 등록한다.
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping("/uss/olh/faq/insertFaq.do")
	@Secured("ROLE_USER")
	public String insertFaq(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO, 
			BindingResult bindingResult,
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(faqManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/olh/faq/FaqRegist";
		}

		// 첨부파일 관련 첨부파일ID 생성
		faqManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "FAQ"));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		faqManageVO.setFrstRegisterId(loginVO.getUserId()); // 최초등록자ID

		faqManageService.insertFaqCn(faqManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/olh/faq/listFaq.do");
	    return "com/cmm/redirect";
	}

	/**
	 * FAQ를 수정하기 위한 전 처리
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping("/uss/olh/faq/editFaq.do")
	@Secured("ROLE_USER")
	public String editFaq(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO,
			ModelMap model) {

		model.addAttribute(faqManageService.selectFaqListDetail(faqManageVO));

		return "com/uss/olh/faq/FaqEdit";
	}

	/**
	 * FAQ를 수정처리한다.
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping("/uss/olh/faq/updateFaq.do")
	@Secured("ROLE_USER")
	public String updateFaq(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO, 
			BindingResult bindingResult,
			MultipartHttpServletRequest multiRequest,
			ModelMap model) 
	throws Exception {

		// Validation
		beanValidator.validate(faqManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/uss/olh/faq/FaqEdit";
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = faqManageVO.getAtchFileId();
		faqManageVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "FAQ", atchFileId));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		faqManageVO.setLastUpdusrId(loginVO.getUserId()); // 최종수정자ID

		faqManageService.updateFaqCn(faqManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/olh/faq/listFaq.do");
	    return "com/cmm/redirect";
	}

	/**
	 * FAQ를 삭제처리한다.
	 * 
	 * @param faqManageVO
	 */
	@RequestMapping(value="/uss/olh/faq/deleteFaq.do")
	@Secured("ROLE_USER")
	public String deleteFaqManage(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute FaqManageVO faqManageVO, 
			ModelMap model) {

		faqManageService.deleteFaqCn(faqManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/olh/faq/listFaq.do");
	    return "com/cmm/redirect";
	}

}
