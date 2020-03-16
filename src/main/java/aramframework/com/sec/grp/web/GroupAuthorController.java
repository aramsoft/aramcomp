package aramframework.com.sec.grp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.arm.domain.AuthorVO;
import aramframework.com.sec.arm.service.AuthorService;
import aramframework.com.sec.grp.domain.GroupAuthorVO;
import aramframework.com.sec.grp.service.GroupAuthorService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한그룹에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class GroupAuthorController {

	@Autowired
	private GroupAuthorService groupAuthorService;
	
	@Autowired
	private AuthorService authorService;
		
	/**
	 * 그룹별 할당된 권한 목록 조회
	 * 
	 * @param groupAuthorVO
	 */
	@IncludedInfo(name = "그룹권한관리", order = 2031, gid = 20)
	@RequestMapping(value = "/sec/grp/listGroupAuthor.do")
	@Secured("ROLE_ADMIN")
	public String listGroupAuthor(
			@ModelAttribute GroupAuthorVO groupAuthorVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		groupAuthorVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", groupAuthorService.selectGroupAuthorList(groupAuthorVO));
		int totCnt = groupAuthorService.selectGroupAuthorListCnt(groupAuthorVO);

		groupAuthorVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		AuthorVO authorVO = new AuthorVO();
		model.addAttribute("authorList", authorService.selectAuthorAllList(authorVO));

		return WebUtil.adjustViewName("sec/grp/GroupAuthor");
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param userIds
	 * @param authorCodes
	 * @param regYns
	 * @param mberTyCodes
	 */
	@RequestMapping(value = "/sec/grp/insertListGroupAuthor.do")
	@Secured("ROLE_ADMIN")
	public String insertGroupAuthors(
			@ModelAttribute GroupAuthorVO groupAuthorVO,
			@RequestParam String strAuthorCodes,
			@RequestParam String strRegYns, 
			@RequestParam String strMberTyCodes,// 2011.08.04 수정 부분
			HttpServletRequest request, 
			ModelMap model) {

    	String[] uniqIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		uniqIds = request.getParameterValues("uniqIds"); 

		String[] authorCodes = strAuthorCodes.split(";");
		String[] regYns = strRegYns.split(";");
		String[] mberTyCodes = strMberTyCodes.split(";");

		groupAuthorService.insertGroupAuthors(uniqIds, authorCodes, regYns, mberTyCodes);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, groupAuthorVO, "/sec/grp/listGroupAuthor.do");
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param userIds
	 */
	@RequestMapping(value = "/sec/grp/deleteListGroupAuthor.do")
	@Secured("ROLE_ADMIN")
	public String deleteGroupAuthors(
			@ModelAttribute GroupAuthorVO groupAuthorVO,
			HttpServletRequest request, 
			ModelMap model) {

    	String[] uniqIds = null;
    	if(request.getParameterValues("uniqIds") != null) 
    		uniqIds = request.getParameterValues("uniqIds"); 

		groupAuthorService.deleteGroupAuthors(uniqIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, groupAuthorVO, "/sec/grp/listGroupAuthor.do");
	}

}