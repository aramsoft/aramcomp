package aramframework.com.uat.uap.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 로그인정책에 대한 model 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class LoginPolicyVO extends BaseVO {

	// domain
	/** 사용자 ID */
	private String emplyrId;
	
	/** IP정보 */
	private String ipInfo;
	
	/** 중복허용여부 */
	private String dplctPermAt;
	
	/** 제한여부 */
	private String lmttAt;
	
	// helper
	/** 사용자 명 */
	private String emplyrNm;
	
	/** 사용자 구분 */
	private String emplyrSe;
	
	/** 등록자 ID */
	private String userId;
	
	/** 등록일시 */
	private String regDate;
	
	/** 등록여부 */
	private String regYn;

	// domain
	/**
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * @return the ipInfo
	 */
	public String getIpInfo() {
		return ipInfo;
	}
	/**
	 * @param ipInfo
	 *            the ipInfo to set
	 */
	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}

	/**
	 * @return the dplctPermAt
	 */
	public String getDplctPermAt() {
		return dplctPermAt;
	}
	/**
	 * @param dplctPermAt
	 *            the dplctPermAt to set
	 */
	public void setDplctPermAt(String dplctPermAt) {
		this.dplctPermAt = dplctPermAt;
	}

	/**
	 * @return the lmttAt
	 */
	public String getLmttAt() {
		return lmttAt;
	}
	/**
	 * @param lmttAt
	 *            the lmttAt to set
	 */
	public void setLmttAt(String lmttAt) {
		this.lmttAt = lmttAt;
	}

	// helper
	/**
	 * @return the emplyrNm
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * @param emplyrNm
	 *            the emplyrNm to set
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

	/**
	 * @return the emplyrSe
	 */
	public String getEmplyrSe() {
		return emplyrSe;
	}
	/**
	 * @param emplyrSe
	 *            the emplyrSe to set
	 */
	public void setEmplyrSe(String emplyrSe) {
		this.emplyrSe = emplyrSe;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * @param regYn
	 *            the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}
	
}
