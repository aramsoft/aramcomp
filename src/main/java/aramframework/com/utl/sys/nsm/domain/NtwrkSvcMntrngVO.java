package aramframework.com.utl.sys.nsm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 네트워크서비스 모니터링대상에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - 네트워크서비스 모니터링대상의 목록 항목, 조회조건 등을 관리한다.
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

public class NtwrkSvcMntrngVO extends SearchVO {

	// domain
	/** 시스템IP */
	private String sysIp;
	
	/** 시스템포트 */
	private String sysPort;
	
	/** 시스템명 */
	private String sysNm;
	
	/** 관리자명 */
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	/** 생성일시 */
	private String creatDt;
	
	// helper
	/** 시스템IP 1 */
	private String sysIp1;
	
	/** 시스템IP 2 */
	private String sysIp2;
	
	/** 시스템IP 3 */
	private String sysIp3;
	
	/** 시스템IP 4 */
	private String sysIp4;
	
	/** 구시스템IP */
	private String oldSysIp;
	
	/** 구시스템포트 */
	private String oldSysPort;
	
	/** 로그정보 */
	private String logInfo;
	
	// domain
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

	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}

	public String getMntrngSttus() {
		return mntrngSttus;
	}
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	// helper
	public String getSysIp1() {
		return sysIp1;
	}
	public void setSysIp1(String sysIp1) {
		this.sysIp1 = sysIp1;
	}

	public String getSysIp2() {
		return sysIp2;
	}
	public void setSysIp2(String sysIp2) {
		this.sysIp2 = sysIp2;
	}

	public String getSysIp3() {
		return sysIp3;
	}
	public void setSysIp3(String sysIp3) {
		this.sysIp3 = sysIp3;
	}

	public String getSysIp4() {
		return sysIp4;
	}
	public void setSysIp4(String sysIp4) {
		this.sysIp4 = sysIp4;
	}

	public String getOldSysIp() {
		return oldSysIp;
	}
	public void setOldSysIp(String oldSysIp) {
		this.oldSysIp = oldSysIp;
	}

	public String getOldSysPort() {
		return oldSysPort;
	}
	public void setOldSysPort(String oldSysPort) {
		this.oldSysPort = oldSysPort;
	}

	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

}