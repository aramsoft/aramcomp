package aramframework.mbl.uss.olh.awm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.olh.awm.service.AdministrationWordVO;
import aramframework.com.uss.olh.awm.service.AdministrationWordService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 행정전문용어사전을 처리하는 Controller 클래스
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
public class MblAdministrationWordManageController {
	
    @Resource(name = "administrationWordService")
    private AdministrationWordService administrationWordService;    

	/**
     * 행정전문용어사전목록 화면을 출력한다.
     * 
     * @param administrationWordVO
     */
    @RequestMapping(value="/uss/olh/awa/listAdministrationWord.mdo")
    public String listAdministrationWord(
    		@ModelAttribute AdministrationWordVO administrationWordVO) {
    	
        return "aramframework/mbl/uss/olh/awa/AdministrationWordList";    
    }
    
    /**
     * 행정전문용어사전목록을 조회한다.
     * 
     * @param administrationWordVO
     */
    @RequestMapping(value = "/uss/olh/awa/listAdministrationWordJson.mdo")
    public ModelAndView listAdministrationWordJson(
    		@ModelAttribute AdministrationWordVO administrationWordVO) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        administrationWordVO.fillPageInfo(paginationInfo);
        
        modelAndView.addObject("resultList", administrationWordService.selectAdministrationWordList(administrationWordVO));

        int totCnt = (Integer) administrationWordService.selectAdministrationWordListCnt(administrationWordVO);
        administrationWordVO.setTotalRecordCount(totCnt);

        paginationInfo.setTotalRecordCount(totCnt);
        modelAndView.addObject("paginationInfo", paginationInfo);

        return modelAndView;
    }

    /**
     * 행정전문용어사전 목록을 상세조회 조회한다.
     * 
     * @param administrationWordVO
     */
    @RequestMapping(value = "/uss/olh/awa/detailAdministrationWord.mdo")
    public String detailAdministrationWord(
    		@ModelAttribute AdministrationWordVO administrationWordVO) {

    	administrationWordService.selectAdministrationWordDetail(administrationWordVO);
    	
        return "aramframework/mbl/uss/olh/awa/AdministrationWordDetail";
    }
    
    /**
     * 마이페이지에 Main으로 출력할 행정전문용어사전목록을 조회한다.
     * 
     * @param administrationWordVO
     */
    @RequestMapping(value = "/uss/olh/awa/listAdministrationWordMainPage.mdo")
    public String listAdministrationWordMainPage(
    		@ModelAttribute AdministrationWordVO administrationWordVO, 
            ModelMap model) {

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        administrationWordVO.fillPageInfo(paginationInfo);
        
        model.addAttribute("resultList", administrationWordService.selectAdministrationWordList(administrationWordVO));

        int totCnt = (Integer) administrationWordService.selectAdministrationWordListCnt(administrationWordVO);
        administrationWordVO.setTotalRecordCount(totCnt);

        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "aramframework/mbl/uss/olh/awa/AdministrationWordMainList";
    }
    
}
