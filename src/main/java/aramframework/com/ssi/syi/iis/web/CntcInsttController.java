package aramframework.com.ssi.syi.iis.web;

import java.util.List;

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
import aramframework.com.ssi.syi.ims.domain.CntcMessageVO;
import aramframework.com.ssi.syi.ims.service.CntcMessageService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 연계기관 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class CntcInsttController {

	@Autowired 
	private CntcInsttService cntcInsttService;

	@Autowired 
	private CntcMessageService cntcMessageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 연계기관 목록을 조회한다.
	 * 
	 * @param cntcInsttVO
	 */
	@IncludedInfo(name = "연계기관관리", order = 7000, gid = 70)
	@RequestMapping(value = "/ssi/syi/iis/listCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String listCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		cntcInsttVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		int totCnt = cntcInsttService.selectCntcInsttListCnt(cntcInsttVO);
		cntcInsttVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttList");
	}

	/**
	 * 연계기관 상세내역을 조회한다.
	 * 
	 * @param cntcInsttVO
	 * @param cntcSystemVO
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/detailCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String detailCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO, 
			@ModelAttribute CntcSystemVO cntcSystemVO,
			@ModelAttribute CntcServiceVO cntcServiceVO, 
			ModelMap model) {

		// 연계메시지 리스트박스 데이터
		CntcMessageVO cntcMessageVO = new CntcMessageVO();
		cntcMessageVO.getSearchVO().setRecordPerPage(999999);
		cntcMessageVO.getSearchVO().setFirstIndex(0);
		cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

		/* 연계기관 상세 */
		cntcInsttService.selectCntcInsttDetail(cntcInsttVO);

		/* 연계시스템 리스트 */
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		/* 연계서비스 리스트 */
		cntcServiceVO.getSearchVO().setRecordPerPage(999999);
		cntcServiceVO.getSearchVO().setFirstIndex(0);
		cntcServiceVO.getSearchVO().setSearchCondition("CodeList_InsttId");
		model.addAttribute("cntcServiceList", cntcInsttService.selectCntcServiceList(cntcServiceVO));

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttDetail");
	}

	/**
	 * 연계기관 등록화면으로 이동한다.
	 * 
	 * @param cntcInsttVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/registCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String registCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO) {
		
		return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttRegist");
	}

	/**
	 * 연계기관을 등록한다.
	 * 
	 * @param cntcInsttVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/insertCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String insertCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcInsttVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcInsttVO.setFrstRegisterId(loginVO.getUniqId());

		cntcInsttService.insertCntcInstt(cntcInsttVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/ssi/syi/iis/listCntcInstt.do");
	}

	/**
	 * 연계기관 수정화면으로 이동한다.
	 * 
	 * @param cntcInsttVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/editCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String editCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO) {

		cntcInsttService.selectCntcInsttDetail(cntcInsttVO);

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttEdit");
	}

	/**
	 * 연계기관을 수정한다.
	 * 
	 * @param cntcInsttVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String updateCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO, 
			BindingResult bindingResult, 
			ModelMap model) {
		
		beanValidator.validate(cntcInsttVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/ssi/syi/iis/CntcInsttEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcInsttVO.setLastUpdusrId(loginVO.getUniqId());

		cntcInsttService.updateCntcInstt(cntcInsttVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/ssi/syi/iis/listCntcInstt.do");
	}

	/**
	 * 연계기관을 삭제한다.
	 * 
	 * @param cntcInsttVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/deleteCntcInstt.do")
	@Secured("ROLE_ADMIN")
	public String deleteCntcInstt(
			@ModelAttribute CntcInsttVO cntcInsttVO, 
			ModelMap model) {

		cntcInsttService.deleteCntcInstt(cntcInsttVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/ssi/syi/iis/listCntcInstt.do");
	}

	/**
	 * 연계시스템  등록화면으로 이동한다.
	 * 
	 * @param cntcSystemVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/registCntcSystem.do")
	@Secured("ROLE_ADMIN")
	public String registCntcSystem(
			@ModelAttribute CntcSystemVO cntcSystemVO, 
			ModelMap model) {

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcSystemRegist");
	}

	/**
	 * 연계시스템을 등록한다.
	 * 
	 * @param cntcSystemVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/insertCntcSystem.do")
	@Secured("ROLE_ADMIN")
	public String insertCntcSystem(
			@ModelAttribute CntcSystemVO cntcSystemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcSystemVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

			return WebUtil.adjustViewName("/ssi/syi/iis/CntcSystemRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcSystemVO.setFrstRegisterId(loginVO.getUniqId());

		cntcInsttService.insertCntcSystem(cntcSystemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

	/**
	 * 연계시스템  수정화면으로 이동한다.
	 * 
	 * @param cntcSystemVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/editCntcSystem.do")
	@Secured("ROLE_ADMIN")
	public String editCntcSystem(
			@ModelAttribute CntcSystemVO cntcSystemVO, 
			ModelMap model) {

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

		// 연계메시지 리스트박스 데이터 2011.09.14
		CntcMessageVO cntcMessageVO = new CntcMessageVO();
		cntcMessageVO.getSearchVO().setRecordPerPage(999999);
		cntcMessageVO.getSearchVO().setFirstIndex(0);
		cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

		cntcInsttService.selectCntcSystemDetail(cntcSystemVO);

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcSystemEdit");
	}

	/**
	 * 연계시스템을 수정한다.
	 * 
	 * @param cntcSystemVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcSystem.do")
	@Secured("ROLE_ADMIN")
	public String updateCntcSystem(
			@ModelAttribute CntcSystemVO cntcSystemVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcSystemVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcInsttList", cntcInsttService.selectCntcInsttList(cntcInsttVO));

			// 연계메시지 리스트박스 데이터 2011.09.14
			CntcMessageVO cntcMessageVO = new CntcMessageVO();
			cntcMessageVO.getSearchVO().setRecordPerPage(999999);
			cntcMessageVO.getSearchVO().setFirstIndex(0);
			cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/iis/CntcSystemEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcSystemVO.setLastUpdusrId(loginVO.getUniqId());

		cntcInsttService.updateCntcSystem(cntcSystemVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

	/**
	 * 연계시스템을 삭제한다.
	 * 
	 * @param cntcSystemVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/deleteCntcSystem.do")
	@Secured("ROLE_ADMIN")
	public String deleteCntcSystem(
			@ModelAttribute CntcSystemVO cntcSystemVO) {

		cntcInsttService.deleteCntcSystem(cntcSystemVO);

		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

	/**
	 * 연계서비스 등록화면으로 이동한다.
	 * 
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/registCntcService.do")
	@Secured("ROLE_ADMIN")
	public String registCntcService(
			@ModelAttribute CntcServiceVO cntcServiceVO, 
			ModelMap model) {

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		List<CntcInsttVO> cntcInsttList = cntcInsttService.selectCntcInsttList(cntcInsttVO);
		model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		if (cntcServiceVO.getInsttId().equals("")) {
			if (cntcInsttList.size() > 0) {
				cntcInsttVO = cntcInsttList.get(0);
				cntcServiceVO.setInsttId(cntcInsttVO.getInsttId());
			}
		}
		cntcSystemVO.setInsttId(cntcServiceVO.getInsttId());
		model.addAttribute("cntcSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO;
		searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.getSearchVO().setRecordPerPage(999999);
		searchCntcMessageVO.getSearchVO().setFirstIndex(0);
		searchCntcMessageVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(searchCntcMessageVO));

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcServiceRegist");
	}

	/**
	 * 연계서비스를 등록한다.
	 * 
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/insertCntcService.do")
	@Secured("ROLE_ADMIN")
	public String insertCntcService(
			@ModelAttribute CntcServiceVO cntcServiceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcServiceVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			List<CntcInsttVO> cntcInsttList = cntcInsttService.selectCntcInsttList(cntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터
			CntcSystemVO cntcSystemVO = new CntcSystemVO();
			cntcSystemVO.getSearchVO().setRecordPerPage(999999);
			cntcSystemVO.getSearchVO().setFirstIndex(0);
			cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
			if (cntcServiceVO.getInsttId().equals("")) {
				if (cntcInsttList.size() > 0) {
					cntcInsttVO = cntcInsttList.get(0);
					cntcServiceVO.setInsttId(cntcInsttVO.getInsttId());
				}
			}
			cntcSystemVO.setInsttId(cntcServiceVO.getInsttId());
			model.addAttribute("cntcSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			// 연계메시지 리스트박스 데이터
			CntcMessageVO cntcMessageVO = new CntcMessageVO();
			cntcMessageVO.getSearchVO().setRecordPerPage(999999);
			cntcMessageVO.getSearchVO().setFirstIndex(0);
			cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/iis/CntcServiceRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcServiceVO.setFrstRegisterId(loginVO.getUniqId());

		cntcInsttService.insertCntcService(cntcServiceVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

	/**
	 * 연계서비스 수정화면으로 이동한다.
	 * 
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/editCntcService.do")
	@Secured("ROLE_ADMIN")
	public String editCntcService(
			@ModelAttribute CntcServiceVO cntcServiceVO, 
			ModelMap model) {

		// 연계기관 리스트박스 데이터
		CntcInsttVO cntcInsttVO = new CntcInsttVO();
		cntcInsttVO.getSearchVO().setRecordPerPage(999999);
		cntcInsttVO.getSearchVO().setFirstIndex(0);
		cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
		List<CntcInsttVO> cntcInsttList = cntcInsttService.selectCntcInsttList(cntcInsttVO);
		model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
		CntcSystemVO cntcSystemVO = new CntcSystemVO();
		cntcSystemVO.getSearchVO().setRecordPerPage(999999);
		cntcSystemVO.getSearchVO().setFirstIndex(0);
		cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
		if (cntcServiceVO.getInsttId().equals("")) {
			if (cntcInsttList.size() > 0) {
				cntcInsttVO = cntcInsttList.get(0);
				cntcServiceVO.setInsttId(cntcInsttVO.getInsttId());
			}
		}
		cntcSystemVO.setInsttId(cntcServiceVO.getInsttId());
		model.addAttribute("cntcSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

		// 연계메시지 리스트박스 데이터
		CntcMessageVO cntcMessageVO = new CntcMessageVO();
		cntcMessageVO.getSearchVO().setRecordPerPage(999999);
		cntcMessageVO.getSearchVO().setFirstIndex(0);
		cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
		model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

		cntcInsttService.selectCntcServiceDetail(cntcServiceVO);

		return WebUtil.adjustViewName("/ssi/syi/iis/CntcServiceEdit");
	}

	/**
	 * 연계서비스를 수정한다.
	 * 
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcService.do")
	@Secured("ROLE_ADMIN")
	public String updateCntcService(
			@ModelAttribute CntcServiceVO cntcServiceVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(cntcServiceVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 연계기관 리스트박스 데이터
			CntcInsttVO cntcInsttVO = new CntcInsttVO();
			cntcInsttVO.getSearchVO().setRecordPerPage(999999);
			cntcInsttVO.getSearchVO().setFirstIndex(0);
			cntcInsttVO.getSearchVO().setSearchCondition("CodeList");
			List<CntcInsttVO> cntcInsttList = cntcInsttService.selectCntcInsttList(cntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터
			CntcSystemVO cntcSystemVO = new CntcSystemVO();
			cntcSystemVO.getSearchVO().setRecordPerPage(999999);
			cntcSystemVO.getSearchVO().setFirstIndex(0);
			cntcSystemVO.getSearchVO().setSearchCondition("CodeList");
			if (cntcServiceVO.getInsttId().equals("")) {
				if (cntcInsttList.size() > 0) {
					cntcInsttVO = cntcInsttList.get(0);
					cntcServiceVO.setInsttId(cntcInsttVO.getInsttId());
				}
			}
			cntcSystemVO.setInsttId(cntcServiceVO.getInsttId());
			model.addAttribute("cntcSystemList", cntcInsttService.selectCntcSystemList(cntcSystemVO));

			// 연계메시지 리스트박스 데이터
			CntcMessageVO cntcMessageVO = new CntcMessageVO();
			cntcMessageVO.getSearchVO().setRecordPerPage(999999);
			cntcMessageVO.getSearchVO().setFirstIndex(0);
			cntcMessageVO.getSearchVO().setSearchCondition("CodeList");
			model.addAttribute("cntcMessageList", cntcMessageService.selectCntcMessageList(cntcMessageVO));

			return WebUtil.adjustViewName("/ssi/syi/iis/CntcServiceEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		cntcServiceVO.setLastUpdusrId(loginVO.getUniqId());

		cntcInsttService.updateCntcService(cntcServiceVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

	/**
	 * 연계서비스를 삭제한다.
	 * 
	 * @param cntcServiceVO
	 */
	@RequestMapping(value = "/ssi/syi/iis/deleteCntcService.do")
	@Secured("ROLE_ADMIN")
	public String deleteCntcService(
			@ModelAttribute CntcServiceVO cntcServiceVO) {

		cntcInsttService.deleteCntcService(cntcServiceVO);

		return "forward:/ssi/syi/iis/detailCntcInstt.do";
	}

}