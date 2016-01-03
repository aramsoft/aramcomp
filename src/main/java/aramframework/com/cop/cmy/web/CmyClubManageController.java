package aramframework.com.cop.cmy.web;

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
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.clb.service.ClubVO;
import aramframework.com.cop.clb.service.ClubManageService;
import aramframework.com.cop.com.service.UserInfService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티, 동호회 정보를 관리하기 위한 컨트롤러 클래스
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
public class CmyClubManageController {

	@Resource(name = "clubManageService")
	private ClubManageService clubService;

	@Resource(name = "userInfService")
	private UserInfService userService; // 커뮤니티 사용자 확인

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 커뮤니티 관리자 및 동호회 운영자 권한을 확인한다.
	 * 
	 */
	private void checkAuthorityManager() {
		if( userService.checkCommunityManager().equals("N") ){
			throw new RuntimeException("해당 커뮤니티 관리자만 사용하실 수 있습니다.");
		}
	}

	/**
	 * 특정 커뮤니티에 사용되는 동호회 목록을 조회한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/listClubByTrget.do")
	@Secured("ROLE_USER")
	public String listClubByTrget(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		PaginationInfo paginationInfo = new PaginationInfo();
		clubVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", clubService.selectClubInfsByTrgetId(clubVO));

		int totCnt = clubService.selectClubInfsCntByTrgetId(clubVO);
		clubVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/cmy/ClubListByTrget");
	}

	/**
	 * 커뮤니티에 사용되는 동호회 상세정보를 조회한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/detailClubByTrget.do")
	@Secured("ROLE_USER")
	public String detailClubByTrget(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {
		
		checkAuthorityManager(); // server-side 권한 확인

		clubService.selectClubInf(clubVO);
		
		model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));

		return WebUtil.adjustViewName("/cop/cmy/ClubDetailByTrget");
	}

	/**
	 * 커뮤니티를 위한 동호회 등록화면으로 이동한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/registClubByTrget.do")
	@Secured("ROLE_USER")
	public String registClubByTrget(
			@ModelAttribute ClubVO clubVO)  {

		checkAuthorityManager(); // server-side 권한 확인

		return WebUtil.adjustViewName("/cop/cmy/ClubRegistByTrget");
	}

	/**
	 * 동호회 정보를 등록한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/insertClubByTrget.do")
	@Secured("ROLE_USER")
	public String insertClubByTrget(
			@ModelAttribute ClubVO clubVO, 
			BindingResult bindingResult,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		beanValidator.validate(clubVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/cmy/ClubRegistByTrget");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubVO.setUseAt("Y");
		clubVO.setRegistSeCode("REGC03");
		clubVO.setFrstRegisterId(loginVO.getUniqId());

		clubService.insertClubInf(clubVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listClubByTrget.do");
	}

	/**
	 * 커뮤니티에 사용되는 동호회 정보 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/editClubByTrget.do")
	@Secured("ROLE_USER")
	public String editClubByTrget(
			@ModelAttribute ClubVO clubVO, 
			ModelMap model) {
		
		checkAuthorityManager(); // server-side 권한 확인

		clubService.selectClubInf(clubVO);

		model.addAttribute("operator", clubService.selectClubOperatorInf(clubVO));
		model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));

		return WebUtil.adjustViewName("/cop/cmy/ClubEditByTrget");
	}

	/**
	 * 커뮤니티에 사용되는 동호회 정보를 수정한다.
	 * 
	 * @param clubVO
	 */
	@RequestMapping("/cop/cmy/updateClubByTrget.do")
	@Secured("ROLE_USER")
	public String updateClubByTrget(
			@ModelAttribute ClubVO clubVO, 
			BindingResult bindingResult,
			ModelMap model) {

		checkAuthorityManager(); // server-side 권한 확인

		beanValidator.validate(clubVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("bbsList", clubService.selectClubUseBBSInfs(clubVO));
			return WebUtil.adjustViewName("/cop/cmy/ClubEditByTrget");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		clubVO.setLastUpdusrId(loginVO.getUniqId());

		clubService.updateClubInf(clubVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/cmy/listClubByTrget.do");
	}

}
