package aramframework.com.cop.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.cmm.security.userdetails.UserDetailsHelper;
import aramframework.cmm.util.MessageHelper;
import aramframework.com.cop.bbs.domain.SatisfactionVO;
import aramframework.com.cop.bbs.service.BBSSatisfactionService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sim.service.FileScrty;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 만족도 서비스 컨트롤러 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class BBSSatisfactionController {

	@Autowired 
	private BBSSatisfactionService bbsSatisfactionService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 만족도조사 목록 조회를 제공한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param satisfactionVO
	 */
	@RequestMapping(value="/board/{bbsId}/id/{nttId}/satisfactions")
	public String listSatisfaction(
			SatisfactionVO satisfactionVO, 
			@PathVariable String bbsId, 
			@PathVariable int nttId,			
			@RequestParam String anonymous,
			ModelMap model)  
	throws Exception {

		satisfactionVO.setBbsId(bbsId);
		satisfactionVO.setNttId(nttId);

		// 수정 처리된 후 만족도조사 등록 화면으로 처리되기 위한 구현
		if (satisfactionVO.isModified()) {
			satisfactionVO.setStsfdgNo(0);
			satisfactionVO.setStsfdgCn("");
			satisfactionVO.setStsfdg(0);
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");
			satisfactionVO.setWrterNm("");
		} else {
			model.addAttribute("anonymous", "false");
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO != null) {
				satisfactionVO.setWrterNm(loginVO.getName());
				model.addAttribute("userId", loginVO.getUserId());
			}	
		}

		// 수정을 위한 처리
		if (satisfactionVO.getStsfdgNo() != 0 ) {
			model.addAttribute(bbsSatisfactionService.selectSatisfaction(satisfactionVO));
		} else {
			satisfactionVO.setStsfdgCn("");
			satisfactionVO.setStsfdg(0);
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		satisfactionVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsSatisfactionService.selectSatisfactionList(satisfactionVO));
		model.addAttribute("summary", bbsSatisfactionService.getSummary(satisfactionVO));
		int totCnt = bbsSatisfactionService.selectSatisfactionListCnt(satisfactionVO);

		satisfactionVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return "com/cop/bbs/SatisfactionList";
	}

	/**
	 * 만족도조사를 등록한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/insertSatisfaction.do")
	public String insertSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			BindingResult bindingResult, 
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");
			satisfactionVO.setFrstRegisterId("ANONYMOUS");
			satisfactionVO.setWrterId("");
			satisfactionVO.setStsfdgPassword(FileScrty.encryptPassword(satisfactionVO.getStsfdgPassword()));
		} else {
			model.addAttribute("anonymous", "false");
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO == null) {
				throw new AccessDeniedException("access denined!!!");
			} else {
				satisfactionVO.setFrstRegisterId(loginVO.getUserId());
				satisfactionVO.setWrterId(loginVO.getUserId());
				satisfactionVO.setStsfdgPassword(""); // dummy
			}	
		}

		bbsSatisfactionService.insertSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 만족도조사를 수정한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/updateSatisfaction.do")
	public String updateSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			BindingResult bindingResult, 
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
			String enpassword = FileScrty.encryptPassword(satisfactionVO.getConfirmPassword());
			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
			}

			satisfactionVO.setLastUpdusrId("ANONYMOUS");
			satisfactionVO.setStsfdgPassword(enpassword);
		} else {
			model.addAttribute("anonymous", "false");

			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO == null) {
				throw new AccessDeniedException("access denined!!!");
			} else {
				satisfactionVO.setLastUpdusrId(loginVO.getUserId());
				satisfactionVO.setStsfdgPassword(""); // dummy
			}	
		}

		bbsSatisfactionService.updateSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/deleteSatisfaction.do")
	public String deleteSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			@RequestParam String anonymous,
			ModelMap model) 
	throws Exception {

		if( "true".equals(anonymous)) {
			model.addAttribute("anonymous", "true");

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
			String enpassword = FileScrty.encryptPassword(satisfactionVO.getConfirmPassword());

			if (!dbpassword.equals(enpassword)) {
				model.addAttribute("message", MessageHelper.getMessage("cop.password.not.same.msg"));
				return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
			}
		} else {
			model.addAttribute("anonymous", "false");
			LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
			if( loginVO == null) {
				throw new AccessDeniedException("access denined!!!");
			} 
		}

		bbsSatisfactionService.deleteSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/board/"+satisfactionVO.getBbsId()+"/id/"+satisfactionVO.getNttId()+"/satisfactions";
	}

}
