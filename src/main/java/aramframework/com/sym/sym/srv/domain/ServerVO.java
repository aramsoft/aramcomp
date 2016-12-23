package aramframework.com.sym.sym.srv.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 서버정보에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ServerVO extends BaseVO {

	// domain
	/** 서버 ID */
	private String serverId;
	
	/** 서버 명 */
	private String serverNm;
	
	/** 서버 종류 */
	private String serverKnd;
	
	/** 등록일자 */
	private String regstYmd;
	
	// helper
	/** 서버명 조회조건 */
	private String strServerNm;

	/** 서버 종류명 */
	private String serverKndNm;
	
	// domain
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
	 * @return the serverKnd
	 */
	public String getServerKnd() {
		return serverKnd;
	}
	/**
	 * @param serverKnd
	 *            the serverKnd to set
	 */
	public void setServerKnd(String serverKnd) {
		this.serverKnd = serverKnd;
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
	 * @return the serverKndNm
	 */
	public String getServerKndNm() {
		return serverKndNm;
	}
	/**
	 * @param serverKndNm
	 *            the serverKndNm to set
	 */
	public void setServerKndNm(String serverKndNm) {
		this.serverKndNm = serverKndNm;
	}

}