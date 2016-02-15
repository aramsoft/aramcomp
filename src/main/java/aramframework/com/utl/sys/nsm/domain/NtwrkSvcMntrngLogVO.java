package aramframework.com.utl.sys.nsm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 네트워크서비스 모니터링 로그에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링 로그의 목록 항목, 조회조건 등을 관리한다.
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

public class NtwrkSvcMntrngLogVO extends BaseVO {

	// domain
	/** 로그ID */
	private String logId;
	
	/** 시스템IP */
	private String sysIp;
	
	/** 시스템포트 */
	private String sysPort;
	
	/** 시스템명 */
	private String sysNm;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 생성일시 */
	private String creatDt;
	
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
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getSysIp() {
		return sysIp;
	}
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}

	public String getSysPort() {
		return sysPort;
	}
	public void setSysPort(String sysPort) {
		this.sysPort = sysPort;
	}

	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}
	
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	// helper
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchBgnHour() {
		return searchBgnHour;
	}
	public void setSearchBgnHour(String searchBgnHour) {
		this.searchBgnHour = searchBgnHour;
	}

	public String getSearchBgnDt() {
		return searchBgnDt;
	}
	public void setSearchBgnDt(String searchBgnDt) {
		this.searchBgnDt = searchBgnDt;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchEndHour() {
		return searchEndHour;
	}
	public void setSearchEndHour(String searchEndHour) {
		this.searchEndHour = searchEndHour;
	}

	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}

}