package aramframework.com.utl.sys.prm.schedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.cmm.constant.Globals;

/**
 * 개요 - 프로세스 모니터링을 위한 Check 클래스
 * 
 * 상세내용 - 프로세스의 상태 결과를 제공한다.
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

public class ProcessMonChecker {

	// Log
	protected static final Logger log = LoggerFactory.getLogger(ProcessMonChecker.class);

	/**
	 * <pre>
	 * Comment : 프로세스 정보를 확인한다. (
	 * </pre>
	 * 
	 * @param String
	 *            processName
	 * @return List<String[]> 프로세스 정보를 리턴한다.
	 * @version 1.0 (2009.01.12.)
	 * @see
	 */
	public static String getProcessId(String processNm) {

		Process p = null;
		String procsSttus = null;
		BufferedReader buf = null;
		String execStr = "tasklist /fo table /nh /fi \"imagename eq " + processNm + "\"";
		int cnt = 0;
//		String str = null;

		try {
			// 2011.10.10 보안점검 후속조치
			if (Globals.OS_TYPE == null) {
				throw new RuntimeException("Globals.OS_TYPE property value is needed!!!");
			}
			// 2011.10.10 보안점검 후속조치 끝

			if ("WINDOWS".equals(Globals.OS_TYPE)) {
				cnt = -1; // 윈도우의 경우 정상 프로세스 일때 두번째 줄에 결과를 리턴한다.
				p = Runtime.getRuntime().exec(execStr);

			} else if ("UNIX".equals(Globals.OS_TYPE)) {
				String[] cmd = { "/bin/csh", "-c", "ps -A | grep " + processNm };
				p = Runtime.getRuntime().exec(cmd);
			}

			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((buf.readLine()) != null) {
				cnt++;
			}
			if (cnt > 0)
				procsSttus = "01";
			else
				procsSttus = "02";

		} catch (Exception e) {
			procsSttus = "02";
		} finally {
			if (buf != null)
				try {
					buf.close();
				} catch (Exception e) {
					log.debug(e.getMessage()); // 2011.10.10 보안점검 후속조치
				}
		}

		return procsSttus;
	}

}
