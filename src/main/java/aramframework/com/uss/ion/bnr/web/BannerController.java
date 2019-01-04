package aramframework.com.uss.ion.bnr.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.bnr.domain.BannerVO;
import aramframework.com.uss.ion.bnr.service.BannerService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 배너에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@Autowired
	private FileMngService fileMngService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * 
	 * @param bannerVO
	 */
	@IncludedInfo(name = "배너관리", order = 5230, gid = 50)
	@RequestMapping(value = "/uss/ion/bnr/listBanner.do")
	@Secured("ROLE_USER")
	public String listBanner(
			@ModelAttribute BannerVO bannerVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		bannerVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bannerService.selectBannerList(bannerVO));
		int totCnt = bannerService.selectBannerListCnt(bannerVO);

		bannerVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/bnr/BannerList");
	}

	/**
	 * 배너등록 화면으로 이동한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/registBanner.do")
	public String registBanner(
			@ModelAttribute BannerVO bannerVO) {

		return WebUtil.adjustViewName("/uss/ion/bnr/BannerRegist");
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/insertBanner.do")
	public String insertBanner(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BannerVO bannerVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(bannerVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnr/BannerRegist");
		} 
		
		String atchFileId = "";
		String bannerImage = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "BNR_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				bannerImage = fileVo.getOrignlFileNm();
			}
		}
		bannerVO.setBannerImageFile(atchFileId);
		bannerVO.setBannerImage(bannerImage);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bannerVO.setUserId(loginVO.getId());

		bannerService.insertBanner(bannerVO);
	
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnr/listBanner.do");
	}

	/**
	 * 배너수정 화면으로 이동한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/editBanner.do")
	public String editBanner(
			BannerVO bannerVO,
			ModelMap model) {

		model.addAttribute(bannerService.selectBanner(bannerVO));
		
		return WebUtil.adjustViewName("/uss/ion/bnr/BannerEdit");
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/updateBanner.do")
	public String updateBanne(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BannerVO bannerVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {
		
		beanValidator.validate(bannerVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnr/BannerEdit");
		} 

		String atchFileId = "";
		String bannerImage = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "BNR_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				bannerImage = fileVo.getOrignlFileNm();
			}

			if (bannerImage.equals("")) {
				bannerVO.setAtchFile(false);
			} else {
				bannerVO.setBannerImage(bannerImage);
				bannerVO.setBannerImageFile(atchFileId);
				bannerVO.setAtchFile(true);
			}
		} else {
			bannerVO.setAtchFile(false);
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bannerVO.setUserId(loginVO.getId());

		bannerService.updateBanner(bannerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnr/listBanner.do");
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/deleteBanner.do")
	public String deleteBanner(
			@ModelAttribute BannerVO bannerVO, 
			ModelMap model) {

		bannerService.deleteBanner(bannerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnr/listBanner.do");
	}

	/**
	 * 기 등록된 배너정보목록을 일괄 삭제한다.
	 * 
	 * @param bannerIds
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/deleteBannerList.do")
	public String deleteBannerList(
			@RequestParam String bannerIds, 
			@ModelAttribute BannerVO bannerVO, 
			ModelMap model) {

		bannerService.deleteBanners(bannerIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnr/listBanner.do");
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param bannerVO
	 */
	@RequestMapping(value = "/uss/ion/bnr/getBannerImage.do")
	public String getBannerImage(
			@ModelAttribute BannerVO bannerVO, 
			ModelMap model) {

		model.addAttribute("fileList", bannerService.selectBannerResult(bannerVO));
		model.addAttribute("resultType", bannerVO.getResultType());

		return WebUtil.adjustViewName("/uss/ion/bnr/BannerView");
	}

	/**
	 * MyPage에 배너정보를 제공하기 위해 목록을 조회한다.
	 * 
	 * @param bannerVO
	 */
	@IncludedInfo(name = "MYPAGE배너관리", order = 5231, gid = 50)
	@RequestMapping(value = "/uss/ion/bnr/listBannerMainPage.do")
	public String listBannerMainPage(
			@ModelAttribute BannerVO bannerVO, 
			ModelMap model) {

		bannerVO.setSizeAndOffset(5, 0);

		model.addAttribute("bannerList", bannerService.selectBannerList(bannerVO));

		return WebUtil.adjustViewName("/uss/ion/bnr/BannerMainPage");
	}
	
}
