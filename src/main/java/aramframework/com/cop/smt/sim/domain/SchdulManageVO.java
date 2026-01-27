package aramframework.com.cop.smt.sim.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 일정관리 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class SchdulManageVO extends BaseVO  {

	// domain
	/** 일정ID */
	private String schdulId;

	/** 일정구분(회의/교육/세미나/강의 기타) */
	private String schdulSe;

	/** 공개범위 */
	private String othbcScope;

	/** 일정시작일자 */
	private String schdulBgnde;

	/** 일정종료일자 */
	private String schdulEndde;

	/** 일정명 */
	private String schdulNm;

	/** 일정내용 */
	private String schdulCn;

	/** 일정장소 */
	private String schdulPlace;

	/** 일정중요도코드 */
	private String schdulIpcrCode;

	/** 일정담담자ID */
	private String schdulChargerId;

	/** 첨부파일ID */
	private String atchFileId;

	/** 반복구분(반복, 연속, 요일반복) */
	private String reptitSeCode;

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 유저ID */
	private String userId;
	
	/** 담당자명 */
	private String schdulChargerName = "";

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

	/** 검색일 */
	private String searchDay = "";
	
	/** 검색시작일 */
	private String searchBgnde = "";
	
	/** 검색종료일 */
	private String searchEndde = "";
	
	// domain
	/**
	 * schdulId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulId() {
		return schdulId;
	}

	/**
	 * schdulId attribute 값을 설정한다.
	 * 
	 * @return schdulId String
	 */
	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}
	
	/**
	 * schdulSe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulSe() {
		return schdulSe;
	}
	/**
	 * schdulSe attribute 값을 설정한다.
	 * 
	 * @return schdulSe String
	 */
	public void setSchdulSe(String schdulSe) {
		this.schdulSe = schdulSe;
	}

	/**
	 * othbcScope attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getOthbcScope() {
		return othbcScope;
	}
	/**
	 * othbcScope attribute 값을 설정한다.
	 * 
	 * @return othbcScope String
	 */
	public void setOthbcScope(String othbcScope) {
		this.othbcScope = othbcScope;
	}

	/**
	 * schdulBgnde attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulBgnde() {
		return schdulBgnde;
	}
	/**
	 * schdulBgnde attribute 값을 설정한다.
	 * 
	 * @return schdulBgnde String
	 */
	public void setSchdulBgnde(String schdulBgnde) {
		this.schdulBgnde = schdulBgnde;
	}

	/**
	 * schdulEndde attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulEndde() {
		return schdulEndde;
	}
	/**
	 * schdulEndde attribute 값을 설정한다.
	 * 
	 * @return schdulEndde String
	 */
	public void setSchdulEndde(String schdulEndde) {
		this.schdulEndde = schdulEndde;
	}

	/**
	 * schdulNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulNm() {
		return schdulNm;
	}
	/**
	 * schdulNm attribute 값을 설정한다.
	 * 
	 * @return schdulNm String
	 */
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	/**
	 * schdulCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulCn() {
		return schdulCn;
	}
	/**
	 * schdulCn attribute 값을 설정한다.
	 * 
	 * @return schdulCn String
	 */
	public void setSchdulCn(String schdulCn) {
		this.schdulCn = schdulCn;
	}

	/**
	 * schdulPlace attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulPlace() {
		return schdulPlace;
	}
	/**
	 * schdulPlace attribute 값을 설정한다.
	 * 
	 * @return schdulPlace String
	 */
	public void setSchdulPlace(String schdulPlace) {
		this.schdulPlace = schdulPlace;
	}

	/**
	 * schdulIpcrCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulIpcrCode() {
		return schdulIpcrCode;
	}
	/**
	 * schdulIpcrCode attribute 값을 설정한다.
	 * 
	 * @return schdulIpcrCode String
	 */
	public void setSchdulIpcrCode(String schdulIpcrCode) {
		this.schdulIpcrCode = schdulIpcrCode;
	}

	/**
	 * schdulChargerId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulChargerId() {
		return schdulChargerId;
	}
	/**
	 * schdulChargerId attribute 값을 설정한다.
	 * 
	 * @return schdulChargerId String
	 */
	public void setSchdulChargerId(String schdulChargerId) {
		this.schdulChargerId = schdulChargerId;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * reptitSeCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getReptitSeCode() {
		return reptitSeCode;
	}
	/**
	 * reptitSeCode attribute 값을 설정한다.
	 * 
	 * @return reptitSeCode String
	 */
	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}

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

	// helper
	/**
	 * userId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * 
	 * @return userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * schdulChargerName attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulChargerName() {
		return schdulChargerName;
	}
	/**
	 * schdulChargerName attribute 값을 설정한다.
	 * 
	 * @return schdulChargerName String
	 */
	public void setSchdulChargerName(String schdulChargerName) {
		this.schdulChargerName = schdulChargerName;
	}

	/**
	 * schdulBgndeHH attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulBgndeHH() {
		return schdulBgndeHH;
	}
	/**
	 * schdulBgndeHH attribute 값을 설정한다.
	 * 
	 * @return schdulBgndeHH String
	 */
	public void setSchdulBgndeHH(String schdulBgndeHH) {
		this.schdulBgndeHH = schdulBgndeHH;
	}

	/**
	 * schdulBgndeMM attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulBgndeMM() {
		return schdulBgndeMM;
	}
	/**
	 * schdulBgndeMM attribute 값을 설정한다.
	 * 
	 * @return schdulBgndeMM String
	 */
	public void setSchdulBgndeMM(String schdulBgndeMM) {
		this.schdulBgndeMM = schdulBgndeMM;
	}

	/**
	 * schdulEnddeHH attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulEnddeHH() {
		return schdulEnddeHH;
	}
	/**
	 * schdulEnddeHH attribute 값을 설정한다.
	 * 
	 * @return schdulEnddeHH String
	 */
	public void setSchdulEnddeHH(String schdulEnddeHH) {
		this.schdulEnddeHH = schdulEnddeHH;
	}

	/**
	 * schdulEnddeMM attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulEnddeMM() {
		return schdulEnddeMM;
	}
	/**
	 * schdulEnddeMM attribute 값을 설정한다.
	 * 
	 * @return schdulEnddeMM String
	 */
	public void setSchdulEnddeMM(String schdulEnddeMM) {
		this.schdulEnddeMM = schdulEnddeMM;
	}

	/**
	 * schdulBgndeYYYMMDD attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulBgndeYYYMMDD() {
		return schdulBgndeYYYMMDD;
	}
	/**
	 * schdulBgndeYYYMMDD attribute 값을 설정한다.
	 * 
	 * @return schdulBgndeYYYMMDD String
	 */
	public void setSchdulBgndeYYYMMDD(String schdulBgndeYYYMMDD) {
		this.schdulBgndeYYYMMDD = schdulBgndeYYYMMDD;
	}

	/**
	 * schdulEnddeYYYMMDD attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSchdulEnddeYYYMMDD() {
		return schdulEnddeYYYMMDD;
	}
	/**
	 * schdulEnddeYYYMMDD attribute 값을 설정한다.
	 * 
	 * @return schdulEnddeYYYMMDD String
	 */
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
