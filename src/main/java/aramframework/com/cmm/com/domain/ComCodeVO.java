package aramframework.com.cmm.com.domain;

/**
 * 공통상세코드 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ComCodeVO {

	// domain
	/** 코드ID */
	private String codeId = "";

	/** 코드	 */
	private String code = "";

	/** 코드명 */
	private String codeNm = "";

	/** 코드설명 */
	private String codeDc = "";

	/**
	 * codeId attribute 를 리턴한다.
	 * 
	 * @return 	String
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * codeId attribute 값을 설정한다.
	 * 
	 * @param 	codeId	String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * code attribute 를 리턴한다.
	 * 
	 * @return 	String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * code attribute 값을 설정한다.
	 * 
	 * @param 	code	String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * codeNm attribute 를 리턴한다.
	 * 
	 * @return 	String
	 */
	public String getCodeNm() {
		return codeNm;
	}
	/**
	 * codeNm attribute 값을 설정한다.
	 * 
	 * @param 	codeNm	String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeDc attribute 를 리턴한다.
	 * 
	 * @return 	String
	 */
	public String getCodeDc() {
		return codeDc;
	}
	/**
	 * codeDc attribute 값을 설정한다.
	 * 
	 * @param 	codeDc	String
	 */
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

}
