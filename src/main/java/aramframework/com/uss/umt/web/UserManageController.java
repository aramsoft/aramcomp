package aramframework.com.uss.umt.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.SearchCodeVO;
import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.umt.domain.UserManageVO;
import aramframework.com.uss.umt.service.UserManageService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 업무사용자관련 요청을 비지니스 클래스로 전달하고 
 * 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
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
public class UserManageController {

	@Autowired
	private UserManageService userManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사용자목록을 조회한다. (pageing)
	 * 
	 * @param userManageVO
	 */
	@IncludedInfo(name = "업무사용자관리", order = 5000, gid = 50)
	@RequestMapping(value = "/uss/umt/listUser.do")
	@Secured("ROLE_ADMIN")
	public String listUser(
			@ModelAttribute UserManageVO userManageVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		userManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", userManageService.selectUserList(userManageVO));

		int totCnt = userManageService.selectUserListCnt(userManageVO);
		userManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 사용자상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		return WebUtil.adjustViewName("/uss/umt/UserList");
	}

	/**
	 * 입력한 사용자아이디의 중복확인화면 이동
	 * 
	 */
	@RequestMapping(value = "/uss/umt/checkIdDplctView.do")
	public String checkIdDplctView(ModelMap model) {
		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "aramframework/com/uss/umt/IdDplctCheck";
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * 
	 */
	@RequestMapping(value = "/uss/umt/checkIdDplct.do")
	public String checkIdDplct(
			HttpServletRequest request, 
			ModelMap model) {

		String checkId = request.getParameter("checkId");
		if (checkId == null || checkId.equals("")) {
			model.addAttribute("checkId", "");
			model.addAttribute("usedCnt", "-1");
			return "aramframework/com/uss/umt/IdDplctCheck";
		}

		int usedCnt = userManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "aramframework/com/uss/umt/IdDplctCheck";
	}

	/**
	 * 사용자등록화면으로 이동한다.
	 * 
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/registUser.do")
	@Secured("ROLE_ADMIN")
	public String registUser(
			@ModelAttribute UserManageVO userManageVO, 
			ModelMap model) {

		fill_common_code(model);
		
		return WebUtil.adjustViewName("/uss/umt/UserRegist");
	}

	/**
	 * 사용자등록처리후 목록화면으로 이동한다.
	 * 
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/insertUser.do")
	@Secured("ROLE_ADMIN")
	public String insertUser(
			@ModelAttribute UserManageVO userManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(userManageVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 조직정보를 조회 - ORGNZT_ID정보
			SearchCodeVO vo = new SearchCodeVO();
			vo.setTableNm("COMTN_ORGNZT_INFO");
			model.addAttribute("orgnztId_result", cmmUseService.selectOgrnztIdList(vo));

			return WebUtil.adjustViewName("/uss/umt/UserRegist");
		} 
		
		if (userManageVO.getOrgnztId().equals("")) {
			userManageVO.setOrgnztId(null);
		}
		userManageService.insertUser(userManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/umt/listUser.do");
	}

	/**
	 * 사용자정보 수정을 위해 사용자정보를 상세조회한다.
	 * 
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/editUser.do")
	@Secured("ROLE_USER")
	public String editUser(
			@ModelAttribute UserManageVO userManageVO, 
			ModelMap model) {

		fill_common_code(model);
		userManageService.selectUser(userManageVO);

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/uss/umt/UserEdit");
	}

	/**
	 * 사용자정보 수정후 상세조회 화면으로 이동한다.
	 * 
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/updateUser.do")
	@Secured("ROLE_USER")
	public String updateUser(
			@ModelAttribute UserManageVO userManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(userManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// 조직정보를 조회 - ORGNZT_ID정보
			SearchCodeVO vo = new SearchCodeVO();
			vo.setTableNm("COMTN_ORGNZT_INFO");
			model.addAttribute("orgnztId_result", cmmUseService.selectOgrnztIdList(vo));

			return WebUtil.adjustViewName("/uss/umt/UserEdit");
		} 

		// 업무사용자 수정시 히스토리 정보를 등록한다.
		userManageService.insertUserHistory(userManageVO);

		if (userManageVO.getOrgnztId().equals("")) {
			userManageVO.setOrgnztId(null);
		}

		userManageService.updateUser(userManageVO);
		updateUserSession(userManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/umt/editUser.do?uniqId="+userManageVO.getUniqId());
	}

	private void updateUserSession(UserManageVO userManageVO) {
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginVO.setName(userManageVO.getEmplyrNm());
		loginVO.setEmail(userManageVO.getEmailAdres());
		loginVO.setOrgnztId(userManageVO.getOrgnztId());
	}
	
	/**
	 * 업무사용자 암호 수정 화면 이동
	 * 
	 * @param userManageVO
	 */
	@RequestMapping(value = "/uss/umt/editUserPassword.do")
	@Secured("ROLE_USER")
	public String editUserPassword(
			@ModelAttribute UserManageVO userManageVO,
			ModelMap model) {

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return WebUtil.adjustViewName("/uss/umt/UserPassword");
	}

	/**
	 * 업무사용자 암호 수정처리 후 상세조회화면 이동
	 * 
	 * @param userManageVO
	 */
	@RequestMapping(value = "/uss/umt/updateUserPassword.do")
	@Secured("ROLE_USER")
	public String updateUserPassword(
			HttpServletRequest request, 
			@ModelAttribute UserManageVO userManageVO,
			ModelMap model)
	throws Exception {
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		String uniqId = request.getParameter("uniqId");

		boolean isCorrectPassword = false;
		String message = "";

		UserManageVO resultVO = userManageService.selectPassword(uniqId);
		// 패스워드 암호화
		String encryptPass = FileScrty.encryptPassword(oldPassword);
		if (encryptPass.equals(resultVO.getPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				message = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			message = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			userManageVO.setPassword(FileScrty.encryptPassword(newPassword));
			userManageService.updatePassword(userManageVO);
			message = "success.common.update";
		} 
		
		model.addAttribute("message", MessageHelper.getMessage(message));
	    return WebUtil.redirectJsp(model, "/uss/umt/editUser.do?uniqId="+userManageVO.getUniqId());
	}

	/**
	 * 사용자정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel
	 * @param userManageVO
	 */
	@RequestMapping("/uss/umt/deleteUser.do")
	@Secured("ROLE_ADMIN")
	public String deleteUser(
			@RequestParam String checkedIdForDel, 
			@ModelAttribute UserManageVO userManageVO,
			ModelMap model) {

		userManageService.deleteUser(checkedIdForDel);
	
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/umt/listUser.do");
	}

	private void fill_common_code(ModelMap model)  {

		// 패스워드힌트목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM022", "COM022_passwordHint");

		// 성별구분코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM014", "COM014_sexdstn");

		// 사용자상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		// 소속기관코드를 코드정보로부터 조회 - COM025
		cmmUseService.populateCmmCodeList("COM025", "COM025_instt");

		// 조직정보를 조회 - ORGNZT_ID정보
		SearchCodeVO vo = new SearchCodeVO();
		vo.setTableNm("COMTN_ORGNZT_INFO");
		model.addAttribute("orgnztId_result", cmmUseService.selectOgrnztIdList(vo));
	}
	
}
