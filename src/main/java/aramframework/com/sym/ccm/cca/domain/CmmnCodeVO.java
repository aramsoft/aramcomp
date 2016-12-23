package aramframework.com.sym.ccm.cca.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 공통코드 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CmmnCodeVO extends BaseVO {

	// domain
	/** 코드ID */
	private String codeId = "";

	/** 코드ID명 */
	private String codeIdNm = "";

	/** 코드ID설명 */
	private String codeIdDc = "";

	/** 사용여부 */
	private String useAt = "";

	/** 분류코드 */
	private String clCode = "";

	// helper
	/** 분류코드명 */
	private String clCodeNm = "";

	// domain
	/**
	 * codeId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * codeId attribute 값을 설정한다.
	 * 
	 * @param codeId
	 *            String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * codeIdNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCodeIdNm() {
		return codeIdNm;
	}
	/**
	 * codeIdNm attribute 값을 설정한다.
	 * 
	 * @param codeIdNm
	 *            String
	 */
	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	/**
	 * codeIdDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCodeIdDc() {
		return codeIdDc;
	}
	/**
	 * codeIdDc attribute 값을 설정한다.
	 * 
	 * @param codeIdDc
	 *            String
	 */
	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
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

	// helper
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

}
