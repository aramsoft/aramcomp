package aramframework.mbl.uss.olh.wor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.olh.wor.domain.WordDicaryVO;
import aramframework.com.uss.olh.wor.service.WordDicaryService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 용어사전을 처리하는 Controller 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MblWordDicaryController {
	
	@Autowired
    private WordDicaryService wordDicaryService;
    
    /**
     * 용어사전목록 화면을 출력한다.
     * 
     * @param wordDicaryVO
     */
    @RequestMapping(value="/uss/olh/wor/listWordDicary.mdo")
    public String listWordDicary(
    		@ModelAttribute WordDicaryVO wordDicaryVO) {
    	
        return "aramframework/mbl/uss/olh/wor/WordDicaryList";    
    }
    
	/**
     * 용어사전목록을 조회한다.
     * 
     * @param wordDicaryVO
     */
    @RequestMapping(value="/uss/olh/wor/listWordDicaryJson.mdo")
    public ModelAndView listWordDicaryJson(
    		@ModelAttribute WordDicaryVO wordDicaryVO) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	wordDicaryVO.getSearchVO().fillPageInfo(paginationInfo);
		
		modelAndView.addObject("wordDicaryList", wordDicaryService.selectWordDicaryList(wordDicaryVO));
        int totCnt = wordDicaryService.selectWordDicaryListCnt(wordDicaryVO);

        wordDicaryVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		modelAndView.addObject(paginationInfo);
        
        return modelAndView;
    }
    
    /**
     * 용어사전 목록에 대한 상세정보를 조회한다.
     * 
     * @param wordDicaryVO
     */
    @RequestMapping("/uss/olh/wor/detailWordDicary.mdo")
    public String detailWordDicary(
    		WordDicaryVO wordDicaryVO,
    		ModelMap model) {
    	
    	model.addAttribute(wordDicaryService.selectWordDicaryDetail(wordDicaryVO));
		
        return "aramframework/mbl/uss/olh/wor/WordDicaryDetail";
    }
    
    /**
     * 마이페이지용 Main 용어사전목록을 조회한다.
     * 
     * @param wordDicaryVO
     */
    @RequestMapping(value="/uss/olh/wor/listWordDicaryMainPage.mdo")
    public String listWordDicaryMainPage(
    		@ModelAttribute WordDicaryVO wordDicaryVO, 
    		ModelMap model) {
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	wordDicaryVO.getSearchVO().fillPageInfo(paginationInfo);
		
		model.addAttribute("wordDicaryList", wordDicaryService.selectWordDicaryList(wordDicaryVO));
        int totCnt = wordDicaryService.selectWordDicaryListCnt(wordDicaryVO);

        wordDicaryVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute(paginationInfo);
        
        return "aramframework/mbl/uss/olh/wor/WordDicaryMainList";
    }
    
}
