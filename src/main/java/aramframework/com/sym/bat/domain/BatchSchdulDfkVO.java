package aramframework.com.sym.bat.domain;

import lombok.Data;

/**
 * 배치스케줄요일에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class BatchSchdulDfkVO {

	// domain
	/** 배치스케줄ID */
	private String batchSchdulId;

	/** 실행스케줄요일 */
	private String executSchdulDfkSe;

	// helper
	/** 실행스케줄요일명 */
	private String executSchdulDfkSeNm;

}