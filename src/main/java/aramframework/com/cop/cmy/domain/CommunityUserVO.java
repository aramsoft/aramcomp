package aramframework.com.cop.cmy.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 커뮤티니 사용자 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class CommunityUserVO extends BaseVO {

	// domain
	/** 커뮤니티아이디 */
	private String cmmntyId = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 관리자여부 */
	private String mngrAt = "";

	/** 가입일 */
	private String sbscrbDe = "";

	/** 탈퇴일 */
	private String secsnDe = "";

	/** 사용여부 */
	private String useAt = "";

	// helper
	/** 인증여부 */
	private String authenticatedAt = "";

	/** 사용자명 */
	private String emplyrNm = "";

	// domain
	/**
	 * cmmntyId attribute를 리턴한다.
	 * 
	 * @return the cmmntyId
	 */
	public String getCmmntyId() {
		return cmmntyId;
	}
	/**
	 * cmmntyId attribute 값을 설정한다.
	 * 
	 * @param cmmntyId
	 *            the cmmntyId to set
	 */
	public void setCmmntyId(String cmmntyId) {
		this.cmmntyId = cmmntyId;
	}

	/**
	 * emplyrId attribute를 리턴한다.
	 * 
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정한다.
	 * 
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * mngrAt attribute를 리턴한다.
	 * 
	 * @return the mngrAt
	 */
	public String getMngrAt() {
		return mngrAt;
	}
	/**
	 * mngrAt attribute 값을 설정한다.
	 * 
	 * @param mngrAt
	 *            the mngrAt to set
	 */
	public void setMngrAt(String mngrAt) {
		this.mngrAt = mngrAt;
	}

	/**
	 * sbscrbDe attribute를 리턴한다.
	 * 
	 * @return the sbscrbDe
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}
	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * 
	 * @param sbscrbDe
	 *            the sbscrbDe to set
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}

	/**
	 * secsnDe attribute를 리턴한다.
	 * 
	 * @return the secsnDe
	 */
	public String getSecsnDe() {
		return secsnDe;
	}
	/**
	 * secsnDe attribute 값을 설정한다.
	 * 
	 * @param secsnDe
	 *            the secsnDe to set
	 */
	public void setSecsnDe(String secsnDe) {
		this.secsnDe = secsnDe;
	}

	/**
	 * useAt attribute를 리턴한다.
	 * 
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	// helper
	/**
	 * authenticatedAt attribute를 리턴한다.
	 * 
	 * @return the authenticatedAt
	 */
	public String getAuthenticatedAt() {
		return authenticatedAt;
	}
	/**
	 * authenticatedAt attribute 값을 설정한다.
	 * 
	 * @param authenticatedAt
	 *            the authenticatedAt to set
	 */
	public void setAuthenticatedAt(String authenticatedAt) {
		this.authenticatedAt = authenticatedAt;
	}

	/**
	 * emplyrNm attribute를 리턴한다.
	 * 
	 * @return the emplyrNm
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * emplyrNm attribute 값을 설정한다.
	 * 
	 * @param emplyrNm
	 *            the emplyrNm to set
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

}
