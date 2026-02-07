package aramframework.com.cop.scp.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 스크랩 서비스를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ScrapVO extends BaseVO {

	// domain
	/** 스크랩 ID */
	private String scrapId = "";

	/** 게시판 ID */
	private String bbsId = "";

	/** 게시물 번호 */
	private long nttId = 0L;

	/** 스크랩명 */
	private String scrapNm = "";

	/** 사용 여부 */
	private String useAt = "";

	// helper
	/** 유일 아이디 */
	private String userId = "";

}
