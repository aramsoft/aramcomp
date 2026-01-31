package aramframework.com.sym.bat.domain;

/**
 * 배치스케줄요일에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class BatchSchdulDfkVO {

	// domain
	/** 배치스케줄ID */
	private String batchSchdulId;

	/** 실행스케줄요일 */
	private String executSchdulDfkSe;

	// helper
	/** 실행스케줄요일명 */
	private String executSchdulDfkSeNm;

	// domain
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