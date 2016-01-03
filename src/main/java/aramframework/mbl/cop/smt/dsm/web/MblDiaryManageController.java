package aramframework.mbl.cop.smt.dsm.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cop.smt.dsm.service.DiaryManageVO;
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
public class MblDiaryManageController {
	
	@Resource(name = "diaryManageService")
	private DiaryManageService diaryManageService;
	 
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 일지관리 목록을 조회한다.
	 *  
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/listDiary.mdo")
	@Secured("ROLE_USER")
	public String listDiary(
			@ModelAttribute DiaryManageVO diaryManageVO, 
    		ModelMap model) {

    	PaginationInfo paginationInfo = new PaginationInfo();
    	diaryManageVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList", diaryManageService.selectDiaryManageList(diaryManageVO));
        
        int totCnt = (Integer)diaryManageService.selectDiaryManageListCnt(diaryManageVO);
        diaryManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
		return "aramframework/mbl/cop/smt/dsm/DiaryList"; 
	}
	
	/**
	 * 일지관리 목록을 상세조회 조회한다. 
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/detailDiary.mdo")
	@Secured("ROLE_USER")
	public String detailDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {
		
        diaryManageService.selectDiaryManageDetail(diaryManageVO);

        return "aramframework/mbl/cop/smt/dsm/DiaryDetail"; 	
	}
	
	/**
	 * 일지관리를 등록한다. / 등록 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/registDiary.mdo")
	@Secured("ROLE_USER")
	public String registDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {
		
		return  "aramframework/mbl/cop/smt/dsm/DiaryRegist"; 
	}
	
	/**
	 * 일지관리를 등록한다. / 등록처리작업
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/insertDiary.mdo")
	@Secured("ROLE_USER")
	public String insertDiary(
			//final MultipartHttpServletRequest multiRequest,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult,
    		ModelMap model) {
		
		//서버  validate 체크
        beanValidator.validate(diaryManageVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "aramframework/mbl/cop/smt/dsm/DiaryRegist";
		}
		
    	/*
		// 첨부파일 관련 첨부파일ID 생성
    	diaryManageVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DIARY_"));
		*/
		
		//로그인 객체 선언
    	LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	diaryManageVO.setFrstRegisterId(loginVO.getUniqId());
    		
        diaryManageService.insertDiaryManage(diaryManageVO);
 
        return  "redirect:/cop/smt/dsm/listDiary.mdo"; 
	}
	
	/**
	 * 일지관리를 수정한다. / 초기페이지
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/editDiary.mdo")
	@Secured("ROLE_USER")
	public String editDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {

		diaryManageService.selectDiaryManageDetail(diaryManageVO);

		return "aramframework/mbl/cop/smt/dsm/DiaryEdit"; 	
	}
	
	/**
	 * 일지관리를 수정한다. / 수정처리작업
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/updateDiary.mdo")
	@Secured("ROLE_USER")
	public String updateDiary(
			//final MultipartHttpServletRequest multiRequest,
			@ModelAttribute DiaryManageVO diaryManageVO, 
			BindingResult bindingResult,
    		ModelMap model) {

		//서버  validate 체크
        beanValidator.validate(diaryManageVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "aramframework/mbl/cop/smt/dsm/DiaryEdit";
		}
		
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		diaryManageVO.setLastUpdusrId(loginVO.getUniqId());

		/* 
		// 첨부파일 관련 ID 생성 start....
		String atchFileId = diaryManageVO.getAtchFileId();
		diaryManageVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DIARY_", atchFileId));
		*/

		/* *****************************************************************
    	// 일지정보 업데이트
		****************************************************************** */
    	diaryManageService.updateDiaryManage(diaryManageVO);
    	
    	return  "redirect:/cop/smt/dsm/listDiary.mdo"; 
	}
	
	/**
	 * 일지관리 목록을 삭제한다. 
	 * 
	 * @param diaryManageVO
	 */
	@RequestMapping(value="/cop/smt/dsm/deleteDiary.mdo")
	@Secured("ROLE_USER")
	public String deleteDiary(
			@ModelAttribute DiaryManageVO diaryManageVO) {
		
		diaryManageService.deleteDiaryManage(diaryManageVO);

		return  "redirect:/cop/smt/dsm/listDiary.mdo";
	}
	
}


