package aramframework.com.uss.ion.isg.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요
 * - 인터넷서비스안내에 대한 VO 클래스를 정의한다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class IntnetSvcGuidanceVO extends BaseVO {

	// domain
	/** 인터넷서비스ID */
	private String intnetSvcId;
	
	/** 인터넷서비스명 */
	private String intnetSvcNm;
	
	/** 인터넷서비스설명 */
	private String intnetSvcDc;
	
	/** 반영여부 */
	private String reflctAt;
	
	// helper
	/** 사용자 ID */
	private String userId;
	
	/** 등록일자 */
	private String regDate;

	// domain
	/**
	 * @return the intnetSvcId
	 */
	public String getIntnetSvcId() {
		return intnetSvcId;
	}
	/**
	 * @param intnetSvcId
	 *            the intnetSvcId to set
	 */
	public void setIntnetSvcId(String intnetSvcId) {
		this.intnetSvcId = intnetSvcId;
	}
	
	/**
	 * @return the intnetSvcNm
	 */
	public String getIntnetSvcNm() {
		return intnetSvcNm;
	}
	/**
	 * @param intnetSvcNm
	 *            the intnetSvcNm to set
	 */
	public void setIntnetSvcNm(String intnetSvcNm) {
		this.intnetSvcNm = intnetSvcNm;
	}
	
	/**
	 * @return the intnetSvcDc
	 */
	public String getIntnetSvcDc() {
		return intnetSvcDc;
	}
	/**
	 * @param intnetSvcDc
	 *            the intnetSvcDc to set
	 */
	public void setIntnetSvcDc(String intnetSvcDc) {
		this.intnetSvcDc = intnetSvcDc;
	}
	
	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt
	 *            the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}
	
	// helper
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
