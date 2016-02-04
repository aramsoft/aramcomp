package aramframework.com.uss.ion.rss.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uss.ion.rss.domain.RssManageVO;
import aramframework.com.uss.ion.rss.service.RssManageService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * RSS태그관리를 처리하는 Controller Class 구현
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
public class RssManageController {

	@Autowired
	private RssManageService rssManageService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * RSS태그관리 목록을 조회한다.
	 * 
	 * @param tableName
	 */
	// @IncludedInfo(name="RSS태그관리", order = 821)
	@RequestMapping(value = "/uss/ion/rss/listRssTableColumnJson.do")
	@Secured("ROLE_ADMIN")
	public ModelAndView listRssTableColumn(
			@RequestParam(value="tableName", required=true) String tableName) {

		ModelAndView modelAndView = new ModelAndView("jsonView");

		String sDbType = (String) MessageHelper.getMessage("Globals.DbType");

		HashMap<String,String> hmParam = new HashMap<String,String>();
		hmParam.put("dbType", sDbType);
		hmParam.put("tableName", tableName);

		modelAndView.addObject("resultList", rssManageService.selectRssManageTableColumnList(hmParam));

        return modelAndView;
	}

	/**
	 * RSS 관리 목록을 조회한다.
	 * 
	 * @param rssManageVO
	 */
	@IncludedInfo(name = "RSS서비스관리", order = 5230, gid = 50)
	@RequestMapping(value = "/uss/ion/rss/listRssManage.do")
	@Secured("ROLE_ADMIN")
	public String listRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		rssManageVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", rssManageService.selectRssManageList(rssManageVO));

		int totCnt = (Integer) rssManageService.selectRssManageListCnt(rssManageVO);
		rssManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/uss/ion/rss/RssManageList");
	}

	/**
	 * RSS태그관리 목록을 삭제한다.
	 * 
	 * @param commandMap
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/deleteListRssManage.do")
	@Secured("ROLE_ADMIN")
	public String deleteListRssManage(
			@CommandMap Map<String, Object> commandMap, 
			@ModelAttribute RssManageVO rssManageVO, 
			ModelMap model)  {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		// 한개의 값으로 삭제가 넘어올때 처리
		if (commandMap.get("checkList") instanceof String) {
			String sCheckList = (String) commandMap.get("checkList");

			rssManageVO.setFrstRegisterId(loginVO.getUniqId());
			rssManageVO.setLastUpdusrId(loginVO.getUniqId());
			rssManageVO.setRssId(sCheckList);

			rssManageService.deleteRssManage(rssManageVO);
		}

		// 여러개의 값으로 삭제가 넘어올때 처리
		if (commandMap.get("checkList") instanceof String[]) {
			String[] sArrCheckList = (String[]) commandMap.get("checkList");

			for (int i = 0; i < sArrCheckList.length; i++) {

				rssManageVO.setFrstRegisterId(loginVO.getUniqId());
				rssManageVO.setLastUpdusrId(loginVO.getUniqId());
				rssManageVO.setRssId(sArrCheckList[i]);

				rssManageService.deleteRssManage(rssManageVO);
			}
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/rss/listRssManage.do");
	}

	/**
	 * RSS태그관리 목록을 상세 조회한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/detailRssManage.do")
	@Secured("ROLE_ADMIN")
	public String detailRssManage(
			@ModelAttribute RssManageVO rssManageVO) {

		// 상세정보 불러오기
		rssManageService.selectRssManageDetail(rssManageVO);

		return WebUtil.adjustViewName("/uss/ion/rss/RssManageDetail");
	}

	/**
	 * RSS태그관리 등록화면으로 이동한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/registRssManage.do")
	@Secured("ROLE_ADMIN")
	public String registRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			ModelMap model) {

		model.addAttribute("trgetSvcTableList", rssManageService.selectRssManageTableList());

		return WebUtil.adjustViewName("/uss/ion/rss/RssManageRegist");
	}

	/**
	 * RSS태그관리를 등록한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/insertRssManage.do")
	@Secured("ROLE_ADMIN")
	public String insertRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(rssManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("trgetSvcTableList", rssManageService.selectRssManageTableList());
			return WebUtil.adjustViewName("/uss/ion/rss/RssManageRegist");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		rssManageVO.setFrstRegisterId(loginVO.getUniqId());

		// 저장
		rssManageService.insertRssManage(rssManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
        return WebUtil.redirectJsp(model, "/uss/ion/rss/listRssManage.do");
	}

	/**
	 * RSS태그관리 수정화면으로 이동한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/editRssManage.do")
	@Secured("ROLE_ADMIN")
	public String editRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			ModelMap model) {

		// 테이블 목록 불러오기
		model.addAttribute("trgetSvcTableList", rssManageService.selectRssManageTableList());

		// 수정정보 불러오기
		rssManageService.selectRssManageDetail(rssManageVO);

		return WebUtil.adjustViewName("/uss/ion/rss/RssManageEdit");
	}

	/**
	 * RSS태그관리를 수정한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/updateRssManage.do")
	@Secured("ROLE_ADMIN")
	public String updateRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(rssManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			// 테이블 목록 불러오기
			model.addAttribute("trgetSvcTableList", rssManageService.selectRssManageTableList());
			return WebUtil.adjustViewName("/uss/ion/rss/RssManageEdit");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		rssManageVO.setLastUpdusrId(loginVO.getUniqId());

		// 저장
		rssManageService.updateRssManage(rssManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
        return WebUtil.redirectJsp(model, "/uss/ion/rss/listRssManage.do");
	}

	/**
	 * RSS태그관리 목록을삭제한다.
	 * 
	 * @param rssManageVO
	 */
	@RequestMapping(value = "/uss/ion/rss/deleteRssManage.do")
	@Secured("ROLE_ADMIN")
	public String deleteRssManage(
			@ModelAttribute RssManageVO rssManageVO, 
			ModelMap model) {

		rssManageService.deleteRssManage(rssManageVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
        return WebUtil.redirectJsp(model, "/uss/ion/rss/listRssManage.do");
	}

}
