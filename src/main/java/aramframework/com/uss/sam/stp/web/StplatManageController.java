package aramframework.com.uss.sam.stp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.sam.stp.service.StplatManageService;
import aramframework.com.uss.sam.stp.service.StplatManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 약관내용을 처리하는 비즈니스 구현 클래스
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
public class StplatManageController {

	@Autowired
	private StplatManageService stplatManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 약관정보 목록을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	@IncludedInfo(name = "약관관리", order = 5040, gid = 50)
	@RequestMapping(value = "/uss/sam/stp/listStplat.do")
	@Secured("ROLE_ADMIN")
	public String listStplat(
			@ModelAttribute StplatManageVO stplatManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		stplatManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", stplatManageService.selectStplatList(stplatManageVO));

		int totCnt = stplatManageService.selectStplatListCnt(stplatManageVO);
		stplatManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/sam/stp/StplatList");
	}

	/**
	 * 약관정보상세내용을 조회한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/detailStplat.do")
	@Secured("ROLE_ADMIN")
	public String detailStplat(
			@ModelAttribute StplatManageVO stplatManageVO) {

		stplatManageService.selectStplatDetail(stplatManageVO);

		return WebUtil.adjustViewName("/uss/sam/stp/StplatDetail");
	}

	/**
	 * 약관정보를 등록하기 위한 전 처리
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/registStplat.do")
	@Secured("ROLE_ADMIN")
	public String registStplat(
			@ModelAttribute StplatManageVO stplatManageVO) {

		return WebUtil.adjustViewName("/uss/sam/stp/StplatRegist");
	}

	/**
	 * 약관정보를 등록한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/insertStplat.do")
	@Secured("ROLE_ADMIN")
	public String insertStplat(
			@ModelAttribute StplatManageVO stplatManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(stplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/sam/stp/StplatRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		stplatManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		stplatManageService.insertStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/uss/sam/stp/listStplat.do");
	}

	/**
	 * 약관정보를 수정하기 위한 전 처리
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/editStplat.do")
	@Secured("ROLE_ADMIN")
	public String editStplat(
			@ModelAttribute StplatManageVO stplatManageVO) {

		stplatManageService.selectStplatDetail(stplatManageVO);

		return WebUtil.adjustViewName("/uss/sam/stp/StplatEdit");
	}

	/**
	 * 약관정보를 수정 처리한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/updateStplat.do")
	@Secured("ROLE_ADMIN")
	public String updateStplat(
			@ModelAttribute StplatManageVO stplatManageVO,
			BindingResult bindingResult, 
			ModelMap model) {

		// Validation
		beanValidator.validate(stplatManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/sam/stp/StplatEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		stplatManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		stplatManageService.updateStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/uss/sam/stp/listStplat.do");
	}

	/**
	 * 약관정보를 삭제 처리한다.
	 * 
	 * @param stplatManageVO
	 */
	@RequestMapping("/uss/sam/stp/deleteStplat.do")
	@Secured("ROLE_ADMIN")
	public String deleteStplat(
			@ModelAttribute StplatManageVO stplatManageVO, 
			ModelMap model) {

		stplatManageService.deleteStplat(stplatManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/uss/sam/stp/listStplat.do");
	}

}
