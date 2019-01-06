package aramframework.com.uss.ion.rwd.web;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.rwd.domain.RwardManageVO;
import aramframework.com.uss.ion.rwd.service.RwardManageService;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 포상관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 포상관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class RwardManageController {

	@Autowired
	private RwardManageService rwardManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	@IncludedInfo(name = "포상관리", order = 5300, gid = 50)
	@RequestMapping(value = "/uss/ion/rwd/listRward.do")
	@Secured("ROLE_USER")
	public String listRward(
			@ModelAttribute RwardManageVO rwardManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		rwardManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", rwardManageService.selectRwardManageList(rwardManageVO));
		int totCnt = rwardManageService.selectRwardManageListCnt(rwardManageVO);

		rwardManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		cmmUseService.populateCmmCodeList("COM055", "COM055_rward");

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardList");
	}

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/detailRward.do")
	public String detailRward(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO,
			ModelMap model) {

		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		model.addAttribute(rwardManageService.selectRwardManage(rwardManageVO));

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardDetail");
	}

	/**
	 * 포상관리 등록 화면으로 이동한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/registRward.do")
	public String registRward(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO) {
		
		cmmUseService.populateCmmCodeList("COM055", "COM055_rward");

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardRegist");
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/insertRward.do")
	public String insertRward(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(rwardManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/rwd/RwardRegist");
		} 
		
		// 첨부파일 관련 첨부파일ID 생성
		rwardManageVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "RWD_"));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		rwardManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		rwardManageService.insertRwardManage(rwardManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/rwd/listRward.do");
	}

	/**
	 * 포상관리 수정 화면으로 이동한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/editRward.do")
	public String editRward(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO,
			ModelMap model) {

		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		model.addAttribute(rwardManageService.selectRwardManage(rwardManageVO));

		cmmUseService.populateCmmCodeList("COM055", "COM055_rward");

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardEdit");
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/updateRward.do")
	public String updateRward(
			MultipartHttpServletRequest multiRequest,
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {
		
		beanValidator.validate(rwardManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/rwd/RwardEdit");
		} 
		
		// 첨부파일 관련 ID 생성 start....
		String atchFileId = rwardManageVO.getAtchFileId();
		rwardManageVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "RWD_", atchFileId));
		
		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		rwardManageService.updtRwardManage(rwardManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/rwd/listRward.do");
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/deleteRward.do")
	public String deleteRward(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO, 
			ModelMap model) {

		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		// 포상 삭제 처리
		rwardManageService.deleteRwardManage(rwardManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/rwd/listRward.do");
	}

	/*** 승인관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO
	 */
	@IncludedInfo(name = "포상승인관리", order = 5301, gid = 50)
	@RequestMapping(value = "/uss/ion/rwd/listRwardConfm.do")
	@Secured("ROLE_USER")
	public String listRwardConfm(
			@ModelAttribute RwardManageVO rwardManageVO, 
			ModelMap model) {
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		rwardManageVO.setSanctnerId(loginVO.getUniqId()); // 사용자가 승인권자인지 조건값 setting selectRwardManageList

		PaginationInfo paginationInfo = new PaginationInfo();
		rwardManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", rwardManageService.selectRwardManageConfmList(rwardManageVO));
		int totCnt = rwardManageService.selectRwardManageConfmListCnt(rwardManageVO);

		rwardManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		cmmUseService.populateCmmCodeList("COM055", "COM055_rward");

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardConfmList");
	}

	/**
	 * 포상을 승인하는 화면으로 이동한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/editRwardConfm.do")
	public String editRwardConfm(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO,
			ModelMap model) {

		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		model.addAttribute(rwardManageService.selectRwardManage(rwardManageVO));

		return WebUtil.adjustViewName("/uss/ion/rwd/RwardConfmEdit");
	}

	/**
	 * 신청된 포상을 승인처리한다.
	 * 
	 * @param rwardManageVO
	 */
	@RequestMapping(value = "/uss/ion/rwd/updateRwardConfm.do")
	public String updtRwardManageConfm(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RwardManageVO rwardManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {
		
		rwardManageVO.setRwardDe(StringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		beanValidator.validate(rwardManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/rwd/RwardConfmEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		rwardManageVO.setSanctnerId(loginVO.getUniqId());
		rwardManageVO.setLastUpdusrId(loginVO.getUniqId());

		rwardManageService.updtRwardManageConfm(rwardManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/rwd/listRward.do");
	}
	
}
