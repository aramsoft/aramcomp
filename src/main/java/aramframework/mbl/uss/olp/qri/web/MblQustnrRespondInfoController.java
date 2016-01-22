package aramframework.mbl.uss.olp.qri.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.service.CmmUseService;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.uss.olp.qri.service.QustnrRespondInfoService;
import aramframework.com.uss.olp.qri.service.QustnrRespondInfoVO;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageService;
import aramframework.com.uss.olp.qrm.service.QustnrRespondManageVO;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문조사 Mobile Controller Class 구현 
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
public class MblQustnrRespondInfoController {
	
	@Autowired
	private QustnrRespondInfoService qustnrRespondInfoService;
	 
	@Autowired
	private QustnrRespondManageService qustnrRespondManageService;
	
	@Autowired
	private CmmUseService cmmUseService;
	
	/**
	 * 설문템플릿을 적용한다.
	 * 
	 * @param commandMap
	 */
	@RequestMapping(value="/uss/olp/qri/template/template.mdo")
	public String template(
			@CommandMap Map<String, Object> commandMap,  
    		ModelMap model) {

		String sTemplateUrl = ((String)commandMap.get("templateUrl")).split("/com/")[0] + "/mbl/" + ((String)commandMap.get("templateUrl")).split("/com/")[1];
    	     	 
		return sTemplateUrl; 
	}
	
	/**
	 * 설문조사(참여) 목록 화면을 출력한다. 
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value="/uss/olp/qri/listQustnrRespondInfoUser.mdo")
	public String listQustnrRespondInfo(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO,  
			ModelMap model) {
		return "aramframework/mbl/uss/olp/qri/QustnrRespondInfoUserList";
	}
	
	/**
	 * 설문조사(참여) 목록을 조회한다. 
	 * 
	 * @param qustnrRespondInfoVO
	 */
	@RequestMapping(value="/uss/olp/qri/listQustnrRespondInfoUserJson.mdo")
	public ModelAndView listQustnrRespondInfoJson(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO,  
    		ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
    	qustnrRespondInfoVO.fillPageInfo(paginationInfo);
		
		modelAndView.addObject("resultList", qustnrRespondInfoService.selectQustnrRespondInfoManageList(qustnrRespondInfoVO));
        
        int totCnt = (Integer)qustnrRespondInfoService.selectQustnrRespondInfoManageListCnt(qustnrRespondInfoVO);
        qustnrRespondInfoVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		modelAndView.addObject("paginationInfo", paginationInfo);
        
		return modelAndView; 
	}
	
	/**
	 * 설문조사에 참여한다. (등록)
	 * 
	 * @param qustnrRespondInfoVO
	 * @param commandMap
	 */
	@RequestMapping(value="/uss/olp/qri/registQustnrRespondInfoUser.mdo")
	@Secured("ROLE_USER")
	public String registQustnrRespondInfo(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO,  
			@CommandMap Map<String, Object> commandMap,  
    		ModelMap model) {
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}
		
    	if(loginVO.getUniqId() != null){
			String uniqId = loginVO.getUniqId();	
			commandMap.put("uniqId", uniqId);
			
        	 //사용자정보
        	 model.addAttribute("emplyrinfo", qustnrRespondInfoService.selectQustnrRespondEmplyrinfo(uniqId));
     	}
    	 
      	//성별코드조회
     	model.addAttribute("sexdstnCode", cmmUseService.selectCmmCodeList("COM014"));
     	//직업코드조회
     	model.addAttribute("occpTyCode", cmmUseService.selectCmmCodeList("COM034"));
     	
		// 설문지정보ID 설정
		String qestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
		model.addAttribute("qestnrId", qestnrId);
		// 설문정보
		model.addAttribute("comtnqestnrinfo", qustnrRespondInfoService.selectQustnrRespondQestnrInfo(qestnrId));
		// 문항정보
		model.addAttribute("comtnqustnrqesitm", qustnrRespondInfoService.selectQustnrRespondQustnrQesitm(qestnrId));
		// 항목정보
		model.addAttribute("comtnqustnriem", qustnrRespondInfoService.selectQustnrRespondQustnrIem(qestnrId));
        
		return "aramframework/mbl/uss/olp/qri/QustnrRespondInfoUserRegist"; 
	}
	
	/**
	 * 설문조사에 참여한다. (등록)
	 * 
	 * @param qustnrRespondInfoVO
	 * @param commandMap
	 */
	@RequestMapping(value="/uss/olp/qri/insertQustnrRespondInfoUser.mdo")
	@Secured("ROLE_USER")
	public String insertQustnrRespondInfo(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			@CommandMap Map<String, Object> commandMap,  
    		ModelMap model) {
		
		LoginVO loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
		
		//설문조사 처리 START
		String sKey ="";
		String sVal ="";
       	for(Object key:commandMap.keySet()){

       		sKey = key.toString();
       	
       		//설문문항정보 추출
       		if(sKey.length() > 6 && sKey.substring(0, 6).equals("QQESTN")){

      			
       			//설문조사 등록
           		//객관식 답안 처리
           		if( ((String)commandMap.get("TY_"+key)).equals("1") ){
        	           			
       				if( commandMap.get(key) instanceof String){
           				sVal = (String)commandMap.get(key);
           				
	           			QustnrRespondInfoVO vo = new QustnrRespondInfoVO(); 

	           			vo.setQestnrId((String)commandMap.get("qestnrId"));
	           			vo.setQestnrQesitmId(sKey);
	           			vo.setQustnrIemId(sVal);
	           			
	           			vo.setRespondAnswerCn("");
	           			
	           			vo.setRespondNm(loginVO.getName());
	           			vo.setEtcAnswerCn((String)commandMap.get("ETC_" + sVal));
	           			
	           			vo.setFrstRegisterId(loginVO.getUniqId());
	            		
	           			qustnrRespondInfoService.insertQustnrRespondInfo(vo);   	
       				}else{
        				String[] arrVal = (String[]) commandMap.get(key);
        				for(int g=0; g < arrVal.length; g++ ){
        					//("QQESTN arr :" + arrVal[g]);
		           			QustnrRespondInfoVO vo = new QustnrRespondInfoVO(); 

		           			vo.setQestnrId((String)commandMap.get("qestnrId"));
		           			vo.setQestnrQesitmId(sKey);
		           			vo.setQustnrIemId(arrVal[g]);
		           			
		           			vo.setRespondAnswerCn("");
		           			
		           			vo.setRespondNm(loginVO.getName());
		           			vo.setEtcAnswerCn((String)commandMap.get("ETC_" + arrVal[g]));
		           			
		           			vo.setFrstRegisterId(loginVO.getUniqId());
		            		
		           			qustnrRespondInfoService.insertQustnrRespondInfo(vo);
        				}
       				}
           			
           		//주관식 답안 처리
           		}else if( ((String)commandMap.get("TY_"+key)).equals("2") ){
           			
           			sVal = (String)commandMap.get(key);
           			
           			QustnrRespondInfoVO vo = new QustnrRespondInfoVO(); 
           			
           			vo.setQestnrId((String)commandMap.get("qestnrId"));
           			vo.setQestnrQesitmId(sKey);
           			vo.setQustnrIemId(null);
           			
           			vo.setRespondAnswerCn(sVal);
           			
           			vo.setRespondNm(loginVO.getName());
           			vo.setEtcAnswerCn(null);
           					
           			vo.setFrstRegisterId(loginVO.getUniqId());
            		
           			qustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);
           		}
       		}
    	}
       	
   		//설문응답자 처리
   		QustnrRespondManageVO vo = new QustnrRespondManageVO();
   		
   		vo.setQestnrId((String)commandMap.get("qestnrId"));
   		
   		vo.setSexdstnCode((String)commandMap.get("sexdstnCode"));
   		vo.setOccpTyCode((String)commandMap.get("occpTyCode"));
   		vo.setBrth(((String)commandMap.get("brth")).replaceAll("-", ""));
   		vo.setRespondNm((String)commandMap.get("respondNm"));
   		
   		vo.setFrstRegisterId(loginVO.getUniqId());

   		qustnrRespondManageService.insertQustnrRespondManage(vo);

       	return "redirect:/uss/olp/qri/listQustnrRespondInfoUser.mdo";
	}
	
	/**
	 * 설문조사 전체 통계를 조회한다.
	 * 
	 * @param qustnrRespondInfoVO
	 * @param commandMap
	 */
	@RequestMapping(value="/uss/olp/qri/statisticsQustnrRespondInfo.mdo")
	public String statisticsQustnrRespondInfo(
			@ModelAttribute QustnrRespondInfoVO qustnrRespondInfoVO, 
			@CommandMap Map<String, Object> commandMap,  
    		ModelMap model) {
		
		// 설문지정보ID 설정
		String qestnrId = commandMap.get("qestnrId") == null ? "" : (String) commandMap.get("qestnrId");
		model.addAttribute("qestnrId", qestnrId);
		
		// 설문정보
		model.addAttribute("Comtnqestnrinfo", qustnrRespondInfoService.selectQustnrRespondQestnrInfo(qestnrId));
		// 문항정보
		model.addAttribute("Comtnqustnrqesitm", qustnrRespondInfoService.selectQustnrRespondQustnrQesitm(qestnrId));
		// 항목정보
		model.addAttribute("Comtnqustnriem", qustnrRespondInfoService.selectQustnrRespondQustnrIem(qestnrId));

		// 객관식통계 답안
		model.addAttribute("qestnrStatistic1", qustnrRespondInfoService.selectQustnrRespondStatistics1(qestnrId));
		// 주관식통계 답안
		model.addAttribute("qestnrStatistic2", qustnrRespondInfoService.selectQustnrRespondStatistics2(qestnrId));
  	     	 
    	return "aramframework/mbl/uss/olp/qri/QustnrRespondInfoStatistics"; 
	}
	
}
