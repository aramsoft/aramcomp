package aramframework.com.dam.map.tea.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.map.tea.domain.MapTeamVO;
import aramframework.com.dam.map.tea.service.MapTeamService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 지식맵(조직별)에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 지식맵(조직별)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 지식맵(조직별)의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class MapTeamController {

	@Autowired
	private MapTeamService mapTeamService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 지식맵(조직별) 정보를 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	@IncludedInfo(name = "지식맵관리(조직)", order = 8000, gid = 80)
	@RequestMapping(value = "/dam/map/tea/listMapTeam.do")
	@Secured("ROLE_USER")
	public String listMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		mapTeamVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", mapTeamService.selectMapTeamList(mapTeamVO));

		int totCnt = mapTeamService.selectMapTeamListCnt(mapTeamVO);
		mapTeamVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/dam/map/tea/MapTeamList");
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/detailMapTeam.do")
	public String detailMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO) {

		mapTeamService.selectMapTeamDetail(mapTeamVO);

		return WebUtil.adjustViewName("/dam/map/tea/MapTeamDetail");
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록화면으로 이동한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/registMapTeam.do")
	public String registMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO) {
		
		return WebUtil.adjustViewName("/dam/map/tea/MapTeamRegist");
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/insertMapTeam.do")
	public String insertMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO, 
			BindingResult bindingResult,
			ModelMap model) {
		
		beanValidator.validate(mapTeamVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/map/tea/MapTeamRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mapTeamVO.setFrstRegisterId(loginVO.getUniqId());

		mapTeamService.insertMapTeam(mapTeamVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/dam/map/tea/listMapTeam.do");
	}

	/**
	 * 기 등록 된 지식맵(조직별)링 정보를 수정화면으로 이동한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/editMapTeam.do")
	public String editMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO) {

		mapTeamService.selectMapTeamDetail(mapTeamVO);
		
		return WebUtil.adjustViewName("/dam/map/tea/MapTeamEdit");
	}

	/**
	 * 기 등록 된 지식맵(조직별)링 정보를 수정 한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/updateMapTeam.do")
	public String updateMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(mapTeamVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/map/tea/MapTeamEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mapTeamVO.setLastUpdusrId(loginVO.getUniqId());

		mapTeamService.updateMapTeam(mapTeamVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/map/tea/listMapTeam.do");
	}

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * 
	 * @param mapTeamVO
	 */
	@RequestMapping(value = "/dam/map/tea/deleteMapTeam.do")
	public String deleteMapTeam(
			@ModelAttribute MapTeamVO mapTeamVO, 
			ModelMap model) {

		mapTeamService.deleteMapTeam(mapTeamVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/dam/map/tea/listMapTeam.do");
	}

}