package aramframework.mbl.uss.olp.cns.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.uss.olp.cns.domain.CnsltManageVO;
import aramframework.com.uss.olp.cns.service.CnsltManageService;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 상담관리를 처리하는 Controller 클래스
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
public class MblCnsltManageController {
	
	@Autowired
    private CnsltManageService cnsltManageService;
    
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
     * 상담관리목록 조회한다.
     * 
     * @param cnsltManageVO
     */
    @RequestMapping(value="/uss/olp/cns/listCnslt.mdo")
    public String listCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO, 
    		ModelMap model) {
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	cnsltManageVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("cnsltManageList", cnsltManageService.selectCnsltList(cnsltManageVO));
        
        /** paging */
        int totCnt = cnsltManageService.selectCnsltListCnt(cnsltManageVO);
        cnsltManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
             
		// 인증여부 체크
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}

		return "aramframework/mbl/uss/olp/cns/CnsltList";
    }
    
    /**
     * 상담정보 목록에 대한 상세정보를 조회한다.
     * 
     * @param cnsltManageVO
     */
    @RequestMapping("/uss/olp/cns/detailCnslt.mdo")
    public String detailCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO) {  
    	
    	// 조회 수 증가
    	cnsltManageService.updateCnsltInqireCo(cnsltManageVO);

    	cnsltManageService.selectCnsltListDetail(cnsltManageVO);
    	
        return "aramframework/mbl/uss/olp/cns/CnsltDetail";
    }
    
    /**
     * 상담정보 목록에 대한 상세정보 공개 여부를 조회한다.
     * 
     * @param cnsltId
     */
    @RequestMapping("/uss/olp/cns/confirmCnsltManageOthbcAtJson.mdo")
    public ModelAndView	confirmCnsltManageOthbcAtJson(
    		@RequestParam String cnsltId) {  
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	CnsltManageVO cnsltManageVO = new CnsltManageVO();
    	cnsltManageVO.setCnsltId(cnsltId);
    	
		cnsltManageService.selectCnsltListDetail(cnsltManageVO);
		
		if("N".equals(cnsltManageVO.getOthbcAt())) {
			modelAndView.addObject("othbcAtConfirm", "N");
		}
		else {
			modelAndView.addObject("othbcAtConfirm", "Y");			
		}
				
        return	modelAndView;
    }
    
    /**
     * 작성 비밀번호를 확인한다.
     * 
     * @param cnsltManageVO
     */
    @RequestMapping("/uss/olp/cns/confirmCnsltPasswordJson.mdo")
    public ModelAndView confirmCnsltPasswordJson(
    		@ModelAttribute CnsltManageVO cnsltManageVO)           
    throws Exception {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(FileScrty.encode(cnsltManageVO.getWritngPassword()));
        int searchCnt = cnsltManageService.selectCnsltPasswordConfirmCnt(cnsltManageVO);
        
        String	passwordConfirmAt = "";
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우
        	
        	passwordConfirmAt = "Y";
        } else	{					// 작성비밀번호가 틀린경우        
        	
        	passwordConfirmAt = "N";        	
        }
        
        modelAndView.addObject("passwordConfirmAt", passwordConfirmAt);
        return modelAndView;
    }
    
    /**
     * 상담내역등록 화면을 출력한다.
     * 
     * @param cnsltManageVO
     */
    @RequestMapping(value="/uss/olp/cns/registCnslt.mdo")
    public String registCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO) {
    			
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setWrterNm(loginVO.getName()); // 작성자명
			cnsltManageVO.setEmailAdres(loginVO.getEmail()); // email 주소
		}
        cnsltManageVO.setOthbcAt("Y");								// 공개여부 초기화
    	
    	return "aramframework/mbl/uss/olp/cns/CnsltRegist";
    }
    
   /**
     * 상담정보를 등록한다.
     * 
     * @param cnsltManageVO
     */				 
    @RequestMapping("/uss/olp/cns/insertCnslt.mdo")
    public String insertCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO,
			BindingResult bindingResult)
    throws Exception {
    	
		beanValidator.validate(cnsltManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "aramframework/com/uss/olp/cns/CnsltRegist";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		} else {
			cnsltManageVO.setFrstRegisterId("USRCNFRM_00000000000"); // guest
		}
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = cnsltManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

        cnsltManageService.insertCnsltDtls(cnsltManageVO);        

        return "redirect:/uss/olp/cns/listCnslt.mdo";        
    }
    
    /**
     * 상담내역수정 화면을 출력한다.
     * 
     * @param cnsltManageVO
     */
    @RequestMapping(value="/uss/olp/cns/editCnslt.mdo")
    public String editCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO) {
    	
		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = cnsltManageVO.getWritngPassword();

		cnsltManageService.selectCnsltListDetail(cnsltManageVO);
		
		cnsltManageVO.setWritngPassword(writngPassword);

		return "aramframework/mbl/uss/olp/cns/CnsltEdit";
    }
    
    /**
     * 상담정보를 수정처리한다.           
     * 
     * @param cnsltManageVO
     */
    @RequestMapping("/uss/olp/cns/updateCnslt.mdo")
    public String updateCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO,
			BindingResult bindingResult, 
            ModelMap model)
    throws Exception {
    	
		// Validation
		beanValidator.validate(cnsltManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "aramframework/com/uss/olp/cns/CnsltEdit";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			cnsltManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = cnsltManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(FileScrty.encode(writngPassword));

    	cnsltManageService.updateCnsltDtls(cnsltManageVO);        
  
        return "forward:/uss/olp/cns/listCnslt.mdo";
    }
    
    /**
     * 상담정보를 삭제처리한다.                
     * 
     * @param cnsltManageVO
     */
    @RequestMapping("/uss/olp/cns/deleteCnslt.mdo")
    public String deleteCnslt(
    		@ModelAttribute CnsltManageVO cnsltManageVO) {
    	
    	cnsltManageService.deleteCnsltDtls(cnsltManageVO); 
    	
        return "redirect:/uss/olp/cns/listCnslt.mdo";
    }
    
}
