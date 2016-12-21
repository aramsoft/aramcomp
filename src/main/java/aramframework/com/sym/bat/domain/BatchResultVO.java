package aramframework.com.sym.bat.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 배치결과관리에 대한 model 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class BatchResultVO extends BaseVO {

	// domain
	/** 배치결과ID */
	private String batchResultId;
	
	/** 배치스케줄ID */
	private String batchSchdulId;

	/** 배치작업ID */
	private String batchOpertId;
	
	/** 파라미터 */
	private String paramtr;
	
	/** 상태 */
	private String sttus;
	
	/** 에러정보 */
	private String errorInfo;

	/** 실행시작시각 */
	private String executBeginTime;
	
	/** 실행종료시각 */
	private String executEndTime;
	
	// helper
	/** 상태명 */
	private String sttusNm;

	/** 배치작업명 */
	private String batchOpertNm;
	
	/** 배치프로그램 */
	private String batchProgrm;
	
	/** 배치클래스 */
	private String batchObject;
	
	/** 배치메소드 */
	private String batchMethod;
	
	/** 배치빈 */
	private String batchBean;
	
	// domain
	/**
	 * @return the batchResultId
	 */
	public String getBatchResultId() {
		return batchResultId;
	}
	/**
	 * @param batchResultId
	 *            the batchResultId to set
	 */
	public void setBatchResultId(String batchResultId) {
		this.batchResultId = batchResultId;
	}
	
	/**
	 * @return the batchSchdulId
	 */
	public String getBatchSchdulId() {
		return batchSchdulId;
	}
	/**
	 * @param batchSchdulId
	 *            the batchSchdulId to set
	 */
	public void setBatchSchdulId(String batchSchdulId) {
		this.batchSchdulId = batchSchdulId;
	}

	/**
	 * @return the batchOpertId
	 */
	public String getBatchOpertId() {
		return batchOpertId;
	}
	/**
	 * @param batchOpertId
	 *            the batchOpertId to set
	 */
	public void setBatchOpertId(String batchOpertId) {
		this.batchOpertId = batchOpertId;
	}

	/**
	 * @return the paramtr
	 */
	public String getParamtr() {
		return paramtr;
	}
	/**
	 * @param paramtr
	 *            the paramtr to set
	 */
	public void setParamtr(String paramtr) {
		this.paramtr = paramtr;
	}

	/**
	 * @return the sttus
	 */
	public String getSttus() {
		return sttus;
	}
	/**
	 * @param sttus
	 *            the sttus to set
	 */
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}
	/**
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the executBeginTime
	 */
	public String getExecutBeginTime() {
		return executBeginTime;
	}
	/**
	 * @param executBeginTime
	 *            the executBeginTime to set
	 */
	public void setExecutBeginTime(String executBeginTime) {
		this.executBeginTime = executBeginTime;
	}
	
	/**
	 * @return the executEndTime
	 */
	public String getExecutEndTime() {
		return executEndTime;
	}
	/**
	 * @param executEndTime
	 *            the executEndTime to set
	 */
	public void setExecutEndTime(String executEndTime) {
		this.executEndTime = executEndTime;
	}

	// helper
	/**
	 * @return the sttusNm
	 */
	public String getSttusNm() {
		return sttusNm;
	}
	/**
	 * @param sttusNm
	 *            the sttusNm to set
	 */
	public void setSttusNm(String sttusNm) {
		this.sttusNm = sttusNm;
	}

	/**
	 * @return the batchOpertNm
	 */
	public String getBatchOpertNm() {
		return batchOpertNm;
	}
	/**
	 * @param batchOpertNm
	 *            the batchOpertNm to set
	 */
	public void setBatchOpertNm(String batchOpertNm) {
		this.batchOpertNm = batchOpertNm;
	}

	/**
	 * @return the batchProgrm
	 */
	public String getBatchProgrm() {
		return batchProgrm;
	}
	/**
	 * @param batchProgrm
	 *            the batchProgrm to set
	 */
	public void setBatchProgrm(String batchProgrm) {
		this.batchProgrm = batchProgrm;
	}

	/**
	 * @return the batchObject
	 */
	public String getBatchObject() {
		return batchObject;
	}
	/**
	 * @param batchObject
	 *            the batchObject to set
	 */
	public void setBatchObject(String batchObject) {
		this.batchObject = batchObject;
	}

	/**
	 * @return the batchMethod
	 */
	public String getBatchMethod() {
		return batchMethod;
	}
	/**
	 * @param batchMethod
	 *            the batchMethod to set
	 */
	public void setBatchMethod(String batchMethod) {
		this.batchMethod = batchMethod;
	}

	/**
	 * @return the batchBean
	 */
	public String getBatchBean() {
		return batchBean;
	}
	/**
	 * @param batchBean
	 *            the batchBean to set
	 */
	public void setBatchBean(String batchBean) {
		this.batchBean = batchBean;
	}

}