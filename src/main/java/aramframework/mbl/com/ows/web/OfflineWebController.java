package aramframework.mbl.com.ows.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.mbl.com.ows.service.OfflineWebService;
import aramframework.mbl.com.ows.service.OfflineWebVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * -오프라인 서비스에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 오프라인 서비스 등록, 수정, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class OfflineWebController {

	@Resource(name = "offlineWebService")
	private OfflineWebService offlineWebService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 오프라인 서비스 페이지로 이동
	 * 
	 */
	@RequestMapping(value="/mbl/com/ows/listMobileOfflineWeb.mdo")
	public String listMobileOfflineWeb() {
        return "aramframework/mbl/com/ows/MobileOfflineWebList";
	}	
	
	/**
	 * 오프라인 서비스 글 목록 조회 
	 * 
	 */
	@RequestMapping(value="/mbl/com/ows/listMobileOfflineWebJson.mdo")
	public ModelAndView listMobileOfflineWebJson(
			@ModelAttribute OfflineWebVO offlineWebVO) {
				
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("offlineList", offlineWebService.selectOfflineWebList(offlineWebVO));
		modelAndView.addObject("success", true);
		
		return modelAndView;
	}
	
	/**
	 * 오프라인 상세조회 페이지 이동
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/goMobileOfflineWeb.mdo")
	public String goMobileOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO,			
			ModelMap model) {
		
		model.addAttribute("sn", offlineWebVO.getSn());
		return "aramframework/mbl/com/ows/MobileOfflineWeb";
	}	
	
	/**
	 * 오프라인 서비스 상세 글 조회
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/goMobileOfflineWebJson.mdo")
	public ModelAndView goMobileOfflineWebJson(
			@ModelAttribute OfflineWebVO offlineWebVO) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 1. 조회 결과를 ModelAndView에 할당한다.
		offlineWebService.selectOfflineWeb(offlineWebVO);		
		modelAndView.addObject("offlineWebVO", offlineWebVO);		
		
		modelAndView.addObject("success", true);		
		return modelAndView;
	}	
	
	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
	/**
	 * 오프라인 목록 조회 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param offlineWebVO
     */
	@RequestMapping(value="/mbl/com/ows/listOfflineWeb.mdo")
	@Secured("ROLE_ADMIN")
	public String listOfflineWeb (
			@ModelAttribute OfflineWebVO offlineWebVO,			
			ModelMap model) {
		
    	PaginationInfo paginationInfo = new PaginationInfo();
    	offlineWebVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("resultList",  offlineWebService.selectOfflineWebList(offlineWebVO));
        
        int totCnt = offlineWebService.selectOfflineWebListCnt(offlineWebVO);
        offlineWebVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "aramframework/mbl/com/ows/OfflineWebList";
	}

	/**
	 * 오프라인 상세정보 조회 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/detailOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String detailOfflineWeb(			
			@ModelAttribute OfflineWebVO offlineWebVO) {
		
		offlineWebService.selectOfflineWeb(offlineWebVO);

		return "aramframework/mbl/com/ows/OfflineWebDetail";
	}
	
	/**
	 * 오프라인 서비스 등록 화면으로 이동한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/registOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String registOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO) {
		
        return "aramframework/mbl/com/ows/OfflineWebRegist";
	}
	
	/**
	 * 오프라인 서비스 등록 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/insertOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String insertOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO,
			BindingResult bindingResult,
			ModelMap model) {
		
    	beanValidator.validate(offlineWebVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "aramframework/mbl/com/ows/OfflineWebRegist";						
		}

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	offlineWebVO.setMberId(loginVO.getId());	
    		
    	offlineWebService.insertOfflineWeb(offlineWebVO);

    	return "forward:/mbl/com/ows/listOfflineWeb.mdo";
	}
	
	/**
	 * 오프라인 서비스 수정 화면으로 이동한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/editOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String editOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO) {
		
		offlineWebService.selectOfflineWeb(offlineWebVO);
		
        return "aramframework/mbl/com/ows/OfflineWebEdit";
	}

	/**
	 * 오프라인 서비스 수정 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/updateOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String updateOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO,
            BindingResult bindingResult,
            ModelMap model) {
		
    	beanValidator.validate(offlineWebVO, bindingResult);
 		if(bindingResult.hasErrors()){			
			return "aramframework/mbl/com/ows/OfflineWebEdit";						
		}

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	offlineWebVO.setMberId(loginVO.getId());	

    	offlineWebService.updateOfflineWeb(offlineWebVO);

		return "forward:/mbl/com/ows/listOfflineWeb.mdo";
	}

	/**
	 * 오프라인 서비스 삭제 Service interface 호출 및 결과를 반환한다.
	 * 
	 * @param offlineWebVO
	 */
	@RequestMapping(value="/mbl/com/ows/deleteOfflineWeb.mdo")
	@Secured("ROLE_USER")
	public String deleteOfflineWeb(
			@ModelAttribute OfflineWebVO offlineWebVO) {
		
		offlineWebService.deleteOfflineWeb(offlineWebVO);

		return "forward:/mbl/com/ows/listOfflineWeb.mdo";
	}
	
}
