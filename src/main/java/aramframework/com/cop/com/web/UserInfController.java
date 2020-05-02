package aramframework.com.cop.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.cmy.domain.CommunityUserVO;
import aramframework.com.cop.cmy.service.CommunityManageService;
import aramframework.com.cop.com.domain.UserInfVO;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.fcc.service.DateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 협업기능에서 활용하는 사용자 정보 조회용 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class UserInfController {

	@Autowired 
	private CommunityManageService cmmntyService; // 커뮤니티 관리자 권한 확인

	@Autowired 
	private UserInfService userInfService;

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
	 * 사용자 정보에 대한 목록을 조회한다.
	 * 
	 * @param userInfVO
	 * @param popFlag
	 */
	@RequestMapping("/cop/com/listUser.do")
	public String selectUserList(
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="PopFlag", required=false) String popFlag,
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		userInfVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userInfService.selectUserList(userInfVO));
		int totCnt = userInfService.selectUserListCnt(userInfVO);

		userInfVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		model.addAttribute("targetMethod", "selectUserList");

		String returnUrl;
		if ("Y".equals(popFlag)) {
			returnUrl = "cop/com/UserListPop";
		} else {
			returnUrl = "cop/com/UserList";
		}
		return returnUrl;
	}

	/**
	 * 커뮤니티 사용자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 * @param popFlag
	 */
	@RequestMapping("/cop/com/listCmmntyUser.do")
	@Secured("ROLE_USER")
	public String selectCmmntyUserList(
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="PopFlag", required=false) String popFlag,
			ModelMap model) {

		if (!"Y".equals(popFlag)) {
			checkAuthorityManager(); // server-side 권한 확인
		}

		String trgetId = WebUtil.getCurTrgetId();
		
		if( userInfVO.getTrgetId() == null 
			|| userInfVO.getTrgetId().equals("") ) {
			userInfVO.setTrgetId(trgetId);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		userInfVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userInfService.selectCmmntyUserList(userInfVO));
		int totCnt = userInfService.selectCmmntyUserListCnt(userInfVO);

		userInfVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		model.addAttribute("targetMethod", "selectCmmntyUserList");

		String returnUrl;
		if ("Y".equals(popFlag)) {
			returnUrl = "cop/com/UserListPop";
		} else {
			returnUrl = "cop/com/UserList";
		}
		return returnUrl;
	}

	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 * 
	 * @param userInfVO
	 * @param popFlag
	 */
	@RequestMapping("/cop/com/listCmmntyMngr.do")
	@Secured("ROLE_USER")
	public String selectCmmntyMngrList(
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="PopFlag", required=false) String popFlag,
			ModelMap model) {

		if (!"Y".equals(popFlag)) {
			checkAuthorityManager(); // server-side 권한 확인
		}

		String trgetId = WebUtil.getCurTrgetId();
		
		if( userInfVO.getTrgetId() == null 
			|| userInfVO.getTrgetId().equals("") ) {
			userInfVO.setTrgetId(trgetId);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		userInfVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userInfService.selectCmmntyMngrList(userInfVO));
		int totCnt = userInfService.selectCmmntyMngrListCnt(userInfVO);

		userInfVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		model.addAttribute("targetMethod", "selectCmmntyMngrList");

		String returnUrl;
		if ("Y".equals(popFlag)) {
			returnUrl = "cop/com/UserListPop";
		} else if ("S".equals(popFlag)) {
			returnUrl = "cop/com/CmmntyMngrListPop";
		} else {
			returnUrl = "cop/com/UserList";
		}
		return returnUrl;
	}

	/**
	 * 커뮤니티 관리자 정보를 등록한다.
	 * 
	 * @param userInfVO
	 * @param cmmntyId
	 * @param emplyrId
	 */
	@RequestMapping("/cop/com/registCmmntyManager.do")
	@Secured("ROLE_USER")
	public String registCmmntyManager(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="param_cmmntyId", required=true) String cmmntyId,
			@RequestParam(value="param_emplyrId", required=true) String emplyrId,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		CommunityUserVO communityUserVO = new CommunityUserVO();

		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		communityUserVO.setCmmntyId(cmmntyId);
		communityUserVO.setEmplyrId(emplyrId);
		communityUserVO.setUseAt("Y");
		communityUserVO.setMngrAt("Y");

		cmmntyService.updateCommunityUserInf(communityUserVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/com/listCmmntyUser.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 관리자 정보를 삭제한다.
	 * 
	 * @param userInfVO
	 * @param cmmntyId
	 * @param emplyrId
	 */
	@RequestMapping("/cop/com/deleteCmmntyManager.do")
	@Secured("ROLE_USER")
	public String deleteCmmntyManager(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="param_cmmntyId", required=true) String cmmntyId,
			@RequestParam(value="param_emplyrId", required=true) String emplyrId,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		CommunityUserVO communityUserVO = new CommunityUserVO();

		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		communityUserVO.setCmmntyId(cmmntyId);
		communityUserVO.setEmplyrId(emplyrId);
		communityUserVO.setUseAt("Y");
		communityUserVO.setMngrAt("N");

		cmmntyService.updateCommunityUserInf(communityUserVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/com/listCmmntyUser.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 사용자를 재가입 신청처리한다.
	 * 
	 * @param userInfVO
	 * @param cmmntyId
	 * @param emplyrId
	 */
	@RequestMapping("/cop/com/reRegistCmmntyUser.do")
	@Secured("ROLE_USER")
	public String reRegisterCmmntyUser(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="param_cmmntyId", required=true) String cmmntyId,
			@RequestParam(value="param_emplyrId", required=true) String emplyrId,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		CommunityUserVO communityUserVO = new CommunityUserVO();

		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		communityUserVO.setCmmntyId(cmmntyId);
		communityUserVO.setEmplyrId(emplyrId);
		communityUserVO.setUseAt("Y");
		communityUserVO.setMngrAt("N"); 
		// 재가입시 운영자가 아닌 사용자로 초기화함. 
		// 값 설정 없을 시 SQL 에러  발생(2011.9.7 추가사항)

		cmmntyService.updateCommunityUserInf(communityUserVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/com/listCmmntyUser.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 사용자정보를 삭제한다.
	 * 
	 * @param userInfVO
	 * @param cmmntyId
	 * @param emplyrId
	 */
	@RequestMapping("/cop/com/deleteCmmntyUser.do")
	@Secured("ROLE_USER")
	public String deleteCmmntyUser(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="param_cmmntyId", required=true) String cmmntyId,
			@RequestParam(value="param_emplyrId", required=true) String emplyrId,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		CommunityUserVO communityUserVO = new CommunityUserVO();

		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		communityUserVO.setCmmntyId(cmmntyId);
		communityUserVO.setEmplyrId(emplyrId);
		communityUserVO.setSecsnDe(DateUtil.getToday());

		cmmntyService.deleteCommunityUserInf(communityUserVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/com/listCmmntyUser.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 커뮤니티 사용자정보를 제거한다.
	 * 
	 * @param userInfVO
	 * @param cmmntyId
	 * @param emplyrId
	 */
	@RequestMapping("/cop/com/eraseCmmntyUser.do")
	@Secured("ROLE_USER")
	public String eraseCmmntyUser(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute UserInfVO userInfVO, 
			@RequestParam(value="param_cmmntyId", required=true) String cmmntyId,
			@RequestParam(value="param_emplyrId", required=true) String emplyrId,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		CommunityUserVO communityUserVO = new CommunityUserVO();

		communityUserVO.setLastUpdusrId(loginVO.getUserId());
		communityUserVO.setCmmntyId(cmmntyId);
		communityUserVO.setEmplyrId(emplyrId);
		communityUserVO.setSecsnDe(DateUtil.getToday());

		cmmntyService.eraseCommunityUserInf(communityUserVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/com/listCmmntyUser.do");
	    return "com/cmm/redirect";
	}

}
