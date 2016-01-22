package aramframework.mbl.uss.olh.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.uss.olh.qna.service.QnaManageService;
import aramframework.com.uss.olh.qna.service.QnaManageVO;
import aramframework.com.utl.sim.service.FileScrty;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * Q&A를 처리하는 Controller 클래스
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
public class MblQnaManageController {
	 
	@Autowired
    private QnaManageService qnaManageService;
    
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * Q&A정보 목록을 조회한다. (pageing)
     * 
     * @param qnaManageVO
     */
    @RequestMapping(value="/uss/olh/qna/listQna.mdo")
    public String listQnaActor(
    		@ModelAttribute QnaManageVO qnaManageVO, 
    		ModelMap model) {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	qnaManageVO.fillPageInfo(paginationInfo);
		
		model.addAttribute("qnaManageList", qnaManageService.selectQnaList(qnaManageVO));
        
        /** paging */
        int totCnt = qnaManageService.selectQnaListCnt(qnaManageVO);
        qnaManageVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// 인증여부 체크
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}
		
        return "aramframework/mbl/uss/olh/qna/QnaList";
    } 
	
    /**
     * Q&A정보 목록에 대한 상세정보를 조회한다.
     * 
     * @param qnaManageVO
     */
    //@RequestParam("passwordConfirmAt") String passwordConfirmAt ,
    @RequestMapping("/uss/olh/qna/detailQna.mdo")
    public String detailQna(
    		@ModelAttribute QnaManageVO qnaManageVO) {  

		qnaManageService.selectQnaListDetail(qnaManageVO);
		
        return	"aramframework/mbl/uss/olh/qna/QnaDetail";
    }

    /**
     * Q&A 조회수를  수정처리한다.      
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/updateQnaCo.mdo")
    public String updateQnaCo(
    		@ModelAttribute QnaManageVO qnaManageVO) {

    	qnaManageService.updateQnaInqireCo(qnaManageVO);
 
    	return "forward:/uss/olh/qna/detailQna.mdo";
    }
    
    /**
     * 로그인/실명확인 처리
     * 
     */
    @RequestMapping("/uss/olh/qna/LoginRealnmChoice.mdo")
    public String LoginRealnmChoice() {
        return "aramframework/mbl/uss/olh/qna/QnaLoginRealnmChoice";        
    }

    /**
     * Q&A정보를 등록하기 위한 전 처리(인증체크)   
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/registQna.mdo")
    public String registQna(
    		@ModelAttribute QnaManageVO qnaManageVO) {

       // 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setWrterNm(loginVO.getName()); // 작성자명
			qnaManageVO.setEmailAdres(loginVO.getEmail()); // email 주소
		}

        return "aramframework/mbl/uss/olh/qna/QnaRegist";
    }
    
    /**
     * Q&A정보를 등록한다.
     * 
     * @param qnaManageVO
     */
    //BindingResult bindingResult,
    @RequestMapping("/uss/olh/qna/insertQna.mdo")
    public String insertQna(
    		@ModelAttribute QnaManageVO qnaManageVO,
			BindingResult bindingResult, 
            ModelMap model)
    throws Exception {

    	beanValidator.validate(qnaManageVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "aramframework/mbl/uss/olh/qna/QnaRegist";
		}
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		} else {
			qnaManageVO.setFrstRegisterId("USRCNFRM_00000000000"); // guest
		}
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));
    	
        qnaManageService.insertQnaCn(qnaManageVO);        
		        
        return "redirect:/uss/olh/qna/listQna.mdo";        
    }

    /**
     * 작성 비밀번호를 확인하기 위한 전 처리
     * 
     */
    @RequestMapping("/uss/olh/qna/confirmQnaPassword.mdo")
    public String confirmQnaPassword() {
        return "aramframework/mbl/uss/olh/qna/QnaPasswordConfirm";        
    }
    
    /**
     * 작성 비밀번호를 확인한다.
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/confirmQnaPasswordJson.mdo")
    public ModelAndView confirmQnaPasswordJson(
    		@ModelAttribute QnaManageVO qnaManageVO,
            ModelMap model)            
    throws Exception {

    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	//String	writngPassword = qnaManageVO.getWritngPassword();

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(FileScrty.encode(qnaManageVO.getWritngPassword()));
    	int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);

        String	passwordConfirmAt = "";
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우

        	passwordConfirmAt = "Y";
        	// Q&A를 수정할 수 있는 화면으로 이동.
        	//return	"forward:/uss/olh/qna/editQna.mdo";
        	
        } else	{					// 작성비밀번호가 틀린경우
        
        	// 작성비밀번호 확인 결과 세팅.
        	//qnaManageVO.setPasswordConfirmAt("N");
        	
        	passwordConfirmAt = "N";
        	
            //model.addAttribute("QnaManageVO", qnaManageVO);
                    	
        	// Q&A 상세조회 화면으로 이동.
        	//return "forward:/uss/olh/qna/detailQna.mdo?passwordConfirmAt=" + passwordConfirmAt;
        }
        modelAndView.addObject("passwordConfirmAt", passwordConfirmAt);
        
        return modelAndView;
    }    
    
    /**
     * Q&A정보를 수정하기 위한 전 처리(비밀번호 암호화)       
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/editQna.mdo")
    public String editQna(
    		@ModelAttribute QnaManageVO qnaManageVO) {    	    		        	
    	        
		// 작성 비밀번호를 얻는다.
		String	writngPassword = qnaManageVO.getWritngPassword();		

		qnaManageService.selectQnaListDetail(qnaManageVO);

		qnaManageVO.setWritngPassword(writngPassword);
		
        return "aramframework/mbl/uss/olh/qna/QnaEdit";
    }

    /**
     * Q&A정보를 수정처리한다.           
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/updateQna.mdo")
    public String updateQna(
    		@ModelAttribute QnaManageVO qnaManageVO,
			BindingResult bindingResult)
    throws Exception {
    	    	
    	// Validation
    	beanValidator.validate(qnaManageVO, bindingResult);
 		if(bindingResult.hasErrors()){
			return "aramframework/mbl/uss/olh/qna/QnaEdit";
		}    	
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if( loginVO != null ) {
			qnaManageVO.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}
 
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(FileScrty.encode(writngPassword));
    	
    	qnaManageService.updateQnaCn(qnaManageVO);

        return "redirect:/uss/olh/qna/listQna.mdo";
    }

    /**
     * Q&A정보를 삭제처리한다.               
     * 
     * @param qnaManageVO
     */
    @RequestMapping("/uss/olh/qna/deleteQna.mdo")
    public String deleteQna(
    		@ModelAttribute QnaManageVO qnaManageVO) {
    	
    	qnaManageService.deleteQnaCn(qnaManageVO);        
        
        return "redirect:/uss/olh/qna/listQna.mdo";
    }
	
}
