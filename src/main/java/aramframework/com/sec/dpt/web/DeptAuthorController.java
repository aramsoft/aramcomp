package aramframework.com.sec.dpt.web;

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
import aramframework.com.sec.dpt.domain.DeptAuthorVO;
import aramframework.com.sec.dpt.service.DeptAuthorService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 부서권한에 관한 controller 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class DeptAuthorController {

	@Autowired
	private DeptAuthorService deptAuthorService;
	
	@Autowired
	private AuthorService authorService;
	
	/**
	 * 부서별 할당된 권한목록 조회
	 * 
	 * @param deptAuthorVO
	 */
	@IncludedInfo(name = "부서권한관리", order = 2041, gid = 20)
	@RequestMapping(value = "/sec/dpt/listDeptAuthor.do")
	@Secured("ROLE_ADMIN")
	public String listDeptAuthor(
			@ModelAttribute DeptAuthorVO deptAuthorVO,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		deptAuthorVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptAuthorService.selectDeptAuthorList(deptAuthorVO));
		int totCnt = deptAuthorService.selectDeptAuthorListCnt(deptAuthorVO);

		deptAuthorVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		AuthorVO authorVO = new AuthorVO();
		model.addAttribute("authorList", authorService.selectAuthorAllList(authorVO));

		return WebUtil.adjustViewName("/sec/dpt/DeptAuthor");
	}

	/**
	 * 부서에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param userIds
	 * @param authorCodes
	 * @param regYns
	 */
	@RequestMapping(value = "/sec/dpt/insertDeptAuthor.do")
	@Secured("ROLE_ADMIN")
	public String insertDeptAuthor(
			@RequestParam String userIds, 
			@RequestParam String authorCodes,
			@RequestParam String regYns, 
			ModelMap model) {

		deptAuthorService.insertDeptAuthors(userIds, authorCodes, regYns);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sec/dpt/listDeptAuthor.do");
	}

	/**
	 * 부서별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param userIds
	 */
	@RequestMapping(value = "/sec/dpt/deleteDeptAuthor.do")
	@Secured("ROLE_ADMIN")
	public String deleteDeptAuthor(
			@RequestParam String userIds, 
			ModelMap model) {

		deptAuthorService.deleteDeptAuthors(userIds);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sec/dpt/listDeptAuthor.do");
	}

}