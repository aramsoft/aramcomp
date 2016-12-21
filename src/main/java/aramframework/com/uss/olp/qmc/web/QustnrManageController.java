package aramframework.com.uss.olp.qmc.web;

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
import aramframework.com.uss.olp.qmc.domain.QustnrManageVO;
import aramframework.com.uss.olp.qmc.service.QustnrManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class QustnrManageController {

	@Autowired
	private QustnrManageService qustnrManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 설문관리 팝업 목록을 조회한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/listQustnrPopup.do")
	public String listQustnrPopup(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrManageService.selectQustnrManageList(qustnrManageVO));
		int totCnt = (Integer) qustnrManageService.selectQustnrManageListCnt(qustnrManageVO);

		qustnrManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qmc/QustnrListPopup");
	}

	/**
	 * 설문관리 목록을 조회한다.
	 * 
	 * @param qustnrManageVO
	 */
	@IncludedInfo(name = "설문관리", order = 5141, gid = 50)
	@RequestMapping(value = "/uss/olp/qmc/listQustnr.do")
	@Secured("ROLE_USER")
	public String listQustnr(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrManageService.selectQustnrManageList(qustnrManageVO));
		int totCnt = (Integer) qustnrManageService.selectQustnrManageListCnt(qustnrManageVO);

		qustnrManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qmc/QustnrList");
	}

	/**
	 * 설문관리 목록을 상세조회 한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/detailQustnr.do")
	public String detailQustnr(
			QustnrManageVO qustnrManageVO,
			ModelMap model) {

		model.addAttribute(qustnrManageService.selectQustnrManageDetail(qustnrManageVO));

		// 공통코드 직업유형 조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/olp/qmc/QustnrDetail");
	}

	/**
	 * 설문관리 등록하기 위한 화면으로 이동한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/registQustnr.do")
	@Secured("ROLE_USER")
	public String registQustnr(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			ModelMap model) {

		// 공통코드 직업유형 조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");
	
		// 설문템플릿 정보 불러오기
		model.addAttribute("listQustnrTmplat", qustnrManageService.selectQustnrTmplatManageList(qustnrManageVO));

		return WebUtil.adjustViewName("/uss/olp/qmc/QustnrRegist");
	}

	/**
	 * 설문관리를 등록한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/insertQustnr.do")
	@Secured("ROLE_USER")
	public String insertQustnr(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(qustnrManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// 설문템플릿 정보 불러오기
			model.addAttribute("listQustnrTmplat", qustnrManageService.selectQustnrTmplatManageList(qustnrManageVO));

			return WebUtil.adjustViewName("/uss/olp/qmc/QustnrRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrManageVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrManageService.insertQustnrManage(qustnrManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qmc/listQustnr.do");
	}

	/**
	 * 설문관리를 수정하기 위한 화면으로 이동한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/editQustnr.do")
	@Secured("ROLE_USER")
	public String editQustnr(
			QustnrManageVO qustnrManageVO,
			ModelMap model) {

		qustnrManageVO = qustnrManageService.selectQustnrManageDetail(qustnrManageVO);

		// 설문템플릿 정보 불러오기
		model.addAttribute("listQustnrTmplat", qustnrManageService.selectQustnrTmplatManageList(qustnrManageVO));
		model.addAttribute(qustnrManageVO);
		
		// 공통코드 직업유형 조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/olp/qmc/QustnrEdit");
	}

	/**
	 * 설문관리를 수정한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/updateQustnr.do")
	@Secured("ROLE_USER")
	public String updateQustnr(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(qustnrManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// 설문템플릿 정보 불러오기
			model.addAttribute("listQustnrTmplat", qustnrManageService.selectQustnrTmplatManageList(qustnrManageVO));

			return WebUtil.adjustViewName("/uss/olp/qmc/QustnrEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrManageVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrManageService.updateQustnrManage(qustnrManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qmc/listQustnr.do");
	}

	/**
	 * 설문관리을 삭제 한다.
	 * 
	 * @param qustnrManageVO
	 */
	@RequestMapping(value = "/uss/olp/qmc/deleteQustnr.do")
	@Secured("ROLE_USER")
	public String deleteQustnr(
			@ModelAttribute QustnrManageVO qustnrManageVO, 
			ModelMap model) {

		qustnrManageService.deleteQustnrManage(qustnrManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qmc/listQustnr.do");
	}

}
