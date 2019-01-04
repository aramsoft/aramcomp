package aramframework.com.cmm.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 조회 base VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BaseVO extends SearchVO {

	// search
	/** 검색 객체 */
	SearchVO searchVO = null;
	
	// rest 관련
	// path id for rest
	protected String pathId = "";
	
	// DB 공통 field
	/** 최초등록자 아이디 */
	private String frstRegisterId = "";

	/** 최초 등록자명 */
	private String frstRegisterNm = "";

	/** 최초등록시점 */
	private Date frstRegisterPnttm = null;

	/** 최종수정자 아이디 */
	private String lastUpdusrId = "";

	/** 최종 수정자명 */
	private String lastUpdusrNm = "";

	/** 최종수정시점 */
	private Date lastUpdusrPnttm = null;

	// common
	/**
	 * pathId attribute 값을 리턴한다.
	 * 
	 * @return 	String
	 */
	public String getPathId() {
		return pathId;
	}
	/**
	 * pathId attribute 값을 설정한다.
	 * 
	 * @param 	pathId	String
	 */
/*
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
*/
	/**
	 * frstRegisterId attribute를 리턴한다.
	 * 
	 * @return 	the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * 
	 * @param frstRegisterId	the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegisterNm attribute를 리턴한다.
	 * 
	 * @return 	the frstRegisterNm
	 */
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}
	/**
	 * frstRegisterNm attribute 값을 설정한다.
	 * 
	 * @param 	frstRegisterNm	the frstRegisterNm to set
	 */
	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	/**
	 * frstRegisterPnttm attribute를 리턴한다.
	 * 
	 * @return 	the frstRegisterPnttm
	 */
	public Date getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * 
	 * @param 	frstRegisterPnttm	the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(Date frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * lastUpdusrId attribute를 리턴한다.
	 * 
	 * @return 	the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * 
	 * @param 	lastUpdusrId	the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdusrNm attribute를 리턴한다.
	 * 
	 * @return 	the lastUpdusrNm
	 */
	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}
	/**
	 * lastUpdusrNm attribute 값을 설정한다.
	 * 
	 * @param 	lastUpdusrNm	the lastUpdusrNm to set
	 */
	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}

	/**
	 * lastUpdusrPnttm attribute를 리턴한다.
	 * 
	 * @return 	the lastUpdusrPnttm
	 */
	public Date getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * 
	 * @param 	lastUpdusrPnttm		the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(Date lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * toString 메소드를 대치한다.
	 * 
	 * @return 	String 
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
