package aramframework.com.sym.prm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.prm.domain.ProgrmManageVO;
import aramframework.com.sym.prm.service.ProgrmManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 프로그램목록 관리및 변경을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ProgrmManageController {

	@Autowired
	private ProgrmManageService progrmManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 프로그램파일명을 조회한다.(팝업화면)
	 * 
	 * @param progrmManageVO
	 */
	@RequestMapping(value = "/sym/prm/listProgramPopup.do")
	public String listProgramPopup(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		progrmManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", progrmManageService.selectProgrmList(progrmManageVO));
		int totCnt = progrmManageService.selectProgrmListCnt(progrmManageVO);

		progrmManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/prm/ProgramPopup");
	}
	
	/**
	 * 프로그램목록 리스트조회한다.
	 * 
	 * @param progrmManageVO
	 */
	@IncludedInfo(name = "프로그램관리", order = 6180, gid = 60)
	@RequestMapping(value = "/sym/prm/listProgram.do")
	@Secured("ROLE_ADMIN")
	public String listProgram(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		progrmManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", progrmManageService.selectProgrmList(progrmManageVO));
		int totCnt = progrmManageService.selectProgrmListCnt(progrmManageVO);

		progrmManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/sym/prm/ProgramList");
	}

	/**
	 * 프로그램목록 멀티 삭제한다.
	 * 
	 * @param progrmManageVO
	 * @param checkedProgrmFileNmForDel
	 */
	@RequestMapping("/sym/prm/deleteListProgram.do")
	@Secured("ROLE_ADMIN")
	public String deleteListProgram(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			@RequestParam String checkedProgrmFileNmForDel,
			ModelMap model) {
		
		String[] delProgrmFileNm = checkedProgrmFileNmForDel.split(",");
		if (delProgrmFileNm == null || (delProgrmFileNm.length == 0)) {
			model.addAttribute("message", MessageHelper.getMessage("fail.common.delete"));
	        return WebUtil.redirectJsp(model, "/sym/prm/listProgram.do");
		} 
		
		progrmManageService.deleteProgrmManageList(checkedProgrmFileNmForDel);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/prm/listProgram.do");
	}

	/**
	 * 프로그램목록 등록화면으로 이동한다.
	 * 
	 * @param progrmManageVO
	 */
	@RequestMapping(value = "/sym/prm/registProgram.do")
	@Secured("ROLE_ADMIN")
	public String registProgrm(
			@ModelAttribute ProgrmManageVO progrmManageVO) {

		return WebUtil.adjustViewName("/sym/prm/ProgramRegist");
	}

	/**
	 * 프로그램목록을 등록 한다.
	 * 
	 * @param progrmManageVO
	 */
	@RequestMapping(value = "/sym/prm/insertProgram.do")
	@Secured("ROLE_ADMIN")
	public String insertProgrm(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/prm/ProgramRegist");
		}
		
		if (progrmManageVO.getProgrmDc() == null || progrmManageVO.getProgrmDc().equals("")) {
			progrmManageVO.setProgrmDc(" ");
		}
		
		progrmManageService.insertProgrm(progrmManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sym/prm/listProgram.do");
	}

	/**
	 * 프로그램목록 수정화면으로 이동한다.
	 * 
	 * @param progrmManageVO
	 */
	@RequestMapping(value = "/sym/prm/editProgram.do")
	@Secured("ROLE_ADMIN")
	public String editProgram(
			ProgrmManageVO progrmManageVO,
			ModelMap model) {
		
		model.addAttribute(progrmManageService.selectProgrm(progrmManageVO));

		return WebUtil.adjustViewName("/sym/prm/ProgramEdit");
	}

	/**
	 * 프로그램목록을 수정 한다.
	 * 
	 * @param progrmManageVO
	 */
	/* 프로그램목록수정 */
	@RequestMapping(value = "/sym/prm/updateProgram.do")
	@Secured("ROLE_ADMIN")
	public String updateProgrm(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/prm/ProgramEdit");
		}
		
		if (progrmManageVO.getProgrmDc() == null || progrmManageVO.getProgrmDc().equals("")) {
			progrmManageVO.setProgrmDc(" ");
		}

		progrmManageService.updateProgrm(progrmManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/prm/listProgram.do");
	}

	/**
	 * 프로그램목록을 삭제 한다.
	 * 
	 * @param progrmManageVO
	 */
	@RequestMapping(value = "/sym/prm/deleteProgram.do")
	@Secured("ROLE_ADMIN")
	public String deleteProgrm(
			@ModelAttribute ProgrmManageVO progrmManageVO, 
			ModelMap model) {

		progrmManageService.deleteProgrm(progrmManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/prm/listProgram.do");
	}

}