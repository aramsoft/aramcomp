package aramframework.com.uss.ion.lsi.web;

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
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.lsi.domain.LoginScrinImageVO;
import aramframework.com.uss.ion.lsi.service.LoginScrinImageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 인터넷서비스안내에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class LoginScrinImageController {

	@Autowired
	private LoginScrinImageService loginScrinImageService;

	@Autowired
	private FileMngService fileMngService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@IncludedInfo(name = "로그인화면이미지관리", order = 5240, gid = 50)
	@RequestMapping(value = "/uss/ion/lsi/listLoginScrinImage.do")
	@Secured("ROLE_USER")
	public String listLoginScrinImage(
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		loginScrinImageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", loginScrinImageService.selectLoginScrinImageList(loginScrinImageVO));

		int totCnt = loginScrinImageService.selectLoginScrinImageListCnt(loginScrinImageVO);
		loginScrinImageVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageList");
	}

	/**
	 * 로그인화면이미지 등록 화면으로 이동한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/registLoginScrinImage.do")
	public String registLoginScrinImage(
			@ModelAttribute LoginScrinImageVO loginScrinImageVO) {

		return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageRegist");
	}

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/insertLoginScrinImage.do")
	public String insertLoginScrinImage(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			BindingResult bindingResult, 
			ModelMap model)
	throws Exception {

		beanValidator.validate(loginScrinImageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageRegist");
		} 

		String atchFileId = "";
		String image = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "LSI_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				image = fileVo.getOrignlFileNm();
			}
		}
		loginScrinImageVO.setImageFile(atchFileId);
		loginScrinImageVO.setImage(image);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginScrinImageVO.setUserId(loginVO.getId());

		loginScrinImageService.insertLoginScrinImage(loginScrinImageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/lsi/listLoginScrinImage.do");
	}

	/**
	 * 등록된 로그인화면이미지의 수정화면으로 이동한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/editLoginScrinImage.do")
	public String editLoginScrinImage(
			@ModelAttribute LoginScrinImageVO loginScrinImageVO) {

		loginScrinImageService.selectLoginScrinImage(loginScrinImageVO);

		return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageEdit");
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/updateLoginScrinImage.do")
	public String updateLoginScrinImage(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(loginScrinImageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageEdit");
		} 

		String atchFileId = "";
		String image = "";

		Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "LSI_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);

			for(FileVO fileVo : result) {
				image = fileVo.getOrignlFileNm();
			}

			if (image.equals("")) {
				loginScrinImageVO.setAtchFile(false);
			} else {
				loginScrinImageVO.setImage(image);
				loginScrinImageVO.setImageFile(atchFileId);
				loginScrinImageVO.setAtchFile(true);
			}
		} else {
			loginScrinImageVO.setAtchFile(false);
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginScrinImageVO.setUserId(loginVO.getId());

		loginScrinImageService.updateLoginScrinImage(loginScrinImageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/lsi/listLoginScrinImage.do");
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/deleteLoginScrinImage.do")
	public String deleteLoginScrinImage(
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			ModelMap model) {

		loginScrinImageService.deleteLoginScrinImage(loginScrinImageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/lsi/listLoginScrinImage.do");
	}

	/**
	 * 기 등록된 로그인화면이미지정보 목록을 일괄 삭제한다.
	 * 
	 * @param imageIds
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/deleteListLoginScrinImage.do")
	public String deleteListLoginScrinImage(
			@RequestParam String imageIds,
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			ModelMap model) {

		String[] strImageIds = imageIds.split(";");
		LoginScrinImageVO lsiVO = new LoginScrinImageVO();
		for (int i = 0; i < strImageIds.length; i++) {
			lsiVO.setImageId(strImageIds[i]);
			loginScrinImageService.deleteLoginScrinImage(lsiVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/lsi/listLoginScrinImage.do");
	}

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	@RequestMapping(value = "/uss/ion/lsi/viewLoginScrinImage.do")
	public String viewLoginScrinImage(
			@ModelAttribute LoginScrinImageVO loginScrinImageVO, 
			ModelMap model) {

		model.addAttribute("fileList", loginScrinImageService.selectLoginScrinImageResult(loginScrinImageVO));

		return WebUtil.adjustViewName("/uss/ion/lsi/LoginScrinImageView");
	}
	
}
