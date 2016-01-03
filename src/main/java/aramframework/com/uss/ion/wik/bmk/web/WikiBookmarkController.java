package aramframework.com.uss.ion.wik.bmk.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.wik.bmk.service.WikiBookmarkService;
import aramframework.com.uss.ion.wik.bmk.service.WikiBookmarkVO;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 위키북마크를 처리하는 Controller Class 구현
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
public class WikiBookmarkController {

	@Resource(name = "wikiBookmarkService")
	private WikiBookmarkService wikiBookmarkService;

	/**
	 * 위키북마크 목록을 조회한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	@IncludedInfo(name = "Wiki기능", order = 5300, gid = 50)
	@RequestMapping(value = "/uss/ion/wik/bmk/listWikiBookmark.do")
	@Secured("ROLE_USER")
	public String listWikiBookmark(
			@ModelAttribute WikiBookmarkVO wikiBookmarkVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		wikiBookmarkVO.setFrstRegisterId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		wikiBookmarkVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", wikiBookmarkService.selectWikiBookmarkList(wikiBookmarkVO));

		int totCnt = (Integer) wikiBookmarkService.selectWikiBookmarkListCnt(wikiBookmarkVO);
		wikiBookmarkVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/wik/bmk/WikiBookmarkList");
	}

	/**
	 * 위키북마크 목록을 삭제한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	@RequestMapping(value = "/uss/ion/wik/bmk/deleteWikiBookmark.do")
	@Secured("ROLE_USER")
	public String deleteWikiBookmark(
			@CommandMap Map<String, Object> commandMap, 
			@ModelAttribute WikiBookmarkVO wikiBookmarkVO, 
			ModelMap model) {

		// 한개의 값으로 삭제가 넘어올때 처리
		if (commandMap.get("checkList") instanceof String) {
			String sCheckList = (String) commandMap.get("checkList");

			wikiBookmarkVO.setWikiBkmkId(sCheckList);
			wikiBookmarkService.deleteWikiBookmark(wikiBookmarkVO);
		}

		// 여러개의 값으로 삭제가 넘어올때 처리
		if (commandMap.get("checkList") instanceof String[]) {
			String[] sArrCheckList = (String[]) commandMap.get("checkList");

			for (int i = 0; i < sArrCheckList.length; i++) {
				wikiBookmarkVO.setWikiBkmkId(sArrCheckList[i]);
				wikiBookmarkService.deleteWikiBookmark(wikiBookmarkVO);
			}
		}

		// 페이지 인텍스 설정
		wikiBookmarkVO.setPageIndex(1);

		return WebUtil.adjustViewName("/uss/ion/wik/bmk/WikiBookmarkList");
	}
	
	/**
	 * 위키북마크를 등록 한다.
	 * 
	 * @param wikiBookmarkVO
	 */
	@RequestMapping(value = "/uss/ion/wik/bmk/registWikiBookmark.do")
	public String registWikiBookmark(
			@ModelAttribute WikiBookmarkVO wikiBookmarkVO, 
			ModelMap model) 
	throws Exception {

		String sDupl = "N";

		if (wikiBookmarkVO.getUsid() != null && wikiBookmarkVO.getWikiBkmkNm() != null) {
			if (wikiBookmarkService.selectWikiBookmarkDuplicationCnt(wikiBookmarkVO) > 0) {
				sDupl = "Y";
			} else {
				wikiBookmarkService.insertWikiBookmark(wikiBookmarkVO);
			}
		}
		// log.debug("Controller EgovWikiBookmarkRegist.WikiBookmark>" + wikiBookmark);
		// 중복 설정
		model.addAttribute("S_DUPL", sDupl);
		return "aramframework/com/uss/ion/wik/bmk/WikiBookmarkRegist";
	}

}
