package aramframework.com.sym.log.lgm.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aramframework.com.sym.log.lgm.service.SysLogService;

/**
 * 시스템 로그요약을 위한 스케쥴링 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Component("sysLogScheduling")
public class SysLogScheduling {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 시스템 로그정보를 요약한다. 전날의 로그를 요약하여 입력하고, 일주일전의 로그를 삭제한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int sysLogSummary() throws Exception {
		int result = -1;
		try {
			sysLogService.logInsertSysLogSummary();
			result = 0;
		} catch(Exception ex) {
			logger.error("IGNORE:", ex);
		}
		return result;
	}

}
