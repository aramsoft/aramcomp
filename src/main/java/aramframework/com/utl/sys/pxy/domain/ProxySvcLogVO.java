package aramframework.com.utl.sys.pxy.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 프록시로그정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용 - 프록시로그정보의 프록시 ID, 로그 ID, 클라이언트 IP, 클라이언트 포트, 접속 시간 등의 항목을 관리한다.
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

public class ProxySvcLogVO extends BaseVO {

	// domain
	/** 로그 ID */
	private String logId;
	
	/** 프록시 ID */
	private String proxyId;
	
	/** 클라이언트 IP */
	private String clntIp;
	
	/** 클라이언트 포트 */
	private String clntPort;
	
	/** 접속시간 */
	private String conectTime;
	
	// helper
	/** 프록시 명 */
	private String proxyNm;
	
	/** 프록시서비스접속 시작일자 조회조건 */
	public String strStartDate;
	
	/** 프록시서비스접속 종료일자 조회조건 */
	public String strEndDate;

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
	 * @return the proxyId
	 */
	public String getProxyId() {
		return proxyId;
	}
	/**
	 * @param proxyId
	 *            the proxyId to set
	 */
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

	/**
	 * @return the clntIp
	 */
	public String getClntIp() {
		return clntIp;
	}
	/**
	 * @param clntIp
	 *            the clntIp to set
	 */
	public void setClntIp(String clntIp) {
		this.clntIp = clntIp;
	}

	/**
	 * @return the clntPort
	 */
	public String getClntPort() {
		return clntPort;
	}
	/**
	 * @param clntPort
	 *            the clntPort to set
	 */
	public void setClntPort(String clntPort) {
		this.clntPort = clntPort;
	}

	/**
	 * @return the conectTime
	 */
	public String getConectTime() {
		return conectTime;
	}
	/**
	 * @param conectTime
	 *            the conectTime to set
	 */
	public void setConectTime(String conectTime) {
		this.conectTime = conectTime;
	}

	// helper
	/**
	 * @return the proxyNm
	 */
	public String getProxyNm() {
		return proxyNm;
	}
	/**
	 * @param proxyNm
	 *            the proxyNm to set
	 */
	public void setProxyNm(String proxyNm) {
		this.proxyNm = proxyNm;
	}

	/**
	 * @return the strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}
	/**
	 * @param strStartDate
	 *            the strStartDate to set
	 */
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * @return the strEndDate
	 */
	public String getStrEndDate() {
		return strEndDate;
	}
	/**
	 * @param strEndDate
	 *            the strEndDate to set
	 */
	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

}