package aramframework.com.uss.ion.sit.web;

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
import aramframework.com.uss.ion.sit.service.SiteManageService;
import aramframework.com.uss.ion.sit.service.SiteManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 사이트정보를 처리하는 Controller 클래스
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
public class SiteManageController {

	@Autowired
	private SiteManageService siteManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사이트목록을 조회한다.
	 * 
	 * @param siteManageVO
	 */
	@IncludedInfo(name = "사이트관리", order = 5180, gid = 50)
	@RequestMapping(value = "/uss/ion/sit/listSiteInfo.do")
	@Secured("ROLE_USER")
	public String listSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		siteManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", siteManageService.selectSiteList(siteManageVO));

		int totCnt = siteManageService.selectSiteListCnt(siteManageVO);
		siteManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoList");
	}

	/**
	 * 사이트정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/detailSiteInfo.do")
	public String detailSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO) {

		siteManageService.selectSiteDetail(siteManageVO);

		return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoDetail");
	}

	/**
	 * 사이트정보 등록전 단계
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/registSiteInfo.do")
	public String registSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO) {

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM023", "COM023_siteThema");

		return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoRegist");
	}

	/**
	 * 사이트정보를 등록한다.
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/insertSiteInfo.do")
	public String insertSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(siteManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		siteManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		siteManageService.insertSiteInfo(siteManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/sit/listSiteInfo.do");
	}

	/**
	 * 사이트정보 수정 전 처리
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/editSiteInfo.do")
	public String editSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO) {

		siteManageService.selectSiteDetail(siteManageVO);

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM023", "COM023_siteThema");

		return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoEdit");
	}

	/**
	 * 사이트정보를 수정한다.
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/updateSiteInfo.do")
	public String updateSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// Validation
		beanValidator.validate(siteManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/sit/SiteInfoEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		siteManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		siteManageService.updateSiteInfo(siteManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/sit/listSiteInfo.do");
	}

	/**
	 * 사이트정보를 삭제처리한다.
	 * 
	 * @param siteManageVO
	 */
	@RequestMapping("/uss/ion/sit/deleteSiteInfo.do")
	public String deleteSiteInfo(
			@ModelAttribute SiteManageVO siteManageVO, 
			BindingResult bindingResult,
			ModelMap model) {

		siteManageService.deleteSiteInfo(siteManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/sit/listSiteInfo.do");
	}

}
