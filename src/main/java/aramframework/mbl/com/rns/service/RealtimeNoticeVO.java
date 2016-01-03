package aramframework.mbl.com.rns.service;

import aramframework.com.cmm.SearchVO;

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

@SuppressWarnings("serial")
public class RealtimeNoticeVO extends SearchVO  {
	/**
	 * 실시간 공지 서비스 일련번호
	 */
	private int sn = 0;
	
	/**
	 * 일련번호
	 */	
	private String searchSn = "";
	
	/**
	 * 목록 조회 갯수
	 */
	private int fetchRow = 0;

	/**
	 * 회원ID
	 */
	private String mberId = "";
	
	/**
	 * 실시간 공지 제목
	 */
	private String noticeSj = "";
	
	/**
	 * 실시간 공지 내용 
	 */
	private String noticeCn = "";
	
	/**
	 * 최신구분명
	 */
	private String recentNm = "";
	
	/**
	 * 실시간 공지 시작일시
	 */
	private String noticeBgnDt = "";
	
	/**
	 * 실시간 공지 종료일시
	 */
	private String noticeEndDt = "";
	
	/**
	 * 실시간 공지 생성일시
	 */
	private String creatDt = "";
	
	/**
	 * 실시간 공지 수정일시
	 */
	private String updtDt = "";
	
	/**
	 * 최신구분 코드 ID
	 */
	private String recentCodeId = "";
	
	/**
	 * 최신구분 코드 
	 */
	private String recentCode = "";
	
	/**
	 * 실시간 공지 서비스 일련번호를 획득한다.
	 * 
	 * @return the sn
	 */
	public int getSn() {
		return sn;
	}
	/**
	 * 실시간 공지 서비스 일련번호를 할당한다. 
	 * 
	 * @param sn the sn to set
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}
	
	/**
	 * 실시간 공지 서비스 목록 조회 갯수를 획득한다.
	 * 
	 * @return int
	 */
	public int getFetchRow() {
		return fetchRow;
	}
	/**
	 * 실시간 공지 서비스 목록 조회 갯수를 할당한다.
	 * 
	 * @param fetchRow
	 */
	public void setFetchRow(int fetchRow) {
		this.fetchRow = fetchRow;
	}

	/**
	 * 실시간 공지 서비스 일련번호를 획득한다.
	 * 
	 * @return String 일련번호
	 */
	public String getSearchSn() {
		return searchSn;
	}
	/**
	 * 실시간 공지 서비스 일변로호를 할당한다.
	 * 
	 * @param searchSn
	 */
	public void setSsearchSn(String searchSn) {
		this.searchSn = searchSn;
	}
	/**
	 * 실시간 공지 회원ID를 획득한다.
	 * 
	 * @return the mberId
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * 실시간 공지 회원ID를 할당한다. 
	 * 
	 * @param mberId the mberId to set
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	
	/**
	 * 실시간 공지 제목을 획득한다.
	 * 
	 * @return the noticeSj
	 */
	public String getNoticeSj() {
		return noticeSj;
	}
	/**
	 * 실시간 공지 제목을 할당한다. 
	 * 
	 * @param noticeSj the noticeSj to set
	 */
	public void setNoticeSj(String noticeSj) {
		this.noticeSj = noticeSj;
	}
	
	/**
	 * 실시간 공지 내용을 획득한다.
	 * 
	 * @return the noticeCn
	 */
	public String getNoticeCn() {
		return noticeCn;
	}
	/**
	 * 실시간 공지 내용을 할당한다.
	 * 
	 * @param noticeCn the noticeCn to set
	 */
	public void setNoticeCn(String noticeCn) {
		this.noticeCn = noticeCn;
	}
	
	/**
	 * 실시간 공지 최신구분명을 획득한다.
	 * 
	 * @return the recentNm
	 */
	public String getRecentNm() {
		return recentNm;
	}
	/**
	 * 실시간 공지 최신구분명을 할당한다.
	 * 
	 * @param recentNm the recentNm to set
	 */
	public void setRecentNm(String recentNm) {
		this.recentNm = recentNm;
	}
	
	/**
	 * 실시간 공지 시작일시를 획득한다.
	 * 
	 * @return the noticeBgnDt
	 */
	public String getNoticeBgnDt() {
		return noticeBgnDt;
	}
	/**
	 * 실시간 공지 시작일시를 할당한다.
	 * 
	 * @param noticeBgnDt the noticeBgnDt to set
	 */
	public void setNoticeBgnDt(String noticeBgnDt) {
		this.noticeBgnDt = noticeBgnDt;
	}
	
	/**
	 * 실시간 공지 종료일시를 획득한다.
	 * 
	 * @return the noticeEndDt
	 */
	public String getNoticeEndDt() {
		return noticeEndDt;
	}
	/**
	 * 실시간 공지 종료일시를 할당한다.
	 * 
	 * @param noticeEndDt the noticeEndDt to set
	 */
	public void setNoticeEndDt(String noticeEndDt) {
		this.noticeEndDt = noticeEndDt;
	}
	
	/**
	 * 실시간 공지 생서일시를 획득한다.
	 * 
	 * @return the creatDt
	 */
	
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * 실시간 공지 생서일시를 할당한다.
	 * 
	 * @param creatDt the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	/**
	 * 실시간 공지 수정일시를 획득한다.
	 * 
	 * @return the updtDt
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * 실시간 공지 수정일시를 할당한다.
	 * 
	 * @param updtDt the updtDt to set
	 */
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	
	/**
	 * 실시간 공지 서비스 최신구분 코드ID를 획득한다.
	 * 
	 * @return the recentCodeId
	 */
	public String getRecentCodeId() {
		return recentCodeId;
	}
	/**
	 * 실시간 공지 서비스 최신구분 코드ID를 할당한다.
	 * 
	 * @param updtDt the updtDt to set
	 */
	public void setRecentCodeId(String recentCodeId) {
		this.recentCodeId = recentCodeId;
	}

	/**
	 * 실시간 공지 서비스 최신구분 코드를 획득한다.
	 * 
	 * @return the recentCode
	 */
	public String getRecentCode() {
		return recentCode;
	}
	/**
	 * 실시간 공지 서비스 최신구분 코드를 할당한다.
	 * 
	 * @param updtDt the updtDt to set
	 */
	public void setRecentCode(String recentCode) {
		this.recentCode = recentCode;
	}
	
}
