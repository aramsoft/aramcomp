package aramframework.com.utl.sys.fsm.web;

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
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngLogVO;
import aramframework.com.utl.sys.fsm.domain.FileSysMntrngVO;
import aramframework.com.utl.sys.fsm.schedule.FileSysChecker;
import aramframework.com.utl.sys.fsm.service.FileSysMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 파일시스템 모니터링대상에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class FileSysMntrngController {

	@Autowired
	private FileSysMntrngService fileSysMntrngService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 파일시스템 모니터링대상 정보에 대한 목록을 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@IncludedInfo(name = "파일시스템모니터링", order = 9030, gid = 90)
	@RequestMapping("/utl/sys/fsm/listFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listFileSysMntrng(
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		fileSysMntrngVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", fileSysMntrngService.selectFileSysMntrngList(fileSysMntrngVO));
		int totCnt = fileSysMntrngService.selectFileSysMntrngListCnt(fileSysMntrngVO);

		fileSysMntrngVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngList");
	}

	/**
	 * 파일시스템 모니터링대상 정보를 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/detailFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO,
			ModelMap model) {
		
		fileSysMntrngVO = fileSysMntrngService.selectFileSysMntrng(fileSysMntrngVO);

		if (fileSysMntrngVO.getCreatDt() != null && !fileSysMntrngVO.getCreatDt().equals("")) {
			if (fileSysMntrngVO.getCreatDt().length() > 18) {
				fileSysMntrngVO.setCreatDt(fileSysMntrngVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(fileSysMntrngVO);
		
		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngDetail");
	}

	/**
	 * 파일시스템 모니터링 대상 정보의 등록페이지로 이동한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/registFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String registFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO) {
		
		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngRegist");
	}

	/**
	 * 파일시스템 모니터링대상 정보를 등록한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/insertFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String insertFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(fileSysMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		fileSysMntrngVO.setFrstRegisterId(loginVO.getUniqId());

		fileSysMntrngService.insertFileSysMntrng(fileSysMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/fsm/listFileSysMntrng.do");
	}

	/**
	 * 파일시스템 모니터링 대상 정보의 수정페이지로 이동한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/editFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String editFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO,
			ModelMap model) {

		fileSysMntrngVO = fileSysMntrngService.selectFileSysMntrng(fileSysMntrngVO);

		if (fileSysMntrngVO.getCreatDt() != null && !fileSysMntrngVO.getCreatDt().equals("")) {
			if (fileSysMntrngVO.getCreatDt().length() > 18) {
				fileSysMntrngVO.setCreatDt(fileSysMntrngVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(fileSysMntrngVO);
		
		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngEdit");
	}

	/**
	 * 파일시스템 모니터링대상 정보를 수정한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/updateFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String updateFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(fileSysMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		fileSysMntrngVO.setLastUpdusrId(loginVO.getUniqId());

		fileSysMntrngService.updateFileSysMntrng(fileSysMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/fsm/listFileSysMntrng.do");
	}

	/**
	 * 파일시스템 모니터링대상 정보를 삭제한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/deleteFileSysMntrng.do")
	@Secured("ROLE_ADMIN")
	public String deleteFileSysMntrng(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO, 
			ModelMap model) {

		fileSysMntrngService.deleteFileSysMntrng(fileSysMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/fsm/listFileSysMntrng.do");
	}

	/**
	 * 파일시스템의 크기를 조회한다.
	 * 
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/getFileSysMg.do")
	@Secured("ROLE_ADMIN")
	public String getFileSysMg(
			@ModelAttribute FileSysMntrngVO fileSysMntrngVO, 
			ModelMap model) {

		// System.out.println("FileSysNm" + fileSysMntrngVO.getFileSysNm());

		int totalSpaceFileSys = 0;
		try {
			totalSpaceFileSys = FileSysChecker.totalSpaceGb(fileSysMntrngVO.getFileSysNm());
		} catch (Exception e) {
			model.addAttribute("notApplicableFileSys", "true");
		}

		model.addAttribute("fileSysMgValue", totalSpaceFileSys);
		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngRegist");
	}

	/**
	 * 파일시스템 모니터링로그 정보에 대한 목록을 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	@RequestMapping("/utl/sys/fsm/listFileSysMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String listFileSysMntrngLog(
			@ModelAttribute FileSysMntrngLogVO fileSysMntrngLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		fileSysMntrngLogVO.fillPageInfo(paginationInfo);

		// 조회기간설정
		if (fileSysMntrngLogVO.getSearchBgnDe() != null && fileSysMntrngLogVO.getSearchEndDe() != null) {
			if (!fileSysMntrngLogVO.getSearchBgnDe().equals("") && !fileSysMntrngLogVO.getSearchEndDe().equals("")) {
				fileSysMntrngLogVO.setSearchBgnDt(fileSysMntrngLogVO.getSearchBgnDe() + " " + fileSysMntrngLogVO.getSearchBgnHour());
				fileSysMntrngLogVO.setSearchEndDt(fileSysMntrngLogVO.getSearchEndDe() + " " + fileSysMntrngLogVO.getSearchEndHour());
			}
		}

		List<FileSysMntrngLogVO> list = fileSysMntrngService.selectFileSysMntrngLogList(fileSysMntrngLogVO);
		for (int k = 0; k < list.size(); k++) {
			FileSysMntrngLogVO logVO = list.get(k);

			if (logVO.getCreatDt() != null && !logVO.getCreatDt().equals("")) {
				if (logVO.getCreatDt().length() > 18) {
					logVO.setCreatDt(logVO.getCreatDt().substring(0, 19));
				}
			}

			list.set(k, logVO);
			// System.out.println(list.get(k).getCreatDt());
		}
		model.addAttribute("resultList", list);
		int totCnt = fileSysMntrngService.selectFileSysMntrngLogListCnt(fileSysMntrngLogVO);

		fileSysMntrngLogVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngLogList");
	}

	// 조회시작시
	@ModelAttribute("searchBgnHour")
	public List<ComCodeVO> searchBgnHour() { return WebUtil.getTimeHH();}
	// 조회종료시
	@ModelAttribute("searchEndHour")
	public List<ComCodeVO> searchEndHour() { return WebUtil.getTimeHH();}

	/**
	 * 파일시스템 모니터링로그 정보를 조회한다.
	 * 
	 * @param fileSysMntrngLogVO
	 */
	@RequestMapping("/utl/sys/fsm/detailFileSysMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String detailFileSysMntrngLog(
			@ModelAttribute SearchVO searchVO,
			@ModelAttribute FileSysMntrngLogVO fileSysMntrngLogVO,
			ModelMap model) {
		
		fileSysMntrngLogVO = fileSysMntrngService.selectFileSysMntrngLog(fileSysMntrngLogVO);

		if (fileSysMntrngLogVO.getCreatDt() != null && !fileSysMntrngLogVO.getCreatDt().equals("")) {
			if (fileSysMntrngLogVO.getCreatDt().length() > 18) {
				fileSysMntrngLogVO.setCreatDt(fileSysMntrngLogVO.getCreatDt().substring(0, 19));
			}
		}
		
		model.addAttribute(fileSysMntrngLogVO);
		
		return WebUtil.adjustViewName("/utl/sys/fsm/FileSysMntrngLogDetail");
	}

}