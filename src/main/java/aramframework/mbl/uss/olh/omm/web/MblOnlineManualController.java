package aramframework.mbl.uss.olh.omm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.uss.olh.omm.domain.OnlineManualVO;
import aramframework.com.uss.olh.omm.service.OnlineManualService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인메뉴얼를 처리하는 Controller Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MblOnlineManualController {
	 
	@Autowired
    private OnlineManualService onlineManualService;

	@Autowired
    private CmmUseService cmmUseService;
    
    /**
     * json데이터를 호출하기 위한 페이지를 호출한다.
     * 
     * @param onlineManualVO
     */
    @RequestMapping(value="/uss/olh/omm/listOnlineManual.mdo")
    public String listOnlineManual(
    		@ModelAttribute OnlineManualVO onlineManualVO) {
    	
		return "aramframework/mbl/uss/olh/omm/OnlineManualList";
    }
    
    /**
     * 사용자 온라인메뉴얼 목록을 조회한다.
     * 
     * @param onlineManualVO
     */
    @RequestMapping(value = "/uss/olh/omm/listOnlineManualJson.mdo")
    public ModelAndView listOnlineManualJson(
    		@ModelAttribute OnlineManualVO onlineManualVO) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        onlineManualVO.getSearchVO().fillPageInfo(paginationInfo);

        modelAndView.addObject("reusltList", onlineManualService.selectOnlineManualList(onlineManualVO));
        int totCnt = (Integer) onlineManualService.selectOnlineManualListCnt(onlineManualVO);

        onlineManualVO.getSearchVO().setTotalRecordCount(totCnt);
        paginationInfo.setTotalRecordCount(totCnt);

        modelAndView.addObject(paginationInfo);
 
        //온라인메뉴얼 구분 설정
        modelAndView.addObject("onlineMnlSeCodeList", cmmUseService.selectCmmCodeList("COM041"));
        
        return modelAndView;
    }

    /**
     * 사용자 온라인메뉴얼 목록을 상세조회 조회한다.
     * 
     * @param onlineManualVO
     */
    @RequestMapping(value = "/uss/olh/omm/detailOnlineManual.mdo")
    public String detailOnlineManual(
    		OnlineManualVO onlineManualVO, 
            ModelMap model) {

    	model.addAttribute(onlineManualService.selectOnlineManualDetail(onlineManualVO));
        
        //온라인메뉴얼 구분 설정
        model.addAttribute("onlineMnlSeCodeList", cmmUseService.selectCmmCodeList("COM041"));
        
        return "aramframework/mbl/uss/olh/omm/OnlineManualDetail";
     }
    
}
