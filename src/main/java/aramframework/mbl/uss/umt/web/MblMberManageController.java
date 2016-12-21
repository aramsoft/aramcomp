package aramframework.mbl.uss.umt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.uat.uia.domain.LoginVO;
import aramframework.com.uss.umt.domain.MberManageVO;
import aramframework.com.uss.umt.service.MberManageService;

/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 
 * 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Controller
public class MblMberManageController {

    /** mberManageService */
	@Autowired
    private MberManageService mberManageService;
    
    /**
     * 일반회원가입신청등록처리후로그인화면으로 이동한다.
     * 
     * @param mberManageVO 
     */
    @RequestMapping("/uss/umt/MberSbscrb.mdo")
    public String MberSbscrb(
            @ModelAttribute MberManageVO mberManageVO) {
    	
    	//가입상태 초기화
    	mberManageVO.setMberSttus("A");
    	//일반회원가입신청 등록시 일반회원등록기능을 사용하여 등록한다. 
    	mberManageService.insertMber(mberManageVO);
        return "forward:/uat/uia/egovLoginUsr.mdo";
    }
    
    /**
     * 일반회원 약관확인
     * 
     */
    @RequestMapping("/uss/umt/StplatCnfirmMber.mdo")
	@Secured("ROLE_USER")
    public String StplatCnfirmMber(ModelMap model) {
        //일반회원용 약관 아이디 설정
        String stplatId = "";
        //회원가입유형 설정-일반회원
        String sbscrbTy = "";
        
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        
        sbscrbTy = loginVO.getUserSe();
        
        if("USR01".equals(sbscrbTy)){
        	stplatId = "STPLAT_0000000000001";	// 개인회원 약관
        }else{
        	stplatId = "STPLAT_0000000000002";  // 기업회원 약관      	
        }
        
        //약관정보 조회 
        model.addAttribute("stplatVO", mberManageService.selectStplat(stplatId));   //약관정보 포함
        model.addAttribute("sbscrbTy", sbscrbTy);     //회원가입유형 포함
        
        return "aramframework/mbl/uss/umt/StplatCnfirm";
    }
            
}