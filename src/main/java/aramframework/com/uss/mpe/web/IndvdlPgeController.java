package aramframework.com.uss.mpe.web;

import java.util.List;

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
import aramframework.com.uss.mpe.service.IndvdlPgeService;
import aramframework.com.uss.mpe.service.IndvdlPgeCntntsVO;
import aramframework.com.uss.mpe.service.IndvdlPgeConfVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 마이페이지에 대한 Controller 클래스를 정의한다.
 * 
 * 상세내용 - 마이페이지 콘텐츠의 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다. 
 *         - 마이페이지 콘텐츠의 조회기능은 목록조회, 상세조회로 구분된다. 
 *         - 등록된 콘텐츠를 마이페이지에 추가, 삭제, 조회 기능을 제공한다.
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
public class IndvdlPgeController {

	@Autowired
	private IndvdlPgeService indvdlPgeService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 사용자가 마이페이지 설정정보를 조회한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	@IncludedInfo(name = "마이페이지관리", order = 5031, gid = 50)
	@RequestMapping(value = "/uss/mpe/detailIndvdlpge.do")
	@Secured("ROLE_USER")
	public String detailIndvdlpge(
			@ModelAttribute IndvdlPgeConfVO indvdlPgeConfVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (configurationProcessedYN(loginVO.getId()) == 0) {
			model.addAttribute("message", "마이페이지 설정 후 마이페이지를 확인 가능합니다.");
			return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeConfRegist");
		} 
			
		indvdlPgeConfVO.setUserId(loginVO.getId());

		// 마이페이지 상세목록설정
		List<EgovMap> resultList =  indvdlPgeService.selectIndvdlpgeResultDetail(indvdlPgeConfVO);
		int totDetailCnt = indvdlPgeService.selectIndvdlpgeResultDetailCnt(indvdlPgeConfVO);
		
		for (int i = 0; i < resultList.size(); i++) {
			String cntcUrl = resultList.get(i).get("cntcUrl").toString();
			int lastIndex = cntcUrl.lastIndexOf(".");
			
			if (!".do".equals(cntcUrl.substring(lastIndex, lastIndex+3))) {
				resultList.remove(i);
				totDetailCnt--;
			}
		}
		
		model.addAttribute("indvdlPgeDetailList", resultList);
		model.addAttribute("indvdlPgeDetailListCount", totDetailCnt);

		// 마이페이지 기본정보 설정
		indvdlPgeService.selectIndvdlpgeConfDetail(indvdlPgeConfVO);
		
		// 기준개수별 사이즈를 구한다.
		int sideCnt = indvdlPgeConfVO.getSortCnt();
		int typeByCnt = (totDetailCnt % sideCnt) == 0 ? totDetailCnt / sideCnt : totDetailCnt / sideCnt + 1;
		indvdlPgeConfVO.setSortLineCnt(typeByCnt);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeDetail");
	}

	/**
	 * 마이페이지를 신규 생성 혹은 설정정보를 수정하기 위해 설정 정보를 확인한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	@RequestMapping(value = "/uss/mpe/registIndvdlpgeConf.do")
	@Secured("ROLE_USER")
	public String registIndvdlpgeConf(
			@ModelAttribute IndvdlPgeConfVO indvdlPgeConfVO) {

		// ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeConfVO.setUserId(loginVO.getId());

		// 기존 생성한 정보가 있을 경우 화면에 보여준다.
		indvdlPgeService.selectIndvdlpgeConfDetail(indvdlPgeConfVO);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeConfRegist");
	}

	/**
	 * 마이페이지설정 정보 존재 유무를 확인하여 수정 혹은 추가를 결정한다.
	 * 
	 * @param indvdlPgeConfVO
	 */
	@RequestMapping(value = "/uss/mpe/updateIndvdlpgeConf.do")
	@Secured("ROLE_USER")
	public String updateIndvdlpgeConf(
			@ModelAttribute IndvdlPgeConfVO indvdlPgeConfVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(indvdlPgeConfVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeConfRegist");
		} 
		
		// ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeConfVO.setUserId(loginVO.getId());

		// 기존에 설정한 정보가 있는지 확인한다.
		int cnt = indvdlPgeService.selectIndvdlpgeConfCnt(indvdlPgeConfVO);
		// 기존 설정 정보 존재시 수정 메서드를 호출하고 비존재 시 추가 메서드를 호출한다.
		if (cnt < 1)
			indvdlPgeService.insertIndvdlpgeConf(indvdlPgeConfVO);
		else
			indvdlPgeService.updateIndvdlpgeConf(indvdlPgeConfVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/mpe/detailIndvdlpge.do");
	}

	/**
	 * 마이페이지 설정 정보 존재 유무를 체크한다.
	 * 
	 * @param user_id
	 */
	private int configurationProcessedYN(String user_id) {
		IndvdlPgeConfVO indvdlPgeConfVO = new IndvdlPgeConfVO();
		indvdlPgeConfVO.setUserId(user_id);
		return  indvdlPgeService.selectIndvdlpgeConfCnt(indvdlPgeConfVO);
	}

	/**
	 * 마이페이지에 컨텐츠를 추가 위해 등록된 마이페이지 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/listIndvdlpgeCntntsMine.do")
	@Secured("ROLE_USER")
	public String listIndvdlpgeCntntsMine(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		// ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeCntntsVO.setUserId(loginVO.getId());

		PaginationInfo paginationInfo = new PaginationInfo();
		indvdlPgeCntntsVO.fillPageInfo(paginationInfo);

		// 사용자가 마이페이지에 컨텐츠를 추가하기 위해 등록되어 있는 마이페이지 목록을 조회한다.
		model.addAttribute("resultList", indvdlPgeService.addIndvdlpgeCntntsList(indvdlPgeCntntsVO));

		// 목록의 페이징을 위해 등록되어 있는 마이페이지 개수를 조회한다.
		int totCnt = indvdlPgeService.addIndvdlpgeCntntsListCnt(indvdlPgeCntntsVO);
		indvdlPgeCntntsVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeList");
	}

	/**
	 * 마이페이지에 컨텐츠를 바로 추가한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/insertIndvdlpgeCntntsMine.do")
	@Secured("ROLE_USER")
	public String insertIndvdlpgeCntntsMine(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		// ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (this.configurationProcessedYN(loginVO.getId()) == 0) {
			model.addAttribute("message", "마이페이지 환경 설정부터 하셔야 합니다.");
			return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeConfRegist");
		} 
		
		indvdlPgeCntntsVO.setUserId(loginVO.getId());

		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.addIndvdlpgeCntnts(indvdlPgeCntntsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/mpe/listIndvdlpgeCntntsMine.do");
	}

	/**
	 * 마이페이지에서 컨텐츠를 바로 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/deleteIndvdlpgeCntntsMine.do")
	@Secured("ROLE_USER")
	public String deleteIndvdlpgeCntntsMine(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		// ID를 받아서 VO에 설정한다.
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		indvdlPgeCntntsVO.setUserId(loginVO.getId());

		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.delIndvdlpgeCntnts(indvdlPgeCntntsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/mpe/detailIndvdlpge.do?userId="+indvdlPgeCntntsVO.getUserId());
	}

	/**
	 * 마이페이지 컨텐츠를 관리하기 위해 등록된 마이페이지 컨텐츠 목록을 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@IncludedInfo(name = "컨텐츠관리", order = 5030, gid = 50)
	@RequestMapping(value = "/uss/mpe/listIndvdlpgeCntnts.do")
	@Secured("ROLE_USER")
	public String listIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		indvdlPgeCntntsVO.fillPageInfo(paginationInfo);

		// 컨텐츠 목록을 조회한다.
		model.addAttribute("resultList", indvdlPgeService.selectIndvdlpgeCntntsList(indvdlPgeCntntsVO));

		// 컨텐츠의 개수를 조회한다.
		int totCnt = indvdlPgeService.selectIndvdlpgeCntntsListCnt(indvdlPgeCntntsVO);
		indvdlPgeCntntsVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsList");
	}

	/**
	 * 등록된 마이페이지 컨텐츠의 상세정보를 조회한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/detailIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String detailIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {

		indvdlPgeService.selectIndvdlpgeCntnts(indvdlPgeCntntsVO);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsDetail");
	}

	/**
	 * 마이페이지 컨텐츠정보 등록 화면 이동
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/registIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String registIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsRegist");
	}

	/**
	 * 마이페이지 컨텐츠정보를 신규로 등록한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/insertIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String insertIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// validation 수행
		beanValidator.validate(indvdlPgeCntntsVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsRegist");
		} 
		
		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.insertIndvdlpgeCntnts(indvdlPgeCntntsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/mpe/listIndvdlpgeCntnts.do");
	}

	/**
	 * 기 등록된 마이페이지 컨텐츠정보를 수정화면으로 이동한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/editIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String editIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO) {

		indvdlPgeService.selectIndvdlpgeCntnts(indvdlPgeCntntsVO);

		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsEdit");
	}

	/**
	 * 기 등록된 마이페이지 컨텐츠정보를 수정한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/updateIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String updateIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(indvdlPgeCntntsVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeCntntsEdit");
		} 
		
		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.updateIndvdlpgeCntnts(indvdlPgeCntntsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/mpe/listIndvdlpgeCntnts.do");
	}

	/**
	 * 기 등록된 마이페이지 컨텐츠정보를 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/deleteIndvdlpgeCntnts.do")
	@Secured("ROLE_ADMIN")
	public String deleteIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.deleteIndvdlpgeCntnts(indvdlPgeCntntsVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/mpe/listIndvdlpgeCntnts.do");
	}

	/**
	 * 기 등록된 마이페이지 컨텐츠정보를 DB에서 완전 삭제한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/deleteIndvdlpgeCntntsDB.do")
	@Secured("ROLE_ADMIN")
	public String deleteIndvdlpgeCntntsDB(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {

		// 디비 작업 성공여부에 따라 메세지 설정 및 이동 페이지를 결정한다.
		indvdlPgeService.deleteIndvdlpgeCntntsDB(indvdlPgeCntntsVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/mpe/listIndvdlpgeCntnts.do");
	}

	/**
	 * 마이페이지 컨텐츠의 미리보기를 위한 jsp URL을 리턴한다.
	 * 
	 * @param indvdlPgeCntntsVO
	 */
	@RequestMapping(value = "/uss/mpe/previewIndvdlpgeCntnts.do")
	public String previewIndvdlpgeCntnts(
			@ModelAttribute IndvdlPgeCntntsVO indvdlPgeCntntsVO, 
			ModelMap model) {
		
		model.addAttribute("linkto", indvdlPgeCntntsVO.getCntcUrl());
		return WebUtil.adjustViewName("/uss/mpe/IndvdlpgeTmp");
	}

}