package aramframework.com.sec.arm.web;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.sec.arm.service.AuthorResourceService;
import aramframework.com.sec.arm.service.AuthorResourceVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
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
public class AuthorResourceController {

	@Resource(name = "authorResourceService")
	private AuthorResourceService authorResourceService;
		
	/**
	 * 권한별 할당된 롤 목록 조회
	 * 
	 * @param authorResourceVO
	 */
	@RequestMapping(value = "/sec/arm/listAuthorResource.do")
	@Secured("ROLE_ADMIN")
	public String listAuthorResource(
			@ModelAttribute AuthorResourceVO authorResourceVO, 
			@RequestParam(value="authorCodeForResources", required=false) String authorCodeForResources, 
			ModelMap model) {

		if ( !(authorCodeForResources == null || authorCodeForResources.equals("")) ) {
			authorResourceVO.setSaveSearchKeyword(authorResourceVO.getSearchKeyword());
			authorResourceVO.setSaveSearchCondition(authorResourceVO.getSearchCondition());
			authorResourceVO.setSavePageIndex(authorResourceVO.getSavePageIndex());
			
			authorResourceVO.setSearchKeyword(authorCodeForResources);
			authorResourceVO.setSearchCondition("1");
			authorResourceVO.setPageIndex(1);
			authorResourceVO.setAuthorCode(authorCodeForResources);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		authorResourceVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", authorResourceService.selectAuthorResourceList(authorResourceVO));

		int totCnt = authorResourceService.selectAuthorResourceListCnt(authorResourceVO);
		authorResourceVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/sec/arm/AuthorResource");
	}

	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * 
	 * @param authorCode
	 * @param resourceCodes
	 * @param regYns
	 */
	@RequestMapping(value = "/sec/arm/insertAuthorResource.do")
	@Secured("ROLE_ADMIN")
	public String insertAuthorResource(
			@RequestParam String authorCode, 
			@RequestParam String resourceCodes,
			@RequestParam String regYns, 
			ModelMap model) {

		String[] strResourceCodes = resourceCodes.split(";");
		String[] strRegYns = regYns.split(";");

		AuthorResourceVO armVO = new AuthorResourceVO();
		armVO.setAuthorCode(authorCode);

		for (int i = 0; i < strResourceCodes.length; i++) {
			armVO.setResourceCode(strResourceCodes[i]);
			armVO.setRegYn(strRegYns[i]);

			authorResourceService.deleteAuthorResource(armVO);// 2011.09.07
			if (strRegYns[i].equals("Y")) {
				authorResourceService.insertAuthorResource(armVO);
			} 
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sec/arm/listAuthorResource.do");
	}
	
}