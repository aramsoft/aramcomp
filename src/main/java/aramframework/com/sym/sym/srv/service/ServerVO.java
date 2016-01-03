package aramframework.com.sym.sym.srv.service;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 서버정보에 대한 Vo 클래스를 정의한다.
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

public class ServerVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 서버명 조회조건 */
	private String strServerNm;

	/** 서버 ID */
	private String serverId;
	
	/** 서버 명 */
	private String serverNm;
	
	/** 서버 종류 */
	private String serverKnd;
	
	/** 서버 종류명 */
	private String serverKndNm;
	
	/** 등록일자 */
	private String regstYmd;
	
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

}