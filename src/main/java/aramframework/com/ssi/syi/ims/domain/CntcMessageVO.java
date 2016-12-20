package aramframework.com.ssi.syi.ims.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 연계메시지 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class CntcMessageVO extends BaseVO  {

	// domain
	/** 연계메시지ID */
	private String cntcMessageId = "";

	/** 연계메시지명 */
	private String cntcMessageNm = "";

	/** 상위연계메시지ID */
	private String upperCntcMessageId = "";

	/**
	 * cntcMessageId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCntcMessageId() {
		return cntcMessageId;
	}
	/**
	 * cntcMessageId attribute 값을 설정한다.
	 * 
	 * @param cntcMessageId
	 *            String
	 */
	public void setCntcMessageId(String cntcMessageId) {
		this.cntcMessageId = cntcMessageId;
	}

	/**
	 * cntcMessageNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCntcMessageNm() {
		return cntcMessageNm;
	}
	/**
	 * cntcMessageNm attribute 값을 설정한다.
	 * 
	 * @param cntcMessageNm
	 *            String
	 */
	public void setCntcMessageNm(String cntcMessageNm) {
		this.cntcMessageNm = cntcMessageNm;
	}

	/**
	 * upperCntcMessageId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUpperCntcMessageId() {
		return upperCntcMessageId;
	}
	/**
	 * upperCntcMessageId attribute 값을 설정한다.
	 * 
	 * @param upperCntcMessageId
	 *            String
	 */
	public void setUpperCntcMessageId(String upperCntcMessageId) {
		this.upperCntcMessageId = upperCntcMessageId;
	}

}
