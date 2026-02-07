package aramframework.com.sym.sym.bak.domain;

import lombok.Data;

/**
 * 백업스케줄요일에 대한 model 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class BackupSchdulDfkVO {

	// domain
	/** 백업작업ID */
	private String backupOpertId;

	/** 실행스케줄요일 */
	private String executSchdulDfkSe;

	// helper
	/** 실행스케줄요일명 */
	private String executSchdulDfkSeNm;

}