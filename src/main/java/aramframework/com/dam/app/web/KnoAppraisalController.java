package aramframework.com.dam.app.web;

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
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.app.service.KnoAppraisalService;
import aramframework.com.dam.app.service.KnoAppraisalVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 지식정보평가에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 지식정보평가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 지식정보평가의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class KnoAppraisalController {

	@Resource(name = "knoAppraisalService")
	private KnoAppraisalService knoAppraisalService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	@IncludedInfo(name = "지식평가관리", order = 8040, gid = 80)
	@RequestMapping(value = "/dam/app/listKnoAppraisal.do")
	@Secured("ROLE_USER")
	public String listKnoAppraisal(
			@ModelAttribute KnoAppraisalVO knoAppraisalVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoAppraisalVO.setEmplyrId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		knoAppraisalVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", knoAppraisalService.selectKnoAppraisalList(knoAppraisalVO));

		int totCnt = knoAppraisalService.selectKnoAppraisalListCnt(knoAppraisalVO);
		knoAppraisalVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/dam/app/KnoAppraisalList");
	}

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	@RequestMapping(value = "/dam/app/detailKnoAppraisal.do")
	public String detailKnoAppraisal(
			@ModelAttribute KnoAppraisalVO knoAppraisalVO) {

		knoAppraisalService.selectKnoAppraisal(knoAppraisalVO);

		return WebUtil.adjustViewName("/dam/app/KnoAppraisalDetail");
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 화면으로 이동한다.
	 * 
	 * @param knoAppraisalVO
	 */
	@RequestMapping(value = "/dam/app/editKnoAppraisal.do")
	public String editKnoAppraisal(
			@ModelAttribute KnoAppraisalVO knoAppraisalVO) {

		knoAppraisalService.selectKnoAppraisal(knoAppraisalVO);

		return WebUtil.adjustViewName("/dam/app/KnoAppraisalEdit");
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * 
	 * @param knoAppraisalVO
	 */
	@RequestMapping(value = "/dam/app/updateKnoAppraisal.do")
	public String updateKnoAppraisal(
			@ModelAttribute KnoAppraisalVO knoAppraisalVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(knoAppraisalVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/app/KnoAppraisalEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoAppraisalVO.setLastUpdusrId(loginVO.getUniqId());
		knoAppraisalVO.setSpeId(loginVO.getUniqId());

		knoAppraisalService.updateKnoAppraisal(knoAppraisalVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/app/listKnoAppraisal.do");
	}

}