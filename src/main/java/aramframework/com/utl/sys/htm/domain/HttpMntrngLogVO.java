package aramframework.com.utl.sys.htm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - HTTP서비스 모니터링 로그에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - HTTP서비스 모니터링의- 목록 항목, 조회조건 등을 관리한다.
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

public class HttpMntrngLogVO extends SearchVO {

	// domain
	/** 로그ID */
	private String logId;
	
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
	
	/** 로그정보 */
	private String logInfo;
	
	/** 관리자명 */
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	// helper
	/** 시작일자 조회조건 */
	private String searchBgnDe = "";

	/** 시작시간 조회조건 */
	private String searchBgnHour = "";

	/** 시작일시 조회조건 */
	private String searchBgnDt = "";

	/** 종료일자 조회조건 */
	private String searchEndDe = "";

	/** 종료시간 조회조건 */
	private String searchEndHour = "";

	/** 종료일시 조회조건 */
	private String searchEndDt = "";

	// domain
	/**
	 * @return the logId
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * @param logId
	 *            the logId to set
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

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
	 * @return the searchBgnDe
	 */
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	/**
	 * @param searchBgnDe
	 *            the searchBgnDe to set
	 */
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	/**
	 * @return the searchBgnHour
	 */
	public String getSearchBgnHour() {
		return searchBgnHour;
	}
	/**
	 * @param searchBgnHour
	 *            the searchBgnHour to set
	 */
	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	/**
	 * @return the searchBgnDt
	 */
	public String getSearchBgnDt() {
		return searchBgnDt;
	}
	/**
	 * @param searchBgnDt
	 *            the searchBgnDt to set
	 */
	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	/**
	 * @return the searchEndDe
	 */
	public String getSearchEndDe() {
		return searchEndDe;
	}
	/**
	 * @param searchEndDe
	 *            the searchEndDe to set
	 */
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	/**
	 * @return the searchEndHour
	 */
	public String getSearchEndHour() {
		return searchEndHour;
	}
	/**
	 * @param searchEndHour
	 *            the searchEndHour to set
	 */
	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	/**
	 * @return the searchEndDt
	 */
	public String getSearchEndDt() {
		return searchEndDt;
	}
	/**
	 * @param searchEndDt
	 *            the searchEndDt to set
	 */
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

}
