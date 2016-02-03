package aramframework.com.utl.sys.prm.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - PROCESS모니터링에 대한 Vo 클래스를 정의한다.
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

public class ProcessMonVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	
	/** 프로세스 명 */
	private String processNm;
	
	/** 프로세스아이디 */	
	private String processId;
	
	/** 로그ID */
	private String logId;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 프로세스 상태 */
	private String procsSttus;
	
	/** 생성시간 */
	private String creatDt;
	
	/** 관리자 명 */
	private String mngrNm;
	
	/** 관리자 이메일 주소 */
	private String mngrEmailAddr;
	
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

}