package aramframework.mbl.uss.ion.rec.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.ion.rec.service.RecomendSiteService;
import aramframework.com.uss.ion.rec.service.RecomendSiteVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 추천사이트처리를 하는 Mobile Controller 클래스
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
public class MblRecomendSiteController {
	
    @Resource(name = "recomendSiteService")
    private RecomendSiteService recomendSiteService;    
    
    /**
     * 추천사이트정보 목록을 조회하기 위한 화면을 출력한다.
     * 
     * @param recomendSiteVO
     */
    @RequestMapping(value="/uss/ion/rec/listRecomendSite.mdo")
    public String listRecomendSite(
    		@ModelAttribute RecomendSiteVO recomendSiteVO) {
    	
    	return "aramframework/mbl/uss/ion/rec/RecomendSiteList";    	
    }
    
    /**
     * 추천사이트정보 목록을 조회한다.
     * 
     * @param recomendSiteVO
     */
    @RequestMapping(value="/uss/ion/rec/listRecomendSiteJson.mdo")
    public ModelAndView listRecomendSiteJson(
    		@ModelAttribute RecomendSiteVO recomendSiteVO) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		recomendSiteVO.fillPageInfo(paginationInfo);
		
		modelAndView.addObject("resultList", recomendSiteService.selectRecomendSiteList(recomendSiteVO));
        
        int totCnt = recomendSiteService.selectRecomendSiteListCnt(recomendSiteVO);
        recomendSiteVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);
        
        return modelAndView;
    } 
    
    /**
     * 추천사이트정보 목록에 대한 상세정보를 조회한다.
     * 
     * @param recomendSiteVO
     */
    @RequestMapping("/uss/ion/rec/detailRecomendSite.mdo")
    public String	detailRecomendSite(
    		@ModelAttribute RecomendSiteVO recomendSiteVO) {  
    	
		recomendSiteService.selectRecomendSiteDetail(recomendSiteVO);
		
        return "aramframework/mbl/uss/ion/rec/RecomendSiteDetail";
    }
    
    /**
     * 마이페이지용 Main 추천사이트정보 목록을 조회한다.
     * 
     * @param recomendSiteVO
     */
    @RequestMapping(value="/uss/ion/rec/listRecomendSiteMainPage.mdo")
    public String listRecomendSiteMainPage(
    		@ModelAttribute RecomendSiteVO recomendSiteVO, 
    		ModelMap model) {
    	
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		recomendSiteVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", recomendSiteService.selectRecomendSiteList(recomendSiteVO));
        
        int totCnt = recomendSiteService.selectRecomendSiteListCnt(recomendSiteVO);
        recomendSiteVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
        
        return "aramframework/mbl/uss/ion/rec/RecomendSiteMainList";
    }
    
}
