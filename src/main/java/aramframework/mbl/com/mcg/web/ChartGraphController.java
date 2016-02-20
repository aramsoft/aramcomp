package aramframework.mbl.com.mcg.web;

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
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.mbl.com.mcg.domain.ChartGraphVO;
import aramframework.mbl.com.mcg.service.ChartGraphService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 차트/그래프 데이터에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 차트/그래프 데이터에 대한 등록, 수정, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class ChartGraphController {

	@Autowired
    private ChartGraphService chartGraphService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 모바일 차트/그래프 화면으로 이동한다.
     * 
     */
    @RequestMapping(value = "/mbl/com/mcg/goMobileChartGraph.mdo")
    public String goMobileChartGraph() {
        return "aramframework/mbl/com/mcg/MobileChartGraph";
    }

    /**
     * 모바일 차트/그래프 데이터 목록 조회 Service interface 호출 및 결과를
     * 반환한다.(JSON 통신)
     * 
     */
    @RequestMapping(value = "/mbl/com/mcg/goMobileChartGraphJson.mdo")
    public ModelAndView goMobileChartGraphJson() {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        ChartGraphVO chartGraphVO = new ChartGraphVO();
        chartGraphVO.getSearchVO().setFirstIndex(0);
        chartGraphVO.getSearchVO().setRecordPerPage(1000);

        modelAndView.addObject("resultList", chartGraphService.selectChartGraphList(chartGraphVO));

        return modelAndView;
    }

	//---------------------------------------------------------------------------------------------------------/
	// 서비스 관리자 ----------------------------------------------------------------------------------------/
	//---------------------------------------------------------------------------------------------------------/
    /**
     * 차트/그래프 데이터 목록 조회 Service interface 호출 및 결과를
     * 반환한다.
     * 
     * @param chartGraphVO
     */
	@IncludedInfo(name = "차트데이터정보", order = 10010, gid = 100)
    @RequestMapping(value = "/mbl/com/mcg/listChartGraph.do")
	@Secured("ROLE_ADMIN")
    public String listChartGraph(
    		@ModelAttribute ChartGraphVO chartGraphVO, 
            ModelMap model) {
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        chartGraphVO.getSearchVO().fillPageInfo(paginationInfo);

        model.addAttribute("resultList", chartGraphService.selectChartGraphList(chartGraphVO));
        int totCnt = chartGraphService.selectChartGraphListCnt(chartGraphVO);

        chartGraphVO.getSearchVO().setTotalRecordCount(totCnt);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute(paginationInfo);

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mcg/ChartGraphList");
     }

    /**
     * 차트/그래프 상세정보 조회 Service interface 호출 및 결과를 반환한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/detailChartGraph.do")
	@Secured("ROLE_USER")
    public String detailChartGraph(
            ChartGraphVO chartGraphVO,
            ModelMap model) {
    	
    	model.addAttribute(chartGraphService.selectChartGraph(chartGraphVO));

		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mcg/ChartGraphDetail");
    }

    /**
     * 차트/그래프 데이터 등록 화면으로 이동한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/registChartGraph.do")
	@Secured("ROLE_USER")
    public String registChartGraph(
            @ModelAttribute ChartGraphVO chartGraphVO) {
    	
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mcg/ChartGraphRegist");
     }

    /**
     * 차트/그래프 데이터 등록 Service interface 호출 및 결과를 반환한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/insertChartGraph.do")
	@Secured("ROLE_USER")
    public String insertChartGraph(
            @ModelAttribute ChartGraphVO chartGraphVO, 
            BindingResult bindingResult, 
            ModelMap model) {

        beanValidator.validate(chartGraphVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mcg/ChartGraphRegist");
        }

        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        chartGraphVO.setMberId(loginVO.getId());

        chartGraphService.insertChartGraph(chartGraphVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mcg/listChartGraph.do");
    }

    /**
     * 차트/그래프 데이터 수정 화면으로 이동한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/editChartGraph.do")
	@Secured("ROLE_USER")
    public String editChartGraph(
            ChartGraphVO chartGraphVO,
            ModelMap model) {
    	
    	model.addAttribute(chartGraphService.selectChartGraph(chartGraphVO));
 
		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
		return WebUtil.adjustViewName("/com/mcg/ChartGraphEdit");
    }

    /**
     * 차트/그래프 데이터 수정 Service interface 호출 및 결과를 반환한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/updateChartGraph.do")
	@Secured("ROLE_USER")
    public String updateChartGraph(
            @ModelAttribute ChartGraphVO chartGraphVO, 
            BindingResult bindingResult, 
            ModelMap model) {

        // Validation
        beanValidator.validate(chartGraphVO, bindingResult);
        if (bindingResult.hasErrors()) {
    		RequestContextHolder.getRequestAttributes().setAttribute("jspPrefix", "aramframework/mbl", RequestAttributes.SCOPE_REQUEST);
    		return WebUtil.adjustViewName("/com/mcg/ChartGraphEdit");
        }
        
        // 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        chartGraphVO.setMberId(loginVO.getId());

        chartGraphService.updateChartGraph(chartGraphVO);
 
		model.addAttribute("message", MessageHelper.getMessage("success.common.update"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mcg/listChartGraph.do");
    }

    /**
     * 차트/그래프 데이터 삭제 Service interface 호출 및 결과를 반환한다.
     * 
     * @param chartGraphVO
     */
    @RequestMapping(value = "/mbl/com/mcg/deleteChartGraph.do")
	@Secured("ROLE_USER")
    public String deleteChartGraph(
            @ModelAttribute ChartGraphVO chartGraphVO, 
            ModelMap model) {
    	
    	// 로그인VO에서 사용자 정보 가져오기
        LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        chartGraphVO.setMberId(loginVO.getId());
        
        chartGraphService.deleteChartGraph(chartGraphVO);
 
		model.addAttribute("message", MessageHelper.getMessage("success.common.delete"));
	    return WebUtil.redirectJsp(model, "/mbl/com/mcg/listChartGraph.do");
    }
    
}
