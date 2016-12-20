package aramframework.com.cop.scp.web;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.scp.domain.ScrapVO;
import aramframework.com.cop.scp.service.BBSScrapService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 스크랩관리 서비스 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class BBSScrapController {

	@Autowired 
	private BBSScrapService bbsScrapService;

	@Autowired 
	private BBSBoardService boardService;

	@Autowired 
	private BBSMasterService bbsMasterService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 스크랩관리 목록 조회를 제공한다.
	 * 
	 * @param scrapVO
	 */
	@IncludedInfo(name = "스크랩 목록", order = 4030, gid = 40)
	@RequestMapping("/cop/scp/listScrap.do")
	@Secured("ROLE_USER")
	public String listScrap(
			@ModelAttribute ScrapVO scrapVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setUniqId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		scrapVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsScrapService.selectScrapList(scrapVO));
		int totCnt = bbsScrapService.selectScrapListCnt(scrapVO);

		scrapVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/scp/ScrapList");
	}

	/**
	 * 스크랩에 대한 상세정보를 조회한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/detailScrap.do")
	@Secured("ROLE_USER")
	public String detailScrap(
			ScrapVO scrapVO, 
			ModelMap model) {

		scrapVO = bbsScrapService.selectScrap(scrapVO);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("uniqId", loginVO.getUniqId());

		// -------------------------------------
		// 게시판 내용 취득
		// -------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));
		model.addAttribute(scrapVO);
		
		return WebUtil.adjustViewName("/cop/scp/ScrapDetail");
	}

	/**
	 * 스크랩된 원 게시판 내용을 조회한다.
	 * 
	 * @param scrapVO
	 */
	private BoardVO getBoardInfo(ScrapVO scrapVO) {

		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(scrapVO.getBbsId());
		boardVO.setNttId(scrapVO.getNttId());
		boardVO.setBoardMasterVO(bbsMasterService.selectBBSMasterInf(boardVO.getBbsId()));
		
		return boardService.selectBoardArticle(boardVO);
	}

	/**
	 * 스크랩 등록을 위한 등록 페이지로 이동한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/registScrap.do")
	@Secured("ROLE_USER")
	public String registScrap(
			@ModelAttribute ScrapVO scrapVO, 
			ModelMap model) {

		// -------------------------------------
		// 게시판 내용 취득
		// -------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));

		return WebUtil.adjustViewName("/cop/scp/ScrapRegist");
	}

	/**
	 * 스크랩을 등록한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/insertScrap.do")
	@Secured("ROLE_USER")
	public String insertScrap(
			@ModelAttribute ScrapVO scrapVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(scrapVO, bindingResult);
		if (bindingResult.hasErrors()) {

			// -------------------------------------
			// 게시판 내용 취득
			// -------------------------------------
			model.addAttribute(getBoardInfo(scrapVO));

			return WebUtil.adjustViewName("/cop/scp/ScrapRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setFrstRegisterId(loginVO.getUniqId());

		bbsScrapService.insertScrap(scrapVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/scp/listScrap.do");
	}

	/**
	 * 스크랩 수정 페이지로 이동한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/editScrap.do")
	@Secured("ROLE_USER")
	public String editScrap(
			ScrapVO scrapVO, 
			ModelMap model) {

		scrapVO = bbsScrapService.selectScrap(scrapVO);

		// -------------------------------------
		// 게시판 내용 취득
		// -------------------------------------
		model.addAttribute(getBoardInfo(scrapVO));
		model.addAttribute(scrapVO);

		return WebUtil.adjustViewName("/cop/scp/ScrapEdit");
	}

	/**
	 * 스크랩을 수정한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/updateScrap.do")
	@Secured("ROLE_USER")
	public String updateScrap(
			@ModelAttribute ScrapVO scrapVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(scrapVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// -------------------------------------
			// 게시판 내용 취득
			// -------------------------------------
			model.addAttribute(getBoardInfo(scrapVO));

			return WebUtil.adjustViewName("/cop/scp/ScrapEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setLastUpdusrId(loginVO.getUniqId());

		bbsScrapService.updateScrap(scrapVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/scp/listScrap.do");
	}

	/**
	 * 스크랩을 삭제한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/deleteScrap.do")
	@Secured("ROLE_USER")
	public String deleteScrap(
			@ModelAttribute ScrapVO scrapVO, 
			ModelMap model) {
		
		bbsScrapService.deleteScrap(scrapVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/scp/listScrap.do");
	}

	/**
	 * 마이페이지용 스크랩관리 목록 조회를 제공한다.
	 * 
	 * @param scrapVO
	 */
	@RequestMapping("/cop/scp/listScrapMainPage.do")
	@Secured("ROLE_USER")
	public String listScrapMainPage(
			@ModelAttribute ScrapVO scrapVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		scrapVO.setUniqId(loginVO.getUniqId());

		scrapVO.getSearchVO().setSizeAndOffset(5, 0);

		model.addAttribute("resultList", bbsScrapService.selectScrapList(scrapVO));

		return WebUtil.adjustViewName("/cop/scp/ScrapMainPage");
	}
	
}
