package aramframework.com.cmm.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.com.cmm.util.LogUtil;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * 공통서비스의 exception 처리 클래스
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

public class ComExcepHndlr implements ExceptionHandler {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 발생된 Exception을 처리한다.
	 */
	public void occur(Exception ex, String packageName) {
		// LOG.debug(" ServiceExceptionHandler run...............");
		if ( ex instanceof org.springframework.security.access.AccessDeniedException ) { 
			LOG.error(ex.getMessage());
		} else {	
			LOG.error(packageName);
			LogUtil.logErrorMessage(LOG, ex, 10);
		}	
	}
}
