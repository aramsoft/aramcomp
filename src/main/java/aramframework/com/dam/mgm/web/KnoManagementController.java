package aramframework.com.dam.mgm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.mgm.domain.KnoManagementVO;
import aramframework.com.dam.mgm.service.KnoManagementService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 지식정보에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class KnoManagementController {

	@Autowired
	private KnoManagementService knoManagementService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	@IncludedInfo(name = "지식정보관리", order = 8050, gid = 80)
	@RequestMapping(value = "/dam/mgm/listKnoManagement.do")
	@Secured("ROLE_USER")
	public String listKnoManagement(
			@ModelAttribute KnoManagementVO knoManagementVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		knoManagementVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", knoManagementService.selectKnoManagementList(knoManagementVO));

		int totCnt = knoManagementService.selectKnoManagementListCnt(knoManagementVO);
		knoManagementVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/dam/mgm/KnoManagementList");
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoManagementVO
	 */
	@RequestMapping(value = "/dam/mgm/detailKnoManagement.do")
	public String detailKnoManagement(
			@ModelAttribute KnoManagementVO knoManagementVO, 
			ModelMap model) {

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("authority", "ROLE_ADMIN");
		}
		knoManagementService.selectKnoManagement(knoManagementVO);

		return WebUtil.adjustViewName("/dam/mgm/KnoManagementDetail");
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정화면으로 이동한다.
	 * 
	 * @param knoManagementVO
	 */
	@RequestMapping(value = "/dam/mgm/editKnoManagement.do")
	public String editKnoManagement(
			@ModelAttribute KnoManagementVO knoManagementVO) {

		knoManagementService.selectKnoManagement(knoManagementVO);

		return WebUtil.adjustViewName("/dam/mgm/KnoManagementEdit");
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * 
	 * @param knoManagementVO
	 */
	@RequestMapping(value = "/dam/mgm/updateKnoManagement.do")
	public String updateKnoManagement(
			@ModelAttribute KnoManagementVO knoManagementVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(knoManagementVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/mgm/KnoManagementEdit");
		}

		knoManagementService.updateKnoManagement(knoManagementVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/mgm/listKnoManagement.do");
	}
	
}