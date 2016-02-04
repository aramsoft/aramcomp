package aramframework.com.utl.sys.pxy.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 프록시서비스정보에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용 - 프록시서비스정보의 목록 항목, 조회조건 등을 관리한다.
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

public class ProxySvcVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 프록시 명 조회조건 */
	private String strProxyNm;
	
	/** 이전 서비스 상태 */
	private String strPreSvcSttus;

	/** 프록시 ID */
	private String proxyId;
	
	/** 프록시 명 */
	private String proxyNm;
	
	/** 프록시 IP */
	private String proxyIp;
	
	/** 프록시 포트 */
	private String proxyPort;
	
	/** 서비스 명 */
	private String trgetSvcNm;
	
	/** 서비스 설명 */
	private String svcDc;
	
	/** 서비스 IP */
	private String svcIp;
	
	/** 서비스 포트 */
	private String svcPort;
	
	/** 서비스 상태 */
	private String svcSttus;
	
	/** 서비스 상태 */
	private String svcSttusNm;
	
	/**
	 * @return the strProxyNm
	 */
	public String getStrProxyNm() {
		return strProxyNm;
	}
	/**
	 * @param strProxyNm
	 *            the strProxyNm to set
	 */
	public void setStrProxyNm(String strProxyNm) {
		this.strProxyNm = strProxyNm;
	}

	/**
	 * @return the strPreSvcSttus
	 */
	public String getStrPreSvcSttus() {
		return strPreSvcSttus;
	}
	/**
	 * @param strPreSvcSttus
	 *            the strPreSvcSttus to set
	 */
	public void setStrPreSvcSttus(String strPreSvcSttus) {
		this.strPreSvcSttus = strPreSvcSttus;
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
	 * @return the proxyIp
	 */
	public String getProxyIp() {
		return proxyIp;
	}
	/**
	 * @param proxyIp
	 *            the proxyIp to set
	 */
	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	/**
	 * @return the proxyPort
	 */
	public String getProxyPort() {
		return proxyPort;
	}
	/**
	 * @param proxyPort
	 *            the proxyPort to set
	 */
	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * @return the trgetSvcNm
	 */
	public String getTrgetSvcNm() {
		return trgetSvcNm;
	}
	/**
	 * @param trgetSvcNm
	 *            the trgetSvcNm to set
	 */
	public void setTrgetSvcNm(String trgetSvcNm) {
		this.trgetSvcNm = trgetSvcNm;
	}

	/**
	 * @return the svcDc
	 */
	public String getSvcDc() {
		return svcDc;
	}
	/**
	 * @param svcDc
	 *            the svcDc to set
	 */
	public void setSvcDc(String svcDc) {
		this.svcDc = svcDc;
	}

	/**
	 * @return the svcIp
	 */
	public String getSvcIp() {
		return svcIp;
	}
	/**
	 * @param svcIp
	 *            the svcIp to set
	 */
	public void setSvcIp(String svcIp) {
		this.svcIp = svcIp;
	}

	/**
	 * @return the svcPort
	 */
	public String getSvcPort() {
		return svcPort;
	}
	/**
	 * @param svcPort
	 *            the svcPort to set
	 */
	public void setSvcPort(String svcPort) {
		this.svcPort = svcPort;
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

}