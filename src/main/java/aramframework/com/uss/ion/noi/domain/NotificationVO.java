package aramframework.com.uss.ion.noi.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 정보알림이 서비스를 위한 VO 클래스
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

public class NotificationVO extends SearchVO {

	// domain
	/** 알림 번호 */
	private String ntfcNo = "";

	/** 알림 제목 */
	private String ntfcSj = "";

	/** 알림 내용 */
	private String ntfcCn = "";

	/** 알림 시간 */
	private String ntfcTime = "";

	/** 사전 알림 간격 문자열 */
	private String bhNtfcIntrvlString = "";

	// helper
	/** 알림 시간 */
	private String ntfcDate = "";

	/** 사전 알림 간격 */
	private String[] bhNtfcIntrvl = new String[0];

	/** 유일 아이디 */
	private String uniqId = "";

	/** 알림 시간 */
	private String ntfcHH = "";

	/** 알림 시간 */
	private String ntfcMM = "";

	/** 정보알림이 표시를 위한 시작일 및 시작시간 */
	private String startDateTime = "";

	/** 정보알림이 표시를 위한 종료일 및 종료시간 */
	private String endDateTime = "";

	// domain
	/**
	 * ntfcNo attribute를 리턴한다.
	 * 
	 * @return the ntfcNo
	 */
	public String getNtfcNo() {
		return ntfcNo;
	}
	/**
	 * ntfcNo attribute 값을 설정한다.
	 * 
	 * @param ntfcNo
	 *            the ntfcNo to set
	 */
	public void setNtfcNo(String ntfcNo) {
		this.ntfcNo = ntfcNo;
	}

	/**
	 * ntfcSj attribute를 리턴한다.
	 * 
	 * @return the ntfcSj
	 */
	public String getNtfcSj() {
		return ntfcSj;
	}
	/**
	 * ntfcSj attribute 값을 설정한다.
	 * 
	 * @param ntfcSj
	 *            the ntfcSj to set
	 */
	public void setNtfcSj(String ntfcSj) {
		this.ntfcSj = ntfcSj;
	}

	/**
	 * ntfcCn attribute를 리턴한다.
	 * 
	 * @return the ntfcCn
	 */
	public String getNtfcCn() {
		return ntfcCn;
	}
	/**
	 * ntfcCn attribute 값을 설정한다.
	 * 
	 * @param ntfcCn
	 *            the ntfcCn to set
	 */
	public void setNtfcCn(String ntfcCn) {
		this.ntfcCn = ntfcCn;
	}

	/**
	 * ntfcTime attribute를 리턴한다.
	 * 
	 * @return the ntfcTime
	 */
	public String getNtfcTime() {
		return ntfcTime;
	}
	/**
	 * ntfcTime attribute 값을 설정한다.
	 * 
	 * @param ntfcTime
	 *            the ntfcTime to set
	 */
	public void setNtfcTime(String ntfcTime) {
		this.ntfcTime = ntfcTime;
	}

	/**
	 * bhNtfcIntrvlString attribute를 리턴한다.
	 * 
	 * @return the bhNtfcIntrvlString
	 */
	public String getBhNtfcIntrvlString() {
		return bhNtfcIntrvlString;
	}
	/**
	 * bhNtfcIntrvlString attribute 값을 설정한다.
	 * 
	 * @param bhNtfcIntrvlString
	 *            the bhNtfcIntrvlString to set
	 */
	public void setBhNtfcIntrvlString(String bhNtfcIntrvlString) {
		this.bhNtfcIntrvlString = bhNtfcIntrvlString;
	}

	// helper
	/**
	 * bhNtfcIntrvl attribute를 리턴한다.
	 * 
	 * @return the bhNtfcIntrvl
	 */
	// public String[] getBhNtfcIntrvl() {
	// 		return bhNtfcIntrvl;
	// }
	// 2011.10.07 private 배열을 public 함수가 반환되지 않도록 함
	public String[] getBhNtfcIntrvl() {
		// 메소드를 private으로 하거나, 복제본을 반환하거나,
		// 수정을 제어하는 public메소드를 별도로 만든다.
		String[] ret = null;
		if (this.bhNtfcIntrvl != null) {
			ret = new String[bhNtfcIntrvl.length];
			for (int i = 0; i < bhNtfcIntrvl.length; i++) {
				ret[i] = this.bhNtfcIntrvl[i];
			}
		}
		return ret;
	}
	/**
	 * bhNtfcIntrvl attribute 값을 설정한다.
	 * 
	 * @param bhNtfcIntrvl
	 *            the bhNtfcIntrvl to set
	 */
	// public void setBhNtfcIntrvl(String[] bhNtfcIntrvl) {
	// 		this.bhNtfcIntrvl = bhNtfcIntrvl;
	// }
	// 2011.10.07 private 배열-유형 필드에 공용 데이터 할당되지 않도록 함
	public void setBhNtfcIntrvl(String[] bhNtfcIntrvl) {
		this.bhNtfcIntrvl = new String[bhNtfcIntrvl.length];
		for (int i = 0; i < bhNtfcIntrvl.length; ++i)
			this.bhNtfcIntrvl[i] = bhNtfcIntrvl[i];
	}

	/**
	 * uniqId attribute를 리턴한다.
	 * 
	 * @return the uniqId
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId
	 *            the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	/**
	 * ntfcDate attribute를 리턴한다.
	 * 
	 * @return the ntfcDate
	 */
	public String getNtfcDate() {
		return ntfcDate;
	}
	/**
	 * ntfcDate attribute 값을 설정한다.
	 * 
	 * @param ntfcDate
	 *            the ntfcDate to set
	 */
	public void setNtfcDate(String ntfcDate) {
		this.ntfcDate = ntfcDate;
	}

	/**
	 * ntfcHH attribute를 리턴한다.
	 * 
	 * @return the ntfcHH
	 */
	public String getNtfcHH() {
		return ntfcHH;
	}
	/**
	 * ntfcHH attribute 값을 설정한다.
	 * 
	 * @param ntfcHH
	 *            the ntfcHH to set
	 */
	public void setNtfcHH(String ntfcHH) {
		this.ntfcHH = ntfcHH;
	}

	/**
	 * ntfcMM attribute를 리턴한다.
	 * 
	 * @return the ntfcMM
	 */
	public String getNtfcMM() {
		return ntfcMM;
	}
	/**
	 * ntfcMM attribute 값을 설정한다.
	 * 
	 * @param ntfcMM
	 *            the ntfcMM to set
	 */
	public void setNtfcMM(String ntfcMM) {
		this.ntfcMM = ntfcMM;
	}

	/**
	 * startDateTime attribute를 리턴한다.
	 * 
	 * @return the startDateTime
	 */
	public String getStartDateTime() {
		return startDateTime;
	}
	/**
	 * startDateTime attribute 값을 설정한다.
	 * 
	 * @param startDateTime
	 *            the startDateTime to set
	 */
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * endDateTime attribute를 리턴한다.
	 * 
	 * @return the endDateTime
	 */
	public String getEndDateTime() {
		return endDateTime;
	}
	/**
	 * endDateTime attribute 값을 설정한다.
	 * 
	 * @param endDateTime
	 *            the endDateTime to set
	 */
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
