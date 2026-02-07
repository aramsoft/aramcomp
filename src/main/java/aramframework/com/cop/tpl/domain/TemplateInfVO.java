package aramframework.com.cop.tpl.domain;


import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 템플릿 정보 관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TemplateInfVO extends BaseVO {

	// domain
	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 템플릿 명 */
	private String tmplatNm = "";

	/** 템플릿 경로 */
	private String tmplatCours = "";

	/** 사용여부 */
	private String useAt = "";

	/** 탬플릿 구분코드 */
	private String tmplatSeCode = "";

	// helper
	/** 템플릿 구분 코드명 */
	private String tmplatSeCodeNm = "";

	/** 구분 유형 */
	private String typeFlag = "";

}
