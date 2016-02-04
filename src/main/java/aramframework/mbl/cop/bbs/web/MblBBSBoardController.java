package aramframework.mbl.cop.bbs.web;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.constant.CacheKey;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.util.FileMngUtil;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.BoardMasterVO;
import aramframework.com.cop.bbs.domain.BoardVO;
import aramframework.com.cop.bbs.service.BBSBoardService;
import aramframework.com.cop.bbs.service.BBSCommentService;
import aramframework.com.cop.bbs.service.BBSMasterService;
import aramframework.com.cop.bbs.service.BBSSatisfactionService;
import aramframework.com.cop.com.service.UserInfService;
import aramframework.com.cop.scp.service.BBSScrapService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
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
public class MblBBSBoardController {
	
	@Resource(name = "cacheDictionary")
	private Map<String, Object> cacheDictionary;

	@Autowired
    private BBSBoardService boardService;
 
	@Autowired
    private BBSMasterService bbsMasterService;
    
	@Autowired
    private FileMngUtil fileUtil;

	@Autowired
	private UserInfService userInfService; // 커뮤니티 사용자 확인

    //---------------------------------
    // 2009.06.29 : 2단계 기능 추가
    // 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
    //---------------------------------
    @Autowired(required=false)
    private BBSCommentService bbsCommentService;
    
    @Autowired(required=false)
    private BBSSatisfactionService bbsSatisfactionService;
    
    @Autowired(required=false)
    private BBSScrapService bbsScrapService;
    ////-------------------------------

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
				boardMasterVO.setTmplatCours("/css/aramframework/com/cop/tpl/egovBaseTemplate.css");
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
	@RequestMapping(value="/content/mbl/board/{bbsPathId}/articles")
	public String listBoardArticle(
			@PathVariable String bbsPathId, 
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");
		
		boardVO.setBbsId(bbsId);
    	
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
			
		//-------------------------------
		// 방명록이면 방명록 URL로 forward
		//-------------------------------
		if (boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_VISIT)) {
		    return "forward:/cop/bbs/selectGuestList.mdo";
		}
	
		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList", boardService.selectBoardArticleList(boardVO));

		int totCnt = boardService.selectBoardArticleListCnt(boardVO);
		boardVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "aramframework/mbl/cop/bbs/NoticeList";
    }
	
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param nttId
	 * @param boardVO
	 */
	@RequestMapping(value="/content/mbl/board/{bbsPathId}/article/{nttId}")
	public String detailBoardArticle(
			@PathVariable String bbsPathId, 
			@PathVariable int nttId,			
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");

		boardVO.setBbsId(bbsId);
		boardVO.setNttId(nttId);
    	
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
		model.addAttribute("editAuthFlag", getEditAuthFlag(boardVO));
		
		// 조회수 증가 여부 지정
		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		boardService.updateRdcnt(boardVO);
		boardService.selectBoardArticle(boardVO);

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
	
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			model.addAttribute("sessionUniqId", loginVO.getUniqId());
		}

		return "aramframework/mbl/cop/bbs/NoticeDetail";
    }

	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param boardVO
	 */
	@RequestMapping(value="/content/mbl/board/{bbsPathId}/article/registform")
	public String registBoardArticle2(
			@PathVariable String bbsPathId, 
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");

		boardVO.setBbsId(bbsId);
		
		return registBoardArticle(boardVO, model);
	}
	
   /**
     * 게시물 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/registBoardArticle.mdo")
    public String registBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model)  {
    	
    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		model.addAttribute("editAuthFlag", editAuthFlag);
		
		return "aramframework/mbl/cop/bbs/NoticeRegist";
    }

    /**
     * 게시물을 등록한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/insertBoardArticle.mdo")
    public String insertBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		
		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("editAuthFlag", editAuthFlag);
		    return "aramframework/mbl/cop/bbs/NoticeRegist";
		}

		// 첨부파일 관련 첨부파일ID 생성
		boardVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "BBS_"));

		boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardVO.setFrstRegisterId(loginVO.getUniqId());
		boardVO.setNtcrId(loginVO.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
		boardVO.setNtcrNm(loginVO.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.insertBoardArticle(boardVO);

		return "redirect:/content/mbl/board/"+boardVO.getPathId()+ "/articles";
    }

    /**
     * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/replyBoardArticle.mdo")
    public String replyBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {
    	
		if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		model.addAttribute("editAuthFlag", editAuthFlag);

		boardService.selectBoardArticle(boardVO);

		return "aramframework/mbl/cop/bbs/NoticeReply";
    }

    /**
     * 게시물에 대한 답변을 등록한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/insertReplyBoardArticle.mdo")
    public String insertReplyBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
			BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		
		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("editAuthFlag", editAuthFlag);
		    return "aramframework/mbl/cop/bbs/NoticeReply";
		}

		// 첨부파일 관련 첨부파일ID 생성
		boardVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "BBS_"));

		boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		boardVO.setAnswerAt("Y");
		boardVO.setFrstRegisterId(loginVO.getUniqId());
		boardVO.setParntsNttId(boardVO.getNttId());
		boardVO.setThreadDepth(boardVO.getThreadDepth() + 1);

		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.insertBoardArticle(boardVO);

		return "redirect:/content/mbl/board/"+boardVO.getPathId()+ "/articles";
    }

    /**
     * 게시물 수정을 위한 수정페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/editBoardArticle.mdo")
    public String editBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {

    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		model.addAttribute("editAuthFlag", getEditAuthFlag(boardVO));
		
    	boardService.selectBoardArticle(boardVO);
		
		return "aramframework/mbl/cop/bbs/NoticeEdit";
    }

    /**
     * 게시물에 대한 내용을 수정한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/updateBoardArticle.mdo")
    public String updateBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
			BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		
		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("editAuthFlag", editAuthFlag);
		    return "aramframework/mbl/cop/bbs/NoticeEdit";
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = boardVO.getAtchFileId();
		boardVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "BBS_", atchFileId));

		boardVO.setNtcrNm(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		boardVO.setPassword(""); // 익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		boardVO.setLastUpdusrId(loginVO.getUniqId());
		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.updateBoardArticle(boardVO);

		return "redirect:/content/mbl/board/"+boardVO.getPathId()+ "/articles";
    }

    /**
     * 게시물에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/deleteBoardArticle.mdo")
    public String deleteBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {
	
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));
  	
		String editAuthFlag = getEditAuthFlag(boardVO);
		if( editAuthFlag.equals("N")) {
			throw new AccessDeniedException("access denined!!!");
		}
		
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardVO.setLastUpdusrId(loginVO.getUniqId());

		boardService.deleteBoardArticle(boardVO);

		return "redirect:/content/mbl/board/"+boardVO.getPathId()+ "/articles";
    }

	/**
	 * 익명게시물에 대한 목록을 조회한다.
	 * 
	 * @param bbsPathId
	 * @param boardVO
	 */
	@RequestMapping(value="/content/mbl/board/anonymous/{bbsPathId}/articles")
	public String listAnonymousBoardArticle(
			@PathVariable String bbsPathId, 
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");

		boardVO.setBbsId(bbsId);
		
    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			return "forward:/content/mbl/board/"+boardVO.getPathId()+ "/articles";
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);
	
		model.addAttribute("resultList", boardService.selectBoardArticleList(boardVO));

		int totCnt = boardService.selectBoardArticleListCnt(boardVO);
		boardVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("anonymous", "true");
		model.addAttribute("editAuthFlag", "Y");

		return "aramframework/mbl/cop/bbs/NoticeList";
    }

	/**
	 * 익명게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param nttId
	 * @param boardVO
	 */
	@RequestMapping(value="/content/mbl/board/anonymous/{bbsPathId}/article/{nttId}")
	public String directAnonymousBoardArticle2(
			@PathVariable String bbsPathId, 
			@PathVariable int nttId,			
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");

		boardVO.setBbsId(bbsId);
		boardVO.setNttId(nttId);
    	
    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			return "forward:/cop/bbs/detailBoardArticle.mdo";
		}

		// 조회수 증가
		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		boardService.updateRdcnt(boardVO);
		boardService.selectBoardArticle(boardVO);

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}

		model.addAttribute("sessionUniqId", "ANONYMOUS");
		model.addAttribute("anonymous", "true");
		model.addAttribute("editAuthFlag", "Y");

		return "aramframework/mbl/cop/bbs/NoticeDetail";
    }
    
	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param bbsPathId
	 * @param boardVO
	 */
	@RequestMapping(value="/content/mbl/board/anonymous/{bbsPathId}/article/registform")
	public String registAnonymousBoardArticle2(
			@PathVariable String bbsPathId, 
			@ModelAttribute BoardVO boardVO,
			ModelMap model) {

		String bbsId = WebUtil.getOriginalId(bbsPathId, "BBSMSTR_");

		boardVO.setBbsId(bbsId);
		
		return registAnonymousBoardArticle(boardVO, model);
	}
	
   /**
     * 익명게시물 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/registBoardArticle.mdo")
    public String registAnonymousBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model)  {
    	
    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
				return "forward:/cop/bbs/registBoardArticle.mdo";
		}

		model.addAttribute("anonymous", "true");
		model.addAttribute("editAuthFlag", "Y");

		return "aramframework/mbl/cop/bbs/NoticeRegist";
    }
    
    /**
     * 익명게시물을 등록한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/insertBoardArticle.mdo")
    public String insertAnonymousBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {

			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");

		    return "aramframework/mbl/cop/bbs/NoticeRegist";
		}

		// 첨부파일 관련 첨부파일ID 생성
		boardVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "BBS_"));

		boardVO.setFrstRegisterId("ANONYMOUS");
		// 익명게시판 관련
		boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.insertBoardArticle(boardVO);

		return "redirect:/content/mbl/board/anonymous/"+boardVO.getPathId()+ "/articles";
    }
    
    /**
     * 익명게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/replyBoardArticle.mdo")
    public String replyAnonymousBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {

    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
			return "forward:/cop/bbs/replyBoardArticle.mdo";
		}

		model.addAttribute("anonymous", "true");
		model.addAttribute("editAuthFlag", "Y");

		return "aramframework/mbl/cop/bbs/NoticeReply";
    }
    
    /**
     * 익명게시물에 대한 답변을 등록한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/insetReplyBoardArticle.mdo")
    public String insetReplyAnonymousBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {

			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");

		    return "aramframework/mbl/cop/bbs/NoticeReply";
		}

		// 첨부파일 관련 첨부파일ID 생성
		boardVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "BBS_"));

		boardVO.setAnswerAt("Y");
		boardVO.setFrstRegisterId("ANONYMOUS");
		boardVO.setParntsNttId(boardVO.getNttId());
		boardVO.setThreadDepth(boardVO.getThreadDepth() + 1);

		// 익명게시판 관련
		boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.insertBoardArticle(boardVO);

		return "redirect:/content/mbl/board/anonymous/"+boardVO.getPathId()+ "/articles";
    }
    
    /**
     * 익명게시물 수정을 위한 수정페이지로 이동한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/editBoardArticle.mdo")
    public String editAnonymousBoardArticle(
			@ModelAttribute BoardVO boardVO,
    		ModelMap model)
	throws Exception {

    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new EgovBizException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!boardVO.getBoardMasterVO().getBbsTyCode().equals(BBSBoardService.BBS_TYPE_ANONYMOUS)) {
	    	return "forward:/cop/bbs/editBoardArticle.mdo";
		}

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = boardService.getPasswordInf(boardVO);
		String enpassword = FileScrty.encryptPassword(boardVO.getPassword());

		if (!dbpassword.equals(enpassword)) {

			model.addAttribute("msg", MessageHelper.getMessage("cop.password.not.same.msg"));

			return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
		}

		boardService.selectBoardArticle(boardVO);

		model.addAttribute("anonymous", "true");
		model.addAttribute("editAuthFlag", "Y");
		
		return "aramframework/mbl/cop/bbs/NoticeEdit";
    }
    
    /**
     * 익명게시물에 대한 내용을 수정한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/updateBoardArticle.mdo")
    public String updateAnonymousBoardArticle(
			final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute BoardVO boardVO,
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {

			model.addAttribute("anonymous", "true");
			model.addAttribute("editAuthFlag", "Y");

		    return "aramframework/mbl/cop/bbs/NoticeEdit";
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = boardVO.getAtchFileId();
		boardVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "BBS_", atchFileId));

		boardVO.setLastUpdusrId("ANONYMOUS");

		// 익명게시판 관련
		boardVO.setPassword(FileScrty.encryptPassword(boardVO.getPassword()));
		boardVO.setNttCn(unscript(boardVO.getNttCn())); // XSS 방지

		boardVO.setNtceBgnde(boardVO.getNtceBgnde().replaceAll("-", ""));
		boardVO.setNtceEndde(boardVO.getNtceEndde().replaceAll("-", ""));

		boardService.updateBoardArticle(boardVO);

		return "redirect:/content/mbl/board/anonymous/"+boardVO.getPathId()+ "/articles";
    }
    
    /**
     * 익명게시물에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/anonymous/deleteBoardArticle.mdo")
    public String deleteAnonymousBoardArticle(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) 
    throws Exception {
	
		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = boardService.getPasswordInf(boardVO);
		String enpassword = FileScrty.encryptPassword(boardVO.getPassword());

		if (!dbpassword.equals(enpassword)) {

			model.addAttribute("msg", MessageHelper.getMessage("cop.password.not.same.msg"));

		    return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
		}
		// //-----------------------------

		boardVO.setLastUpdusrId("ANONYMOUS");
		boardService.deleteBoardArticle(boardVO);
		return "redirect:/content/mbl/board/anonymous/"+boardVO.getPathId()+ "/articles";
    }

    /**
     * 방명록에 대한 목록을 조회한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/selectGuestList.mdo")
	@Secured("ROLE_USER")
    public String selectGuestList(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {

    	if( boardVO.getBbsId() == null || boardVO.getBbsId().equals("") ) {
			throw new RuntimeException("bbsId not found");
		}
		
		// set boardMasterVO
		boardVO.setBoardMasterVO(getBoardMasterVO(boardVO.getBbsId()));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		// form clear 
		boardVO.setNttId(0);
		boardVO.setNttCn("");
		boardVO.setNtcrNm(loginVO.getName());
		boardVO.setNtcrId(loginVO.getUniqId());
		boardVO.setPassword("");
		
		model.addAttribute("sessionUniqId", loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", boardService.selectGuestList(boardVO));

		int totCnt = boardService.selectGuestListCnt(boardVO);
		boardVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "aramframework/mbl/cop/bbs/GuestList";
    }

    /**
     * 방명록에 대한 내용을 등록한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/insertGuestList.mdo")
	@Secured("ROLE_USER")
    public String insertGuestList(
			@ModelAttribute BoardVO boardVO, 
    		BindingResult bindingResult,
    		ModelMap model) {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "forward:/cop/bbs/selectGuestList.mdo";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		boardVO.setFrstRegisterId(loginVO.getUniqId());

		boardService.insertBoardArticle(boardVO);

		return "forward:/cop/bbs/selectGuestList.mdo";
    }
    
    /**
     * 방명록 수정을 위한 특정 내용을 조회한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/editGuestList.mdo")
	@Secured("ROLE_USER")
    public String editGuestList(
			@ModelAttribute BoardVO boardVO, 
			ModelMap model) {

    	boardService.selectBoardArticle(boardVO);

		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", boardService.selectGuestList(boardVO));

		int totCnt = boardService.selectGuestListCnt(boardVO);
		boardVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "aramframework/mbl/cop/bbs/GuestList";
    }

    /**
     * 방명록 수정후  목록을 조회한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/updateGuestList.mdo")
	@Secured("ROLE_USER")
    public String updateGuestList(
			@ModelAttribute BoardVO boardVO, 
    		BindingResult bindingResult,
    		ModelMap model) {

		beanValidator.validate(boardVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "forward:/cop/bbs/selectGuestList.mdo";
		}

		boardService.updateBoardArticle(boardVO);

		return "forward:/cop/bbs/selectGuestList.mdo";
    }

     /**
     * 방명록에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     */
    @RequestMapping("/cop/bbs/deleteGuestList.mdo")
	@Secured("ROLE_USER")
    public String deleteGuestList(
			@ModelAttribute BoardVO boardVO, 
    		ModelMap model) {
    	
    	boardService.deleteGuestList(boardVO);
		
		return "forward:/cop/bbs/selectGuestList.mdo";
    }

}
