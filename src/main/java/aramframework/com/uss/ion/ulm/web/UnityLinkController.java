package aramframework.com.uss.ion.ulm.web;

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
import aramframework.com.uss.ion.ulm.domain.UnityLinkVO;
import aramframework.com.uss.ion.ulm.service.UnityLinkService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 통합링크관리를 처리하는 Controller Class 구현
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
public class UnityLinkController {

	@Autowired
	private UnityLinkService unityLinkService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 통합링크관리 목록을 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	@IncludedInfo(name = "통합링크관리", order = 5270, gid = 50)
	@RequestMapping(value = "/uss/ion/ulm/listUnityLink.do")
	@Secured("ROLE_USER")
	public String listUnityLink(
			@ModelAttribute UnityLinkVO unityLinkVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		unityLinkVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", unityLinkService.selectUnityLinkList(unityLinkVO));
		int totCnt = (Integer) unityLinkService.selectUnityLinkListCnt(unityLinkVO);

		unityLinkVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 통합링크구분설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkList");
	}

	/**
	 * 통합링크관리 목록을 상세조회 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/detailUnityLink.do")
	public String detailUnityLink(
			UnityLinkVO unityLinkVO,
			ModelMap model) {

		model.addAttribute(unityLinkService.selectUnityLinkDetail(unityLinkVO));

		// 통합링크구분설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkDetail");
	}

	/**
	 * 통합링크관리 등록화면으로 이동한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/registUnityLink.do")
	public String registUnityLink(
			@ModelAttribute UnityLinkVO unityLinkVO) {

		// 통합링크구분설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkRegist");
	}
	
	/**
	 * 통합링크관리를 등록한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/insertUnityLink.do")
	public String insertUnityLink(
			@ModelAttribute UnityLinkVO unityLinkVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(unityLinkVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		unityLinkVO.setFrstRegisterId(loginVO.getUniqId());

		// 저장
		unityLinkService.insertUnityLink(unityLinkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/ulm/listUnityLink.do");
	}
	
	/**
	 * 통합링크관리 수정화면으로 이동한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/editUnityLink.do")
	public String editUnityLink(
			UnityLinkVO unityLinkVO,
			ModelMap model) {

		model.addAttribute(unityLinkService.selectUnityLinkDetail(unityLinkVO));

		// 통합링크구분설정
		cmmUseService.populateCmmCodeList("COM039", "COM039_pollKind");

		return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkEdit");
	}

	/**
	 * 통합링크관리를 수정한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/updateUnityLink.do")
	public String updateUnityLink(
			@ModelAttribute UnityLinkVO unityLinkVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(unityLinkVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkEdit");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		unityLinkVO.setLastUpdusrId(loginVO.getUniqId());

		// 저장
		unityLinkService.updateUnityLink(unityLinkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ulm/listUnityLink.do");
	}

	/**
	 * 통합링크관리 목록을 삭제한다.
	 * 
	 * @param unityLinkVO
	 */
	@RequestMapping(value = "/uss/ion/ulm/deleteUnityLink.do")
	public String deleteUnityLink(
			@ModelAttribute UnityLinkVO unityLinkVO, 
			ModelMap model) {

		unityLinkService.deleteUnityLink(unityLinkVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/ulm/listUnityLink.do");
	}

	/**
	 * 통합링크관리 메인 셈플 목록을 조회한다.
	 * 
	 * @param unityLinkVO
	 */
	@IncludedInfo(name = "통합링크 결과", order = 5271, gid = 50)
	@RequestMapping(value = "/uss/ion/ulm/listUnityLinkSample.do")
	public String listUnityLinkSample(
			@ModelAttribute UnityLinkVO unityLinkVO, 
			ModelMap model) {

		model.addAttribute("resultList", unityLinkService.selectUnityLinkSample(unityLinkVO));

		return WebUtil.adjustViewName("/uss/ion/ulm/UnityLinkSample");
	}

}
