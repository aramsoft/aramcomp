package aramframework.com.sym.cal.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 휴일 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class RestdeVO extends BaseVO {

	// domain
	/** 휴일번호 */
	private int restdeNo = 0;

	/** 휴일일자 */
	private String restdeDe = "";

	/** 휴일명 */
	private String restdeNm = "";

	/** 휴일설명 */
	private String restdeDc = "";

	/** 휴일구분코드 */
	private String restdeSeCode = "";

	// helper
	/** 휴일구분 */
	private String restdeSe = "";

	/** 년 */
	private String year = "";

	/** 월 */
	private String month = "";

	/** 일 */
	private String day = "";

	/** 휴일여부 */
	private String restdeAt = "";

	/** 달력셀 */
	private int cellNum = 0;

	/** 월별 주순위 */
	private int weeks = 0;

	/** 월 주수 */
	private int maxWeeks = 0;

	/** 요일 */
	private int week = 0;

	/** 시작요일 */
	private int startWeekMonth = 0;

	/** 마지막 일자 */
	private int lastDayMonth = 0;

	// domain
	/**
	 * restdeNo attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getRestdeNo() {
		return restdeNo;
	}
	/**
	 * restdeNo attribute 값을 설정한다.
	 * 
	 * @param restdeNo
	 *            int
	 */
	public void setRestdeNo(int restdeNo) {
		this.restdeNo = restdeNo;
	}

	/**
	 * restdeDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeDe() {
		return restdeDe;
	}
	/**
	 * restdeDe attribute 값을 설정한다.
	 * 
	 * @param restdeDe
	 *            String
	 */
	public void setRestdeDe(String restdeDe) {
		this.restdeDe = restdeDe;
	}

	/**
	 * restdeNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeNm() {
		return restdeNm;
	}
	/**
	 * restdeNm attribute 값을 설정한다.
	 * 
	 * @param restdeNm
	 *            String
	 */
	public void setRestdeNm(String restdeNm) {
		this.restdeNm = restdeNm;
	}

	/**
	 * restdeDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeDc() {
		return restdeDc;
	}
	/**
	 * restdeDc attribute 값을 설정한다.
	 * 
	 * @param restdeDc
	 *            String
	 */
	public void setRestdeDc(String restdeDc) {
		this.restdeDc = restdeDc;
	}

	/**
	 * restdeSeCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeSeCode() {
		return restdeSeCode;
	}
	/**
	 * restdeSeCode attribute 값을 설정한다.
	 * 
	 * @param restdeSeCode
	 *            String
	 */
	public void setRestdeSeCode(String restdeSeCode) {
		this.restdeSeCode = restdeSeCode;
	}

	// helper
	/**
	 * restdeSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeSe() {
		return restdeSe;
	}
	/**
	 * restdeSe attribute 값을 설정한다.
	 * 
	 * @param restdeSe
	 *            String
	 */
	public void setRestdeSe(String restdeSe) {
		this.restdeSe = restdeSe;
	}

	/**
	 * year attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getYear() {
		return year;
	}
	/**
	 * year attribute 값을 설정한다.
	 * 
	 * @param year
	 *            String
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * month attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * month attribute 값을 설정한다.
	 * 
	 * @param month
	 *            String
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * day attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getDay() {
		return day;
	}
	/**
	 * day attribute 값을 설정한다.
	 * 
	 * @param day
	 *            String
	 */
	public void setDay(String day) {
		this.day = day;
	}
	
	/**
	 * restdeAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRestdeAt() {
		return restdeAt;
	}
	/**
	 * restdeAt attribute 값을 설정한다.
	 * 
	 * @param restdeAt
	 *            String
	 */
	public void setRestdeAt(String restdeAt) {
		this.restdeAt = restdeAt;
	}
	
	/**
	 * cellNum attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getCellNum() {
		return cellNum;
	}
	/**
	 * cellNum attribute 값을 설정한다.
	 * 
	 * @param cellNum
	 *            int
	 */
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}

	/**
	 * weeks attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getWeeks() {
		return weeks;
	}
	/**
	 * weeks attribute 값을 설정한다.
	 * 
	 * @param weeks
	 *            int
	 */
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	/**
	 * maxWeeks attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getMaxWeeks() {
		return maxWeeks;
	}
	/**
	 * maxWeeks attribute 값을 설정한다.
	 * 
	 * @param maxWeeks
	 *            int
	 */
	public void setMaxWeeks(int maxWeeks) {
		this.maxWeeks = maxWeeks;
	}

	/**
	 * week attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getWeek() {
		return week;
	}
	/**
	 * week attribute 값을 설정한다.
	 * 
	 * @param week
	 *            int
	 */
	public void setWeek(int week) {
		this.week = week;
	}

	/**
	 * startWeekMonth attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getStartWeekMonth() {
		return startWeekMonth;
	}
	/**
	 * startWeekMonth attribute 값을 설정한다.
	 * 
	 * @param startWeekMonth
	 *            int
	 */
	public void setStartWeekMonth(int startWeekMonth) {
		this.startWeekMonth = startWeekMonth;
	}

	/**
	 * lastDayMonth attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getLastDayMonth() {
		return lastDayMonth;
	}
	/**
	 * lastDayMonth attribute 값을 설정한다.
	 * 
	 * @param lastDayMonth
	 *            int
	 */
	public void setLastDayMonth(int lastDayMonth) {
		this.lastDayMonth = lastDayMonth;
	}

}
