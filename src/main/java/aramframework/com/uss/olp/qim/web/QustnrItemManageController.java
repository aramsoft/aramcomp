package aramframework.com.uss.olp.qim.web;

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
import aramframework.com.uss.olp.qim.domain.QustnrItemManageVO;
import aramframework.com.uss.olp.qim.service.QustnrItemManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문항목관리를 처리하는 Controller Class 구현
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
public class QustnrItemManageController {

	@Autowired
	private QustnrItemManageService qustnrItemManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 설문항목 팝업 목록을 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/listQustnrItemPopup.do")
	public String listQustnrItemPopup(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrItemManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrItemManageService.selectQustnrItemManageList(qustnrItemManageVO));
		int totCnt = (Integer) qustnrItemManageService.selectQustnrItemManageListCnt(qustnrItemManageVO);

		qustnrItemManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemListPopup");
	}

	/**
	 * 설문항목 목록을 조회한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@IncludedInfo(name = "항목관리", order = 5143, gid = 50)
	@RequestMapping(value = "/uss/olp/qim/listQustnrItem.do")
	@Secured("ROLE_USER")
	public String listQustnrItem(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO, 
			ModelMap model) {

		// 설문문항에 넘어온 건에 대해 조회
		if (qustnrItemManageVO.getSearchMode().equals("Y")) {
			qustnrItemManageVO.getSearchVO().setSearchCondition("QUSTNR_QESITM_ID");// qestnrQesitmId
			qustnrItemManageVO.getSearchVO().setSearchKeyword(qustnrItemManageVO.getQestnrQesitmId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrItemManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrItemManageService.selectQustnrItemManageList(qustnrItemManageVO));
		int totCnt = (Integer) qustnrItemManageService.selectQustnrItemManageListCnt(qustnrItemManageVO);

		qustnrItemManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemList");
	}

	/**
	 * 설문항목 목록을 상세조회 한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/detailQustnrItem.do")
	@Secured("ROLE_USER")
	public String detailQustnrItem(
			QustnrItemManageVO qustnrItemManageVO,
			ModelMap model) {

		model.addAttribute(qustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO));

		return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemDetail");
	}

	/**
	 * 설문항목 등록화면으로 이동한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/registQustnrItem.do")
	@Secured("ROLE_USER")
	public String registQustnrItem(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO) {

		return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemRegist");
	}

	/**
	 * 설문항목를 등록한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/insertQustnrItem.do")
	@Secured("ROLE_USER")
	public String insertQustnrItem(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrItemManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrItemManageVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrItemManageService.insertQustnrItemManage(qustnrItemManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qim/listQustnrItem.do");
	}

	/**
	 * 설문항목 수정화면으로 이동한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/editQustnrItem.do")
	@Secured("ROLE_USER")
	public String editQustnrItem(
			QustnrItemManageVO qustnrItemManageVO,
			ModelMap model) {

		model.addAttribute(qustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO));

		return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemEdit");
	}

	/**
	 * 설문항목를 수정한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/updateQustnrItem.do")
	@Secured("ROLE_USER")
	public String updateQustnrItem(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrItemManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qim/QustnrItemEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrItemManageVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrItemManageService.updateQustnrItemManage(qustnrItemManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qim/listQustnrItem.do");
	}

	/**
	 * 설문항목 목록을 삭제한다.
	 * 
	 * @param qustnrItemManageVO
	 */
	@RequestMapping(value = "/uss/olp/qim/deleteQustnrItem.do")
	@Secured("ROLE_USER")
	public String deleteQustnrItem(
			@ModelAttribute QustnrItemManageVO qustnrItemManageVO, 
			ModelMap model) {

		qustnrItemManageService.deleteQustnrItemManage(qustnrItemManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qim/listQustnrItem.do");
	}

}
