package aramframework.com.sec.arm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 권한관리에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AuthorVO extends BaseVO {

	// domain
	/** 권한코드 */
	private String authorCode;
	
	/** 권한 명 */
	private String authorNm;

	/** 권한코드설명 */
	private String authorDc;
	
	/** 권한등록일자 */
	private String authorCreatDe;
	
}
