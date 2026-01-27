package aramframework.com.utl.sim.service;

import javax.servlet.http.HttpServletRequest;

import aramframework.cmm.constant.AramProperties;
import aramframework.cmm.constant.Globals;

/**
 * 클라이언트(Client)의 IP주소, OS정보, 웹브라우저정보를 조회하는 Business Interface class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ClntInfo {

	/**
	 * 클라이언트(Client)의 IP주소를 조회하는 기능
	 * 
	 * @param HttpServletRequest
	 *            request Request객체
	 * @return String ipAddr IP주소
	 * @exception Exception
	 */
	public static String getClntIP(HttpServletRequest request) throws Exception {

		// IP주소
		String ipAddr = request.getRemoteAddr();
		return ipAddr;
	}

	/**
	 * 클라이언트(Client)의 OS 정보를 조회하는 기능
	 * 
	 * @param HttpServletRequest
	 *            request Request객체
	 * @return String osInfo OS 정보
	 * @exception Exception
	 */
	public static String getClntOsInfo(HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");
		String os_info = user_agent.toUpperCase().split(";")[2].split("\\)")[0];
		String os_conf = AramProperties.getProperty(Globals.CLIENT_CONF_PATH, os_info.replaceAll(" ", ""));
		String osInfo = "";
		if (os_conf != null && !"".equals(os_conf)) {
			osInfo = os_conf;
		} else {
			osInfo = os_info;
		}
		return osInfo;
	}

	/**
	 * 클라이언트(Client)의 웹브라우저 종류를 조회하는 기능
	 * 
	 * @param HttpServletRequest
	 *            request Request객체
	 * @return String webKind 웹브라우저 종류
	 * @exception Exception
	 */
	public static String getClntWebKind(HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		// 웹브라우저 종류 조회
		String webKind = "";
		if (user_agent.toUpperCase().indexOf("GECKO") != -1) {
			if (user_agent.toUpperCase().indexOf("NESCAPE") != -1) {
				webKind = "Netscape (Gecko/Netscape)";
			} else if (user_agent.toUpperCase().indexOf("FIREFOX") != -1) {
				webKind = "Mozilla Firefox (Gecko/Firefox)";
			} else {
				webKind = "Mozilla (Gecko/Mozilla)";
			}
		} else if (user_agent.toUpperCase().indexOf("MSIE") != -1) {
			if (user_agent.toUpperCase().indexOf("OPERA") != -1) {
				webKind = "Opera (MSIE/Opera/Compatible)";
			} else {
				webKind = "Internet Explorer (MSIE/Compatible)";
			}
		} else if (user_agent.toUpperCase().indexOf("SAFARI") != -1) {
			if (user_agent.toUpperCase().indexOf("CHROME") != -1) {
				webKind = "Google Chrome";
			} else {
				webKind = "Safari";
			}
		} else if (user_agent.toUpperCase().indexOf("THUNDERBIRD") != -1) {
			webKind = "Thunderbird";
		} else {
			webKind = "Other Web Browsers";
		}
		return webKind;
	}

	/**
	 * 클라이언트(Client)의 웹브라우저 버전을 조회하는 기능
	 * 
	 * @param HttpServletRequest
	 *            request Request객체
	 * @return String webVer 웹브라우저 버전
	 * @exception Exception
	 */
	public static String getClntWebVer(HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		// 웹브라우저 버전 조회
		String webVer = "";
		String[] arr = { "MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI" };
		for (int i = 0; i < arr.length; i++) {
			int s_loc = user_agent.toUpperCase().indexOf(arr[i]);
			if (s_loc != -1) {
				int f_loc = s_loc + arr[i].length();
				webVer = user_agent.toUpperCase().substring(f_loc, f_loc + 5);
				webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "").replaceAll("//.", "");
			}
		}
		return webVer;
	}
}
