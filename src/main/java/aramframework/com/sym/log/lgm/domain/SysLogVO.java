package aramframework.com.sym.log.lgm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.cmm.domain.BaseVO;

/**
 * 시스템 로그를 위한 VO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class SysLogVO extends BaseVO {

	// domain
	/** 요청아이디 */
	private String requstId = "";
	
	/** 업무구분코드 */
	private String jobSeCode = "";

	/** 기관코드 */
	private String insttCode = "";
	
	/** 발생일자 */
	private String occrrncDe = "";
	
	/** 요청아이피 */
	private String rqesterIp = "";
	
	/** 요청자아이디 */
	private String rqesterId = "";
	
	/** 대상메뉴명 */
	private String trgetMenuNm = "";
	
	/** 서비스명 */
	private String srvcNm = "";
	
	/** 메서드명 */
	private String methodNm = "";
	
	/** 처리구분코드 */
	private String processSeCode = "";
	
	/** 처리횟수 */
	private int processCo = 0;
	
	/** 처리시간 */
	private String processTime = "";
	
	/** 응답코드 */
	private String rspnsCode = "";
	
	/** 에러구분 */
	private String errorSe = "";
	
	/** 에러횟수 */
	private int errorCo = 0;
	
	/** 에러코드 */
	private String errorCode = "";
	
	// helper
	/** 에러코드 명 */
	private String errorCodeNm = "";
	
	/** 기관코드 명 */
	private String insttCodeNm = "";
	
	/** 업무구분코드명 */
	private String jobSeCodeNm = "";
	
	/** 처리구분코드명 */
	private String processSeCodeNm = "";
	
	/** 요청자 이름 */
	private String rqesterNm = "";
	
	/** 응답코드 명 */
	private String rspnsCodeNm = "";
	
	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";

	// domain
	/**
	 * @return the requstId
	 */
	public String getRequstId() {
		return requstId;
	}
	/**
	 * @param requstId
	 *            the requstId to set
	 */
	public void setRequstId(String requstId) {
		this.requstId = requstId;
	}

	/**
	 * @return the jobSeCode
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * @param jobSeCode
	 *            the jobSeCode to set
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	/**
	 * @return the insttCode
	 */
	public String getInsttCode() {
		return insttCode;
	}
	/**
	 * @param insttCode
	 *            the insttCode to set
	 */
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}

	/**
	 * @return the occrrncDe
	 */
	public String getOccrrncDe() {
		return occrrncDe;
	}
	/**
	 * @param occrrncDe
	 *            the occrrncDe to set
	 */
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	/**
	 * @return the rqesterIp
	 */
	public String getRqesterIp() {
		return rqesterIp;
	}
	/**
	 * @param rqesterIp
	 *            the rqesterIp to set
	 */
	public void setRqesterIp(String rqesterIp) {
		this.rqesterIp = rqesterIp;
	}

	/**
	 * @return the rqesterId
	 */
	public String getRqesterId() {
		return rqesterId;
	}
	/**
	 * @param rqesterId
	 *            the rqesterId to set
	 */
	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	/**
	 * @return the trgetMenuNm
	 */
	public String getTrgetMenuNm() {
		return trgetMenuNm;
	}
	/**
	 * @param trgetMenuNm
	 *            the trgetMenuNm to set
	 */
	public void setTrgetMenuNm(String trgetMenuNm) {
		this.trgetMenuNm = trgetMenuNm;
	}

	/**
	 * @return the srvcNm
	 */
	public String getSrvcNm() {
		return srvcNm;
	}
	/**
	 * @param srvcNm
	 *            the srvcNm to set
	 */
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}

	/**
	 * @return the methodNm
	 */
	public String getMethodNm() {
		return methodNm;
	}
	/**
	 * @param methodNm
	 *            the methodNm to set
	 */
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}

	/**
	 * @return the processSeCode
	 */
	public String getProcessSeCode() {
		return processSeCode;
	}
	/**
	 * @param processSeCode
	 *            the processSeCode to set
	 */
	public void setProcessSeCode(String processSeCode) {
		this.processSeCode = processSeCode;
	}

	/**
	 * @return the processCo
	 */
	public int getProcessCo() {
		return processCo;
	}
	/**
	 * @param processCo
	 *            the processCo to set
	 */
	public void setProcessCo(int processCo) {
		this.processCo = processCo;
	}

	/**
	 * @return the processTime
	 */
	public String getProcessTime() {
		return processTime;
	}
	/**
	 * @param processTime
	 *            the processTime to set
	 */
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	/**
	 * @return the rspnsCode
	 */
	public String getRspnsCode() {
		return rspnsCode;
	}
	/**
	 * @param rspnsCode
	 *            the rspnsCode to set
	 */
	public void setRspnsCode(String rspnsCode) {
		this.rspnsCode = rspnsCode;
	}

	/**
	 * @return the errorSe
	 */
	public String getErrorSe() {
		return errorSe;
	}
	/**
	 * @param errorSe
	 *            the errorSe to set
	 */
	public void setErrorSe(String errorSe) {
		this.errorSe = errorSe;
	}

	/**
	 * @return the errorCo
	 */
	public int getErrorCo() {
		return errorCo;
	}
	/**
	 * @param errorCo
	 *            the errorCo to set
	 */
	public void setErrorCo(int errorCo) {
		this.errorCo = errorCo;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	// helper
	/**
	 * @return the errorCodeNm
	 */
	public String getErrorCodeNm() {
		return errorCodeNm;
	}
	/**
	 * @param errorCodeNm
	 *            the errorCodeNm to set
	 */
	public void setErrorCodeNm(String errorCodeNm) {
		this.errorCodeNm = errorCodeNm;
	}

	/**
	 * @return the insttCodeNm
	 */
	public String getInsttCodeNm() {
		return insttCodeNm;
	}
	/**
	 * @param insttCodeNm
	 *            the insttCodeNm to set
	 */
	public void setInsttCodeNm(String insttCodeNm) {
		this.insttCodeNm = insttCodeNm;
	}

	/**
	 * @return the jobSeCodeNm
	 */
	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}
	/**
	 * @param jobSeCodeNm
	 *            the jobSeCodeNm to set
	 */
	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}

	/**
	 * @return the processSeCodeNm
	 */
	public String getProcessSeCodeNm() {
		return processSeCodeNm;
	}
	/**
	 * @param processSeCodeNm
	 *            the processSeCodeNm to set
	 */
	public void setProcessSeCodeNm(String processSeCodeNm) {
		this.processSeCodeNm = processSeCodeNm;
	}

	/**
	 * @return the rqesterNm
	 */
	public String getRqesterNm() {
		return rqesterNm;
	}
	/**
	 * @param rqesterNm
	 *            the rqesterNm to set
	 */
	public void setRqesterNm(String rqesterNm) {
		this.rqesterNm = rqesterNm;
	}

	/**
	 * @return the rspnsCodeNm
	 */
	public String getRspnsCodeNm() {
		return rspnsCodeNm;
	}
	/**
	 * @param rspnsCodeNm
	 *            the rspnsCodeNm to set
	 */
	public void setRspnsCodeNm(String rspnsCodeNm) {
		this.rspnsCodeNm = rspnsCodeNm;
	}

	/**
	 * @return the searchBgnDe
	 */
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	/**
	 * @param searchBgnDe
	 *            the searchBgnDe to set
	 */
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}
	
	/**
	 * @return the searchEndDe
	 */
	public String getSearchEndDe() {
		return searchEndDe;
	}
	/**
	 * @param searchEndDe
	 *            the searchEndDe to set
	 */
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	/**
	 * 
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
