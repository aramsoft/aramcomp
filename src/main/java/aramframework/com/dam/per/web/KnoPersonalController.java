package aramframework.com.dam.per.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.map.mat.domain.MapMaterialVO;
import aramframework.com.dam.map.mat.service.MapMaterialService;
import aramframework.com.dam.map.tea.domain.MapTeamVO;
import aramframework.com.dam.map.tea.service.MapTeamService;
import aramframework.com.dam.per.domain.KnoPersonalVO;
import aramframework.com.dam.per.service.KnoPersonalService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 개인지식정보에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 개인지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - 개인지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class KnoPersonalController {

	@Autowired
	private KnoPersonalService knoPersonalService;

	@Autowired
	private MapTeamService mapTeamService;

	@Autowired
	private MapMaterialService mapMaterialService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 개인지식 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	@IncludedInfo(name = "개인지식관리", order = 8030, gid = 80)
	@RequestMapping(value = "/dam/per/listKnoPersonal.do")
	@Secured("ROLE_USER")
	public String listKnoPersonal(
			@ModelAttribute KnoPersonalVO knoPersonalVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoPersonalVO.setFrstRegisterId(loginVO.getUniqId());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		knoPersonalVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", knoPersonalService.selectKnoPersonalList(knoPersonalVO));
		int totCnt = knoPersonalService.selectKnoPersonalListCnt(knoPersonalVO);

		knoPersonalVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/dam/per/KnoPersonalList");
	}

	/**
	 * 개인지식정보 상세 정보를 조회 한다.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/detailKnoPersonal.do")
	public String detailKnoPersonal(
			KnoPersonalVO knoPersonalVO,
			ModelMap model) {

		model.addAttribute(knoPersonalService.selectKnoPersonal(knoPersonalVO));

		return WebUtil.adjustViewName("/dam/per/KnoPersonalDetail");
	}

	/**
	 * 개인지식 정보를 등록화면으로 이동한다.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/registKnoPersonal.do")
	public String registKnoPersonal(
			@ModelAttribute KnoPersonalVO knoPersonalVO, 
			ModelMap model) {

		populateMapTeam(knoPersonalVO, model);

		return WebUtil.adjustViewName("/dam/per/KnoPersonalRegist");
	}

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/insertKnoPersonal.do")
	public String insertKnoPersonal(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute KnoPersonalVO knoPersonalVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception{

		beanValidator.validate(knoPersonalVO, bindingResult);
		if (bindingResult.hasErrors()) {
			
			populateMapTeam(knoPersonalVO, model);

			return WebUtil.adjustViewName("/dam/per/KnoPersonalRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		knoPersonalVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoPersonalVO.setFrstRegisterId(loginVO.getUniqId());

		knoPersonalService.insertKnoPersonal(knoPersonalVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/dam/per/listKnoPersonal.do");
	}

	/**
	 * mapTeamList, mapMaterialList를 가져온다.
	 * 
	 * @param knoSpecialistVO
	 */
	private void populateMapTeam(KnoPersonalVO knoPersonalVO, ModelMap model) {
		
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.getSearchVO().setSizeAndOffset(999999, 0);
		List<EgovMap> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		MapMaterialVO mapMaterialVO = new MapMaterialVO();
		mapMaterialVO.getSearchVO().setSizeAndOffset(999999, 0);
		mapMaterialVO.getSearchVO().setSearchCondition("ORGNZT_ID");
		if (knoPersonalVO.getOrgnztId() == null || knoPersonalVO.getOrgnztId().equals("")) {
			EgovMap vo = mapTeamList.get(0);
			mapMaterialVO.getSearchVO().setSearchKeyword(vo.get("orgnztId").toString());
		} else {
			mapMaterialVO.getSearchVO().setSearchKeyword(knoPersonalVO.getOrgnztId());
		}
		model.addAttribute("mapMaterialList", mapMaterialService.selectMapMaterialList(mapMaterialVO));
	}
	
	/**
	 * 기 등록 된 개인지식 정보를 수정폼.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/editKnoPersonal.do")
	public String editKnoPersonal(
			KnoPersonalVO knoPersonalVO,
			ModelMap model) {

		model.addAttribute(knoPersonalService.selectKnoPersonal(knoPersonalVO));

		return WebUtil.adjustViewName("/dam/per/KnoPersonalEdit");
	}

	/**
	 * 기 등록 된 개인지식 정보를 수정 한다.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/updateKnoPersonal.do")
	public String updateKnoPersonal(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute KnoPersonalVO knoPersonalVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(knoPersonalVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/per/KnoPersonalEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = knoPersonalVO.getAtchFileId();
		knoPersonalVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSCH_", atchFileId));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		knoPersonalVO.setLastUpdusrId(loginVO.getUniqId());

		knoPersonalService.updateKnoPersonal(knoPersonalVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/per/listKnoPersonal.do");
	}

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * 
	 * @param knoPersonalVO
	 */
	@RequestMapping(value = "/dam/per/deleteKnoPersonal.do")
	public String deleteKnoPersonal(
			@ModelAttribute KnoPersonalVO knoPersonalVO, 
			ModelMap model) {

		knoPersonalService.deleteKnoPersonal(knoPersonalVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/dam/per/listKnoPersonal.do");
	}

}