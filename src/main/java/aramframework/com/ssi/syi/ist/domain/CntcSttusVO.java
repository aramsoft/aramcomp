package aramframework.com.ssi.syi.ist.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 연계현황 VO 클래스
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

public class CntcSttusVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 연계ID */
	private String cntcId = "";

	/** 연계명 */
	private String cntcNm = "";

	/** 연계유형 */
	private String cntcType = "";

	/** 총연계건수 */
	private int cntAll = 0;

	/** 성공연계수 */
	private int cntSuccess = 0;

	/** 실패연계수 */
	private int cntFail = 0;

	/** 제공기관ID */
	private String provdInsttId = "";

	/** 제공기관명 */
	private String provdInsttNm = "";

	/** 제공시스템ID */
	private String provdSysId = "";

	/** 제공시스템명 */
	private String provdSysNm = "";

	/** 제공서비스ID */
	private String provdSvcId = "";

	/** 제공서비스명 */
	private String provdSvcNm = "";

	/** 요청기관ID */
	private String requstInsttId = "";

	/** 요청기관명 */
	private String requstInsttNm = "";

	/** 요청시스템ID */
	private String requstSysId = "";

	/** 요청시스템명 */
	private String requstSysNm = "";

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
	 * cntAll attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getCntAll() {
		return cntAll;
	}
	/**
	 * cntAll attribute 값을 설정한다.
	 * 
	 * @param cntAll
	 *            int
	 */
	public void setCntAll(int cntAll) {
		this.cntAll = cntAll;
	}

	/**
	 * cntSuccess attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getCntSuccess() {
		return cntSuccess;
	}
	/**
	 * cntSuccess attribute 값을 설정한다.
	 * 
	 * @param cntSuccess
	 *            int
	 */
	public void setCntSuccess(int cntSuccess) {
		this.cntSuccess = cntSuccess;
	}

	/**
	 * cntFail attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getCntFail() {
		return cntFail;
	}
	/**
	 * cntFail attribute 값을 설정한다.
	 * 
	 * @param cntFail
	 *            int
	 */
	public void setCntFail(int cntFail) {
		this.cntFail = cntFail;
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
	 * provdInsttNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdInsttNm() {
		return provdInsttNm;
	}
	/**
	 * provdInsttNm attribute 값을 설정한다.
	 * 
	 * @param provdInsttNm
	 *            String
	 */
	public void setProvdInsttNm(String provdInsttNm) {
		this.provdInsttNm = provdInsttNm;
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
	 * provdSysNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdSysNm() {
		return provdSysNm;
	}
	/**
	 * provdSysNm attribute 값을 설정한다.
	 * 
	 * @param provdSysNm
	 *            String
	 */
	public void setProvdSysNm(String provdSysNm) {
		this.provdSysNm = provdSysNm;
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
	 * provdSvcNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProvdSvcNm() {
		return provdSvcNm;
	}
	/**
	 * provdSvcNm attribute 값을 설정한다.
	 * 
	 * @param provdSvcNm
	 *            String
	 */
	public void setProvdSvcNm(String provdSvcNm) {
		this.provdSvcNm = provdSvcNm;
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
	 * requstInsttNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRequstInsttNm() {
		return requstInsttNm;
	}
	/**
	 * requstInsttNm attribute 값을 설정한다.
	 * 
	 * @param requstInsttNm
	 *            String
	 */
	public void setRequstInsttNm(String requstInsttNm) {
		this.requstInsttNm = requstInsttNm;
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
	 * requstSysNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getRequstSysNm() {
		return requstSysNm;
	}
	/**
	 * requstSysNm attribute 값을 설정한다.
	 * 
	 * @param requstSysNm
	 *            String
	 */
	public void setRequstSysNm(String requstSysNm) {
		this.requstSysNm = requstSysNm;
	}

}
