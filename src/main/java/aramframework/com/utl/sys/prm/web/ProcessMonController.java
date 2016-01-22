package aramframework.com.utl.sys.prm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.ComCodeVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.sys.prm.service.ProcessMonService;
import aramframework.com.utl.sys.prm.service.ProcessMonLogVO;
import aramframework.com.utl.sys.prm.service.ProcessMonVO;
import aramframework.com.utl.sys.prm.service.impl.ProcessMonChecker;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - PROCESS모니터링에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 *         - PROCESS모니터링에 조회기능은 목록조회, 상세조회로 구분된다.
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
public class ProcessMonController {

	@Autowired
	private ProcessMonService processMonService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 등록된 PROCESS모니터링 정보를 조회 한다.
	 * 
	 * @param processMonVO
	 */
	@IncludedInfo(name = "프로세스모니터링", order = 9060, gid = 90)
	@RequestMapping("/utl/sys/prm/listProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String listProcessMon(
			@ModelAttribute ProcessMonVO processMonVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		processMonVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", processMonService.selectProcessMonList(processMonVO));

		int totCnt = processMonService.selectProcessMonListCnt(processMonVO);
		processMonVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonList");
	}

	/**
	 * PROCESS모니터링상세 정보를 조회 한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping("/utl/sys/prm/detailProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String detailProcessMon(
			@ModelAttribute ProcessMonVO processMonVO) {

		processMonService.selectProcessMon(processMonVO);

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonDetail");
	}

	/**
	 * PROCESS모니터링 정보를 신규로 등록하는 화면으로 이동한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping(value = "/utl/sys/prm/registProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String registProcessMon(
			@ModelAttribute ProcessMonVO processMonVO) {

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonRegist");
	}

	/**
	 * PROCESS모니터링 정보를 신규로 등록한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping(value = "/utl/sys/prm/insertProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String insertProcessMon(
			@ModelAttribute ProcessMonVO processMonVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(processMonVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		processMonVO.setFrstRegisterId(loginVO.getUniqId());

		processMonService.insertProcessMon(processMonVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/prm/listProcessMon.do");
	}

	/**
	 * 기 등록 된 PROCESS모니터링 정보를 수정하는 화면으로 이동한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping(value = "/utl/sys/prm/editProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String editProcessMon(
			@ModelAttribute ProcessMonVO processMonVO) {

		processMonService.selectProcessMon(processMonVO);

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonEdit");
	}

	/**
	 * 기 등록 된 PROCESS모니터링 정보를 수정 한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping(value = "/utl/sys/prm/updateProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String updateProcessMon(
			@ModelAttribute ProcessMonVO processMonVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(processMonVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		processMonVO.setLastUpdusrId(loginVO.getUniqId());

		processMonService.updateProcessMon(processMonVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/prm/listProcessMon.do");
	}

	/**
	 * 기 등록된 PROCESS모니터링 정보를 삭제한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping(value = "/utl/sys/prm/deleteProcessMon.do")
	@Secured("ROLE_ADMIN")
	public String deleteProcessMon(
			@ModelAttribute ProcessMonVO processMonVO, 
			ModelMap model) {

		processMonService.deleteProcessMon(processMonVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/prm/listProcessMon.do");
	}

	/**
	 * 프로세스의 상태를 조회한다.
	 * 
	 * @param processMonVO
	 */
	@RequestMapping("/utl/sys/prm/selectProcessSttus.do")
	@Secured("ROLE_ADMIN")
	public String selectProcessSttus(
			@ModelAttribute ProcessMonVO processMonVO, 
			ModelMap model) {

		// System.out.println("FileSysNm" + fileSysMntrngVO.getFileSysNm());
		model.addAttribute("processSttus", ProcessMonChecker.getProcessId(processMonVO.getProcessNm()));

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonRegist");
	}

	/**
	 * 프로세스 모니터링로그 정보에 대한 목록을 조회한다.
	 * 
	 * @param processMonLogVO
	 */
	@RequestMapping("/utl/sys/prm/listProcessMonLog.do")
	@Secured("ROLE_ADMIN")
	public String listProcessMonLog(
			@ModelAttribute ProcessMonLogVO processMonLogVO, 
			ModelMap model) {

		// 조회기간설정
		if (processMonLogVO.getSearchBgnDe() != null && processMonLogVO.getSearchEndDe() != null) {
			if (!processMonLogVO.getSearchBgnDe().equals("") && !processMonLogVO.getSearchEndDe().equals("")) {
				processMonLogVO.setSearchBgnDt(processMonLogVO.getSearchBgnDe() + " " + processMonLogVO.getSearchBgnHour());
				processMonLogVO.setSearchEndDt(processMonLogVO.getSearchEndDe() + " " + processMonLogVO.getSearchEndHour());
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		processMonLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", processMonService.selectProcessMonLogList(processMonLogVO));

		int totCnt = processMonService.selectProcessMonLogListCnt(processMonLogVO);
		processMonLogVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonLogList");
	}

	// 조회시작시
	@ModelAttribute("searchBgnHour")
	public List<ComCodeVO> searchBgnHour() { return WebUtil.getTimeHH();}
	// 조회종료시
	@ModelAttribute("searchEndHour")
	public List<ComCodeVO> searchEndHour() { return WebUtil.getTimeHH();}

	/**
	 * 프로세스 모니터링로그 상세정보를 조회한다.
	 * 
	 * @param processMonLogVO
	 */
	@RequestMapping("/utl/sys/prm/detailProcessMonLog.do")
	@Secured("ROLE_ADMIN")
	public String detailProcessMonLog(
			@ModelAttribute ProcessMonLogVO processMonLogVO) {

		processMonService.selectProcessMonLog(processMonLogVO);

		if (processMonLogVO.getCreatDt() != null && !processMonLogVO.getCreatDt().equals("")) {
			if (processMonLogVO.getCreatDt().length() > 18) {
				processMonLogVO.setCreatDt(processMonLogVO.getCreatDt().substring(0, 19));
			}
		}

		return WebUtil.adjustViewName("/utl/sys/prm/ProcessMonLogDetail");
	}

}