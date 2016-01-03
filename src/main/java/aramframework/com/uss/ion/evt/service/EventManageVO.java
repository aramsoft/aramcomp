package aramframework.com.uss.ion.evt.service;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 행사관리에 대한 Vo 클래스를 정의한다.
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

public class EventManageVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	// 추가 검색 조건
	/** 검색 승인여부 */
	private String searchConfmAt;

	/** 검색 연도 */
	private String searchYear;

	/** 검색 월 */
	private String searchMonth;

	/** 검색 명 */
	private String searchNm;

	/** 검색 구분명 */
	private String searchSe;

	// 도메인 정보
	/** 행사기간 일수 */
	private int eventDayCount;
	
	/** 행사접수기간 일수 */
	private int eventAtdrnDayCount;

	/** 행사참여인원 */
	private int eventAtdrnCount;

	/** 행사ID */
	private String eventId;

	/** 행사구분 */
	private String eventSe;

	/** 행사구분명 */
	private String eventSeNm;

	/** 행사명 */
	private String eventNm;

	/** 행사목적 */
	private String eventPurps;

	/** 행사시작일자 */
	private String eventBeginDe;

	/** 행사종료일자 */
	private String eventEndDe;

	/** 행사주최기관명 */
	private String eventAuspcInsttNm;

	/** 행사주관기관명 */
	private String eventMngtInsttNm;

	/** 행사장소 */
	private String eventPlace;

	/** 행사내용 */
	private String eventCn;

	/** 비용발생여부 */
	private String ctOccrrncAt;

	/** 참가비용 */
	private int partcptCt;

	/** 정원 */
	private int garden;

	/** 참조URL */
	private String refrnUrl;

	/** 접수시작일자 */
	private String rceptBeginDe;

	/** 접수종료일자 */
	private String rceptEndDe;

	// 추가 검색
	/**
	 * @return the searchConfmAt
	 */
	public String getSearchConfmAt() {
		return searchConfmAt;
	}
	/**
	 * @param searchConfmAt
	 *            the searchConfmAt to set
	 */
	public void setSearchConfmAt(String searchConfmAt) {
		this.searchConfmAt = searchConfmAt;
	}

	/**
	 * @return the searchYear
	 */
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * @param searchYear
	 *            the searchYear to set
	 */
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	/**
	 * @return the searchMonth
	 */
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * @param searchMonth
	 *            the searchMonth to set
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	/**
	 * @return the searchNm
	 */
	public String getSearchNm() {
		return searchNm;
	}
	/**
	 * @param searchNm
	 *            the searchNm to set
	 */
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}

	/**
	 * @return the searchSe
	 */
	public String getSearchSe() {
		return searchSe;
	}
	/**
	 * @param searchSe
	 *            the searchSe to set
	 */
	public void setSearchSe(String searchSe) {
		this.searchSe = searchSe;
	}

	// 도메인 정보
	/**
	 * @return the eventDayCount
	 */
	public int getEventDayCount() {
		return eventDayCount;
	}
	/**
	 * @param eventDayCount
	 *            the eventDayCount to set
	 */
	public void setEventDayCount(int eventDayCount) {
		this.eventDayCount = eventDayCount;
	}

	/**
	 * @return the eventDayCount
	 */
	public int getEventAtdrnDayCount() {
		return eventAtdrnDayCount;
	}
	/**
	 * @param eventDayCount
	 *            the eventDayCount to set
	 */
	public void setEventAtdrnDayCount(int eventAtdrnDayCount) {
		this.eventAtdrnDayCount = eventAtdrnDayCount;
	}

	/**
	 * @return the eventAtdrnCount
	 */
	public int getEventAtdrnCount() {
		return eventAtdrnCount;
	}
	/**
	 * @param eventAtdrnCount
	 *            the eventAtdrnCount to set
	 */
	public void setEventAtdrnCount(int eventAtdrnCount) {
		this.eventAtdrnCount = eventAtdrnCount;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventSe
	 */
	public String getEventSe() {
		return eventSe;
	}
	/**
	 * @param eventSe
	 *            the eventSe to set
	 */
	public void setEventSe(String eventSe) {
		this.eventSe = eventSe;
	}

	/**
	 * @return the eventSeNm
	 */
	public String getEventSeNm() {
		return eventSeNm;
	}
	/**
	 * @param eventSeNm
	 *            the eventSeNm to set
	 */
	public void setEventSeNm(String eventSeNm) {
		this.eventSeNm = eventSeNm;
	}

	/**
	 * @return the eventNm
	 */
	public String getEventNm() {
		return eventNm;
	}
	/**
	 * @param eventNm
	 *            the eventNm to set
	 */
	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	/**
	 * @return the eventPurps
	 */
	public String getEventPurps() {
		return eventPurps;
	}
	/**
	 * @param eventPurps
	 *            the eventPurps to set
	 */
	public void setEventPurps(String eventPurps) {
		this.eventPurps = eventPurps;
	}

	/**
	 * @return the eventBeginDe
	 */
	public String getEventBeginDe() {
		return eventBeginDe;
	}
	/**
	 * @param eventBeginDe
	 *            the eventBeginDe to set
	 */
	public void setEventBeginDe(String eventBeginDe) {
		this.eventBeginDe = eventBeginDe;
	}

	/**
	 * @return the eventEndDe
	 */
	public String getEventEndDe() {
		return eventEndDe;
	}
	/**
	 * @param eventEndDe
	 *            the eventEndDe to set
	 */
	public void setEventEndDe(String eventEndDe) {
		this.eventEndDe = eventEndDe;
	}

	/**
	 * @return the eventAuspcInsttNm
	 */
	public String getEventAuspcInsttNm() {
		return eventAuspcInsttNm;
	}
	/**
	 * @param eventAuspcInsttNm
	 *            the eventAuspcInsttNm to set
	 */
	public void setEventAuspcInsttNm(String eventAuspcInsttNm) {
		this.eventAuspcInsttNm = eventAuspcInsttNm;
	}

	/**
	 * @return the eventMngtInsttNm
	 */
	public String getEventMngtInsttNm() {
		return eventMngtInsttNm;
	}
	/**
	 * @param eventMngtInsttNm
	 *            the eventMngtInsttNm to set
	 */
	public void setEventMngtInsttNm(String eventMngtInsttNm) {
		this.eventMngtInsttNm = eventMngtInsttNm;
	}

	/**
	 * @return the eventPlace
	 */
	public String getEventPlace() {
		return eventPlace;
	}
	/**
	 * @param eventPlace
	 *            the eventPlace to set
	 */
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	/**
	 * @return the eventCn
	 */
	public String getEventCn() {
		return eventCn;
	}
	/**
	 * @param eventCn
	 *            the eventCn to set
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}

	/**
	 * @return the ctOccrrncAt
	 */
	public String getCtOccrrncAt() {
		return ctOccrrncAt;
	}
	/**
	 * @param ctOccrrncAt
	 *            the ctOccrrncAt to set
	 */
	public void setCtOccrrncAt(String ctOccrrncAt) {
		this.ctOccrrncAt = ctOccrrncAt;
	}

	/**
	 * @return the partcptCt
	 */
	public int getPartcptCt() {
		return partcptCt;
	}
	/**
	 * @param partcptCt
	 *            the partcptCt to set
	 */
	public void setPartcptCt(int partcptCt) {
		this.partcptCt = partcptCt;
	}

	/**
	 * @return the garden
	 */
	public int getGarden() {
		return garden;
	}
	/**
	 * @param garden
	 *            the garden to set
	 */
	public void setGarden(int garden) {
		this.garden = garden;
	}

	/**
	 * @return the refrnUrl
	 */
	public String getRefrnUrl() {
		return refrnUrl;
	}
	/**
	 * @param refrnUrl
	 *            the refrnUrl to set
	 */
	public void setRefrnUrl(String refrnUrl) {
		this.refrnUrl = refrnUrl;
	}

	/**
	 * @return the rceptBeginDe
	 */
	public String getRceptBeginDe() {
		return rceptBeginDe;
	}
	/**
	 * @param rceptBeginDe
	 *            the rceptBeginDe to set
	 */
	public void setRceptBeginDe(String rceptBeginDe) {
		this.rceptBeginDe = rceptBeginDe;
	}

	/**
	 * @return the rceptEndDe
	 */
	public String getRceptEndDe() {
		return rceptEndDe;
	}
	/**
	 * @param rceptEndDe
	 *            the rceptEndDe to set
	 */
	public void setRceptEndDe(String rceptEndDe) {
		this.rceptEndDe = rceptEndDe;
	}

}
