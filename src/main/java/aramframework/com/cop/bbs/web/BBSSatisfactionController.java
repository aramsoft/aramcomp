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
import aramframework.com.cop.bbs.domain.SatisfactionVO;
import aramframework.com.cop.bbs.service.BBSSatisfactionService;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 만족도 서비스 컨트롤러 클래스
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
	@RequestMapping(value="/content/board/{bbsId}/article/{nttId}/satisfactions")
	public String listSatisfaction(
			@PathVariable String bbsId, 
			@PathVariable int nttId,			
			@ModelAttribute SatisfactionVO satisfactionVO, 
			ModelMap model) {

		satisfactionVO.setBbsId(bbsId);
		satisfactionVO.setNttId(nttId);

		// 수정 처리된 후 만족도조사 등록 화면으로 처리되기 위한 구현
		if (satisfactionVO.isModified()) {
			satisfactionVO.setStsfdgNo("");
			satisfactionVO.setStsfdgCn("");
			satisfactionVO.setStsfdg(0);
		}

		// 수정을 위한 처리
		if (!satisfactionVO.getStsfdgNo().equals("")) {
			bbsSatisfactionService.selectSatisfaction(satisfactionVO);
		} else {
			satisfactionVO.setStsfdgCn("");
			satisfactionVO.setStsfdg(0);
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			satisfactionVO.setWrterNm(loginVO.getName());
			model.addAttribute("sessionUniqId", loginVO.getUniqId());
		} else {
			satisfactionVO.setWrterNm("");
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		satisfactionVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsSatisfactionService.selectSatisfactionList(satisfactionVO));
		model.addAttribute("summary", bbsSatisfactionService.getSummary(satisfactionVO));

		int totCnt = bbsSatisfactionService.selectSatisfactionListCnt(satisfactionVO);
		satisfactionVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("anonymous", "false");
		return WebUtil.adjustViewName("/cop/bbs/SatisfactionList");
	}

	/**
	 * 만족도조사를 등록한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/insertSatisfaction.do")
	@Secured("ROLE_USER")
	public String insertSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/content/board/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		satisfactionVO.setFrstRegisterId(loginVO.getUniqId());
		satisfactionVO.setWrterId(loginVO.getUniqId());
		satisfactionVO.setStsfdgPassword(""); // dummy

		bbsSatisfactionService.insertSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/content/board/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 만족도조사를 수정한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/updateSatisfaction.do")
	@Secured("ROLE_USER")
	public String updateSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/content/board/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		satisfactionVO.setLastUpdusrId(loginVO.getUniqId());
		satisfactionVO.setStsfdgPassword(""); // dummy

		bbsSatisfactionService.updateSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/content/board/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/deleteSatisfaction.do")
	@Secured("ROLE_USER")
	public String deleteSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO, 
			ModelMap model) {

		bbsSatisfactionService.deleteSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/content/board/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 익명용 만족도조사 목록 조회를 제공한다.
	 * 
	 * @param bbsId
	 * @param nttId
	 * @param satisfactionVO
	 */
	@RequestMapping(value="/content/board/anonymous/{bbsId}/article/{nttId}/satisfactions")
	public String listAnonymousSatisfaction(
			@PathVariable String bbsId, 
			@PathVariable int nttId,			
			@ModelAttribute SatisfactionVO satisfactionVO, 
			ModelMap model)
	throws Exception {

		satisfactionVO.setBbsId(bbsId);
		satisfactionVO.setNttId(nttId);

		// 수정 처리된 후 만족도조사 등록 화면으로 처리되기 위한 구현
		if (satisfactionVO.isModified()) {
			satisfactionVO.setStsfdgNo("");
			satisfactionVO.setStsfdgCn("");
			satisfactionVO.setStsfdg(0);
			satisfactionVO.setWrterNm("");
		}

		// 수정을 위한 처리
		if (!satisfactionVO.getStsfdgNo().equals("")) {
			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
			String enpassword = FileScrty.encryptPassword(satisfactionVO.getConfirmPassword());

			if (!dbpassword.equals(enpassword)) {

				model.addAttribute("subMsg", MessageHelper.getMessage("cop.password.not.same.msg"));

				satisfactionVO.setStsfdgNo("");
				satisfactionVO.setStsfdgCn("");
				satisfactionVO.setStsfdg(0);
				satisfactionVO.setWrterNm("");

			} else {
				bbsSatisfactionService.selectSatisfaction(satisfactionVO);
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		satisfactionVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", bbsSatisfactionService.selectSatisfactionList(satisfactionVO));
		model.addAttribute("summary", bbsSatisfactionService.getSummary(satisfactionVO));

		int totCnt = bbsSatisfactionService.selectSatisfactionListCnt(satisfactionVO);
		satisfactionVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);


		model.addAttribute("anonymous", "true");
		return WebUtil.adjustViewName("/cop/bbs/SatisfactionList");
	}

	/**
	 * 익명 만족도조사를 등록한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/anonymous/insertSatisfaction.do")
	public String insertAnonymousSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		satisfactionVO.setFrstRegisterId("ANONYMOUS");
		satisfactionVO.setWrterId("");
		satisfactionVO.setStsfdgPassword(FileScrty.encryptPassword(satisfactionVO.getStsfdgPassword()));

		bbsSatisfactionService.insertSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}

	/**
	 * 익명 만족도조사를 수정한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/anonymous/updateSatisfaction.do")
	public String updateAnonymousSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(satisfactionVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");
			return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
		}

		satisfactionVO.setLastUpdusrId("ANONYMOUS");
		satisfactionVO.setStsfdgPassword(FileScrty.encryptPassword(satisfactionVO.getStsfdgPassword()));

		bbsSatisfactionService.updateSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}
	
	/**
	 * 익명 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	@RequestMapping("/cop/bbs/anonymous/deleteSatisfaction.do")
	public String deleteAnonymousSatisfaction(
			@ModelAttribute SatisfactionVO satisfactionVO,
			ModelMap model) 
	throws Exception {

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
		String enpassword = FileScrty.encryptPassword(satisfactionVO.getConfirmPassword());

		if (!dbpassword.equals(enpassword)) {
			model.addAttribute("message", MessageHelper.getMessage("cop.password.not.same.msg"));
			return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
		}
		// //-----------------------------

		bbsSatisfactionService.deleteSatisfaction(satisfactionVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return "forward:/content/board/anonymous/"+satisfactionVO.getBbsId()+"/article/"+satisfactionVO.getNttId()+"/satisfactions";
	}

}
