package aramframework.com.uss.ion.ans.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

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
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ans.domain.AnnvrsryManageVO;
import aramframework.com.uss.ion.ans.service.AnnvrsryManageService;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 기념일관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 기념일관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class AnnvrsryManageController {

	@Autowired
	private AnnvrsryManageService annvrsryManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@IncludedInfo(name = "기념일관리", order = 5310, gid = 50)
	@RequestMapping(value = "/uss/ion/ans/listAnnvrsry.do")
	@Secured("ROLE_USER")
	public String listAnnvrsry(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO,
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) + 2 - x);
		}
		model.addAttribute("yearList", yearList);

		if (annvrsryManageVO.getSearchKeyword() == null || annvrsryManageVO.getSearchKeyword().equals(""))
			annvrsryManageVO.setSearchKeyword(Integer.toString(cal.get(java.util.Calendar.YEAR)));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setUsid(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		annvrsryManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", annvrsryManageService.selectAnnvrsryManageList(annvrsryManageVO));
		int totCnt = annvrsryManageService.selectAnnvrsryManageListCnt(annvrsryManageVO);

		annvrsryManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryList");
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/detailAnnvrsry.do")
	public String detailAnnvrsry(
			AnnvrsryManageVO annvrsryManageVO,
			ModelMap model) {

		annvrsryManageVO = annvrsryManageService.selectAnnvrsryManage(annvrsryManageVO);

		String sTempCldrSe = null;
		if ("1".equals(annvrsryManageVO.getCldrSe()))
			sTempCldrSe = "양";
		else
			sTempCldrSe = "음";
		String sTempAnnvrsryDe = DateUtil.formatDate(annvrsryManageVO.getAnnvrsryDe(), "-") + "(" + sTempCldrSe + ")";
		annvrsryManageVO.setAnnvrsryDeNm(sTempAnnvrsryDe);

		String sTempAnnvrsrySetup = null;
		if ("Y".equals(annvrsryManageVO.getAnnvrsrySetup()))
			sTempAnnvrsrySetup = "ON";
		else
			sTempAnnvrsrySetup = "OFF";
		annvrsryManageVO.setAnnvrsrySetupNm(sTempAnnvrsrySetup);

		model.addAttribute(annvrsryManageVO);
		
		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryDetail");
	}

	/**
	 * 기념일관리 등록 화면으로 이동한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/registAnnvrsry.do")
	public String registAnnvrsry(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setUsid(loginVO.getUniqId());
		annvrsryManageVO.setAnnvrsrySetup("Y");
		annvrsryManageVO.setCldrSe("1"); // 1:양력 2:음력
		annvrsryManageVO.setUsNm(loginVO.getName()); // 사용자명
		annvrsryManageVO.setOrgnztNm(loginVO.getOrgnztNm()); // 조직 ID

		cmmUseService.populateCmmCodeList("COM069", "COM069_annvrsrySe");

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryRegist");
	}

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/insertAnnvrsry.do")
	public String insertAnnvrsry(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(annvrsryManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.insert"));
			return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setFrstRegisterId(loginVO.getUniqId());

		if (annvrsryManageService.selectAnnvrsryManageDplctAt(annvrsryManageVO) != 0) {
			model.addAttribute("message", "이미 등록된 데이타입니다. 해당 데이타를 확인해 주세요");
			return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryRegist");
		}
		
		annvrsryManageService.insertAnnvrsryManage(annvrsryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/ans/listAnnvrsry.do");
	}

	/**
	 * 기념일관리수정 화면으로 이동한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/editAnnvrsry.do")
	public String editAnnvrsry(
			AnnvrsryManageVO annvrsryManageVO,
			ModelMap model) {

		model.addAttribute(annvrsryManageService.selectAnnvrsryManage(annvrsryManageVO));

		cmmUseService.populateCmmCodeList("COM069", "COM069_annvrsrySe");

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryEdit");
	}

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/updateAnnvrsry.do")
	public String updateAnnvrsry(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(annvrsryManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setLastUpdusrId(loginVO.getUniqId());

		annvrsryManageService.updateAnnvrsryManage(annvrsryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/ans/listAnnvrsry.do");
	}

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/deleteAnnvrsry.do")
	public String deleteAnnvrsry(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO, 
			ModelMap model) {

		annvrsryManageService.deleteAnnvrsryManage(annvrsryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/ans/listAnnvrsry.do");
	}

	/**
	 * Main화면에서 알림설정에 다른 기념일관리 목록을 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@IncludedInfo(name = "기념일목록(메인화면용)", order = 5311, gid = 50)
	@RequestMapping(value = "/uss/ion/ans/listAnnvrsryMainPage.do")
	@Secured("ROLE_USER")
	public String listAnnvrsryMainPage(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO,
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setUsid(loginVO.getUniqId());
		annvrsryManageVO.setSizeAndOffset(5, 0);

		model.addAttribute("resultList", annvrsryManageService.selectAnnvrsryGdcc(annvrsryManageVO));

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryMainPage");
	}

	/**
	 * 등록된 기념일관리의 알림 화면을 조회한다.
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/detailAnnvrsryGdcc.do")
	public String detailAnnvrsryGdcc(
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO) {

		/*
		 * String sAnnvrsryDe_Temp = null;
		 * 
		 * sAnnvrsryDe_Temp = EgovStringUtil.removeMinusChar(annvrsryManageVO.getAnnvrsryDe());
		 * if("0".equals(annvrsryManageVO.getCldrSe())){ // 음력인 경우 양력으로 환산
		 * 		sAnnvrsryDe_Temp = EgovDateUtil.toSolar(sAnnvrsryDe_Temp, 0);
		 * 		annvrsryManageVO.setAnnvrsryDe(sAnnvrsryDe_Temp); 
		 * }
		 */
		annvrsryManageService.selectAnnvrsryManage(annvrsryManageVO);

		String sTempCldrSe = null;
		String sAnnvrsryDe = annvrsryManageVO.getAnnvrsryDe();
		if ("1".equals(annvrsryManageVO.getCldrSe()))
			sTempCldrSe = "양";
		else {
			sTempCldrSe = "음";
			sAnnvrsryDe = DateUtil.toSolar(sAnnvrsryDe, 0);
		}

		String sTempAnnvrsryDe = DateUtil.formatDate(sAnnvrsryDe, "-") + "(" + sTempCldrSe + ")";
		annvrsryManageVO.setAnnvrsryDeNm(sTempAnnvrsryDe);

		String sTempAnnvrsrySetup = null;
		if ("Y".equals(annvrsryManageVO.getAnnvrsrySetup()))
			sTempAnnvrsrySetup = "ON";
		else
			sTempAnnvrsrySetup = "OFF";
		annvrsryManageVO.setAnnvrsrySetupNm(sTempAnnvrsrySetup);

		/* 날짜 사이의 기간 산출 */
		long resultDay = 0;
		Calendar to_day = Calendar.getInstance(); // Calendar객체를 생성합니다.
		Calendar target_day = Calendar.getInstance();

		if (sAnnvrsryDe != null && !sAnnvrsryDe.equals("")) {
			target_day.set(Integer.parseInt(sAnnvrsryDe.substring(0, 4)), Integer.parseInt(sAnnvrsryDe.substring(4, 6)) - 1,
					Integer.parseInt(sAnnvrsryDe.substring(6, 8)));
		} else {
			target_day.set(to_day.get(Calendar.YEAR), to_day.get(Calendar.MONTH) + 1, to_day.get(Calendar.DATE));
		}

		long resultTime = target_day.getTime().getTime() - to_day.getTime().getTime(); // 차이 구하기
		if (resultTime > 0) {
			resultDay = resultTime / (1000 * 60 * 60 * 24);// 일로 바꾸기
		} else
			resultDay = 0;

		annvrsryManageVO.setAnnvrsryBeginDe(Long.toString(resultDay));

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryGdcc");
	}

	/**
	 * 기념일일괄등록화면 호출 및 기념일일괄등록처리 프로세스
	 * 
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/listAnnvrsryBndePopup.do")
	public String listAnnvrsryBndePopup(
			HttpServletRequest request, 
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO,
			ModelMap model) 
	throws Exception {

		String message = "";

		String sCmd = request.getParameter("cmd") == null ? "" : request.getParameter("cmd"); // 상세정보 구분
		if (sCmd.equals("bnde")) {
			final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			
			for (MultipartFile file : multiRequest.getFileMap().values()) {
				if (!"".equals(file.getOriginalFilename())) {
					model.addAttribute("resultList", annvrsryManageService.selectAnnvrsryManageBnde(file.getInputStream()));
				} else {
					message = MessageHelper.getMessage("fail.common.msg");
				}
			}
			model.addAttribute("message", message);
		}

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryBndeListPopup");
	}

	/**
	 * 기념일정보를 일괄등록처리한다.
	 * 
	 * @param checkedAnnvrsryManageForInsert
	 * @param annvrsryManageVO
	 */
	@RequestMapping(value = "/uss/ion/ans/insertAnnvrsryBnde.do")
	public String insertAnnvrsryBnde(
			@RequestParam String checkedAnnvrsryManageForInsert,
			@ModelAttribute AnnvrsryManageVO annvrsryManageVO, 
			ModelMap model) {

		// int iTemp = egovAnnvrsryManageService.selectAnnvrsryManageMonthCnt(annvrsryManageVO);
		// if(iTemp == 0 ){

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		annvrsryManageVO.setFrstRegisterId(loginVO.getUniqId());

		annvrsryManageService.insertAnnvrsryManageBnde(annvrsryManageVO, checkedAnnvrsryManageForInsert);

		model.addAttribute("message", "true");

		return WebUtil.adjustViewName("/uss/ion/ans/AnnvrsryBndeListPopup");

		// }else{
		// 		String sTempMessage =
		// 		annvrsryManageVO.getBndtDe().substring(0,4)+"년"+bndtManageVO.getBndtDe().substring(4,6)+"월 데이타가 존재합니다.";
		// 		model.addAttribute("message", sTempMessage);
		// 		return "aramframework/com/uss/ion/bnt/AnnvrsryBndeListPopup";
		// }
	}

}
