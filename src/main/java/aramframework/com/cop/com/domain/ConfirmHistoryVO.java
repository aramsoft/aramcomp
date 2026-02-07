package aramframework.com.cop.com.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 승인정보를 관리하기 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ConfirmHistoryVO extends BaseVO {

	// domain
	/** 확인구분코드 */
	private int confmNumber = 0;

	/** 승인요청자 아이디 */
	private String confmRqesterId = "";

	/** 승인자 아이디 */
	private String confmerId = "";

	/** 승인일 */
	private String confmDe = "";

	/** 승인유형코드 */
	private String confmTyCode = "";

	/** 승인상태코드 */
	private String confmSttusCode = "";

	/** 작업유형코드 */
	private String opertTyCode = "";

	/** 작업 아이디 */
	private String opertId = "";

	/** 대상업무유형코드 */
	private String trgetJobTyCode = "";

	/** 대상업무 아이디 */
	private String trgetJobId = "";

	// helper
	/** 확인요청자명 */
	private String confmRqesterNm = "";

	/** 확인구분 코드명 */
	private String confmTyCodeNm = "";

	/** 확인상태 코드명 */
	private String confmSttusCodeNm = "";

	/** 대상유형구분 코드명 */
	private String trgetJobTyNm = "";

	/** 처리구분 코드명 */
	private String opertTyCodeNm = "";

}
