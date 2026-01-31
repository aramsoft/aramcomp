package aramframework.com.sec.rnc.web;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import aramframework.com.sec.rnc.service.RlnmManageService;

/**
 * 실명인증관련 요청을 비지니스 클래스로 전달하고 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Controller
public class RlnmManageController {

	@Autowired
	private RlnmManageService rlnmManageService;

	/** Log Info */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 실명인증확인화면 호출(주민번호)
	 * 
	 */
	@RequestMapping("/sec/rnc/confirmRlnm.do")
	public String confirmRlnm(
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {

		String realName = request.getParameter("realname") == null ? "" : request.getParameter("realname");
		String ihidNum = request.getParameter("ihidnum") == null ? "" : request.getParameter("ihidnum");
		String sbscrbTy = request.getParameter("sbscrbTy") == null ? "" : request.getParameter("sbscrbTy");

		if( "".equals(realName) || "".equals(ihidNum) ) {
			throw new EgovBizException("이름과 주민번호는 필수입니다.");
		}

		model.addAttribute("nextUrlName", request.getParameter("nextUrlName")); 
		// 다음단계버튼명(이동할 URL에 따른)
		model.addAttribute("nextUrl", request.getParameter("nextUrl")); 
		// 다음단계로 이동할 URL
		String result = "";

		try {
			result = rlnmManageService.rlnmCnfirm(ihidNum, realName, sbscrbTy);
		} catch (Exception e) {
			log.error("Exception:  " + e.getClass().getName());
			log.error("Exception  Message:  " + e.getMessage());
		} 
		
		model.addAttribute("result_tmp", result + "__" + result.substring(0, 2));
		if (result.substring(0, 2).equals("00")) {
			result = "success.user.rlnmCnfirm";
		} else if (result.substring(0, 2).equals("01")) {
			result = "fail.user.rlnmCnfirm";
		} else {
			result = "fail.user.connectFail";
		}
		model.addAttribute("result", result); // 실명확인 결과

		return "com/sec/rnc/RlnmCnfirm";
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * 
	 * @exception Exception
	 */
	@RequestMapping("/sec/rnc/confirmRlnmPin.do")
	public String confirmRlnmPin(
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {

		String realName = request.getParameter("realName") == null ? "" : request.getParameter("realName");

		model.addAttribute("sbscrbTy", request.getParameter("sbscrbTy")); // 사용자유형
		model.addAttribute("nextUrlName", request.getParameter("nextUrlName")); // 다음단계버튼명(이동할 URL에 따른)
		model.addAttribute("nextUrl", request.getParameter("nextUrl")); // 다음단계로 이동할 URL

		String result = request.getParameter("result"); // 결과_최초는 블랭크
		log.debug("realName:" + realName);

		if ("".equals(result) && "".equals(realName)) {
			result = "info.user.rlnmPinCnfirm";
			model.addAttribute("result", result); // 실명확인 결과
		} else {
			if (!realName.equals("null") && !realName.equals("")) {
				result = "success.user.rlnmPinCnfirm";
				model.addAttribute("result", result); // 실명확인 결과 메시지
				realName = URLDecoder.decode(realName, "UTF-8"); // gpin배포샘플은 기본인코딩이 euc-kr
				model.addAttribute("realName", realName); // 실명확인 결과 이름
				// return "forward:" + request.getParameter("nextUrl"); 성공시만
				// 체크하여 직접 다음 URL로 이동할수도 있음
			} else {
				result = "fail.user.rlnmPinCnfirm";
				model.addAttribute("result", result); // 실명확인 결과 메시지
				realName = new String(realName.getBytes("UTF-8"), "EUC-KR"); // gpin배포샘플은 기본인코딩이 euc-kr
				model.addAttribute("realName", realName); // 실명확인 결과 이름
			}
		}
		log.debug("result:" + result);
		return "com/sec/rnc/RlnmPinCnfirm";
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * 
	 */
	@RequestMapping("/sec/rnc/callGPin.do")
	public String callGPin(
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {
		model.addAttribute("gpinId", request.getParameter("gpinId")); // GPIN
																			// 아이디
		model.addAttribute("gpinPassword", request.getParameter("gpinPassword")); // GPIN 패스워드
		model.addAttribute("sbscrbTy", request.getParameter("sbscrbTy")); // 사용자유형
		// model.addAttribute("urlName", request.getParameter("urlName"));
		// //다음단계버튼명(이동할 URL에 따른)
		// model.addAttribute("urlInfo", request.getParameter("urlInfo"));
		// //다음단계로 이동할 URL
		model.addAttribute("nextUrlName", request.getParameter("nextUrlName")); 
		// 다음단계버튼명(이동할 URL에 따른)
		model.addAttribute("nextUrl", request.getParameter("nextUrl")); 
		// 다음단계로 이동할 URL

		return "com/sec/rnc/gpin/Sample-AuthRequest";
	}

}