package aramframework.com.uat.uap.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 개요
 * - 로그인정책에 대한 model 클래스를 정의한다.
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LoginPolicyVO extends BaseVO {

	// domain
	/** 사용자 ID */
	private String emplyrId;
	
	/** IP정보 */
	private String ipInfo;
	
	/** 중복허용여부 */
	private String dplctPermAt;
	
	/** 제한여부 */
	private String lmttAt;
	
	// helper
	/** 사용자 명 */
	private String emplyrNm;
	
	/** 사용자 구분 */
	private String emplyrSe;
	
	/** 등록자 ID */
	private String userId;
	
	/** 등록일시 */
	private String regDate;
	
	/** 등록여부 */
	private String regYn;

}
