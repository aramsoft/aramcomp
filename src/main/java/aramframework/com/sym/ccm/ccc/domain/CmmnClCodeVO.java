package aramframework.com.sym.ccm.ccc.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 공통분류코드 모델 클래스
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

public class CmmnClCodeVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 분류코드 */
	private String clCode = "";

	/** 분류코드명 */
	private String clCodeNm = "";

	/** 분류코드설명 */
	private String clCodeDc = "";

	/** 사용여부 */
	private String useAt = "";

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
