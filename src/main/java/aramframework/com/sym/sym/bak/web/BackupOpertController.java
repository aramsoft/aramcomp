package aramframework.com.sym.sym.bak.web;

import java.util.LinkedHashMap;
import java.util.Map;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.sym.sym.bak.domain.BackupOpertVO;
import aramframework.com.sym.sym.bak.schedule.BackupScheduler;
import aramframework.com.sym.sym.bak.service.BackupOpertService;
import aramframework.com.sym.sym.bak.validation.BackupOpertValidator;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 백업작업관리에 대한 controller 클래스를 정의한다.
 * 
 * 백업작업관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 * 백업작업관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BackupOpertController {

	@Autowired
	private BackupOpertService backupOpertService;

	@Autowired
	private BackupOpertValidator backupOpertValidator;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	@Autowired
	private BackupScheduler backupScheduler;

	/**
	 * 백업작업 목록을 조회한다.
	 * 
	 * @param backupOpertVO
	 */
	@IncludedInfo(name = "백업작업관리", order = 6230, gid = 60)
	@RequestMapping("/sym/sym/bak/listBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String listBackupOpert(
			@ModelAttribute BackupOpertVO backupOpertVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		backupOpertVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", backupOpertService.selectBackupOpertList(backupOpertVO));
		int totCnt = backupOpertService.selectBackupOpertListCnt(backupOpertVO);

		backupOpertVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/sym/bak/BackupOpertList";
	}

	/**
	 * 백업작업정보을 상세조회한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/detailBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String detailBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO,
			ModelMap model) {
		
		model.addAttribute(backupOpertService.selectBackupOpert(backupOpertVO));

		return "com/sym/sym/bak/BackupOpertDetail";
	}

	/**
	 * 백업작업 등록화면으로 이동한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/registBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String registBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO, 
			ModelMap model) {

		referenceData(model);

		return "com/sym/sym/bak/BackupOpertRegist";
	}

	/**
	 * 백업작업을 등록한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/insertBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String insertBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(backupOpertVO, bindingResult);
		backupOpertValidator.validate(backupOpertVO, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			return "com/sym/sym/bak/BackupOpertRegist";
		} 
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		backupOpertVO.setFrstRegisterId(loginVO.getUserId());

		backupOpertService.insertBackupOpert(backupOpertVO);

		// 배치스케줄러에 스케줄정보반영
		backupOpertService.selectBackupOpert(backupOpertVO);
		backupScheduler.insertBackupOpert(backupOpertVO, false);

		// Exception 없이 진행시 등록성공메시지
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sym/sym/bak/listBackupOpert.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 백업작업 수정화면으로 이동한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/editBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String editBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO,
			ModelMap model) {
		
		model.addAttribute(backupOpertService.selectBackupOpert(backupOpertVO));
	
		referenceData(model);

		return "com/sym/sym/bak/BackupOpertEdit";
	}

	/**
	 * 백업작업을 수정한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/updateBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String updateBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(backupOpertVO, bindingResult);
		backupOpertValidator.validate(backupOpertVO, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			return "com/sym/sym/bak/BackupOpertEdit";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		backupOpertVO.setLastUpdusrId(loginVO.getUserId());

		backupOpertService.updateBackupOpert(backupOpertVO);

		// 백업스케줄러에 스케줄정보반영
		backupOpertService.selectBackupOpert(backupOpertVO);
		backupScheduler.insertBackupOpert(backupOpertVO, true);		// 갱신시 true

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sym/sym/bak/listBackupOpert.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 백업작업을 삭제한다.
	 * 
	 * @param backupOpertVO
	 */
	@RequestMapping("/sym/sym/bak/deleteBackupOpert.do")
	@Secured("ROLE_ADMIN")
	public String deleteBackupOpert(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupOpertVO backupOpertVO, 
			ModelMap model) {

		// 백업스케줄러에 스케줄정보반영
		backupScheduler.deleteBackupOpert(backupOpertVO);
		backupOpertService.deleteBackupOpert(backupOpertVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/sym/bak/listBackupOpert.do");
	    return "com/cmm/redirect";
	}

	/**
	 * Reference Data 를 설정한다.
	 * 
	 * @param model
	 */
	private void referenceData(ModelMap model) {

		// 실행주기구분 코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM047", "COM047_executCycle");
		// 압축구분코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM049", "COM049_cmprsSe");
		// 요일구분코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM074", "COM074_executSchdulDfkSe");

		// 실행스케줄 시, 분, 초 값 설정.
		Map<String, String> executSchdulHourList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				executSchdulHourList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulHourList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulHourList", executSchdulHourList);

		Map<String, String> executSchdulMntList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				executSchdulMntList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulMntList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulMntList", executSchdulMntList);

		Map<String, String> executSchdulSecndList = new LinkedHashMap<String, String>();
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				executSchdulSecndList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
			} else {
				executSchdulSecndList.put(Integer.toString(i), Integer.toString(i));
			}
		}
		model.addAttribute("executSchdulSecndList", executSchdulSecndList);
	}

}