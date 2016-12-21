package aramframework.mbl.com.mdi.web;

import javax.servlet.http.HttpServletRequest;

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

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.mbl.com.mdi.domain.DeviceIdentVO;
import aramframework.mbl.com.mdi.service.DeviceIdentService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 모바일 기기 식별에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 모바일 기기 식별정보에 대한 등록, 수정, 삭제, 조회 요청과 
 *   User-Agent값 조회, 모바일기기 정보 추출 요청 사항을 Service와  매핑 처리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class DeviceIdentController {

	@Autowired
    private DeviceIdentService deviceIdentService;

	@Autowired
    private CmmUseService cmmUseService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 모바일 기기 식별 정보 화면으로 이동한다.
     * 
     */
    @RequestMapping(value = "/mbl/com/mdi/goMobileDeviceIdent.mdo")
	@Secured("ROLE_USER")
    public String goMobileDeviceIdent() {
        return "aramframework/mbl/com/mdi/MobileDeviceIdent";
    }

    /**
     * 모바일 기기 정보 조회 Service interface 호출 및 결과를
     * 반환한다.(JSON 통신)
     * 
     */
    @RequestMapping(value = "/mbl/com/mdi/goMobileDeviceIdentJson.mdo")
    public ModelAndView goMobileDeviceIdentJson(
            HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        String uagentInfo = request.getHeader("user-agent");

        DeviceIdentVO deviceIdentVO = deviceIdentService.getDeviceIdentFromXML(uagentInfo);

        if (deviceIdentVO == null) {

        	deviceIdentVO = new DeviceIdentVO();

            // 로그인VO에서 사용자 정보 가져오기
            LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
            deviceIdentVO.setMberId(loginVO.getId());

            // Unknown 코드 및 명 입력
            deviceIdentVO.setBrowserCode("BRS001");
            deviceIdentVO.setBrowserNm("Unknown");
            deviceIdentVO.setOsCode("OS01");
            deviceIdentVO.setOsNm("Unknown");

            // User-Agent 입력
            deviceIdentVO.setUagentInfo(uagentInfo);

            // 등록상태 입력
            deviceIdentVO.setRecentCode("REG01");

            // DB 저장
            deviceIdentService.insertDeviceIdent(deviceIdentVO);

            // XML 파일 생성
            deviceIdentService.createDeviceIndentListToXML();
        }

        modelAndView.addObject("result", deviceIdentVO);

        return modelAndView;
    }

	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
    /**
     * 모바일 기기 목록 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param deviceIdentVO
     */
	@IncludedInfo(name = "모바일 기기 정보", order = 10020, gid = 100)
    @RequestMapping(value = "/mbl/com/mdi/listDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String listDeviceIdent(
            @ModelAttribute DeviceIdentVO deviceIdentVO, 
            ModelMap model) {
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        deviceIdentVO.getSearchVO().fillPageInfo(paginationInfo);

        model.addAttribute("resultList", deviceIdentService.selectDeviceIdentList(deviceIdentVO));
        int totCnt = deviceIdentService.selectDeviceIdentListCnt(deviceIdentVO);

        deviceIdentVO.getSearchVO().setTotalRecordCount(totCnt);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute(paginationInfo);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mdi/DeviceIdentList");
    }

    /**
     * 모바일 기기 상세정보 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/detailDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String detailDeviceIdent(
            DeviceIdentVO deviceIdentVO,
            ModelMap model) {
    	
    	model.addAttribute(deviceIdentService.selectDeviceIdent(deviceIdentVO));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mdi/DeviceIdentDetail");
    }

    /**
     * 모바일 기기 정보 등록 화면으로 이동한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/registDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String registDeviceIdent(
            @ModelAttribute DeviceIdentVO deviceIdentVO, 
            ModelMap model) {

        model.addAttribute("browserCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM083"));
        model.addAttribute("osCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM084"));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mdi/DeviceIdentRegist");
    }

    /**
     * 모바일 기기 정보 등록 Service interface 호출 및 결과를 반환한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/insertDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String insertDeviceIdent(
            @ModelAttribute DeviceIdentVO deviceIdentVO,
            BindingResult bindingResult, 
            ModelMap model) {

        beanValidator.validate(deviceIdentVO, bindingResult);
        if (bindingResult.hasErrors()) {

            model.addAttribute("browserCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM083"));
            model.addAttribute("osCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM084"));

    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mdi/DeviceIdentRegist");
        }

        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        deviceIdentVO.setMberId(loginVO.getId());
        deviceIdentVO.setBrowserNm("COM083");
        deviceIdentVO.setOsNm("COM084");

        deviceIdentService.insertDeviceIdent(deviceIdentVO);

        // XML 파일 생성
        deviceIdentService.createDeviceIndentListToXML();

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mdi/listDeviceIdent.do");
    }

    /**
     * 모바일 기기 정보 수정 화면으로 이동한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/editDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String editDeviceIdent(
            DeviceIdentVO deviceIdentVO,
            ModelMap model) {
    	
    	model.addAttribute(deviceIdentService.selectDeviceIdent(deviceIdentVO));
 
        model.addAttribute("browserCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM083"));
        model.addAttribute("osCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM084"));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mdi/DeviceIdentEdit");
    }

    /**
     * 모바일 기기 정보 수정 Service interface 호출 및 결과를 반환한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/updateDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String updateDeviceIdent(
            @ModelAttribute DeviceIdentVO deviceIdentVO,
            BindingResult bindingResult, 
            ModelMap model) {

        // Validation
        beanValidator.validate(deviceIdentVO, bindingResult);
        if (bindingResult.hasErrors()) {

        	model.addAttribute("browserCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM083"));
            model.addAttribute("osCmmCodeDetailList", cmmUseService.selectCmmCodeList("COM084"));

    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mdi/DeviceIdentEdit");
        }

        deviceIdentService.updateDeviceIdent(deviceIdentVO);

        // XML 파일 생성
        deviceIdentService.createDeviceIndentListToXML();

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mdi/listDeviceIdent.do");
    }

    /**
     * 모바일 기기 정보 삭제 Service interface 호출 및 결과를 반환한다.
     * 
     * @param deviceIdentVO
     */
    @RequestMapping(value = "/mbl/com/mdi/deleteDeviceIdent.do")
	@Secured("ROLE_ADMIN")
    public String deleteDeviceIdent(
            @ModelAttribute DeviceIdentVO deviceIdentVO,
            ModelMap model) {
    	
        deviceIdentService.deleteDeviceIdent(deviceIdentVO);

        // XML 파일 생성
        deviceIdentService.createDeviceIndentListToXML();

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mdi/listDeviceIdent.do");
    }

}
