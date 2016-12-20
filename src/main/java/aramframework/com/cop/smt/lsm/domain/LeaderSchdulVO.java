package aramframework.com.cop.smt.lsm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 간부일정에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class LeaderSchdulVO extends BaseVO  {

	// domain
	/** 일정ID	 */
	private String schdulId;

	/** 일정구분	 */
	private String schdulSe;

	/** 일정명	 */
	private String schdulNm;

	/** 일정내용	 */
	private String schdulCn;

	/** 일정장소	 */
	private String schdulPlace;

	/** 간부ID */
	private String leaderId;

	/** 반복구분코드	 */
	private String reptitSeCode;

	/** 일정시작일자	 */
	private String schdulBgnde;

	/** 일정종료일자	 */
	private String schdulEndde;

	/** 일정담당자ID	 */
	private String schdulChargerId;

	// helper
	/** 간부명	 */
	private String leaderName;

	/** 일정담당자명	 */
	private String schdulChargerName;

	/** 일정시작일자(시간) */
	private String schdulBgndeHH = "";

	/** 일정시작일자(분) */
	private String schdulBgndeMM = "";

	/** 일정종료일자(시간) */
	private String schdulEnddeHH = "";

	/** 일정종료일자(분) */
	private String schdulEnddeMM = "";

	/** 일정시작일자(Year/Month/Day) */
	private String schdulBgndeYYYMMDD = "";

	/** 일정종료일자(Year/Month/Day) */
	private String schdulEnddeYYYMMDD = "";

	// 페이지간 검색유지
	/** 검색일정구분 */
	private String searchSchdulSe = "";

	/** 검색대상자 */
	private String searchUser = "";

	/** 년 */
	private String  year = "";

	/** 월 */
	private String  month = "";

	/** 주 */ 
	private String  week = "";
	
	/** 일 */
	private String  day = "";

	// IBatis 검색용
	/** 검색모드(월/주/일) */
	private String searchMode = "";
	
	/** 검색월 */
	private String searchMonth = "";

	/** 검색시작일 */
	private String searchBgnde = "";
	
	/** 검색종료일 */
	private String searchEndde = "";
	
	/** 검색일 */
	private String searchDay = "";
	
	// domain
	public String getSchdulId() {
		return schdulId;
	}
	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}

	public String getSchdulSe() {
		return schdulSe;
	}
	public void setSchdulSe(String schdulSe) {
		this.schdulSe = schdulSe;
	}

	public String getSchdulNm() {
		return schdulNm;
	}
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	public String getSchdulCn() {
		return schdulCn;
	}
	public void setSchdulCn(String schdulCn) {
		this.schdulCn = schdulCn;
	}

	public String getSchdulPlace() {
		return schdulPlace;
	}
	public void setSchdulPlace(String schdulPlace) {
		this.schdulPlace = schdulPlace;
	}

	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getReptitSeCode() {
		return reptitSeCode;
	}
	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}

	public String getSchdulBgnde() {
		return schdulBgnde;
	}
	public void setSchdulBgnde(String schdulBgnde) {
		this.schdulBgnde = schdulBgnde;
	}

	public String getSchdulEndde() {
		return schdulEndde;
	}
	public void setSchdulEndde(String schdulEndde) {
		this.schdulEndde = schdulEndde;
	}

	public String getSchdulChargerId() {
		return schdulChargerId;
	}
	public void setSchdulChargerId(String schdulChargerId) {
		this.schdulChargerId = schdulChargerId;
	}

	// helper
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getSchdulChargerName() {
		return schdulChargerName;
	}
	public void setSchdulChargerName(String schdulChargerName) {
		this.schdulChargerName = schdulChargerName;
	}

	public String getSchdulBgndeHH() {
		return schdulBgndeHH;
	}
	public void setSchdulBgndeHH(String schdulBgndeHH) {
		this.schdulBgndeHH = schdulBgndeHH;
	}

	public String getSchdulBgndeMM() {
		return schdulBgndeMM;
	}
	public void setSchdulBgndeMM(String schdulBgndeMM) {
		this.schdulBgndeMM = schdulBgndeMM;
	}

	public String getSchdulEnddeHH() {
		return schdulEnddeHH;
	}
	public void setSchdulEnddeHH(String schdulEnddeHH) {
		this.schdulEnddeHH = schdulEnddeHH;
	}

	public String getSchdulEnddeMM() {
		return schdulEnddeMM;
	}
	public void setSchdulEnddeMM(String schdulEnddeMM) {
		this.schdulEnddeMM = schdulEnddeMM;
	}

	public String getSchdulBgndeYYYMMDD() {
		return schdulBgndeYYYMMDD;
	}
	public void setSchdulBgndeYYYMMDD(String schdulBgndeYYYMMDD) {
		this.schdulBgndeYYYMMDD = schdulBgndeYYYMMDD;
	}

	public String getSchdulEnddeYYYMMDD() {
		return schdulEnddeYYYMMDD;
	}
	public void setSchdulEnddeYYYMMDD(String schdulEnddeYYYMMDD) {
		this.schdulEnddeYYYMMDD = schdulEnddeYYYMMDD;
	}

	/**
	 * @return the searchSchdulSe
	 */
	public String getSearchSchdulSe() {
		return searchSchdulSe;
	}
	/**
	 * @param searchSchdulSe the searchSchdulSe to set
	 */
	public void setSearchSchdulSe(String searchSchdulSe) {
		this.searchSchdulSe = searchSchdulSe;
	}

	/**
	 * @return the searchUser
	 */
	public String getSearchUser() {
		return searchUser;
	}
	/**
	 * @param searchUser the searchSchdulSe to set
	 */
	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}
	/**
	 * @param week the month to set
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the searchMode
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * @param searchMode the searchMode to set
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	/**
	 * @return the searchMonth
	 */
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * @param searchMonth the searchMonth to set
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	/**
	 * @return the searchBgnde
	 */
	public String getSearchBgnde() {
		return searchBgnde;
	}
	/**
	 * @param searchBgnde the searchBgnde to set
	 */
	public void setSearchBgnde(String searchBgnde) {
		this.searchBgnde = searchBgnde;
	}

	/**
	 * @return the searchEndde
	 */
	public String getSearchEndde() {
		return searchEndde;
	}
	/**
	 * @param searchEndde the searchEndde to set
	 */
	public void setSearchEndde(String searchEndde) {
		this.searchEndde = searchEndde;
	}

	/**
	 * @return the searchDay
	 */
	public String getSearchDay() {
		return searchDay;
	}
	/**
	 * @param searchDay the searchDay to set
	 */
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

}