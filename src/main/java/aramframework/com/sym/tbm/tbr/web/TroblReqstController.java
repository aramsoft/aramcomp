package aramframework.com.sym.tbm.tbr.web;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.tbm.tbr.domain.TroblReqstVO;
import aramframework.com.sym.tbm.tbr.service.TroblReqstService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 장애신청정보에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 장애신청정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다. 
 *         - 장애신청정보의 조회기능은 목록조회, 상세조회로  구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class TroblReqstController {

	@Autowired
	private TroblReqstService troblReqstService;

	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	@IncludedInfo(name = "장애신청관리", order = 6260, gid = 60)
	@RequestMapping(value = "/sym/tbm/tbr/listTroblReqst.do")
	@Secured("ROLE_USER")
	public String listTroblReqst(
			@ModelAttribute TroblReqstVO troblReqstVO, 
			ModelMap model) {

		if (troblReqstVO.getStrTroblKnd() == null)
			troblReqstVO.setStrTroblKnd("00");
		if (troblReqstVO.getStrProcessSttus() == null)
			troblReqstVO.setStrProcessSttus("00");

		PaginationInfo paginationInfo = new PaginationInfo();
		troblReqstVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", troblReqstService.selectTroblReqstList(troblReqstVO));
		int totCnt = troblReqstService.selectTroblReqstListCnt(troblReqstVO);

		troblReqstVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		cmmUseService.populateCmmCodeList("COM065", "COM065_troblKnd");
		cmmUseService.populateCmmCodeList("COM068", "COM068_processSttus");

		return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstList");
	}

	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/detailTroblReqst.do")
	@Secured("ROLE_USER")
	public String detailTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO,
			ModelMap model) {

		model.addAttribute(troblReqstService.selectTroblReqst(troblReqstVO));
		
		return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstDetail");
	}

	/**
	 * 장애요청정보 등록 화면으로 이동한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/registTroblReqst.do")
	@Secured("ROLE_USER")
	public String registTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO) {

		cmmUseService.populateCmmCodeList("COM065", "COM065_troblKnd");

		return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstRegist");
	}

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/insertTroblReqst.do")
	@Secured("ROLE_USER")
	public String insertTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(troblReqstVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		troblReqstVO.setTroblOccrrncTime(StringUtil.removeMinusChar(troblReqstVO.getTroblOccrrncTime()));
		troblReqstVO.setTroblRequstTime(StringUtil.removeMinusChar(troblReqstVO.getTroblRequstTime()));
		troblReqstVO.setFrstRegisterId(loginVO.getId());
		troblReqstVO.setProcessSttus("A");

		troblReqstService.insertTroblReqst(troblReqstVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbr/listTroblReqst.do");
	}

	/**
	 * 장애요청정보 수정 화면으로 이동한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/editTroblReqst.do")
	@Secured("ROLE_USER")
	public String editTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO,
			ModelMap model) {

		model.addAttribute(troblReqstService.selectTroblReqst(troblReqstVO));
		
		cmmUseService.populateCmmCodeList("COM065", "COM065_troblKnd");

		return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstEdit");
	}

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/updateTroblReqst.do")
	@Secured("ROLE_USER")
	public String updateTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(troblReqstVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/tbm/tbr/TroblReqstEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		troblReqstVO.setTroblOccrrncTime(StringUtil.removeMinusChar(troblReqstVO.getTroblOccrrncTime()));
		troblReqstVO.setTroblRequstTime(StringUtil.removeMinusChar(troblReqstVO.getTroblRequstTime()));
		troblReqstVO.setLastUpdusrId(loginVO.getId());

		troblReqstService.updateTroblReqst(troblReqstVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbr/listTroblReqst.do");
	}

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/deleteTroblReqst.do")
	@Secured("ROLE_USER")
	public String deleteTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO, 
			ModelMap model) {

		troblReqstService.deleteTroblReqst(troblReqstVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbr/listTroblReqst.do");
	}

	/**
	 * 장애처리를 요청한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/requstTroblReqst.do")
	@Secured("ROLE_USER")
	public String requstTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		troblReqstVO.setLastUpdusrId(loginVO.getId());
		troblReqstVO.setProcessSttus("R");
		
		troblReqstService.requstTroblReqst(troblReqstVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbr/listTroblReqst.do");
	}

	/**
	 * 장애처리취소를 요청한다.
	 * 
	 * @param troblReqstVO
	 */
	@RequestMapping(value = "/sym/tbm/tbr/cancelTroblReqst.do")
	@Secured("ROLE_USER")
	public String cancelTroblReqst(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute TroblReqstVO troblReqstVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		troblReqstVO.setLastUpdusrId(loginVO.getId());
		troblReqstVO.setProcessSttus("A");

		troblReqstService.requstTroblReqst(troblReqstVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbr/listTroblReqst.do");
	}

}