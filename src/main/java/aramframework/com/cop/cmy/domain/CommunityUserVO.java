package aramframework.com.cop.cmy.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 커뮤티니 사용자 관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommunityUserVO extends BaseVO {

	// domain
	/** 커뮤니티아이디 */
	private String cmmntyId = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 관리자여부 */
	private String mngrAt = "";

	/** 가입일 */
	private String sbscrbDe = "";

	/** 탈퇴일 */
	private String secsnDe = "";

	/** 사용여부 */
	private String useAt = "";

	// helper
	/** 인증여부 */
	private String authenticatedAt = "";

	/** 사용자명 */
	private String emplyrNm = "";

}
