package aramframework.com.sym.sym.nwk.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 네트워크에 대한 Vo 클래스를 정의한다.
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

public class NtwrkVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	// 추가 검색 정보
	/** 관리항목 */
	private String strManageIem;
	
	/** 사용자명 */
	private String strUserNm;

	// 도메인 정보
	/** 네트워크ID */
	private String ntwrkId;
	
	/** 네트워크IP */
	private String ntwrkIp;
	
	/** 게이트웨이 */
	private String gtwy;
	
	/** SUBNET */
	private String subnet;
	
	/** 도메인이름서버 */
	private String domnServer;
	
	/** 관리항목 */
	private String manageIem;
	
	/** 사용자명 */
	private String userNm;
	
	/** 사용여부 */
	private String useAt;
	
	/** 등록일자 */
	private String regstYmd;
	
	/**
	 * @return the strManageIem
	 */
	public String getStrManageIem() {
		return strManageIem;
	}
	/**
	 * @param strManageIem
	 *            the strManageIem to set
	 */
	public void setStrManageIem(String strManageIem) {
		this.strManageIem = strManageIem;
	}

	/**
	 * @return the strUserNm
	 */
	public String getStrUserNm() {
		return strUserNm;
	}
	/**
	 * @param strUserNm
	 *            the strUserNm to set
	 */
	public void setStrUserNm(String strUserNm) {
		this.strUserNm = strUserNm;
	}

	/**
	 * @return the ntwrkId
	 */
	public String getNtwrkId() {
		return ntwrkId;
	}
	/**
	 * @param ntwrkId
	 *            the ntwrkId to set
	 */
	public void setNtwrkId(String ntwrkId) {
		this.ntwrkId = ntwrkId;
	}

	/**
	 * @return the ntwrkIp
	 */
	public String getNtwrkIp() {
		return ntwrkIp;
	}
	/**
	 * @param ntwrkIp
	 *            the ntwrkIp to set
	 */
	public void setNtwrkIp(String ntwrkIp) {
		this.ntwrkIp = ntwrkIp;
	}

	/**
	 * @return the gtwy
	 */
	public String getGtwy() {
		return gtwy;
	}
	/**
	 * @param gtwy
	 *            the gtwy to set
	 */
	public void setGtwy(String gtwy) {
		this.gtwy = gtwy;
	}

	/**
	 * @return the subnet
	 */
	public String getSubnet() {
		return subnet;
	}
	/**
	 * @param subnet
	 *            the subnet to set
	 */
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	/**
	 * @return the domnServer
	 */
	public String getDomnServer() {
		return domnServer;
	}
	/**
	 * @param domnServer
	 *            the domnServer to set
	 */
	public void setDomnServer(String domnServer) {
		this.domnServer = domnServer;
	}

	/**
	 * @return the manageIem
	 */
	public String getManageIem() {
		return manageIem;
	}
	/**
	 * @param manageIem
	 *            the manageIem to set
	 */
	public void setManageIem(String manageIem) {
		this.manageIem = manageIem;
	}

	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm
	 *            the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * @param useAt
	 *            the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
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
