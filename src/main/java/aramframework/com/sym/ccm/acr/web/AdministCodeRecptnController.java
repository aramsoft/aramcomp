package aramframework.com.sym.ccm.acr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.ccm.acr.domain.AdministCodeRecptnVO;
import aramframework.com.sym.ccm.acr.service.AdministCodeRecptnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 법정동코드를 수신에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class AdministCodeRecptnController {

	@Autowired
	private AdministCodeRecptnService administCodeManageService;

	@Autowired
	private CmmUseService cmmUseService;

	/**
	 * 법정동코드수신 개인화페이지용 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/acr/listAdministCodeRecptnMainPage.do")
	public String listAdministCodeRecptnMainPage(
			@ModelAttribute AdministCodeRecptnVO administCodeRecptnVO, 
			ModelMap model) {

		administCodeRecptnVO.setSizeAndOffset(5,0);

		model.addAttribute("resultList", administCodeManageService.selectAdministCodeRecptnList(administCodeRecptnVO));

		return WebUtil.adjustViewName("/sym/ccm/acr/AdministCodeRecptnMainPage");
	}

	/**
	 * 법정동코드수신 목록을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	@IncludedInfo(name = "행정코드수신", order = 6060, gid = 60)
	@RequestMapping(value = "/sym/ccm/acr/listAdministCodeRecptn.do")
	@Secured("ROLE_USER")
	public String listAdministCodeRecptn(
			@ModelAttribute AdministCodeRecptnVO administCodeRecptnVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		administCodeRecptnVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", administCodeManageService.selectAdministCodeRecptnList(administCodeRecptnVO));
		int totCnt = administCodeManageService.selectAdministCodeRecptnListCnt(administCodeRecptnVO);

		administCodeRecptnVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/acr/AdministCodeRecptnList");
	}

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/acr/detailAdministCodeRecptn.do")
	public String detailAdministCodeRecptn(
			AdministCodeRecptnVO administCodeRecptnVO, 
			ModelMap model)  {

		/* 법정동코드 조회 */
		administCodeRecptnVO = administCodeManageService.selectAdministCodeDetail(administCodeRecptnVO);

		model.addAttribute(administCodeRecptnVO);

		/* 법정동코드수신 리스트 */
		AdministCodeRecptnVO vo = new AdministCodeRecptnVO();
		vo.setSizeAndOffset(9999999, 0);
		vo.setSearchCondition("CodeList");
		vo.setAdministZoneSe(administCodeRecptnVO.getAdministZoneSe());
		vo.setAdministZoneCode(administCodeRecptnVO.getAdministZoneCode());

		model.addAttribute("administCodeRecptnList", administCodeManageService.selectAdministCodeRecptnList(vo));

		/* 변경구분코드 */
		cmmUseService.populateCmmCodeList("COM043", "COM043_changeSe");
		/* 처리구분코드 */
		cmmUseService.populateCmmCodeList("COM044", "COM044_processSe");

		return WebUtil.adjustViewName("/sym/ccm/acr/AdministCodeRecptnDetail");
	}

	/**
	 * TEST 용 Controller 실 적용시 Job Scheduler 에 등록하여 처리한다. 법정동코드를 수신처리한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/acr/registAdministCodeRecptn.do")
	public String registAdministCodeRecptn(
			@ModelAttribute AdministCodeRecptnVO administCodeRecptnVO) {
		
		return WebUtil.adjustViewName("/sym/ccm/acr/AdministCodeRecptnRegist");
	}

	/**
	 * TEST 용 Controller 실 적용시 Job Scheduler 에 등록하여 처리한다. 법정동코드를 수신처리한다.
	 * 
	 * @param administCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/acr/insertAdministCodeRecptn.do")
	public String insertAdministCodeRecptn(
			@ModelAttribute AdministCodeRecptnVO administCodeRecptnVO, 
			ModelMap model) {
		
		administCodeManageService.insertAdministCodeRecptn();
		
        return WebUtil.redirectJsp(model, "/sym/ccm/acr/listAdministCodeRecptn.do");
	}
	
}