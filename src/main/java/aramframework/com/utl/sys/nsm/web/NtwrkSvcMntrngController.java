package aramframework.com.utl.sys.nsm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.ComCodeVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngLogVO;
import aramframework.com.utl.sys.nsm.domain.NtwrkSvcMntrngVO;
import aramframework.com.utl.sys.nsm.service.NtwrkSvcMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 네트워크서비스 모니터링대상에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class NtwrkSvcMntrngController {

	@Autowired
	private NtwrkSvcMntrngService ntwrkSvcMntrngService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 네트워크서비스 모니터링대상 정보에 대한 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@IncludedInfo(name = "네트워크서비스모니터링", order = 9050, gid = 90)
	@RequestMapping("/utl/sys/nsm/listNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listNtwrkSvcMntrng(
			@ModelAttribute NtwrkSvcMntrngVO ntwrkSvcMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		ntwrkSvcMntrngVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", ntwrkSvcMntrngService.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO));
		int totCnt = ntwrkSvcMntrngService.selectNtwrkSvcMntrngListCnt(ntwrkSvcMntrngVO);

		ntwrkSvcMntrngVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngList");
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/detailNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailNtwrkSvcMntrng(
			NtwrkSvcMntrngVO ntwrkSvcMntrngVO,
			ModelMap model) {

		ntwrkSvcMntrngVO = ntwrkSvcMntrngService.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);

		// 시스템 IP 설정
		String[] sysIps = ntwrkSvcMntrngVO.getSysIp().split("[.]");
		ntwrkSvcMntrngVO.setSysIp1(sysIps[0]);
		ntwrkSvcMntrngVO.setSysIp2(sysIps[1]);
		ntwrkSvcMntrngVO.setSysIp3(sysIps[2]);
		ntwrkSvcMntrngVO.setSysIp4(sysIps[3]);

		if (ntwrkSvcMntrngVO.getCreatDt() != null && !ntwrkSvcMntrngVO.getCreatDt().equals("")) {
			if (ntwrkSvcMntrngVO.getCreatDt().length() > 18) {
				ntwrkSvcMntrngVO.setCreatDt(ntwrkSvcMntrngVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(ntwrkSvcMntrngVO);
		
		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngDetail");
	}

	/**
	 * 네트워크서비스 모니터링 대상 정보의 등록페이지로 이동한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/registNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String registNtwrkSvcMntrng(
			@ModelAttribute NtwrkSvcMntrngVO ntwrkSvcMntrngVO) {

		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngRegist");
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 등록한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/insertNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String insertNtwrkSvcMntrng(
			@ModelAttribute NtwrkSvcMntrngVO ntwrkSvcMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(ntwrkSvcMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngRegist");
		}

		// 시스템 IP 설정
		String sysIp = "";
		sysIp += ntwrkSvcMntrngVO.getSysIp1();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp2();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp3();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp4();
		ntwrkSvcMntrngVO.setSysIp(sysIp);

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ntwrkSvcMntrngVO.setFrstRegisterId(loginVO.getUniqId());

		if (ntwrkSvcMntrngService.selectNtwrkSvcMntrngCheck(ntwrkSvcMntrngVO) > 0) {
			model.addAttribute("ntwrkSvcMntrngDuplicated", "true");
			return  "aramframework/com/utl/sys/nsm/NtwrkSvcMntrngRegist";
		}

		ntwrkSvcMntrngService.insertNtwrkSvcMntrng(ntwrkSvcMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/nsm/listNtwrkSvcMntrng.do");
	}

	/**
	 * 네트워크서비스 모니터링 대상 정보의 수정페이지로 이동한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/editNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String editNtwrkSvcMntrng(
			NtwrkSvcMntrngVO ntwrkSvcMntrngVO,
			ModelMap model) {

		ntwrkSvcMntrngVO = ntwrkSvcMntrngService.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);

		// 시스템 IP 설정
		String[] sysIps = ntwrkSvcMntrngVO.getSysIp().split("[.]");
		ntwrkSvcMntrngVO.setSysIp1(sysIps[0]);
		ntwrkSvcMntrngVO.setSysIp2(sysIps[1]);
		ntwrkSvcMntrngVO.setSysIp3(sysIps[2]);
		ntwrkSvcMntrngVO.setSysIp4(sysIps[3]);

		if (ntwrkSvcMntrngVO.getCreatDt() != null && !ntwrkSvcMntrngVO.getCreatDt().equals("")) {
			if (ntwrkSvcMntrngVO.getCreatDt().length() > 18) {
				ntwrkSvcMntrngVO.setCreatDt(ntwrkSvcMntrngVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(ntwrkSvcMntrngVO);
		
		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngEdit");
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 수정한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/updateNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String updateNtwrkSvcMntrng(
			@ModelAttribute NtwrkSvcMntrngVO ntwrkSvcMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(ntwrkSvcMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngEdit");
		}

		// 시스템 IP 설정
		String sysIp = "";
		sysIp += ntwrkSvcMntrngVO.getSysIp1();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp2();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp3();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp4();
		ntwrkSvcMntrngVO.setSysIp(sysIp);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		ntwrkSvcMntrngVO.setLastUpdusrId(loginVO.getUniqId());

		ntwrkSvcMntrngService.updateNtwrkSvcMntrng(ntwrkSvcMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/nsm/listNtwrkSvcMntrng.do");
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 삭제한다.
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	@RequestMapping("/utl/sys/nsm/deleteNtwrkSvcMntrng.do")
	@Secured("ROLE_ADMIN")
	public String deleteNtwrkSvcMntrng(
			@ModelAttribute NtwrkSvcMntrngVO ntwrkSvcMntrngVO, 
			ModelMap model) {

		ntwrkSvcMntrngService.deleteNtwrkSvcMntrng(ntwrkSvcMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/nsm/listNtwrkSvcMntrng.do");
	}

	/**
	 * 네트워크서비스 모니터링로그 정보에 대한 목록을 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	@RequestMapping("/utl/sys/nsm/listNtwrkSvcMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String listNtwrkSvcMntrngLog(
			@ModelAttribute NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO, 
			ModelMap model) {

		// 조회기간설정
		if (ntwrkSvcMntrngLogVO.getSearchBgnDe() != null && ntwrkSvcMntrngLogVO.getSearchEndDe() != null) {
			if (!ntwrkSvcMntrngLogVO.getSearchBgnDe().equals("") && !ntwrkSvcMntrngLogVO.getSearchEndDe().equals("")) {
				ntwrkSvcMntrngLogVO.setSearchBgnDt(ntwrkSvcMntrngLogVO.getSearchBgnDe() + " " + ntwrkSvcMntrngLogVO.getSearchBgnHour());
				ntwrkSvcMntrngLogVO.setSearchEndDt(ntwrkSvcMntrngLogVO.getSearchEndDe() + " " + ntwrkSvcMntrngLogVO.getSearchEndHour());
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		ntwrkSvcMntrngLogVO.fillPageInfo(paginationInfo);

		List<NtwrkSvcMntrngLogVO> list = ntwrkSvcMntrngService.selectNtwrkSvcMntrngLogList(ntwrkSvcMntrngLogVO);
		for (int k = 0; k < list.size(); k++) {
			NtwrkSvcMntrngLogVO logVO = list.get(k);

			if (logVO.getCreatDt() != null && !logVO.getCreatDt().equals("")) {
				if (logVO.getCreatDt().length() > 18) {
					logVO.setCreatDt(logVO.getCreatDt().substring(0, 19));
				}
			}

			list.set(k, logVO);
			// System.out.println(list.get(k).getCreatDt());
		}
		model.addAttribute("resultList", list);
		int totCnt = ntwrkSvcMntrngService.selectNtwrkSvcMntrngLogListCnt(ntwrkSvcMntrngLogVO);

		ntwrkSvcMntrngLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngLogList");
	}

	// 조회시작시
	@ModelAttribute("searchBgnHour")
	public List<ComCodeVO> searchBgnHour() { return WebUtil.getTimeHH();}
	// 조회종료시
	@ModelAttribute("searchEndHour")
	public List<ComCodeVO> searchEndHour() { return WebUtil.getTimeHH();}

	/**
	 * 네트워크서비스 모니터링로그 정보를 조회한다.
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	@RequestMapping("/utl/sys/nsm/detailNtwrkSvcMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String detailNtwrkSvcMntrngLog(
			NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO,
			ModelMap model) {

		ntwrkSvcMntrngLogVO = ntwrkSvcMntrngService.selectNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);

		if (ntwrkSvcMntrngLogVO.getCreatDt() != null && !ntwrkSvcMntrngLogVO.getCreatDt().equals("")) {
			if (ntwrkSvcMntrngLogVO.getCreatDt().length() > 18) {
				ntwrkSvcMntrngLogVO.setCreatDt(ntwrkSvcMntrngLogVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(ntwrkSvcMntrngLogVO);
		
		return WebUtil.adjustViewName("/utl/sys/nsm/NtwrkSvcMntrngLogDetail");
	}

}