package aramframework.com.sym.log.wlg.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aramframework.com.sym.log.wlg.service.WebLogService;

/**
 * 웹 로그 요약을 위한 스케쥴링 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
@Component("webLogScheduling")
public class WebLogScheduling {

	@Autowired
	private WebLogService webLogService;

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * 웹 로그정보를 요약한다. 전날의 로그를 요약하여 입력하고, 일주일전의 로그를 삭제한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int webLogSummary() throws Exception {
		int result = -1;
		try {
			webLogService.logInsertWebLogSummary();
			result = 0;
		} catch(Exception ex) {
			LOG.error("IGNORE:", ex);
		}
		return result;
	}

}
