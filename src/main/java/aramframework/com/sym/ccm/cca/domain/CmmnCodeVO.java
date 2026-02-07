package aramframework.com.sym.ccm.cca.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통코드 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CmmnCodeVO extends BaseVO {

	// domain
	/** 코드ID */
	private String codeId = "";

	/** 코드ID명 */
	private String codeIdNm = "";

	/** 코드ID설명 */
	private String codeIdDc = "";

	/** 사용여부 */
	private String useAt = "";

	/** 분류코드 */
	private String clCode = "";

	// helper
	/** 분류코드명 */
	private String clCodeNm = "";

}
