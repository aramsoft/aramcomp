package aramframework.com.uss.ion.ctn.web;

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
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ctn.domain.CtsnnManageVO;
import aramframework.com.uss.ion.ctn.service.CtsnnManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 경조관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 경조관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class CtsnnManageController {

	@Autowired
	private CtsnnManageService ctsnnManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@IncludedInfo(name = "직원경조사관리", order = 5270, gid = 50)
	@RequestMapping(value = "/uss/ion/ctn/listCtsnn.do")
	@Secured("ROLE_USER")
	public String listCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		ctsnnManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ctsnnManageService.selectCtsnnManageList(ctsnnManageVO));

		int totCnt = ctsnnManageService.selectCtsnnManageListCnt(ctsnnManageVO);
		ctsnnManageVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		cmmUseService.populateCmmCodeList("COM054", "COM054_ctsnn");

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnList");
	}

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/detailCtsnn.do")
	public String detailCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO) {

		// 등록 상세정보
		ctsnnManageService.selectCtsnnManage(ctsnnManageVO);

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnDetail");
	}

	/**
	 * 경조관리 등록 화면으로 이동한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/registCtsnn.do")
	public String registCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO) {

		cmmUseService.populateCmmCodeList("COM054", "COM054_ctsnn");
		cmmUseService.populateCmmCodeList("COM073", "COM073_relate");

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnRegist");
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/insertCtsnn.do")
	public String insertCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ctsnnManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ctsnnManageVO.setFrstRegisterId(loginVO.getUniqId());

		ctsnnManageService.insertCtsnnManage(ctsnnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/ctn/listCtsnn.do");
	}

	/**
	 * 경조관리 수정 화면으로 이동한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/editCtsnn.do")
	public String editCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO) {

		// 등록 상세정보
		ctsnnManageService.selectCtsnnManage(ctsnnManageVO);

		cmmUseService.populateCmmCodeList("COM054", "COM054_ctsnn");
		cmmUseService.populateCmmCodeList("COM073", "COM073_relate");

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnEdit");
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/updateCtsnn.do")
	public String updateCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ctsnnManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnEdit");
		} 

		ctsnnManageService.updateCtsnnManage(ctsnnManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ctn/listCtsnn.do");
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/deleteCtsnn.do")
	public String deleteCtsnn(
			@ModelAttribute CtsnnManageVO ctsnnManageVO,
			ModelMap model) {

		ctsnnManageService.deleteCtsnnManage(ctsnnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/ctn/listCtsnn.do");
	}

	/*** 승인관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@IncludedInfo(name = "직원경조사승인관리", order = 5271, gid = 50)
	@RequestMapping(value = "/uss/ion/ctn/listCtsnnConfm.do")
	@Secured("ROLE_USER")
	public String listCtsnnConfm(
			@ModelAttribute CtsnnManageVO ctsnnManageVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ctsnnManageVO.setSanctnerId(loginVO.getUniqId()); // 사용자가 승인권자인지 조건값 setting selectCtsnnManageList

		PaginationInfo paginationInfo = new PaginationInfo();
		ctsnnManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ctsnnManageService.selectCtsnnManageConfmList(ctsnnManageVO));

		int totCnt = ctsnnManageService.selectCtsnnManageConfmListCnt(ctsnnManageVO);
		ctsnnManageVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		cmmUseService.populateCmmCodeList("COM054", "COM054_ctsnn");

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnConfmList");
	}

	/**
	 * 경조승인관리 상세정보를 조회한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/editCtsnnConfm.do")
	public String detailCtsnnConfm(
			@ModelAttribute CtsnnManageVO ctsnnManageVO) {
		
		// 등록 상세정보
		ctsnnManageService.selectCtsnnManage(ctsnnManageVO);

		return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnConfmEdit");
	}

	/**
	 * 기 등록된 경조관리정보를 승인 처리한다.
	 * 
	 * @param ctsnnManageVO
	 */
	@RequestMapping(value = "/uss/ion/ctn/updateCtsnnConfm.do")
	public String updateCtsnnConfm(
			@ModelAttribute CtsnnManageVO ctsnnManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ctsnnManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ctn/CtsnnConfmEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ctsnnManageVO.setSanctnerId(loginVO.getUniqId());
		ctsnnManageVO.setLastUpdusrId(loginVO.getUniqId());

		ctsnnManageService.updtCtsnnManageConfm(ctsnnManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ctn/listCtsnnConfm.do");
	}

}
