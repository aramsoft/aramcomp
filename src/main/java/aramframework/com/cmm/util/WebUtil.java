package aramframework.com.cmm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import aramframework.com.cmm.domain.ComCodeVO;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class WebUtil {

	protected static final Logger LOG = LoggerFactory.getLogger(WebUtil.class);
	
	final static String comDefaultPath = "aramframework/com";
	
	public static String redirectJsp(ModelMap model, String redirectUrl) {
		model.addAttribute("redirectURL", redirectUrl);
		return "aramframework/com/cmm/redirect";
	}
/*
	public static String redirectJsp(ModelMap model, String redirectUrl, String param) {
		model.addAttribute("redirectURL", redirectUrl);
		
		HashMap<String, String> parameterMap = new HashMap<String, String>();

		String params[] = param.split("&");
		for(int i = 0; i < params.length; ++i) {
			String values[] = params[i].split("=");
//			LOG.debug("values[0]=" + values[0] + ", values[1] = " + values[1]);
			parameterMap.put(values[0], values[1]);
		}
		model.addAttribute("parameterMap", parameterMap);

		return comRedirectJsp;
	}
*/	
	public static String adjustViewName(String viewName) {
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

		String jspPrefix = (String) requestAttributes.getAttribute("jspPrefix", RequestAttributes.SCOPE_REQUEST);
		if (jspPrefix == null || "".equals(jspPrefix)) jspPrefix = comDefaultPath;
		
		String jspPage = jspPrefix + viewName;	
		
//		LOG.debug("jspPage = " + jspPage);

		// if tiles exist, forward tiles layout
		String aTrgetId   = (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
		String aCurMenuNo = (String) requestAttributes.getAttribute("curMenuNo", RequestAttributes.SCOPE_REQUEST);
		if( aTrgetId != null
				&& aTrgetId.startsWith("CMMNTY_") 
				&& aCurMenuNo != null
				&& !"".equals(aCurMenuNo) )  {
			
			requestAttributes.setAttribute("jspPage", jspPage, RequestAttributes.SCOPE_REQUEST);
			
			return "forward:/cop/cmy/CmmntyTilesPage.do";
		}
		
		return jspPage;
	}
	
	public static String getCurTrgetId() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return (String) requestAttributes.getAttribute("curTrgetId", RequestAttributes.SCOPE_REQUEST);
	}
	
	public static String getOriginalId(String source, String prefix) {
		if ( source.startsWith(prefix)  ) {
			return source;
		}
		
		String format = prefix + "%1$0" + (20-prefix.length()) + "d";
		try {
			return String.format(format, Integer.parseInt(source));
		} catch(Exception ex) {
			LOG.error("fail to id conversion!!, format = " + format + ", source = " + source);
			return source;
		}
	}	

	/**
	 * pathId를 추출한다.
	 * 
	 * @param 	original		String
	 * @return 					String
	 */
	public static String getPathId(String original) {
		int index = original.lastIndexOf("_")+1;
		if( original.charAt(index) != '0' ) return original;
		 
		for( ; index < original.length() ; index++)  {
			if( original.charAt(index) != '0' ) break;
		}
		return (index == original.length()) ? "0" : original.substring(index);
	}
	
	/**
	 * 시간의 LIST를 반환한다.
	 * 
	 * @return 		List
	 */
	public static List<ComCodeVO> getTimeHH() {
		ArrayList<ComCodeVO> listHH = new ArrayList<ComCodeVO>();
		// HashMap hmHHMM;
		for (int i = 0; i < 24; i++) {
			
			String sHH = Integer.toString(i);
			if (sHH.length() == 1) {
				sHH = "0" + sHH;
			} 

			ComCodeVO codeVO = new ComCodeVO();
			codeVO.setCode(sHH);
			codeVO.setCodeNm(sHH);

			listHH.add(codeVO);
		}

		return listHH;
	}

	/**
	 * 분의 LIST를 반환한다.
	 * 
	 * @return 		List
	 */
	public static List<ComCodeVO> getTimeMM() {
		ArrayList<ComCodeVO> listMM = new ArrayList<ComCodeVO>();
		// HashMap hmHHMM;
		for (int i = 0; i < 60; i++) {

			String sMM = Integer.toString(i);
			if (sMM.length() == 1) {
				sMM = "0" + sMM;
			} 

			ComCodeVO codeVO = new ComCodeVO();
			codeVO.setCode(sMM);
			codeVO.setCodeNm(sMM);

			listMM.add(codeVO);
		}
		return listMM;
	}

	/**
	 * 0을 붙여 반환
	 * 
	 * @return 	String
	 */
	public static String dateTypeIntForString(int iInput) {
		String sOutput = Integer.toString(iInput);
		if (sOutput.length() == 1) {
			sOutput = "0" + sOutput;
		} 
		return sOutput;
	}
	
	// Original
	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 * 
	 * @param 	value	String
	 * @return			String
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value; 
	}

	public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

		return ipPattern.matcher(str).matches();
	}

	 public static String removeCRLF(String parameter) {
		 return parameter.replaceAll("\r", "").replaceAll("\n", "");
	 }
	 
	 public static String removeSQLInjectionRisk(String parameter) {
		 return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "")
				 		 .replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
	 }
	 
	 public static String removeOSCmdRisk(String parameter) {
		 return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
	 }
	 
	/*
	 * public static void main(String[] args) { 
	 * 
	 * 		String test = null;
	 * 
	 * 		test = "<script language='javascript' encoding=\"utf-8\">q&a</script>";
	 * 		System.out.println("clearXSSMinimum() Test"); System.out.println(test);
	 * 		System.out.println("=>"); System.out.println(clearXSSMinimum(test));
	 * 		System.out.println();
	 * 
	 * 		test = "/a/b/c../..\\"; 
	 * 		System.out.println("clearXSSMaximum() Test");
	 * 		System.out.println(test); System.out.println(" =>");
	 * 		System.out.println(clearXSSMaximum(test)); 
	 * 		System.out.println();
	 * 
	 * 		test = "/a/b/c/../../../..\\..\\";
	 * 		System.out.println("filePathBlackList() Test"); 
	 * 		System.out.println(test);
	 * 		System.out.println("=>"); 
	 * 		System.out.println(filePathBlackList(test));
	 * 		System.out.println();
	 * 
	 * 		test = "192.168.0.1"; 
	 * 		System.out.println("isIPAddress() test");
	 * 		System.out.println("IP : " + test + " => " + isIPAddress(test)); } //
	 */
}