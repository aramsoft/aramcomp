package aramframework.com.uss.ion.msi.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.service.FileVO;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.msi.service.MainImageService;
import aramframework.com.uss.ion.msi.service.MainImageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 메인화면이미지에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class MainImageController {

	@Resource(name = "mainImageService")
	private MainImageService mainImageService;

	@Resource(name = "fileMngService")
	private FileMngService fileMngService;

	@Resource(name = "fileMngUtil")
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * 
	 * @param mainImageVO
	 */
	@IncludedInfo(name = "메인이미지관리", order = 5250, gid = 50)
	@RequestMapping("/uss/ion/msi/listMainImage.do")
	@Secured("ROLE_USER")
	public String listMainImage(
			@ModelAttribute MainImageVO mainImageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		mainImageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", mainImageService.selectMainImageList(mainImageVO));

		int totCnt = mainImageService.selectLoginScrinImageListCnt(mainImageVO);
		mainImageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/msi/MainImageList");
	}

	/**
	 * 메인인화면이미지 등록 화면으로 이동한다.
	 * 
	 * @param mainImageVO
	 */
	@RequestMapping(value = "/uss/ion/msi/registMainImage.do")
	public String registMainImage(
			@ModelAttribute MainImageVO mainImageVO) {
		
		return WebUtil.adjustViewName("/uss/ion/msi/MainImageRegist");
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param mainImageVO
	 */
	@RequestMapping(value = "/uss/ion/msi/insertMainImage.do")
	public String insertMainImage(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute MainImageVO mainImageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(mainImageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/msi/MainImageRegist");
		} 

		String atchFileId = "";
		String image = "";

		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "MSI_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				image = fileVo.getOrignlFileNm();
			}
		}
		mainImageVO.setImageFile(atchFileId);
		mainImageVO.setImage(image);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mainImageVO.setUserId(loginVO.getId());

		mainImageService.insertMainImage(mainImageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/msi/listMainImage.do");
	}

	/**
	 * 등록된 메인화면이미지의 수정화면으로 이동한다.
	 * 
	 * @param mainImageVO
	 */
	@RequestMapping(value = "/uss/ion/msi/editMainImage.do")
	public String editMainImage(
			@ModelAttribute MainImageVO mainImageVO) {

		mainImageService.selectMainImage(mainImageVO);

		return WebUtil.adjustViewName("/uss/ion/msi/MainImageEdit");
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * 
	 * @param mainImageVO
	 */
	@RequestMapping(value = "/uss/ion/msi/updateMainImage.do")
	public String updateMainImage(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute MainImageVO mainImageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(mainImageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/msi/MainImageEdit");
		}

		String atchFileId = "";
		String image = "";

		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "MSI_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				image = fileVo.getOrignlFileNm();
			}

			if (image.equals("")) {
				mainImageVO.setAtchFile(false);
			} else {
				mainImageVO.setImage(image);
				mainImageVO.setImageFile(atchFileId);
				mainImageVO.setAtchFile(true);
			}
		} else {
			mainImageVO.setAtchFile(false);
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mainImageVO.setUserId(loginVO.getId());

		mainImageService.updateMainImage(mainImageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/msi/listMainImage.do");
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * 
	 * @param mainImageVO
	 */
	@RequestMapping(value = "/uss/ion/msi/deleteMainImage.do")
	public String deleteMainImage(
			@ModelAttribute MainImageVO mainImageVO, 
			ModelMap model) {

		mainImageService.deleteMainImage(mainImageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/msi/listMainImage.do");
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * 
	 * @param imageIds
	 */
	@RequestMapping(value = "/uss/ion/msi/deleteListMainImage.do")
	public String deleteListMainImage(
			@RequestParam String imageIds, 
			ModelMap model) {

		String[] strImageIds = imageIds.split(";");

		MainImageVO mainImageVO = new MainImageVO();
		for (int i = 0; i < strImageIds.length; i++) {
			mainImageVO.setImageId(strImageIds[i]);
			mainImageService.deleteMainImage(mainImageVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/msi/listMainImage.do");
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param mainImageVO
	 */
	@IncludedInfo(name = "메인이미지 반영결과보기", order = 5251, gid = 50)
	@RequestMapping(value = "/uss/ion/msi/viewMainImage.do")
	public String viewMainImage(
			@ModelAttribute MainImageVO mainImageVO, 
			ModelMap model) {

		model.addAttribute("fileList", mainImageService.selectMainImageResult(mainImageVO));

		return WebUtil.adjustViewName("/uss/ion/msi/MainImageView");
	}
	
}
