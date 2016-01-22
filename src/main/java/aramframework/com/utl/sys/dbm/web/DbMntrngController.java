package aramframework.com.utl.sys.dbm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.utl.sys.dbm.service.DbMntrngVO;
import aramframework.com.utl.sys.dbm.service.DbMntrngLogVO;
import aramframework.com.utl.sys.dbm.service.DbMntrngService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * DB서비스모니터링관리에 대한 controller 클래스를 정의한다.
 * 
 * DB서비스모니터링관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. 
 * DB서비스모니터링관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class DbMntrngController {

	@Autowired
	private DbMntrngService dbMntrngService;

	@Autowired
	private CmmUseService cmmUseService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * DB서비스모니터링 목록을 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	@IncludedInfo(name = "DB서비스모니터링", order = 9020, gid = 90)
	@RequestMapping("/utl/sys/dbm/listDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String listDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		dbMntrngVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", dbMntrngService.selectDbMntrngList(dbMntrngVO));

		int totCnt = dbMntrngService.selectDbMntrngListCnt(dbMntrngVO);
		dbMntrngVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngList");
	}

	/**
	 * DB서비스모니터링정보을 상세조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	@RequestMapping("/utl/sys/dbm/detailDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String detailDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO) {

		dbMntrngService.selectDbMntrng(dbMntrngVO);

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngDetail");
	}

	/**
	 * 등록화면을 위한 DB서비스모니터링정보을 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	@RequestMapping("/utl/sys/dbm/registDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String registDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO) {

		// DBMS종류코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM048", "COM048_dbmsKind");

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngRegist");
	}

	/**
	 * DB서비스모니터링을 등록한다.
	 * 
	 * @param dbMntrngVO
	 */
	@RequestMapping("/utl/sys/dbm/insertDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String insertDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(dbMntrngVO, bindingResult);
		checkDuplication(dbMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngRegist");
		}
		
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		dbMntrngVO.setFrstRegisterId(loginVO.getUniqId());

		dbMntrngService.insertDbMntrng(dbMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/utl/sys/dbm/listDbMntrng.do");
	}

	private void checkDuplication(DbMntrngVO dbMntrngVO, Errors errors) {

		String dataSourcNm = dbMntrngVO.getDataSourcNm();

		DbMntrngVO exist = null;
		try {
			exist = dbMntrngService.selectDbMntrng(dbMntrngVO);
			if (exist != null) {
				errors.rejectValue("dataSourcNm", "errors.dataSourcNm", new Object[] { dataSourcNm }, "모니터링대상으로 데이타소스명 {0}이 이미 존재합니다.");
				return;
			}
		} catch (Exception se) {
			errors.rejectValue("dataSourcNm", "errors.dataSourcNm", new Object[] { dataSourcNm }, " 모니터링대상으로 데이타소스명 {0}을 중복체크중 시스템에러가 발생했습니다. ");
			return;
		}
	}

	/**
	 * 수정화면을 위한 DB서비스모니터링정보을 조회한다.
	 * 
	 * @param dbMntrngVO
	 */
	@RequestMapping("/utl/sys/dbm/editDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String editDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO) {

		dbMntrngService.selectDbMntrng(dbMntrngVO);

		// DBMS종류코드목록을 코드정보로부터 조회
		cmmUseService.populateCmmCodeList("COM048", "COM048_dbmsKind");

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngEdit");
	}

	/**
	 * DB서비스모니터링을 수정한다.
	 * 
	 * @param dbMntrngVO
	 *            ModelMap
	 */
	@RequestMapping("/utl/sys/dbm/updateDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String updateDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		beanValidator.validate(dbMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngEdit");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		dbMntrngVO.setLastUpdusrId(loginVO.getUniqId());

		dbMntrngService.updateDbMntrng(dbMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/utl/sys/dbm/listDbMntrng.do");
	}

	/**
	 * DB서비스모니터링을 삭제한다.
	 * 
	 * @param dbMntrngVO
	 */
	@RequestMapping("/utl/sys/dbm/deleteDbMntrng.do")
	@Secured("ROLE_ADMIN")
	public String deleteDbMntrng(
			@ModelAttribute DbMntrngVO dbMntrngVO, 
			ModelMap model) {

		dbMntrngService.deleteDbMntrng(dbMntrngVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/utl/sys/dbm/listDbMntrng.do");
	}

	/**
	 * DB서비스모니터링로그 목록을 조회한다.
	 * 
	 * @param dbMntrngLogVO
	 */
	@RequestMapping("/utl/sys/dbm/listDbMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String listDbMntrngLog(
			@ModelAttribute DbMntrngLogVO dbMntrngLogVO, 
			ModelMap model) {

		PaginationInfo paginationInfo = new PaginationInfo();
		dbMntrngLogVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", dbMntrngService.selectDbMntrngLogList(dbMntrngLogVO));

		int totCnt = dbMntrngService.selectDbMntrngLogListCnt(dbMntrngLogVO);
		dbMntrngLogVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngLogList");
	}

	/**
	 * DB서비스모니터링로그정보을 상세조회한다.
	 * 
	 * @param dbMntrngLogVO
	 */

	@RequestMapping("/utl/sys/dbm/detailDbMntrngLog.do")
	@Secured("ROLE_ADMIN")
	public String detailDbMntrngLog(
			@ModelAttribute DbMntrngLogVO dbMntrngLogVO) {

		dbMntrngService.selectDbMntrngLog(dbMntrngLogVO);

		return WebUtil.adjustViewName("/utl/sys/dbm/DbMntrngLogDetail");
	}

}