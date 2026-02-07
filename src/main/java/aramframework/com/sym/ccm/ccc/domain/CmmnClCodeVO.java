package aramframework.com.sym.ccm.ccc.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통분류코드 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CmmnClCodeVO extends BaseVO {

	// domain
	/** 분류코드 */
	private String clCode = "";

	/** 분류코드명 */
	private String clCodeNm = "";

	/** 분류코드설명 */
	private String clCodeDc = "";

	/** 사용여부 */
	private String useAt = "";

}
