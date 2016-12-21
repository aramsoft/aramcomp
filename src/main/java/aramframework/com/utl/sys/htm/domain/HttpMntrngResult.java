package aramframework.com.utl.sys.htm.domain;

/**
 * 개요 - 
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class HttpMntrngResult {

	/**
	 * 모니터링 정상여부
	 */
	private boolean nrmltAt;
	/**
	 * 원인 Exception
	 */
	private Throwable cause;

	/**
	 * @return the nrmltAt
	 */
	public boolean isNrmltAt() {
		return nrmltAt;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param nrmltAt
	 *            the nrmltAt to set
	 */
	public void setNrmltAt(boolean nrmltAt) {
		this.nrmltAt = nrmltAt;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public HttpMntrngResult(boolean nrmltAt, Throwable cause) {
		this.nrmltAt = nrmltAt;
		this.cause = cause;
	}

}
