package aramframework.com.sym.bat.web;

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
import aramframework.com.sym.bat.domain.BatchOpertVO;
import aramframework.com.sym.bat.service.BatchOpertService;
import aramframework.com.sym.bat.validation.BatchOpertValidator;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 배치작업관리에 대한 controller 클래스를 정의한다.
 * 
 * 배치작업관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 * 배치작업관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BatchOpertController {

	@Autowired
	private BatchOpertService batchOpertService;
	
	@Autowired
	private BatchOpertValidator batchOpertValidator;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 배치작업 목록을 조회한다.(Popup)
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/listBatchOpertPopup.do")
	public String listBatchOpertPopup(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		batchOpertVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", batchOpertService.selectBatchOpertList(batchOpertVO));
		int totCnt = batchOpertService.selectBatchOpertListCnt(batchOpertVO);

		batchOpertVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/bat/BatchOpertPopup");
	}

	/**
	 * 배치작업 목록을 조회한다.
	 * 
	 * @param batchOpertVO
	 */
	@IncludedInfo(name = "배치작업관리", order = 6200, gid = 60)
	@RequestMapping("/sym/bat/listBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String listBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		batchOpertVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", batchOpertService.selectBatchOpertList(batchOpertVO));
		int totCnt = batchOpertService.selectBatchOpertListCnt(batchOpertVO);

		batchOpertVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/bat/BatchOpertList");
	}

	/**
	 * 배치작업정보을 상세조회한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/detailBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String detailBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO,
			ModelMap model) {
		
		model.addAttribute(batchOpertService.selectBatchOpert(batchOpertVO));

		return WebUtil.adjustViewName("/sym/bat/BatchOpertDetail");
	}

	/**
	 * 등록화면으로 이동한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/registBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String registBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			ModelMap model) {
	
		return WebUtil.adjustViewName("/sym/bat/BatchOpertRegist");
	}

	/**
	 * 배치작업을 등록한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/insertBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String insertBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(batchOpertVO, bindingResult);
		if( !"".equals(batchOpertVO.getBatchProgrm()) ) {
			batchOpertValidator.validate(batchOpertVO, bindingResult);
		}
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/bat/BatchOpertRegist");
		} 
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		batchOpertVO.setFrstRegisterId(loginVO.getUniqId());

		batchOpertService.insertBatchOpert(batchOpertVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, batchOpertVO, "/sym/bat/listBatchOpert.do");
	}

	/**
	 * 수정화면을 위한 배치작업정보을 조회한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/editBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String editBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO,
			ModelMap model) {
		
		model.addAttribute(batchOpertService.selectBatchOpert(batchOpertVO));

		return WebUtil.adjustViewName("/sym/bat/BatchOpertEdit");
	}

	/**
	 * 배치작업을 수정한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/updateBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String updateBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(batchOpertVO, bindingResult);
		if( !"".equals(batchOpertVO.getBatchProgrm()) ) {
			batchOpertValidator.validate(batchOpertVO, bindingResult);
		}
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/bat/BatchOpertEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		batchOpertVO.setLastUpdusrId(loginVO.getUniqId());

		batchOpertService.updateBatchOpert(batchOpertVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, batchOpertVO, "/sym/bat/listBatchOpert.do");
	}

	/**
	 * 배치작업을 삭제한다.
	 * 
	 * @param batchOpertVO
	 */
	@RequestMapping("/sym/bat/deleteBatchOpert.do")
	@Secured("ROLE_ADMIN")
	public String deleteBatchOpert(
			@ModelAttribute BatchOpertVO batchOpertVO, 
			ModelMap model) {

		batchOpertService.deleteBatchOpert(batchOpertVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, batchOpertVO, "/sym/bat/listBatchOpert.do");
	}

}