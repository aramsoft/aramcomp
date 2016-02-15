package aramframework.mbl.cop.adb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.domain.SearchCodeVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cop.adb.domain.AdressBookUserVO;
import aramframework.com.cop.adb.domain.AdressBookVO;
import aramframework.com.cop.adb.service.AdressBookService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 주소록정보를 관리하기 위한 컨트롤러 클래스
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
public class MblAdressBookController {

	@Autowired
    private AdressBookService adressBookService;
    
	@Autowired
	private CmmUseService cmmUseService;

    /**
     * 주소록 정보에 대한 목록 화면을 출력한다.
     * 
     * @param adressBookVO
     */
    @RequestMapping(value="/cop/adb/listAdressBook.mdo")
	@Secured("ROLE_USER")
    public String listAdressBook(
    		@ModelAttribute AdressBookVO adressBookVO) {
        return "aramframework/mbl/cop/adb/AdressBookList";    
    }
    
     /**
     * 주소록 정보에 대한 목록을 조회한다.
     * 
     * @param adressBookVO
     */    
    @RequestMapping("/cop/adb/listAdressBookJson.mdo")
	@Secured("ROLE_USER")
    public ModelAndView listAdressBookJson(
    		@ModelAttribute AdressBookVO adressBookVO, 
    		ModelMap model) {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        adressBookVO.setWrterId(loginVO.getId());
        adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());
        modelAndView.addObject("userId", loginVO.getId());
       
        PaginationInfo paginationInfo = new PaginationInfo();
        adressBookVO.getSearchVO().fillPageInfo(paginationInfo);

        modelAndView.addObject("resultList", adressBookService.selectAdressBookList(adressBookVO));        
 
        int totCnt = adressBookService.selectAdressBookListCnt(adressBookVO);  
        adressBookVO.getSearchVO().setTotalRecordCount(totCnt);

        paginationInfo.setTotalRecordCount(totCnt);
        modelAndView.addObject("paginationInfo", paginationInfo);
 
        return modelAndView;
    }
    
	private void fill_common_code(ModelMap model) {

		// 조직정보를 조회 - ORGNZT_ID정보
		SearchCodeVO vo = new SearchCodeVO();
		vo.setTableNm("COMTN_ORGNZT_INFO");
		model.addAttribute("orgnztList", cmmUseService.selectOgrnztIdList(vo));
	}
	
    /**
     * 주소록등록 화면으로 이동한다.
     * 
     * @param adressBookVO
     */
    @RequestMapping("/cop/adb/registAddressBook.mdo")
	@Secured("ROLE_USER")
    public String registAddressBook(
    		@ModelAttribute AdressBookVO adressBookVO, 
    		ModelMap model) {
		fill_common_code(model);
        return "aramframework/mbl/cop/adb/AdressBookRegist";
    }
 
    /**
     * 주소록 정보를 등록한다.
     * 
     * @param adressBookVO
     */    
    @RequestMapping("/cop/adb/insertAddressBook.mdo") 
	@Secured("ROLE_USER")
    public String insertAddressBook(
			@ModelAttribute AdressBookVO adressBookVO) {
            
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
         
        adressBookVO.setWrterId(loginVO.getId());
        adressBookVO.setFrstRegisterId(loginVO.getId());
         
        String[] tempId = adressBookVO.getUserIds().split(",");
        
        for(int i =0; i < tempId.length; i++){
            if(!tempId[i].equals("")){
                AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]); 
                adressBookVO.getAdbkMan().add(adbkUser);    
            }
        }    
 
        adressBookVO.setTrgetOrgnztId(loginVO.getOrgnztId());
        
        adressBookService.insertAdressBook(adressBookVO);
        
        return "forward:/cop/adb/listAdressBook.mdo";
    }
   
    /**
     * 주소록상세조회수정 화면으로 이동한다.
     * 
     * @param adressBookVO
     */    
    @RequestMapping("/cop/adb/editAddressBook.mdo")
	@Secured("ROLE_USER")
    public String editAddressBook(
			@ModelAttribute AdressBookVO adressBookVO, 
    		ModelMap model) {
         
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        
        adressBookService.selectAdressBook(adressBookVO);
         
        boolean writer = false;
        String id = "";
              
        for(int i = 0; i < adressBookVO.getAdbkMan().size(); i++){
            if (adressBookVO.getAdbkMan().get(i).getNcrdId() == null) {
            	adressBookVO.getAdbkMan().get(i).setNcrdId("");
            }          
            
            if (adressBookVO.getAdbkMan().get(i).getEmplyrId() == null) {
            	adressBookVO.getAdbkMan().get(i).setEmplyrId("");
            }
        }
        
        for(int i = 0; i < adressBookVO.getAdbkMan().size(); i++){
            if (adressBookVO.getAdbkMan().get(i).getEmplyrId().equals("")){
                id += adressBookVO.getAdbkMan().get(i).getNcrdId() + ",";
            } else {
                id += adressBookVO.getAdbkMan().get(i).getEmplyrId() + ",";
            }               
        }
      
        adressBookVO.setUserIds(id);
        
        if(adressBookVO.getWrterId().equals(loginVO.getId())){
            writer = true;
        }
        model.addAttribute("writer" , writer);

        fill_common_code(model);
        return "aramframework/mbl/cop/adb/AdressBookEdit";
    }
        
    /**
     * 주소록 정보를 수정한다.
     * 
     * @param adressBookVO
     */ 
    @RequestMapping("/cop/adb/updateAdressBook.mdo") 
	@Secured("ROLE_USER")
    public String updateAdressBook(
			@ModelAttribute AdressBookVO adressBookVO) {
            
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        
        String[] tempId = adressBookVO.getUserIds().split(",");
             
        for(int i =0; i < tempId.length; i++){
            if(!tempId[i].equals("")){
                AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]); 
                adressBookVO.getAdbkMan().add(adbkUser);    
            }
        }          
        
        adressBookVO.setLastUpdusrId(loginVO.getId());
        adressBookVO.setUseAt("Y");
        
        adressBookService.updateAdressBook(adressBookVO); 
         
        return "forward:/cop/adb/listAdressBook.mdo";
    }
        
    /**
     * 주소록을 삭제한다.
     * 
     * @param adressBookVO
     */
    @RequestMapping("/cop/adb/deleteAddressBook.mdo")
	@Secured("ROLE_USER")
    public String deleteAdressBook(
    		@ModelAttribute AdressBookVO adressBookVO) {
              
        LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
              
        adressBookService.selectAdressBook(adressBookVO);
 
        adressBookVO.setUseAt("N");
        adressBookVO.setLastUpdusrId(loginVO.getId());
        
        adressBookService.deleteAdressBook(adressBookVO);               

        return "forward:/cop/adb/listAdressBook.mdo";
    }    

    /**
     * 주소록 구성원 찾기 팝업화면으로 이동한다.
     * 
     * @param adressBookVO
     */
    @RequestMapping("/cop/adb/listUserPopup.mdo")
    public String listUserPopup(
    		@ModelAttribute AdressBookVO adressBookVO, 
            @RequestParam(value="userIds", required = false) String userIds,
            @RequestParam(value="checkCnd", required = false) String checkCnd, 
            ModelMap model) {  

        model.addAttribute("userIds", userIds);
        model.addAttribute("checkCnd", checkCnd);
        
        return "aramframework/mbl/cop/adb/AdressBookPopup";
    }
    
    /**
     * 주소록 등록가능한 구성원을 조회한다.
     * 
     * @param adressBookUserVO
     */
    @RequestMapping("/cop/adb/listUserPopupJson.mdo") 
    public ModelAndView listUserPopupJson(
			@ModelAttribute AdressBookUserVO adressBookUserVO) {
        
        ModelAndView modelAndView = new ModelAndView("jsonView");
        
        if(adressBookUserVO.getSearchVO().getSearchCondition() == null 
        		|| adressBookUserVO.getSearchVO().getSearchCondition().equals("")){
        	adressBookUserVO.getSearchVO().setSearchCondition("0");
        }
        
        PaginationInfo paginationInfo = new PaginationInfo();
        adressBookUserVO.getSearchVO().fillPageInfo(paginationInfo);
      
		List<EgovMap> resultList = null;
		int totCnt = 0;
		if (adressBookUserVO.getSearchVO().getSearchCondition().equals("0")) {
			resultList = adressBookService.selectManList(adressBookUserVO.getSearchVO());
			totCnt = adressBookService.selectManListCnt(adressBookUserVO.getSearchVO());
		} else {
			resultList = adressBookService.selectCardList(adressBookUserVO.getSearchVO());
			totCnt = adressBookService.selectCardListCnt(adressBookUserVO.getSearchVO());
		}

        modelAndView.addObject("resultList", resultList);

		adressBookUserVO.getSearchVO().setTotalRecordCount(totCnt); 

        paginationInfo.setTotalRecordCount(totCnt); 
        modelAndView.addObject("paginationInfo", paginationInfo);
      
        return modelAndView;
    }

    /**
     * 주소록의 구성원을 추가한다.
     * 
     * @param adressBookVO
     * @param checkCnd
     */
    @RequestMapping("/cop/adb/insertAdressBookUser.mdo")
	@Secured("ROLE_USER")
    public String insertAdressBookUser(
			@ModelAttribute AdressBookVO adressBookVO, 
            @RequestParam String checkCnd,
            ModelMap model) {       
 
        String[] tempId = adressBookVO.getUserIds().split(",");
           
        for(int i =0; i < tempId.length; i++){
            if(!tempId[i].equals("")){
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
            }
        }
        
        if(checkCnd.equals("regist"))
            return "aramframework/mbl/cop/adb/AdressBookRegist";
        else{
            model.addAttribute("writer" , true);          
            return "aramframework/mbl/cop/adb/AdressBookEdit";
        }
    }    
    
    /**
     * 주소록의 구성원을 삭제한다.
     * 
     * @param adressBookVO
     * @param checkWord
     * @param checkCnd
     */
    @RequestMapping("/cop/adb/deleteAdressBookUser.mdo")
	@Secured("ROLE_USER")
    public String deleteAdressBookUser( 
			@ModelAttribute AdressBookVO adressBookVO, 
            @RequestParam String checkWord, 
            @RequestParam String checkCnd,
            ModelMap model) {
       
        String[] tempId = adressBookVO.getUserIds().split(",");       
        
        String id = "";
                                
        for(int i =0; i < tempId.length; i++){
            
            if(tempId[i].equals(checkWord)){
                continue;
            }
            
            if(!tempId[i].equals("")){
				AdressBookUserVO adbkUser = adressBookService.selectAdbkUser(tempId[i]);
				adressBookVO.getAdbkMan().add(adbkUser);
            } 
            
            id += tempId[i] + ",";          
        }      
        
        adressBookVO.setUserIds(id);
      
        if(checkCnd.equals("regist"))
            return "aramframework/mbl/cop/adb/AdressBookRegist";
        else{
            model.addAttribute("writer" , true);        
            return "aramframework/mbl/cop/adb/AdressBookEdit";
        }
    }
    
}
