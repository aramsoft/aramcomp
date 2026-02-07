package aramframework.com.cmm.code.domain;

import lombok.Data;

/**
 * 공통코드 조회 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class SearchCodeVO {

	/** 코드 ID */
	private String codeId = "";

	/** 상세코드 */
	private String code = "";

	/** 특정테이블명 */
	private String tableNm = ""; // 특정테이블에서 코드정보를추출시 사용

	/** 상세 조건 여부 */
	private String haveDetailCondition = "N";

	/** 상세 조건 */
	private String detailCondition = "";

}
