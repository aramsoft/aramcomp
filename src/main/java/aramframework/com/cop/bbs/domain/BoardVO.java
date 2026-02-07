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
public class BoardVO extends BaseVO {

	// domain
	/** 게시물 아이디   */
	private long nttId = 0L;

	/** 게시판 아이디	 */
	private String bbsId = "";

	/** 게시물 쓰레드 번호  */
	private int threadNo = 0;

	/** 게시물 제목  */
	private String nttSj = "";

	/** 게시물 내용  */
	private String nttCn = "";

	/** 답장여부  */
	private String answerAt = "";

	/** 부모글번호  */
	private long parntsNttId = 0L;

	/** 답장위치 */
	private int threadDepth = 0;

	/** 정렬순서   */
	private long threadGroupNo = 0L;

	/** 조회수  */
	private int rdcnt = 0;

	/** 사용여부 	 */
	private String useAt = "";

	/** 게시시작일  */
	private String ntceBgnde = "";

	/** 게시종료일  */
	private String ntceEndde = "";

	/** 게시자 아이디	 */
	private String ntcrId = "";

	/** 게시자명   */
	private String ntcrNm = "";

	/** 패스워드	*/
	private String password = "";

	/** 게시물 첨부파일 아이디  */
	private String atchFileId = "";

	// helper
	// Board Master 정보
	private BoardMasterVO boardMasterVO = null;
	
	/** 유효여부   */
	private String isExpired = "N";
	/** 수정 처리 여부 */
	private boolean isGuestModified = false;

}
