package aramframework.com.uss.umt.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 업무사용자VO클래스로서 업무사용자관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class EmplyrManageVO extends BaseVO {

	// domain
	/** 사용자 ID */
	private String emplyrId;
	
	/** 조직 ID */
	private String orgnztId;
	
	/** 사용자 명 */
	private String emplyrNm;
	
	/** 비밀번호 */
	private String password;
	
	/** 사원번호 */
	private String emplNo;
	
	/** 주민등록번호 */
	private String ihidnum;
	
	/** 성별코드	*/
	private String sexdstnCode;
	
	/** 생일 */
	private String brth;
	
	/** 팩스번호 */
	private String fxnum;
	
	/** 비밀번호 힌트 */
	private String passwordHint;
	
	/** 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 집중간전화번호 */
	private String homemiddleTelno;
	
	/** 집끝전화번호 */
	private String homeendTelno;
	
	/** 우편번호 */
	private String zip;
	
	/** 집 주소 */
	private String homeadres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 사무실전화번호 */
	private String offmTelno;
	
	/** 핸드폰번호 */
	private String moblphonNo;
	
	/** 이메일주소 */
	private String emailAdres;
	
	/** 직위명 */
	private String ofcpsNm;
	
	/** 소속기관코드 */
	private String insttCode;
	
	/** 사용자 상태 */
	private String emplyrSttusCode;
	
	/** DN 값 */
	private String subDn;

	/** 가입일 */
	private String sbscrbDe;
	
	/** 구글 계정 */
	private String googleAccount;

	// helper
	/** 사용자 유형 */
	private String userTy;
	
	// 추가 검색 조건
	/** 이전비밀번호 - 비밀번호 변경시 사용 */
	private String oldPassword = "";

	/** 검색조건-회원상태 (0, A, D, P) */
	private String sbscrbSttus = "0";

	/** 검색조건 회원타입  */
	private String mberTy;

	/** 검색조건 가입일자 시작일  */
	private String sbscrbDeBegin;

	/** 검색조건 가입일자 종료일  */
	private String sbscrbDeEnd;

	// domain
	/**
	 * emplyrId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정한다.
	 * 
	 * @param emplyrId
	 *            String
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * orgnztId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * orgnztId attribute 값을 설정한다.
	 * 
	 * @param orgnztId
	 *            String
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * emplyrNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * emplyrNm attribute 값을 설정한다.
	 * 
	 * @param emplyrNm
	 *            String
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
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
	 * emplNo attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmplNo() {
		return emplNo;
	}
	/**
	 * emplNo attribute 값을 설정한다.
	 * 
	 * @param emplNo
	 *            String
	 */
	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

	/**
	 * ihidnum attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getIhidnum() {
		return ihidnum;
	}
	/**
	 * ihidnum attribute 값을 설정한다.
	 * 
	 * @param ihidnum
	 *            String
	 */
	public void setIhidnum(String ihidnum) {
		this.ihidnum = ihidnum;
	}

	/**
	 * sexdstnCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}
	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * 
	 * @param sexdstnCode
	 *            String
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	/**
	 * brth attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getBrth() {
		return brth;
	}
	/**
	 * brth attribute 값을 설정한다.
	 * 
	 * @param brth
	 *            String
	 */
	public void setBrth(String brth) {
		this.brth = brth;
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
	 * homemiddleTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getHomemiddleTelno() {
		return homemiddleTelno;
	}
	/**
	 * homemiddleTelno attribute 값을 설정한다.
	 * 
	 * @param homemiddleTelno
	 *            String
	 */
	public void setHomemiddleTelno(String homemiddleTelno) {
		this.homemiddleTelno = homemiddleTelno;
	}

	/**
	 * homeendTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getHomeendTelno() {
		return homeendTelno;
	}
	/**
	 * homeendTelno attribute 값을 설정한다.
	 * 
	 * @param homeendTelno
	 *            String
	 */
	public void setHomeendTelno(String homeendTelno) {
		this.homeendTelno = homeendTelno;
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
	 * homeadres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getHomeadres() {
		return homeadres;
	}
	/**
	 * homeadres attribute 값을 설정한다.
	 * 
	 * @param homeadres
	 *            String
	 */
	public void setHomeadres(String homeadres) {
		this.homeadres = homeadres;
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
	 * offmTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getOffmTelno() {
		return offmTelno;
	}

	/**
	 * offmTelno attribute 값을 설정한다.
	 * 
	 * @param offmTelno
	 *            String
	 */
	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	/**
	 * moblphonNo attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMoblphonNo() {
		return moblphonNo;
	}
	/**
	 * moblphonNo attribute 값을 설정한다.
	 * 
	 * @param moblphonNo
	 *            String
	 */
	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}

	/**
	 * emailAdres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * 
	 * @param emailAdres
	 *            String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * ofcpsNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getOfcpsNm() {
		return ofcpsNm;
	}
	/**
	 * ofcpsNm attribute 값을 설정한다.
	 * 
	 * @param ofcpsNm
	 *            String
	 */
	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}
	
	/**
	 * insttCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getInsttCode() {
		return insttCode;
	}
	/**
	 * insttCode attribute 값을 설정한다.
	 * 
	 * @param insttCode
	 *            String
	 */
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}

	/**
	 * emplyrSttusCode attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmplyrSttusCode() {
		return emplyrSttusCode;
	}
	/**
	 * emplyrSttusCode attribute 값을 설정한다.
	 * 
	 * @param emplyrSttusCode
	 *            String
	 */
	public void setEmplyrSttusCode(String emplyrSttusCode) {
		this.emplyrSttusCode = emplyrSttusCode;
	}

	/**
	 * subDn attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSubDn() {
		return subDn;
	}
	/**
	 * subDn attribute 값을 설정한다.
	 * 
	 * @param subDn
	 *            String
	 */
	public void setSubDn(String subDn) {
		this.subDn = subDn;
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

	// 추가 검색 조건
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

	/**
	 * mberTy attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberTy() {
		return mberTy;
	}
	/**
	 * mberTy attribute 값을 설정한다.
	 * 
	 * @param mberTy
	 *            String
	 */
	public void setMberTy(String mberTy) {
		this.mberTy = mberTy;
	}

	/**
	 * sbscrbDeBegin attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSbscrbDeBegin() {
		return sbscrbDeBegin;
	}
	/**
	 * sbscrbDeBegin attribute 값을 설정한다.
	 * 
	 * @param sbscrbDeBegin
	 *            String
	 */
	public void setSbscrbDeBegin(String sbscrbDeBegin) {
		this.sbscrbDeBegin = sbscrbDeBegin;
	}

	/**
	 * sbscrbDeEnd attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getSbscrbDeEnd() {
		return sbscrbDeEnd;
	}
	/**
	 * sbscrbDeEnd attribute 값을 설정한다.
	 * 
	 * @param sbscrbDeEnd
	 *            String
	 */
	public void setSbscrbDeEnd(String sbscrbDeEnd) {
		this.sbscrbDeEnd = sbscrbDeEnd;
	}

}