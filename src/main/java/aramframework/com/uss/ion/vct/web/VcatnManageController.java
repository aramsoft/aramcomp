package aramframework.com.uss.ion.vct.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.vct.domain.IndvdlYrycManageVO;
import aramframework.com.uss.ion.vct.domain.VcatnManageVO;
import aramframework.com.uss.ion.vct.service.VcatnManageService;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 휴 가관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 휴가관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class VcatnManageController {

	@Autowired
	private VcatnManageService vcatnManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	@IncludedInfo(name = "휴가관리", order = 5280, gid = 50)
	@RequestMapping(value = "/uss/ion/vct/listVcatn.do")
	@Secured("ROLE_USER")
	public String listVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}
		model.addAttribute("yearList", yearList);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		vcatnManageVO.setApplcntId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		vcatnManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", vcatnManageService.selectVcatnManageList(vcatnManageVO));

		int totCnt = vcatnManageService.selectVcatnManageListCnt(vcatnManageVO);
		vcatnManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnList");
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/detailVcatn.do")
	public String detailVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		// 등록 상세정보
		vcatnManageService.selectVcatnManage(vcatnManageVO);

		IndvdlYrycManageVO indvdlYrycManageVO = vcatnManageService.selectIndvdlYrycManage(vcatnManageVO.getApplcntId());
		model.addAttribute("indvdlYrycManageVO", indvdlYrycManageVO);

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnDetail");
	}

	/**
	 * 휴가관리 등록 화면으로 이동한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/registVcatn.do")
	public String registVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO,
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		vcatnManageVO.setApplcntId(loginVO.getUniqId());
		vcatnManageVO.setApplcntNm(loginVO.getName());
		vcatnManageVO.setOrgnztNm(loginVO.getOrgnztNm());

		cmmUseService.populateCmmCodeList("COM056", "COM056_vcatnSe");

		model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(loginVO.getUniqId()));

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnRegist");
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/insertVcatn.do")
	public String insertVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(vcatnManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(loginVO.getUniqId()));
			return WebUtil.adjustViewName("/uss/ion/vct/VcatnRegist");
		} 
		
		if (vcatnManageVO.getSanctnerId() != null)
			vcatnManageVO.setConfmAt("A");
		vcatnManageVO.setApplcntId(loginVO.getUniqId());
		vcatnManageVO.setFrstRegisterId(loginVO.getUniqId());

		int iTemp = 0;
		String result = null;
		String resultMessage = null;

		// 시작일자 포함여부
		vcatnManageVO.setSearchKeyword(StringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		iTemp = vcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);
		// 종료일자 포함여부
		vcatnManageVO.setSearchKeyword(StringUtil.removeMinusChar(vcatnManageVO.getEndde()));
		iTemp += vcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);

		if (iTemp != 0) {
			resultMessage = "휴가일자가 중복되었습니다. 휴가일자를 확인해 주세요.";
			model.addAttribute("errorMessage", resultMessage);

			model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(loginVO.getUniqId()));

			vcatnManageVO.setBgnde(DateUtil.formatDate(vcatnManageVO.getBgnde(), "-"));
			vcatnManageVO.setEndde(DateUtil.formatDate(vcatnManageVO.getEndde(), "-"));

			return WebUtil.adjustViewName("/uss/ion/vct/VcatnRegist");
		}
		
		result = vcatnManageService.insertVcatnManage(vcatnManageVO);

		if (result.equals("01")) {
			model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	        return WebUtil.redirectJsp(model, "/uss/ion/vct/listVcatn.do");
		} else {

			if (result.equals("99"))
				resultMessage = "휴가일자 지정 오류 - 휴가시작일자/종료일자를 확인하세요.";
			if (result.equals("09"))
				resultMessage = "연차사용시 휴가는 당해연도만 가능합니다.";
			if (result.equals("02"))
				resultMessage = "연차휴가 등록실패(잔여연차 부족)";
			if (result.equals("03"))
				resultMessage = "반차휴가 등록실패(잔여연차 부족)";
			model.addAttribute("errorMessage", resultMessage);

			model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(loginVO.getUniqId()));
			
			vcatnManageVO.setBgnde(DateUtil.formatDate(vcatnManageVO.getBgnde(), "-"));
			vcatnManageVO.setEndde(DateUtil.formatDate(vcatnManageVO.getEndde(), "-"));

			return WebUtil.adjustViewName("/uss/ion/vct/VcatnRegist");
		}
	}

	/**
	 * 등록된 휴가관리의 상세정보를 편집한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/editVcatn.do")
	public String editVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		// 등록 상세정보
		vcatnManageService.selectVcatnManage(vcatnManageVO);

		model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(vcatnManageVO.getApplcntId()));

		cmmUseService.populateCmmCodeList("COM056", "COM056_vcatnSe");

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnEdit");
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/updateVcatn.do")
	public String updateVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(vcatnManageVO, bindingResult); 
		 //validation 수행
		if (bindingResult.hasErrors()) {
			model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(vcatnManageVO.getApplcntId()));

			return WebUtil.adjustViewName("/uss/ion/vct/VcatnEdit");
		} 
		  
		vcatnManageService.updateVcatnManage(vcatnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/vct/listVcatn.do");
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/deleteVcatn.do")
	public String deleteVcatn(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		vcatnManageService.deleteVcatnManage(vcatnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/vct/listVcatn.do");
	}

	/*** 승인관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * 
	 * @param vcatnManageVO
	 */
	@IncludedInfo(name = "휴가승인관리", order = 5281, gid = 50)
	@RequestMapping(value = "/uss/ion/vct/listVcatnConfm.do")
	@Secured("ROLE_USER")
	public String listVcatnConfm(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}
		model.addAttribute("yearList", yearList);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		vcatnManageVO.setSanctnerId(loginVO.getUniqId()); // 사용자가 승인권자인지 조건값  setting

		vcatnManageVO.setSearchKeyword(vcatnManageVO.getSearchYear() + vcatnManageVO.getSearchMonth());

		PaginationInfo paginationInfo = new PaginationInfo();
		vcatnManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", vcatnManageService.selectVcatnManageConfmList(vcatnManageVO));

		int totCnt = vcatnManageService.selectVcatnManageConfmListCnt(vcatnManageVO);
		vcatnManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnConfmList");
	}

	/**
	 * 휴가승인화면으로 이동한다..
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/editVcatnConfm.do")
	public String editVcatnConfm(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			ModelMap model) {

		// 등록 상세정보
		vcatnManageService.selectVcatnManage(vcatnManageVO);

		model.addAttribute("indvdlYrycManageVO", vcatnManageService.selectIndvdlYrycManage(vcatnManageVO.getApplcntId()));

		return WebUtil.adjustViewName("/uss/ion/vct/VcatnConfmEdit");
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 * 
	 * @param vcatnManageVO
	 */
	@RequestMapping(value = "/uss/ion/vct/updateVcatnConfm.do")
	public String updateVcatnConfm(
			@ModelAttribute VcatnManageVO vcatnManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(vcatnManageVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/ion/vct/VcatnConfmEdit");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		vcatnManageVO.setSanctnerId(loginVO.getUniqId());
		vcatnManageVO.setLastUpdusrId(loginVO.getUniqId());

		vcatnManageService.updateVcatnManageConfm(vcatnManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/vct/listVcatnConfm.do");
	}

}
