package aramframework.com.dam.map.mat.web;

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
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.map.mat.domain.MapMaterialVO;
import aramframework.com.dam.map.mat.service.MapMaterialService;
import aramframework.com.dam.map.tea.domain.MapTeamVO;
import aramframework.com.dam.map.tea.service.MapTeamService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 지식맵(지식유형)에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 지식맵(지식유형)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 지식맵(지식유형)의 조회기능은 목록조회, 상세조회로  구분된다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class MapMaterialController {

	@Autowired
	private MapTeamService mapTeamService;

	@Autowired
	private MapMaterialService mapMaterialService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다.
	 * 
	 * @param mapMaterialVO
	 */
	@IncludedInfo(name = "지식맵관리(유형)", order = 8010, gid = 80)
	@RequestMapping(value = "/dam/map/mat/listMapMaterial.do")
	@Secured("ROLE_USER")
	public String listMapMaterial(
			@ModelAttribute MapMaterialVO mapMaterialVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		mapMaterialVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList",  mapMaterialService.selectMapMaterialList(mapMaterialVO));
		int totCnt = mapMaterialService.selectMapMaterialListCnt(mapMaterialVO);

		mapMaterialVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/dam/map/mat/MapMaterialList");
	}

	/**
	 * 지식맵(지식유형)상세 정보를 조회 한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/detailMapMaterial.do")
	public String detailMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO,
			ModelMap model) {

		model.addAttribute(mapMaterialService.selectMapMaterial(mapMaterialVO));

		return WebUtil.adjustViewName("/dam/map/mat/MapMaterialDetail");
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록화면으로 이동한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/registMapMaterial.do")
	public String registMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO, 
			ModelMap model) {

		populateMapTeam(model);
		
		return WebUtil.adjustViewName("/dam/map/mat/MapMaterialRegist");
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/insertMapMaterial.do")
	public String insertMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mapMaterialVO, bindingResult);
		if (bindingResult.hasErrors()) {

			populateMapTeam(model);
			
			return WebUtil.adjustViewName("/dam/map/mat/MapMaterialRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mapMaterialVO.setFrstRegisterId(loginVO.getUniqId());

		mapMaterialService.insertMapMaterial(mapMaterialVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/dam/map/mat/listMapMaterial.do");
	}

	/**
	 * mapTeamList, mapMaterialList를 가져온다.
	 * 
	 * @param knoSpecialistVO
	 */
	private void populateMapTeam(ModelMap model) {
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		model.addAttribute("mapTeamList", mapTeamService.selectMapTeamList(mapTeamVO));
	}
	
	/**
	 * 기 등록 된 지식맵(지식유형)링 정보를 수정화면으로 이동한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/editMapMaterial.do")
	public String editMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO,
			ModelMap model) {

		model.addAttribute(mapMaterialService.selectMapMaterial(mapMaterialVO));

		return WebUtil.adjustViewName("/dam/map/mat/MapMaterialEdit");
	}

	/**
	 * 기 등록 된 지식맵(지식유형)링 정보를 수정 한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/updateMapMaterial.do")
	public String updateMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(mapMaterialVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/map/mat/MapMaterialEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		mapMaterialVO.setLastUpdusrId(loginVO.getUniqId());

		mapMaterialService.updateMapMaterial(mapMaterialVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/map/mat/listMapMaterial.do");
	}

	/**
	 * 기 등록된 지식맵(지식유형) 정보를 삭제한다.
	 * 
	 * @param mapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/deleteMapMaterial.do")
	public String deleteMapMaterial(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute MapMaterialVO mapMaterialVO, 
			ModelMap model) {

		mapMaterialService.deleteMapMaterial(mapMaterialVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/dam/map/mat/listMapMaterial.do");
	}

}