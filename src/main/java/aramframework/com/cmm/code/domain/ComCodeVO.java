package aramframework.com.cmm.code.domain;

import lombok.Data;

/**
 * 공통상세코드 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class ComCodeVO {

	// domain
	/** 코드ID */
	private String codeId = "";

	/** 코드	 */
	private String code = "";

	/** 코드명 */
	private String codeNm = "";

	/** 코드설명 */
	private String codeDc = "";

}
