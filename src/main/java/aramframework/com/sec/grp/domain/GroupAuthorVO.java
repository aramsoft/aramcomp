package aramframework.com.sec.grp.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 권한그룹에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class GroupAuthorVO extends BaseVO {

	/** 설정대상 사용자 ID	 */
	private String userId;
	
	/** 설정대상 사용자 명 */
	private String userNm;
	
	/** 설정대상 사용자 유형 코드 */
	private String mberTyCode;
	
	/** 설정대상 사용자 유형 명 */
	private String mberTyNm;
	
	/** 권한코드 */
	private String authorCode;
	
	/** 등록 여부 */
	private String regYn;
	
}