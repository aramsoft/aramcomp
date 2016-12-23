package aramframework.com.ssi.syi.iis.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 연계기관 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CntcServiceVO extends BaseVO  {

	// domain
	/** 기관ID */
	private String insttId = "";

	/** 시스템ID */
	private String sysId = "";

	/** 서비스ID */
	private String svcId = "";

	/** 서비스명 */
	private String svcNm = "";

	/** 요청메시지ID */
	private String requestMessageId = "";

	/** 응답메시지ID */
	private String rspnsMessageId = "";

	// domain
	/**
	 * insttId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getInsttId() {
		return insttId;
	}
	/**
	 * insttId attribute 값을 설정한다.
	 * 
	 * @param insttId
	 *            String
	 */
	public void setInsttId(String insttId) {
		this.insttId = insttId;
	}

	/**
	 * sysId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * sysId attribute 값을 설정한다.
	 * 
	 * @param sysId
	 *            String
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * svcId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSvcId() {
		return svcId;
	}
	/**
	 * svcId attribute 값을 설정한다.
	 * 
	 * @param svcId
	 *            String
	 */
	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}

	/**
	 * svcNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSvcNm() {
		return svcNm;
	}
	/**
	 * svcNm attribute 값을 설정한다.
	 * 
	 * @param svcNm
	 *            String
	 */
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}

	/**
	 * requestMessageId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRequestMessageId() {
		return requestMessageId;
	}
	/**
	 * requestMessageId attribute 값을 설정한다.
	 * 
	 * @param requestMessageId
	 *            String
	 */
	public void setRequestMessageId(String requestMessageId) {
		this.requestMessageId = requestMessageId;
	}

	/**
	 * rspnsMessageId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRspnsMessageId() {
		return rspnsMessageId;
	}
	/**
	 * rspnsMessageId attribute 값을 설정한다.
	 * 
	 * @param rspnsMessageId
	 *            String
	 */
	public void setRspnsMessageId(String rspnsMessageId) {
		this.rspnsMessageId = rspnsMessageId;
	}

}
