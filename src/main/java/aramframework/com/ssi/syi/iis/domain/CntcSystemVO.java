package aramframework.com.ssi.syi.iis.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 연계기관 VO 클래스
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

public class CntcSystemVO extends SearchVO {

	// domain
	/** 기관ID */
	private String insttId = "";

	/** 시스템ID */
	private String sysId = "";

	/** 시스템명 */
	private String sysNm = "";

	/** 시스템IP */
	private String sysIp = "";

	// domain
	/**
	 * insttId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getInsttId() {
		return insttId;
	}
	/**
	 * insttId attribute 값을 설정한다.
	 * 
	 * @param insttId
	 *            String
	 */
	public void setInsttId(String insttId) {
		this.insttId = insttId;
	}

	/**
	 * sysId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * sysId attribute 값을 설정한다.
	 * 
	 * @param sysId
	 *            String
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * sysNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSysNm() {
		return sysNm;
	}
	/**
	 * sysNm attribute 값을 설정한다.
	 * 
	 * @param sysNm
	 *            String
	 */
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	/**
	 * sysIp attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSysIp() {
		return sysIp;
	}
	/**
	 * sysIp attribute 값을 설정한다.
	 * 
	 * @param sysIp
	 *            String
	 */
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}

}
