package aramframework.com.sym.sym.bak.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 백업결과관리에 대한 model 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BackupResultVO extends BaseVO {

	// domain
	/** 백업결과ID */
	private String backupResultId;
	
	/** 백업작업ID */
	private String backupOpertId;
	
	/** 백업화일 */
	private String backupFile;
	
	/** 상태 */
	private String sttus;
	
	/** 에러정보 */
	private String errorInfo;

	/** 실행시작시각 */
	private String executBeginTime;
	
	/** 실행종료시각 */
	private String executEndTime;
	
	// helper
	/** 백업작업명 */
	private String backupOpertNm;
	
	/** 상태명 */
	private String sttusNm;
	
	/** 백업원본디렉토리 */
	private String backupOrginlDrctry;
	
	/** 백업저장디렉토리 */
	private String backupStreDrctry;

	// domain
	/**
	 * @return the backupResultId
	 */
	public String getBackupResultId() {
		return backupResultId;
	}
	/**
	 * @param backupResultId
	 *            the backupResultId to set
	 */
	public void setBackupResultId(String backupResultId) {
		this.backupResultId = backupResultId;
	}
	
	/**
	 * @return the backupOpertId
	 */
	public String getBackupOpertId() {
		return backupOpertId;
	}
	/**
	 * @param backupOpertId
	 *            the backupOpertId to set
	 */
	public void setBackupOpertId(String backupOpertId) {
		this.backupOpertId = backupOpertId;
	}

	/**
	 * @return the backupFile
	 */
	public String getBackupFile() {
		return backupFile;
	}
	/**
	 * @param backupFile
	 *            the backupFile to set
	 */
	public void setBackupFile(String backupFile) {
		this.backupFile = backupFile;
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
	 * @return the backupOpertNm
	 */
	public String getBackupOpertNm() {
		return backupOpertNm;
	}
	/**
	 * @param backupOpertNm
	 *            the backupOpertNm to set
	 */
	public void setBackupOpertNm(String backupOpertNm) {
		this.backupOpertNm = backupOpertNm;
	}

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
	 * @return the backupOrginlDrctry
	 */
	public String getBackupOrginlDrctry() {
		return backupOrginlDrctry;
	}
	/**
	 * @param backupOrginlDrctry
	 *            the backupOrginlDrctry to set
	 */
	public void setBackupOrginlDrctry(String backupOrginlDrctry) {
		this.backupOrginlDrctry = backupOrginlDrctry;
	}

	/**
	 * @return the backupStreDrctry
	 */
	public String getBackupStreDrctry() {
		return backupStreDrctry;
	}
	/**
	 * @param backupStreDrctry
	 *            the backupStreDrctry to set
	 */
	public void setBackupStreDrctry(String backupStreDrctry) {
		this.backupStreDrctry = backupStreDrctry;
	}

}