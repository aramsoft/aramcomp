package aramframework.com.cop.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
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
			CommentVO commentVO, 
			@PathVariable String bbsId, 
			@PathVariable int nttId,
			@RequestParam String anonymous,
			ModelMap model)  
	throws Exception {
	
		commentVO.setBbsId(bbsId);
		commentVO.setNttId(nttId);

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");
			commentVO.setWrterNm("");
		} else {
			model.addAttribute("anonymous", "false");
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			commentVO.setWrterNm(loginVO.getName());
			model.addAttribute("uniqId", loginVO.getUniqId());
		}

		// comment 수정을 위한 처리
		if (!commentVO.getCommentNo().equals("")) {
			model.addAttribute(bbsCommentService.selectComment(commentVO));
		} else {
			commentVO.setCommentCn("");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		commentVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsCommentService.selectCommentList(commentVO));
		int totCnt = bbsCommentService.selectCommentListCnt(commentVO);

		commentVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "cop/bbs/CommentList";
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
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/cop/bbs/listComment.do";
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");
			commentVO.setFrstRegisterId("ANONYMOUS");
			commentVO.setWrterId("");
			commentVO.setCommentPassword(FileScrty.encryptPassword(commentVO.getCommentPassword()));
		} else {
			model.addAttribute("anonymous", "false");
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

			commentVO.setFrstRegisterId(loginVO.getUniqId());
			commentVO.setWrterId(loginVO.getUniqId());
			commentVO.setCommentPassword(""); // dummy
		}

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
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(commentVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsCommentService.getCommentPassword(commentVO);
			String enpassword = FileScrty.encryptPassword(commentVO.getConfirmPassword());
			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
			}

			commentVO.setLastUpdusrId("ANONYMOUS");
			commentVO.setCommentPassword(enpassword);
		} else {
			model.addAttribute("anonymous", "false");

			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			commentVO.setLastUpdusrId(loginVO.getUniqId());
			commentVO.setCommentPassword(""); // dummy
		}

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
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {
	
		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsCommentService.getCommentPassword(commentVO);
			String enpassword = FileScrty.encryptPassword(commentVO.getConfirmPassword());
			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
			}
		} else {
			model.addAttribute("anonymous", "false");
		}

		bbsCommentService.deleteComment(commentVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/content/board/"+commentVO.getBbsId()+"/article/"+commentVO.getNttId()+"/comments";
	}

}
