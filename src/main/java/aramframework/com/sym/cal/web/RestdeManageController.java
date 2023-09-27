package aramframework.com.sym.cal.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
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
import aramframework.com.sym.cal.domain.RestdeVO;
import aramframework.com.sym.cal.service.RestdeManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공휴일에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를  정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class RestdeManageController {

	@Autowired
	private RestdeManageService restdeManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	public BindingResult checkRestdeWithValidator(RestdeVO restdeVO, BindingResult bindingResult) {

		restdeVO.setRestdeDe("dummy");
		restdeVO.setRestdeNm("dummy");
		restdeVO.setRestdeDc("dummy");
		restdeVO.setRestdeSeCode("dummy");

		beanValidator.validate(restdeVO, bindingResult);

		return bindingResult;
	}

	/**
	 * 휴일 리스트를 조회한다.
	 * 
	 * @param restdeVO
	 */
	@IncludedInfo(name = "공휴일관리(달력)", order = 6040, gid = 60)
	@RequestMapping(value = "/sym/cal/listRestde.do")
	public String listRestde(
			@ModelAttribute RestdeVO restdeVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		restdeVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", restdeManageService.selectRestdeList(restdeVO));
		int totCnt = restdeManageService.selectRestdeListCnt(restdeVO);

		restdeVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/cal/RestdeList";
	}

	/**
	 * 휴일 세부내역을 조회한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/detailRestde.do")
	public String detailRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO,
			ModelMap model) {
		
		model.addAttribute(restdeManageService.selectRestdeDetail(restdeVO));

		return "com/sym/cal/RestdeDetail";
	}

	/**
	 * 휴일 등록화면으로 이동한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/registRestde.do")
	@Secured("ROLE_ADMIN")
	public String registRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO, 
			ModelMap model) {
		
		cmmUseService.populateCmmCodeList("COM017", "COM017_restde");

		return "com/sym/cal/RestdeRegist";
	}

	/**
	 * 휴일을 등록한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/insertRestde.do")
	@Secured("ROLE_ADMIN")
	public String insertRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {
		
		beanValidator.validate(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/cal/RestdeRegist";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		restdeVO.setFrstRegisterId(loginVO.getUserId());

		restdeManageService.insertRestde(restdeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/sym/cal/listRestde.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 휴일 수정화면으로 이동한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/editRestde.do")
	@Secured("ROLE_ADMIN")
	public String editRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO,
			ModelMap model) {
		
		model.addAttribute(restdeManageService.selectRestdeDetail(restdeVO));

		cmmUseService.populateCmmCodeList("COM017", "COM017_restde");

		return "com/sym/cal/RestdeEdit";
	}

	/**
	 * 휴일을 수정한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/updateRestde.do")
	@Secured("ROLE_ADMIN")
	public String updateRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/sym/cal/RestdeEdit";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		restdeVO.setLastUpdusrId(loginVO.getUserId());

		restdeManageService.updateRestde(restdeVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/sym/cal/listRestde.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 휴일을 삭제한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/deleteRestde.do")
	@Secured("ROLE_ADMIN")
	public String deleteRestde(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute RestdeVO restdeVO, 
			ModelMap model) {

		restdeManageService.deleteRestde(restdeVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/cal/listRestde.do");
	    return "com/cmm/redirect";
	}

	/**
	 *  달력 메인창을 호출한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/CallCalPopup.do")
	public String callCal(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}

		cal.set(iYear, iMonth - 1, 1);

		int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		int week = cal.get(Calendar.DAY_OF_WEEK);

		String year = Integer.toString(iYear);
		String month = Integer.toString(iMonth);
//		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

		restdeVO.setStartWeekMonth(firstWeek);
		restdeVO.setLastDayMonth(lastDay);
		restdeVO.setYear(year);
		restdeVO.setMonth(month);

		List<ListOrderedMap> CalInfoList = new ArrayList<ListOrderedMap>();
		String tmpDay = "";

		/**
		 * 계산... START
		 */
		for (int i = 0; i < 42; i++) {
			ListOrderedMap map = new ListOrderedMap();
			int cc = i + 1;
			int dd = cc - firstWeek + 1;

			if (dd > 0 && dd <= lastDay) {
				tmpDay = Integer.toString(dd);
			} else {
				tmpDay = "";
			}

			map.put("year", year);
			map.put("month", month);
			map.put("day", tmpDay);
			map.put("cellNum", cc);
			map.put("weeks", (cc - 1) / 7 + 1);
			map.put("week", (week - 1) % 7 + 1);
			map.put("restAt", ((week - 1) % 7 + 1 == 1) ? "Y" : "N");

			if (dd > 0 && dd <= lastDay) {
				week++;
			}
			CalInfoList.add(map);
		}
		/**
		 * 계산... END
		 */

		model.addAttribute("resultList", CalInfoList);

		return "com/sym/cal/CallCalPopup";
	}

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/NormalCalPopup.do")
	public String selectNormalCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectNormalRestdePopup(restdeVO));

		return "com/sym/cal/NormalCalPopup";
	}

	/**
	 * 일반달력 일간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/NormalDayCalendar.do")
	public String normalDayCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restdeVO.getDay() == null || restdeVO.getDay().equals("")) {
			restdeVO.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());
		int iDay = Integer.parseInt(restdeVO.getDay());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, iDay);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restdeVO.getDay()));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		restdeVO.setDay(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
		restdeVO.setWeek(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectNormalDayCal(restdeVO));
		model.addAttribute("RestdeList", restdeManageService.selectNormalDayRestde(restdeVO));

		return "com/sym/cal/NormalDayCalendar";
	}

	/**
	 * 일반달력 주간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/NormalWeekCalendar.do")
	public String normalWeekCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restdeVO.getDay() == null || restdeVO.getDay().equals("")) {
			restdeVO.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restdeVO.getDay()));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restdeVO.getStartWeekMonth();
		int iLastDate = restdeVO.getLastDayMonth();
		int iDayWeek = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = (int) Math.floor(iLastDate / 7);
		iMaxWeeks = iMaxWeeks + (int) Math.ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0);
		restdeVO.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restdeVO.getWeeks()) {
			restdeVO.setWeeks(iMaxWeeks);
		}

		RestdeVO vo = new RestdeVO();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if (restdeVO.getWeeks() != 0) {
			weekCal.set(Calendar.DATE, (restdeVO.getWeeks() - 1) * 7 + 1);
			if (restdeVO.getWeeks() > 1) {
				iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);
				weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
			}
			restdeVO.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH) + 1));
		}

		iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);

		// 일요일
		weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_1 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_1 = restdeManageService.selectNormalDayRestde(vo);

		// 월요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_2 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_2 = restdeManageService.selectNormalDayRestde(vo);

		// 화요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_3 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_3 = restdeManageService.selectNormalDayRestde(vo);

		// 수요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_4 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_4 = restdeManageService.selectNormalDayRestde(vo);

		// 목요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_5 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_5 = restdeManageService.selectNormalDayRestde(vo);

		// 금요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_6 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_6 = restdeManageService.selectNormalDayRestde(vo);

		// 토요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_7 = restdeManageService.selectNormalDayCal(vo);
		List<EgovMap> NormalWeekRestdeList_7 = restdeManageService.selectNormalDayRestde(vo);

		model.addAttribute("resultList_1", CalInfoList_1);
		model.addAttribute("resultList_2", CalInfoList_2);
		model.addAttribute("resultList_3", CalInfoList_3);
		model.addAttribute("resultList_4", CalInfoList_4);
		model.addAttribute("resultList_5", CalInfoList_5);
		model.addAttribute("resultList_6", CalInfoList_6);
		model.addAttribute("resultList_7", CalInfoList_7);
		model.addAttribute("RestdeList_1", NormalWeekRestdeList_1);
		model.addAttribute("RestdeList_2", NormalWeekRestdeList_2);
		model.addAttribute("RestdeList_3", NormalWeekRestdeList_3);
		model.addAttribute("RestdeList_4", NormalWeekRestdeList_4);
		model.addAttribute("RestdeList_5", NormalWeekRestdeList_5);
		model.addAttribute("RestdeList_6", NormalWeekRestdeList_6);
		model.addAttribute("RestdeList_7", NormalWeekRestdeList_7);

		model.addAttribute("resultList", restdeManageService.selectNormalDayCal(restdeVO));

		return "com/sym/cal/NormalWeekCalendar";
	}

	/**
	 * 일반달력 월간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/NormalMonthCalendar.do")
	public String normalMonthCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectNormalRestdePopup(restdeVO));
		model.addAttribute("RestdeList", restdeManageService.selectNormalMonthRestde(restdeVO));

		return "com/sym/cal/NormalMonthCalendar";
	}

	/**
	 * 일반달력 연간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/NormalYearCalendar.do")
	public String normalYearCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_1 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_1 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 2월 */
		iMonth = 2;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_2 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_2 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 3월 */
		iMonth = 3;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_3 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_3 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 4월 */
		iMonth = 4;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_4 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_4 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 5월 */
		iMonth = 5;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_5 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_5 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 6월 */
		iMonth = 6;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_6 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_6 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 7월 */
		iMonth = 7;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_7 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_7 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 8월 */
		iMonth = 8;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_8 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_8 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 9월 */
		iMonth = 9;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_9 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_9 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 10월 */
		iMonth = 10;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_10 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_10 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 11월 */
		iMonth = 11;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_11 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_11 = restdeManageService.selectNormalMonthRestde(restdeVO);

		/* 12월 */
		iMonth = 12;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_12 = restdeManageService.selectNormalRestdePopup(restdeVO);
		List<EgovMap> NormalMonthRestdeList_12 = restdeManageService.selectNormalMonthRestde(restdeVO);

		model.addAttribute("resultList_1", CalInfoList_1);
		model.addAttribute("resultList_2", CalInfoList_2);
		model.addAttribute("resultList_3", CalInfoList_3);
		model.addAttribute("resultList_4", CalInfoList_4);
		model.addAttribute("resultList_5", CalInfoList_5);
		model.addAttribute("resultList_6", CalInfoList_6);
		model.addAttribute("resultList_7", CalInfoList_7);
		model.addAttribute("resultList_8", CalInfoList_8);
		model.addAttribute("resultList_9", CalInfoList_9);
		model.addAttribute("resultList_10", CalInfoList_10);
		model.addAttribute("resultList_11", CalInfoList_11);
		model.addAttribute("resultList_12", CalInfoList_12);
		model.addAttribute("RestdeList_1", NormalMonthRestdeList_1);
		model.addAttribute("RestdeList_2", NormalMonthRestdeList_2);
		model.addAttribute("RestdeList_3", NormalMonthRestdeList_3);
		model.addAttribute("RestdeList_4", NormalMonthRestdeList_4);
		model.addAttribute("RestdeList_5", NormalMonthRestdeList_5);
		model.addAttribute("RestdeList_6", NormalMonthRestdeList_6);
		model.addAttribute("RestdeList_7", NormalMonthRestdeList_7);
		model.addAttribute("RestdeList_8", NormalMonthRestdeList_8);
		model.addAttribute("RestdeList_9", NormalMonthRestdeList_9);
		model.addAttribute("RestdeList_10", NormalMonthRestdeList_10);
		model.addAttribute("RestdeList_11", NormalMonthRestdeList_11);
		model.addAttribute("RestdeList_12", NormalMonthRestdeList_12);

		return "com/sym/cal/NormalYearCalendar";
	}

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/AdministCalPopup.do")
	public String selectAdministCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectAdministRestdePopup(restdeVO));

		return "com/sym/cal/AdministCalPopup";
	}

	/**
	 * 행정달력 일간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/AdministDayCalendar.do")
	public String administDayCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restdeVO.getDay() == null || restdeVO.getDay().equals("")) {
			restdeVO.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());
		int iDay = Integer.parseInt(restdeVO.getDay());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, iDay);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restdeVO.getDay()));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		restdeVO.setDay(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
		restdeVO.setWeek(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectAdministDayCal(restdeVO));
		model.addAttribute("RestdeList", restdeManageService.selectAdministDayRestde(restdeVO));

		return "com/sym/cal/AdministDayCalendar";
	}

	/**
	 * 행정달력 주간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/AdministWeekCalendar.do")
	public String administWeekCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		if (restdeVO.getDay() == null || restdeVO.getDay().equals("")) {
			restdeVO.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear, iMonth - 1, Integer.parseInt(restdeVO.getDay()));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restdeVO.getStartWeekMonth();
		int iLastDate = restdeVO.getLastDayMonth();
		int iDayWeek = cal.get(Calendar.DAY_OF_WEEK);

		int iMaxWeeks = (int) Math.floor(iLastDate / 7);
		iMaxWeeks = iMaxWeeks + (int) Math.ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0);
		restdeVO.setMaxWeeks(iMaxWeeks);

		if (iMaxWeeks < restdeVO.getWeeks()) {
			restdeVO.setWeeks(iMaxWeeks);
		}

		RestdeVO vo = new RestdeVO();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());

		if (restdeVO.getWeeks() != 0) {
			weekCal.set(Calendar.DATE, (restdeVO.getWeeks() - 1) * 7 + 1);
			if (restdeVO.getWeeks() > 1) {
				iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);
				weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
			}
			restdeVO.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH) + 1));
		}
		List<EgovMap> CalInfoList = restdeManageService.selectAdministDayCal(restdeVO);

		iDayWeek = weekCal.get(Calendar.DAY_OF_WEEK);

		// 일요일
		weekCal.add(Calendar.DATE, (-1) * (iDayWeek - 1));
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_1 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_1 = restdeManageService.selectAdministDayRestde(vo);

		// 월요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_2 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_2 = restdeManageService.selectAdministDayRestde(vo);

		// 화요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_3 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_3 = restdeManageService.selectAdministDayRestde(vo);

		// 수요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_4 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_4 = restdeManageService.selectAdministDayRestde(vo);

		// 목요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_5 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_5 = restdeManageService.selectAdministDayRestde(vo);

		// 금요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_6 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_6 = restdeManageService.selectAdministDayRestde(vo);

		// 토요일
		weekCal.add(Calendar.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(Calendar.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(Calendar.MONTH) + 1));
		vo.setDay(Integer.toString(weekCal.get(Calendar.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(Calendar.DAY_OF_WEEK));
		List<EgovMap> CalInfoList_7 = restdeManageService.selectAdministDayCal(vo);
		List<EgovMap> AdministWeekRestdeList_7 = restdeManageService.selectAdministDayRestde(vo);

		model.addAttribute("resultList_1", CalInfoList_1);
		model.addAttribute("resultList_2", CalInfoList_2);
		model.addAttribute("resultList_3", CalInfoList_3);
		model.addAttribute("resultList_4", CalInfoList_4);
		model.addAttribute("resultList_5", CalInfoList_5);
		model.addAttribute("resultList_6", CalInfoList_6);
		model.addAttribute("resultList_7", CalInfoList_7);
		model.addAttribute("RestdeList_1", AdministWeekRestdeList_1);
		model.addAttribute("RestdeList_2", AdministWeekRestdeList_2);
		model.addAttribute("RestdeList_3", AdministWeekRestdeList_3);
		model.addAttribute("RestdeList_4", AdministWeekRestdeList_4);
		model.addAttribute("RestdeList_5", AdministWeekRestdeList_5);
		model.addAttribute("RestdeList_6", AdministWeekRestdeList_6);
		model.addAttribute("RestdeList_7", AdministWeekRestdeList_7);

		model.addAttribute("resultList", CalInfoList);

		return "com/sym/cal/AdministWeekCalendar";
	}

	/**
	 * 행정달력 월간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/AdministMonthCalendar.do")
	public String administMonthCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult,
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));
		restdeVO.setMonth(Integer.toString(iMonth));

		cal.set(iYear, iMonth - 1, 1);

		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		model.addAttribute("resultList", restdeManageService.selectAdministRestdePopup(restdeVO));
		model.addAttribute("RestdeList", restdeManageService.selectAdministMonthRestde(restdeVO));

		return "com/sym/cal/AdministMonthCalendar";
	}

	/**
	 * 행정달력 연간
	 * 
	 * @param restdeVO
	 */
	@RequestMapping(value = "/sym/cal/AdministYearCalendar.do")
	public String administYearCalendar(
			@ModelAttribute RestdeVO restdeVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 2011.10.18 달력 출력을 위해 필요한 숫자 이외의 값을 사용하는 경우 체크
		bindingResult = checkRestdeWithValidator(restdeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cmm/error/dataAccessFailure";
		}

		Calendar cal = Calendar.getInstance();

		if (restdeVO.getYear() == null || restdeVO.getYear().equals("")) {
			restdeVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if (restdeVO.getMonth() == null || restdeVO.getMonth().equals("")) {
			restdeVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
		}
		int iYear = Integer.parseInt(restdeVO.getYear());
		int iMonth = Integer.parseInt(restdeVO.getMonth());

		if (iMonth < 1) {
			iYear--;
			iMonth = 12;
		}
		if (iMonth > 12) {
			iYear++;
			iMonth = 1;
		}
		if (iYear < 1) {
			iYear = 1;
			iMonth = 1;
		}
		if (iYear > 9999) {
			iYear = 9999;
			iMonth = 12;
		}
		restdeVO.setYear(Integer.toString(iYear));

		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_1 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_1 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 2월 */
		iMonth = 2;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_2 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_2 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 3월 */
		iMonth = 3;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_3 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_3 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 4월 */
		iMonth = 4;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_4 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_4 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 5월 */
		iMonth = 5;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_5 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_5 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 6월 */
		iMonth = 6;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_6 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_6 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 7월 */
		iMonth = 7;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_7 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_7 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 8월 */
		iMonth = 8;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_8 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_8 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 9월 */
		iMonth = 9;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_9 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_9 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 10월 */
		iMonth = 10;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_10 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_10 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 11월 */
		iMonth = 11;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_11 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_11 = restdeManageService.selectAdministMonthRestde(restdeVO);

		/* 12월 */
		iMonth = 12;
		restdeVO.setMonth(Integer.toString(iMonth));
		cal.set(iYear, iMonth - 1, 1);
		restdeVO.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restdeVO.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		List<EgovMap> CalInfoList_12 = restdeManageService.selectAdministRestdePopup(restdeVO);
		List<EgovMap> AdministMonthRestdeList_12 = restdeManageService.selectAdministMonthRestde(restdeVO);

		model.addAttribute("resultList_1", CalInfoList_1);
		model.addAttribute("resultList_2", CalInfoList_2);
		model.addAttribute("resultList_3", CalInfoList_3);
		model.addAttribute("resultList_4", CalInfoList_4);
		model.addAttribute("resultList_5", CalInfoList_5);
		model.addAttribute("resultList_6", CalInfoList_6);
		model.addAttribute("resultList_7", CalInfoList_7);
		model.addAttribute("resultList_8", CalInfoList_8);
		model.addAttribute("resultList_9", CalInfoList_9);
		model.addAttribute("resultList_10", CalInfoList_10);
		model.addAttribute("resultList_11", CalInfoList_11);
		model.addAttribute("resultList_12", CalInfoList_12);
		model.addAttribute("RestdeList_1", AdministMonthRestdeList_1);
		model.addAttribute("RestdeList_2", AdministMonthRestdeList_2);
		model.addAttribute("RestdeList_3", AdministMonthRestdeList_3);
		model.addAttribute("RestdeList_4", AdministMonthRestdeList_4);
		model.addAttribute("RestdeList_5", AdministMonthRestdeList_5);
		model.addAttribute("RestdeList_6", AdministMonthRestdeList_6);
		model.addAttribute("RestdeList_7", AdministMonthRestdeList_7);
		model.addAttribute("RestdeList_8", AdministMonthRestdeList_8);
		model.addAttribute("RestdeList_9", AdministMonthRestdeList_9);
		model.addAttribute("RestdeList_10", AdministMonthRestdeList_10);
		model.addAttribute("RestdeList_11", AdministMonthRestdeList_11);
		model.addAttribute("RestdeList_12", AdministMonthRestdeList_12);

		return "com/sym/cal/AdministYearCalendar";
	}

}