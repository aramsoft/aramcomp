package aramframework.com.uss.umt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class EntrprsManageVO extends BaseVO {

	// domain
	/** 기업 회원 ID */
	private String entrprsmberId;
	
	/** 기업구분코드 */
	private String entrprsSeCode;
	
	/** 기업 회원 상태 */
	private String entrprsMberSttus;
	
	/** 기업 회원 비밀번호 */
	private String password;
	
	/** 기업 회원 비밀번호 힌트 */
	private String passwordHint;
	
	/** 기업 회원 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 사업자번호 */
	private String bizrno;
	
	/** 법인등록번호 */
	private String jurirno;
	
	/** 회사명 */
	private String cmpnyNm;
	
	/** 대표이사 */
	private String cxfc;
	
	/** 우편번호 */
	private String zip;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 회사중간전화번호 */
	private String entrprsMiddleTelno;
	
	/** 회사끝전화번호 */
	private String entrprsEndTelno;
	
	/** 팩스번호 */
	private String fxnum;
	
	/** 업종코드 */
	private String indutyCode;
	
	/** 신청자 명 */
	private String applcntNm;
	
	/** 신청자 주민등록번호*/
	private String applcntIhidnum;
	
	/** 가입 일자 */
	private String sbscrbDe;
	
	/** 신청자 이메일주소 */
	private String applcntEmailAdres;

	/** 구글 계정 */
	private String googleAccount;

	// helper
	/** 사용자 ID */
	private String userId;

	/** 사용자 유형 */
	private String userTy;
	
	// 추가 검색 조건
	/** 이전비밀번호 - 비밀번호 변경시 사용 */
	private String oldPassword = "";

	/** 검색조건-회원상태 (0, A, D, P) */
	private String sbscrbSttus = "0";

	// domain
	/**
	 * entrprsmberId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEntrprsmberId() {
		return entrprsmberId;
	}
	/**
	 * entrprsmberId attribute 값을 설정한다.
	 * 
	 * @param entrprsmberId
	 *            String
	 */
	public void setEntrprsmberId(String entrprsmberId) {
		this.entrprsmberId = entrprsmberId;
	}

	/**
	 * entrprsSeCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEntrprsSeCode() {
		return entrprsSeCode;
	}
	/**
	 * entrprsSeCode attribute 값을 설정한다.
	 * 
	 * @param entrprsSeCode
	 *            String
	 */
	public void setEntrprsSeCode(String entrprsSeCode) {
		this.entrprsSeCode = entrprsSeCode;
	}

	/**
	 * entrprsMberSttus attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEntrprsMberSttus() {
		return entrprsMberSttus;
	}
	/**
	 * entrprsMberSttus attribute 값을 설정한다.
	 * 
	 * @param entrprsMberSttus
	 *            String
	 */
	public void setEntrprsMberSttus(String entrprsMberSttus) {
		this.entrprsMberSttus = entrprsMberSttus;
	}

	/**
	 * password attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정한다.
	 * 
	 * @param password
	 *            String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * passwordHint attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getPasswordHint() {
		return passwordHint;
	}
	/**
	 * passwordHint attribute 값을 설정한다.
	 * 
	 * @param passwordHint
	 *            String
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	/**
	 * passwordCnsr attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getPasswordCnsr() {
		return passwordCnsr;
	}
	/**
	 * passwordCnsr attribute 값을 설정한다.
	 * 
	 * @param passwordCnsr
	 *            String
	 */
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}

	/**
	 * bizrno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getBizrno() {
		return bizrno;
	}
	/**
	 * bizrno attribute 값을 설정한다.
	 * 
	 * @param bizrno
	 *            String
	 */
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}

	/**
	 * jurirno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getJurirno() {
		return jurirno;
	}
	/**
	 * jurirno attribute 값을 설정한다.
	 * 
	 * @param jurirno
	 *            String
	 */
	public void setJurirno(String jurirno) {
		this.jurirno = jurirno;
	}

	/**
	 * cmpnyNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	/**
	 * cmpnyNm attribute 값을 설정한다.
	 * 
	 * @param cmpnyNm
	 *            String
	 */
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}

	/**
	 * cxfc attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getCxfc() {
		return cxfc;
	}
	/**
	 * cxfc attribute 값을 설정한다.
	 * 
	 * @param cxfc
	 *            String
	 */
	public void setCxfc(String cxfc) {
		this.cxfc = cxfc;
	}

	/**
	 * zip attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * zip attribute 값을 설정한다.
	 * 
	 * @param zip
	 *            String
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * adres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * adres attribute 값을 설정한다.
	 * 
	 * @param adres
	 *            String
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * detailAdres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getDetailAdres() {
		return detailAdres;
	}
	/**
	 * detailAdres attribute 값을 설정한다.
	 * 
	 * @param detailAdres
	 *            String
	 */
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}

	/**
	 * areaNo attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @param areaNo
	 *            String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * entrprsMiddleTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEntrprsMiddleTelno() {
		return entrprsMiddleTelno;
	}
	/**
	 * entrprsMiddleTelno attribute 값을 설정한다.
	 * 
	 * @param entrprsMiddleTelno
	 *            String
	 */
	public void setEntrprsMiddleTelno(String entrprsMiddleTelno) {
		this.entrprsMiddleTelno = entrprsMiddleTelno;
	}

	/**
	 * entrprsEndTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEntrprsEndTelno() {
		return entrprsEndTelno;
	}
	/**
	 * entrprsEndTelno attribute 값을 설정한다.
	 * 
	 * @param entrprsEndTelno
	 *            String
	 */
	public void setEntrprsEndTelno(String entrprsEndTelno) {
		this.entrprsEndTelno = entrprsEndTelno;
	}

	/**
	 * fxnum attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getFxnum() {
		return fxnum;
	}
	/**
	 * fxnum attribute 값을 설정한다.
	 * 
	 * @param fxnum
	 *            String
	 */
	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
	}

	/**
	 * indutyCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getIndutyCode() {
		return indutyCode;
	}
	/**
	 * indutyCode attribute 값을 설정한다.
	 * 
	 * @param indutyCode
	 *            String
	 */
	public void setIndutyCode(String indutyCode) {
		this.indutyCode = indutyCode;
	}

	/**
	 * applcntNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getApplcntNm() {
		return applcntNm;
	}
	/**
	 * applcntNm attribute 값을 설정한다.
	 * 
	 * @param applcntNm
	 *            String
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}

	/**
	 * applcntIhidnum attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getApplcntIhidnum() {
		return applcntIhidnum;
	}
	/**
	 * applcntIhidnum attribute 값을 설정한다.
	 * 
	 * @param applcntIhidnum
	 *            String
	 */
	public void setApplcntIhidnum(String applcntIhidnum) {
		this.applcntIhidnum = applcntIhidnum;
	}

	/**
	 * sbscrbDe attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}
	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * 
	 * @param sbscrbDe
	 *            String
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}

	/**
	 * applcntEmailAdres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getApplcntEmailAdres() {
		return applcntEmailAdres;
	}
	/**
	 * applcntEmailAdres attribute 값을 설정한다.
	 * 
	 * @param applcntEmailAdres
	 *            String
	 */
	public void setApplcntEmailAdres(String applcntEmailAdres) {
		this.applcntEmailAdres = applcntEmailAdres;
	}

	/**
	 * googleAccount attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getGoogleAccount() {
		return googleAccount;
	}
	/**
	 * googleAccount attribute 값을 설정한다.
	 * 
	 * @param googleAccount
	 *            String
	 */
	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	// helper
	/**
	 * userId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @param userId
	 *            String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userTy attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserTy() {
		return userTy;
	}
	/**
	 * userTy attribute 값을 설정한다.
	 * 
	 * @param userTy
	 *            String
	 */
	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}

	/**
	 * oldPassword attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * oldPassword attribute 값을 설정한다.
	 * 
	 * @param oldPassword
	 *            String
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * sbscrbSttus attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSbscrbSttus() {
		return sbscrbSttus;
	}
	/**
	 * sbscrbSttus attribute 값을 설정한다.
	 * 
	 * @param sbscrbSttus
	 *            String
	 */
	public void setSbscrbSttus(String sbscrbSttus) {
		this.sbscrbSttus = sbscrbSttus;
	}

}