package aramframework.mbl.uss.olh.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.olh.faq.domain.FaqManageVO;
import aramframework.com.uss.olh.faq.service.FaqManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 
/**
 * FAQ내용을 처리하는 비즈니스 구현 클래스
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
public class MblFaqManageController {

	@Autowired
    private FaqManageService faqManageService;
    
    /**
     * 메뉴를 조회한다.
     * 
     * @param faqManageVO
     */
    @RequestMapping(value="/uss/olh/faq/listFaq.mdo")
    public String listFaq(
    		@ModelAttribute FaqManageVO faqManageVO) {
    	
        return "aramframework/mbl/uss/olh/faq/FaqList";
    }
    
    /**
     * FAQ 목록을 조회한다.
     * 
     * @param faqManageVO
     */
    @RequestMapping(value="/uss/olh/faq/listFaqJson.mdo")
    public ModelAndView listFaqJson(
    		@ModelAttribute FaqManageVO faqManageVO) {
    	
		ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	faqManageVO.getSearchVO().fillPageInfo(paginationInfo);
		
		modelAndView.addObject("FaqList", faqManageService.selectFaqList(faqManageVO));
        int totCnt = faqManageService.selectFaqListCnt(faqManageVO);
 
        faqManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		modelAndView.addObject(paginationInfo);
		
        return modelAndView;
    }
    
    /**
     * FAQ 목록에 대한 상세정보를 조회한다.
     * 
     * @param faqManageVO
     */
    @RequestMapping("/uss/olh/faq/detailFaq.mdo")
    public String detailFaq(
    		FaqManageVO faqManageVO,
    		ModelMap model) {
    	
    	model.addAttribute(faqManageService.selectFaqListDetail(faqManageVO));
		
        return	"aramframework/mbl/uss/olh/faq/FaqDetail";
    }
    
    /**
     * FAQ 조회수를  수정처리     
     * 
     * @param faqManageVO
     */
    @RequestMapping("/uss/olh/faq/updateFaqCo.mdo")
    public String updateFaqCo(
    		@ModelAttribute FaqManageVO faqManageVO) {

    	faqManageService.updateFaqInqireCo(faqManageVO);
            	        
        return "forward:/uss/olh/faq/detailFaq.mdo";
    }
    
}
