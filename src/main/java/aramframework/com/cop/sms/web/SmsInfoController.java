package aramframework.com.cop.sms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.userdetails.UserDetailsHelper;
import aramframework.com.cmm.util.MessageHelper;
import aramframework.com.cmm.util.WebUtil;
import aramframework.com.cop.sms.domain.SmsVO;
import aramframework.com.cop.sms.service.SmsInfoService;
import aramframework.com.uat.uia.domain.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 문자메시지 서비스 컨트롤러 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class SmsInfoController {

	@Autowired
	private SmsInfoService smsInfoService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 문자메시지 목록을 조회한다.
	 * 
	 * @param smsVO
	 */
	@IncludedInfo(name = "문자메시지", order = 4510, gid = 45)
	@RequestMapping("/cop/sms/listSms.do")
	@Secured("ROLE_USER")
	public String listSms(
			@ModelAttribute SmsVO smsVO, 
			ModelMap model) 
	throws Exception {

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		smsVO.setUniqId(loginVO.getUniqId());

		PaginationInfo paginationInfo = new PaginationInfo();
		smsVO.fillPageInfo(paginationInfo);

		model.addAttribute("resultList", smsInfoService.selectSmsInfs(smsVO));

		int totCnt = smsInfoService.selectSmsInfsCnt(smsVO);
		smsVO.setTotalRecordCount(totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return WebUtil.adjustViewName("/cop/sms/SmsInfoList");
	}

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param smsVO
	 */
	@RequestMapping("/cop/sms/detailSms.do")
	@Secured("ROLE_USER")
	public String detailSms(
			@ModelAttribute SmsVO smsVO, 
			ModelMap model) 
	throws Exception {

		smsInfoService.selectSmsInf(smsVO);

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("sessionUniqId", loginVO.getUniqId());

		return WebUtil.adjustViewName("/cop/sms/SmsInfoDetail");
	}

	/**
	 * 문자메시지 전송(등록)을 위한 전송 페이지로 이동한다.
	 * 
	 * @param smsVO
	 */
	@RequestMapping("/cop/sms/registSms.do")
	@Secured("ROLE_USER")
	public String registSms(
			@ModelAttribute SmsVO smsVO) {

		return WebUtil.adjustViewName("/cop/sms/SmsInfoRegist");
	}

	/**
	 * 문자메시지 전송을 요청한다.
	 * 
	 * @param smsVO
	 */
	@RequestMapping("/cop/sms/insertSms.do")
	@Secured("ROLE_USER")
	public String insertSms(
			@ModelAttribute SmsVO smsVO, 
			BindingResult bindingResult, 
			ModelMap model) 
	throws Exception {

		beanValidator.validate(smsVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return WebUtil.adjustViewName("/cop/sms/SmsInfoRegist");
		}

		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		smsVO.setFrstRegisterId(loginVO.getUniqId());

		smsInfoService.insertSmsInf(smsVO);

		model.addAttribute("message", MessageHelper.getMessage("success.common.insert"));
		return WebUtil.redirectJsp(model, "/cop/sms/listSms.do");
	}

}
