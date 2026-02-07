package aramframework.com.sym.sym.bak.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 백업결과관리에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
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

}