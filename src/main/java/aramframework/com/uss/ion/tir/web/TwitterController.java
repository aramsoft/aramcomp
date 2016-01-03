package aramframework.com.uss.ion.tir.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import aramframework.com.cmm.LoginVO;
import aramframework.com.cmm.annotation.IncludedInfo;
import aramframework.com.cmm.util.UserDetailsHelper;
import aramframework.com.uss.ion.tir.service.TwitterRecptnService;
import aramframework.com.uss.ion.tir.service.TwitterTrnsmitService;
import aramframework.com.uss.ion.tir.service.TwitterInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 트위터 수신, 송신를 처리하는 Controller Class 구현
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
public class TwitterController {

	@Resource(name = "twitterRecptnService")
	private TwitterRecptnService twitterRecptnService;

	@Resource(name = "twitterTrnsmitService")
	private TwitterTrnsmitService twitterTrnsmitService;

	/**
	 * 트위터를 메인 인증 페이지조회
	 * 
	 * @return "aramframework/com/uss/ion/tir/TwitterMain"
	 * @throws Exception
	 *             -Exception Throws
	 */
	@IncludedInfo(name = "Twitter연동(송신)", order = 5240, gid = 50)
	@RequestMapping(value = "/uss/ion/tir/selectTwitterMainTransmit.do")
	@Secured("ROLE_USER")
	public String EgovTwitterMainTransmit(ModelMap model) {
		model.addAttribute("cmd", "TRNSMIT");
		return "aramframework/com/uss/ion/tir/TwitterMain";
	}

	/**
	 * 트위터를 메인 인증 페이지조회
	 * 
	 * @return "aramframework/com/uss/ion/tir/TwitterMain"
	 * @throws Exception
	 *             -Exception Throws
	 */
	@IncludedInfo(name = "Twitter연동(수신)", order = 5241, gid = 50)
	@RequestMapping(value = "/uss/ion/tir/selectTwitterMainReceive.do")
	@Secured("ROLE_USER")
	public String EgovTwitterMainReceive(ModelMap model) {
		model.addAttribute("cmd", "RECPTN");
		return "aramframework/com/uss/ion/tir/TwitterMain";
	}

	/**
	 * 트위터를 인증키 관리 페이지를 조회한다.
	 * 
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return "aramframework/com/uss/ion/tir/TwitterAccount"
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterAccount.do", method = RequestMethod.GET)
	public String EgovTwitterAccountGet(ModelMap model) throws Exception {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String usid = loginVO.getUniqId();

		EgovMap mapResult = twitterTrnsmitService.selectTwitterAccount(usid);

		// Consumer key/Consumer secret 키 값 조회
		if (mapResult == null) {
			model.addAttribute("consumerKey", "");
			model.addAttribute("consumerSecret", "");
		} else {
			model.addAttribute("consumerKey", (String) mapResult.get("CONSUMER_KEY"));
			model.addAttribute("consumerSecret", (String) mapResult.get("CONSUMER_SECRET"));
		}

		return "aramframework/com/uss/ion/tir/TwitterAccount";
	}

	/**
	 * 트위터를 인증키 관리 페이지를 수정한다.
	 * 
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterAccount.do", method = RequestMethod.POST)
	public String EgovTwitterAccountPost(
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) 
	throws Exception {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String usid = loginVO.getUniqId();

		String sConsumerKey = request.getParameter("ConsumerKey") == null ? "" : (String) request.getParameter("ConsumerKey");
		String sConsumerSecret = request.getParameter("ConsumerSecret") == null ? "" : (String) request.getParameter("ConsumerSecret");

		twitterTrnsmitService.selectTwitterAccount(usid);

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", usid);
		hmPram.put("consumerKey", sConsumerKey);
		hmPram.put("consumerSecret", sConsumerSecret);
		hmPram.put("frstRegisterId", (String) loginVO.getUniqId());
		hmPram.put("lastUpdusrId", (String) loginVO.getUniqId());

		if (twitterTrnsmitService.selectTwitterAccountCheck(usid) > 0) {
			twitterTrnsmitService.updateTwitterAccount(hmPram);
		} else {
			twitterTrnsmitService.insertTwitterAccount(hmPram);
		}

		// 저장된키 키 Attribute 설정
		model.addAttribute("consumerKey", sConsumerKey);
		model.addAttribute("consumerSecret", sConsumerSecret);

		// 트위터 세션정보 삭제
		WebUtils.setSessionAttribute(request, "sCONSUMER_KEY", null);
		WebUtils.setSessionAttribute(request, "sCONSUMER_SECRET", null);
		WebUtils.setSessionAttribute(request, "atoken", null);
		WebUtils.setSessionAttribute(request, "astoken", null);

		// 저장메세지 설정
		String ReusltScript = "";

		ReusltScript += "<script type='text/javaScript' language='javascript'>";
		ReusltScript += "alert(' 작성된  트위터 인증키(ConsumerKey/ConsumerSecret)를 저장 하였습니다!  ');";
		ReusltScript += "</script>";

		model.addAttribute("reusltScript", ReusltScript);

		return "aramframework/com/uss/ion/tir/TwitterAccount";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * 
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return "aramframework/com/uss/ion/tir/TwitterPopup"
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopup.do")
	public String EgovTwitterPopupGet(ModelMap model) throws Exception {

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String usid = loginVO.getUniqId();

		EgovMap mapResult = twitterTrnsmitService.selectTwitterAccount(usid);

		// Consumer key/Consumer secret 키 값 조회
		if (mapResult == null) {
			model.addAttribute("consumerKey", "");
			model.addAttribute("consumerSecret", "");
		} else {
			model.addAttribute("consumerKey", (String) mapResult.get("CONSUMER_KEY"));
			model.addAttribute("consumerSecret", (String) mapResult.get("CONSUMER_SECRET"));
		}

		return "aramframework/com/uss/ion/tir/TwitterPopup";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * 
	 * @param searchVO
	 *            -트위터 Model
	 * @param twitterInfo
	 *            -트위터 Model
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopupActor.do")
	public String EgovTwitterPopupPost(
			HttpServletRequest request, 
			ModelMap model) 
	throws Exception {

		String sCheckKey = request.getParameter("chkKey") == null ? "" : request.getParameter("chkKey");

		String sConsumerKey = request.getParameter("ConsumerKey") == null ? "" : request.getParameter("ConsumerKey");
		String sConsumerSecret = request.getParameter("ConsumerSecret") == null ? "" : request.getParameter("ConsumerSecret");

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
		String usid = loginVO.getUniqId();

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", usid);
		hmPram.put("consumerKey", sConsumerKey);
		hmPram.put("consumerSecret", sConsumerSecret);
		hmPram.put("frstRegisterId", (String) loginVO.getUniqId());
		hmPram.put("lastUpdusrId", (String) loginVO.getUniqId());

		// Consumer key/Consumer secret 키 값 저장 체크시
		if (sCheckKey.equals("1")) {
			if (twitterTrnsmitService.selectTwitterAccountCheck(usid) > 0) {
				twitterTrnsmitService.updateTwitterAccount(hmPram);
			} else {
				twitterTrnsmitService.insertTwitterAccount(hmPram);
			}

		} else {
			twitterTrnsmitService.deleteTwitterAccount(hmPram);
		}

		return "aramframework/com/uss/ion/tir/TwitterPopupActor";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * 
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopupProcess.do")
	public String EgovTwitterPopupProcess(ModelMap model) throws Exception {
		return "aramframework/com/uss/ion/tir/TwitterPopupProcess";
	}

	/**
	 * 트위터를 수신을 조회 처리한다.
	 * 
	 * @param searchVO
	 *            -트위터 Model
	 * @param twitterInfo
	 *            -트위터 Model
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @param request
	 *            -HttpServletRequest 객체
	 * @param response
	 *            -HttpServletResponse 객체
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/listTwitterRecptn.do")
	public String EgovTwitterRecptnPost(
			@ModelAttribute("searchVO") TwitterInfoVO searchVO, 
			@ModelAttribute("twitterInfo") TwitterInfoVO twitterInfo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) 
	throws Exception {

		// 리턴 URL
		String sLocationUrl = "aramframework/com/uss/ion/tir/TwitterRecptn";

		/** 네비게이션 정보 설정 */
		PaginationInfo paginationInfo = new PaginationInfo();
		searchVO.fillPageInfo(paginationInfo);

		String sCONSUMER_KEY = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_SECRET");

		String atoken = (String) WebUtils.getSessionAttribute(request, "atoken");
		String astoken = (String) WebUtils.getSessionAttribute(request, "astoken");

		int nPageSize = 50;

		HashMap<String, String> hmParam = new HashMap<String, String>();

		try {
			if (request.getParameter("pageSize") != null) {
				if (!((String) request.getParameter("pageSize")).equals("")) {
					nPageSize = Integer.parseInt(String.valueOf(request.getParameter("pageSize")));
				}
			}

			// 인증키값 설정
			hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
			hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
			hmParam.put("atoken", atoken);
			hmParam.put("astoken", astoken);
			// 엑서스 토큰 키 설정
			AccessToken accessToken = new AccessToken(atoken, astoken);

			// 트위터 객체선언
			Twitter twitter = new TwitterFactory().getOAuthAuthorizedInstance(sCONSUMER_KEY, sCONSUMER_SECRET, accessToken);

			twitter.verifyCredentials();
			twitter.getFriendsTimeline().subList(0, 100);

			model.addAttribute("resultList", twitterRecptnService.twitterRecptnList(hmParam, nPageSize));
			model.addAttribute("pageSize", nPageSize);
			// 전체 페이지 갯수
		} catch (Exception e) {
			// e.printStackTrace();
			throw e; // 2011.10.10 보안점검 후속조치
		}

		return sLocationUrl;

	}

	/**
	 * 트위터를 송신 페이지를 조회 한다.
	 * 
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/registTwitterTrnsmit.do", method = RequestMethod.GET)
	public String EgovTwitterTrnsmitGet(ModelMap model) throws Exception {

		model.addAttribute("twitterInfo", new TwitterInfoVO());
		return "aramframework/com/uss/ion/tir/TwitterTrnsmit";
	}

	/**
	 * 트위터를 송신을 등록 처리 한다.
	 * 
	 * @param searchVO
	 *            -트위터 Model
	 * @param twitterInfo
	 *            -트위터 Model
	 * @param request
	 *            -HttpServletRequest 객체
	 * @param response
	 *            -HttpServletResponse 객체
	 * @param model
	 *            -Spring 제공하는 ModelMap
	 * @return String -리턴 URL
	 * @throws Exception
	 *             -Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/registTwitterTrnsmit.do", method = RequestMethod.POST)
	public String EgovTwitterTrnsmitPost(
			TwitterInfoVO twitterInfo, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) 
	throws Exception {

		String sCONSUMER_KEY = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_SECRET");

		String atoken = (String) WebUtils.getSessionAttribute(request, "atoken");
		String astoken = (String) WebUtils.getSessionAttribute(request, "astoken");

		HashMap<String, String> hmParam = new HashMap<String, String>();

		// 인증키값 설정
		hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
		hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
		hmParam.put("atoken", atoken);
		hmParam.put("astoken", astoken);

		// 트위터 글 게시
		twitter4j.Status status = twitterTrnsmitService.twitterTrnsmitRegist(hmParam, twitterInfo.getTwitterText());
		model.addAttribute("status", status);

		return "aramframework/com/uss/ion/tir/TwitterTrnsmitResult";
	}
	
}
