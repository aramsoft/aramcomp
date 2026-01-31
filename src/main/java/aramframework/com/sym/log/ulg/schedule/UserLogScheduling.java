package aramframework.com.sym.log.ulg.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aramframework.com.sym.log.ulg.service.UserLogService;

/**
 * 사용자 로그요약을 위한 스케쥴링 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Component("userLogScheduling")
public class UserLogScheduling {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserLogService userLogService;

	/**
	 * 사용자 로그정보를 생성한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int userLogInsert() throws Exception {
		int result = -1;
		try {
			userLogService.logInsertUserLog();
			result = 0;
		} catch(Exception ex) {
			logger.error("IGNORE:", ex);
		}
		return result;
	}

}
