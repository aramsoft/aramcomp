package aramframework.com.sym.bat.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.bat.domain.BatchSchdulVO;
import aramframework.com.sym.bat.schedule.BatchScheduler;
import aramframework.com.sym.bat.service.BatchSchdulService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 배치스케줄관리에 대한 controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BatchSchdulController {

	@Autowired
	private BatchSchdulService batchSchdulService;
	
	@Autowired
	private BatchScheduler batchScheduler;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 배치스케줄 목록을 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	@IncludedInfo(name = "배치스케줄관리", order = 6210, gid = 60)
	@RequestMapping("/sym/bat/listBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String listBatchSchdul(
			@ModelAttribute BatchSchdulVO batchSchdulVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		batchSchdulVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", batchSchdulService.selectBatchSchdulList(batchSchdulVO));
		int totCnt = batchSchdulService.selectBatchSchdulListCnt(batchSchdulVO);

		batchSchdulVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/bat/BatchSchdulList");
	}

	/**
	 * 배치스케줄정보을 상세조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/detailBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String detailBatchSchdul(
			BatchSchdulVO batchSchdulVO,
			ModelMap model) {

		model.addAttribute(batchSchdulService.selectBatchSchdul(batchSchdulVO));

		return WebUtil.adjustViewName("/sym/bat/BatchSchdulDetail");
	}

	/**
	 * 등록화면을 위한 배치스케줄정보을 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/registBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String registBatchSchdul(
			@ModelAttribute BatchSchdulVO batchSchdulVO, 
			ModelMap model) {

		referenceData(model);

		return WebUtil.adjustViewName("/sym/bat/BatchSchdulRegist");
	}

	/**
	 * 배치스케줄을 등록한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/insertBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String insertBatchSchdul(
			@ModelAttribute BatchSchdulVO batchSchdulVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(batchSchdulVO, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			return WebUtil.adjustViewName("/sym/bat/BatchSchdulRegist");
		} 
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		batchSchdulVO.setFrstRegisterId(loginVO.getUniqId());

		batchSchdulService.insertBatchSchdul(batchSchdulVO);

		// 배치스케줄러에 스케줄정보반영
		batchSchdulService.selectBatchSchdul(batchSchdulVO);
		batchScheduler.insertBatchSchdul(batchSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sym/bat/listBatchSchdul.do");
	}

	/**
	 * 수정화면을 위한 배치스케줄정보을 조회한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/editBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String editBatchSchdul(
			BatchSchdulVO batchSchdulVO,
			ModelMap model) {

		model.addAttribute(batchSchdulService.selectBatchSchdul(batchSchdulVO));

		referenceData(model);

		return WebUtil.adjustViewName("/sym/bat/BatchSchdulEdit");
	}

	/**
	 * 배치스케줄을 수정한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/updateBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String updateBatchSchdul(
			@ModelAttribute BatchSchdulVO batchSchdulVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(batchSchdulVO, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			return WebUtil.adjustViewName("/sym/bat/BatchSchdulEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		batchSchdulVO.setLastUpdusrId(loginVO.getUniqId());

		batchSchdulService.updateBatchSchdul(batchSchdulVO);

		// 배치스케줄러에 스케줄정보반영
		batchSchdulService.selectBatchSchdul(batchSchdulVO);
		batchScheduler.updateBatchSchdul(batchSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/sym/bat/listBatchSchdul.do");
	}

	/**
	 * 배치스케줄을 삭제한다.
	 * 
	 * @param batchSchdulVO
	 */
	@RequestMapping("/sym/bat/deleteBatchSchdul.do")
	@Secured("ROLE_ADMIN")
	public String deleteBatchSchdul(
			@ModelAttribute BatchSchdulVO batchSchdulVO, 
			ModelMap model) 
	throws Exception {

		// 배치스케줄러에 스케줄정보반영
		batchScheduler.deleteBatchSchdul(batchSchdulVO);

		batchSchdulService.deleteBatchSchdul(batchSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sym/bat/listBatchSchdul.do");
	}

	/**
	 * Reference Data 를 설정한다.
	 * 
	 * @param model
	 */
	private void referenceData(ModelMap model) {

		// DBMS종류코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM047", "COM047_executCycle");
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