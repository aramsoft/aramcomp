package aramframework.com.cop.com.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 사용자 정보 조회를 위한 VO 클래스
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

public class UserInfVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	/** 유일 아이디 */
	private String uniqId = "";

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

	/** 사용여부 */
	private String useAt = "Y";

	/** 대상 중지 여부 (커뮤니티 또는 동호회) */
	private String deletedAt = "N";

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

	/**
	 * uniqId attribute를 리턴한다.
	 * 
	 * @return the uniqId
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId
	 *            the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

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
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
