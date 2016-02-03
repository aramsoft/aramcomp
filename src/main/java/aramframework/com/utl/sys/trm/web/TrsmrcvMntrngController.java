package aramframework.com.utl.sys.trm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngLogVO;
import aramframework.com.utl.sys.trm.domain.TrsmrcvMntrngVO;
import aramframework.com.utl.sys.trm.service.TrsmrcvMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 송수신모니터링에 대한 controller 클래스를 정의한다.
 * 
 * 송수신모니터링관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 송수신모니터링관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class TrsmrcvMntrngController {

	@Autowired
	private TrsmrcvMntrngService trsmrcvMntrngService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 송수신모니터링 목록을 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@IncludedInfo(name = "송수신모니터링", order = 9090, gid = 90)
	@RequestMapping("/utl/sys/trm/listTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		trsmrcvMntrngVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", trsmrcvMntrngService.selectTrsmrcvMntrngList(trsmrcvMntrngVO));

		int totCnt = trsmrcvMntrngService.selectTrsmrcvMntrngListCnt(trsmrcvMntrngVO);
		trsmrcvMntrngVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngList");
	}

	/**
	 * 송수신모니터링정보을 상세조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/detailTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO) {

		trsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrngVO);

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngDetail");
	}

	/**
	 * 등록화면을 위한 송수신모니터링정보을 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/registTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String registTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO) {

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngRegist");
	}

	/**
	 * 송수신모니터링을 등록한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/insertTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String insertTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(trsmrcvMntrngVO, bindingResult);
		checkDuplication(trsmrcvMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngRegist");
		} 
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		trsmrcvMntrngVO.setFrstRegisterId(loginVO.getUniqId());

		trsmrcvMntrngService.insertTrsmrcvMntrng(trsmrcvMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/trm/listTrsmrcvMntrng.do");
	}

	private void checkDuplication(TrsmrcvMntrngVO trsmrcvMntrngVO, Errors errors) {
		String cntcId = trsmrcvMntrngVO.getCntcId();

		TrsmrcvMntrngVO exist = null;

		try {
			exist = trsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrngVO);
			if (exist != null) {
				errors.rejectValue("cntcId", "errors.cntcId", new Object[] { cntcId }, "모니터링대상으로 연계ID {0}이 이미 존재합니다.");
				return;
			}
		} catch (Exception se) {
			errors.rejectValue("cntcId", "errors.cntcId", new Object[] { cntcId }, " 모니터링대상으로 연계ID {0}을 중복체크중 시스템에러가 발생했습니다. ");
			return;
		}
	}

	/**
	 * 수정화면을 위한 송수신모니터링정보을 조회한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/editTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String editTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO) {

		trsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrngVO);

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngEdit");
	}

	/**
	 * 송수신모니터링을 수정한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/updateTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String updateTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(trsmrcvMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		trsmrcvMntrngVO.setLastUpdusrId(loginVO.getUniqId());

		trsmrcvMntrngService.updateTrsmrcvMntrng(trsmrcvMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/trm/listTrsmrcvMntrng.do");
	}

	/**
	 * 송수신모니터링을 삭제한다.
	 * 
	 * @param trsmrcvMntrngVO
	 */
	@RequestMapping("/utl/sys/trm/deleteTrsmrcvMntrng.do")
	@Secured("ROLE_ADMIN")
	public String deleteTrsmrcvMntrng(
			@ModelAttribute TrsmrcvMntrngVO trsmrcvMntrngVO, 
			ModelMap model) {

		trsmrcvMntrngService.deleteTrsmrcvMntrng(trsmrcvMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/trm/listTrsmrcvMntrng.do");
	}

	/**
	 * 송수신모니터링로그 목록을 조회한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	@RequestMapping("/utl/sys/trm/listTrsmrcvMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String selectTrsmrcvMntrngLogList(
			@ModelAttribute TrsmrcvMntrngLogVO trsmrcvMntrngLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		trsmrcvMntrngLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", trsmrcvMntrngService.selectTrsmrcvMntrngLogList(trsmrcvMntrngLogVO));

		int totCnt = trsmrcvMntrngService.selectTrsmrcvMntrngLogListCnt(trsmrcvMntrngLogVO);
		trsmrcvMntrngLogVO.setTotalRecordCount(totCnt);
	
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngLogList");
	}

	/**
	 * 송수신모니터링로그정보을 상세조회한다.
	 * 
	 * @param trsmrcvMntrngLogVO
	 */
	@RequestMapping("/utl/sys/trm/detailTrsmrcvMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String selectTrsmrcvMntrngLog(
			@ModelAttribute TrsmrcvMntrngLogVO trsmrcvMntrngLogVO) {
		
		trsmrcvMntrngService.selectTrsmrcvMntrngLog(trsmrcvMntrngLogVO);

		return WebUtil.adjustViewName("/utl/sys/trm/TrsmrcvMntrngLogDetail");
	}

	/**
	 * 연계정보 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/utl/sys/trm/listCntcPopup.do")
	public String listCntc(
			@ModelAttribute SearchVO searchVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		searchVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", trsmrcvMntrngService.selectCntcList(searchVO));

		int totCnt = trsmrcvMntrngService.selectCntcListCnt(searchVO);
		searchVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/trm/CntcListPopup");
	}

}