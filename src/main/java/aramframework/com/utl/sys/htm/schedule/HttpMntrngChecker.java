package aramframework.com.utl.sys.htm.schedule;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import aramframework.com.utl.sys.htm.service.HttpMntrngService;

/**
 * 개요 - HTTP서비스모니터링을 위한 Check 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class HttpMntrngChecker {

	@Autowired
	private HttpMntrngService httpMntrngService;
	
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	/**
	 * 시스템에 존재하는 서버의 실행상태 정보를 조회하는 기능
	 * 
	 * @param String
	 *            sitUrl 사용포트
	 * @return String status 실행상태
	 * @exception Exception
	 */
	public static String getPrductStatus(String siteUrl) {

		String httpSttusCd = null;

		try {
			URL url = new URL(siteUrl);
			url.openStream();

			httpSttusCd = "01";

		} catch (Exception e) {
			httpSttusCd = "02";
		}

		return httpSttusCd;
	}

}
