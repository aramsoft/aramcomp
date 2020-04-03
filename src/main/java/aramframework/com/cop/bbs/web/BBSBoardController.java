package aramframework.com.cop.bbs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSCommentService;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSSatisfactionService;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.cop.scp.service.BBSScrapService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BBSBoardController {

	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	@Autowired
	private BBSBoardService boardService;

	@Autowired
	private BBSMasterService bbsMasterService;

	@Autowired
	private FileMngUtil fileMngUtil; 

	@Autowired 
	private UserInfService userInfService; // 커뮤니티 사용자 확인

	// ---------------------------------
	// 2009.06.29 : 2단계 기능 추가
	// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
	// ---------------------------------
	@Autowired(required = false)
	private BBSCommentService bbsCommentService;

	@Autowired(required = false)
	private BBSSatisfactionService bbsSatisfactionService;

	@Autowired(required = false)
	private BBSScrapService bbsScrapService;
	// //-------------------------------

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * XSS 방지 처리.
	 * 
	 * @param data
	 * @return
	 */
	protected String unscript(String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}

		String ret = data;

		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		return ret;
	}

	private String getEditAuthFlag(BoardVO boardVO) {

		String bbsTyCode = boardVO.getBoardMasterVO().getBbsTyCode();

		if( bbsTyCode.equals(BBSBoardService.BBS_TYPE_NOTICE) ) {
			return userInfService.checkCommunityManager();
		} else {
			return userInfService.checkCommunityUser("Y");
		}
	}

	private BoardMasterVO getBoardMasterVO(String bbsId) {

		BoardMasterVO boardMasterVO = (BoardMasterVO) cacheDictionary.get(CacheKey.BBS_PREFIX + bbsId);
        if( boardMasterVO == null ) {
        	boardMasterVO = bbsMasterService.selectBBSMasterInf(bbsId);

    		if( boardMasterVO == null  ) {
    			throw new RuntimeException("bbs is not created yet !!!");
    		}
        	
			if (boardMasterVO.getTmplatCours() == null || boardMasterVO.getTmplatCours().equals("")) {
				boardMasterVO.setTmplatCours("/css/cop/tpl/egovBaseTemplate.css");
			}
   		
			cacheDictionary.put(CacheKey.BBS_PREFIX + bbsId, boardMasterVO);
        }
		return boardMasterVO;
	}
	
	/**
	 * 게시물에 대한 목록을 조회한다.
	 * 
	 * @param bbsPathId
	 * @param boardVO
	 */
	@RequestMapping(value="/board/{bbsPathId}/list")
	public String listlBoardArticle(
			@ModelAttribute BoardVO boardVO, 
			@PathVariable String bbsPathId, 
			ModelMap model) {

		boardVO.setBbsId(WebUtil.getOriginalId(bbsPathId, "BBSMSTR_"));
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId is not found !!!");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		// -------------------------------
		// 방명록이면 방명록 URL로 redirect
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_VISIT)) {
			return WebUtil.redirectJsp(model, boardVO, "/cop/bbs/selectGuestList.do?bbsId="+boardVO.getBbsId());
		}

		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			model.addAttribute("editAuthFlag", getEditAuthFlag(boardVO));
		}

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("role", "ROLE_ADMIN");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", boardService.selectBoardArticleList(boardVO));
		int totCnt = boardService.selectBoardArticleListCnt(boardVO);

		boardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "cop/bbs/NoticeList";
	}
	
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param nttId
	 * @param boardVO
	 */
	@RequestMapping(value="/board/{bbsPathId}/id/{nttId}")
	public String detailBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO, 
			@PathVariable String bbsPathId, 
			@PathVariable int nttId,			
			ModelMap model) {

		boardVO.setBbsId(WebUtil.getOriginalId(bbsPathId, "BBSMSTR_"));
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		boardVO.setNttId(nttId);
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
			
			model.addAttribute("userId", "ANONYMOUS");
		} else {
			model.addAttribute("anonymous", "false");
			model.addAttribute("editAuthFlag", getEditAuthFlag(boardVO));

			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO != null ) {
				model.addAttribute("userId", loginVO.getUserId());
			} 
		}

		if( UserDetailsHelper.getAuthorities().contains("ROLE_ADMIN") ) {
			model.addAttribute("role", "ROLE_ADMIN");
		}
		
		// 조회수 증가 여부 지정
		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		boardService.updateRdcnt(boardVO);
		boardVO = boardService.selectBoardArticle(boardVO);

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
		
		model.addAttribute("mainTitle", boardVO.getNttSj());

		setDirectUrlToModel(boardVO, model);
		model.addAttribute(boardVO);
		
		return "cop/bbs/NoticeDetail";
	}
	
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param nttId
	 * @param boardVO
	 */
	@RequestMapping(value="/board/{bbsPathId}/view/{nttId}", method=RequestMethod.GET)
	public String viewlBoardArticle(
			@ModelAttribute BoardVO boardVO, 
			@PathVariable String bbsPathId, 
			@PathVariable int nttId,			
			ModelMap model) {

		boardVO.setBbsId(WebUtil.getOriginalId(bbsPathId, "BBSMSTR_"));
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			model.addAttribute("userId", loginVO.getUserId());
		} 
		
		boardVO.setNttId(nttId);
		
		boardVO = boardService.selectBoardArticle(boardVO);

		setDirectUrlToModel(boardVO, model);

		model.addAttribute(boardVO);

		return "cop/bbs/NoticeView";
	}

	private void setDirectUrlToModel(BoardVO boardVO, ModelMap model) {
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

		String trgetId = (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
		String contextUrl = (String) requestAttributes.getAttribute("contextUrl", RequestAttributes.SCOPE_REQUEST);
		String directUrl = "";
		if( trgetId != null 
				&& trgetId != "" 
				&& trgetId.indexOf("CMMNTY_") != -1) {
			String cmmntyId = WebUtil.getPathId(trgetId);
			directUrl = contextUrl + "/apps/id/"+cmmntyId+"/board/"+boardVO.getPathId()+"/id/"+boardVO.getNttId();
		} else {
			directUrl = contextUrl + "/board/"+boardVO.getPathId()+"/id/"+ boardVO.getNttId();
		}
		model.addAttribute("directUrl", directUrl);
	}
	
	/**
	 * 게시물 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/registBoardArticle.do")
	public String registBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) {

		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		return "cop/bbs/NoticeRegist";
	}

	/**
	 * 게시물을 등록한다.
	 * 
	 * @param multiRequest
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/insertBoardArticle.do")
	public String insertBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO,
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model) 
	throws Exception {

		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/bbs/NoticeRegist";
		}

		boardVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "BBS"));

		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			boardVO.setFrstRegisterId("ANONYMOUS");
			boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		} else {
			boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			boardVO.setFrstRegisterId(loginVO.getUserId());
			boardVO.setNtcrId(loginVO.getUserId()); // 게시물 통계 집계를 위해 등록자 ID 저장
			boardVO.setNtcrNm(loginVO.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장
		}

		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardService.insertBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, boardVO, "/board/"+boardVO.getPathId()+ "/list");
	}

	/**
	 * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/replyBoardArticle.do")
	public String replyBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) {

		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		boardService.selectBoardArticle(boardVO);

		return "cop/bbs/NoticeReply";
	}

	/**
	 * 게시물에 대한 답변을 등록한다.
	 * 
	 * @param multiRequest
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/insertReplyBoardArticle.do")
	public String addReplyBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO,
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model)
	throws Exception {

		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/bbs/NoticeReply";
		}

		boardVO.setAtchFileId(fileMngUtil.insertMultiFile(multiRequest, "BBS"));

		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			boardVO.setFrstRegisterId("ANONYMOUS");
			boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		} else {
			boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			boardVO.setFrstRegisterId(loginVO.getUserId());
			boardVO.setNtcrId(loginVO.getUserId()); // 게시물 통계 집계를 위해 등록자 ID 저장
			boardVO.setNtcrNm(loginVO.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장
		}

		boardVO.setAnswerAt("Y");
		boardVO.setParntsNttId(boardVO.getNttId());
		boardVO.setThreadDepth(boardVO.getThreadDepth() + 1);
		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardService.insertBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, boardVO,"/board/"+boardVO.getPathId()+ "/list");
	}

	/**
	 * 게시물 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/editBoardArticle.do")
	public String editBoardArticle(
			@ModelAttribute("searchVO") SearchVO searchVO,
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) 
	throws Exception {

		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
			
			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = boardService.getPasswordInf(boardVO);
			String enpassword = FileScrty.encryptPassword(boardVO.getPassword());
			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("message", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "cop/bbs/NoticeDetail";
			}

		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		model.addAttribute(boardService.selectBoardArticle(boardVO));
		
		return "cop/bbs/NoticeEdit";
	}

	/**
	 * 게시물에 대한 내용을 수정한다.
	 * 
	 * @param multiRequest
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/updateBoardArticle.do")
	public String updateBoardArticle(
			@ModelAttribute BoardVO boardVO,
			BindingResult bindingResult, 
			MultipartHttpServletRequest multiRequest, 
			ModelMap model)
	throws Exception {

		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
		}

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "cop/bbs/NoticeEdit";
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = boardVO.getAtchFileId();
		boardVO.setAtchFileId(fileMngUtil.updateMultiFile(multiRequest, "BBS", atchFileId));

		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			boardVO.setLastUpdusrId("ANONYMOUS");
			boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		} else {
			boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
			
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			boardVO.setLastUpdusrId(loginVO.getUserId());
		}

		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardService.updateBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model,  boardVO, "/board/"+boardVO.getPathId()+ "/list");
	}

	/**
	 * 게시물에 대한 내용을 삭제한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping(value="/cop/bbs/deleteBoardArticle.do")
	public String deleteBoardArticle(
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) 
	throws Exception {

		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		// -------------------------------
		// 익명게시판 확인
		// -------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = boardService.getPasswordInf(boardVO);
			String enpassword = FileScrty.encryptPassword(boardVO.getPassword());
			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("message", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "cop/bbs/NoticeDetail";
			}

			boardVO.setLastUpdusrId("ANONYMOUS");
		} else {
			model.addAttribute("anonymous", "false");
			String editAuthFlag = getEditAuthFlag(boardVO);
			if( editAuthFlag.equals("N")) {
				throw new AccessDeniedException("access denined!!!");
			}
			model.addAttribute("editAuthFlag", editAuthFlag);
			
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			boardVO.setLastUpdusrId(loginVO.getUserId());
		}

		boardService.deleteBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, boardVO, "/board/"+boardVO.getPathId()+ "/list");
	}

	/**
	 * 게시물에 대한 내용을 완전삭제한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/eraseBoardArticle.do")
	@Secured("ROLE_ADMIN")
	public String eraseBoardArticle(
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) {

		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		
		boardService.eraseBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, boardVO, "/board/"+boardVO.getPathId()+ "/list");
	}

	/**
	 * 방명록에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/selectGuestList.do")
	@Secured("ROLE_USER")
	public String selectGuestList(
			BoardVO boardVO, 
			ModelMap model) 
	throws Exception {

		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
			
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (boardVO.isGuestModified()) {
			boardVO.setNttId(0);
			boardVO.setNttCn("");
			boardVO.setPassword("");
		}
		
		// comment 수정을 위한 처리
		if ( boardVO.getNttId() != 0) {
			boardService.selectBoardArticle(boardVO);
		}

		// 항상 설정
		boardVO.setNtcrNm(loginVO.getName());
		boardVO.setNtcrId(loginVO.getUserId());
		model.addAttribute("sessionUserId", loginVO.getUserId());

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", boardService.selectGuestList(boardVO));
		int totCnt = boardService.selectGuestListCnt(boardVO);

		boardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "cop/bbs/GuestList";
	}

	/**
	 * 방명록에 대한 내용을 등록한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/insertGuestList.do")
	@Secured("ROLE_USER")
	public String insertGuestList(
			@ModelAttribute BoardVO boardVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "forward:/cop/bbs/selectGuestList.do";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardVO.setFrstRegisterId(loginVO.getUserId());

		boardService.insertBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/cop/bbs/selectGuestList.do";
	}

	/**
	 * 방명록 수정을 위한 특정 내용을 조회한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/editGuestList.do")
	@Secured("ROLE_USER")
	public String editGuestList(
			BoardVO boardVO, 
			ModelMap model) {

		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		model.addAttribute(boardService.selectBoardArticle(boardVO));

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", boardService.selectGuestList(boardVO));
		int totCnt = boardService.selectGuestListCnt(boardVO);

		boardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "cop/bbs/GuestList";
	}

	/**
	 * 방명록 수정후  목록을 조회한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/updateGuestList.do")
	@Secured("ROLE_USER")
	public String updateGuestList(
			@ModelAttribute BoardVO boardVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "forward:/cop/bbs/selectGuestList.do";
		}

		boardService.updateBoardArticle(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/cop/bbs/selectGuestList.do";
	}

	/**
	 * 방명록에 대한 내용을 삭제한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/deleteGuestList.do")
	@Secured("ROLE_USER")
	public String deleteGuestList(
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) {

		boardService.deleteGuestList(boardVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/cop/bbs/selectGuestList.do";
	}

	/**
	 * 템플릿에 대한 미리보기용 게시물 목록을 조회한다.
	 * 
	 * @param boardVO
	 */
	@RequestMapping("/cop/bbs/previewBoardList.do")
	public String previewBoardArticle(
			BoardVO boardVO, 
			ModelMap model) {

		String template = boardVO.getSearchKeyword(); // 템플릿 URL

		BoardMasterVO masterVo = new BoardMasterVO();
		masterVo.setBbsNm("미리보기 게시판");

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		EgovMap target = null;
		List<EgovMap> list = new ArrayList<EgovMap>();

		target = new EgovMap();
		target.put("nttSj", "게시판 기능 설명");
		target.put("frstRegisterId", "ID");
		target.put("frstRegisterNm", "관리자");
		target.put("frstRegistPnttm", "2009-01-01");
		target.put("inqireCo", 7);
		target.put("replyAt", "N");
		target.put("replyLc", "0");
		target.put("useAt", "Y");

		list.add(target);

		target = new EgovMap();
		target.put("nttSj", "게시판 부가 기능 설명");
		target.put("frstRegisterId", "ID");
		target.put("frstRegisterNm", "관리자");
		target.put("frstRegistPnttm", "2009-01-01");
		target.put("inqireCo", 7);
		target.put("replyAt", "N");
		target.put("replyLc", "0");
		target.put("useAt", "Y");

		list.add(target);

		boardVO.setSearchKeyword("");

		model.addAttribute("resultList", list);

		int totCnt = list.size();
		boardVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		masterVo.setTmplatCours(template);
		boardVO.setBoardMasterVO(masterVo);

		model.addAttribute("preview", "true");
		
		model.addAttribute(boardVO);
				
		return "cop/bbs/NoticeList";
	}
	
}
