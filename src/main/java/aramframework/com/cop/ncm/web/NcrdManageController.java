package aramframework.com.cop.ncm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.security.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cop.ncm.domain.NameCardUseVO;
import aramframework.com.cop.ncm.domain.NameCardVO;
import aramframework.com.cop.ncm.service.NcrdManageService;
import aramframework.com.uat.uia.domain.LoginVO;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 명함정보를 관리하기 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NcrdManageController {

	@Autowired 
	private NcrdManageService ncrdService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 명함 정보에 대한 상세정보를 조회한다.(POPUP)
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/detailNameCardPopup.do")
	public String detailNameCardPopup(
			NameCardVO nameCardVO,
			ModelMap model) {

		model.addAttribute(ncrdService.selectNcrdItem(nameCardVO));

		return "com/cop/ncm/NameCardPopup";
	}

	/**
	 * 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	@IncludedInfo(name = "내명함목록", order = 4071, gid = 40)
	@RequestMapping("/cop/ncm/listNameCard.do")
	@Secured("ROLE_USER")
	public String listNameCard(
			@ModelAttribute NameCardVO nameCardVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardVO.setEmplyrId(loginVO.getUserId());
		model.addAttribute("userId", loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		nameCardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ncrdService.selectNcrdListMine(nameCardVO));
		int totCnt = ncrdService.selectNcrdListMineCnt(nameCardVO);
	
		nameCardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/ncm/NameCardList";
	}

	/**
	 * 명함 정보 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/registNameCard.do")
	@Secured("ROLE_USER")
	public String registNameCard(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardVO nameCardVO, 
			ModelMap model) {
		
		return "com/cop/ncm/NameCardRegist";
	}

	/**
	 * 명함 정보를 등록한다.
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/insertNameCard.do")
	@Secured("ROLE_USER")
	public String insertNameCard(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardVO nameCardVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(nameCardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/ncm/NameCardRegist";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardVO.setFrstRegisterId(loginVO.getUserId());
//		nameCardVO.setTrgetId(loginVO.getOrgnztId());

		ncrdService.insertNcrdItem(nameCardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/ncm/listNameCard.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 명함 정보수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/editNameCard.do")
	@Secured("ROLE_USER")
	public String editNameCard(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardVO nameCardVO, 
			ModelMap model) {

		nameCardVO = ncrdService.selectNcrdItem(nameCardVO);

		boolean writer = false;
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (nameCardVO.getFrstRegisterId().equals(loginVO.getUserId())) {
			writer = true;
		}
		model.addAttribute("writer", writer);
		model.addAttribute(nameCardVO);

		return "com/cop/ncm/NameCardEdit";
	}

	/**
	 * 명함 정보를 수정한다.
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/updateNameCard.do")
	@Secured("ROLE_USER")
	public String updateNameCard(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardVO nameCardVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(nameCardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "com/cop/ncm/NameCardEdit";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardVO.setLastUpdusrId(loginVO.getUserId());

		ncrdService.updateNcrdItem(nameCardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		model.addAttribute("redirectURL", "/cop/ncm/listNameCard.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 명함 정보를 삭제한다.
	 * 
	 * @param nameCardVO
	 */
	@RequestMapping("/cop/ncm/deleteNameCard.do")
	@Secured("ROLE_USER")
	public String deleteNameCard(
			@ModelAttribute NameCardVO nameCardVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardVO.setLastUpdusrId(loginVO.getUserId());

		ncrdService.deleteNcrdItem(nameCardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/ncm/listNameCard.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 명함 정보에 대한 목록을 조회한다.
	 * 
	 * @param nameCardVO
	 */
	@IncludedInfo(name = "공개명함목록", order = 4070, gid = 40)
	@RequestMapping("/cop/ncm/listNameCardPublic.do")
	@Secured("ROLE_USER")
	public String listNameCardPublic(
			@ModelAttribute NameCardVO nameCardVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardVO.setEmplyrId(loginVO.getUserId());
		model.addAttribute("userId", loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		nameCardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ncrdService.selectNcrdListPublic(nameCardVO));
		int totCnt = ncrdService.selectNcrdListPublicCnt(nameCardVO);

		nameCardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/ncm/NameCardPublic";
	}

	/**
	 * 명함사용자 정보를 등록한다.
	 * 
	 * @param nameCardUseVO
	 */
	@RequestMapping("/cop/ncm/insertNameCardUse.do")
	@Secured("ROLE_USER")
	public String insertNameCardUse(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardUseVO nameCardUseVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardUseVO.setEmplyrId(loginVO.getUserId());
		nameCardUseVO.setUseAt("Y");

		ncrdService.insertNcrdUseInf(nameCardUseVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		model.addAttribute("redirectURL", "/cop/ncm/listNameCardPublic.do");
	    return "com/cmm/redirect";
	}

	/**
	 * 명함사용자 정보를 삭제한다.
	 * 
	 * @param nameCardUseVO
	 */
	@RequestMapping("/cop/ncm/deleteNameCardUse.do")
	@Secured("ROLE_USER")
	public String deleteNameCardUse(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute NameCardUseVO nameCardUseVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		nameCardUseVO.setEmplyrId(loginVO.getUserId());

		ncrdService.deleteNcrdUseInf(nameCardUseVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		model.addAttribute("redirectURL", "/cop/ncm/listNameCard.do");
	    return "com/cmm/redirect";
	}
	
}
