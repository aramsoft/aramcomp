package aramframework.com.cmm.util;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 
 * ReloadableResourceBundleMessageSource 클래스의 구현체
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MessageHelper {

	static ReloadableResourceBundleMessageSource messageSource;

	/**
	 * getReloadableResourceBundleMessageSource()
	 * 
	 * @param 	reloadableResourceBundleMessageSource	resource MessageSource
	 */
	public void setMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		MessageHelper.messageSource = reloadableResourceBundleMessageSource;
	}

	/**
	 * getReloadableResourceBundleMessageSource()
	 * 
	 * @return 	ReloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * 정의된 메세지 조회
	 * 
	 * @param 	code	메세지 코드
	 * @return 			String
	 */
	public static String getMessage(String code) {
		return messageSource.getMessage(code, null, Locale.getDefault());
	}

}
