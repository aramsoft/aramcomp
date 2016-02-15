package aramframework.com.utl.sys.prm.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - PROCESS모니터링 로그에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - PROCESS모니터링의 목록 항목, 조회조건 등을 관리한다.
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

public class ProcessMonLogVO extends BaseVO {

	// domain
	/** 로그ID */
	private String logId;
	
	/** 프로세스아이디 */
	private String processId;
	
	/** 프로세스 명 */
	private String processNm;
	
	/** 프로세스 상태 */
	private String procsSttus;
	
	/** 생성시간 */
	private String creatDt;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 관리자 명 */
	private String mngrNm;
	
	/** 관리자 이메일 주소 */
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
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the processNm
	 */
	public String getProcessNm() {
		return processNm;
	}
	/**
	 * @param processNm
	 *            the processNm to set
	 */
	public void setProcessNm(String processNm) {
		this.processNm = processNm;
	}

	/**
	 * @return the procsSttus
	 */
	public String getProcsSttus() {
		return procsSttus;
	}
	/**
	 * @param procsSttus
	 *            the procsSttus to set
	 */
	public void setProcsSttus(String procsSttus) {
		this.procsSttus = procsSttus;
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
