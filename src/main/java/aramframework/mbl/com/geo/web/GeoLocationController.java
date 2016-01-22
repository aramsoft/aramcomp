package aramframework.mbl.com.geo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.mbl.com.geo.service.GeoLocationService;
import aramframework.mbl.com.geo.service.GeoLocationVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * -  건물 위치정보에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * -  건물 위치정보에 대한 등록, 수정, 삭제, 조회, 상세조회 요청 사항을 Service와 매핑 처리한다.
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
public class GeoLocationController {

	@Autowired
    private GeoLocationService geoLocationService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 현재 위치정보 조회 화면(공공지도)으로 이동한다.
     * 
     */
    @RequestMapping(value="/mbl/com/geo/goMobileGeoLocationPublic.mdo")
    public String goMobileGeoLocationPublic() {
        return "aramframework/mbl/com/geo/MobileGeoLocationPublicInqire";
    }
    
    /**
     * 현재 위치정보 조회 화면(민간지도)으로 이동한다.
     * 
     */
    @RequestMapping(value="/mbl/com/geo/goMobileGeoLocationPrivate.mdo")
    public String goMobileGeoLocationPrivate() {
        return "aramframework/mbl/com/geo/MobileGeoLocationPrivateInqire";
    }

    /**
     * 주변 건물 위치정보 목록 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping("/mbl/com/geo/listMobileBuildingLocationInfoJson.mdo")
    public ModelAndView listMobileBuildingLocationInfoJson(
     		@ModelAttribute GeoLocationVO geoLocationVO) {
    	
        ModelAndView modelAndView = new ModelAndView("jsonView");

        geoLocationVO.setSearchUseYn("Y");
        modelAndView.addObject("resultList", geoLocationService.selectBuildingLocationInfoList(geoLocationVO));
         
        return modelAndView;
    }
    
	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
    /**
     * 건물 위치정보 목록 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
	@IncludedInfo(name = "건물위치정보", order = 10000, gid = 100)
    @RequestMapping("/mbl/com/geo/listBuildingLocationInfo.do")
	@Secured("ROLE_ADMIN")
    public String listBuildingLocationInfo(
    		@ModelAttribute GeoLocationVO geoLocationVO, 
    		ModelMap model) {
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        geoLocationVO.fillPageInfo(paginationInfo);
        
        model.addAttribute("resultList", geoLocationService.selectBuildingLocationInfoList(geoLocationVO));
 
        int totCnt =  geoLocationService.selectBuildingLocationInfoListCnt(geoLocationVO);
        geoLocationVO.setTotalRecordCount(totCnt);

        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoList");
    }

    /**
     * 건물 위치정보 상세조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping("/mbl/com/geo/detailBuildingLocationInfo.do")
    public String detailBuildingLocationInfo(
    		@ModelAttribute GeoLocationVO geoLocationVO) {
    	
        geoLocationService.selectBuildingLocationInfo(geoLocationVO);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoDetail");
    }

    /**
     * 건물 위치정보 등록 화면으로 이동한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping(value="/mbl/com/geo/registBuildingLocationInfo.do")
    public String registBuildingLocationInfo(
    		@ModelAttribute GeoLocationVO geoLocationVO) {
    	
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoRegist");
     }

    /**
     * 건물 위치정보 등록 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping("/mbl/com/geo/insertBuildingLocationInfo.do")
    public String insertBuildingLocationInfo(
     		@ModelAttribute GeoLocationVO geoLocationVO, 
            BindingResult bindingResult, 
    		ModelMap model) {
    	
        // Validation
        beanValidator.validate(geoLocationVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoRegist");
        }
        
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        geoLocationVO.setMberId(loginVO.getId());

        geoLocationService.insertBuildingLocationInfo(geoLocationVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/geo/listBuildingLocationInfo.do");
     }
    
    /**
     * 건물 위치정보 수정 화면으로 이동한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping(value="/mbl/com/geo/editBuildingLocationInfo.do")
    public String editBuildingLocationInfo(
    		@ModelAttribute GeoLocationVO geoLocationVO) {
    	
    	geoLocationService.selectBuildingLocationInfo(geoLocationVO);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoEdit");
    }
    
    /**
     * 건물 위치정보 수정 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping(value="/mbl/com/geo/updateBuildingLocationInfo.do")
    public String updateBuildingLocationInfo(
     		@ModelAttribute GeoLocationVO geoLocationVO, 
            BindingResult bindingResult, 
            ModelMap model) {
    	
        // Validation
        beanValidator.validate(geoLocationVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/geo/BuildingLocationInfoEdit");
        }
        
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        geoLocationVO.setMberId(loginVO.getId());

        geoLocationService.updateBuildingLocationInfo(geoLocationVO);
        
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/mbl/com/geo/listBuildingLocationInfo.do");
    }
    
    /**
     * 건물 위치정보 삭제 Service interface 호출 및 결과를 반환한다.
     * 
     * @param geoLocationVO
     */
    @RequestMapping(value="/mbl/com/geo/deleteBuildingLocationInfo.mdo")
    public String deleteBuildingLocationInfo(
			@ModelAttribute GeoLocationVO geoLocationVO, 
    		ModelMap model) {
    	
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        geoLocationVO.setMberId(loginVO.getId());
 
        geoLocationService.deleteBuildingLocationInfo(geoLocationVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/mbl/com/geo/listBuildingLocationInfo.do");
    }
    
}