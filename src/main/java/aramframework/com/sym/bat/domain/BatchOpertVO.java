package aramframework.com.sym.bat.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 배치작업관리에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BatchOpertVO extends BaseVO {

	// domain
	/** 배치작업ID */
	private String batchOpertId;
	
	/** 배치작업명 */
	private String batchOpertNm;
	
	// batch type 1
	/** 배치프로그램 */
	private String batchProgrm;
	
	/** 파라미터 */
	private String paramtr;
	
	// batch type 2
	/** 배치클래스 */
	private String batchObject;
	
	/** 배치메소드 */
	private String batchMethod;
	
	/** 사용여부 */
	private String useAt;
	
}