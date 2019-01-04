package aramframework.com.utl.sys.ssy.web;

import java.io.File;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sys.ssy.domain.SynchrnServerVO;
import aramframework.com.utl.sys.ssy.service.SynchrnServerService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 동기화대상 서버관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 동기화대상 서버관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 동기화대상 서버관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SynchrnServerController {

	@Autowired
	private SynchrnServerService synchrnServerService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	final static String uploadDir = AramProperties.getProperty("Globals.SynchrnServerPath");

	// final static String uploadDir = "/product/jeus2/egovProps/tmp/upload/";
	// final static String uploadDir = "D:/ftp/";

	/**
	 * 동기화대상 서버정보를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	@IncludedInfo(name = "파일동기화(대상서버)", order = 9000, gid = 90)
	@RequestMapping(value = "/utl/sys/ssy/listSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String listSynchrnServer(
			@ModelAttribute SynchrnServerVO synchrnServerVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		synchrnServerVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", synchrnServerService.selectSynchrnServerList(synchrnServerVO));
		int totCnt = synchrnServerService.selectSynchrnServerListCnt(synchrnServerVO);

		synchrnServerVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

//		model.addAttribute("fileList", synchrnServerService.getFileName(uploadDir));

		return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerList");
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/detailSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String detailSynchrnServer(
			SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		model.addAttribute(synchrnServerService.selectSynchrnServer(synchrnServerVO));

//		model.addAttribute("fileList", egovSynchrnServerService.selectSynchrnServerFiles(synchrnServerVO));

		return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerDetail");
	}

	/**
	 * 동기화대상 서버정보 등록 화면으로 이동한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/registSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String registSynchrnServer(
			@ModelAttribute SynchrnServerVO synchrnServerVO) {

		return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerRegist");
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/insertSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String insertSynchrnServer(
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(synchrnServerVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		synchrnServerVO.setFrstRegisterId(loginVO.getId());
		if (!synchrnServerVO.getSynchrnLc().endsWith("/")) {
			synchrnServerVO.setSynchrnLc(synchrnServerVO.getSynchrnLc().concat("/"));
		}
		synchrnServerVO.setReflctAt("N");

		synchrnServerService.insertSynchrnServer(synchrnServerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정하는 화면으로 이동한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/editSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String editSynchrnServer(
			SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		model.addAttribute(synchrnServerService.selectSynchrnServer(synchrnServerVO));
		
		return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerEdit");
	}

	/**
	 * 동기화대상 서버정보 수정한다..
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/updateSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String updateSynchrnServer(
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(synchrnServerVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/ssy/SynchrnServerEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		synchrnServerVO.setLastUpdusrId(loginVO.getId());
		if (!synchrnServerVO.getSynchrnLc().endsWith("/")) {
			synchrnServerVO.setSynchrnLc(synchrnServerVO.getSynchrnLc().concat("/"));
		}

		synchrnServerService.updateSynchrnServer(synchrnServerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/deleteSynchrnServer.do")
	@Secured("ROLE_ADMIN")
	public String deleteSynchrnServer(
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		synchrnServerService.deleteSynchrnServer(synchrnServerVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/processSynchrn.do")
	@Secured("ROLE_ADMIN")
	public String processSynchrn(
			@ModelAttribute SynchrnServerVO synchrnServerVO, 
			ModelMap model) {

		synchrnServerVO.setFilePath(uploadDir);
		File uploadFile = new File(uploadDir);
		File[] fileList = uploadFile.listFiles();

		synchrnServerVO.setReflctAt("N");

		synchrnServerService.processSynchrn(synchrnServerVO, fileList);

		/*
		 * for(int i=0; i<fileList.length; i++) { 
		 * 		if(fileList[i].isFile()) {
		 * 			egovSynchrnServerService.processSynchrn(synchrnServerVO, fileList[i]); 
		 * 		} 
		 * }
		 */

	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/deleteSynchrnServerFile.do")
	@Secured("ROLE_ADMIN")
	public String deleteSynchrnServerFile(
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		synchrnServerService.selectSynchrnServer(synchrnServerVO);

		synchrnServerService.deleteSynchrnServerFile(synchrnServerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * 
	 * @param fileNm
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/getSynchrnServerFile.do")
	@Secured("ROLE_ADMIN")
	public String downloadFtpFile(
			@RequestParam String fileNm,
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		synchrnServerService.selectSynchrnServer(synchrnServerVO);

		synchrnServerVO.setFilePath(uploadDir);
		synchrnServerService.downloadFtpFile(synchrnServerVO, fileNm);

	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * 
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/uploadFile.do")
	@Secured("ROLE_ADMIN")
	public String uploadFile(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			ModelMap model) 
	throws Exception {

		MultipartFile multipartFile = multiRequest.getFile("file");
		String fileName = multipartFile.getOriginalFilename();

		synchrnServerService.writeFile(multipartFile, fileName, uploadDir, synchrnServerVO);

	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

	/**
	 * 업로드 파일을 삭제한다.
	 * 
	 * @param deleteFiles
	 * @param synchrnServerVO
	 */
	@RequestMapping(value = "/utl/sys/ssy/deleteFile.do")
	@Secured("ROLE_ADMIN")
	public String deleteFile(
			@RequestParam String deleteFiles, 
			@ModelAttribute SynchrnServerVO synchrnServerVO,
			ModelMap model) {

		synchrnServerVO.setReflctAt("");
		synchrnServerService.deleteFile(deleteFiles, uploadDir, synchrnServerVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/ssy/listSynchrnServer.do");
	}

}