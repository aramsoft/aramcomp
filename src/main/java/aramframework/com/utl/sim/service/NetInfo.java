package aramframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import aramframework.com.cmm.constant.AramProperties;
import aramframework.com.cmm.constant.Globals;

/**
 * 시스템 네트워크 정보를 확인하여 제공하는  Business class
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class NetInfo {
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	/**
	 * <pre>
	 * Comment : 호스트 정보를 확인한다.
	 * </pre>
	 * 
	 * @return String hostStr 호스트명을 리턴한다.(테스트용 함수)
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static String getHostName() throws Exception {
		// 실행할 명령을 프로퍼티 파일에서 확인한다.
		String command = AramProperties.getSysPathProperty(Globals.SHELL_CONF_PATH, "SHELL." + Globals.OS_TYPE + ".getHostName");

		// 출력할 결과 (파싱대상)
		String hostStr = "";
		String tmp = "";
		try {
			Process p = Runtime.getRuntime().exec(command);
			// 프로세스가 처리될때까지 대기
			p.waitFor();

			// 프로세스 에러시 종료
			if (p.exitValue() != 0) {
				BufferedReader b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while (b_err.ready()) {
					b_err.close();
				}
			}
			// 프로세스 실행 성공시 결과 확인
			else {
				BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
				int i = 0;
				while (b_out.ready()) {
					// 도스명령어 실행시 결과는 3번째 라인부터 출력됨..
					tmp = b_out.readLine();
					if (tmp.length() <= MAX_STR_LEN) {
						if (i > 1) {
							hostStr += tmp + "\n";
						}
						i++;
					}
				}
				b_out.close();
				// 시스템 로그 출력
			}
		} catch (InterruptedException ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		} catch (IOException ex) {
			// ex.printStackTrace();
			throw new RuntimeException(ex); // 2011.10.10 보안점검 후속조치
		}
		return hostStr;
	}
}
