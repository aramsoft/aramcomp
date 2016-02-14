package aramframework.com.sym.sym.bak.domain;

/**
 * 백업스케줄요일에 대한 model 클래스
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

public class BackupSchdulDfkVO {

	// domain
	/** 백업작업ID */
	private String backupOpertId;

	/** 실행스케줄요일 */
	private String executSchdulDfkSe;

	// helper
	/** 실행스케줄요일명 */
	private String executSchdulDfkSeNm;

	// domain
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
	 * @return the executSchdulDfkSe
	 */
	public String getExecutSchdulDfkSe() {
		return executSchdulDfkSe;
	}
	/**
	 * @param executSchdulDfkSe
	 *            the executSchdulDfkSe to set
	 */
	public void setExecutSchdulDfkSe(String executSchdulDfkSe) {
		this.executSchdulDfkSe = executSchdulDfkSe;
	}

	// helper
	/**
	 * @return the executSchdulDfkSeNm
	 */
	public String getExecutSchdulDfkSeNm() {
		return executSchdulDfkSeNm;
	}
	/**
	 * @param executSchdulDfkSeNm
	 *            the executSchdulDfkSeNm to set
	 */
	public void setExecutSchdulDfkSeNm(String executSchdulDfkSeNm) {
		this.executSchdulDfkSeNm = executSchdulDfkSeNm;
	}

}