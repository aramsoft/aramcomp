package aramframework.com.cop.cmy.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.domain.CommunityVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.cop.com.domain.ConfirmHistoryVO;
import aramframework.com.cop.com.service.ConfirmService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.DateUtil;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티 정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class CommunityManageController {

	@Autowired 
	private CommunityManageService cmmntyService;

	@Autowired 
	private ConfirmService confirmService;

	@Autowired 
	private UserInfService userInfService; // 커뮤니티 사용자 확인

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	private void checkAuthorityManager() {
		if( userInfService.checkCommunityManager().equals("N") ){
			throw new RuntimeException("해당 관리자만 사용하실 수 있습니다.");
		}
	}

	/**
	 * 팝업을 위한 커뮤니티 목록 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/listCommunityPopup.do")
	public String listCommunityPopup(
			@ModelAttribute CommunityVO communityVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		communityVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmmntyService.selectCommunityList(communityVO));
		int totCnt = cmmntyService.selectCommunityListCnt(communityVO);

		communityVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/cmy/CmmntyListPopup";
	}

	/**
	 * 포트릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/listCommunityPortlet.do")
	public String listCommunityPortlet(
			@ModelAttribute CommunityVO communityVO, 
			ModelMap model) {

		model.addAttribute("resultList", cmmntyService.selectCmmntyListPortlet(communityVO));

		return "com/cop/cmy/CmmntyListPortlet";
	}

	/**
	 * 커뮤니티에 대한 목록을 조회한다.
	 * 
	 * @param communityVO
	 */
	@IncludedInfo(name = "커뮤니티관리", order = 4040, gid = 40)
	@RequestMapping("/cop/cmy/listCommunity.do")
	@Secured("ROLE_ADMIN")
	public String listCommunity(
			@ModelAttribute CommunityVO communityVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		communityVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", cmmntyService.selectCommunityList(communityVO));
		int totCnt = cmmntyService.selectCommunityListCnt(communityVO);

		communityVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/cmy/CmmntyList";
	}

	/**
	 * 커뮤니티에 대한 상세정보를 조회한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/detailCommunity.do")
	@Secured("ROLE_USER")
	public String detailCommunity(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityVO communityVO, 
			HttpServletRequest request, 
			ModelMap model) {
		
		checkAuthorityManager(); // server-side 권한 확인

		String trgetId = WebUtil.getCurTrgetId();
		
		if( communityVO.getCmmntyId() == null 
			|| communityVO.getCmmntyId().equals("") ) {
			communityVO.setCmmntyId(trgetId);
		}
		
		communityVO = cmmntyService.selectCommunityInf(communityVO);

		// -----------------------
		// 제공 URL
		// -----------------------
//		communityVO.setProvdUrl(request.getContextPath() + "/apps/id/" + communityVO.getCmmntyId());
		communityVO.setProvdUrl2(request.getContextPath() + "/apps/id/" + communityVO.getPathId());
  
		model.addAttribute("bbsList", cmmntyService.selectCommunityBBSUseInf(communityVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		model.addAttribute(communityVO);
		
		return "com/cop/cmy/CmmntyDetail";
	}

	/**
	 * 커뮤니티 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/registCommunity.do")
	@Secured("ROLE_ADMIN")
	public String registCommunity(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityVO communityVO, 
			ModelMap model) {
		
		return "com/cop/cmy/CmmntyRegist";
	}

	/**
	 * 커뮤니티 정보를 등록한다.
	 * 
	 * @param multiRequest
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/insertCommunity.do")
	@Secured("ROLE_ADMIN")
	public String insertCommunity(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityVO communityVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(communityVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/cmy/CmmntyRegist";
		}

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			// 파일 수정여부 확인
			if (file.getOriginalFilename() != "") {
				if (file.getName().equals("cmmntyImageName")) {
					communityVO.setCmmntyLogoImage(file.getBytes());
				}
			}
		}	
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		communityVO.setUseAt("Y");
		communityVO.setRegistSeCode("REGC02");	// 커뮤니티 등록 
		communityVO.setFrstRegisterId(loginVO.getUserId());

		cmmntyService.insertCommunityInf(communityVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/cmy/listCommunity.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 정보 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/editCommunity.do")
	@Secured("ROLE_USER")
	public String editCommunity(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityVO communityVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		communityVO = cmmntyService.selectCommunityInf(communityVO);

		model.addAttribute("manager", cmmntyService.selectCommunityManagerInf(communityVO));
		model.addAttribute("bbsList", cmmntyService.selectCommunityBBSUseInf(communityVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		model.addAttribute(communityVO);
		
		return "com/cop/cmy/CmmntyEdit";
	}

	/**
	 * 커뮤니티 정보를 수정한다.
	 * 
	 * @param multiRequest
	 * @param communityVO
	 */
	@RequestMapping("/cop/cmy/updateCommunity.do")
	@Secured("ROLE_USER")
	public String updateCommunity(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute CommunityVO communityVO, 
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(communityVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("manager",cmmntyService.selectCommunityManagerInf(communityVO));
			model.addAttribute("bbsList", cmmntyService.selectCommunityBBSUseInf(communityVO));

			return "com/cop/cmy/CmmntyEdit";
		}

		for (MultipartFile file : multiRequest.getFileMap().values()) {
			// 파일 수정여부 확인
			if (file.getOriginalFilename() != "") {
				if (file.getName().equals("cmmntyImageName")) {
					communityVO.setCmmntyLogoImage(file.getBytes());
				}
			}
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		communityVO.setLastUpdusrId(loginVO.getUserId());

		if ("Y".equals(communityVO.getUseAt())) {
			cmmntyService.updateCommunityInf(communityVO);
		} else {
			cmmntyService.deleteCommunityInf(communityVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/cmy/detailCommunity.do?cmmntyId="+communityVO.getCmmntyId());
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 사용신청을 등록한다.
	 * 
	 * @param trgetId
	 */
	@RequestMapping("/cop/cmy/insertCmmntyUserBySelf.do")
	@Secured("ROLE_USER")
	public String insertCmmntyUserBySelf(
			@RequestParam(value="trgetId", required=false) String trgetId,
			ModelMap model) {

		if( trgetId == null || "".equals(trgetId)) 
			trgetId = WebUtil.getCurTrgetId();
		
		String retVal = "";

		CommunityUserVO communityUserVO = new CommunityUserVO(); 
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		communityUserVO.setCmmntyId(trgetId);
		communityUserVO.setUseAt("Y");
		communityUserVO.setFrstRegisterId(loginVO.getUserId());
		communityUserVO.setEmplyrId(loginVO.getUserId());

		// ---------------------------------------------
		// 승인요청 처리
		// ---------------------------------------------
		// retVal = cmmntyService.insertCommunityUserInf(cmmntyUser);
		retVal = cmmntyService.checkCommunityUserInf(communityUserVO);

		if (!retVal.equals("EXIST")) {

			CommunityVO communityVO = new CommunityVO();
			communityVO.setCmmntyId(communityUserVO.getCmmntyId());

			CommunityUserVO manager = cmmntyService.selectCommunityManagerInf(communityVO);

			ConfirmHistoryVO confirmHistoryVO = new ConfirmHistoryVO();
			confirmHistoryVO.setConfmRqesterId(loginVO.getUserId()); // 요청자 ID
			confirmHistoryVO.setConfmerId(manager.getEmplyrId()); // 관리자
			confirmHistoryVO.setConfmTyCode("CF11"); // 커뮤니티사용자등록
			confirmHistoryVO.setConfmSttusCode("AP01"); // 승인요청
			confirmHistoryVO.setOpertTyCode("WC01"); // 회원가입
			confirmHistoryVO.setOpertId(""); // 작업자 ID
			confirmHistoryVO.setTrgetJobTyCode("CMY"); // 대상작업구분
			confirmHistoryVO.setTrgetJobId(trgetId); // 대상작업 ID

			int cnt = confirmService.countConfirmRequest(confirmHistoryVO);

			if (cnt == 0) {
				confirmService.insertConfirmRequest(confirmHistoryVO);
			} else {
				retVal = "ING";
			}
		}

		model.addAttribute("returnMsg", retVal);
		return "com/cop/cmy/CmmntyMsg";
	}

	/**
	 * 커뮤니티 탈퇴신청을 처리한다.
	 * 
	 * @param communityUserVO
	 */
	@RequestMapping("/cop/cmy/deleteCmmntyUserBySelf.do")
	@Secured("ROLE_USER")
	public String deleteCmmntyUserBySelf(
			@ModelAttribute CommunityUserVO communityUserVO, 
			ModelMap model) {

		String retVal = "DEL_REQ_SUCCESS";

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		communityUserVO.setUseAt("N");
		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		// cmmntyUser.setEmplyrId(user.getUserId()); //커뮤니티 탈퇴 요청시 승인자를 선택하므로 탈퇴
		// 승인자가 자신이 될 수 없음(2011.9.7 수정분)
		communityUserVO.setSecsnDe(DateUtil.getToday());

		ConfirmHistoryVO confirmHistoryVO = new ConfirmHistoryVO();

		confirmHistoryVO.setConfmRqesterId(loginVO.getUserId());
		confirmHistoryVO.setConfmerId(communityUserVO.getEmplyrId());
		confirmHistoryVO.setConfmTyCode("CF12"); // 006
		confirmHistoryVO.setConfmSttusCode("AP01"); // 007
		confirmHistoryVO.setOpertTyCode("WC03");
		confirmHistoryVO.setOpertId("");
		confirmHistoryVO.setTrgetJobTyCode("CMY");
		confirmHistoryVO.setTrgetJobId(communityUserVO.getCmmntyId());

		confirmService.insertConfirmRequest(confirmHistoryVO);

		// cmmntyService.deleteCommunityUserInf(cmmntyUser);

		model.addAttribute("returnMsg", retVal);
		return "com/cop/cmy/CmmntyMsg";
	}

}
