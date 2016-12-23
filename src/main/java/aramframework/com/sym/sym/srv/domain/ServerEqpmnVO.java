package aramframework.com.sym.sym.srv.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 서버장비에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ServerEqpmnVO extends BaseVO {

	// domain
	/** 서버 장비 ID */
	private String serverEqpmnId;
	
	/** 서버 장비 명 */
	private String serverEqpmnNm;
	
	/** 서버 장비 IP */
	private String serverEqpmnIp;
	
	/** 서버 장비 관리자 명 */
	private String serverEqpmnMngrNm;
	
	/** 관리자 이메일 주소 */
	private String mngrEmailAddr;
	
	/** 운영체제 정보 */
	private String opersysmInfo;
	
	/** CPU 정보 */
	private String cpuInfo;
	
	/** 메모리 정보 */
	private String moryInfo;
	
	/** 하드디스크 정보 */
	private String hdDisk;
	
	/** 기타 정보 */
	private String etcInfo;
	
	/** 등록일자 */
	private String regstYmd;
	
	// helper
	/** 서버장비명 조회조건 */
	private String strServerEqpmnNm;

	// domain
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
	 * @return the serverEqpmnNm
	 */
	public String getServerEqpmnNm() {
		return serverEqpmnNm;
	}
	/**
	 * @param serverEqpmnNm
	 *            the serverEqpmnNm to set
	 */
	public void setServerEqpmnNm(String serverEqpmnNm) {
		this.serverEqpmnNm = serverEqpmnNm;
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
	 * @return the serverEqpmnMngrNm
	 */
	public String getServerEqpmnMngrNm() {
		return serverEqpmnMngrNm;
	}
	/**
	 * @param serverEqpmnMngrNm
	 *            the serverEqpmnMngrNm to set
	 */
	public void setServerEqpmnMngrNm(String serverEqpmnMngrNm) {
		this.serverEqpmnMngrNm = serverEqpmnMngrNm;
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

	/**
	 * @return the opersysmInfo
	 */
	public String getOpersysmInfo() {
		return opersysmInfo;
	}
	/**
	 * @param opersysmInfo
	 *            the opersysmInfo to set
	 */
	public void setOpersysmInfo(String opersysmInfo) {
		this.opersysmInfo = opersysmInfo;
	}

	/**
	 * @return the cpuInfo
	 */
	public String getCpuInfo() {
		return cpuInfo;
	}
	/**
	 * @param cpuInfo
	 *            the cpuInfo to set
	 */
	public void setCpuInfo(String cpuInfo) {
		this.cpuInfo = cpuInfo;
	}

	/**
	 * @return the moryInfo
	 */
	public String getMoryInfo() {
		return moryInfo;
	}
	/**
	 * @param moryInfo
	 *            the moryInfo to set
	 */
	public void setMoryInfo(String moryInfo) {
		this.moryInfo = moryInfo;
	}

	/**
	 * @return the hdDisk
	 */
	public String getHdDisk() {
		return hdDisk;
	}
	/**
	 * @param hdDisk
	 *            the hdDisk to set
	 */
	public void setHdDisk(String hdDisk) {
		this.hdDisk = hdDisk;
	}

	/**
	 * @return the etcInfo
	 */
	public String getEtcInfo() {
		return etcInfo;
	}
	/**
	 * @param etcInfo
	 *            the etcInfo to set
	 */
	public void setEtcInfo(String etcInfo) {
		this.etcInfo = etcInfo;
	}

	/**
	 * @return the regstYmd
	 */
	public String getRegstYmd() {
		return regstYmd;
	}
	/**
	 * @param regstYmd
	 *            the regstYmd to set
	 */
	public void setRegstYmd(String regstYmd) {
		this.regstYmd = regstYmd;
	}

	// helper
	/**
	 * @return the strServerEqpmnNm
	 */
	public String getStrServerEqpmnNm() {
		return strServerEqpmnNm;
	}
	/**
	 * @param strServerEqpmnNm
	 *            the strServerEqpmnNm to set
	 */
	public void setStrServerEqpmnNm(String strServerEqpmnNm) {
		this.strServerEqpmnNm = strServerEqpmnNm;
	}

}