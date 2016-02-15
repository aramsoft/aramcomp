package aramframework.com.uss.ion.ecc.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 외부인사관리 VO Class 구현
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

public class TnextrlHrInfoVO extends BaseVO {

	// domain
	/** 행사/이벤트/캠페인 아이디 */
	private String eventId = "";

	/** 외부인사ID */
	private String extrlHrId = "";

	/** 성별코드 */
	private String sexdstnCode = "";

	/** 외부인사명 */
	private String extrlHrNm = "";

	/** 직업유형코드 */
	private String occpTyCode = "";

	/** 소속기관명 */
	private String psitnInsttNm = "";

	/** 생년월일 */
	private String brth = "";

	/** 지역번호 */
	private String areaNo = "";

	/** 중간전화번호 */
	private String middleTelno = "";

	/** 끝전화번호 */
	private String endTelno = "";

	/** 이메일주소 */
	private String emailAdres = "";

	// helper
	/** 행사/이벤트/캠페인 아이디 */
	private String eventCn = "";

	/** 생년월일(년) */
	private String brthYYYY = "";

	/** 생년월일(월) */
	private String brthMM = "";

	/** 생년월일(일) */
	private String brthDD = "";

	/** 검색모드설정 */
	private String searchMode = "";

	// domain
	/**
	 * eventId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * eventId attribute 값을 설정한다.
	 * 
	 * @return eventId String
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * extrlHrId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getExtrlHrId() {
		return extrlHrId;
	}
	/**
	 * extrlHrId attribute 값을 설정한다.
	 * 
	 * @return extrlHrId String
	 */
	public void setExtrlHrId(String extrlHrId) {
		this.extrlHrId = extrlHrId;
	}

	/**
	 * sexdstnCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}
	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * 
	 * @return sexdstnCode String
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	/**
	 * extrlHrNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getExtrlHrNm() {
		return extrlHrNm;
	}
	/**
	 * extrlHrNm attribute 값을 설정한다.
	 * 
	 * @return extrlHrNm String
	 */
	public void setExtrlHrNm(String extrlHrNm) {
		this.extrlHrNm = extrlHrNm;
	}

	/**
	 * occpTyCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getOccpTyCode() {
		return occpTyCode;
	}
	/**
	 * occpTyCode attribute 값을 설정한다.
	 * 
	 * @return occpTyCode String
	 */
	public void setOccpTyCode(String occpTyCode) {
		this.occpTyCode = occpTyCode;
	}

	/**
	 * psitnInsttNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getPsitnInsttNm() {
		return psitnInsttNm;
	}
	/**
	 * psitnInsttNm attribute 값을 설정한다.
	 * 
	 * @return psitnInsttNm String
	 */
	public void setPsitnInsttNm(String psitnInsttNm) {
		this.psitnInsttNm = psitnInsttNm;
	}

	/**
	 * brth attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBrth() {
		return brth;
	}
	/**
	 * brth attribute 값을 설정한다.
	 * 
	 * @return brth String
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * areaNo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @return areaNo String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}
	/**
	 * middleTelno attribute 값을 설정한다.
	 * 
	 * @return middleTelno String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEndTelno() {
		return endTelno;
	}
	/**
	 * endTelno attribute 값을 설정한다.
	 * 
	 * @return endTelno String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	/**
	 * emailAdres attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * 
	 * @return emailAdres String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	// helper
	/**
	 * eventCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventCn() {
		return eventCn;
	}
	/**
	 * eventId attribute 값을 설정한다.
	 * 
	 * @return eventCn String
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}

	/**
	 * brthYYYY attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBrthYYYY() {
		return brthYYYY;
	}
	/**
	 * brthYYYY attribute 값을 설정한다.
	 * 
	 * @return brthYYYY String
	 */
	public void setBrthYYYY(String brthYYYY) {
		this.brthYYYY = brthYYYY;
	}

	/**
	 * brthMM attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBrthMM() {
		return brthMM;
	}
	/**
	 * brthMM attribute 값을 설정한다.
	 * 
	 * @return brthMM String
	 */
	public void setBrthMM(String brthMM) {
		this.brthMM = brthMM;
	}

	/**
	 * brthDD attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBrthDD() {
		return brthDD;
	}
	/**
	 * brthDD attribute 값을 설정한다.
	 * 
	 * @return brthDD String
	 */
	public void setBrthDD(String brthDD) {
		this.brthDD = brthDD;
	}

	/**
	 * searchMode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * searchMode attribute 값을 설정한다.
	 * 
	 * @return searchMode String
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

}
