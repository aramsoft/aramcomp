package aramframework.com.cop.smt.sim.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.ComponentChecker;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.sim.domain.SchdulManageVO;
import aramframework.com.cop.smt.sim.service.SchdulManageService;
import aramframework.com.sym.cal.domain.RestdeVO;
import aramframework.com.sym.cal.service.RestdeManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일정관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SchdulManageController {

	@Autowired 
	private RestdeManageService restdeManageService;

	@Autowired 
	private SchdulManageService schdulManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사용자 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param baseVO
	 */
	@RequestMapping("/cop/smt/sim/listEmplyrPopup.do")
	public String listEmplyrPopup(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		baseVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", schdulManageService.selectEmplyrList(baseVO));

		int totCnt = schdulManageService.selectEmplyrListCnt(baseVO);
		baseVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute(paginationInfo);

		return "cop/smt/sim/EmplyrListPopup";
	}

	/**
	 * 일정관리 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/listSchdulPopup.do")
	@Secured("ROLE_USER")
	public String listSchdulPopup(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		schdulManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", schdulManageService.selectSchdulManageList(schdulManageVO));

		int totCnt = (Integer) schdulManageService.selectSchdulManageListCnt(schdulManageVO);
		schdulManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute(paginationInfo);

		return "cop/smt/sim/SchdulListPopup";
	}

	/**
	 * 메인페이지/일정관리조회
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/listSchdulMainPage.do")
	public String listSchdulMainPage(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		schdulManageVO.setUserId(loginVO.getUserId());
 
		model.addAttribute("resultList", schdulManageService.selectSchdulManageMainList(schdulManageVO));

		return "cop/smt/sim/SchdulMainPage";
	}
	
	/**
	 * 일정 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	@IncludedInfo(name = "일정관리", order = 4050, gid = 40)
	@RequestMapping(value = "/cop/smt/sim/listSchdul.do")
	public String listSchdul(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {
		
		// 공통코드 일정종류
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");
		// 공통코드 중요도 조회
		cmmUseService.populateCmmCodeList("COM019", "COM019_schdulIpcr");
		// 공통코드 반복구분 조회
		cmmUseService.populateCmmCodeList("COM031", "COM031_reptitSe");

		return "cop/smt/sim/SchdulList";
	}

	/**
	 * 일정(일별) 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/listSchdulDaily.do")
	public String listSchdulDaily(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			HttpServletRequest request,
			ModelMap model) {

		/* *****************************************************************
		 * // 캘런더 설정 로직
		 * *****************************************************************
		 */
		Calendar calNow = Calendar.getInstance();

		String strYear = schdulManageVO.getYear();
		String strMonth = schdulManageVO.getMonth();
		String strDay = schdulManageVO.getDay();
		
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
			schdulManageVO.setYear(Integer.toString(iNowYear));
			schdulManageVO.setMonth(Integer.toString(iNowMonth));
			schdulManageVO.setDay(Integer.toString(iNowDay));
		}

		schdulManageVO.setSearchMode("DAILY");
		schdulManageVO.setSearchDay(strSearchDay);

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		schdulManageVO.setUserId(loginVO.getUserId());
		schdulManageVO.setTrgetId((String)request.getAttribute("curTrgetId"));

		model.addAttribute("resultList", schdulManageService.selectSchdulManageRetrieve(schdulManageVO));

		return "cop/smt/sim/SchdulDailyList";
	}

	/**
	 * 일정(주간별) 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/listSchdulWeek.do")
	public String listSchdulWeek(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			HttpServletRequest request,
			ModelMap model) {

		/* *****************************************************************
		 * // 캘런더 설정 로직
		 * *****************************************************************
		 */
		Calendar calNow = Calendar.getInstance();
		Calendar calBefore = Calendar.getInstance();
		Calendar calNext = Calendar.getInstance();

		String strYear = schdulManageVO.getYear();
		String strMonth = schdulManageVO.getMonth();
		String strWeek = schdulManageVO.getWeek();

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

//		int startDay = calNow.getMinimum(Calendar.DATE);
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
			// sUseDate += Integer.toString(iNowMonth).length() == 1 ? "0" +
			// Integer.toString(iNowMonth+1) : Integer.toString(iNowMonth+1);
			// (2011.09.02 수정사항) 10월의 주별 날짜가 이상하게 나와서 LeaderSchedule 보고 수정함. 위의
			// 코드가 원래 코드
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
			schdulManageVO.setYear(Integer.toString(iNowYear));
			schdulManageVO.setMonth(Integer.toString(iNowMonth));
			schdulManageVO.setWeek(Integer.toString(iNowWeek));
		}

		model.addAttribute("listWeekGrop", listWeekGrop);

		List<String> listWeek = (List<String>) listWeekGrop.get(iNowWeek);
		schdulManageVO.setSearchMode("WEEK");
		schdulManageVO.setSearchBgnde(listWeek.get(0));
		schdulManageVO.setSearchEndde(listWeek.get(listWeek.size() - 1));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		schdulManageVO.setUserId(loginVO.getUserId());
		schdulManageVO.setTrgetId((String)request.getAttribute("curTrgetId"));

		model.addAttribute("resultList", schdulManageService.selectSchdulManageRetrieve(schdulManageVO));

		return "cop/smt/sim/SchdulWeekList";
	}

	/**
	 * 일정(월별) 목록을 조회한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/listSchdulMonth.do")
	public String listSchdulMonth(
			@ModelAttribute SchdulManageVO schdulManageVO, 
			HttpServletRequest request,
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String strYear = schdulManageVO.getYear();
		String strMonth = schdulManageVO.getMonth();

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
			schdulManageVO.setYear(Integer.toString(iNowYear));
			schdulManageVO.setMonth(Integer.toString(iNowMonth));
		}

		schdulManageVO.setSearchMode("MONTH");
		schdulManageVO.setSearchMonth(sSearchDate);

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		schdulManageVO.setUserId(loginVO.getUserId());
		schdulManageVO.setTrgetId((String)request.getAttribute("curTrgetId"));

		model.addAttribute("resultList", schdulManageService.selectSchdulManageRetrieve(schdulManageVO));

		// 휴일 검색
		RestdeVO restdeVO = new RestdeVO();
		restdeVO.setYear(Integer.toString(iNowYear));
		restdeVO.setMonth(Integer.toString(iNowMonth+1));
		
		model.addAttribute("restdeList", restdeManageService.selectNormalMonthRestde(restdeVO));

		return "cop/smt/sim/SchdulMonthList";
	}

	/**
	 * 일정 목록을 상세조회 한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/detailSchdul.do")
	public String detailSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		model.addAttribute(schdulManageService.selectSchdulManageDetail(schdulManageVO));

		// -----------------------------------------------------------
		// 2011.09.16 : 일지관리가 존재할 때 버튼이 나타나도록 수정
		// -----------------------------------------------------------

		if (ComponentChecker.hasComponent("diaryManageService")) {
			model.addAttribute("useDiaryManage", "true");
		}

		return "cop/smt/sim/SchdulDetail";
	}

	// 일정일자(시)
	@ModelAttribute("schdulListHH")
	public List<ComCodeVO> schdulListHH() {return WebUtil.getTimeHH();}
	// 일정일자(분)
	@ModelAttribute("schdulListMM")
	public List<ComCodeVO> schdulListMM() {return WebUtil.getTimeMM();}

	/**
	 * 일정를 등록 폼
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/registSchdul.do")
	@Secured("ROLE_USER")
	public String registSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		return "cop/smt/sim/SchdulRegist";
	}

	/**
	 * 일정를 등록 처리 한다.
	 * 
	 * @param multiRequest
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/insertSchdul.do")
	@Secured("ROLE_USER")
	public String insertSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model)
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(schdulManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/smt/sim/SchdulRegist";
		}

		// 첨부파일 관련 첨부파일ID 생성
		schdulManageVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "SIM"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		schdulManageVO.setFrstRegisterId(loginVO.getUserId());

		schdulManageService.insertSchdulManage(schdulManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/smt/sim/listSchdul.do");
	    return "cmm/redirect";
	}

	/**
	 * 일정 수정 폼
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/editSchdul.do")
	@Secured("ROLE_USER")
	public String editSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		schdulManageVO = schdulManageService.selectSchdulManageDetail(schdulManageVO);
 
		String sSchdulBgnde = schdulManageVO.getSchdulBgnde();
		String sSchdulEndde = schdulManageVO.getSchdulEndde();

		schdulManageVO.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) + "-" + sSchdulBgnde.substring(4, 6) + "-"+ sSchdulBgnde.substring(6, 8));
		schdulManageVO.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10));
		schdulManageVO.setSchdulBgndeMM(sSchdulBgnde.substring(10, 12));

		schdulManageVO.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) + "-" + sSchdulEndde.substring(4, 6) + "-"+ sSchdulEndde.substring(6, 8));
		schdulManageVO.setSchdulEnddeHH(sSchdulEndde.substring(8, 10));
		schdulManageVO.setSchdulEnddeMM(sSchdulEndde.substring(10, 12));

		boolean writer = false;
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (schdulManageVO.getFrstRegisterId().equals(loginVO.getUserId())) {
			writer = true;
		}
		model.addAttribute("writer", writer);
		model.addAttribute(schdulManageVO);

		return "cop/smt/sim/SchdulEdit";
	}

	/**
	 * 일정를 수정 처리 한다.
	 * 
	 * @param multiRequest
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/updateSchdul.do")
	@Secured("ROLE_USER")
	public String updateSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(schdulManageVO, bindingResult);
		if (bindingResult.hasErrors()) {

			boolean writer = false;
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if (schdulManageVO.getFrstRegisterId().equals(loginVO.getUserId())) {
				writer = true;
			}
			model.addAttribute("writer", writer);

			return "cop/smt/sim/SchdulEdit";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		schdulManageVO.setLastUpdusrId(loginVO.getUserId());

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = schdulManageVO.getAtchFileId();
		schdulManageVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "SIM", atchFileId));

		/* *****************************************************************
		 * // 일정관리정보 업데이트 처리
		 * *****************************************************************
		 */
		schdulManageService.updateSchdulManage(schdulManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/smt/sim/listSchdul.do");
	    return "cmm/redirect";
	}

	/**
	 * 일정 목록을 삭제 한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value = "/cop/smt/sim/deleteSchdul.do")
	@Secured("ROLE_USER")
	public String deleteSchdul(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute SchdulManageVO schdulManageVO, 
			ModelMap model) {

		schdulManageService.deleteSchdulManage(schdulManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/smt/sim/listSchdul.do");
	    return "cmm/redirect";
	}

}
