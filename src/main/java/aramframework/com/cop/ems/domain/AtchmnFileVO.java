package aramframework.com.cop.ems.domain;

import lombok.Data;

/**
 * 발송메일에 첨부되는 파일 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
public class AtchmnFileVO {

	/** 첨부파일ID */
	private String atchFileId;

	/** 파일연번 */
	private String fileSn;

	/** 파일저장경로 */
	private String fileStreCours;

	/** 저장파일명 */
	private String streFileNm;

	/** 원파일명 */
	private String orignlFileNm;

	/** 파일확장자 */
	private String fileExtsn;

	/** 파일타입 */
	private String fileType;

	/** 파일크기 */
	private int fileSize;

}
