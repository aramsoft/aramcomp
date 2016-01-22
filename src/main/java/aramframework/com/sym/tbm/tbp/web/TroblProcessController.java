package aramframework.com.sym.tbm.tbp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.tbm.tbp.service.TroblProcessService;
import aramframework.com.sym.tbm.tbp.service.TroblProcessVO;
import aramframework.com.utl.fcc.service.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 장애관리정보에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 장애관리정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다. 
 *         - 장애관리정보의 조회기능은 목록조회, 상세조회로  구분된다.
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
public class TroblProcessController {

	@Autowired
	private TroblProcessService troblProcessService;

	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * 
	 * @param troblProcessVO
	 */
	@IncludedInfo(name = "장애처리결과관리", order = 6261, gid = 60)
	@RequestMapping(value = "/sym/tbm/tbp/listTroblProcess.do")
	@Secured("ROLE_ADMIN")
	public String listTroblProcess(
			@ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO, 
			ModelMap model) {

		if (troblProcessVO.getStrTroblKnd() == null)
			troblProcessVO.setStrTroblKnd("00");
		if (troblProcessVO.getStrProcessSttus() == null)
			troblProcessVO.setStrProcessSttus("00");

		PaginationInfo paginationInfo = new PaginationInfo();
		troblProcessVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", troblProcessService.selectTroblProcessList(troblProcessVO));

		int totCnt = troblProcessService.selectTroblProcessListCnt(troblProcessVO);
		troblProcessVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		cmmUseService.populateCmmCodeList("COM065", "COM065_troblKnd");
		cmmUseService.populateCmmCodeList("COM068", "COM068_processSttus");

		return WebUtil.adjustViewName("/sym/tbm/tbp/TroblProcessList");
	}

	/**
	 * 장애처리정보 등록화면으로 이동한다.
	 * 
	 * @param troblProcessVO
	 */
	@RequestMapping(value = "/sym/tbm/tbp/registTroblProcess.do")
	@Secured("ROLE_ADMIN")
	public String registTroblProcess(
			@ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO) {

		troblProcessService.selectTroblProcess(troblProcessVO);
		
		return WebUtil.adjustViewName("/sym/tbm/tbp/TroblProcessRegist");
	}

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * 
	 * @param troblProcessVO
	 */
	@RequestMapping(value = "/sym/tbm/tbp/insertTroblProcess.do")
	@Secured("ROLE_ADMIN")
	public String insertTroblProcess(
			@ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(troblProcessVO, bindingResult); // validation 수행
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/tbm/tbp/TroblProcessRegist");
		} 
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		troblProcessVO.setTroblProcessTime(StringUtil.removeMinusChar(troblProcessVO.getTroblProcessTime()));
		troblProcessVO.setLastUpdusrId(loginVO.getId());
		troblProcessVO.setProcessSttus("C");

		troblProcessService.insertTroblProcess(troblProcessVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbp/listTroblProcess.do");
	}

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * 
	 * @param troblProcessVO
	 */
	@RequestMapping(value = "/sym/tbm/tbp/deleteTroblProcess.do")
	@Secured("ROLE_ADMIN")
	public String deleteTroblProcess(
			@ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO, 
			ModelMap model) {

		troblProcessVO.setProcessSttus("R");

		troblProcessService.deleteTroblProcess(troblProcessVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/tbm/tbp/registTroblProcess.do");
	}

}