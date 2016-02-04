package aramframework.com.cmm.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

public class ComOthersExcepHndlr implements ExceptionHandler {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	public void occur(Exception ex, String packageName) {
		// LOG.debug(" ServiceExceptionHandler run...............");
		LOG.error(packageName, ex);
	}
}
