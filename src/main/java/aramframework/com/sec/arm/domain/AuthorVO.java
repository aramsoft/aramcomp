package aramframework.com.sec.arm.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 권한관리에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class AuthorVO extends BaseVO {

	// domain
	/** 권한코드 */
	private String authorCode;
	
	/** 권한 명 */
	private String authorNm;

	/** 권한코드설명 */
	private String authorDc;
	
	/** 권한등록일자 */
	private String authorCreatDe;
	
	// domain	
	/**
	 * authorCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * 
	 * @param authorCode
	 *            String
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	/**
	 * authorNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorNm() {
		return authorNm;
	}
	/**
	 * authorNm attribute 값을 설정한다.
	 * 
	 * @param authorNm
	 *            String
	 */
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	/**
	 * authorDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorDc() {
		return authorDc;
	}
	/**
	 * authorDc attribute 값을 설정한다.
	 * 
	 * @param authorDc
	 *            String
	 */
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}

	/**
	 * authorCreatDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCreatDe() {
		return authorCreatDe;
	}
	/**
	 * authorCreatDe attribute 값을 설정한다.
	 * 
	 * @param authorCreatDe
	 *            String
	 */
	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}

}
