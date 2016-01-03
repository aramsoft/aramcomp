package aramframework.com.sym.log.ulg.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 사용자 로그요약을 위한 스케쥴링 클래스
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

@Service("userLogScheduling")
public class UserLogScheduling {

	@Resource(name = "userLogService")
	private UserLogService userLogService;

	protected static final Logger LOG = LoggerFactory.getLogger(UserLogScheduling.class);
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
			LOG.error("IGNORE:", ex);
		}
		return result;
	}

}
