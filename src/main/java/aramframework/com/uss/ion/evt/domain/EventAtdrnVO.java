package aramframework.com.uss.ion.evt.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 행사참석자에 대한 Vo 클래스를 정의한다.
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

public class EventAtdrnVO  extends SearchVO  {

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
	/** 신청자ID */
	private String applcntId;

	/** 신청자명 */
	private String applcntNm;

	/** 신청자부서 */
	private String orgnztNm;

	/** 행사ID */
	private String eventId;

	/** 신청일자 */
	private String reqstDe;

	/** 결재자ID */
	private String sanctnerId;

	/** 결재자명 */
	private String sanctnerNm;

	/** 승인여부 */
	private String confmAt;

	/** 결재일시 */
	private String sanctnDt;

	/** 반려사유 */
	private String returnResn;

	/** 약식결재ID */
	private String infrmlSanctnId;

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

	/**
	 * @return the applcntId
	 */
	public String getApplcntId() {
		return applcntId;
	}
	/**
	 * @param applcntId
	 *            the applcntId to set
	 */
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	/**
	 * @return the applcntNm
	 */
	public String getApplcntNm() {
		return applcntNm;
	}
	/**
	 * @param applcntNm
	 *            the applcntNm to set
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
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
	 * @return the reqstDe
	 */
	public String getReqstDe() {
		return reqstDe;
	}
	/**
	 * @param reqstDe
	 *            the reqstDe to set
	 */
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	/**
	 * @return the sanctnerId
	 */
	public String getSanctnerId() {
		return sanctnerId;
	}
	/**
	 * @param sanctnerId
	 *            the sanctnerId to set
	 */
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	/**
	 * @return the sanctnerNm
	 */
	public String getSanctnerNm() {
		return sanctnerNm;
	}
	/**
	 * @param sanctnerNm
	 *            the sanctnerNm to set
	 */
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}

	/**
	 * @return the confmAt
	 */
	public String getConfmAt() {
		return confmAt;
	}
	/**
	 * @param confmAt
	 *            the confmAt to set
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * @return the sanctnDt
	 */
	public String getSanctnDt() {
		return sanctnDt;
	}
	/**
	 * @param sanctnDt
	 *            the sanctnDt to set
	 */
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	/**
	 * @return the returnResn
	 */
	public String getReturnResn() {
		return returnResn;
	}
	/**
	 * @param returnResn
	 *            the returnResn to set
	 */
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

	/**
	 * @return the infrmlSanctnId
	 */
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}
	/**
	 * @param infrmlSanctnId
	 *            the infrmlSanctnId to set
	 */
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
	}

}