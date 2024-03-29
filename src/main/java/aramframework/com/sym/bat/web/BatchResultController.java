package aramframework.com.sym.bat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.sym.bat.domain.BatchResultVO;
import aramframework.com.sym.bat.service.BatchResultService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 배치결과관리에 대한 controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BatchResultController {

	@Autowired
	private BatchResultService batchResultService;
	
	/**
	 * 배치결과 목록을 조회한다.
	 * 
	 * @param batchResultVO
	 */
	@IncludedInfo(name = "배치결과관리", order = 6220, gid = 60)
	@RequestMapping("/sym/bat/listBatchResult.do")
	@Secured("ROLE_ADMIN")
	public String listBatchResult(
			@ModelAttribute BatchResultVO batchResultVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		batchResultVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", batchResultService.selectBatchResultList(batchResultVO));
		int totCnt = batchResultService.selectBatchResultListCnt(batchResultVO);

		batchResultVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/sym/bat/BatchResultList";
	}

	/**
	 * 배치결과정보을 상세조회한다.
	 * 
	 * @param batchResultVO
	 */
	@RequestMapping("/sym/bat/detailBatchResult.do")
	@Secured("ROLE_ADMIN")
	public String detailBatchResult(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BatchResultVO batchResultVO,
			ModelMap model) {
		
		model.addAttribute(batchResultService.selectBatchResult(batchResultVO));

		return "com/sym/bat/BatchResultDetail";
	}

	/**
	 * 배치결과을 삭제한다.
	 * 
	 * @param batchResultVO
	 */
	@RequestMapping("/sym/bat/deleteBatchResult.do")
	@Secured("ROLE_ADMIN")
	public String deleteBatchResult(
			@ModelAttribute BatchResultVO batchResultVO, 
			ModelMap model) {

		batchResultService.deleteBatchResult(batchResultVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/sym/bat/listBatchResult.do");
	    return "com/cmm/redirect";
	}

}