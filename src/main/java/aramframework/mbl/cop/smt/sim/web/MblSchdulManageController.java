package aramframework.mbl.cop.smt.sim.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cop.smt.sim.domain.SchdulManageVO;
import aramframework.com.cop.smt.sim.service.SchdulManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일정관리를 처리하는 Mobile Controller Class 구현
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
public class MblSchdulManageController {

	@Autowired
	private SchdulManageService schdulManageService;
		
	@Autowired
	private CmmUseService cmmUseService;
	
	/**
	 * 일정(일별) 목록 화면을 출력한다.
	 * 
	 * @param schdulManageVO 
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulDaily.mdo")
	public String listSchdulDaily(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		//공통코드 부서일정종류
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");

    	/* *****************************************************************
    	// 검색조건 유지
		****************************************************************** */
    	model.addAttribute("selDate", request.getParameter("selDate"));
		
		return  "aramframework/mbl/cop/smt/sim/SchdulDailyList";
	}
	
	/**
	 * 일정(일별) 목록을 조회한다.
	 * 
	 * @param schdulManageVO 
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulDailyJson.mdo")
	public ModelAndView listSchdulDailyJson(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		//공통코드 부서일정종류
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");

		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        
		String strDate = request.getParameter("selDate");
		
		String strSearchDay = "";
		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH) + 1;
		int iNowDay = calNow.get(Calendar.DATE);
		
		if(strDate != null && !"".equals(strDate))
		{
		  iNowYear = Integer.parseInt(strDate.split("-")[0]);
		  iNowMonth = Integer.parseInt(strDate.split("-")[1]);
		  iNowDay = Integer.parseInt(strDate.split("-")[2]);
		}
        
		strSearchDay = Integer.toString(iNowYear);
		strSearchDay += DateTypeIntForString(iNowMonth); 
		strSearchDay += DateTypeIntForString(iNowDay); 
		
		schdulManageVO.setSearchMode("DAILY");
		schdulManageVO.setSearchDay(strSearchDay);

		strDate = Integer.toString(iNowYear) + "-" + DateTypeIntForString(iNowMonth) + "-" + DateTypeIntForString(iNowDay);
		modelAndView.addObject("selDate", strDate);
		
		model.addAttribute("resultList", schdulManageService.selectSchdulManageRetrieve(schdulManageVO));

		return modelAndView; 
	}

	/**
	 * 일정(주간별) 목록 화면을 출력한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulWeek.mdo")
	public String listSchdulWeek(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		//공통코드 부서일정종류
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");

    	/* *****************************************************************
    	// 검색조건 유지
		****************************************************************** */
    	model.addAttribute("selDate", request.getParameter("selDate"));
		
		return "aramframework/mbl/cop/smt/sim/SchdulWeekList";
	}
	
	/**
	 * 일정(주간별) 목록을 조회한다. 
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulWeekJson.mdo")
	public ModelAndView listSchdulWeekJson(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();
        
        String strDate = request.getParameter("selDate");

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDate = calNow.get(Calendar.DATE);
        
        if(strDate != null && !"".equals(strDate)) {
        	
  		  iNowYear = Integer.parseInt(strDate.split("-")[0]);
		  iNowMonth = Integer.parseInt(strDate.split("-")[1]);
		  iNowDate = Integer.parseInt(strDate.split("-")[2]);
	        	
        } 
       
        calNow.set(iNowYear, iNowMonth, iNowDate);
        calBefore = (Calendar) calNow.clone();
        calNext = (Calendar) calNow.clone();
        
        // 한 주의 시작날과 마지막날 설정 (일 ~ 금)
        int day = calNow.get(Calendar.DAY_OF_WEEK);
        calBefore.add(Calendar.DATE, -(day));
        calNext.add(Calendar.DATE, 7 - day);
               
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

		schdulManageVO.setSearchMode("WEEK");
		schdulManageVO.setSearchBgnde(dateFormat.format(calBefore.getTime()));
		schdulManageVO.setSearchEndde(dateFormat.format(calNext.getTime()));
	
		List<EgovMap> indvdlSchdulManageList = schdulManageService.selectSchdulManageRetrieve(schdulManageVO);
       
		// 한 주의 일정 그룹
		ArrayList<ArrayList<EgovMap>> weekSchdul = new ArrayList<ArrayList<EgovMap>>();
		// 당일 일정
		ArrayList<EgovMap> dailySchdul = null;
		// 한 주의 그룹
		ArrayList<String> weekGroup = new ArrayList<String>();
		
		/* *****************************************************************
    	// 한 주의 일정 저장
		****************************************************************** */
		calBefore.add(Calendar.DATE, 1);
		Calendar calTmp = (Calendar)calBefore.clone();
		for(; calTmp.getTime().compareTo(calNext.getTime()) <= 0; calTmp.add(Calendar.DATE, 1) ) {
			
			dailySchdul = new ArrayList<EgovMap>();
			String calSchdul = dateFormat.format(calTmp.getTime());
			
			for (EgovMap map : indvdlSchdulManageList) {
				String schdulBeginde = (String)map.get("schdulBgnde");
				String schdulEndde = (String)map.get("schdulEndde");
				
				if(schdulBeginde.substring(0, 8).compareTo(calSchdul) <= 0 && schdulEndde.substring(0, 8).compareTo(calSchdul) >= 0) {
					dailySchdul.add(map);
				}
			}
			weekGroup.add(calSchdul);
			weekSchdul.add(dailySchdul);
		}
		
		strDate = Integer.toString(iNowYear) + "-" + DateTypeIntForString(iNowMonth) + "-" + DateTypeIntForString(iNowDate);
		modelAndView.addObject("selDate", strDate);
		modelAndView.addObject("resultList", weekSchdul);
		modelAndView.addObject("weekGroup", weekGroup);
				
		dateFormat.applyPattern("yyyy-MM-dd");
		modelAndView.addObject("schdulBgnde", dateFormat.format(calBefore.getTime()));
		modelAndView.addObject("schdulEndde", dateFormat.format(calNext.getTime()));
       
        return modelAndView; 
	}
	
	/**
	 * 일정 목록을 상세조회한다. 
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/detailSchdul.mdo")
	public String detailSchdul(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		//공통코드  중요도 조회
		cmmUseService.populateCmmCodeList("COM019", "COM019_schdulIpcr");
     	//공통코드  일정구분 조회
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");
    	//공통코드  반복구분 조회
		cmmUseService.populateCmmCodeList("COM031", "COM031_reptitSe");
    	
        schdulManageService.selectSchdulManageDetail(schdulManageVO);
        
		/* *****************************************************************
    	// 검색조건 유지
		****************************************************************** */
        model.addAttribute("path", request.getParameter("path"));
        model.addAttribute("selDate", request.getParameter("selDate"));
        
		return "aramframework/mbl/cop/smt/sim/SchdulDetail"; 	
	}
	
	/**
	 * 일정 등록 화면을 출력한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/registSchdul.mdo")
	@Secured("ROLE_USER")
	public String registSchdul(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		//공통코드  중요도 조회
		cmmUseService.populateCmmCodeList("COM019", "COM019_schdulIpcr");
     	//공통코드  일정구분 조회
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");
    	//공통코드  반복구분 조회
		cmmUseService.populateCmmCodeList("COM031", "COM031_reptitSe");
    	
		model.addAttribute("path", request.getParameter("path"));
		model.addAttribute("selDate", request.getParameter("selDate"));
		 
    	return "aramframework/mbl/cop/smt/sim/SchdulRegist"; 
	}
	
	/**
	 * 일정를 등록 처리 한다. 
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/insertSchdul.mdo")
	@Secured("ROLE_USER")
	public String insertSchdul(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {

		// 시간 변환
		String schdulBgndeYYYMMDD = request.getParameter("schdulBgndeYYYMMDD");
		String schdulEnddeYYYMMDD = request.getParameter("schdulEnddeYYYMMDD");
		String schdulBgndeHH = request.getParameter("schdulBgndeHH");
		String schdulEnddeHH = request.getParameter("schdulEnddeHH");
				
		schdulManageVO.setSchdulBgnde(schdulBgndeYYYMMDD.replaceAll("-", "") + schdulBgndeHH.replaceAll(":", ""));		
		schdulManageVO.setSchdulEndde(schdulEnddeYYYMMDD.replaceAll("-", "") + schdulEnddeHH.replaceAll(":", ""));
    	
		// kind Code 설정
		schdulManageVO.setOthbcScope("G");

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		schdulManageVO.setFrstRegisterId(loginVO.getUniqId());
   		
    	schdulManageService.insertSchdulManage(schdulManageVO);
        
        return "redirect:/cop/smt/sim/listSchdulDaily.mdo";
 	}
	
	/**
	 * 일정 수정 화면을 출력한다. 
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/editSchdul.mdo")
	@Secured("ROLE_USER")
	public String editSchdul(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) {
		
		//공통코드  중요도 조회
		cmmUseService.populateCmmCodeList("COM019", "COM019_schdulIpcr");
     	//공통코드  일정구분 조회
		cmmUseService.populateCmmCodeList("COM030", "COM030_schdulSe");
    	//공통코드  반복구분 조회
		cmmUseService.populateCmmCodeList("COM031", "COM031_reptitSe");

    	schdulManageService.selectSchdulManageDetail(schdulManageVO);
		
    	String sSchdulBgnde = schdulManageVO.getSchdulBgnde();
    	String sSchdulEndde = schdulManageVO.getSchdulEndde();
    	
    	schdulManageVO.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 4) +"-"+sSchdulBgnde.substring(4, 6)+"-"+sSchdulBgnde.substring(6, 8) );
    	schdulManageVO.setSchdulBgndeHH(sSchdulBgnde.substring(8, 10) + ":" + sSchdulBgnde.substring(10, 12));
    	
    	schdulManageVO.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 4) +"-"+sSchdulEndde.substring(4, 6)+"-"+sSchdulEndde.substring(6, 8) );
    	schdulManageVO.setSchdulEnddeHH(sSchdulEndde.substring(8, 10) + ":" + sSchdulEndde.substring(10, 12));
    	
    	/* *****************************************************************
    	// 검색조건 유지
		****************************************************************** */
		model.addAttribute("path", request.getParameter("path"));
		model.addAttribute("selDate", request.getParameter("selDate"));
		 
		return "aramframework/mbl/cop/smt/sim/SchdulEdit"; 	
	}
	
	/**
	 * 일정을 수정 처리 한다.
	 * 
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/updateSchdul.mdo")
	@Secured("ROLE_USER")
	public String updateSchdul(
			HttpServletRequest request, 
			@ModelAttribute SchdulManageVO schdulManageVO,
    		ModelMap model) { 
		
		// 시간 변환
		String schdulBgndeYYYMMDD = request.getParameter("schdulBgndeYYYMMDD");
		String schdulEnddeYYYMMDD = request.getParameter("schdulEnddeYYYMMDD");
		String schdulBgndeHH = request.getParameter("schdulBgndeHH");
		String schdulEnddeHH = request.getParameter("schdulEnddeHH");
				
		schdulManageVO.setSchdulBgnde(schdulBgndeYYYMMDD.replaceAll("-", "") + schdulBgndeHH.replaceAll(":", ""));		
		schdulManageVO.setSchdulEndde(schdulEnddeYYYMMDD.replaceAll("-", "") + schdulEnddeHH.replaceAll(":", ""));
    	
		// kind Code 설정
		schdulManageVO.setOthbcScope("G");
		
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		schdulManageVO.setLastUpdusrId(loginVO.getUniqId());	

		/* *****************************************************************
    	// 일정관리정보 업데이트 처리
		****************************************************************** */
    	schdulManageService.updateSchdulManage(schdulManageVO);
    	
    	/* *****************************************************************
    	// 검색조건 유지
		****************************************************************** */
		model.addAttribute("path", request.getParameter("path"));
		model.addAttribute("selDate", request.getParameter("selDate"));
		 
		return "redirect:/cop/smt/sim/listSchdulDaily.mdo"; 	
	}
	
	/**
	 *  일정을 삭제 처리한다. 
	 *  
	 * @param schdulManageVO
	 */
	@RequestMapping(value="/cop/smt/sim/deleteSchdul.mdo")
	@Secured("ROLE_USER")
	public String deleteSchdul(
			@ModelAttribute SchdulManageVO schdulManageVO) {
		
		schdulManageService.deleteSchdulManage(schdulManageVO);
		
		return "redirect:/cop/smt/sim/listSchdulDaily.mdo";
	}
	
	/**
	 * 일정관리 목록 화면을 출력한다.
	 * 
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulPopup.mdo")
	public String listSchdulPopup() {
		return "aramframework/mbl/cop/smt/sim/SchdulPopup";
	}
	
	/**
	 * 일정관리 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping(value="/cop/smt/sim/listSchdulPopupJson.mdo")
	public ModelAndView listSchdulPopupJson(
			@ModelAttribute SearchVO searchVO) {

		ModelAndView modelAndView = new ModelAndView("jsonView");

    	PaginationInfo paginationInfo = new PaginationInfo();
    	searchVO.fillPageInfo(paginationInfo);
		
    	modelAndView.addObject("resultList", schdulManageService.selectSchdulManageList(searchVO));
        
        int totCnt = (Integer)schdulManageService.selectSchdulManageListCnt(searchVO);
        searchVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);
        
        return modelAndView; 
	}
	
	/**
	 * 담당자관리 목록 화면을 출력한다.
	 * 
	 */
	@RequestMapping(value="/cop/smt/sim/listEmplyrPopup.mdo")
	public String listEmplyrPopup() {
		return "aramframework/mbl/cop/smt/sim/EmplyrPopup";
	}
	
	/**
	 * 담당자관리 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping(value="/cop/smt/sim/listEmplyrPopupJson.mdo")
	public ModelAndView listEmplyrPopupJson(
			@ModelAttribute SearchVO searchVO) {

		ModelAndView modelAndView = new ModelAndView("jsonView");

    	PaginationInfo paginationInfo = new PaginationInfo();
    	searchVO.fillPageInfo(paginationInfo);
		
    	modelAndView.addObject("resultList", schdulManageService.selectEmplyrList(searchVO));
        
        int totCnt = (Integer)schdulManageService.selectEmplyrListCnt(searchVO);
        searchVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);
        
        return modelAndView; 
	}
	
	/**
	 * 0을 붙여 반환
	 * 
	 * @return  iInput
	 */
    public String DateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}
		
       return sOutput;
    }

}


