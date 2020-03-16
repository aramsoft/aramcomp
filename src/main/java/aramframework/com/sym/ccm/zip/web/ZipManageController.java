package aramframework.com.sym.ccm.zip.web;

import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.ccm.zip.domain.ZipVO;
import aramframework.com.sym.ccm.zip.service.ZipManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 우편번호에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를  정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ZipManageController {

	@Autowired
	private ZipManageService zipManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 우편번호 목록을 조회한다.
	 * 
	 * @param zipVO
	 */
	@IncludedInfo(name = "우편번호관리", order = 6030, gid = 60)
	@RequestMapping(value = "/sym/ccm/zip/listZip.do")
	@Secured("ROLE_USER")
	public String listZip(
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		zipVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", zipManageService.selectZipList(zipVO));
		int totCnt = zipManageService.selectZipListCnt(zipVO);

		zipVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("sym/ccm/zip/ZipList");
	}

	/**
	 * 우편번호 상세항목을 조회한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/detailZip.do")
	public String detailZip(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ZipVO zipVO,
			ModelMap model) {

		model.addAttribute(zipManageService.selectZipDetail(zipVO));

		return WebUtil.adjustViewName("sym/ccm/zip/ZipDetail");
	}

	/**
	 * 우편번호 등록 전처리 화면으로 이동한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/registZip.do")
	@Secured("ROLE_ADMIN")
	public String registZip(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {

		return WebUtil.adjustViewName("sym/ccm/zip/ZipRegist");
	}

	/**
	 * 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/insertZip.do")
	@Secured("ROLE_ADMIN")
	public String insertZip(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ZipVO zipVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		zipVO.setFrstRegisterId(loginVO.getUniqId());

		beanValidator.validate(zipVO, bindingResult);
		if (bindingResult.hasErrors()){
			return WebUtil.adjustViewName("sym/ccm/zip/ZipRegist");
		}
		
		zipManageService.insertZip(zipVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, zipVO, "/sym/ccm/zip/listZip.do");
	}

	/**
	 * 우편번호 등록 전처리 화면으로 이동한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/registZipExcel.do")
	@Secured("ROLE_ADMIN")
	public String registZipExcel(
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {

		return WebUtil.adjustViewName("sym/ccm/zip/ZipExcelRegist");
	}

	/**
	 * 엑셀파일을 업로드하여 우편번호를 등록한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/insertZipExcel.do")
	@Secured("ROLE_ADMIN")
	public String insertExcelZip(
			@ModelAttribute ZipVO zipVO, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		InputStream fis = null; // 2011.11.1 보안점검 후속조치

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			if (!"".equals(file.getOriginalFilename())) {
				// 2011.10.07 업로드 파일에 대한 확장자를 체크
				if (file.getOriginalFilename().endsWith(".xls") 
						|| file.getOriginalFilename().endsWith(".xlsx") 
						|| file.getOriginalFilename().endsWith(".XLS")
						|| file.getOriginalFilename().endsWith(".XLSX")) {

					// zipManageService.deleteAllZip();
					// excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file.getInputStream(), 2);
					// 2011.10.21 보안점검 후속조치
					try {
						fis = file.getInputStream();
//						zipManageService.insertExcelZip(fis);
						zipManageService.insertExcelZipAram(fis);
					} catch (Exception e) {
						throw e;
					} finally {
						if (fis != null)	// 2011.11.1 보안점검 후속조치
							fis.close();					}
				} else {
					model.addAttribute("message", "xls, xlsx 파일 타입만 등록이 가능합니다.");
					return WebUtil.adjustViewName("sym/ccm/zip/ZipExcelRegist");
				}
			}
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, zipVO, "/sym/ccm/zip/listZip.do");
	}

	/**
	 * 우편번호를 수정 전처리 화면으로 이동한다
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/editZip.do")
	@Secured("ROLE_ADMIN")
	public String editZip(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ZipVO zipVO,
			ModelMap model) {

		model.addAttribute(zipManageService.selectZipDetail(zipVO));

		return WebUtil.adjustViewName("sym/ccm/zip/ZipEdit");
	}

	/**
	 * 우편번호를 수정한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/updateZip.do")
	@Secured("ROLE_ADMIN")
	public String updateZip(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute ZipVO zipVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		zipVO.setLastUpdusrId(loginVO.getUniqId());

		beanValidator.validate(zipVO, bindingResult);
		if (bindingResult.hasErrors()){
			return WebUtil.adjustViewName("sym/ccm/zip/ZipEdit");
		}
		
		zipManageService.updateZip(zipVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, zipVO, "/sym/ccm/zip/listZip.do");
	}
	
	/**
	 * 우편번호를 삭제한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/deleteZip.do")
	@Secured("ROLE_ADMIN")
	public String deleteZip(
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {

		zipManageService.deleteZip(zipVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, zipVO, "/sym/ccm/zip/listZip.do");
	}

	/**
	 * 우편번호 찾기 팝업 메인창을 호출한다.
	 * 
	 * @param zipVO
	 */
	@RequestMapping(value = "/sym/ccm/zip/listZipPopup.do")
	public String listZipPopup(
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		zipVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", zipManageService.selectZipList(zipVO));
 		int totCnt = zipManageService.selectZipListCnt(zipVO);

 		zipVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("sym/ccm/zip/ZipSearchPopup");
	}

	/**
	 * 도로명 찾기 팝업 메인창을 호출한다.
	 * 
	 * @param zipVO
	 */

	@RequestMapping(value = "/sym/ccm/zip/listRdNmPopup.do")
	public String listEdNmPopup(
			@ModelAttribute ZipVO zipVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		zipVO.fillPageInfo(paginationInfo);

		Map<String, Object> map = zipManageService.selectRdNmList(zipVO);
		model.addAttribute("resultList", map.get("resultList"));
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		zipVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("sym/ccm/zip/RdNmSearchPopup");
	}
	
}