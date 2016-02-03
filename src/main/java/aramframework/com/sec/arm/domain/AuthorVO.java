package aramframework.com.sec.arm.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 권한관리에 대한 model 클래스를 정의한다.
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

public class AuthorVO extends SearchVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 권한코드 */
	private String authorCode;
	
	/** 권한등록일자 */
	private String authorCreatDe;
	
	/** 권한코드설명 */
	private String authorDc;
	
	/** 권한 명 */
	private String authorNm;

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

}
