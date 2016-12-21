package aramframework.mbl.uss.sam.stp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.sam.stp.domain.StplatManageVO;
import aramframework.com.uss.sam.stp.service.StplatManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 약관내용을 처리하는 비즈니스 구현 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MblStplatManageController {

	@Autowired
    private StplatManageService stplatManageService;
    
    /**
     * json데이터를 호출하기 위한 페이지를 호출한다.
     * 
     * @param stplatManageVO
     */
    @RequestMapping(value="/uss/sam/stp/listStplat.mdo")
    public String listStplat(
			@ModelAttribute StplatManageVO stplatManageVO, 
    		ModelMap model)
    throws Exception {
    	return "aramframework/mbl/uss/sam/stp/StplatList";
    }
    
    /**
     * 약관정보 목록을 조회한다.
     * 
     * @param stplatManageVO
     */
    @RequestMapping(value="/uss/sam/stp/listStplatJson.mdo")
    public ModelAndView listStplatJson(
			@ModelAttribute StplatManageVO stplatManageVO, 
    		ModelMap model) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	stplatManageVO.getSearchVO().fillPageInfo(paginationInfo);
		
		modelAndView.addObject("reusltList", stplatManageService.selectStplatList(stplatManageVO));
        int totCnt = stplatManageService.selectStplatListCnt(stplatManageVO);
 
        stplatManageVO.getSearchVO().setTotalRecordCount(totCnt);
		paginationInfo.setTotalRecordCount(totCnt);

		modelAndView.addObject(paginationInfo);
		
		return modelAndView;
    } 
    
    /**
     * 약관정보상세내용을 조회한다.
     * 
     * @param stplatManageVO
     */
    @RequestMapping("/uss/sam/stp/detailStplat.mdo")
    public String detailStplat(
			StplatManageVO stplatManageVO, 
            ModelMap model) {  
    	
    	model.addAttribute(stplatManageService.selectStplatDetail(stplatManageVO));
        return	"aramframework/mbl/uss/sam/stp/StplatDetail";
    }

}
