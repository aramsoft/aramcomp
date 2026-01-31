package aramframework.com.sym.ccm.ccc.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 공통분류코드 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class CmmnClCodeVO extends BaseVO {

	// domain
	/** 분류코드 */
	private String clCode = "";

	/** 분류코드명 */
	private String clCodeNm = "";

	/** 분류코드설명 */
	private String clCodeDc = "";

	/** 사용여부 */
	private String useAt = "";

	// domain
	/**
	 * clCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getClCode() {
		return clCode;
	}
	/**
	 * clCode attribute 값을 설정한다.
	 * 
	 * @param clCode
	 *            String
	 */
	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	/**
	 * clCodeNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getClCodeNm() {
		return clCodeNm;
	}
	/**
	 * clCodeNm attribute 값을 설정한다.
	 * 
	 * @param clCodeNm
	 *            String
	 */
	public void setClCodeNm(String clCodeNm) {
		this.clCodeNm = clCodeNm;
	}

	/**
	 * clCodeDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getClCodeDc() {
		return clCodeDc;
	}
	/**
	 * clCodeDc attribute 값을 설정한다.
	 * 
	 * @param clCodeDc
	 *            String
	 */
	public void setClCodeDc(String clCodeDc) {
		this.clCodeDc = clCodeDc;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

}
