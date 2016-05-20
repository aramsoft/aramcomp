package aramframework.com.uss.ion.mtg.web;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.FileVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.FileMngService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceFxtrsVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceManageVO;
import aramframework.com.uss.ion.mtg.domain.MtgPlaceResveVO;
import aramframework.com.uss.ion.mtg.service.MtgPlaceManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 회의실관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 회의실관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class MtgPlaceManageController {

	@Autowired
	private MtgPlaceManageService mtgPlaceManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private FileMngService fileMngService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@IncludedInfo(name = "회의실관리", order =5260, gid = 50)
	@RequestMapping(value = "/uss/ion/mtg/listMtgPlace.do")
	@Secured("ROLE_USER")
	public String listMtgPlace(
			@ModelAttribute MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		mtgPlaceManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", mtgPlaceManageService.selectMtgPlaceManageList(mtgPlaceManageVO));
		int totCnt = mtgPlaceManageService.selectMtgPlaceManageListCnt(mtgPlaceManageVO);
	
		mtgPlaceManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceList");
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/detailMtgPlace.do")
	@Secured("ROLE_USER")
	public String detailMtgPlace(
			MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) {

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		MtgPlaceFxtrsVO	mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
		mtgPlaceFxtrsVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());
		model.addAttribute("mtgPlaceFxtrsList", mtgPlaceManageService.selectFxtrsManageList(mtgPlaceFxtrsVO));

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceDetail");
	}

	/**
	 * 회의실관리 등록 화면으로 이동한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/registMtgPlace.do")
	@Secured("ROLE_USER")
	public String registMtgPlace(
			@ModelAttribute MtgPlaceManageVO mtgPlaceManageVO, 
			ModelMap model) {

		MtgPlaceFxtrsVO	mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
		mtgPlaceFxtrsVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());
		model.addAttribute("mtgPlaceFxtrsList", mtgPlaceManageService.selectFxtrsManageList(mtgPlaceFxtrsVO));

		cmmUseService.populateCmmCodeList("COM070", "COM070_lcSe");

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceRegist");
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/insertMtgPlace.do")
	@Secured("ROLE_USER")
	public String insertMtgPlace(
			MultipartHttpServletRequest multiRequest,
			@RequestParam String checkedMtgPlacesForInsert, 
			@ModelAttribute MtgPlaceManageVO mtgPlaceManageVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(mtgPlaceManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			MtgPlaceFxtrsVO	mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
			mtgPlaceFxtrsVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());
			model.addAttribute("mtgPlaceFxtrsList", mtgPlaceManageService.selectFxtrsManageList(mtgPlaceFxtrsVO));

			return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceRegist");
		}
		
		// 첨부파일 관련 첨부파일ID 생성
		mtgPlaceManageVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "MTG_"));

		mtgPlaceManageService.insertMtgPlaceManage(mtgPlaceManageVO, checkedMtgPlacesForInsert);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlace.do");
	}

	/**
	 * 회의실관리 수정 화면으로 이동한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/editMtgPlace.do")
	@Secured("ROLE_USER")
	public String editMtgPlace(
			MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) {

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		MtgPlaceFxtrsVO	mtgPlaceFxtrsVO = new MtgPlaceFxtrsVO();
		mtgPlaceFxtrsVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());
		model.addAttribute("mtgPlaceFxtrsList", mtgPlaceManageService.selectFxtrsManageList(mtgPlaceFxtrsVO));

		cmmUseService.populateCmmCodeList("COM070", "COM070_lcSe");

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceEdit");
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * 
	 * @param checkedMtgPlacesForInsert
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/updateMtgPlace.do")
	@Secured("ROLE_USER")
	public String updateMtgPlace(
			MultipartHttpServletRequest multiRequest, 
			@RequestParam String checkedMtgPlacesForInsert, 
			@ModelAttribute MtgPlaceManageVO mtgPlaceManageVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(mtgPlaceManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceEdit");
		} 

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = mtgPlaceManageVO.getAtchFileId();
		mtgPlaceManageVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "MTG_", atchFileId));

		mtgPlaceManageService.updateMtgPlaceManage(mtgPlaceManageVO, checkedMtgPlacesForInsert);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlace.do");
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * 
	 * @param mtgPlaceManageVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/deleteMtgPlace.do")
	@Secured("ROLE_USER")
	public String deleteMtgPlace(
			@ModelAttribute MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) {
		
		mtgPlaceManageService.deleteMtgPlaceManage(mtgPlaceManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlace.do");
	}

	/**
	 * 등록된 회의실관리의 이미지 상세정보를 조회한다.
	 * 
	 * @param sTmMtgPlaceId
	 */
	@RequestMapping(value = "/uss/ion/mtg/detailMtgPlaceImage.do")
	public String detailMtgPlaceImage(
			@RequestParam String sTmMtgPlaceId, 
			ModelMap model) {
		
		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		mtgPlaceManageVO.setMtgPlaceId(sTmMtgPlaceId);

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(mtgPlaceManageVO.getAtchFileId());
		List<FileVO> result = fileMngService.selectImageFileList(fileVO);

		model.addAttribute("fileList", result);

		return "aramframework/com/uss/ion/mtg/MtgPlaceImageDetail";
	}

	/**** 회의실 예약 ****/

	/**
	 * 회의실예약 정보를 관리하기 위해 등록된 회의실예약 목록을 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@IncludedInfo(name = "회의실예약관리", order = 5261, gid = 50)
	@RequestMapping(value = "/uss/ion/mtg/listMtgPlaceResve.do")
	public String listMtgPlaceResve(
			@ModelAttribute MtgPlaceResveVO mtgPlaceResveVO, 
			ModelMap model) {
		
		/* *****************************************************************
		 * // 캘런더 설정 로직
		 * *****************************************************************
		 */
		Calendar calNow = Calendar.getInstance();
		/*
		 * String strYear = request.getParameter("year"); 
		 * String strMonth = request.getParameter("month"); 
		 * String strDay = request.getParameter("day");
		 */
		String strSearchDay = "";

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if (mtgPlaceResveVO.getResveDe() == null) {
			strSearchDay = Integer.toString(iNowYear);
			strSearchDay += dateTypeIntForString(iNowMonth + 1);
			strSearchDay += dateTypeIntForString(iNowDay);
			mtgPlaceResveVO.setResveDe(strSearchDay);
		}
		
		model.addAttribute("resultList", mtgPlaceManageService.selectMtgPlaceResveManageList(mtgPlaceResveVO));

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveList");
	}

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/detailMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String detailMtgPlaceResve(
			MtgPlaceResveVO mtgPlaceResveVO, 
			ModelMap model) {

		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		mtgPlaceManageVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId());

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		mtgPlaceResveVO = mtgPlaceManageService.selectMtgPlaceResveDetail(mtgPlaceResveVO);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mtgPlaceResveVO.setUsidTemp(loginVO.getUniqId());

		String resveBeginTm = mtgPlaceResveVO.getResveBeginTm();
		String resveEndTm = mtgPlaceResveVO.getResveEndTm();
		if (resveBeginTm.length() == 3)
			resveBeginTm = "0" + resveBeginTm.substring(0, 1) + ":" + resveBeginTm.substring(1, 3);
		else if (resveBeginTm.length() == 4)
			resveBeginTm = resveBeginTm.substring(0, 2) + ":" + resveBeginTm.substring(2, 4);
		if (resveEndTm.length() == 3)
			resveEndTm = "0" + resveEndTm.substring(0, 1) + ":" + resveEndTm.substring(1, 3);
		else if (resveEndTm.length() == 4)
			resveEndTm = resveEndTm.substring(0, 2) + ":" + resveEndTm.substring(2, 4);

		mtgPlaceResveVO.setResveBeginTm(resveBeginTm);
		mtgPlaceResveVO.setResveEndTm(resveEndTm);
		
		model.addAttribute(mtgPlaceResveVO);
		
		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveDetail");
	}

	/**
	 * 회의실예약 신청 화면을 조회한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/registMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String registMtgPlaceResve(
			@ModelAttribute MtgPlaceResveVO mtgPlaceResveVO, 
			ModelMap model) {

		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		mtgPlaceManageVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId());

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mtgPlaceResveVO.setResveManNm(loginVO.getName());
		mtgPlaceResveVO.setResevOrgnztNm(loginVO.getOrgnztNm());

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveRegist");
	}

	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/insertMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String insertMtgPlaceResve(
			@ModelAttribute MtgPlaceResveVO mtgPlaceResveVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mtgPlaceResveVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveRegist");
		} 

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mtgPlaceResveVO.setResveManId(loginVO.getUniqId());
		mtgPlaceResveVO.setFrstRegisterId(loginVO.getUniqId());

		mtgPlaceManageService.insertMtgPlaceResve(mtgPlaceResveVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlaceResve.do");
	}

	/**
	 * 등록된 회의실예약 상세정보를 수정하는 화면으로 이동한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/editMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String editMtgPlaceResve(
			MtgPlaceResveVO mtgPlaceResveVO, 
			ModelMap model) {

		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		mtgPlaceManageVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId());

		model.addAttribute(mtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));
		model.addAttribute(mtgPlaceManageService.selectMtgPlaceResveDetail(mtgPlaceResveVO));

		return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveEdit");
	}

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/updateMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String updateMtgPlaceResve(
			@ModelAttribute MtgPlaceResveVO mtgPlaceResveVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mtgPlaceResveVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/mtg/MtgPlaceResveEdit");
		} 
		
		mtgPlaceManageService.updateMtgPlaceResve(mtgPlaceResveVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlaceResve.do");
	}

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
	 * 
	 * @param mtgPlaceResveVO
	 */
	@RequestMapping(value = "/uss/ion/mtg/deleteMtgPlaceResve.do")
	@Secured("ROLE_USER")
	public String deleteMtgPlaceResve(
			@ModelAttribute MtgPlaceResveVO mtgPlaceResveVO, 
			ModelMap model) {

		mtgPlaceManageService.deleteMtgPlaceResve(mtgPlaceResveVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/mtg/listMtgPlaceResve.do");
	}

	/**
	 * 회의실 중복여부 체크.
	 * 
	 * @param sTempResveDe
	 * @param sTempResveBeginTm
	 * @param sTempResveEndTm
	 * @param sTempResveEndTm
	 * @param sTempResveId
	 */
	@RequestMapping(value = "/uss/ion/mtg/checkMtgPlaceResveDplact.do")
	public String checkMtgPlaceResveDplact(
			@RequestParam String sTempResveDe, 
			@RequestParam String sTempResveBeginTm,
			@RequestParam String sTempResveEndTm, 
			@RequestParam String sTempMtgPlaceId,
			@RequestParam String sTempResveId, 
			ModelMap model) {
		
		MtgPlaceResveVO mtgPlaceResveVO = new MtgPlaceResveVO();
		mtgPlaceResveVO.setResveDe(sTempResveDe);
		mtgPlaceResveVO.setMtgPlaceId(sTempMtgPlaceId);
		mtgPlaceResveVO.setResveBeginTm(sTempResveBeginTm);
		mtgPlaceResveVO.setResveEndTm(sTempResveEndTm);
		mtgPlaceResveVO.setResveId(sTempResveId);

		int dplactCeckCnt = mtgPlaceManageService.mtgPlaceResveDplactCeck(mtgPlaceResveVO);

		model.addAttribute("dplactCeck", dplactCeckCnt);
		return "aramframework/com/uss/ion/mtg/MtgPlaceResveDplactCeck";
	}

	/**
	 * 0을 붙여 반환
	 * 
	 * @param iInput
	 */
	private String dateTypeIntForString(int iInput) {
		String sOutput = "";
		if (Integer.toString(iInput).length() == 1) {
			sOutput = "0" + Integer.toString(iInput);
		} else {
			sOutput = Integer.toString(iInput);
		}
		return sOutput;
	}
}
