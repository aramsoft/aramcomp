package aramframework.com.sym.ccm.cde.service;

import aramframework.com.cmm.SearchVO;

/**
 * 공통상세코드 모델 클래스
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

public class CmmnDetailCodeVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 코드ID */
	private String codeId = "";

	/** 코드ID명 */
	private String codeIdNm = "";

	/** 코드 */
	private String code = "";

	/** 코드명 */
	private String codeNm = "";

	/** 코드설명 */
	private String codeDc = "";

	/** 사용여부 */
	private String useAt = "";

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
	 * code attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * code attribute 값을 설정한다.
	 * 
	 * @param code
	 *            String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * codeNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCodeNm() {
		return codeNm;
	}
	/**
	 * codeNm attribute 값을 설정한다.
	 * 
	 * @param codeNm
	 *            String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCodeDc() {
		return codeDc;
	}
	/**
	 * codeDc attribute 값을 설정한다.
	 * 
	 * @param codeDc
	 *            String
	 */
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
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
