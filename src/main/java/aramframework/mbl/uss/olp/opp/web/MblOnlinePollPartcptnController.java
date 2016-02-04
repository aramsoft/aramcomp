package aramframework.mbl.uss.olp.opp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.domain.LoginVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.uss.olp.opp.domain.OnlinePollPartcptnVO;
import aramframework.com.uss.olp.opp.service.OnlinePollPartcptnService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인POLL참여를 처리하는 Controller Class 구현
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
public class MblOnlinePollPartcptnController {
	
	@Autowired
    private OnlinePollPartcptnService onlinePollPartcptnService;

	@Autowired
    private CmmUseService cmmUseService;
    
    /**
     * 온라인POLL참여 목록을 조회한다.
     * 
     * @param onlinePollPartcptnVO
     */
    @RequestMapping(value = "/uss/olp/opp/listOnlinePollPartcptn.mdo")
    public String listOnlinePollPartcptn(
            @ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
            ModelMap model) {

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        onlinePollPartcptnVO.fillPageInfo(paginationInfo);

        model.addAttribute("resultList", onlinePollPartcptnService.selectOnlinePollManageList(onlinePollPartcptnVO));

        int totCnt = (Integer) onlinePollPartcptnService.selectOnlinePollManageListCnt(onlinePollPartcptnVO);
        onlinePollPartcptnVO.setTotalRecordCount(totCnt);
 
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "aramframework/mbl/uss/olp/opp/OnlinePollPartcptnList";
    }

    /**
     * 온라인POLL참여를 등록한다.
     * 
     * @param onlinePollPartcptnVO
     */
    @RequestMapping(value = "/uss/olp/opp/registOnlinePollPartcptn.mdo")
	@Secured("ROLE_USER")
    public String registOnlinePollPartcptn(
            @ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
            ModelMap model) {

        //POLL종류 설정
        model.addAttribute("pollKindCodeList", cmmUseService.selectCmmCodeList("COM039"));
        //POLL페기유무 설정 /POLL자동페기유무
        model.addAttribute("pollDeuseYnList", cmmUseService.selectCmmCodeList("COM038"));
        
        //온라인POLL관리 정보 설정
        onlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptnVO);
         //온라인POLL항목 정보 설정            
        model.addAttribute("PollItem", onlinePollPartcptnService.selectOnlinePollItemList(onlinePollPartcptnVO));
        return "aramframework/mbl/uss/olp/opp/OnlinePollPartcptnRegist";
    }
    
    /**
     * 온라인POLL참여를 등록한다.
     * 
     * @param onlinePollPartcptnVO
     */
    @RequestMapping(value = "/uss/olp/opp/insertOnlinePollPartcptn.mdo")
	@Secured("ROLE_USER")
    public String insertOnlinePollPartcptn(
            @ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
            ModelMap model) {

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	onlinePollPartcptnVO.setFrstRegisterId(loginVO.getUniqId());
        
        //투표여부 체크
        if(onlinePollPartcptnService.selectOnlinePollResult(onlinePollPartcptnVO) != 0){
        	 String ReusltScript = "";
             
             ReusltScript += "<script type='text/javaScript' language='javascript'>";
             ReusltScript += "alert('한 온라인POLL엔 한번만 투표 가능합니다. ');";
             ReusltScript += "</script>";
             
             model.addAttribute("reusltScript", ReusltScript);            
             return "forward:/uss/olp/opp/listOnlinePollPartcptn.mdo";
        }
        
        onlinePollPartcptnService.insertOnlinePollResult(onlinePollPartcptnVO);
        
        String ReusltScript = "";
        
        ReusltScript += "<script type='text/javaScript' language='javascript'>";
        ReusltScript += "alert(' 온라인POLL참여에 응해주셔서 감사합니다!  ');";
        ReusltScript += "</script>";
        
        model.addAttribute("reusltScript", ReusltScript);            
        return "forward:/uss/olp/opp/listOnlinePollPartcptn.mdo";
    }
    
   /**
     * 온라인POLL관리 통계를 조회한다.
     * 
     * @param onlinePollPartcptnVO
     */
    @RequestMapping(value = "/uss/olp/opp/statisticsOnlinePollPartcptn.mdo")
    public String statisticsOnlinePollPartcptn(
			HttpServletRequest request, 
            @ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
            ModelMap model) {
        
        //POLL종류 설정
        model.addAttribute("pollKindCodeList", cmmUseService.selectCmmCodeList("COM039"));
         //POLL페기유무 설정 /POLL자동페기유무
        model.addAttribute("pollDeuseYnList", cmmUseService.selectCmmCodeList("COM038"));
        
        //온라인POLL관리 정보 설정
        onlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptnVO);
        //온라인POLL항목 정보 설정            
        model.addAttribute("PollItemList", onlinePollPartcptnService.selectOnlinePollItemList(onlinePollPartcptnVO));
        //온라인POLL결과 정보 설정          
        model.addAttribute("statisticsList", onlinePollPartcptnService.selectOnlinePollManageStatistics(onlinePollPartcptnVO));

        //이전 주소
        model.addAttribute("returnUrl", request.getHeader("REFERER"));
        
        model.addAttribute("linkType", request.getParameter("linkType"));
       
        return "aramframework/mbl/uss/olp/opp/OnlinePollPartcptnStatistics"; 
    }

    /**
     * 온라인POLL참여 목록을 조회한다.
     * 
     * @param onlinePollPartcptnVO
     */
    @RequestMapping(value = "/uss/olp/opp/listOnlinePollPartcptnMainPage.mdo")
    public String listOnlinePollPartcptnMainPage(
            @ModelAttribute OnlinePollPartcptnVO onlinePollPartcptnVO, 
            ModelMap model) {

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        onlinePollPartcptnVO.fillPageInfo(paginationInfo);

        model.addAttribute("resultList", onlinePollPartcptnService.selectOnlinePollManageList(onlinePollPartcptnVO));
        model.addAttribute("paginationInfo", paginationInfo);
  
        return "aramframework/mbl/uss/olp/opp/OnlinePollPartcptnMainList";
    }
    
}
