package aramframework.com.cmm.file.domain;

import aramframework.cmm.domain.BaseVO;
import aramframework.cmm.util.WebUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 파일정보 처리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class FileVO extends BaseVO {

	// domain
	/** 첨부파일 아이디 */
	private String atchFileId = "";

	/** 참조 아이디 */
	private String category = "";
	
	/** 생성일자	 */
	private String creatDt = "";

	/** 파일연번  */
	private int fileSn = 0;

	/** 파일저장경로 */
	private String fileStreCours = "";

	/** 저장파일명 */
	private String streFileNm = "";

	/** 원파일명	 */
	private String orignlFileNm = "";

	/** 파일확장자 */
	private String fileExtsn = "";

	/** 파일콘텐트타입 */
	private String fileType = "";

	/** 파일내용	 */
	private String fileCn = "";

	/** 파일크기	 */
	private long fileSize = 0;

	public String getPathId() {
		return WebUtil.getPathId(this.atchFileId);
	}
	
}
