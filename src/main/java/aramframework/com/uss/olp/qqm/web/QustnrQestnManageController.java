package aramframework.com.uss.olp.qqm.web;

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
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olp.qqm.domain.QustnrQestnManageVO;
import aramframework.com.uss.olp.qqm.service.QustnrQestnManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문문항을 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class QustnrQestnManageController {

	@Autowired
	private QustnrQestnManageService qustnrQestnManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 설문문항 팝업 록을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/listQustnrQestnPopup.do")
	public String listQustnrQestnPopup(
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			ModelMap model) {

		// 설문지정보에서 넘어오면 자동검색 설정
		if (qustnrQestnManageVO.getSearchMode().equals("Y")) {
			qustnrQestnManageVO.setSearchCondition("QESTNR_ID");
			qustnrQestnManageVO.setSearchKeyword(qustnrQestnManageVO.getQestnrId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrQestnManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrQestnManageService.selectQustnrQestnManageList(qustnrQestnManageVO));
		int totCnt = (Integer) qustnrQestnManageService.selectQustnrQestnManageListCnt(qustnrQestnManageVO);

		qustnrQestnManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnListPopup");
	}

	/**
	 * 설문문항 목록을 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@IncludedInfo(name = "질문관리", order = 5142, gid = 50)
	@RequestMapping(value = "/uss/olp/qqm/listQustnrQestn.do")
	@Secured("ROLE_USER")
	public String listQustnrQestn(
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			ModelMap model) {

		// 설문지정보에서 넘어오면 자동검색 설정
		if (qustnrQestnManageVO.getSearchMode().equals("Y")) {
			qustnrQestnManageVO.setSearchCondition("QESTNR_ID");
			qustnrQestnManageVO.setSearchKeyword(qustnrQestnManageVO.getQestnrId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrQestnManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrQestnManageService.selectQustnrQestnManageList(qustnrQestnManageVO));
		int totCnt = (Integer) qustnrQestnManageService.selectQustnrQestnManageListCnt(qustnrQestnManageVO);

		qustnrQestnManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnList");
	}

	/**
	 * 설문문항 목록을 상세조회 한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/detailQustnrQestn.do")
	@Secured("ROLE_USER")
	public String detailQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO,
			ModelMap model) {

		model.addAttribute(qustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO));

		// 공통코드 질문유형 조회
		cmmUseService.populateCmmCodeList("COM018", "COM018_qestnType");

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnDetail");
	}

	/**
	 * 설문문항 등록화면으로 이동한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/registQustnrQestn.do")
	@Secured("ROLE_USER")
	public String registQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO) {

		// 공통코드 질문유형 조회
		cmmUseService.populateCmmCodeList("COM018", "COM018_qestnType");

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnRegist");
	}
	
	/**
	 * 설문문항를 등록한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/insertQustnrQestn.do")
	@Secured("ROLE_USER")
	public String insertQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrQestnManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrQestnManageVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrQestnManageService.insertQustnrQestnManage(qustnrQestnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qqm/listQustnrQestn.do");
	}
	
	/**
	 * 설문문항 수정화면으로 이동한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/editQustnrQestn.do")
	@Secured("ROLE_USER")
	public String editQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO,
			ModelMap model) {

		model.addAttribute(qustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO));

		// 공통코드 질문유형 조회
		cmmUseService.populateCmmCodeList("COM018", "COM018_qestnType");

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnEdit");
	}

	/**
	 * 설문문항를 수정한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/updateQustnrQestn.do")
	@Secured("ROLE_USER")
	public String updateQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrQestnManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrQestnManageVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrQestnManageService.updateQustnrQestnManage(qustnrQestnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qqm/listQustnrQestn.do");
	}

	/**
	 * 설문문항 목록을 삭제 한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/deleteQustnrQestn.do")
	@Secured("ROLE_USER")
	public String deleteQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			ModelMap model) {

		qustnrQestnManageService.deleteQustnrQestnManage(qustnrQestnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qqm/listQustnrQestn.do");
	}

	/**
	 * 설문항목 통계를 조회한다.
	 * 
	 * @param qustnrQestnManageVO
	 */
	@RequestMapping(value = "/uss/olp/qqm/statisticsQustnrQestn.do")
	@Secured("ROLE_USER")
	public String statisticsQustnrQestn(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute QustnrQestnManageVO qustnrQestnManageVO, 
			ModelMap model) {

		model.addAttribute(qustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO));
		
		// 객관식설문통계
		model.addAttribute("statisticsList", qustnrQestnManageService.selectQustnrManageStatistics(qustnrQestnManageVO));
		// 주관식설문통계
		model.addAttribute("statisticsList2", qustnrQestnManageService.selectQustnrManageStatistics2(qustnrQestnManageVO));

		return WebUtil.adjustViewName("/uss/olp/qqm/QustnrQestnStatistics");
	}

}
