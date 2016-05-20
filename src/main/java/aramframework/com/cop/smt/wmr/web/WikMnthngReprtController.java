package aramframework.com.cop.smt.wmr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.BaseVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.wmr.domain.WikMnthngReprtVO;
import aramframework.com.cop.smt.wmr.service.WikMnthngReprtService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 주간월간보고에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 주간월간보고에 대한 등록, 수정, 삭제, 조회, 승인기능을 제공한다. 
 *         - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class WikMnthngReprtController {

	@Autowired 
	private WikMnthngReprtService wikMnthngReprtService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	// 첨부파일 관련
	@Autowired 
	private FileMngUtil fileUtil;

	/**
	 * 보고자 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/smt/wmr/listReportrPopup.do")
	public String listReportr(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		baseVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", wikMnthngReprtService.selectReportrList(baseVO));

		int totCnt = wikMnthngReprtService.selectReportrListCnt(baseVO);
		baseVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/wmr/ReportrListPopup");
	}

	/**
	 * 주간월간보고 정보에 대한 목록을 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@IncludedInfo(name = "주간/월간보고관리", order = 4110, gid = 40)
	@RequestMapping("/cop/smt/wmr/listWikMnthngReprt.do")
	@Secured("ROLE_USER")
	public String listWikMnthngReprt(
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wikMnthngReprtVO.setSearchId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		wikMnthngReprtVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", wikMnthngReprtService.selectWikMnthngReprtList(wikMnthngReprtVO));

		int totCnt = wikMnthngReprtService.selectWikMnthngReprtListCnt(wikMnthngReprtVO);
		wikMnthngReprtVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtList");
	}

	/**
	 * 주간월간보고 정보를 조회한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/detailWikMnthngReprt.do")
	public String detailWikMnthngReprt(
			WikMnthngReprtVO wikMnthngReprtVO, 
			ModelMap model)  {
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("uniqId", loginVO.getUniqId());

		model.addAttribute(wikMnthngReprtService.selectWikMnthngReprt(wikMnthngReprtVO));

		// 공통코드 우선순위 조회
		cmmUseService.populateCmmCodeList("COM060", "COM060_reprtSe");

		return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtDetail");
	}

	/**
	 * 주간월간보고 정보의 등록페이지로 이동한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/registWikMnthngReprt.do")
	public String registWikMnthngReprt(
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		wikMnthngReprtVO.setReprtDe(formatter.format(new java.util.Date()));
		wikMnthngReprtVO.setWrterId(loginVO.getUniqId());
		wikMnthngReprtVO.setWrterNm(loginVO.getName());
		wikMnthngReprtVO.setWrterClsfNm(wikMnthngReprtService.selectWrterClsfNm(loginVO.getUniqId()));

		return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtRegist");
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/insertWikMnthngReprt.do")
	public String insertWikMnthngReprt(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(wikMnthngReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtRegist");
		}
		
		// 첨부파일 관련 첨부파일ID 생성
		wikMnthngReprtVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wikMnthngReprtVO.setFrstRegisterId(loginVO.getUniqId());

		wikMnthngReprtService.insertWikMnthngReprt(wikMnthngReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/wmr/listWikMnthngReprt.do");
	}

	/**
	 * 주간월간보고 정보의 수정페이지로 이동한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/editWikMnthngReprt.do")
	public String editWikMnthngReprt(
			WikMnthngReprtVO wikMnthngReprtVO,
			ModelMap model)  {

		model.addAttribute(wikMnthngReprtService.selectWikMnthngReprt(wikMnthngReprtVO));

		return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtEdit");
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/updateWikMnthngReprt.do")
	public String updateWikMnthngReprt(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(wikMnthngReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/wmr/WikMnthngReprtEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = wikMnthngReprtVO.getAtchFileId();
		wikMnthngReprtVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSCH_", atchFileId));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wikMnthngReprtVO.setLastUpdusrId(loginVO.getUniqId());

		wikMnthngReprtService.updateWikMnthngReprt(wikMnthngReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/wmr/listWikMnthngReprt.do");
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/deleteWikMnthngReprt.do")
	public String deleteWikMnthngReprt(
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO, 
			ModelMap model) {

		wikMnthngReprtService.deleteWikMnthngReprt(wikMnthngReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/wmr/listWikMnthngReprt.do");
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * 
	 * @param wikMnthngReprtVO
	 */
	@RequestMapping("/cop/smt/wmr/confirmWikMnthngReprt.do")
	public String confirmWikMnthngReprt(
			@ModelAttribute WikMnthngReprtVO wikMnthngReprtVO, 
			ModelMap model) {

		wikMnthngReprtService.confirmWikMnthngReprt(wikMnthngReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/wmr/listWikMnthngReprt.do");
	}

}