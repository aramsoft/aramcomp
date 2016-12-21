package aramframework.com.utl.sys.srm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.fcc.service.DateUtil;
import aramframework.com.utl.fcc.service.StringUtil;
import aramframework.com.utl.sys.srm.domain.ServerResrceMntrngVO;
import aramframework.com.utl.sys.srm.service.ServerResrceMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 서버자원모니터링에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class ServerResrceMntrngController {

	@Autowired
	private ServerResrceMntrngService serverResrceMntrngService;

	/**
	 * 서버자원모니터링의 대상정보 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	@IncludedInfo(name = "서버자원모니터링", order = 9080, gid = 90)
	@RequestMapping(value = "/utl/sys/srm/listMntrngServer.do")
	@Secured("ROLE_ADMIN")
	public String listMntrngServer(
			@ModelAttribute ServerResrceMntrngVO serverResrceMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		serverResrceMntrngVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", serverResrceMntrngService.selectMntrngServerList(serverResrceMntrngVO));
		int totCnt = serverResrceMntrngService.selectMntrngServerListCnt(serverResrceMntrngVO);

		serverResrceMntrngVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/srm/MntrngServerList");
	}

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/srm/listServerResrceMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listServerResrceMntrng(
			@ModelAttribute ServerResrceMntrngVO serverResrceMntrngVO,
			ModelMap model) {

		if (serverResrceMntrngVO.getStrStartDt() == null || serverResrceMntrngVO.getStrEndDt() == null) {
			serverResrceMntrngVO.setStrStartDt(DateUtil.addMonth(DateUtil.getToday(), -1));
			serverResrceMntrngVO.setStrEndDt(DateUtil.getToday());
		} else {
			serverResrceMntrngVO.setStrStartDt(StringUtil.removeMinusChar(serverResrceMntrngVO.getStrStartDt()));
			serverResrceMntrngVO.setStrEndDt(StringUtil.removeMinusChar(serverResrceMntrngVO.getStrEndDt()));
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		serverResrceMntrngVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("serverResrceMntrngList", serverResrceMntrngService.selectServerResrceMntrngList(serverResrceMntrngVO));
		int totCnt = serverResrceMntrngService.selectServerResrceMntrngListCnt(serverResrceMntrngVO);

		serverResrceMntrngVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		serverResrceMntrngVO.setStrStartDt(StringUtil.addMinusChar(serverResrceMntrngVO.getStrStartDt()));
		serverResrceMntrngVO.setStrEndDt(StringUtil.addMinusChar(serverResrceMntrngVO.getStrEndDt()));

		return WebUtil.adjustViewName("/utl/sys/srm/ServerResrceMntrngList");
	}

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * 
	 * @param serverResrceMntrngVO
	 */
	@RequestMapping(value = "/utl/sys/srm/detailServerResrceMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailServerResrceMntrng(
			ServerResrceMntrngVO serverResrceMntrngVO,
			ModelMap model) {

		model.addAttribute(serverResrceMntrngService.selectServerResrceMntrng(serverResrceMntrngVO));

		return WebUtil.adjustViewName("/utl/sys/srm/ServerResrceMntrngDetail");
	}

}