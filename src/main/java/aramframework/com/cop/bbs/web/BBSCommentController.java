package aramframework.com.cop.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.bbs.domain.CommentVO;
import aramframework.com.cop.bbs.service.BBSCommentService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 댓글관리 서비스 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BBSCommentController {

	@Autowired
	private BBSCommentService bbsCommentService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 댓글관리 목록 조회를 제공한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param commentVO
	 */
	@RequestMapping(value="/content/board/{bbsId}/article/{nttId}/comments")
	public String listComment(
			@PathVariable String bbsId, 
			@PathVariable int nttId,			
			CommentVO commentVO, 
			ModelMap model) {

		commentVO.setBbsId(bbsId);
		commentVO.setNttId(nttId);

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
		}

		// comment 수정을 위한 처리
		if (!commentVO.getCommentNo().equals("")) {
			model.addAttribute(bbsCommentService.selectComment(commentVO));
		} else {
			commentVO.setCommentCn("");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			commentVO.setWrterNm(loginVO.getName());
			model.addAttribute("uniqId", loginVO.getUniqId());
		} else {
			commentVO.setWrterNm("");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		commentVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsCommentService.selectCommentList(commentVO));
		int totCnt = bbsCommentService.selectCommentListCnt(commentVO);

		commentVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		model.addAttribute("anonymous", "false");
		
		return WebUtil.adjustViewName("/cop/bbs/CommentList");
	}

	/**
	 * 댓글을 등록한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/insertComment.do")
	@Secured("ROLE_USER")
	public String insertComment(
			@ModelAttribute CommentVO commentVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/cop/bbs/listComment.do";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		commentVO.setFrstRegisterId(loginVO.getUniqId());
		commentVO.setWrterId(loginVO.getUniqId());
		commentVO.setCommentPassword(""); // dummy
		
		bbsCommentService.insertComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

	/**
	 * 댓글을 수정한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/updateComment.do")
	@Secured("ROLE_USER")
	public String updateComment(
			@ModelAttribute CommentVO commentVO, 
			BindingResult bindingResult,
			ModelMap model) {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		commentVO.setLastUpdusrId(loginVO.getUniqId());
		commentVO.setCommentPassword(""); // dummy

		bbsCommentService.updateComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/deleteComment.do")
	@Secured("ROLE_USER")
	public String deleteComment(
			@ModelAttribute CommentVO commentVO, 
			ModelMap model) {

		bbsCommentService.deleteComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

	/**
	 * 익명용 댓글관리 목록 조회를 제공한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param commentVO
	 */
	@RequestMapping(value="/content/board/anonymous/{bbsId}/article/{nttId}/comments")
	public String listAnonymousComment(
			@PathVariable String bbsId, 
			@PathVariable int nttId,			
			CommentVO commentVO, 
			ModelMap model) 
	throws Exception {

		commentVO.setBbsId(bbsId);
		commentVO.setNttId(nttId);

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
			commentVO.setWrterNm("");
		}

		// 수정을 위한 처리
		if (!commentVO.getCommentNo().equals("")) {
			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsCommentService.getCommentPassword(commentVO);
			String enpassword = FileScrty.encryptPassword(commentVO.getConfirmPassword());

			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
			} else {
				model.addAttribute(bbsCommentService.selectComment(commentVO));
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		commentVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsCommentService.selectCommentList(commentVO));
		int totCnt = bbsCommentService.selectCommentListCnt(commentVO);

		commentVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		model.addAttribute("anonymous", "true");

		return WebUtil.adjustViewName("/cop/bbs/CommentList");
	}

	/**
	 * 익명 댓글을 등록한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/anonymous/insertComment.do")
	public String insertAnonymousComment(
			@ModelAttribute CommentVO commentVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자, 내용 및 패스워드는 필수 입력값입니다.");
			return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
		}

		commentVO.setFrstRegisterId("ANONYMOUS");
		commentVO.setWrterId("");
		commentVO.setCommentPassword(FileScrty.encryptPassword(commentVO.getCommentPassword()));

		bbsCommentService.insertComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

	/**
	 * 익명 댓글을 수정한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/anonymous/updateComment.do")
	public String updateAnonymousComment(
			@ModelAttribute CommentVO commentVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
		}

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsCommentService.getCommentPassword(commentVO);
		String enpassword = FileScrty.encryptPassword(commentVO.getConfirmPassword());

		if (!dbpassword.equals(enpassword)) {
			model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
			return "forward:/cop/bbs/anonymous/listComment.do";
		}
		// //-----------------------------

		commentVO.setLastUpdusrId("ANONYMOUS");
		commentVO.setCommentPassword(FileScrty.encryptPassword(commentVO.getCommentPassword()));

		bbsCommentService.updateComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}
	
	/**
	 * 익명 댓글을 삭제한다.
	 * 
	 * @param commentVO
	 */
	@RequestMapping("/cop/bbs/anonymous/deleteComment.do")
	public String deleteAnonymousComment(
			@ModelAttribute CommentVO commentVO, 
			ModelMap model)
	throws Exception {

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsCommentService.getCommentPassword(commentVO);
		String enpassword = FileScrty.encryptPassword(commentVO.getConfirmPassword());

		if (!dbpassword.equals(enpassword)) {
			model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
			return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
		}
		// //-----------------------------

		bbsCommentService.deleteComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/content/board/anonymous/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

}
