package aramframework.com.cop.smt.mrm.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.mrm.domain.MemoReprtVO;
import aramframework.com.cop.smt.mrm.service.MemoReprtService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 메모보고에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 메모보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 메모보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MemoReprtController {

	@Autowired 
	private MemoReprtService memoReprtService;

	@Autowired 
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 보고자 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/smt/mrm/listReportrPopup.do")
	public String listReportr(
			@ModelAttribute BaseVO baseVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		baseVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", memoReprtService.selectReportrList(baseVO));
		int totCnt = memoReprtService.selectReportrListCnt(baseVO);

		baseVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/mrm/ReportrListPopup");
	}

	/**
	 * 메모보고 정보에 대한 목록을 조회한다.
	 * 
	 * @param memoReprtVO
	 */
	@IncludedInfo(name = "메모보고", order = 4130, gid = 40)
	@RequestMapping("/cop/smt/mrm/listMemoReprt.do")
	@Secured("ROLE_USER")
	public String listMemoReprt(
			@ModelAttribute MemoReprtVO memoReprtVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoReprtVO.setSearchId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		memoReprtVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", memoReprtService.selectMemoReprtList(memoReprtVO));
		int totCnt = memoReprtService.selectMemoReprtListCnt(memoReprtVO);

		memoReprtVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtList");
	}

	/**
	 * 메모보고 정보를 조회한다.
	 * 
	 * @param memoReprtVO 
	 */
	@RequestMapping("/cop/smt/mrm/detailMemoReprt.do")
	@Secured("ROLE_USER")
	public String detailMemoReprt(
			MemoReprtVO memoReprtVO, 
			ModelMap model) {
	
		memoReprtVO = memoReprtService.selectMemoReprt(memoReprtVO);
		
		// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("uniqId", loginVO.getUniqId());

		if (loginVO.getUniqId().equals(memoReprtVO.getReportrId())) {
			memoReprtService.readMemoReprt(memoReprtVO);	// notice read status
		}
		model.addAttribute(memoReprtVO);
		
		return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtDetail");
	}

	/**
	 * 메모보고 정보의 등록페이지로 이동한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/registMemoReprt.do")
	@Secured("ROLE_USER")
	public String registMemoReprt(
			@ModelAttribute MemoReprtVO memoReprtVO) {

		// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		memoReprtVO.setReprtDe(formatter.format(new java.util.Date()));
		memoReprtVO.setWrterId(loginVO.getUniqId());
		memoReprtVO.setWrterNm(loginVO.getName());
		memoReprtVO.setWrterClsfNm(memoReprtService.selectWrterClsfNm(loginVO.getUniqId()));

		return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtRegist");
	}

	/**
	 * 메모보고 정보를 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/insertMemoReprt.do")
	@Secured("ROLE_USER")
	public String insertMemoReprt(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute MemoReprtVO memoReprtVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(memoReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		memoReprtVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoReprtVO.setFrstRegisterId(loginVO.getUniqId());

		memoReprtService.insertMemoReprt(memoReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/mrm/listMemoReprt.do");
	}

	/**
	 * 메모보고 정보의 수정페이지로 이동한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/editMemoReprt.do")
	@Secured("ROLE_USER")
	public String editMemoReprt(
			MemoReprtVO memoReprtVO,
			ModelMap model) {

		model.addAttribute(memoReprtService.selectMemoReprt(memoReprtVO));

		return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtEdit");
	}

	/**
	 * 메모보고 정보를 수정한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/updateMemoReprt.do")
	@Secured("ROLE_USER")
	public String updateMemoReprt(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute MemoReprtVO memoReprtVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(memoReprtVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/mrm/MemoReprtEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = memoReprtVO.getAtchFileId();
		memoReprtVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSCH_", atchFileId));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoReprtVO.setLastUpdusrId(loginVO.getUniqId());

		memoReprtService.updateMemoReprt(memoReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/mrm/listMemoReprt.do");
	}

	/**
	 * 메모보고 정보를 삭제한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/deleteMemoReprt.do")
	@Secured("ROLE_USER")
	public String deleteMemoReprt(
			@ModelAttribute MemoReprtVO memoReprtVO, 
			ModelMap model) {

		memoReprtService.deleteMemoReprt(memoReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/mrm/listMemoReprt.do");
	}

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * 
	 * @param memoReprtVO
	 */
	@RequestMapping("/cop/smt/mrm/updateMemoReprtDrctMatter.do")
	@Secured("ROLE_USER")
	public String updateMemoReprtDrctMatter(
			@ModelAttribute MemoReprtVO memoReprtVO, 
			ModelMap model) {

		memoReprtService.updateMemoReprtDrctMatter(memoReprtVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/mrm/listMemoReprt.do");
	}

}