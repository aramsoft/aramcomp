package aramframework.com.uss.olp.qrm.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageService;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문응답자관리 Controller Class 구현
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
public class QustnrRespondManageController {

	@Resource(name = "qustnrRespondManageService")
	private QustnrRespondManageService qustnrRespondManageService;

	@Resource(name = "cmmUseService")
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 응답자정보 목록을 조회한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@IncludedInfo(name = "응답자관리", order = 5145, gid = 50)
	@RequestMapping(value = "/uss/olp/qrm/listQustnrRespond.do")
	@Secured("ROLE_USER")
	public String listQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, 
			ModelMap model) {

		// 설문지정보에서 넘어오면 자동검색 설정
		if (qustnrRespondManageVO.getSearchMode().equals("Y")) {
			qustnrRespondManageVO.setSearchCondition("QESTNR_ID");
			qustnrRespondManageVO.setSearchKeyword(qustnrRespondManageVO.getQestnrId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		qustnrRespondManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", qustnrRespondManageService.selectQustnrRespondManageList(qustnrRespondManageVO));

		int totCnt = (Integer) qustnrRespondManageService.selectQustnrRespondManageListCnt(qustnrRespondManageVO);
		qustnrRespondManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondList");
	}

	/**
	 * 응답자정보 목록을 상세조회 한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/detailQustnrRespond.do")
	@Secured("ROLE_USER")
	public String detailQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO) {

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		qustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);

		return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondDetail");
	}

	/**
	 * 응답자정보 등록화면으로 이동한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/registQustnrRespond.do")
	@Secured("ROLE_USER")
	public String registQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO) {

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondRegist");
	}

	/**
	 * 응답자정보를 등록한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/insertQustnrRespond.do")
	@Secured("ROLE_USER")
	public String insertQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondRegist");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrRespondManageVO.setFrstRegisterId(loginVO.getUniqId());

		qustnrRespondManageService.insertQustnrRespondManage(qustnrRespondManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qrm/listQustnrRespond.do");
	}

	/**
	 * 응답자정보 수정화면으로 이동한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/editQustnrRespond.do")
	@Secured("ROLE_USER")
	public String editQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO)  {

		// 성별코드조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");
		// 직업코드조회
		cmmUseService.populateCmmCodeList("COM034", "COM034_occpType");

		qustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);

		return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondEdit");
	}

	/**
	 * 응답자정보를 수정한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/updateQustnrRespond.do")
	@Secured("ROLE_USER")
	public String updateQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olp/qrm/QustnrRespondEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		qustnrRespondManageVO.setLastUpdusrId(loginVO.getUniqId());

		qustnrRespondManageService.updateQustnrRespondManage(qustnrRespondManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qrm/listQustnrRespond.do");
	}

	/**
	 * 응답자정보 목록을 삭제한다.
	 * 
	 * @param qustnrRespondManageVO
	 */
	@RequestMapping(value = "/uss/olp/qrm/deleteQustnrRespond.do")
	@Secured("ROLE_USER")
	public String deleteQustnrRespond(
			@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, 
			ModelMap model)  {

		qustnrRespondManageService.deleteQustnrRespondManage(qustnrRespondManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/olp/qrm/listQustnrRespond.do");
	}

}
