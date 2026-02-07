package aramframework.com.cop.bbs.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시물 관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommentVO extends BaseVO {

	// domain
	/** 댓글번호 */
	private long commentNo = 0L;

	/** 게시판 ID */
	private String bbsId = "";

	/** 게시물 번호 */
	private long nttId = 0L;

	/** 작성자 ID */
	private String wrterId = "";

	/** 작성자명 */
	private String wrterNm = "";

	/** 패스워드 */
	private String commentPassword = "";

	/** 댓글 내용 */
	private String commentCn = "";

	/** 사용 여부 */
	private String useAt = "";

	// helper
	/** 수정 처리 여부 */
	private boolean isModified = false;

	/** 확인 패스워드 */
	private String confirmPassword = "";

}
