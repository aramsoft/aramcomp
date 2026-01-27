package aramframework.cmm.config.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aramframework.cmm.util.LogUtil;

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * 공통서비스의 Web exception 처리 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class WebExcepHndlr implements ExceptionHandler {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 발생된 Exception을 처리한다.
	 * 
	 * @param 	ex			Exception
	 * @param 	packageName	패키지 이름
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
