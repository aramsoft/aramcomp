package aramframework.mbl.uss.ion.ecc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.ion.ecc.domain.EventCmpgnVO;
import aramframework.com.uss.ion.ecc.service.EventCmpgnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행사/이벤트/캠페인을 처리하는 Controller Class 구현
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
public class MblEventCmpgnController {
	 
	@Autowired
	private EventCmpgnService eventCmpgnService;
	 
	@Autowired
	private CmmUseService cmmUseService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행사/이벤트/캠페인 목록 팝업을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/listEventCmpgnPopup.mdo")
	public String listEventCmpgnPopup(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
    		ModelMap model) {
		
    	PaginationInfo paginationInfo = new PaginationInfo();
    	eventCmpgnVO.getSearchVO().fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList", eventCmpgnService.selectEventCmpgnList(eventCmpgnVO));
        int totCnt = (Integer)eventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);
 
        eventCmpgnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute(paginationInfo);
        
		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");

		return "aramframework/mbl/uss/ion/ecc/EventCmpgnListPopup";
	}

	/**
	 * 행사/이벤트/캠페인 목록을 조회한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/listEventCmpgn.mdo")
	public String listEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
    		ModelMap model) {
		
    	PaginationInfo paginationInfo = new PaginationInfo();
    	eventCmpgnVO.getSearchVO().fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList", eventCmpgnService.selectEventCmpgnList(eventCmpgnVO));
        int totCnt = (Integer)eventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);
   
        eventCmpgnVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute(paginationInfo);
        
		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");
    	
		return "aramframework/mbl/uss/ion/ecc/EventCmpgnList";
	}
	
	/**
	 * 행사/이벤트/캠페인 상세조회 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/detailEventCmpgn.mdo")
	public String detailEventCmpgn(
			EventCmpgnVO eventCmpgnVO,
	   		ModelMap model) {

		model.addAttribute(eventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO));

		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");
    	
		return "aramframework/mbl/uss/ion/ecc/EventCmpgnDetail"; 	
	}

	/**
	 * 행사/이벤트/캠페인을 등록화면.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/registEventCmpgn.mdo")
	@Secured("ROLE_USER")
	public String registEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			ModelMap model) {
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String)loginVO.getUserSe());
		
		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");
    	
		return "aramframework/mbl/uss/ion/ecc/EventCmpgnRegist"; 
	}
	
	/**
	 * 행사/이벤트/캠페인을 등록 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/insertEventCmpgn.mdo")
	@Secured("ROLE_USER")
	public String insertEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			BindingResult bindingResult,
			ModelMap model) {
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String)loginVO.getUserSe());
		
		//서버  validate 체크
        beanValidator.validate(eventCmpgnVO, bindingResult);
		if(bindingResult.hasErrors()){
	        return "aramframework/mbl/uss/ion/ecc/EventCmpgnRegist";
		} 
		//아이디 설정
		eventCmpgnVO.setFrstRegisterId(loginVO.getUniqId());
		
    	eventCmpgnService.insertEventCmpgn(eventCmpgnVO);

    	return "redirect:/uss/ion/ecc/listEventCmpgn.mdo"; 
	}
	
	/**
	 * 행사/이벤트/캠페인을 수정화면.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/editEventCmpgn.mdo")
	@Secured("ROLE_USER")
	public String editEventCmpgn(
			EventCmpgnVO eventCmpgnVO, 
    		ModelMap model) {
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String)loginVO.getUserSe());
		
		// 공통코드 행사유형 조회
		cmmUseService.populateCmmCodeList("COM035", "COM035_eventTy");
    	
		model.addAttribute(eventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO));
        
    	return "aramframework/mbl/uss/ion/ecc/EventCmpgnEdit"; 	
	}
	
	/**
	 * 행사/이벤트/캠페인을 수정 한다.
	 * 
	 * @param eventCmpgnVO
	 */
	@RequestMapping(value="/uss/ion/ecc/updateEventCmpgn.mdo")
	@Secured("ROLE_USER")
	public String updateEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
			BindingResult bindingResult,
    		ModelMap model) {
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("SUserSe", (String)loginVO.getUserSe());
		
		//서버  validate 체크
        beanValidator.validate(eventCmpgnVO, bindingResult);
		if (bindingResult.hasErrors()){
	        return "aramframework/mbl/uss/ion/ecc/EventCmpgnEdit";
		}
		//아이디 설정
		eventCmpgnVO.setLastUpdusrId(loginVO.getUniqId());
		
    	eventCmpgnService.updateEventCmpgn(eventCmpgnVO);

    	return "redirect:/uss/ion/ecc/listEventCmpgn.mdo"; 
	}
	
    /**
     * 행사/이벤트/캠페인 를 삭제처리한다.     
     *            
	 * @param eventCmpgnVO
     */
	@RequestMapping(value="/uss/ion/ecc/deleteEventCmpgn.mdo")
	@Secured("ROLE_USER")
    public String deleteEventCmpgn(
			@ModelAttribute EventCmpgnVO eventCmpgnVO, 
    		ModelMap model) {
    	
		eventCmpgnService.deleteEventCmpgn(eventCmpgnVO);

		return  "redirect:/uss/ion/ecc/listEventCmpgn.mdo";
    }
    
}
