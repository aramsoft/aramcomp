package aramframework.com.uss.ion.nws.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.nws.domain.NewsManageVO;
import aramframework.com.uss.ion.nws.service.NewsManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 뉴스정보를 처리하는 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NewsManageController {

	@Autowired
	private NewsManageService newsManageService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 뉴스정보 목록을 조회한다.
	 * 
	 * @param newsManageVO
	 */
	@IncludedInfo(name = "뉴스관리", order = 5170, gid = 50)
	@RequestMapping(value = "/uss/ion/nws/listNewsInfo.do")
	public String listNewsInfo(
			@ModelAttribute NewsManageVO newsManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		newsManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", newsManageService.selectNewsList(newsManageVO));
		int totCnt = newsManageService.selectNewsListCnt(newsManageVO);

		newsManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoList");
	}

	/**
	 * 뉴스정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/detailNewsInfo.do")
	public String detailNewsInfo(
			NewsManageVO newsManageVO,
			ModelMap model) {

		model.addAttribute(newsManageService.selectNewsDetail(newsManageVO));

		return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoDetail");
	}

	/**
	 * 뉴스정보를 등록 전 단계처리
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/registNewsInfo.do")
	@Secured("ROLE_USER")
	public String registNewsInfo(
			@ModelAttribute NewsManageVO newsManageVO) {

		return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoRegist");
	}

	/**
	 * 뉴스정보를 등록한다.
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/insertNewsInfo.do")
	@Secured("ROLE_USER")
	public String insertNewsInfo(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute NewsManageVO newsManageVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(newsManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		newsManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "NEWS_"));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		newsManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		newsManageService.insertNewsInfo(newsManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/nws/listNewsInfo.do");
	}

	/**
	 * 뉴스정보를 수정하기 전 단계처리
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/editNewsInfo.do")
	@Secured("ROLE_USER")
	public String editNewsInfo(
			NewsManageVO newsManageVO,
			ModelMap model) {

		model.addAttribute(newsManageService.selectNewsDetail(newsManageVO));

		return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoEdit");
	}

	/**
	 * 뉴스정보를 수정 처리한다
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/updateNewsInfo.do")
	@Secured("ROLE_USER")
	public String updateNewsInfo(
			MultipartHttpServletRequest multiRequest,
			@ModelAttribute NewsManageVO newsManageVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		// Validation
		beanValidator.validate(newsManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/nws/NewsInfoEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = newsManageVO.getAtchFileId();
		newsManageVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "NEWS_", atchFileId));

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		newsManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		newsManageService.updateNewsInfo(newsManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/nws/listNewsInfo.do");
	}

	/**
	 * 뉴스정보를 삭제한다.
	 * 
	 * @param newsManageVO
	 */
	@RequestMapping("/uss/ion/nws/deleteNewsInfo.do")
	@Secured("ROLE_USER")
	public String deleteNewsInfo(
			@ModelAttribute NewsManageVO newsManageVO, 
			ModelMap model) {

		newsManageService.deleteNewsInfo(newsManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/nws/listNewsInfo.do");
	}

}
