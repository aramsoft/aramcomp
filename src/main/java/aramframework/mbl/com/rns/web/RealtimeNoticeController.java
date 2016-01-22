package aramframework.mbl.com.rns.web;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.mbl.com.rns.service.RealtimeNoticeService;
import aramframework.mbl.com.rns.service.RealtimeNoticeVO;

/**
 * 개요
 * -실시간 공지 서비스에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 실시간 공지 등록, 삭제, 조회 요청 사항을 Service와 매핑 처리한다.
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
public class RealtimeNoticeController {
	
	@Autowired
	private RealtimeNoticeService realtimeNoticeService;
	
	/**
	 * 실시간 공지 서비스 메시지 페이지로 이동
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/goRealtimeNoticeMsg.mdo")
	public String goRealtimeNoticeMsg() {
		return "aramframework/mbl/com/rns/MobileRealtimeNoticeMsg";
	}
	
	/**
	 * 실시간 공지서비스 메시지 조회 
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/goRealtimeNoticeMsgJson.mdo")
	public ModelAndView goRealtimeNoticeMsgJson() {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		RealtimeNoticeVO oRealtimeNotice = (RealtimeNoticeVO)realtimeNoticeService.selectRealtimeNoticeMsg();
		modelAndView.addObject("rnsMsg", oRealtimeNotice);
		modelAndView.addObject("success", true);

		modelAndView.setViewName("aramframework/mbl/com/rns/MobileRealtimeNoticeMsg");
		return modelAndView;
	}
	
	/**
	 * 실시간 공지 서비스 공지내용 조회 : Server sent Event용 메서드
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/goEvent.mdo")
	public String goEvent(
			HttpServletRequest req, 
			HttpServletResponse res, 
			ModelMap model) 
	throws Exception {
			
		// 1. 실시간 공지 전송 메시지를 조회
		RealtimeNoticeVO oRealtimeNotice = (RealtimeNoticeVO)realtimeNoticeService.selectRealtimeNoticeMsg();
		if(oRealtimeNotice != null) {
			// 2. 공지 메시지 조합
			// 'data:' 는 server sent event 구현을 위해 필요한 키워드로 변경 하면 실행 불가함.. 
			String noticeMsg = oRealtimeNotice.getSn() + "|" + oRealtimeNotice.getNoticeSj() + "|" + oRealtimeNotice.getNoticeCn(); 
			
			model.addAttribute("data", URLEncoder.encode(noticeMsg, "utf-8"));
			model.addAttribute("retry", "5000");
		}
		
		return "aramframework/mbl/com/rns/MobileRealtimeNoticeData";
	}
	
	/**
	 * [관리자] 실시간 공지 서비스 목록 페이지로 이동
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/listRealtimeNotice.mdo")
	@Secured("ROLE_ADMIN")
	public String listRealtimeNotice() {
		return "aramframework/mbl/com/rns/MobileRealtimeNoticeList";
	}
	
	/**
	 * [관리자] 실시간공지서비스 공지 목록 조회
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mbl/com/rns/listRealtimeNoticeJson.mdo")
	@Secured("ROLE_ADMIN")
	public ModelAndView listRealtimeNoticeJson(
			@ModelAttribute("searchVO") RealtimeNoticeVO realtimeNoticeVO) {
		      
		ModelAndView modelAndView = new ModelAndView("jsonView");

		modelAndView.addObject("rtnList", realtimeNoticeService.selectRealtimeNoticeList(realtimeNoticeVO));
		modelAndView.addObject("success", true);
		
		return modelAndView;
	}
	
	/**
	 * [관리자] 실시간 공지 상세 페이지로 이동
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/detailRealtimeNotice.mdo")
	@Secured("ROLE_ADMIN")
	public String detailRealtimeNotice(
			@RequestParam String searchSn,
			ModelMap model) {
	
		model.addAttribute("param", searchSn);
	
		return "aramframework/mbl/com/rns/MobileRealtimeNotice";
	}
	
	/**
	 * [관리자] 실시간공지서비스 공지 상세 조회
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mbl/com/rns/detailRealtimeNoticeJson.mdo")
	@Secured("ROLE_ADMIN")
	public ModelAndView detailRealtimeNoticeJson(
			@ModelAttribute RealtimeNoticeVO realtimeNoticeVO) {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 1. 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	realtimeNoticeVO.setMberId(loginVO.getId());	
		
		// 2. 실시간 공지 서비스 모바일 목록을 조회 한다.
    	RealtimeNoticeVO oRealtimeNotice = (RealtimeNoticeVO)realtimeNoticeService.selectRealtimeNotice(realtimeNoticeVO);
		
		// 3. 조회 결과를 ModelAndView에 할당한다.
		modelAndView.addObject("resultView", oRealtimeNotice);
		modelAndView.addObject("searchVO", realtimeNoticeVO);
		
		return modelAndView;
	}
	
	/**
	 * [관리자] 실시간 공지 등록 페이지로 이동
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/registRealtimeNotice.mdo")
	@Secured("ROLE_ADMIN")
	public String registRealtimeNotice() {
		
		return "aramframework/mbl/com/rns/MobileRealtimeNoticeInsert";
	}
	
	/**
	 * [관리자] 실시간공지서비스 공지 등록
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mbl/com/rns/insertRealtimeNoticeJson.mdo")
	@Secured("ROLE_ADMIN")
	public ModelAndView insertRealtimeNoticeJson(
			@ModelAttribute RealtimeNoticeVO realtimeNoticeVO) {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	realtimeNoticeVO.setMberId(loginVO.getId());	
    	
		int rtn = realtimeNoticeService.insertRealtimeNotice(realtimeNoticeVO);
		if(rtn > 0) {
			modelAndView.addObject("success", true);
		} else {
			modelAndView.addObject("success", false);
			
		}
        return modelAndView;
	}
	
	/**
	 * [관리자] 실시간공지서비스 공지 삭제
	 * 
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mbl/com/rns/deleteRealtimeNotice.mdo")
	@Secured("ROLE_ADMIN")
	public String deleteRealtimeNotice(
			@ModelAttribute RealtimeNoticeVO realtimeNoticeVO) {

		// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
    	realtimeNoticeVO.setMberId(loginVO.getId());	
    	
		realtimeNoticeService.deleteRealtimeNotice(realtimeNoticeVO);

        return "forward:/mbl/com/rns/listRealtimeNotice.mdo";
	}
	
}
