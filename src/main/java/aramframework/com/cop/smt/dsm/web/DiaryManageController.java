package aramframework.com.cop.smt.dsm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.FileMngUtil;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.com.domain.SearchVO;
import aramframework.com.cop.smt.dsm.domain.DiaryManageVO;
import aramframework.com.cop.smt.dsm.service.DiaryManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일지관리를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class DiaryManageController {

	@Autowired 
	private DiaryManageService diaryManageService;

	@Autowired 
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 일지관리 목록을 조회한다.
	 * 
	 * @param diaryManageVO
	 */
	@IncludedInfo(name = "일지관리", order = 4060, gid = 40)
	@RequestMapping(value = "/cop/smt/dsm/listDiary.do")
	@Secured("ROLE_USER")
	public String listDiary(
			@ModelAttribute DiaryManageVO diaryManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		diaryManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", diaryManageService.selectDiaryManageList(diaryManageVO));
		int totCnt = (Integer) diaryManageService.selectDiaryManageListCnt(diaryManageVO);

		diaryManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/smt/dsm/DiaryList";
	}

	/**
	 * 일지관리 목록을 상세조회한다.
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/detailDiary.do")
	@Secured("ROLE_USER")
	public String detailDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO,
			ModelMap model) {

		model.addAttribute(diaryManageService.selectDiaryManageDetail(diaryManageVO));

		return "com/cop/smt/dsm/DiaryDetail";
	}

	/**
	 * 일지관리를 등록한다. / 등록 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/registDiary.do")
	@Secured("ROLE_USER")
	public String registDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			ModelMap model) {

		return "com/cop/smt/dsm/DiaryRegist";
	}

	/**
	 * 일지관리를 등록한다. / 등록처리작업
	 * 
	 * @param multiRequest
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/insertDiary.do")
	@Secured("ROLE_USER")
	public String insertDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(diaryManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/smt/dsm/DiaryRegist";
		}

		// 첨부파일 관련 첨부파일ID 생성
		diaryManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSM"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		diaryManageVO.setFrstRegisterId(loginVO.getUserId());

		diaryManageService.insertDiaryManage(diaryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/smt/dsm/listDiary.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 일지관리를 수정한다. / 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/editDiary.do")
	@Secured("ROLE_USER")
	public String editDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO,
			ModelMap model) {

		model.addAttribute(diaryManageService.selectDiaryManageDetail(diaryManageVO));

		return "com/cop/smt/dsm/DiaryEdit";
	}

	/**
	 * 일지관리를 수정한다. / 수정처리작업
	 * 
	 * @param multiRequest
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/updateDiary.do")
	@Secured("ROLE_USER")
	public String updateDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(diaryManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/smt/dsm/DiaryEdit";
		}
		
		 // 첨부파일 관련 ID 생성 start....
		String atchFileId = diaryManageVO.getAtchFileId();
		diaryManageVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSM", atchFileId));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		diaryManageVO.setLastUpdusrId(loginVO.getUserId());

		diaryManageService.updateDiaryManage(diaryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/smt/dsm/listDiary.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 일지관리 목록을 삭제한다.
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/deleteDiary.do")
	@Secured("ROLE_USER")
	public String deleteDiary(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			ModelMap model) {

		diaryManageService.deleteDiaryManage(diaryManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/smt/dsm/listDiary.do");
	    return "com/cmm/redirect";
	}

}
