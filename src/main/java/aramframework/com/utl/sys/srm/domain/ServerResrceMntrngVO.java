package aramframework.com.utl.sys.srm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 서버자원모니터링에 대한 model 클래스를 정의한다.
 * 
 * 상세내용 - 서버자원모니터링의 ID, 서버 명, 서버 IP, 서버자원종류, 현황정보 등의 항목을 관리한다.
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

public class ServerResrceMntrngVO extends SearchVO {
	
	// domain
	/** 로그ID */
	private String logId;
	
	/** 서버 ID */
	private String serverEqpmnId;
	
	/** 서버 ID */
	private String serverId;
	
	/** CPU 사용률 */
	private String cpuUseRt;
	
	/** 메모리 사용률 */
	private String moryUseRt;
	
	/** 서비스상태 */
	private String svcSttus;
	
	/** 로그정보 */
	private String logInfo;
	
	/** 생성일시 */
	private String creatDt;
	
	// helper
	/** 서버 명 */
	private String serverNm;
	
	/** 서버 IP */
	private String serverEqpmnIp;
	
	/** 서비스상태명 */
	private String svcSttusNm;
	
	/** 관리자이메일 */
	private String mngrEamilAddr;
	
	/** 서버자원모니터링 서버명 조회조건 */
	private String strServerNm;
	
	/** 시작일자 검색조건 */
	private String strStartDt;
	
	/** 종료일자 검색조건 */
	private String strEndDt;

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
	 * @return the serverEqpmnId
	 */
	public String getServerEqpmnId() {
		return serverEqpmnId;
	}
	/**
	 * @param serverEqpmnId
	 *            the serverEqpmnId to set
	 */
	public void setServerEqpmnId(String serverEqpmnId) {
		this.serverEqpmnId = serverEqpmnId;
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}
	/**
	 * @param serverId
	 *            the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * @return the cpuUseRt
	 */
	public String getCpuUseRt() {
		return cpuUseRt;
	}
	/**
	 * @param cpuUseRt
	 *            the cpuUseRt to set
	 */
	public void setCpuUseRt(String cpuUseRt) {
		this.cpuUseRt = cpuUseRt;
	}

	/**
	 * @return the moryUseRt
	 */
	public String getMoryUseRt() {
		return moryUseRt;
	}
	/**
	 * @param moryUseRt
	 *            the moryUseRt to set
	 */
	public void setMoryUseRt(String moryUseRt) {
		this.moryUseRt = moryUseRt;
	}

	/**
	 * @return the svcSttus
	 */
	public String getSvcSttus() {
		return svcSttus;
	}
	/**
	 * @param svcSttus
	 *            the svcSttus to set
	 */
	public void setSvcSttus(String svcSttus) {
		this.svcSttus = svcSttus;
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

	// helper
	/**
	 * @return the serverNm
	 */
	public String getServerNm() {
		return serverNm;
	}
	/**
	 * @param serverNm
	 *            the serverNm to set
	 */
	public void setServerNm(String serverNm) {
		this.serverNm = serverNm;
	}

	/**
	 * @return the serverEqpmnIp
	 */
	public String getServerEqpmnIp() {
		return serverEqpmnIp;
	}
	/**
	 * @param serverEqpmnIp
	 *            the serverEqpmnIp to set
	 */
	public void setServerEqpmnIp(String serverEqpmnIp) {
		this.serverEqpmnIp = serverEqpmnIp;
	}

	/**
	 * @return the svcSttusNm
	 */
	public String getSvcSttusNm() {
		return svcSttusNm;
	}
	/**
	 * @param svcSttusNm
	 *            the svcSttusNm to set
	 */
	public void setSvcSttusNm(String svcSttusNm) {
		this.svcSttusNm = svcSttusNm;
	}

	/**
	 * @return the mngrEamilAddr
	 */
	public String getMngrEamilAddr() {
		return mngrEamilAddr;
	}
	/**
	 * @param mngrEamilAddr
	 *            the mngrEamilAddr to set
	 */
	public void setMngrEamilAddr(String mngrEamilAddr) {
		this.mngrEamilAddr = mngrEamilAddr;
	}

	/**
	 * @return the strServerNm
	 */
	public String getStrServerNm() {
		return strServerNm;
	}
	/**
	 * @param strServerNm
	 *            the strServerNm to set
	 */
	public void setStrServerNm(String strServerNm) {
		this.strServerNm = strServerNm;
	}

	/**
	 * @return the strStartDt
	 */
	public String getStrStartDt() {
		return strStartDt;
	}
	/**
	 * @param strStartDt
	 *            the strStartDt to set
	 */
	public void setStrStartDt(String strStartDt) {
		this.strStartDt = strStartDt;
	}

	/**
	 * @return the strEndDt
	 */
	public String getStrEndDt() {
		return strEndDt;
	}
	/**
	 * @param strEndDt
	 *            the strEndDt to set
	 */
	public void setStrEndDt(String strEndDt) {
		this.strEndDt = strEndDt;
	}

}