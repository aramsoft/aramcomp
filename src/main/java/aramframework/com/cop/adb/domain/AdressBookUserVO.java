package aramframework.com.cop.adb.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 주소록구성원 관리를 위한 VO 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AdressBookUserVO extends BaseVO {

	// domain
	/** 주소록구성원 아이디 */
	private String adbkUserId = "";

	/** 주소록 아이디 */
	private String adbkId = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 명함 아이디 */
	private String ncrdId = "";

	/** 주소록구성원 이름 */
	private String nm = "";

	/** 이메일 주소 */
	private String emailAdres = "";

	/** 폰 번호 */
	private String moblphonNo = "";

	/** 팩스 번호 */
	private String fxnum = "";

	/** 회사 번호 */
	private String offmTelno = "";

	/** 집 전화번호 */
	private String homeTelno = "";

	// helper
	/** 사용자 아이디 */
	private String userId = "";

	/** 사용자 명 */
	private String userNm = "";

	/** 사용자 이메일 */
	private String userEmail = "";

	/** 사용자 접전화 처음 */
	private String areaNo = "";

	/** 사용자 집전화 가운데 */
	private String homemiddleTelno = "";

	/** 사용자 집전화 마지막 */
	private String homeendTelno = "";

	// domain
	/**
	 * adbkUserId attribute를 리턴한다.
	 * 
	 * @return 	the adbkUserId
	 */
	public String getAdbkUserId() {
		return adbkUserId;
	}
	/**
	 * adbkUserId attribute 값을 설정한다.
	 * 
	 * @param 	adbkUserId	the adbkUserId to set
	 */
	public void setAdbkUserId(String adbkUserId) {
		this.adbkUserId = adbkUserId;
	}

	/**
	 * adbkId attribute를 리턴한다.
	 * 
	 * @return 	the adbkId
	 */
	public String getAdbkId() {
		return adbkId;
	}
	/**
	 * adbkId attribute 값을 설정한다.
	 * 
	 * @param 	adbkId	the adbkId to set
	 */
	public void setAdbkId(String adbkId) {
		this.adbkId = adbkId;
	}

	/**
	 * emplyrId attribute를 리턴한다.
	 * 
	 * @return 	the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정한다.
	 * 
	 * @param 	emplyrId	the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
		if( emplyrId == null ) this.emplyrId = "";
		this.emplyrId = this.emplyrId.trim();
	}

	/**
	 * ncrdId attribute를 리턴한다.
	 * 
	 * @return 	the ncrdId
	 */
	public String getNcrdId() {
		return ncrdId;
	}
	/**
	 * ncrdId attribute 값을 설정한다.
	 * 
	 * @param 	ncrdId	the ncrdId to set
	 */
	public void setNcrdId(String ncrdId) {
		this.ncrdId = ncrdId;
		if( ncrdId == null ) this.ncrdId = "";
		this.ncrdId = this.ncrdId.trim();
	}

	/**
	 * nm attribute를 리턴한다.
	 * 
	 * @return 	the nm
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * nm attribute 값을 설정한다.
	 * 
	 * @param 	nm	the nm to set
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}

	/**
	 * emailAdres attribute를 리턴한다.
	 * 
	 * @return 	the emailAdres
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * 
	 * @param 	emailAdres	the emailAdres to set
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * moblphonNo attribute를 리턴한다.
	 * 
	 * @return 	the moblphonNo
	 */
	public String getMoblphonNo() {
		return moblphonNo;
	}
	/**
	 * moblphonNo attribute 값을 설정한다.
	 * 
	 * @param 	moblphonNo	the moblphonNo to set
	 */
	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}

	/**
	 * fxnum attribute를 리턴한다.
	 * 
	 * @return the fxnum
	 */
	public String getFxnum() {
		return 	fxnum;
	}
	/**
	 * fxnum attribute 값을 설정한다.
	 * 
	 * @param 	fxnum	the fxnum to set
	 */
	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
	}

	/**
	 * offmTelno attribute를 리턴한다.
	 * 
	 * @return 	the offmTelno
	 */
	public String getOffmTelno() {
		return offmTelno;
	}
	/**
	 * offmTelno attribute 값을 설정한다.
	 * 
	 * @param 	offmTelno	the offmTelno to set
	 */
	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	/**
	 * homeTelno attribute를 리턴한다.
	 * 
	 * @return 	the homeTelno
	 */
	public String getHomeTelno() {
		return homeTelno;
	}
	/**
	 * homeTelno attribute 값을 설정한다.
	 * 
	 * @param 	homeTelno	the homeTelno to set
	 */
	public void setHomeTelno(String homeTelno) {
		this.homeTelno = homeTelno;
	}

	// helper
	/**
	 * userId attribute를 리턴한다.
	 * 
	 * @return 	the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param 	userId	the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNm attribute를 리턴한다.
	 * 
	 * @return 	the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * 
	 * @param 	userNm	the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * userEmail attribute를 리턴한다.
	 * 
	 * @return 	the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * userEmail attribute 값을 설정한다.
	 * 
	 * @param 	userEmail	the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * areaNo attribute를 리턴한다.
	 * 
	 * @return 	the areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @param 	areaNo	the areaNo to set
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * homemiddleTelno attribute를 리턴한다.
	 * 
	 * @return 	the homemiddleTelno
	 */
	public String getHomemiddleTelno() {
		return homemiddleTelno;
	}
	/**
	 * homemiddleTelno attribute 값을 설정한다.
	 * 
	 * @param 	homemiddleTelno	the homemiddleTelno to set
	 */
	public void setHomemiddleTelno(String homemiddleTelno) {
		this.homemiddleTelno = homemiddleTelno;
	}

	/**
	 * homeendTelno attribute를 리턴한다.
	 * 
	 * @return 	the homeendTelno
	 */
	public String getHomeendTelno() {
		return homeendTelno;
	}
	/**
	 * homeendTelno attribute 값을 설정한다.
	 * 
	 * @param 	homeendTelno	the homeendTelno to set
	 */
	public void setHomeendTelno(String homeendTelno) {
		this.homeendTelno = homeendTelno;
	}

}
