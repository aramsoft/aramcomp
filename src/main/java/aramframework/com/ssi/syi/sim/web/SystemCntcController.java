package aramframework.com.ssi.syi.sim.web;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.ssi.syi.iis.domain.CntcInsttVO;
import aramframework.com.ssi.syi.iis.domain.CntcServiceVO;
import aramframework.com.ssi.syi.iis.domain.CntcSystemVO;
import aramframework.com.ssi.syi.iis.service.CntcInsttService;
import aramframework.com.ssi.syi.sim.domain.SystemCntcVO;
import aramframework.com.ssi.syi.sim.service.SystemCntcService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시스템연계 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SystemCntcController {

	@Autowired
	private SystemCntcService systemCntcService;

	@Autowired
	private CntcInsttService cntcInsttService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 시스템연계 목록을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	@IncludedInfo(name = "시스템연계관리", order = 7020, gid = 70)
	@RequestMapping(value = "/ssi/syi/sim/listSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String listSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		systemCntcVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", systemCntcService.selectSystemCntcList(systemCntcVO));

		int totCnt = systemCntcService.selectSystemCntcListCnt(systemCntcVO);
		systemCntcVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcList");
	}

	/**
	 * 시스템연계 상세내역을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/detailSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String detailSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		systemCntcService.selectSystemCntcDetail(systemCntcVO);

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
		model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
		model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계서비스 리스트박스 데이터
		CntcServiceVO cntcServiceVO = new CntcServiceVO();
		cntcServiceVO.getSearchVO().setRecordPerPage(999999);
		cntcServiceVO.getSearchVO().setFirstIndex(0);
		cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
		cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
		cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
		model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcDetail");
	}

	/**
	 * 시스템연계를 등록한다.(등록폼)
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/registSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String registSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
		model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
		model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계서비스 리스트박스 데이터
		CntcServiceVO cntcServiceVO = new CntcServiceVO();
		cntcServiceVO.getSearchVO().setRecordPerPage(999999);
		cntcServiceVO.getSearchVO().setFirstIndex(0);
		cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
		cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
		cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
		model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcRegist");
	}

	/**
	 * 시스템연계를 등록한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/insertSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String insertSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(systemCntcVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

			// 연계시스템 리스트박스 데이터
			CntcSystemVO cntcSystemVO = new CntcSystemVO();
			cntcSystemVO.getSearchVO().setRecordPerPage(999999);
			cntcSystemVO.getSearchVO().setFirstIndex(0);
			cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
			cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
			model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
			model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			// 연계서비스 리스트박스 데이터
			CntcServiceVO cntcServiceVO = new CntcServiceVO();
			cntcServiceVO.getSearchVO().setRecordPerPage(999999);
			cntcServiceVO.getSearchVO().setFirstIndex(0);
			cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
			cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
			cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
			model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

			return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		systemCntcVO.setFrstRegisterId(loginVO.getUniqId());

		systemCntcService.insertSystemCntc(systemCntcVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/ssi/syi/sim/listSystemCntc.do");
	}

	/**
	 * 시스템연계를 수정한다.(수정폼)
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/editSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String editSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		systemCntcService.selectSystemCntcDetail(systemCntcVO);

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO;
		cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
		model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
		model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계서비스 리스트박스 데이터
		CntcServiceVO cntcServiceVO = new CntcServiceVO();
		cntcServiceVO.getSearchVO().setRecordPerPage(999999);
		cntcServiceVO.getSearchVO().setFirstIndex(0);
		cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
		cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
		cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
		model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcEdit");
	}

	/**
	 * 시스템연계를 수정한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/updateSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String updateSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(systemCntcVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

			// 연계시스템 리스트박스 데이터
			CntcSystemVO cntcSystemVO = new CntcSystemVO();
			cntcSystemVO.getSearchVO().setRecordPerPage(999999);
			cntcSystemVO.getSearchVO().setFirstIndex(0);
			cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
			cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
			model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
			model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			// 연계서비스 리스트박스 데이터
			CntcServiceVO cntcServiceVO = new CntcServiceVO();
			cntcServiceVO.getSearchVO().setRecordPerPage(999999);
			cntcServiceVO.getSearchVO().setFirstIndex(0);
			cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
			cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
			cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
			model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

			return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		systemCntcVO.setLastUpdusrId(loginVO.getUniqId());

		systemCntcService.updateSystemCntc(systemCntcVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/ssi/syi/sim/listSystemCntc.do");
	}

	/**
	 * 시스템연계를 삭제한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/deleteSystemCntc.do")
	@Secured("ROLE_ADMIN")
	public String deleteSystemCntc(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		systemCntcService.deleteSystemCntc(systemCntcVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/ssi/syi/sim/listSystemCntc.do");
	}

	/**
	 * 시스템연계 승인 목록을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	@IncludedInfo(name = "시스템연계승인관리", order = 7021, gid = 70)
	@RequestMapping(value = "/ssi/syi/sim/listSystemCntcConfirm.do")
	@Secured("ROLE_ADMIN")
	public String listSystemCntcConfirm(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		systemCntcVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", systemCntcService.selectSystemCntcList(systemCntcVO));

		int totCnt = systemCntcService.selectSystemCntcListCnt(systemCntcVO);
		systemCntcVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcConfirmList");
	}

	/**
	 * 시스템연계 승인 상세내역을 조회한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/editSystemCntcConfirm.do")
	@Secured("ROLE_ADMIN")
	public String editSystemCntcConfirm(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		systemCntcService.selectSystemCntcDetail(systemCntcVO);

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		cntcSystemVO.setInsttId(systemCntcVO.getProvdInsttId());
		model.addAttribute("cntcProvdSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		cntcSystemVO.setInsttId(systemCntcVO.getRequstInsttId());
		model.addAttribute("cntcRequstSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계서비스 리스트박스 데이터
		CntcServiceVO cntcServiceVO = new CntcServiceVO();
		cntcServiceVO.getSearchVO().setRecordPerPage(999999);
		cntcServiceVO.getSearchVO().setFirstIndex(0);
		cntcServiceVO.getSearchVO().setSearchCondition("CodeList");
		cntcServiceVO.setInsttId(systemCntcVO.getProvdInsttId());
		cntcServiceVO.setSysId(systemCntcVO.getProvdSysId());
		model.addAttribute("cntcProvdServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

		return WebUtil.adjustViewName("/ssi/syi/sim/SystemCntcConfirmEdit");
	}

	/**
	 * 시스템연계 승인 한다.
	 * 
	 * @param systemCntcVO
	 */
	@RequestMapping(value = "/ssi/syi/sim/confirmSystemCntcConfirm.do")
	@Secured("ROLE_ADMIN")
	public String confirmSystemCntcConfirm(
			@ModelAttribute SystemCntcVO systemCntcVO, 
			ModelMap model) {

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		systemCntcVO.setLastUpdusrId(loginVO.getUniqId());

		systemCntcService.confirmSystemCntc(systemCntcVO);

        return WebUtil.redirectJsp(model, "/ssi/syi/sim/listSystemCntcConfirm.do");
	}

}