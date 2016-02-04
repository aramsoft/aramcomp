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

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.dsm.domain.DiaryManageVO;
import aramframework.com.cop.smt.dsm.service.DiaryManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일지관리를 처리하는 Controller Class 구현
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
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/dsm/DiaryList");
	}

	/**
	 * 일지관리 목록을 상세조회한다.
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/detailDiary.do")
	@Secured("ROLE_USER")
	public String detailDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {

		diaryManageService.selectDiaryManageDetail(diaryManageVO);

		return WebUtil.adjustViewName("/cop/smt/dsm/DiaryDetail");
	}

	/**
	 * 일지관리를 등록한다. / 등록 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/registDiary.do")
	@Secured("ROLE_USER")
	public String registDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {

		return WebUtil.adjustViewName("/cop/smt/dsm/DiaryRegist");
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
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(diaryManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/dsm/DiaryRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		diaryManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DIARY_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		diaryManageVO.setFrstRegisterId(loginVO.getUniqId());

		diaryManageService.insertDiaryManage(diaryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/dsm/listDiary.do");
	}

	/**
	 * 일지관리를 수정한다. / 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/editDiary.do")
	@Secured("ROLE_USER")
	public String editDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {

		diaryManageService.selectDiaryManageDetail(diaryManageVO);

		return WebUtil.adjustViewName("/cop/smt/dsm/DiaryEdit");
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
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(diaryManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/dsm/DiaryEdit");
		}
		
		 // 첨부파일 관련 ID 생성 start....
		String atchFileId = diaryManageVO.getAtchFileId();
		diaryManageVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DIARY_", atchFileId));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		diaryManageVO.setLastUpdusrId(loginVO.getUniqId());

		diaryManageService.updateDiaryManage(diaryManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/dsm/listDiary.do");
	}

	/**
	 * 일지관리 목록을 삭제한다.
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value = "/cop/smt/dsm/deleteDiary.do")
	@Secured("ROLE_USER")
	public String deleteDiary(
			@ModelAttribute DiaryManageVO diaryManageVO, 
			ModelMap model) {

		diaryManageService.deleteDiaryManage(diaryManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/dsm/listDiary.do");
	}

}
