package aramframework.com.sym.log.slg.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.log.slg.domain.SysHistoryVO;
import aramframework.com.sym.log.slg.service.SysHistoryService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시스템 이력관리를 위한 웹 컨트롤러 클래스
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
public class SysHistoryController {

	@Autowired
	private SysHistoryService sysHistoryService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 시스템이력 목록 조회
	 * 
	 * @param sysHistoryVO
	 */
	@IncludedInfo(name = "시스템이력관리", order = 6130, gid = 60)
	@RequestMapping(value = "/sym/log/slg/listSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String listSysHistory(
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		sysHistoryVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", sysHistoryService.selectSysHistoryList(sysHistoryVO));

		int totCnt = sysHistoryService.selectSysHistoryListCnt(sysHistoryVO);
		sysHistoryVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sym/log/slg/SysHistList");
	}

	/**
	 * 시스템이력 상세 조회
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/detailSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String detailSysHistory(
			@ModelAttribute SysHistoryVO sysHistoryVO) {

		sysHistoryService.selectSysHistory(sysHistoryVO);

		return WebUtil.adjustViewName("/sym/log/slg/SysHistDetail");
	}

	/**
	 * 시스템이력 등록 화면
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/registSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String registSysHistory(
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			ModelMap model) {

		cmmUseService.populateCmmCodeList("COM002", "COM002_histSe");

		return WebUtil.adjustViewName("/sym/log/slg/SysHistRegist");
	}

	/**
	 * 시스템이력 등록
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/insertSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String insertSysHistory(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(sysHistoryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/log/slg/SysHistRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		sysHistoryVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "SHF_"));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		sysHistoryVO.setFrstRegisterId(loginVO.getUniqId());
		
		sysHistoryService.insertSysHistory(sysHistoryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/sym/log/slg/listSysHistory.do");
	}

	/**
	 * 시스템이력 수정 화면
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/editSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String editSysHistory(
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			ModelMap model) {

		sysHistoryService.selectSysHistory(sysHistoryVO);

		cmmUseService.populateCmmCodeList("COM002", "COM002_histSe");
		
		return WebUtil.adjustViewName("/sym/log/slg/SysHistEdit");
	}

	/**
	 * 시스템이력 수정
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/updateSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String updateSysHistory(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(sysHistoryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/sym/log/slg/SysHistEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = sysHistoryVO.getAtchFileId();
		sysHistoryVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "SHF_", atchFileId));

		sysHistoryService.updateSysHistory(sysHistoryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/sym/log/slg/listSysHistory.do");
	}

	/**
	 * 시스템이력 삭제
	 * 
	 * @param sysHistoryVO
	 */
	@RequestMapping(value = "/sym/log/slg/deleteSysHistory.do")
	@Secured("ROLE_ADMIN")
	public String deleteSysHistory(
			@ModelAttribute SysHistoryVO sysHistoryVO, 
			ModelMap model) {

		sysHistoryService.deleteSysHistory(sysHistoryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/sym/log/slg/listSysHistory.do");
	}

}
