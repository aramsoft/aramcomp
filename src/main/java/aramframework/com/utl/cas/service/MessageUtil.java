package aramframework.com.utl.cas.service;

import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.utl.fcc.service.StringUtil;

/**
 * 메시지 처리 관련 유틸리티
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MessageUtil {

	/**
	 * 해당되는 속성키로부터 에러 메시지를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @return	에러 메시지
	 */
	public static String getErrorMsg(String strCode) {

		return getMessage("error", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 에러 메시지(파라미터 변환 포함)를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @param arrParam	파라미터
	 * @return	에러 메시지
	 */
	public static String getErrorMsg(String strCode, String[] arrParam) {

		return getMessage("error", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 정보 메시지를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @return	정보 메시지
	 */
	public static String getInfoMsg(String strCode) {

		return getMessage("info", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 정보 메시지(파라미터 변환 포함)를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @param arrParam	파라미터
	 * @return	정보 메시지
	 */
	public static String getInfoMsg(String strCode, String[] arrParam) {

		return getMessage("info", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 경고 메시지를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @return 경고 메시지
	 */
	public static String getWarnMsg(String strCode) {

		return getMessage("warn", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 경고 메시지(파라미터 변환 포함)를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @param arrParam	파라미터
	 * @return  경고 메시지
	 */
	public static String getWarnMsg(String strCode, String[] arrParam) {

		return getMessage("warn", strCode, arrParam);
	}

	/**
	 * 해당되는 속성키로부터 확인 메시지를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @return 확인 메시지
	 */
	public static String getConfirmMsg(String strCode) {

		return getMessage("confirm", strCode, null);
	}

	/**
	 * 해당되는 속성키로부터 확인 메시지(파라미터 변환 포함)를 얻는다.
	 * 
	 * @param strCode	속성키
	 * @param arrParam	파라미터 변환
	 * @return 확인 메시지
	 */
	public static String getConfirmMsg(String strCode, String[] arrParam) {

		return getMessage("confirm", strCode, arrParam);
	}

	private static String getMessage(String wrkCode, String strCode, String[] arrParam) {

		String message = null;

		try {

			String strMsg = "";
			if (!"".equals(StringUtil.isNullToString(strCode.trim()))) {
				String properties = AramProperties.SYSCONFIG_PATH_PREFIX 
						+ "egovProps" + System.getProperty("file.separator") 
						+ "conf" + System.getProperty("file.separator") 
						+ wrkCode + "message.properties";
				
				strMsg = AramProperties.getProperty(properties, strCode);

				for (int i = (arrParam != null && arrParam.length > 0 ? arrParam.length - 1 : -1); i >= 0; i--) {
					strMsg = StringUtil.replace(strMsg, "{" + i + "}", arrParam[i]);
				}
				message = strMsg;
			} else {
				message = "";
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}

		return message;
	}
	
}
