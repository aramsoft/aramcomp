package aramframework.mbl.uss.ion.nws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.ion.nws.domain.NewsManageVO;
import aramframework.com.uss.ion.nws.service.NewsManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 뉴스정보를 처리하는 Controller 클래스
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
public class MblNewsManageController {
	
	@Autowired
    private NewsManageService newsManageService;
    
    /**
     * 뉴스정보 목록을 조회한다.
     * 
     * @param newsManageVO
     */ 
    @RequestMapping(value="/uss/ion/nws/listNewsInfo.mdo")
    public String listNewsInfo(
    		@ModelAttribute NewsManageVO newsManageVO) {
    	
        return "aramframework/mbl/uss/ion/nws/NewsInfoList";
    }
    
    /**
     * 뉴스정보 목록을 조회한다.
     * 
     * @param newsManageVO
     */ 
    @RequestMapping(value="/uss/ion/nws/listNewsInfoJson.mdo")
    public ModelAndView listNewsInfoJson(
    		@ModelAttribute NewsManageVO newsManageVO) {

    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	newsManageVO.getSearchVO().fillPageInfo(paginationInfo);

		modelAndView.addObject("NewsList", newsManageService.selectNewsList(newsManageVO));

        int totCnt = newsManageService.selectNewsListCnt(newsManageVO);
        newsManageVO.getSearchVO().setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);

        return modelAndView;
    } 

    /**
     * 뉴스정보 목록에 대한 상세정보를 조회한다.
     * 
     * @param newsManageVO
     */
    @RequestMapping("/uss/ion/nws/detailNewsInfo.mdo")
    public String detailNewsInfo(
    		@ModelAttribute NewsManageVO newsManageVO) {  
    	
		newsManageService.selectNewsDetail(newsManageVO);
		
        return	"aramframework/mbl/uss/ion/nws/NewsInfoDetail";
    }

}