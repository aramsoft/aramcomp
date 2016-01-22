package aramframework.mbl.uss.olh.hpc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.uss.olh.hpc.service.HpcmManageService;
import aramframework.com.uss.olh.hpc.service.HpcmManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 도움말을 처리하는 비즈니스 구현 클래스
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
public class MblHpcmManageController {
	 
	@Autowired
    private HpcmManageService hpcmManageService;
    
    /**
     * json데이터를 호출하기 위한 페이지를 호출한다.
     * 
     * @param hpcmManageVO
     */
    @RequestMapping(value="/uss/olh/hpc/listHpcm.mdo")
    public String listHpcm(
    		@ModelAttribute HpcmManageVO hpcmManageVO) {
    	
		return "aramframework/mbl/uss/olh/hpc/HpcmList";
    }
    
    /**
     * 도움말내용 목록을 조회한다. (pageing)
     * 
     * @param hpcmManageVO
     */
    @RequestMapping(value="/uss/olh/hpc/listHpcmJson.mdo")
    public ModelAndView listHpcmJson(
    		@ModelAttribute HpcmManageVO hpcmManageVO) {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	hpcmManageVO.fillPageInfo(paginationInfo);
		
		modelAndView.addObject("reusltList", hpcmManageService.selectHpcmList(hpcmManageVO));
        
        int totCnt = hpcmManageService.selectHpcmListCnt(hpcmManageVO);
        hpcmManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
        modelAndView.addObject("paginationInfo", paginationInfo);
 
        return modelAndView;
    } 
    
    /**
     * 도움말내용 목록에 대한 상세정보를 조회한다.
     * 
     * @param hpcmManageVO
     */
    @RequestMapping("/uss/olh/hpc/detailHpcm.mdo")
    public String detailHpcm(
    		@ModelAttribute HpcmManageVO hpcmManageVO) {  

    	hpcmManageService.selectHpcmDetail(hpcmManageVO);
		
        return	"aramframework/mbl/uss/olh/hpc/HpcmDetail";
    }

}
