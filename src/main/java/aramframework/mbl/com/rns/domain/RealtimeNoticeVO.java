package aramframework.mbl.com.rns.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 실시간 공지 서비스에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 실시간 공지 서비스 정보를 조회하기 위해 필요한 정보를 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class RealtimeNoticeVO extends BaseVO  {

	/** 실시간 공지 서비스 일련번호	 */
	private int sn = 0;
	
	/** 회원ID	 */
	private String mberId = "";
	
	/** 실시간 공지 제목	 */
	private String noticeSj = "";
	
	/** 실시간 공지 내용 	 */
	private String noticeCn = "";
	
	/** 최신구분 코드 ID	 */
	private String recentCodeId = "";
	
	/** 최신구분 코드 	 */
	private String recentCode = "";
	
	/** 실시간 공지 시작일시	 */
	private String noticeBgnDt = "";
	
	/** 실시간 공지 종료일시	 */
	private String noticeEndDt = "";
	
	/** 실시간 공지 생성일시	 */
	private String creatDt = "";
	
	/** 실시간 공지 수정일시	 */
	private String updtDt = "";
	
	// helper
	/** 최신구분명	 */
	private String recentNm = "";
	
	/** 일련번호	 */	
	private String searchSn = "";
	
	/** 목록 조회 갯수	 */
	private int fetchRow = 0;

	// domain
	/**
	 * 실시간 공지 서비스 일련번호를 획득한다.
	 * 
	 * @return 실시간 공지 서비스 일련번호
	 */
	public int getSn() {
		return sn;
	}
	/**
	 * 실시간 공지 서비스 일련번호를 할당한다. 
	 * 
	 * @param 	sn 	실시간 공지 서비스 일련번호
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}
	
	/**
	 * 실시간 공지 회원ID를 획득한다.
	 * 
	 * @return 	실시간 공지 회원ID
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * 실시간 공지 회원ID를 할당한다. 
	 * 
	 * @param mberId 	실시간 공지 회원ID
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	
	/**
	 * 실시간 공지 제목을 획득한다.
	 * 
	 * @return 실시간 공지 제목
	 */
	public String getNoticeSj() {
		return noticeSj;
	}
	/**
	 * 실시간 공지 제목을 할당한다. 
	 * 
	 * @param noticeSj 실시간 공지 제목
	 */
	public void setNoticeSj(String noticeSj) {
		this.noticeSj = noticeSj;
	}
	
	/**
	 * 실시간 공지 내용을 획득한다.
	 * 
	 * @return 실시간 공지 내용
	 */
	public String getNoticeCn() {
		return noticeCn;
	}
	/**
	 * 실시간 공지 내용을 할당한다.
	 * 
	 * @param noticeCn 실시간 공지 내용
	 */
	public void setNoticeCn(String noticeCn) {
		this.noticeCn = noticeCn;
	}
	
	/**
	 * 실시간 공지 서비스 최신구분 코드ID를 획득한다.
	 * 
	 * @return 최신구분 코드ID
	 */
	public String getRecentCodeId() {
		return recentCodeId;
	}
	/**
	 * 실시간 공지 서비스 최신구분 코드ID를 할당한다.
	 * 
	 * @param updtDt 최신구분 코드ID
	 */
	public void setRecentCodeId(String recentCodeId) {
		this.recentCodeId = recentCodeId;
	}

	/**
	 * 실시간 공지 서비스 최신구분 코드를 획득한다.
	 * 
	 * @return 최신구분 코드
	 */
	public String getRecentCode() {
		return recentCode;
	}
	/**
	 * 실시간 공지 서비스 최신구분 코드를 할당한다.
	 * 
	 * @param updtDt 최신구분 코드
	 */
	public void setRecentCode(String recentCode) {
		this.recentCode = recentCode;
	}
	
	/**
	 * 실시간 공지 시작일시를 획득한다.
	 * 
	 * @return 실시간 공지 시작일시
	 */
	public String getNoticeBgnDt() {
		return noticeBgnDt;
	}
	/**
	 * 실시간 공지 시작일시를 할당한다.
	 * 
	 * @param noticeBgnDt 실시간 공지 시작일시
	 */
	public void setNoticeBgnDt(String noticeBgnDt) {
		this.noticeBgnDt = noticeBgnDt;
	}
	
	/**
	 * 실시간 공지 종료일시를 획득한다.
	 * 
	 * @return 실시간 공지 종료일시
	 */
	public String getNoticeEndDt() {
		return noticeEndDt;
	}
	/**
	 * 실시간 공지 종료일시를 할당한다.
	 * 
	 * @param noticeEndDt 실시간 공지 종료일시
	 */
	public void setNoticeEndDt(String noticeEndDt) {
		this.noticeEndDt = noticeEndDt;
	}
	
	/**
	 * 실시간 공지 생성일시를 획득한다.
	 * 
	 * @return 실시간 공지 생성일시
	 */
	
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * 실시간 공지 생성일시를 할당한다.
	 * 
	 * @param creatDt 실시간 공지 생성일시
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	/**
	 * 실시간 공지 수정일시를 획득한다.
	 * 
	 * @return 실시간 공지 수정일시
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * 실시간 공지 수정일시를 할당한다.
	 * 
	 * @param updtDt 실시간 공지 수정일시
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	
	// helper
	/**
	 * 실시간 공지 최신구분명을 획득한다.
	 * 
	 * @return 실시간 공지 최신구분명
	 */
	public String getRecentNm() {
		return recentNm;
	}
	/**
	 * 실시간 공지 최신구분명을 할당한다.
	 * 
	 * @param recentNm 실시간 공지 최신구분명
	 */
	public void setRecentNm(String recentNm) {
		this.recentNm = recentNm;
	}
	
	/**
	 * 실시간 공지 서비스 목록 조회 갯수를 획득한다.
	 * 
	 * @return 목록 조회 갯수
	 */
	public int getFetchRow() {
		return fetchRow;
	}
	/**
	 * 실시간 공지 서비스 목록 조회 갯수를 할당한다.
	 * 
	 * @param fetchRow	목록 조회 갯수
	 */
	public void setFetchRow(int fetchRow) {
		this.fetchRow = fetchRow;
	}

	/**
	 * 실시간 공지 서비스 일련번호를 획득한다.
	 * 
	 * @return 실시간 공지 서비스 일련번호
	 */
	public String getSearchSn() {
		return searchSn;
	}
	/**
	 * 실시간 공지 서비스 일련번호를 할당한다.
	 * 
	 * @param searchSn	실시간 공지 서비스 일련번호
	 */
	public void setSsearchSn(String searchSn) {
		this.searchSn = searchSn;
	}
	
}
