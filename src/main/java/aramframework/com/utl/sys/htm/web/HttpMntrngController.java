package aramframework.com.utl.sys.htm.web;

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
import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sys.htm.domain.HttpMntrngLogVO;
import aramframework.com.utl.sys.htm.domain.HttpMntrngVO;
import aramframework.com.utl.sys.htm.schedule.HttpMntrngChecker;
import aramframework.com.utl.sys.htm.service.HttpMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - HTTP서비스모니터링에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class HttpMntrngController {

	@Autowired
	private HttpMntrngService httpMntrngService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 HTTP서비스모니터링 정보를 조회 한다.
	 * 
	 * @param httpMntrngVO
	 */
	@IncludedInfo(name = "HTTP서비스모니터링", order = 9040, gid = 90)
	@RequestMapping(value = "/utl/sys/htm/listHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listHttpMon(
			@ModelAttribute HttpMntrngVO httpMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		httpMntrngVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", httpMntrngService.selectHttpMntrngList(httpMntrngVO));
		int totCnt = httpMntrngService.selectHttpMntrngListCnt(httpMntrngVO);

		httpMntrngVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngList");
	}

	/**
	 * HTTP서비스모니터링상세 정보를 조회 한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/detailHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailHttpMon(
			HttpMntrngVO httpMntrngVO,
			ModelMap model) {

		model.addAttribute(httpMntrngService.selectHttpMntrngDetail(httpMntrngVO));

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngDetail");
	}

	/**
	 * 기 등록 된 Http서비스모니터링 정보를 수정 한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/registHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String registHttpMon(
			@ModelAttribute HttpMntrngVO httpMntrngVO) {

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngRegist");
	}

	/**
	 * Http서비스모니터링 정보를 신규로 등록한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/insertHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String insertHttpMon(
			@ModelAttribute HttpMntrngVO httpMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(httpMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		httpMntrngVO.setFrstRegisterId(loginVO.getUniqId());

		httpMntrngService.insertHttpMntrng(httpMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/htm/listHttpMntrng.do");
	}

	/**
	 * 기 등록 된 Http서비스모니터링 정보를 수정 한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/editHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String editHttpMon(
			HttpMntrngVO httpMntrngVO,
			ModelMap model) {

		model.addAttribute(httpMntrngService.selectHttpMntrngDetail(httpMntrngVO));

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngEdit");
	}

	/**
	 * 기 등록 된 Http서비스모니터링 정보를 수정 한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/updateHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String updateHttpMon(
			@ModelAttribute HttpMntrngVO httpMntrngVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(httpMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		httpMntrngVO.setLastUpdusrId(loginVO.getUniqId());

		httpMntrngService.updateHttpMntrng(httpMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/htm/listHttpMntrng.do");
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/htm/deleteHttpMntrng.do")
	@Secured("ROLE_ADMIN")
	public String deleteHttpMon(
			@ModelAttribute HttpMntrngVO httpMntrngVO, 
			ModelMap model) {

		httpMntrngService.deleteHttpMntrng(httpMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/htm/listHttpMntrng.do");
	}

	/**
	 * HTTP 서비스 상태를 조회한다.
	 * 
	 * @param httpMntrngVO
	 */
	@RequestMapping("/utl/sys/htm/selectHttpMntrngSttus.do")
	@Secured("ROLE_ADMIN")
	public String selectHttpMonSttus(
			@ModelAttribute HttpMntrngVO httpMntrngVO, 
			ModelMap model) {

		model.addAttribute("httpSttusCd", HttpMntrngChecker.getPrductStatus(httpMntrngVO.getSiteUrl()));

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngRegist");
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 정보를 조회 한다.
	 * 
	 * @param httpMonLogVO
	 */
	@RequestMapping(value = "/utl/sys/htm/listHttpMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String selectHttpMonLogList(
			@ModelAttribute HttpMntrngLogVO httpMntrngLogVO, 
			ModelMap model) {

		// 조회기간설정
		if (httpMntrngLogVO.getSearchBgnDe() != null && httpMntrngLogVO.getSearchEndDe() != null) {
			if (!httpMntrngLogVO.getSearchBgnDe().equals("") && !httpMntrngLogVO.getSearchEndDe().equals("")) {
				httpMntrngLogVO.setSearchBgnDt(httpMntrngLogVO.getSearchBgnDe() + " " + httpMntrngLogVO.getSearchBgnHour());
				httpMntrngLogVO.setSearchEndDt(httpMntrngLogVO.getSearchEndDe() + " " + httpMntrngLogVO.getSearchEndHour());
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		httpMntrngLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", httpMntrngService.selectHttpMntrngLogList(httpMntrngLogVO));
		int totCnt = httpMntrngService.selectHttpMntrngLogListCnt(httpMntrngLogVO);

		httpMntrngLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngLogList");
	}

	// 조회시작시
	@ModelAttribute("searchBgnHour")
	public List<ComCodeVO> searchBgnHour() { return WebUtil.getTimeHH();}
	// 조회종료시
	@ModelAttribute("searchEndHour")
	public List<ComCodeVO> searchEndHour() { return WebUtil.getTimeHH();}

	/**
	 * HTTP서비스모니터링로그상세 정보를 조회 한다.
	 * 
	 * @param httpMonLogVO
	 */
	@RequestMapping(value = "/utl/sys/htm/detailHttpMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String selectHttpMonDetailLog(
			HttpMntrngLogVO httpMntrngLogVO,
			ModelMap model) {

		model.addAttribute(httpMntrngService.selectHttpMntrngDetailLog(httpMntrngLogVO));

		return WebUtil.adjustViewName("/utl/sys/htm/HttpMntrngLogDetail");
	}

}
