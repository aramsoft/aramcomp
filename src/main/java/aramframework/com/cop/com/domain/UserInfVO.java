package aramframework.com.cop.com.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 정보 조회를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfVO extends BaseVO {

	// domain
	/** 사용자 아이디 */
	private String userId = "";

	/** 사용자 명 */
	private String userNm = "";

	/** 사용자 우편번호 */
	private String userZip = "";

	/** 사용자 주소 */
	private String userAdres = "";

	/** 사용자 이메일 */
	private String userEmail = "";

	// helper
	/** 사용여부 */
	private String useAt = "Y";

	/** 대상 중지 여부 (커뮤니티 또는 동호회) */
	private String deletedAt = "N";

	/** 대상 아이디 */
	private String trgetId = "";

}
