package aramframework.com.uss.ion.ecc.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 행사/이벤트/캠페인 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class EventCmpgnVO extends BaseVO {

	// domain
	/** 행사/이벤트/캠페인ID */
	private String eventId = "";

	/** 사업년도 */
	private String bsnsYear = "";

	/** 사업코드 */
	private String bsnsCode = "";

	/** 행사내용 */
	private String eventCn = "";

	/** 행사시작일자 */
	private String eventSvcBeginDe = "";

	/** 서비스이용 인원수 */
	private int svcUseNmprCo = 0;

	/** 담당자명 */
	private String chargerNm = "";

	/** 준비물내용 */
	private String prparetgCn = "";

	/** 행사종료일자 */
	private String eventSvcEndDe = "";

	/** 행사유형코드 */
	private String eventTyCode = "";

	/** 행사/이벤트 승인여부 */
	private String eventConfmAt = "";

	/** 행사/이벤트 승인일 */
	private String eventConfmDe = "";

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
	 * bsnsYear attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBsnsYear() {
		return bsnsYear;
	}
	/**
	 * bsnsYear attribute 값을 설정한다.
	 * 
	 * @return bsnsYear String
	 */
	public void setBsnsYear(String bsnsYear) {
		this.bsnsYear = bsnsYear;
	}

	/**
	 * bsnsCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getBsnsCode() {
		return bsnsCode;
	}
	/**
	 * bsnsCode attribute 값을 설정한다.
	 * 
	 * @return bsnsCode String
	 */
	public void setBsnsCode(String bsnsCode) {
		this.bsnsCode = bsnsCode;
	}

	/**
	 * eventCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventCn() {
		return eventCn;
	}
	/**
	 * eventCn attribute 값을 설정한다.
	 * 
	 * @return eventCn String
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}

	/**
	 * eventSvcBeginDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventSvcBeginDe() {
		return eventSvcBeginDe;
	}
	/**
	 * eventSvcBeginDe attribute 값을 설정한다.
	 * 
	 * @return eventSvcBeginDe String
	 */
	public void setEventSvcBeginDe(String eventSvcBeginDe) {
		this.eventSvcBeginDe = eventSvcBeginDe;
	}

	/**
	 * svcUseNmprCo attribute 를 리턴한다.
	 * 
	 * @return the int
	 */
	public int getSvcUseNmprCo() {
		return svcUseNmprCo;
	}
	/**
	 * svcUseNmprCo attribute 값을 설정한다.
	 * 
	 * @return svcUseNmprCo int
	 */
	public void setSvcUseNmprCo(int svcUseNmprCo) {
		this.svcUseNmprCo = svcUseNmprCo;
	}

	/**
	 * chargerNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getChargerNm() {
		return chargerNm;
	}
	/**
	 * chargerNm attribute 값을 설정한다.
	 * 
	 * @return chargerNm String
	 */
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}

	/**
	 * prparetgCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getPrparetgCn() {
		return prparetgCn;
	}
	/**
	 * prparetgCn attribute 값을 설정한다.
	 * 
	 * @return prparetgCn String
	 */
	public void setPrparetgCn(String prparetgCn) {
		this.prparetgCn = prparetgCn;
	}

	/**
	 * eventSvcEndDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventSvcEndDe() {
		return eventSvcEndDe;
	}
	/**
	 * eventSvcEndDe attribute 값을 설정한다.
	 * 
	 * @return eventSvcEndDe String
	 */
	public void setEventSvcEndDe(String eventSvcEndDe) {
		this.eventSvcEndDe = eventSvcEndDe;
	}

	/**
	 * eventTyCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventTyCode() {
		return eventTyCode;
	}
	/**
	 * eventTyCode attribute 값을 설정한다.
	 * 
	 * @return eventTyCode String
	 */
	public void setEventTyCode(String eventTyCode) {
		this.eventTyCode = eventTyCode;
	}

	/**
	 * eventConfmAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventConfmAt() {
		return eventConfmAt;
	}
	/**
	 * eventConfmAt attribute 값을 설정한다.
	 * 
	 * @return eventId String
	 */
	public void setEventConfmAt(String eventConfmAt) {
		this.eventConfmAt = eventConfmAt;
	}

	/**
	 * eventConfmDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEventConfmDe() {
		return eventConfmDe;
	}
	/**
	 * eventConfmDe attribute 값을 설정한다.
	 * 
	 * @return eventId String
	 */
	public void setEventConfmDe(String eventConfmDe) {
		this.eventConfmDe = eventConfmDe;
	}

}
