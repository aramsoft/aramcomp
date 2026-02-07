package aramframework.com.cop.ems.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 발송메일 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SndngMailVO extends BaseVO {
	
	// domain
	/** 메세지ID */
	private String mssageId;

	/** 메일내용 */
	private String emailCn;

	/** 발신자 */
	private String dsptchPerson;

	/** 수신자 */
	private String recptnPerson;

	/** 제목 */
	private String sj;

	/** 발송결과코드 */
	private String sndngResultCode;

	/** 발신일자 */
	private String sndngDe;

	/** 첨부파일ID */
	private String atchFileId;

	// helper
	/** 발송요청XML내용 */
	private String xmlContent;

	/** 팝업링크여부(Y/N) */
	private String link;

}
