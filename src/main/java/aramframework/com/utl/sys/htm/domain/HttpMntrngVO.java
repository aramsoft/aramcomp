package aramframework.com.utl.sys.htm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - HTTP서비스모니터링에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - HTTP서비스모니터링의 목록 항목, 조회조건 등을 관리한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class HttpMntrngVO extends BaseVO {

	// domain
	/** 시스템ID */
	private String sysId;
	
	/** 사이트URL */
	private String siteUrl;
	
	/** 웹서비스종류 */
	private String webKind;
	
	/** 웹서비스상태 */
	private String httpSttusCd;
	
	/** 생성일시 */
	private String creatDt;
	
	/** 관리자명	*/
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	// helper
	/** 로그정보 */
	private String logInfo;
	
	/** 웹서비스종류 */
	private String webKindNm;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	// domain
	/**
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * @param sysId
	 *            the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}
	/**
	 * @param siteUrl
	 *            the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * @return the webKind
	 */
	public String getWebKind() {
		return webKind;
	}
	/**
	 * @param webKind
	 *            the webKind to set
	 */
	public void setWebKind(String webKind) {
		this.webKind = webKind;
	}

	/**
	 * @return the httpSttusCd
	 */
	public String getHttpSttusCd() {
		return httpSttusCd;
	}
	/**
	 * @param httpSttusCd
	 *            the httpSttusCd to set
	 */
	public void setHttpSttusCd(String httpSttusCd) {
		this.httpSttusCd = httpSttusCd;
	}

	/**
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * @param creatDt
	 *            the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @param mngrNm
	 *            the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	/**
	 * @return the mngrEmailAddr
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	/**
	 * @param mngrEmailAddr
	 *            the mngrEmailAddr to set
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}
	
	// helper
	/**
	 * @return the logInfo
	 */
	public String getLogInfo() {
		return logInfo;
	}
	/**
	 * @param logInfo
	 *            the logInfo to set
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * @return the webKindNm
	 */
	public String getWebKindNm() {
		return webKindNm;
	}
	/**
	 * @param webKindNm
	 *            the webKindNm to set
	 */
	public void setWebKindNm(String webKindNm) {
		this.webKindNm = webKindNm;
	}

	/**
	 * @return the mntrngSttus
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	/**
	 * @param mntrngSttus
	 *            the mntrngSttus to set
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

}
