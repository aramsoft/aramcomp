package aramframework.com.cop.bbs.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 만족도조사 서비스를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SatisfactionVO extends BaseVO {

	// domain
	/** 만족도 번호 */
	private long stsfdgNo = 0L;

	/** 게시판 ID */
	private String bbsId = "";

	/** 게시물 번호 */
	private long nttId = 0L;

	/** 작성자 ID */
	private String wrterId = "";

	/** 작성자명 */
	private String wrterNm = "";

	/** 패스워드 */
	private String stsfdgPassword = "";

	/** 만족도 */
	private int stsfdg = 0;

	/** 만족도 내용 */
	private String stsfdgCn = "";

	/** 사용 여부 */
	private String useAt = "";

	// helper
	/** 수정 처리 여부 */
	private boolean isModified = false;

	/** 확인 패스워드 */
	private String confirmPassword = "";

}
