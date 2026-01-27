package aramframework.com.cop.com.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 사용자 정보 조회를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class UserInfVO extends BaseVO {

	// domain
	/** 사용자 아이디 */
	private String userId = "";

	/** 사용자 명 */
	private String userNm = "";

	/** 사용자 우편번호 */
	private String userZip = "";

	/** 사용자 주소 */
	private String userAdres = "";

	/** 사용자 이메일 */
	private String userEmail = "";

	// helper
	/** 사용여부 */
	private String useAt = "Y";

	/** 대상 중지 여부 (커뮤니티 또는 동호회) */
	private String deletedAt = "N";

	/** 대상 아이디 */
	private String trgetId = "";

	// domain

	/**
	 * userId attribute를 리턴한다.
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNm attribute를 리턴한다.
	 * 
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * 
	 * @param userNm
	 *            the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * userZip attribute를 리턴한다.
	 * 
	 * @return the userZip
	 */
	public String getUserZip() {
		return userZip;
	}
	/**
	 * userZip attribute 값을 설정한다.
	 * 
	 * @param userZip
	 *            the userZip to set
	 */
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}

	/**
	 * userAdres attribute를 리턴한다.
	 * 
	 * @return the userAdres
	 */
	public String getUserAdres() {
		return userAdres;
	}
	/**
	 * userAdres attribute 값을 설정한다.
	 * 
	 * @param userAdres
	 *            the userAdres to set
	 */
	public void setUserAdres(String userAdres) {
		this.userAdres = userAdres;
	}

	/**
	 * userEmail attribute를 리턴한다.
	 * 
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * userEmail attribute 값을 설정한다.
	 * 
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	// helper
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

	/**
	 * deletedAt attribute를 리턴한다.
	 * 
	 * @return the deletedAt
	 */
	public String getDeletedAt() {
		return deletedAt;
	}
	/**
	 * deletedAt attribute 값을 설정한다.
	 * 
	 * @param deletedAt
	 *            the deletedAt to set
	 */
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * trgetId attribute를 리턴한다.
	 * 
	 * @return the trgetId
	 */
	public String getTrgetId() {
		return trgetId;
	}
	/**
	 * trgetId attribute 값을 설정한다.
	 * 
	 * @param trgetId
	 *            the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

}
