package aramframework.mbl.cop.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cop.bbs.domain.CommentVO;
import aramframework.com.cop.bbs.service.BBSCommentService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 댓글관리 서비스 컨트롤러 클래스
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
public class MblBBSCommentController {
	
	@Autowired(required=false)
	private BBSCommentService bbsCommentService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /**
     * 댓글관리 목록 조회를 제공한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/bbs/listComment.mdo")
    public String listComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		ModelMap model) 
    throws Exception {

    	LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			commentVO.setWrterNm(loginVO.getName());
			model.addAttribute("sessionUniqId", loginVO.getUniqId());
		} else {
			commentVO.setWrterNm("");
			model.addAttribute("sessionUniqId", "");
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		commentVO.fillPageInfo(paginationInfo);
	
		model.addAttribute("resultList", bbsCommentService.selectCommentList(commentVO));

		int totCnt = bbsCommentService.selectCommentListCnt(commentVO);
		commentVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
	
		model.addAttribute("anonymous", "false");
		return "aramframework/mbl/cop/bbs/CommentList";
    }
    
    /**
     * 댓글을 등록한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/insertComment.mdo")
    public String insertComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
		    
		    return "forward:/cop/bbs/detailBoardArticle.mdo";
		}
	
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();

		commentVO.setFrstRegisterId(loginVO.getUniqId());
		commentVO.setWrterId(loginVO.getUniqId());
		commentVO.setCommentPassword("");	// dummy
	    
	    bbsCommentService.insertComment(commentVO);
	    
	    commentVO.setCommentCn("");
	    commentVO.setCommentNo("");
	
		return "forward:/cop/bbs/detailBoardArticle.mdo";
    }
    
    /**
     * 댓글을 수정한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/updateComment.mdo")
    public String updateComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		BindingResult bindingResult, 
    		ModelMap model) {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
		    
		    return "forward:/cop/bbs/detailBoardArticle.mdo";
		}
	
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		commentVO.setLastUpdusrId(loginVO.getUniqId());
		commentVO.setCommentPassword("");	// dummy
		    
		bbsCommentService.updateComment(commentVO);
		    
		commentVO.setCommentCn("");
		commentVO.setCommentNo("");
	
		return "forward:/cop/bbs/detailBoardArticle.mdo";
    }
    
    /**
     * 댓글을 삭제한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/deleteComment.mdo")
    public String deleteComment(
			@ModelAttribute("commentVO") CommentVO commentVO) {

    	bbsCommentService.deleteComment(commentVO);
		
		commentVO.setCommentCn("");
		commentVO.setCommentNo("");
		
		return "forward:/cop/bbs/detailBoardArticle.mdo";
    }
    
    /**
     * 익명용 댓글관리 목록 조회를 제공한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/bbs/anonymous/listComment.mdo")
    public String listAnonymousComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		commentVO.fillPageInfo(paginationInfo);
	
		model.addAttribute("resultList", bbsCommentService.selectCommentList(commentVO));

		int totCnt = bbsCommentService.selectCommentListCnt(commentVO);
		commentVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
	
		model.addAttribute("anonymous", "true");
		return "aramframework/mbl/cop/bbs/CommentList";
    }
    
    /**
     * 익명 댓글을 등록한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/anonymous/insertComment.mdo")
    public String insertAnonymousComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "댓글 작성자, 내용 및 패스워드는 필수 입력값입니다.");
		    
		    return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
		}
	
		commentVO.setFrstRegisterId("ANONYMOUS");
		commentVO.setWrterId("");
		commentVO.setCommentPassword(FileScrty.encryptPassword(commentVO.getCommentPassword()));
	
		bbsCommentService.insertComment(commentVO);
	
		return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
    }
    
    /**
     * 익명 댓글을 수정한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/anonymous/updateComment.mdo")
    public String updateAnonymousComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		BindingResult bindingResult, 
    		ModelMap model) 
    throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
		    
		    return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
		}
	
		//-------------------------------
	    // 패스워드 비교
	    //-------------------------------
	    String dbpassword = bbsCommentService.getCommentPassword(commentVO);
	    String enpassword = FileScrty.encryptPassword(commentVO.getCommentPassword());
	    
	    if (!dbpassword.equals(enpassword)) {
	    	model.addAttribute("msg", MessageHelper.getMessage("cop.password.not.same.msg"));
	    	return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
	    }
    
	    commentVO.setLastUpdusrId("ANONYMOUS");
	    commentVO.setCommentPassword(FileScrty.encryptPassword(commentVO.getCommentPassword()));
		    
		bbsCommentService.updateComment(commentVO);
	
		return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
    }
    
    /**
     * 익명 댓글을 삭제한다.
     * 
     * @param commentVO
     */
    @RequestMapping("/cop/cmt/anonymous/deleteComment.mdo")
    public String deleteAnonymousComment(
			@ModelAttribute("commentVO") CommentVO commentVO, 
    		ModelMap model) 
    throws Exception {
	
		//-------------------------------
		// 패스워드 비교
		//-------------------------------
		String dbpassword = bbsCommentService.getCommentPassword(commentVO);
	    String enpassword = FileScrty.encryptPassword(commentVO.getCommentPassword());
		
		if (!dbpassword.equals(enpassword)) {
		    model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
		    return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
		}
		////-----------------------------
		
		bbsCommentService.deleteComment(commentVO);
		
		return "forward:/cop/bbs/anonymous/detailBoardArticle.mdo";
    }
    
}
