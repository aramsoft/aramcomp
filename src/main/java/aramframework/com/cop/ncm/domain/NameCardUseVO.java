package aramframework.com.cop.ncm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 명함사용자 정보를 관리하기 위한 모델 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NameCardUseVO extends BaseVO {

	// domain
	/** 명함아이디 */
	private String ncrdId = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 사용여부 */
	private String useAt = "";

	/** 생성일시 */
	private String creatDt = "";

	// helper
	/** 사용자 명 */
	private String userNm = "";

	/** 명함 이름 */
	private String ncrdNm = "";

	/** 회사명 */
	private String cmpnyNm = "";

	/** 부서명 */
	private String deptNm = "";

}
