package aramframework.com.dam.spe.spe.web;

import java.util.List;

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
import aramframework.com.dam.spe.spe.domain.KnoSpecialistVO;
import aramframework.com.dam.spe.spe.service.KnoSpecialistService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 지식전문가에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class KnoSpecialistController {

	@Autowired
	public MapMaterialService mapMaterialService;

	@Autowired
	private MapTeamService mapTeamService;

	@Autowired
	private KnoSpecialistService knoSpecialistService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@IncludedInfo(name = "지식전문가관리", order = 8020, gid = 80)
	@RequestMapping(value = "/dam/spe/spe/listKnoSpecialist.do")
	@Secured("ROLE_USER")
	public String listKnoSpecialist(
			@ModelAttribute KnoSpecialistVO knoSpecialistVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		knoSpecialistVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", knoSpecialistService.selectKnoSpecialistList(knoSpecialistVO));
		int totCnt = knoSpecialistService.selectKnoSpecialistListCnt(knoSpecialistVO);

		knoSpecialistVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistList");
	}

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/detailKnoSpecialist.do")
	public String detailKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO,
			ModelMap model) {

		model.addAttribute(knoSpecialistService.selectKnoSpecialist(knoSpecialistVO));

		return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistDetail");
	}

	/**
	 * 지식전문가 정보를 신규로 등록화면으로 이동한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/registKnoSpecialist.do")
	public String registKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO, 
			ModelMap model) {

		populateMapTeam(knoSpecialistVO, model);
		
		return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistRegist");
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/insertKnoSpecialist.do")
	public String insertKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(knoSpecialistVO, bindingResult);
		if (bindingResult.hasErrors()) {
			
			populateMapTeam(knoSpecialistVO, model);

			return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoSpecialistVO.setFrstRegisterId(loginVO.getUniqId());

		knoSpecialistService.insertKnoSpecialist(knoSpecialistVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/dam/spe/spe/listKnoSpecialist.do");
	}

	/**
	 * mapTeamList, mapMaterialList를 가져온다.
	 * 
	 * @param knoSpecialistVO
	 */
	private void populateMapTeam(KnoSpecialistVO knoSpecialistVO, ModelMap model) {
		
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setSizeAndOffset(999999, 0);
		List<EgovMap> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		MapMaterialVO mapMaterialVO = new MapMaterialVO();
		mapMaterialVO.setSizeAndOffset(999999, 0);
		mapMaterialVO.setSearchCondition("ORGNZT_ID");

		if (knoSpecialistVO.getOrgnztId() == null || knoSpecialistVO.getOrgnztId().equals("")) {
			EgovMap vo = mapTeamList.get(0);
			mapMaterialVO.setSearchKeyword(vo.get("orgnztId").toString());
		} else {
			mapMaterialVO.setSearchKeyword(knoSpecialistVO.getOrgnztId());
		}

		model.addAttribute("mapMaterialList", mapMaterialService.selectMapMaterialList(mapMaterialVO));
	}
	
	/**
	 * 기 등록 된 지식전문가 정보를 수정화면으로 이동한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/editKnoSpecialist.do")
	public String editKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO,
			ModelMap model) {

		model.addAttribute(knoSpecialistService.selectKnoSpecialist(knoSpecialistVO));

		return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistEdit");
	}

	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/updateKnoSpecialist.do")
	public String updateKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(knoSpecialistVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/spe/spe/KnoSpecialistEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoSpecialistVO.setLastUpdusrId(loginVO.getUniqId());

		knoSpecialistService.updateKnoSpecialist(knoSpecialistVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/spe/spe/listKnoSpecialist.do");
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * 
	 * @param knoSpecialistVO
	 */
	@RequestMapping(value = "/dam/spe/spe/deleteKnoSpecialist.do")
	public String deleteKnoSpecialist(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute KnoSpecialistVO knoSpecialistVO, 
			ModelMap model) {

		knoSpecialistService.deleteKnoSpecialist(knoSpecialistVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/dam/spe/spe/listKnoSpecialist.do");
	}

}