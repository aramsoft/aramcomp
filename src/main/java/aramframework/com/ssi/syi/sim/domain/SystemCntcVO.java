package aramframework.com.ssi.syi.sim.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 시스템연계 VO 클래스
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

public class SystemCntcVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 연계ID */
	private String cntcId = "";

	/** 연계명 */
	private String cntcNm = "";

	/** 연계유형코드 */
	private String cntcType = "";

	/** 제공기관ID */
	private String provdInsttId = "";

	/** 제공시스템ID */
	private String provdSysId = "";

	/** 제공서비스ID */
	private String provdSvcId = "";

	/** 요청기관ID */
	private String requstInsttId = "";

	/** 요청시스템ID */
	private String requstSysId = "";

	/** 승인여부 */
	private String confmAt = "";

	/** 사용여부 */
	private String useAt = "";

	/** 유효시작일자 */
	private String validBeginDe = "";

	/** 유효종료일자 */
	private String validEndDe = "";

	/**
	 * cntcId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCntcId() {
		return cntcId;
	}
	/**
	 * cntcId attribute 값을 설정한다.
	 * 
	 * @param cntcId
	 *            String
	 */
	public void setCntcId(String cntcId) {
		this.cntcId = cntcId;
	}

	/**
	 * cntcNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCntcNm() {
		return cntcNm;
	}
	/**
	 * cntcNm attribute 값을 설정한다.
	 * 
	 * @param cntcNm
	 *            String
	 */
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}

	/**
	 * cntcType attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCntcType() {
		return cntcType;
	}
	/**
	 * cntcType attribute 값을 설정한다.
	 * 
	 * @param cntcType
	 *            String
	 */
	public void setCntcType(String cntcType) {
		this.cntcType = cntcType;
	}

	/**
	 * provdInsttId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdInsttId() {
		return provdInsttId;
	}
	/**
	 * provdInsttId attribute 값을 설정한다.
	 * 
	 * @param provdInsttId
	 *            String
	 */
	public void setProvdInsttId(String provdInsttId) {
		this.provdInsttId = provdInsttId;
	}

	/**
	 * provdSysId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdSysId() {
		return provdSysId;
	}
	/**
	 * provdSysId attribute 값을 설정한다.
	 * 
	 * @param provdSysId
	 *            String
	 */
	public void setProvdSysId(String provdSysId) {
		this.provdSysId = provdSysId;
	}

	/**
	 * provdSvcId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdSvcId() {
		return provdSvcId;
	}
	/**
	 * provdSvcId attribute 값을 설정한다.
	 * 
	 * @param provdSvcId
	 *            String
	 */
	public void setProvdSvcId(String provdSvcId) {
		this.provdSvcId = provdSvcId;
	}

	/**
	 * requstInsttId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRequstInsttId() {
		return requstInsttId;
	}
	/**
	 * requstInsttId attribute 값을 설정한다.
	 * 
	 * @param requstInsttId
	 *            String
	 */
	public void setRequstInsttId(String requstInsttId) {
		this.requstInsttId = requstInsttId;
	}

	/**
	 * requstSysId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRequstSysId() {
		return requstSysId;
	}
	/**
	 * requstSysId attribute 값을 설정한다.
	 * 
	 * @param requstSysId
	 *            String
	 */
	public void setRequstSysId(String requstSysId) {
		this.requstSysId = requstSysId;
	}

	/**
	 * confmAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getConfmAt() {
		return confmAt;
	}
	/**
	 * confmAt attribute 값을 설정한다.
	 * 
	 * @param confmAt
	 *            String
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * validBeginDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getValidBeginDe() {
		return validBeginDe;
	}
	/**
	 * validBeginDe attribute 값을 설정한다.
	 * 
	 * @param validBeginDe
	 *            String
	 */
	public void setValidBeginDe(String validBeginDe) {
		this.validBeginDe = validBeginDe;
	}

	/**
	 * validEndDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getValidEndDe() {
		return validEndDe;
	}
	/**
	 * validEndDe attribute 값을 설정한다.
	 * 
	 * @param validEndDe
	 *            String
	 */
	public void setValidEndDe(String validEndDe) {
		this.validEndDe = validEndDe;
	}

}
