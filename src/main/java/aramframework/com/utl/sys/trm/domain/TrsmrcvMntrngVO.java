package aramframework.com.utl.sys.trm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 송수신모니터링관리에 대한 model 클래스
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

public class TrsmrcvMntrngVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	
	/** 연계ID */
	private String cntcId;
	
	/** 테스트클래스명 */
	private String testClassNm;
	
	/** 관리자명 */
	private String mngrNm;
	
	/** 관리자이메일주소 */
	private String mngrEmailAddr;
	
	/** 모니터링상태 */
	private String mntrngSttus;
	
	/** 생성일시 */
	private String creatDt;

	/** 연계명 */
	private String cntcNm;
	
	/** 제공기관명 */
	private String provdInsttNm;
	
	/** 제공시스템명 */
	private String provdSysNm;
	
	/** 제공서비스명 */
	private String provdSvcNm;
	
	/** 요청기관명 */
	private String requstInsttNm;
	
	/** 요청시스템명 */
	private String requstSysNm;
	
	/** 모니터링상태명 */
	private String mntrngSttusNm;

	/**
	 * @return the cntcId
	 */
	public String getCntcId() {
		return cntcId;
	}
	/**
	 * @param cntcId
	 *            the cntcId to set
	 */
	public void setCntcId(String cntcId) {
		this.cntcId = cntcId;
	}

	/**
	 * @return the testClassNm
	 */
	public String getTestClassNm() {
		return testClassNm;
	}
	/**
	 * @param testClassNm
	 *            the testClassNm to set
	 */
	public void setTestClassNm(String testClassNm) {
		this.testClassNm = testClassNm;
	}

	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @param mngrNm
	 *            the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
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
	 * @return the mntrngSttus
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}
	/**
	 * @param mntrngSttus
	 *            the mntrngSttus to set
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	/**
	 * @return the cntcNm
	 */
	public String getCntcNm() {
		return cntcNm;
	}
	/**
	 * @param cntcNm
	 *            the cntcNm to set
	 */
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}

	/**
	 * @return the provdInsttNm
	 */
	public String getProvdInsttNm() {
		return provdInsttNm;
	}
	/**
	 * @param provdInsttNm
	 *            the provdInsttNm to set
	 */
	public void setProvdInsttNm(String provdInsttNm) {
		this.provdInsttNm = provdInsttNm;
	}

	/**
	 * @return the provdSysNm
	 */
	public String getProvdSysNm() {
		return provdSysNm;
	}
	/**
	 * @param provdSysNm
	 *            the provdSysNm to set
	 */
	public void setProvdSysNm(String provdSysNm) {
		this.provdSysNm = provdSysNm;
	}

	/**
	 * @return the provdSvcNm
	 */
	public String getProvdSvcNm() {
		return provdSvcNm;
	}
	/**
	 * @param provdSvcNm
	 *            the provdSvcNm to set
	 */
	public void setProvdSvcNm(String provdSvcNm) {
		this.provdSvcNm = provdSvcNm;
	}

	/**
	 * @return the requstInsttNm
	 */
	public String getRequstInsttNm() {
		return requstInsttNm;
	}
	/**
	 * @param requstInsttNm
	 *            the requstInsttNm to set
	 */
	public void setRequstInsttNm(String requstInsttNm) {
		this.requstInsttNm = requstInsttNm;
	}

	/**
	 * @return the requstSysNm
	 */
	public String getRequstSysNm() {
		return requstSysNm;
	}
	/**
	 * @param requstSysNm
	 *            the requstSysNm to set
	 */
	public void setRequstSysNm(String requstSysNm) {
		this.requstSysNm = requstSysNm;
	}

	/**
	 * @return the mntrngSttusNm
	 */
	public String getMntrngSttusNm() {
		return mntrngSttusNm;
	}
	/**
	 * @param mntrngSttusNm
	 *            the mntrngSttusNm to set
	 */
	public void setMntrngSttusNm(String mntrngSttusNm) {
		this.mntrngSttusNm = mntrngSttusNm;
	}

	/**
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * @param creatDt
	 *            the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

}