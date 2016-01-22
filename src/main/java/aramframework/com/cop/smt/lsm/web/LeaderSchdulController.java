package aramframework.com.cop.smt.lsm.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.ComCodeVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.lsm.service.LeaderSchdulService;
import aramframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import aramframework.com.cop.smt.lsm.service.LeaderSttusVO;
import aramframework.com.sym.cal.service.RestdeManageService;
import aramframework.com.sym.cal.service.RestdeVO;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 간부일정에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
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
public class LeaderSchdulController {

	@Autowired 
	private RestdeManageService restdeManageService;

	@Autowired 
	private LeaderSchdulService leaderSchdulService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 간부일정 정보에 대한 목록을 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@IncludedInfo(name = "간부일정관리", order = 4090, gid = 40)
	@RequestMapping(value = "/cop/smt/lsm/listLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String listLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO) {
		
		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulList");
	}

	/**
	 * 일별 간부일정 정보에 대한 목록을 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/listLeaderSchdulDaily.do")
	public String listLeaderSchdulDaily(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			ModelMap model) {

		/* *****************************************************************
		 * // 캘런더 설정 로직
		 * *****************************************************************
		 */
		Calendar calNow = Calendar.getInstance();

		String strYear = leaderSchdulVO.getYear();
		String strMonth = leaderSchdulVO.getMonth();
		String strDay = leaderSchdulVO.getDay();

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if (!StringUtil.isEmpty(strYear)) {
			iNowYear = Integer.parseInt(strYear);
			iNowMonth = Integer.parseInt(strMonth);
			if( !StringUtil.isEmpty(strDay) ) iNowDay = Integer.parseInt(strDay);
		} 
		
		String strSearchDay = "";
		strSearchDay = Integer.toString(iNowYear);
		strSearchDay += WebUtil.dateTypeIntForString(iNowMonth + 1);
		strSearchDay += WebUtil.dateTypeIntForString(iNowDay);

		if (StringUtil.isEmpty(strYear)) {
			leaderSchdulVO.setYear(Integer.toString(iNowYear));
			leaderSchdulVO.setMonth(Integer.toString(iNowMonth));
			leaderSchdulVO.setDay(Integer.toString(iNowDay));
		}

		leaderSchdulVO.setSearchMode("DAILY");
		leaderSchdulVO.setSearchDay(strSearchDay);

		model.addAttribute("resultList", leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO));

		/*
		 * 공통코드 간부일정구분
		 */
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulDailyList");
	}

	/**
	 * 주별 간부일정 정보에 대한 목록을 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/listLeaderSchdulWeek.do")
	public String listLeaderSchdulWeek(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			ModelMap model)  {
		
		/* *****************************************************************
		 * // 캘런더 설정 로직
		 * *****************************************************************
		 */
		Calendar calNow = Calendar.getInstance();
		Calendar calBefore = Calendar.getInstance();
		Calendar calNext = Calendar.getInstance();

		String strYear = leaderSchdulVO.getYear();
		String strMonth = leaderSchdulVO.getMonth();
		String strWeek = leaderSchdulVO.getWeek();
		
		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDate = calNow.get(Calendar.DATE);
		int iNowWeek = calNow.get(java.util.Calendar.WEEK_OF_MONTH)-1;

		if (!StringUtil.isEmpty(strYear)) {
			iNowYear = Integer.parseInt(strYear);
			iNowMonth = Integer.parseInt(strMonth);
			if( !StringUtil.isEmpty(strWeek) ) iNowWeek = Integer.parseInt(strWeek);
		}

		// 년도/월 셋팅
		calNow.set(iNowYear, iNowMonth, 1);
		calBefore.set(iNowYear, iNowMonth, 1);
		calNext.set(iNowYear, iNowMonth, 1);

		calBefore.add(Calendar.MONTH, -1);
		calNext.add(Calendar.MONTH, +1);

		int endDay = calNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startWeek = calNow.get(Calendar.DAY_OF_WEEK);

		ArrayList<ArrayList<String>> listWeekGrop = new ArrayList<ArrayList<String>>();
		ArrayList<String> listWeekDate = new ArrayList<String>();

		String sUseDate = "";

		calBefore.add(Calendar.DATE, calBefore.getActualMaximum(Calendar.DAY_OF_MONTH) - (startWeek - 1));
		for (int i = 1; i < startWeek; i++) {
			sUseDate = Integer.toString(calBefore.get(Calendar.YEAR));
			sUseDate += WebUtil.dateTypeIntForString(calBefore.get(Calendar.MONTH) + 1);
			sUseDate += WebUtil.dateTypeIntForString(calBefore.get(Calendar.DATE));

			listWeekDate.add(sUseDate);
			calBefore.add(Calendar.DATE, +1);
		}

		int iBetweenCount = startWeek;

		// 주별로 자른다. BETWEEN 구하기
		for (int i = 1; i <= endDay; i++) {
			sUseDate = Integer.toString(iNowYear);
			sUseDate += Integer.toString(iNowMonth + 1).length() == 1 ? "0" + Integer.toString(iNowMonth + 1) : Integer.toString(iNowMonth + 1);
			sUseDate += Integer.toString(i).length() == 1 ? "0" + Integer.toString(i) : Integer.toString(i);

			listWeekDate.add(sUseDate);

			if (iBetweenCount % 7 == 0) {
				listWeekGrop.add(listWeekDate);
				listWeekDate = new ArrayList<String>();

				if (strYear == null && i < iNowDate) {
					iNowWeek++;
				}
			}

			// 미지막 7일 자동계산
			if (i == endDay) {

				for (int j = listWeekDate.size(); j < 7; j++) {
					String sUseNextDate = Integer.toString(calNext.get(Calendar.YEAR));
					sUseNextDate += WebUtil.dateTypeIntForString(calNext.get(Calendar.MONTH) + 1);
					sUseNextDate += WebUtil.dateTypeIntForString(calNext.get(Calendar.DATE));
					listWeekDate.add(sUseNextDate);
					calNext.add(Calendar.DATE, +1);
				}

				listWeekGrop.add(listWeekDate);
			}

			iBetweenCount++;
		}

		if (StringUtil.isEmpty(strYear)) {
			leaderSchdulVO.setYear(Integer.toString(iNowYear));
			leaderSchdulVO.setMonth(Integer.toString(iNowMonth));
			leaderSchdulVO.setWeek(Integer.toString(iNowWeek));
		}

		model.addAttribute("listWeekGrop", listWeekGrop);

		List<String> listWeek = (List<String>) listWeekGrop.get(iNowWeek);
		leaderSchdulVO.setSearchMode("WEEK");
		leaderSchdulVO.setSearchBgnde(listWeek.get(0));
		leaderSchdulVO.setSearchEndde(listWeek.get(listWeek.size() - 1));

		model.addAttribute("resultList", leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO));

		/*
		 * 공통코드 간부일정구분
		 */
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulWeekList");
	}

	/**
	 * 월별 간부일정 정보에 대한 목록을 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/listLeaderSchdulMonth.do")
	public String listLeaderSchdulMonth(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String strYear = leaderSchdulVO.getYear();
		String strMonth = leaderSchdulVO.getMonth();

		int iNowYear = cal.get(java.util.Calendar.YEAR);
		int iNowMonth = cal.get(java.util.Calendar.MONTH);

		if ( !StringUtil.isEmpty(strYear) ) {
			iNowYear = Integer.parseInt(strYear);
			iNowMonth = Integer.parseInt(strMonth);
		}
	
		// 검색 설정
		String sSearchDate = "";
		if (StringUtil.isEmpty(strYear) || StringUtil.isEmpty(strMonth)) {
			sSearchDate += Integer.toString(iNowYear);
			sSearchDate += Integer.toString(iNowMonth + 1).length() == 1 ? "0" + Integer.toString(iNowMonth + 1) : Integer.toString(iNowMonth + 1);
		} else {
			iNowYear = Integer.parseInt(strYear);
			iNowMonth = Integer.parseInt(strMonth);
			sSearchDate += strYear;
			sSearchDate += Integer.toString(iNowMonth + 1).length() == 1 ? "0" + Integer.toString(iNowMonth + 1) : Integer.toString(iNowMonth + 1);
		}

		if (StringUtil.isEmpty(strYear)) {
			leaderSchdulVO.setYear(Integer.toString(iNowYear));
			leaderSchdulVO.setMonth(Integer.toString(iNowMonth));
		}

		leaderSchdulVO.setSearchMode("MONTH");
		leaderSchdulVO.setSearchMonth(sSearchDate);

		model.addAttribute("resultList", leaderSchdulService.selectLeaderSchdulList(leaderSchdulVO));

		// 휴일 검색
		RestdeVO restdeVO = new RestdeVO();
		restdeVO.setYear(Integer.toString(iNowYear));
		restdeVO.setMonth(Integer.toString(iNowMonth+1));
		
		model.addAttribute("restdeList", restdeManageService.selectNormalMonthRestde(restdeVO));
		/*
		 * 공통코드 간부일정구분
		 */
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulMonthList");
	}
	
	/**
	 * 간부일정 정보를 조회한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/detailLeaderSchdul.do")
	public String detailLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO) {

		// 공통코드 간부일정구분
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");
		// 공통코드 반복구분 조회
		cmmUseService.populateCmmCodeList("COM058", "COM058_reptitSeLeader");

		leaderSchdulService.selectLeaderSchdul(leaderSchdulVO);

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulDetail");
	}

	// 일정시작일자(시)
	@ModelAttribute("schdulBgndeHH")
	public List<ComCodeVO> schdulBgndeHH() { return WebUtil.getTimeHH();}
	// 일정시작일자(분)
	@ModelAttribute("schdulBgndeMM")
	public List<ComCodeVO> schdulBgndeMM() { return WebUtil.getTimeMM();}
	// 일정종료일자(시)
	@ModelAttribute("schdulEnddeHH")
	public List<ComCodeVO> schdulEnddeHH() { return WebUtil.getTimeHH();}
	// 일정정료일자(분)
	@ModelAttribute("schdulEnddeMM")
	public List<ComCodeVO> schdulEnddeMM() { return WebUtil.getTimeMM();}

	/**
	 * 간부일정 등록을 위한 등록 페이지로 이동한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/registLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String registLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			ModelMap model) {

		// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("schdulChargerId", loginVO.getUniqId());
		model.addAttribute("schdulChargerName", loginVO.getName());

		//  공통코드 간부일정구분
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulRegist");
	}

	/**
	 * 간부일정 정보를 등록한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/insertLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String insertLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(leaderSchdulVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		leaderSchdulVO.setFrstRegisterId(loginVO.getUniqId());

		leaderSchdulService.insertLeaderSchdul(leaderSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return  "forward:/cop/smt/lsm/listLeaderSchdul.do";
	}

	/**
	 * 간부일정 정보를 수정할수 있는 수정폼으로 이동한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/editLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String editLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO) {

		// 공통코드 간부일정구분
		cmmUseService.populateCmmCodeList("COM057", "COM057_schdulSeLeader");

		leaderSchdulService.selectLeaderSchdul(leaderSchdulVO);

		String sSchdulBgnde = leaderSchdulVO.getSchdulBgnde();
		String sSchdulEndde = leaderSchdulVO.getSchdulEndde();

		leaderSchdulVO.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) + "-" + sSchdulBgnde.substring(4, 6) + "-" + sSchdulBgnde.substring(6, 8));
		leaderSchdulVO.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10));
		leaderSchdulVO.setSchdulBgndeMM(sSchdulBgnde.substring(10, 12));

		leaderSchdulVO.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) + "-" + sSchdulEndde.substring(4, 6) + "-" + sSchdulEndde.substring(6, 8));
		leaderSchdulVO.setSchdulEnddeHH(sSchdulEndde.substring(8, 10));
		leaderSchdulVO.setSchdulEnddeMM(sSchdulEndde.substring(10, 12));

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulEdit");
	}

	/**
	 * 간부일정 정보를 수정한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/updateLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String updateLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(leaderSchdulVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSchdulEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		leaderSchdulVO.setLastUpdusrId((String) loginVO.getUniqId());

		leaderSchdulService.updateLeaderSchdul(leaderSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return  "forward:/cop/smt/lsm/listLeaderSchdul.do";
	}

	/**
	 * 간부일정 정보를 삭제한다.
	 * 
	 * @param leaderSchdulVO
	 */
	@RequestMapping(value = "/cop/smt/lsm/deleteLeaderSchdul.do")
	@Secured("ROLE_USER")
	public String deleteLeaderSchdul(
			@ModelAttribute LeaderSchdulVO leaderSchdulVO, 
			ModelMap model) {

		leaderSchdulService.deleteLeaderSchdul(leaderSchdulVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/cop/smt/lsm/listLeaderSchdul.do";
	}

	/**
	 * 간부상태 정보에 대한 목록을 조회한다. (사용자 화면)
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/listLeaderSttusView.do")
	@Secured("ROLE_USER")
	public String listLeaderSttusView(
			@ModelAttribute LeaderSttusVO leaderSttusVO, 
			ModelMap model) {
		
		for (String authority: UserDetailsHelper.getAuthorities()) {
			if ("ROLE_LEADERSCHDUL".equals(authority)) {
				return "forward:/cop/smt/lsm/listLeaderSttus.do";
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		leaderSttusVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", leaderSchdulService.selectLeaderSttusList(leaderSttusVO));

		int totCnt = leaderSchdulService.selectLeaderSttusListCnt(leaderSttusVO);
		leaderSttusVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusListView");
	}

	/**
	 * 간부상태 정보에 대한 목록을 조회한다. (관리자 화면)
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/listLeaderSttus.do")
	public String listLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		leaderSttusVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", leaderSchdulService.selectLeaderSttusList(leaderSttusVO));

		int totCnt = leaderSchdulService.selectLeaderSttusListCnt(leaderSttusVO);
		leaderSttusVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusList");
	}

	/**
	 * 간부상태 정보의 등록화면으로 이동한다.
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/registLeaderSttus.do")
	@Secured("ROLE_USER")
	public String registLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO) {
		
		// 공통코드 간부상태
		cmmUseService.populateCmmCodeList("COM061", "COM061_leaderSttus");

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusRegist");
	}

	/**
	 * 간부상태 정보를 등록한다.
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/insertLeaderSttus.do")
	@Secured("ROLE_USER")
	public String insertLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(leaderSttusVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		leaderSttusVO.setFrstRegisterId(loginVO.getUniqId());

		// 간부상태 중복체크
		if (leaderSchdulService.selectLeaderSttusCheck(leaderSttusVO) > 0) {
			model.addAttribute("leaderIdDuplicated", "true");
			return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusRegist");
		} 
		
		leaderSchdulService.insertLeaderSttus(leaderSttusVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/lsm/listLeaderSttus.do");
	}

	/**
	 * 간부상태 정보의 수정화면으로 이동한다.
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/editLeaderSttus.do")
	@Secured("ROLE_USER")
	public String editLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO) {

		// 공통코드 간부상태
		cmmUseService.populateCmmCodeList("COM061", "COM061_leaderSttus");

		leaderSchdulService.selectLeaderSttus(leaderSttusVO);

		return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusEdit");
	}

	/**
	 * 간부상태 정보를 수정한다.
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/updateLeaderSttus.do")
	@Secured("ROLE_USER")
	public String updateLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(leaderSttusVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/lsm/LeaderSttusEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		leaderSttusVO.setLastUpdusrId(loginVO.getUniqId());

		leaderSchdulService.updateLeaderSttus(leaderSttusVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/lsm/listLeaderSttus.do");
	}

	/**
	 * 간부상태 정보를 삭제한다.
	 * 
	 * @param leaderSttusVO
	 */
	@RequestMapping("/cop/smt/lsm/deleteLeaderSttus.do")
	@Secured("ROLE_USER")
	public String deleteLeaderSttus(
			@ModelAttribute LeaderSttusVO leaderSttusVO, 
			ModelMap model) {

		leaderSchdulService.deleteLeaderSttus(leaderSttusVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/lsm/listLeaderSttus.do");
	}

}