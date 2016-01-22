package aramframework.com.dam.spe.req.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.dam.map.mat.service.MapMaterialService;
import aramframework.com.dam.map.mat.service.MapMaterialVO;
import aramframework.com.dam.map.tea.service.MapTeamService;
import aramframework.com.dam.map.tea.service.MapTeamVO;
import aramframework.com.dam.spe.req.service.RequestOfferService;
import aramframework.com.dam.spe.req.service.RequestOfferVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 지식정보제공/지식정보요청를 처리하는 Controller Class 구현
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
public class RequestOfferController {

	@Autowired
	private RequestOfferService requestOfferService;

	@Autowired
	private MapTeamService mapTeamService;

	@Autowired
	private MapMaterialService mapMaterialService;

	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 지식정보제공/지식정보요청 목록을 조회한다.
	 * 
	 * @param requestOfferVO
	 */
	@IncludedInfo(name = "지식정보요청/제공", order = 8060, gid = 80)
	@RequestMapping(value = "/dam/spe/req/listRequestOffer.do")
	@Secured("ROLE_USER")
	public String listRequestOffer(
			@ModelAttribute RequestOfferVO requestOfferVO, 
			ModelMap model) {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		requestOfferVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", requestOfferService.selectRequestOfferList(requestOfferVO));

		int totCnt = (Integer) requestOfferService.selectRequestOfferListCnt(requestOfferVO);
		requestOfferVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_UNIQ_ID", (String) loginVO.getUniqId());

		// 지식전문가 일때
		if (requestOfferService.selectRequestOfferSpeCheck(loginVO.getUniqId())) {
			model.addAttribute("IS_SPE", "Y");
		} else {
			model.addAttribute("IS_SPE", "N");
		}

		return WebUtil.adjustViewName("/dam/spe/req/RequestOfferList");
	}

	/**
	 * 지식정보제공/지식정보요청 목록을 상세조회 한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/detailRequestOffer.do")
	public String detailRequestOffer(
			@ModelAttribute RequestOfferVO requestOfferVO, 
			ModelMap model) {

		// 상세정보 불러오기
		requestOfferService.selectRequestOffer(requestOfferVO);

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_UNIQ_ID", (String) loginVO.getUniqId());

		// 지식전문가 일때
		if (requestOfferService.selectRequestOfferSpeCheck(loginVO.getUniqId())) {
			model.addAttribute("IS_SPE", "Y");
		} else {
			model.addAttribute("IS_SPE", "N");
		}

		return WebUtil.adjustViewName("/dam/spe/req/RequestOfferDetail");
	}

	/**
	 * 지식정보제공/지식정보요청를 등록 화면으로 이동한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/registRequestOffer.do")
	public String registRequestOffer(
			@RequestParam(value="cmd", required=false) String cmd,
			@ModelAttribute RequestOfferVO requestOfferVO, 
			ModelMap model) {

		// 조직유형 불러오기
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		List<EgovMap> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		// 지식유형코드불러오기
		MapMaterialVO mapMaterialVO = new MapMaterialVO();
		mapMaterialVO.setRecordPerPage(999999);
		mapMaterialVO.setFirstIndex(0);
		mapMaterialVO.setSearchCondition("ORGNZT_ID");

		EgovMap vo = new EgovMap();
		if (requestOfferVO.getOrgnztId() == null 
				|| requestOfferVO.getOrgnztId().equals("")) {
			vo = mapTeamList.get(0);
			mapMaterialVO.setSearchKeyword(vo.get("orgnztId").toString());
		} else {
			mapMaterialVO.setSearchKeyword(requestOfferVO.getOrgnztId());
		}

		model.addAttribute("mapMaterialList", mapMaterialService.selectMapMaterialList(mapMaterialVO));
		model.addAttribute("cmd", cmd);

		return WebUtil.adjustViewName("/dam/spe/req/RequestOfferRegist");
	}

	/**
	 * 지식정보제공/지식정보요청를 등록 처리 한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/insertRequestOffer.do")
	public String insertRequestOffer(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute RequestOfferVO requestOfferVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(requestOfferVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/spe/req/RequestOfferRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		requestOfferVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		requestOfferVO.setFrstRegisterId(loginVO.getUniqId());

		// 지식전문가 일때
		String sCmd = multiRequest.getParameter("cmd") == null ? "" : multiRequest.getParameter("cmd");
		if (sCmd.equals("reply") ) {
			if( requestOfferService.selectRequestOfferSpeCheck(loginVO.getUniqId())) {
				requestOfferVO.setSpeId(loginVO.getUniqId());
				requestOfferService.replyRequestOffer(requestOfferVO);
			} else {
				return WebUtil.adjustViewName("/dam/spe/req/RequestOfferRegist");
			}
		} else {
			requestOfferVO.setEmplyrId(loginVO.getUniqId());
			requestOfferService.insertRequestOffer(requestOfferVO);
		}
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/dam/spe/req/listRequestOffer.do");
	}

	/**
	 * 지식정보제공/지식정보요청를 수정화면으로 이동한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/editRequestOffer.do")
	public String editRequestOffer(
			@ModelAttribute RequestOfferVO requestOfferVO, 
			ModelMap model) {

		// 수정정보 불러오기
		if (requestOfferVO.getOrgnztId() != null 
				&& !requestOfferVO.getOrgnztId().equals("")) {
			requestOfferService.selectRequestOffer(requestOfferVO);
		}
		
		// 조직유형 불러오기
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		List<EgovMap> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		// 지식유형코드불러오기
		MapMaterialVO mapMaterialVO = new MapMaterialVO();
		mapMaterialVO.setRecordPerPage(999999);
		mapMaterialVO.setFirstIndex(0);
		mapMaterialVO.setSearchCondition("ORGNZT_ID");

		EgovMap vo = new EgovMap();
		if (requestOfferVO.getOrgnztId() == null 
				|| requestOfferVO.getOrgnztId().equals("")) {
			vo = mapTeamList.get(0);
			mapMaterialVO.setSearchKeyword(vo.get("orgnztId").toString());
		} else {
			mapMaterialVO.setSearchKeyword(requestOfferVO.getOrgnztId());
		}
		model.addAttribute("mapMaterialList", mapMaterialService.selectMapMaterialList(mapMaterialVO));

		return WebUtil.adjustViewName("/dam/spe/req/RequestOfferEdit");
	}

	/**
	 * 지식정보제공/지식정보요청를 수정한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/updateRequestOffer.do")
	public String updateRequestOffer(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute RequestOfferVO requestOfferVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(requestOfferVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/dam/spe/req/RequestOfferEdit");
		}
		
		// 첨부파일 관련 ID 생성 start....
		String atchFileId = requestOfferVO.getAtchFileId();
		requestOfferVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSCH_", atchFileId));
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		requestOfferVO.setLastUpdusrId(loginVO.getUniqId());

		requestOfferService.updateRequestOffer(requestOfferVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/dam/spe/req/listRequestOffer.do");
	}

	/**
	 * 지식정보제공/지식정보요청 을 삭제한다.
	 * 
	 * @param requestOfferVO
	 */
	@RequestMapping(value = "/dam/spe/req/deleteRequestOffer.do")
	public String deleteRequestOffer(
			@ModelAttribute RequestOfferVO requestOfferVO, 
			ModelMap model) {

		// 하위답변 검색 건수를 체크
		if (requestOfferService.selectRequestOfferDelCnt(requestOfferVO.getKnoId()) > 0) {
			// 에러 메세지 출력
			String ReusltScript = "";

			ReusltScript += "<script type='text/javaScript' language='javascript'>";
			ReusltScript += "alert(' 하위 답변이 등록되어 있어 삭제할수 없습니다!  ');";
			ReusltScript += "</script>";

			model.addAttribute("reusltScript", ReusltScript);

			return WebUtil.adjustViewName("/dam/spe/req/RequestOfferDetail");
		} 

		requestOfferService.deleteRequestOffer(requestOfferVO);
	
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/dam/spe/req/listRequestOffer.do");
	}

}
