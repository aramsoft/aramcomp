package aramframework.com.sec.grp.web;

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
		groupAuthorVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", groupAuthorService.selectGroupAuthorList(groupAuthorVO));

		int totCnt = groupAuthorService.selectGroupAuthorListCnt(groupAuthorVO);
		groupAuthorVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		AuthorVO authorVO = new AuthorVO();
		model.addAttribute("authorList", authorService.selectAuthorAllList(authorVO));

		return WebUtil.adjustViewName("/sec/grp/GroupAuthor");
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param userIds
	 * @param authorCodes
	 * @param regYns
	 * @param mberTyCodes
	 */
	@RequestMapping(value = "/sec/grp/insertGroupAuthor.do")
	@Secured("ROLE_ADMIN")
	public String insertGroupAuthor(
			@RequestParam String userIds, 
			@RequestParam String authorCodes,
			@RequestParam String regYns, 
			@RequestParam String mberTyCodes,// 2011.08.04 수정 부분
			ModelMap model) {

		String[] strUserIds = userIds.split(";");
		String[] strAuthorCodes = authorCodes.split(";");
		String[] strRegYns = regYns.split(";");
		String[] strMberTyCodes = mberTyCodes.split(";");// 2011.08.04 수정 부분

		GroupAuthorVO agVO = new GroupAuthorVO();
		for (int i = 0; i < strUserIds.length; i++) {
			agVO.setUniqId(strUserIds[i]);
			agVO.setAuthorCode(strAuthorCodes[i]);
			agVO.setMberTyCode(strMberTyCodes[i]);// 2011.08.04 수정 부분
			if (strRegYns[i].equals("N"))
				groupAuthorService.insertGroupAuthor(agVO);
			else
				groupAuthorService.updateGroupAuthor(agVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroupAuthor.do");
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * 
	 * @param userIds
	 */
	@RequestMapping(value = "/sec/grp/deleteGroupAuthor.do")
	@Secured("ROLE_ADMIN")
	public String deleteGroupAuthor(
			@RequestParam String userIds, 
			ModelMap model) {

		String[] strUserIds = userIds.split(";");

		GroupAuthorVO agVO = new GroupAuthorVO();
		for (int i = 0; i < strUserIds.length; i++) {
			agVO.setUniqId(strUserIds[i]);
			groupAuthorService.deleteGroupAuthor(agVO);
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/sec/grp/listGroupAuthor.do");
	}

}