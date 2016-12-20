package aramframework.com.sec.dpt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 부서관리VO클래스.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class DeptVO extends BaseVO {

	// domain
	private String orgnztId;
	private String orgnztNm;
	private String orgnztDc;

	// domain
	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId
	 *            the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the orgnztDc
	 */
	public String getOrgnztDc() {
		return orgnztDc;
	}
	/**
	 * @param orgnztDc
	 *            the orgnztDc to set
	 */
	public void setOrgnztDc(String orgnztDc) {
		this.orgnztDc = orgnztDc;
	}

}
