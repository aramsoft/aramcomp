package aramframework.com.cop.smt.dsm.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 일지관리 VO Class 구현
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DiaryManageVO extends BaseVO  {

	// domain
	/** 일지ID */
	private String diaryId;

	/** 일정ID */
	private String schdulId;

	/** 진척율 */
	private String diaryProcsPte;

	/** 일정명 */
	private String diaryNm;

	/** 지지사항 */
	private String drctMatter;

	/** 특이사항 */
	private String partclrMatter;

	/** 첨부파일 */
	private String atchFileId;

	// helper
	/** 일정명 */
	private String schdulNm;

}
