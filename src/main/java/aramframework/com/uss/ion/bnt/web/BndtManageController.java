package aramframework.com.uss.ion.bnt.web;

import javax.annotation.Resource;
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

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import aramframework.com.uss.ion.bnt.service.BndtDiaryVO;
import aramframework.com.uss.ion.bnt.service.BndtManageVO;
import aramframework.com.uss.ion.bnt.service.BndtManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 당직관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 당직관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class BndtManageController {

	@Resource(name = "bndtManageService")
	private BndtManageService bndtManageService;

	@Resource(name = "cmmUseService")
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 당직관리 목록화면 이동
	 * 
	 * @param bndtManageVO
	 */
	@IncludedInfo(name = "당직관리", order = 5290, gid = 50)
	@RequestMapping("/uss/ion/bnt/listBndtManage.do")
	@Secured("ROLE_USER")
	public String listBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO, 
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String sYear = bndtManageVO.getYear();
		String sMonth = bndtManageVO.getMonth();

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);

		// 검색 설정
		String sSearchDate = "";
		if (sYear == null || sMonth == null) {
			bndtManageVO.setYear(Integer.toString(iYear));
			bndtManageVO.setMonth(Integer.toString(iMonth));
			sSearchDate += Integer.toString(iYear);
			sSearchDate += Integer.toString(iMonth + 1).length() == 1 ? "0" + Integer.toString(iMonth + 1) : Integer.toString(iMonth + 1);
		} else {
			iYear = Integer.parseInt(sYear);
			iMonth = Integer.parseInt(sMonth);
			sSearchDate += sYear;
			sSearchDate += Integer.toString(iMonth + 1).length() == 1 ? "0" + Integer.toString(iMonth + 1) : Integer.toString(iMonth + 1);
		}
		bndtManageVO.setBndtDe(sSearchDate);

		model.addAttribute("bndtManageList", bndtManageService.selectBndtManageList(bndtManageVO));

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageList");
	}

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/selectBndtManageList.do")
	public String selectBndtManageList(
			@ModelAttribute BndtManageVO bndtManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		bndtManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("bndtManageList", bndtManageService.selectBndtManageList(bndtManageVO));

		int totCnt = bndtManageService.selectBndtManageListCnt(bndtManageVO);
		bndtManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageList");
	}

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/detailBndtManage.do")
	public String detailBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO) {

		bndtManageService.selectBndtManage(bndtManageVO);

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageDetail");
	}

	/**
	 * 당직관리 등록 화면으로 이동한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/registBndtManage.do")
	public String registBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO) {
		
		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageRegist");
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtManage.do")
	public String insertBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(bndtManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageRegist");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtManageVO.setFrstRegisterId(loginVO.getUniqId());

		bndtManageService.insertBndtManage(bndtManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/**
	 * 당직관리 수정 화면으로 이동한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/editBndtManage.do")
	public String editBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO) {

		bndtManageService.selectBndtManage(bndtManageVO);

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageEdit");
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/updateBndtManage.do")
	public String updateBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(bndtManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtManageVO.setLastUpdusrId(loginVO.getUniqId());

		bndtManageService.updtBndtManage(bndtManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtManage.do")
	public String deleteBndtManage(
			@ModelAttribute BndtManageVO bndtManageVO,
			ModelMap model) {

		int iDiaryTotCnt = bndtManageService.selectBndtDiaryTotCnt(bndtManageVO);
		if (iDiaryTotCnt != 0) {
			model.addAttribute("errorMessage", "당직일지를 삭제하신 후 당직정보를 삭제 하세요.");
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageEdit");
		}
		
		bndtManageService.deleteBndtManage(bndtManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/****** 당직체크 관리 ******/
	/**
	 * 당직체크정보를 관리하기 위해 등록된 당직체크 목록을 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@IncludedInfo(name = "당직체크관리", order = 5291, gid = 50)
	@RequestMapping(value = "/uss/ion/bnt/listBndtCeckManage.do")
	@Secured("ROLE_USER")
	public String selectBndtCeckManageList(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		bndtCeckManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bndtManageService.selectBndtCeckManageList(bndtCeckManageVO));

		int totCnt = bndtManageService.selectBndtCeckManageListCnt(bndtCeckManageVO);
		bndtCeckManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		cmmUseService.populateCmmCodeList("COM071", "COM071_bndtCeckSe");

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageList");
	}

	/**
	 * 등록된 당직체크의 상세정보를 조회한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/detailBndtCeckManage.do")
	public String detailBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO) {

		bndtManageService.selectBndtCeckManage(bndtCeckManageVO);

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageDetail");
	}

	/**
	 * 당직체크 등록 화면으로 이동한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/registBndtCeckManage.do")
	public String registBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO) {

		cmmUseService.populateCmmCodeList("COM071", "COM071_bndtCeckSe");

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageRegist");
	}

	/**
	 * 당직체크정보를 신규로 등록한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtCeckManage.do")
	public String insertBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(bndtCeckManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageRegist");
		} 
		
		if (bndtManageService.selectBndtCeckManageDplctAt(bndtCeckManageVO) != 0) {
			model.addAttribute("dplctMessage", "이미 등록된 데이타입니다. 해당 데이타를 확인해 주세요");
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageRegist");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtCeckManageVO.setFrstRegisterId(loginVO.getUniqId());

		bndtManageService.insertBndtCeckManage(bndtCeckManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtCeckManage.do");
	}

	/**
	 * 당직체크수정 화면으로 이동한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/editBndtCeckManage.do")
	public String editBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO) {

		bndtManageService.selectBndtCeckManage(bndtCeckManageVO);

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageEdit");
	}

	/**
	 * 기 등록된 당직체크정보를 수정한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/updateBndtCeckManage.do")
	public String updateBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(bndtCeckManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtCeckManageEdit");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtCeckManageVO.setLastUpdusrId(loginVO.getUniqId());

		bndtManageService.updtBndtCeckManage(bndtCeckManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtCeckManage.do");
	}

	/**
	 * 기 등록된 당직체크정보를 삭제한다.
	 * 
	 * @param bndtCeckManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtCeckManage.do")
	public String deleteBndtCeckManage(
			@ModelAttribute BndtCeckManageVO bndtCeckManageVO, 
			ModelMap model) {

		bndtManageService.deleteBndtCeckManage(bndtCeckManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtCeckManage.do");
	}

	/****** 당직일지 ******/

	/**
	 * 등록된 당직일지의 정보를 조회한다.
	 * 
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/detailBndtDiary.do")
	public String detailBndtDiary(
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		model.addAttribute("bndtDiaryList", bndtManageService.selectBndtDiary(bndtDiaryVO));

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtDiaryDetail");
	}

	/**
	 * 당직일지 등록화면으로 이동한다.
	 * 
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/registBndtDiary.do")
	public String registBndtDiary(
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		model.addAttribute("bndtDiaryList", bndtManageService.selectBndtDiary(bndtDiaryVO));

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtDiaryRegist");
	}

	/**
	 * 당직일지정보를 신규로 등록한다.
	 * 
	 * @param diaryForInsert
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtDiary.do")
	public String insertBndtDiary(
			@RequestParam String diaryForInsert, 
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtDiaryVO.setFrstRegisterId(loginVO.getUniqId());

		bndtManageService.insertBndtDiary(bndtDiaryVO, diaryForInsert);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/**
	 * 당직일지 수정화면으로 이동한다.
	 * 
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/editBndtDiary.do")
	public String editBndtDiary(
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		model.addAttribute("bndtDiaryList", bndtManageService.selectBndtDiary(bndtDiaryVO));

		return WebUtil.adjustViewName("/uss/ion/bnt/BndtDiaryEdit");
	}

	/**
	 * 기 등록된 당직일지정보를 수정한다.
	 * 
	 * @param diaryForUpdate
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/updateBndtDiary.do")
	public String updateBndtDiary(
			@RequestParam String diaryForUpdate, 
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtDiaryVO.setLastUpdusrId(loginVO.getUniqId());

		bndtManageService.updtBndtDiary(bndtDiaryVO, diaryForUpdate);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/**
	 * 기 등록된 당직일지정보를 삭제한다.
	 * 
	 * @param bndtDiaryVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtDiary.do")
	public String deleteBndtDiary(
			@ModelAttribute BndtDiaryVO bndtDiaryVO, 
			ModelMap model) {

		bndtManageService.deleteBndtDiary(bndtDiaryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/bnt/listBndtManage.do");
	}

	/**
	 * 당직일괄등록화면 호출 및 당직일괄등록처리 프로세스
	 * 
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/BndtManageListPop.do")
	public String selectBndtManageBnde(
			HttpServletRequest request, 
			@ModelAttribute BndtManageVO bndtManageVO, 
			ModelMap model) 
	throws Exception {
		
		String sCmd = request.getParameter("cmd") == null ? "" : request.getParameter("cmd"); // 상세정보 구분
		if (!sCmd.equals("bnde")) {
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageBndeListPop");
		}
		
		String message = "";
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			if (!"".equals(file.getOriginalFilename())) {
				model.addAttribute("bndtManageList", bndtManageService.selectBndtManageBnde(file.getInputStream()));
			} else {
				message = MessageHelper.getMessage("fail.common.msg");
			}
		}
		model.addAttribute("message", message);
		
		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageBndeListPop");
	}

	/**
	 * 당직정보를 일괄등록처리한다.
	 * 
	 * @param checkedBndtManageForInsert
	 * @param bndtManageVO
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtManageBnde.do")
	public String insertBndtManageBnde(
			@RequestParam String checkedBndtManageForInsert,
			@ModelAttribute BndtManageVO bndtManageVO, 
			ModelMap model) {

		int iTemp = bndtManageService.selectBndtManageMonthCnt(bndtManageVO);
		if (iTemp != 0) {
			String sTempMessage = bndtManageVO.getBndtDe().substring(0, 4) + "년" + bndtManageVO.getBndtDe().substring(4, 6) + "월 데이타가 존재합니다.";
			model.addAttribute("message", sTempMessage);
			return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageBndeListPop");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		bndtManageVO.setFrstRegisterId(loginVO.getUniqId());

		bndtManageService.insertBndtManageBnde(bndtManageVO, checkedBndtManageForInsert);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.adjustViewName("/uss/ion/bnt/BndtManageBndeListPop");
	}

}
