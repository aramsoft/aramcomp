package aramframework.com.uss.olp.qrm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 설문응답자관리 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class QustnrRespondManageVO extends BaseVO  {

	// domain
	/** 설문지ID */
	private String qestnrId = "";

	/** 설문응답자아이디 */
	private String qestnrRespondId = "";

	/** 설별코드 */
	private String sexdstnCode = "";

	/** 직업유형코드 */
	private String occpTyCode = "";

	/** 응답자명 */
	private String respondNm = "";

	/** 생년월일 */
	private String brth = "";

	/** 첫번째전화번호 */
	private String areaNo = "";

	/** 두번째전화번호 */
	private String middleTelno = "";

	/** 마지막전화번호 */
	private String endTelno = "";

	// helper
	/** 설문제목 */
	private String qestnrSj = "";

	/** 검색모드설정 */
	private String searchMode = "";

	// domain
	/**
	 * qestnrId attribute 를 리턴한다.
	 * 
	 * @return the qestnrId
	 */
	public String getQestnrId() {
		return qestnrId;
	}
	/**
	 * qestnrId attribute 값을 설정한다.
	 * 
	 * @param qestnrId String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * qestnrRespondId attribute 를 리턴한다.
	 * 
	 * @return the qestnrRespondId
	 */
	public String getQestnrRespondId() {
		return qestnrRespondId;
	}
	/**
	 * qestnrRespondId attribute 값을 설정한다.
	 * 
	 * @param qestnrRespondId String
	 */
	public void setQestnrRespondId(String qestnrRespondId) {
		this.qestnrRespondId = qestnrRespondId;
	}

	/**
	 * sexdstnCode attribute 를 리턴한다.
	 * 
	 * @return the sexdstnCode
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}
	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * 
	 * @param 	sexdstnCode 	String
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	/**
	 * occpTyCode attribute 를 리턴한다.
	 * 
	 * @return the occpTyCode
	 */
	public String getOccpTyCode() {
		return occpTyCode;
	}
	/**
	 * occpTyCode attribute 값을 설정한다.
	 * 
	 * @param 	occpTyCode 	String
	 */
	public void setOccpTyCode(String occpTyCode) {
		this.occpTyCode = occpTyCode;
	}

	/**
	 * respondNm attribute 를 리턴한다.
	 * 
	 * @return the respondNm
	 */
	public String getRespondNm() {
		return respondNm;
	}
	/**
	 * respondNm attribute 값을 설정한다.
	 * 
	 * @param 	respondNm 	String
	 */
	public void setRespondNm(String respondNm) {
		this.respondNm = respondNm;
	}

	/**
	 * brth attribute 를 리턴한다.
	 * 
	 * @return the brth
	 */
	public String getBrth() {
		return brth;
	}
	/**
	 * brth attribute 값을 설정한다.
	 * 
	 * @param 	brth 	String
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * areaNo attribute 를 리턴한다.
	 * 
	 * @return the areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @param 	areaNo 	String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * 
	 * @return the middleTelno
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}
	/**
	 * middleTelno attribute 값을 설정한다.
	 * 
	 * @param 	middleTelno 	String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * 
	 * @return the endTelno
	 */
	public String getEndTelno() {
		return endTelno;
	}
	/**
	 * endTelno attribute 값을 설정한다.
	 * 
	 * @param 	endTelno 	String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	// helper
	/**
	 * qestnrSj attribute 를 리턴한다.
	 * 
	 * @return the qestnrSj
	 */
	public String getQestnrSj() {
		return qestnrSj;
	}
	/**
	 * qestnrSj attribute 값을 설정한다.
	 * 
	 * @searchMode 	qestnrSj 	String
	 */
	public void setQestnrSj(String qestnrSj) {
		this.qestnrSj = qestnrSj;
	}

	/**
	 * searchMode attribute 를 리턴한다.
	 * 
	 * @return the searchMode
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * searchMode attribute 값을 설정한다.
	 * 
	 * @searchMode searchMode String
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

}
