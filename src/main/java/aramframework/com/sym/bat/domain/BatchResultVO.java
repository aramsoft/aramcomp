package aramframework.com.sym.bat.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 배치결과관리에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
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
	
}