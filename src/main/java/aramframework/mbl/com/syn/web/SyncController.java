package aramframework.mbl.com.syn.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.mbl.com.syn.service.SyncService;
import aramframework.mbl.com.syn.service.SyncVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * -동기화 서비스에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 동기화 등록, 수정, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class SyncController {

	@Resource(name = "syncService")
	private SyncService syncService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 동기화 서비스 페이지로 이동
	 * 
	 */
	@RequestMapping(value="/mbl/com/syn/listMobileSync.mdo")
	public String listMobileSync(ModelMap model) {
		return "aramframework/mbl/com/syn/MobileSyncList";
	}	
	
	/**
	 * 동기화 서비스 글 목록 조회 
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/listMobileSyncActor.mdo")
	public ModelAndView listMobileSyncActor(
			@ModelAttribute("syncVO") SyncVO syncVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("synList", syncService.selectSyncList(syncVO));
		modelAndView.addObject("success", true);
		
		return modelAndView;
	}
	
	/**
	 * 동기화 상세조회로 페이지 이동
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/detailMobileSync.mdo")
	public String detailMobileSync(
			@ModelAttribute SyncVO syncVO,			
			ModelMap model) {

		model.addAttribute("sn", syncVO.getSn());
		return "aramframework/mbl/com/syn/MobileSync";
	}	
	
	/**
	 * 동기화 서비스 상세 글 조회
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/detailMobileSyncJson.mdo")
	public ModelAndView detailMobileSyncJson(
			@ModelAttribute SyncVO syncVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 1. 동기화 서비스 목록을 조회 한다.
		syncService.selectSync(syncVO);

		// 2. 조회 결과를 ModelAndView에 할당한다.
		modelAndView.addObject("syncVO", syncVO);
		
		return modelAndView;
	}
	
	/**
	 * 동기화 서비스 글 등록 페이지로 이동

	 */
	@RequestMapping(value="/mbl/com/syn/registMobileSync.mdo")
	@Secured("ROLE_USER")
	public String goMobileSyncInsert() {
		return "aramframework/mbl/com/syn/MobileSyncInsert";
	}	
	
	/**
	 * 동기화 서비스 글 등록
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/insertMobileSyncJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView insertMobileSyncJson(
			@ModelAttribute SyncVO syncVO)  {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	
		
		syncService.insertSync(syncVO);
		modelAndView.addObject("success", true);
		
        return modelAndView;
	}
	
	/**
	 * 동기화 서비스 글 수정 페이지로 이동
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/editMobileSync.mdo")
	@Secured("ROLE_USER")
	public String editMobileSync(
			@ModelAttribute SyncVO syncVO,			
			ModelMap model) {

		model.addAttribute("sn", syncVO.getSn());
		
		return "aramframework/mbl/com/syn/MobileSyncUpdate";
	}	
	
	/**
	 * 동기화 서비스 글 수정
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/updateMobileSyncJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView updateMobileSyncJson(
			@ModelAttribute SyncVO syncVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	
		
		syncService.updateSync(syncVO);
		modelAndView.addObject("success", true);
		
		return modelAndView;
	}
	
	/**
	 * 동기화 서비스 글 삭제
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/deleteMobileSync.mdo")
	@Secured("ROLE_USER")
	public String deleteMobileSync(
			@ModelAttribute SyncVO syncVO) {
		
		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	
		
    	syncService.deleteSync(syncVO);
		
        return "redirect:/mbl/com/syn/listMobileSync.mdo";
	}	
	
	/**
	 * 동기화 서비스 글 '동기화' 를 실행한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/executeMobileSyncJson.mdo")
	@Secured("ROLE_USER")
	public ModelAndView executeMobileSyncJson(
			@RequestParam String localData,
			@ModelAttribute SyncVO syncVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	
		
		syncService.executeSync(syncVO, localData); 
		modelAndView.addObject("success", true);
		
        return modelAndView;
	}	
	
	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
	/**
	 * 동기화 목록 조회 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param syncVO
     */
	@IncludedInfo(name = "동기화목록 정보", order = 10060, gid = 100)
	@RequestMapping(value="/mbl/com/syn/listSync.do")
	@Secured("ROLE_USER")
	public String listSync (			
			@ModelAttribute SyncVO syncVO,			
			ModelMap model) {
		
    	PaginationInfo paginationInfo = new PaginationInfo();
    	syncVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList", syncService.selectSyncList(syncVO));
        
        int totCnt = syncService.selectSyncListCnt(syncVO);
        syncVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/syn/SyncList");
 	}
	
	/**
	 * 동기화 상세정보 조회 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/detailSync.do")
	@Secured("ROLE_USER")
	public String detailSync(			
			@ModelAttribute SyncVO syncVO) {
		
		syncService.selectSync(syncVO);
		
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/syn/SyncDetail");
	}
	
	/**
	 * 동기화 서비스 등록 화면으로 이동한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/registSync.do")
	@Secured("ROLE_USER")
	public String registSync(
			@ModelAttribute SyncVO syncVO) {
		
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/syn/SyncRegist");
 	}
	
	/**
	 * 동기화 서비스 등록 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/insertSync.do")
	@Secured("ROLE_USER")
	public String insertSync(
			@ModelAttribute SyncVO syncVO,			
			BindingResult bindingResult,
			ModelMap model) {
		
    	beanValidator.validate(syncVO, bindingResult);
		if(bindingResult.hasErrors()){			
			RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
			return WebUtil.adjustViewName("/com/syn/SyncRegist");
		}

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	

    	syncService.insertSync(syncVO);
    	
		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/syn/listSync.do");
	}
	
	/**
	 * 동기화 서비스 수정 화면으로 이동한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/editSync.do")
	@Secured("ROLE_USER")
	public String editSync(
			@ModelAttribute SyncVO syncVO) {
		
		syncService.selectSync(syncVO);
		
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/syn/SyncEdit");
	}

	/**
	 * 동기화 서비스 수정 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/updateSync.do")
	@Secured("ROLE_USER")
	public String updateSync(
			@ModelAttribute SyncVO syncVO,			
            BindingResult bindingResult,
            ModelMap model) {
		
    	beanValidator.validate(syncVO, bindingResult);
		if(bindingResult.hasErrors()){			
			RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
			return WebUtil.adjustViewName("/com/syn/SyncEdit");
		}

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	syncVO.setMberId(loginVO.getId());	

    	syncService.updateSync(syncVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/mbl/com/syn/listSync.do");
	}
	
	/**
	 * 동기화 서비스 삭제 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param syncVO
	 */
	@RequestMapping(value="/mbl/com/syn/deleteSync.do")
	@Secured("ROLE_USER")
	public String deleteSync(
			@ModelAttribute SyncVO syncVO,			
            ModelMap model) {
		
		syncService.deleteSync(syncVO);
		
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/mbl/com/syn/listSync.do");
	}
	
}
