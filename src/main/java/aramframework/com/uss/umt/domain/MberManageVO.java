package aramframework.com.uss.umt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일                 수정자          수정내용
 *   -------     ------   ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2012.08.25  조헌철          패키지수정
 * 
 * </pre>
 */

public class MberManageVO extends BaseVO {

	// domain
	/** 회원 ID */
	private String mberId;
	
	/** 비밀번호 */
	private String password;
	
	/** 비밀번호 힌트 */
	private String passwordHint;
	
	/** 비밀번호 정답 */
	private String passwordCnsr;
	
	/** 주민등록번호 */
	private String ihidnum;
	
	/** 회원명 */
	private String mberNm;
	
	/** 우편번호 */
	private String zip;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 지역번호 */
	private String areaNo;
	
	/** 중간전화번호 */
	private String middleTelno;
	
	/** 끝전화번호 */
	private String endTelno;
	
	/** 핸드폰번호 */
	private String moblphonNo;
	
	/** 회원상태 */
	private String mberSttus;
	
	/** 팩스번호 */
	private String mberFxnum;
	
	/** 이메일주소 */
	private String mberEmailAdres;

	/** 가입 일자 */
	private String sbscrbDe;
	
	/** 성별코드 */
	private String sexdstnCode;
	
	/** 사용자고유아이디 */
	private String uniqId = "";
	
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

	// domain
	/**
	 * mberId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * mberId attribute 값을 설정한다.
	 * 
	 * @param mberId
	 *            String
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
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
	 * mberNm attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberNm() {
		return mberNm;
	}
	/**
	 * mberNm attribute 값을 설정한다.
	 * 
	 * @param mberNm
	 *            String
	 */
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
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
	 * middleTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}
	/**
	 * middleTelno attribute 값을 설정한다.
	 * 
	 * @param middleTelno
	 *            String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getEndTelno() {
		return endTelno;
	}
	/**
	 * endTelno attribute 값을 설정한다.
	 * 
	 * @param endTelno
	 *            String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
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
	 * mberSttus attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberSttus() {
		return mberSttus;
	}
	/**
	 * mberSttus attribute 값을 설정한다.
	 * 
	 * @param mberSttus
	 *            String
	 */
	public void setMberSttus(String mberSttus) {
		this.mberSttus = mberSttus;
	}

	/**
	 * mberFxnum attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberFxnum() {
		return mberFxnum;
	}
	/**
	 * mberFxnum attribute 값을 설정한다.
	 * 
	 * @param mberFxnum
	 *            String
	 */
	public void setMberFxnum(String mberFxnum) {
		this.mberFxnum = mberFxnum;
	}

	/**
	 * mberEmailAdres attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getMberEmailAdres() {
		return mberEmailAdres;
	}
	/**
	 * mberEmailAdres attribute 값을 설정한다.
	 * 
	 * @param mberEmailAdres
	 *            String
	 */
	public void setMberEmailAdres(String mberEmailAdres) {
		this.mberEmailAdres = mberEmailAdres;
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
	 * uniqId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId
	 *            String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
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