package aramframework.com.sym.sym.srv.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 서버장비관계에 대한 Vo 클래스를 정의한다.
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

public class ServerEqpmnRelateVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버ID 조회조건
	 */
	private String strServerId;

	/**
	 * 서버명 조회조건
	 */
	private String strServerNm;

	/**
	 * 서버ID
	 */
	private String serverId;
	/**
	 * 서버장비ID
	 */
	private String serverEqpmnId;
	/**
	 * 서버장비명
	 */
	private String serverEqpmnNm;
	/**
	 * 서버장비IP
	 */
	private String serverEqpmnIp;
	/**
	 * 서버장비관리자
	 */
	private String serverEqpmnMngrNm;
	/**
	 * 등록여부
	 */
	private String regYn;

	/**
	 * @return the strServerId
	 */
	public String getStrServerId() {
		return strServerId;
	}

	/**
	 * @param strServerId
	 *            the strServerId to set
	 */
	public void setStrServerId(String strServerId) {
		this.strServerId = strServerId;
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
	 * @return the regYn
	 */
	public String getRegYn() {
		return regYn;
	}

	/**
	 * @param regYn
	 *            the regYn to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

}