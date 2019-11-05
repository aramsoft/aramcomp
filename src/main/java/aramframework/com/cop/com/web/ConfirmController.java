package aramframework.com.cop.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.com.domain.ConfirmHistoryVO;
import aramframework.com.cop.com.service.ConfirmService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 승인정보 관리를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class ConfirmController {

	@Autowired 
	private ConfirmService confirmService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired 
	private UserInfService userInfService; // 커뮤니티 사용자 확인

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	protected void checkAuthorityManager() {
		if( userInfService.checkCommunityManager().equals("N") ){
			throw new RuntimeException("해당 관리자만 사용하실 수 있습니다.");
		}
	}

	/**
	 * 커뮤니티/동호회 관리부분에서 승인(탈퇴)요청에 대한 목록을 조회한다.
	 * 
	 * @param confirmHistoryVO
	 */
	@RequestMapping("/cop/com/listConfirmByTrget.do")
	public String listConfirmByTrget(
			@ModelAttribute ConfirmHistoryVO confirmHistoryVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		confirmHistoryVO.setTrgetJobId(WebUtil.getCurTrgetId());
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		confirmHistoryVO.setConfmerId(loginVO.getUniqId());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		confirmHistoryVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", confirmService.selectConfirmRequestList(confirmHistoryVO));
		int totCnt = confirmService.selectConfirmRequestListCnt(confirmHistoryVO);

		confirmHistoryVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/com/ConfirmList");
	}

	/**
	 * 승인(탈퇴)요청 확인 처리를 위해 수정페이지로 이동한다.
	 * 
	 * @param confirmHistoryVO
	 */
	@RequestMapping("/cop/com/editConfirm.do")
	@Secured("ROLE_USER")
	public String editConfirm(
			@ModelAttribute ConfirmHistoryVO confirmHistoryVO,
			ModelMap model) {
		
		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		confirmHistoryVO.setConfmerId(loginVO.getUniqId());

		model.addAttribute(confirmService.selectSingleConfirmRequest(confirmHistoryVO));

		cmmUseService.populateCmmCodeList("COM007", "COM007_confmSttus");

		return WebUtil.adjustViewName("/cop/com/ConfirmEdit");
	}

	/**
	 * 승인(탈퇴)요청에 대한 확인을 처리한다.
	 * 
	 * @param confirmHistoryVO
	 */
	@RequestMapping("/cop/com/updateConfirm.do")
	@Secured("ROLE_USER")
	public String updateConfirm(
			@ModelAttribute ConfirmHistoryVO confirmHistoryVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		confirmHistoryVO.setConfmerId(loginVO.getUniqId());

		confirmService.updateConfirmRequest(confirmHistoryVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, confirmHistoryVO, "/cop/com/listConfirmByTrget.do");
	}

}
