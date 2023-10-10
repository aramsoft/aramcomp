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

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.com.domain.SearchCodeVO;
import aramframework.com.cmm.com.service.CmmUseService;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.umt.domain.EmplyrManageVO;
import aramframework.com.uss.umt.service.EmplyrManageService;
import aramframework.com.utl.sim.service.FileScrty;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 업무사용자관련 요청을 비지니스 클래스로 전달하고 
 * 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class EmplyrManageController {

	@Autowired
	private EmplyrManageService emplyrManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사용자목록을 조회한다. (pageing)
	 * 
	 * @param emplyrManageVO
	 */
	@IncludedInfo(name = "업무사용자관리", order = 5000, gid = 50)
	@RequestMapping(value = "/uss/umt/listEmplyr.do")
	@Secured("ROLE_ADMIN")
	public String listEmplyr(
			@ModelAttribute EmplyrManageVO emplyrManageVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		emplyrManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", emplyrManageService.selectEmplyrList(emplyrManageVO));
		int totCnt = emplyrManageService.selectEmplyrListCnt(emplyrManageVO);

		emplyrManageVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		// 사용자상태코드를 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM013", "COM013_mberSttus");

		return "com/uss/umt/EmplyrList";
	}

	/**
	 * 입력한 사용자아이디의 중복확인화면 이동
	 * 
	 */
	@RequestMapping(value = "/uss/umt/checkIdDplctView.do")
	public String checkIdDplctView(ModelMap model) {
		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "com/uss/umt/IdDplctCheck";
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
			return "com/uss/umt/IdDplctCheck";
		}

		int usedCnt = emplyrManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "com/uss/umt/IdDplctCheck";
	}

	/**
	 * 사용자등록화면으로 이동한다.
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/registEmplyr.do")
	@Secured("ROLE_ADMIN")
	public String registEmplyr(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO, 
			ModelMap model) {

		fill_common_code(model);
		
		return "com/uss/umt/EmplyrRegist";
	}

	/**
	 * 사용자등록처리후 목록화면으로 이동한다.
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/insertEmplyr.do")
	@Secured("ROLE_ADMIN")
	public String insertEmplyr(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(emplyrManageVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// 조직정보를 조회 - ORGNZT_ID정보
			SearchCodeVO vo = new SearchCodeVO();
			vo.setTableNm("COMTN_ORGNZT_INFO");
			model.addAttribute("orgnztId_result", cmmUseService.selectOgrnztIdList(vo));

			return "com/uss/umt/EmplyrRegist";
		} 
		
		if (emplyrManageVO.getOrgnztId().equals("")) {
			emplyrManageVO.setOrgnztId(null);
		}
		emplyrManageService.insertEmplyr(emplyrManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/uss/umt/listEmplyr.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 사용자정보 수정을 위해 사용자정보를 상세조회한다.
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/editEmplyr.do")
	@Secured("ROLE_USER")
	public String editEmplyr(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO, 
			ModelMap model) {

		fill_common_code(model);
		
		model.addAttribute(emplyrManageService.selectEmplyr(emplyrManageVO));

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return "com/uss/umt/EmplyrEdit";
	}

	/**
	 * 사용자정보 수정후 상세조회 화면으로 이동한다.
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/updateEmplyr.do")
	@Secured("ROLE_USER")
	public String updateEmplyr(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(emplyrManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// 조직정보를 조회 - ORGNZT_ID정보
			SearchCodeVO vo = new SearchCodeVO();
			vo.setTableNm("COMTN_ORGNZT_INFO");
			model.addAttribute("orgnztId_result", cmmUseService.selectOgrnztIdList(vo));

			return "com/uss/umt/EmplyrEdit";
		} 

		// 업무사용자 수정시 히스토리 정보를 등록한다.
		emplyrManageService.insertEmplyrHistory(emplyrManageVO);

		if (emplyrManageVO.getOrgnztId().equals("")) {
			emplyrManageVO.setOrgnztId(null);
		}

		emplyrManageService.updateEmplyr(emplyrManageVO);
		updateEmplyrSession(emplyrManageVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/uss/umt/editEmplyr.do?emplyrId="+emplyrManageVO.getEmplyrId());
	    return "com/cmm/redirect";
	}

	private void updateEmplyrSession(EmplyrManageVO emplyrManageVO) {
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		loginVO.setName(emplyrManageVO.getEmplyrNm());
		loginVO.setEmail(emplyrManageVO.getEmailAdres());
		loginVO.setOrgnztId(emplyrManageVO.getOrgnztId());
	}
	
	/**
	 * 업무사용자 암호 수정 화면 이동
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping(value = "/uss/umt/editEmplyrPassword.do")
	@Secured("ROLE_USER")
	public String editUserPassword(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO,
			ModelMap model) {

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("isAdmin", "true");
		}
		return "com/uss/umt/EmplyrPassword";
	}

	/**
	 * 업무사용자 암호 수정처리 후 상세조회화면 이동
	 * 
	 * @param emplyrManageVO
	 */
	@RequestMapping(value = "/uss/umt/updateEmplyrPassword.do")
	@Secured("ROLE_USER")
	public String updateEmplyrPassword(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO,
			HttpServletRequest request, 
			ModelMap model)
	throws Exception {
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");

		boolean isCorrectPassword = false;
		String message = "";

		EmplyrManageVO resultVO = emplyrManageService.selectPassword(emplyrManageVO.getEmplyrId());
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
			emplyrManageVO.setPassword(FileScrty.encryptPassword(newPassword));
			emplyrManageService.updatePassword(emplyrManageVO);
			message = "success.common.update";
		} 
		
		model.addAttribute("message", MessageHelper.getMessage(message));
		model.addAttribute("redirectURL", "/uss/umt/editEmplyr.do?emplyrId="+emplyrManageVO.getEmplyrId());
	    return "com/cmm/redirect";
	}

	/**
	 * 사용자정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/deleteEmplyr.do")
	@Secured("ROLE_ADMIN")
	public String deleteEmplyr(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO,
			ModelMap model) {

		emplyrManageService.deleteEmplyr(emplyrManageVO);
	
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/uss/umt/listEmplyr.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 사용자정보삭제후 목록조회 화면으로 이동한다.
	 * 
	 * @param checkedIdForDel
	 * @param emplyrManageVO
	 */
	@RequestMapping("/uss/umt/deleteIdsAll.do")
	@Secured("ROLE_ADMIN")
	public String deleteIdsAll(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute EmplyrManageVO emplyrManageVO,
			@RequestParam String returnUrl, 
			HttpServletRequest request, 
			ModelMap model) {

    	String[] uniqIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		uniqIds = request.getParameterValues("uniqIds");

    	emplyrManageService.deleteIdsAll(uniqIds);
	
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", returnUrl);
	    return "com/cmm/redirect";
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
