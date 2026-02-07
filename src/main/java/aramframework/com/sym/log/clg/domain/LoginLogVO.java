package aramframework.com.sym.log.clg.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 로그인 로그관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LoginLogVO extends BaseVO {

	// domain
	/** 로그ID */
	private String logId;

	/** 사용자ID */
	private String loginId;

	/** 접속IP */
	private String loginIp;

	/** 접속방식 */
	private String loginMthd;

	/** 에러발생여부 */
	private String errOccrrAt;

	/** 에러코드 */
	private String errorCode;

	/** 생성일시 */
	private String creatDt;

	// helper
	/** 사용자명 */
	private String loginNm;

	/** 검색시작일 */
	private String searchBgnDe = "";
	
	/** 검색종료일 */
	private String searchEndDe = "";

}
