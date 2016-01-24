package aramframework.com.sym.log.lgm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 시스템 로그요약을 위한 스케쥴링 클래스
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

@Component("sysLogScheduling")
public class SysLogScheduling {

	@Autowired
	private SysLogService sysLogService;

	protected static final Logger LOG = LoggerFactory.getLogger(SysLogScheduling.class);
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
			LOG.error("IGNORE:", ex);
		}
		return result;
	}

}
