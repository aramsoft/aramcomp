package aramframework.com.uss.ion.rec.web;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.rec.domain.RecomendSiteVO;
import aramframework.com.uss.ion.rec.service.RecomendSiteService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 추천사이트처리를 하는 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class RecomendSiteController {

	@Autowired
	private RecomendSiteService recomendSiteService;	

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 추천사이트정보 목록을 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	@IncludedInfo(name = "추천사이트관리", order = 5190, gid = 50)
	@RequestMapping(value = "/uss/ion/rec/listRecomendSite.do")
	public String listRecomendSite(
			@ModelAttribute RecomendSiteVO recomendSiteVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		recomendSiteVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", recomendSiteService.selectRecomendSiteList(recomendSiteVO));
		int totCnt = recomendSiteService.selectRecomendSiteListCnt(recomendSiteVO);

		recomendSiteVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteList");
	}

	/**
	 * 추천사이트정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/detailRecomendSite.do")
	public String detailRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO,
			ModelMap model) {

		model.addAttribute(recomendSiteService.selectRecomendSiteDetail(recomendSiteVO));

		return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteDetail");
	}

	/**
	 * 추천사이트정보를 등록하기 전 처리
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/registRecomendSite.do")
	@Secured("ROLE_USER")
	public String registRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO) {

		return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteRegist");
	}

	/**
	 * 추천사이트정보를 등록한다.
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/insertRecomendSite.do")
	@Secured("ROLE_USER")
	public String insertRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(recomendSiteVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		recomendSiteVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		recomendSiteService.insertRecomendSiteInfo(recomendSiteVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/rec/listRecomendSite.do");
	}

	/**
	 * 추천사이트정보를 수정하기 전 처리
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/editRecomendSite.do")
	@Secured("ROLE_USER")
	public String editRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO,
			ModelMap model) {

		model.addAttribute(recomendSiteService.selectRecomendSiteDetail(recomendSiteVO));

		return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteEdit");
	}

	/**
	 * 추천사이트정보를 수정처리한다.
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/updateRecomendSite.do")
	@Secured("ROLE_USER")
	public String updateRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// Validation
		beanValidator.validate(recomendSiteVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/rec/RecomendSiteEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		recomendSiteVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		recomendSiteService.updateRecomendSiteInfo(recomendSiteVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/rec/listRecomendSite.do");
	}

	/**
	 * 추천사이트정보를 삭제처리한다.
	 * 
	 * @param recomendSiteVO
	 */
	@RequestMapping("/uss/ion/rec/deleteRecomendSite.do")
	@Secured("ROLE_USER")
	public String deleteRecomendSite(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute RecomendSiteVO recomendSiteVO, 
			ModelMap model) {

		recomendSiteService.deleteRecomendSiteInfo(recomendSiteVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/rec/listRecomendSite.do");
	}

}
