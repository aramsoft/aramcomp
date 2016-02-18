package aramframework.com.cop.smt.djm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.domain.SearchVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.service.FileMngUtil;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.smt.djm.domain.DeptJobBxVO;
import aramframework.com.cop.smt.djm.domain.DeptJobVO;
import aramframework.com.cop.smt.djm.service.DeptJobService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 부서업무에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class DeptJobController {

	@Autowired 
	private DeptJobService deptJobService;

	// 첨부파일 관련
	@Autowired
	private FileMngUtil fileUtil;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 담당자 정보에 대한 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/smt/djm/listChargerPopup.do")
	public String listCharger(
			@ModelAttribute SearchVO searchVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		searchVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptJobService.selectChargerList(searchVO));
		int totCnt = deptJobService.selectChargerListCnt(searchVO);

		searchVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/djm/ChargerListPopup");
	}

	/**
	 * 부서 정보에 대한 목록을 조회한다.
	 * 
	 * @param searchVO
	 */
	@RequestMapping("/cop/smt/djm/listDeptPopup.do")
	public String listDept(
			@ModelAttribute SearchVO searchVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		searchVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptJobService.selectDeptList(searchVO));
		int totCnt = deptJobService.selectDeptListCnt(searchVO);

		searchVO.setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/djm/DeptListPopup");
	}

	/**
	 * 부서업무함 정보에 대한 목록을 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	@IncludedInfo(name = "부서업무함관리", order = 4100, gid = 40)
	@RequestMapping("/cop/smt/djm/listDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String listDeptJobBx(
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			@RequestParam(value="PopFlag", required=false) String popFlag,
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		deptJobBxVO.getSearchVO().fillPageInfo(paginationInfo);

		List<EgovMap> list = deptJobService.selectDeptJobBxList(deptJobBxVO);
		model.addAttribute("resultList", list);
		model.addAttribute("resultNum", list.size());
		int totCnt = deptJobService.selectDeptJobBxListCnt(deptJobBxVO);

		deptJobBxVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);

		String returnUrl;
		if ("Y".equals(popFlag)) {
			returnUrl = WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxListPopup");
		} else {
			returnUrl = WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxList");
		}
		return returnUrl;
	}

	/**
	 * 부서업무함 정보를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	 @RequestMapping("/cop/smt/djm/detailDeptJobBx.do")
	 public String detailDeptJobBx(
			DeptJobBxVO deptJobBxVO,
			ModelMap model) {
	
		 model.addAttribute(deptJobService.selectDeptJobBx(deptJobBxVO));

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxDetail");
	}

	/**
	 * 부서업무함 정보의 등록화면으로 이동한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/registDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String registDeptJobBx(
			@ModelAttribute DeptJobBxVO deptJobBxVO) {
		
		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxRegist");
	}

	/**
	 * 부서업무함 등록시 표시순서를 조회한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/getDeptJobBxOrdr.do")
	@Secured("ROLE_ADMIN")
	public String getDeptJobBxOrdr(
			HttpServletRequest request, 
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			ModelMap model) {

		// deptJobBxVO.setIndictOrdr(deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()) + 1);
		model.addAttribute("indictOrdrValue", deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()) + 1);

		if (request.getHeader("Referer").indexOf("insertDeptJobBx.do") == -1) {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxEdit");
		} else {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxRegist");
		}	
	}

	/**
	 * 부서업무함 정보를 등록한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/insertDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String insertDeptJobBx(
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(deptJobBxVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		deptJobBxVO.setFrstRegisterId(loginVO.getUniqId());

		// 부서내 부서업무함명 중복체크
		if (deptJobService.selectDeptJobBxCheck(deptJobBxVO) > 0) {
			model.addAttribute("deptJobBxNmDuplicated", "true");
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxRegist");
		} 
		
		deptJobService.insertDeptJobBx(deptJobBxVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJobBx.do");
	}

	/**
	 * 부서업무함 정보의 수정화면으로 이동한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/editDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String editDeptJobBx(
			DeptJobBxVO deptJobBxVO, 
			ModelMap model) {

		model.addAttribute(deptJobService.selectDeptJobBx(deptJobBxVO));
		model.addAttribute("indictOrdrValue", deptJobBxVO.getIndictOrdr());

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxEdit");
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String updateDeptJobBx(
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(deptJobBxVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobBxEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		deptJobBxVO.setLastUpdusrId(loginVO.getUniqId());

		deptJobService.updateDeptJobBx(deptJobBxVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJobBx.do");
	}

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJobBxOrdr.do")
	@Secured("ROLE_ADMIN")
	public String updateDeptJobBxOrdr(
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			ModelMap model) {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		deptJobBxVO.setLastUpdusrId(loginVO.getUniqId());

		boolean changed = deptJobService.updateDeptJobBxOrdr(deptJobBxVO);
		if (!changed) {
			model.addAttribute("indictOrdrChanged", "false");
		}

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJobBx.do");
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/deleteDeptJobBx.do")
	@Secured("ROLE_ADMIN")
	public String deleteDeptJobBx(
			@ModelAttribute DeptJobBxVO deptJobBxVO, 
			ModelMap model) {

		deptJobService.deleteDeptJobBx(deptJobBxVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJobBx.do");
	}

	/**
	 * 부서업무 정보에 대한 목록을 조회한다.
	 * 
	 * @param deptJobVO
	 */
	@IncludedInfo(name = "부서업무정보", order = 4101, gid = 40)
	@RequestMapping("/cop/smt/djm/listDeptJob.do")
	@Secured("ROLE_USER")
	public String listDeptJob(
			@ModelAttribute DeptJobVO deptJobVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if (deptJobVO.getSearchDeptId() == null || deptJobVO.getSearchDeptId().equals("")) {
			deptJobVO.setSearchDeptId(loginVO.getOrgnztId());
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		deptJobVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", deptJobService.selectDeptJobList(deptJobVO));
		int totCnt = deptJobService.selectDeptJobListCnt(deptJobVO);

		deptJobVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);
		model.addAttribute("resultBxList", deptJobService.selectDeptJobBxListAll());

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobList");
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/detailDeptJob.do")
	@Secured("ROLE_USER")
	public String detailDeptJob(
			DeptJobVO deptJobVO, 
			ModelMap model) {
		
		model.addAttribute(deptJobService.selectDeptJob(deptJobVO));

		// 공통코드 우선순위 조회
		model.addAttribute("priort", cmmUseService.selectCmmCodeList("COM059"));

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobDetail");
	}

	/**
	 * 부서업무 정보의 등록화면으로 이동한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/registDeptJob.do")
	@Secured("ROLE_USER")
	public String registDeptJob(
			@ModelAttribute DeptJobVO deptJobVO) {

		deptJobVO.setDeptId(deptJobVO.getSearchDeptId());
		deptJobVO.setDeptNm(deptJobService.selectDept(deptJobVO.getSearchDeptId()));
		deptJobVO.setDeptJobBxId(deptJobVO.getSearchDeptJobBxId());

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobRegist");
	}

	/**
	 * 부서업무 정보를 등록한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/insertDeptJob.do")
	@Secured("ROLE_USER")
	public String insertDeptJob(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute DeptJobVO deptJobVO, 
			BindingResult bindingResult,
			ModelMap model) 
	throws Exception {

		// 서버 validate 체크
		beanValidator.validate(deptJobVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobRegist");
		}

		// 첨부파일 관련 첨부파일ID 생성
		deptJobVO.setAtchFileId(fileUtil.insertMultiFile(multiRequest, "DSCH_"));

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		deptJobVO.setFrstRegisterId(loginVO.getUniqId());

		deptJobService.insertDeptJob(deptJobVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJob.do");
	}

	/**
	 * 부서업무 정보의 수정화면으로 이동한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/editDeptJob.do")
	@Secured("ROLE_USER")
	public String editDeptJob(
			DeptJobVO deptJobVO,
			ModelMap model) {

		model.addAttribute(deptJobService.selectDeptJob(deptJobVO));

		return WebUtil.adjustViewName("/cop/smt/djm/DeptJobEdit");
	}

	/**
	 * 부서업무 정보를 수정한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJob.do")
	@Secured("ROLE_USER")
	public String updateDeptJob(
			MultipartHttpServletRequest multiRequest, 
			@ModelAttribute DeptJobVO deptJobVO,
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(deptJobVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/djm/DeptJobEdit");
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = deptJobVO.getAtchFileId();
		deptJobVO.setAtchFileId(fileUtil.updateMultiFile(multiRequest, "DSCH_", atchFileId));

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		deptJobVO.setLastUpdusrId(loginVO.getUniqId());

		deptJobService.updateDeptJob(deptJobVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJob.do");
	}

	/**
	 * 부서업무 정보를 삭제한다.
	 * 
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/deleteDeptJob.do")
	@Secured("ROLE_USER")
	public String deleteDeptJob(
			@ModelAttribute DeptJobVO deptJobVO, 
			ModelMap model) {

		deptJobService.deleteDeptJob(deptJobVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/djm/listDeptJob.do");
	}

}