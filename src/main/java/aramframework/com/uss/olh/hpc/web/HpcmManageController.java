package aramframework.com.uss.olh.hpc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.olh.hpc.domain.HpcmManageVO;
import aramframework.com.uss.olh.hpc.service.HpcmManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 도움말을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class HpcmManageController {

	@Autowired
	private HpcmManageService hpcmManageService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 도움말내용 목록을 조회한다. (pageing)
	 * 
	 * @param hpcmManageVO
	 */
	@IncludedInfo(name = "도움말", order = 5070, gid = 50)
	@RequestMapping(value = "/uss/olh/hpc/listHpcm.do")
	public String listHpcm(
			@ModelAttribute HpcmManageVO hpcmManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		hpcmManageVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", hpcmManageService.selectHpcmList(hpcmManageVO));
		int totCnt = hpcmManageService.selectHpcmListCnt(hpcmManageVO);

		hpcmManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/uss/olh/hpc/HpcmList");
	}

	/**
	 * 도움말내용 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping("/uss/olh/hpc/detailHpcm.do")
	public String detailHpcm(
			HpcmManageVO hpcmManageVO,
			ModelMap model) {

		model.addAttribute(hpcmManageService.selectHpcmDetail(hpcmManageVO));

		return WebUtil.adjustViewName("/uss/olh/hpc/HpcmDetail");
	}

	/**
	 * 도움말내용를 등록하기 위한 전 처리(공통코드 처리)
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping("/uss/olh/hpc/registHpcm.do")
	@Secured("ROLE_USER")
	public String registHpcm(
			@ModelAttribute HpcmManageVO hpcmManageVO) {

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM021", "COM021_hpcmSe");

		return WebUtil.adjustViewName("/uss/olh/hpc/HpcmRegist");
	}

	/**
	 * 도움말내용를 등록한다.
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping("/uss/olh/hpc/insertHpcm.do")
	@Secured("ROLE_USER")
	public String insertHpcm(
			@ModelAttribute HpcmManageVO hpcmManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(hpcmManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/hpc/HpcmRegist");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		hpcmManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID

		hpcmManageService.insertHpcmCn(hpcmManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/olh/hpc/listHpcm.do");
	}

	/**
	 * 도움말내용를 수정하기 위한 전 처리(공통코드 처리)
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping("/uss/olh/hpc/editHpcm.do")
	@Secured("ROLE_USER")
	public String editHpcmManage(
			HpcmManageVO hpcmManageVO,
			ModelMap model) {

		model.addAttribute(hpcmManageService.selectHpcmDetail(hpcmManageVO));

		// 공통코드를 가져오기 위한 Vo
		cmmUseService.populateCmmCodeList("COM021", "COM021_hpcmSe");

		return WebUtil.adjustViewName("/uss/olh/hpc/HpcmEdit");
	}

	/**
	 * 도움말내용를 수정한다.
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping("/uss/olh/hpc/updateHpcm.do")
	@Secured("ROLE_USER")
	public String updateHpcmManage(
			@ModelAttribute HpcmManageVO hpcmManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// Validation
		beanValidator.validate(hpcmManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/olh/hpc/HpcmEdit");
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		hpcmManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID

		hpcmManageService.updateHpcmCn(hpcmManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/olh/hpc/listHpcm.do");
	}

	/**
	 * 도움말내용를 삭제한다.
	 * 
	 * @param hpcmManageVO
	 */
	@RequestMapping(value="/uss/olh/hpc/deleteHpcm.do")
	@Secured("ROLE_USER")
	public String deleteHpcm(
			@ModelAttribute HpcmManageVO hpcmManageVO, 
			ModelMap model) {

		hpcmManageService.deleteHpcmCn(hpcmManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/olh/hpc/listHpcm.do");
	}

}
