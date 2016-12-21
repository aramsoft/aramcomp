package aramframework.com.sym.ccm.icr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.ccm.icr.domain.InsttCodeRecptnVO;
import aramframework.com.sym.ccm.icr.service.InsttCodeRecptnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 기관코드를 수신에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 
 * 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class InsttCodeRecptnController {

	@Autowired
	private InsttCodeRecptnService insttCodeManageService;

	@Autowired
	private CmmUseService cmmUseService;

	/**
	 * 기관코드수신 개인화페이지용 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/icr/listInsttCodeRecptnMainPage.do")
	public String listInsttCodeRecptnMainPage(
			@ModelAttribute InsttCodeRecptnVO insttCodeRecptnVO, 
			ModelMap model) {

		insttCodeRecptnVO.getSearchVO().setSizeAndOffset(5, 0);

		model.addAttribute("resultList", insttCodeManageService.selectInsttCodeRecptnList(insttCodeRecptnVO));

		return WebUtil.adjustViewName("/sym/ccm/icr/InsttCodeRecptnMainPage");
	}

	/**
	 * 기관코드수신 목록을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	@IncludedInfo(name = "기관코드수신", order = 6070, gid = 60)
	@RequestMapping(value = "/sym/ccm/icr/listInsttCodeRecptn.do")
	@Secured("ROLE_USER")
	public String listInsttCodeRecptn(
			@ModelAttribute InsttCodeRecptnVO insttCodeRecptnVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		insttCodeRecptnVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", insttCodeManageService.selectInsttCodeRecptnList(insttCodeRecptnVO));
		int totCnt = insttCodeManageService.selectInsttCodeRecptnListCnt(insttCodeRecptnVO);

		insttCodeRecptnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/ccm/icr/InsttCodeRecptnList");
	}

	/**
	 * 기관코드 상세내역을 조회한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/icr/detailInsttCodeRecptn.do")
	public String selectInsttCodeDetail(
			InsttCodeRecptnVO insttCodeRecptnVO, 
			ModelMap model) {

		/* 기관코드 조회 */
		insttCodeRecptnVO = insttCodeManageService.selectInsttCodeDetail(insttCodeRecptnVO);

		model.addAttribute(insttCodeRecptnVO);

		/* 기관코드수신 리스트 */
		InsttCodeRecptnVO vo = new InsttCodeRecptnVO();
		vo.getSearchVO().setSizeAndOffset(9999999, 0);
		vo.getSearchVO().setSearchCondition("CodeList");
		vo.setInsttCode(insttCodeRecptnVO.getInsttCode());

		model.addAttribute("insttCodeRecptnList", insttCodeManageService.selectInsttCodeRecptnList(vo));

		/* 변경구분코드 */
		cmmUseService.populateCmmCodeList("COM043", "COM043_changeSe");
		/* 처리구분코드 */
		cmmUseService.populateCmmCodeList("COM044", "COM044_processSe");

		return WebUtil.adjustViewName("/sym/ccm/icr/InsttCodeRecptnDetail");
	}

	/**
	 * TEST 용 Controller 실 적용시 Job Scheduler 에 등록하여 처리한다. 기관코드를 수신처리한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/icr/registInsttCodeRecptn.do")
	public String registInsttCodeRecptn(
			@ModelAttribute InsttCodeRecptnVO insttCodeRecptnVO) {
		
		return WebUtil.adjustViewName("/sym/ccm/icr/InsttCodeRecptnRegist");
	}

	/**
	 * TEST 용 Controller 실 적용시 Job Scheduler 에 등록하여 처리한다. 기관코드를 수신처리한다.
	 * 
	 * @param insttCodeRecptnVO
	 */
	@RequestMapping(value = "/sym/ccm/icr/insertInsttCodeRecptn.do")
	public String insertInsttCodeRecptn(
			@ModelAttribute InsttCodeRecptnVO insttCodeRecptnVO, 
			ModelMap model) {
		
		insttCodeManageService.insertInsttCodeRecptn();
		
        return WebUtil.redirectJsp(model, "/sym/ccm/icr/listInsttCodeRecptn.do");
	}

}