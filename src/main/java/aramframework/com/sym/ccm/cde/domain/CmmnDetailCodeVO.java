package aramframework.com.sym.ccm.cde.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통상세코드 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CmmnDetailCodeVO extends BaseVO {

	// domain
	/** 코드ID */
	private String codeId = "";

	/** 코드 */
	private String code = "";

	/** 코드명 */
	private String codeNm = "";

	/** 코드설명 */
	private String codeDc = "";

	/** 사용여부 */
	private String useAt = "";

	// helper
	/** 코드ID명 */
	private String codeIdNm = "";

}
