package aramframework.com.sym.sym.bak.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sym.sym.bak.domain.BackupResultVO;
import aramframework.com.sym.sym.bak.service.BackupResultService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 백업결과관리에 대한 controller 클래스
 * 
 * 백업결과관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 * 백업결과관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BackupResultController {

	@Autowired
	private BackupResultService backupResultService;

	/**
	 * 백업결과 목록을 조회한다.
	 * 
	 * @param backupResultVO
	 */
	@IncludedInfo(name = "백업결과관리", order = 6231, gid = 60)
	@RequestMapping("/sym/sym/bak/listBackupResult.do")
	@Secured("ROLE_ADMIN")
	public String listBackupResult(
			@ModelAttribute BackupResultVO backupResultVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		backupResultVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", backupResultService.selectBackupResultList(backupResultVO));
		int totCnt = backupResultService.selectBackupResultListCnt(backupResultVO);

		backupResultVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "sym/sym/bak/BackupResultList";
	}

	/**
	 * 백업결과정보을 상세조회한다.
	 * 
	 * @param backupResultVO
	 */
	@RequestMapping("/sym/sym/bak/detailBackupResult.do")
	@Secured("ROLE_ADMIN")
	public String detailBackupResult(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BackupResultVO backupResultVO,
			ModelMap model) {
		
		model.addAttribute(backupResultService.selectBackupResult(backupResultVO));

		return "sym/sym/bak/BackupResultDetail";
	}

	/**
	 * 백업결과을 삭제한다.
	 * 
	 * @param backupResultVO
	 */
	@RequestMapping("/sym/sym/bak/deleteBackupResult.do")
	@Secured("ROLE_ADMIN")
	public String deleteBackupResult(
			@ModelAttribute BackupResultVO backupResultVO, 
			ModelMap model) {

		backupResultService.deleteBackupResult(backupResultVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, backupResultVO, "/sym/sym/bak/listBackupResult.do");
	}

}