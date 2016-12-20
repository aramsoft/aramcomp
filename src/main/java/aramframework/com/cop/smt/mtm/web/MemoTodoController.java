package aramframework.com.cop.smt.mtm.web;

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
import aramframework.com.cop.smt.mtm.domain.MemoTodoVO;
import aramframework.com.cop.smt.mtm.service.MemoTodoService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요 - 메모할일에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용 - 메모할일에 대한 등록, 수정, 삭제, 조회기능을 제공한다. 
 *         - 메모할일의 조회기능은 목록조회, 상세조회, 오늘의 할일조회로 구분된다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MemoTodoController {

	@Autowired 
	private MemoTodoService memoTodoService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 메모할일 정보 중 오늘의 할일 대한 목록을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/listMemoTodoToday.do")
	@Secured("ROLE_USER")
	public String listMemoTodoToday(
			@ModelAttribute MemoTodoVO memoTodoVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoTodoVO.setSearchId(loginVO.getUniqId());

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		String strToday = formatter.format(new java.util.Date());
		memoTodoVO.setSearchBgnDe(strToday + "0000");
		memoTodoVO.setSearchEndDe(strToday + "2359");
		model.addAttribute("resultToday", strToday);

		model.addAttribute("resultList", memoTodoService.selectMemoTodoListToday(memoTodoVO));

		return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoListToday");
	}

	/**
	 * 메모할일 정보에 대한 목록을 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	@IncludedInfo(name = "메모할일관리", order = 4120, gid = 40)
	@RequestMapping("/cop/smt/mtm/listMemoTodo.do")
	@Secured("ROLE_USER")
	public String listMemoTodo(
			@ModelAttribute MemoTodoVO memoTodoVO, 
			ModelMap model) {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoTodoVO.setSearchId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		memoTodoVO.getSearchVO().fillPageInfo(paginationInfo);

		model.addAttribute("resultList", memoTodoService.selectMemoTodoList(memoTodoVO));

		int totCnt = memoTodoService.selectMemoTodoListCnt(memoTodoVO);
		memoTodoVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute(paginationInfo);

		return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoList");
	}

	/**
	 * 메모할일 정보를 조회한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/detailMemoTodo.do")
	@Secured("ROLE_USER")
	public String detailMemoTodo(
			MemoTodoVO memoTodoVO,
			ModelMap model) {
		
		memoTodoVO = memoTodoService.selectMemoTodo(memoTodoVO);

		memoTodoVO.setTodoDe(memoTodoVO.getTodoBeginTime().substring(0, 8));
		memoTodoVO.setTodoBeginHour(memoTodoVO.getTodoBeginTime().substring(8, 10));
		memoTodoVO.setTodoBeginMin(memoTodoVO.getTodoBeginTime().substring(10, 12));
		memoTodoVO.setTodoEndHour(memoTodoVO.getTodoEndTime().substring(8, 10));
		memoTodoVO.setTodoEndMin(memoTodoVO.getTodoEndTime().substring(10, 12));

		model.addAttribute(memoTodoVO);

		return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoDetail");
	}

	// 할일일자(시)
	@ModelAttribute("todoListHour")
	public List<ComCodeVO> todoListHour() {return WebUtil.getTimeHH();}
	// 할일일자(분)
	@ModelAttribute("todoListMin")
	public List<ComCodeVO> todoListMin() {return WebUtil.getTimeMM();}

	/**
	 * 메모할일 정보의 등록페이지로 이동한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/registMemoTodo.do")
	@Secured("ROLE_USER")
	public String registMemoTodo(
			@ModelAttribute MemoTodoVO memoTodoVO) {
		
		// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		memoTodoVO.setTodoDe(formatter.format(new java.util.Date()));
		memoTodoVO.setWrterId(loginVO.getUniqId());
		memoTodoVO.setWrterNm(loginVO.getName());

		return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoRegist");
	}

	/**
	 * 메모할일 정보를 등록한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/insertMemoTodo.do")
	@Secured("ROLE_USER")
	public String insertMemoTodo(
			@ModelAttribute MemoTodoVO memoTodoVO, 
			BindingResult bindingResult, 
			ModelMap model) {

		// 서버 validate 체크
		beanValidator.validate(memoTodoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoRegist");
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoTodoVO.setFrstRegisterId(loginVO.getUniqId());

		memoTodoVO.setTodoBeginTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoBeginHour() + memoTodoVO.getTodoBeginMin());
		memoTodoVO.setTodoEndTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoEndHour() + memoTodoVO.getTodoEndMin());

		memoTodoService.insertMemoTodo(memoTodoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/smt/mtm/listMemoTodo.do");
	}
	
	/**
	 * 메모할일 정보의 수정페이지로 이동한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/editMemoTodo.do")
	@Secured("ROLE_USER")
	public String editMemoTodo(
			MemoTodoVO memoTodoVO,
			ModelMap model) {

		memoTodoVO = memoTodoService.selectMemoTodo(memoTodoVO);

		memoTodoVO.setTodoDe(memoTodoVO.getTodoBeginTime().substring(0, 8));
		memoTodoVO.setTodoBeginHour(memoTodoVO.getTodoBeginTime().substring(8, 10));
		memoTodoVO.setTodoBeginMin(memoTodoVO.getTodoBeginTime().substring(10, 12));
		memoTodoVO.setTodoEndHour(memoTodoVO.getTodoEndTime().substring(8, 10));
		memoTodoVO.setTodoEndMin(memoTodoVO.getTodoEndTime().substring(10, 12));

		model.addAttribute(memoTodoVO);

		return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoEdit");
	}

	/**
	 * 메모할일 정보를 수정한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/updateMemoTodo.do")
	@Secured("ROLE_USER")
	public String updateMemoTodo(
			@ModelAttribute MemoTodoVO memoTodoVO, 
			BindingResult bindingResult, 
			ModelMap model) {
		
		beanValidator.validate(memoTodoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/smt/mtm/MemoTodoEdit");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		memoTodoVO.setLastUpdusrId(loginVO.getUniqId());

		memoTodoVO.setTodoBeginTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoBeginHour() + memoTodoVO.getTodoBeginMin());
		memoTodoVO.setTodoEndTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoEndHour() + memoTodoVO.getTodoEndMin());

		memoTodoService.updateMemoTodo(memoTodoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
		return WebUtil.redirectJsp(model, "/cop/smt/mtm/listMemoTodo.do");
	}

	/**
	 * 메모할일 정보를 삭제한다.
	 * 
	 * @param memoTodoVO
	 */
	@RequestMapping("/cop/smt/mtm/deleteMemoTodo.do")
	@Secured("ROLE_USER")
	public String deleteMemoTodo(
			@ModelAttribute MemoTodoVO memoTodoVO, 
			ModelMap model) {

		memoTodoService.deleteMemoTodo(memoTodoVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
		return WebUtil.redirectJsp(model, "/cop/smt/mtm/listMemoTodo.do");
	}

}