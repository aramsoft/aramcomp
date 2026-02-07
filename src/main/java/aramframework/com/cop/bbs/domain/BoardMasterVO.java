package aramframework.com.cop.bbs.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시판 속성 정보를 관리하기 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BoardMasterVO extends BaseVO {

	// domain
	/** 게시판 아이디 */
	private String bbsId = "";

	/** 게시판 명 */
	private String bbsNm = "";

	/** 게시판 소개 */
	private String bbsIntrcn = "";

	/** 게시판 유형코드 */
	private String bbsTyCode = "";

	/** 게시판 속성코드 */
	private String bbsAttrbCode = "";

	/** 답장가능여부 */
	private String replyPosblAt = "";

	/** 파일첨부가능여부 */
	private String fileAtchPosblAt = "";

	/** 첨부가능파일숫자 */
	private int posblAtchFileNumber = 0;

	/** 첨부가능파일사이즈 */
	private String posblAtchFileSize = null;

	/** 사용여부 */
	private String useAt = "";

	/** 템플릿 아이디 */
	private String tmplatId = "";

	// ---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	// ---------------------------------
	/** 추가 option (댓글-comment, 만족도조사-stsfdg) */
	private String option = "";

	/** 댓글 여부 */
	private String commentAt = "";

	/** 만족도조사 */
	private String stsfdgAt = "";

	// helper
	/** 게시판유형 코드명 */
	private String bbsTyCodeNm = "";

	/** 게시판속성 코드명 */
	private String bbsAttrbCodeNm = "";

	/** 템플릿 명 */
	private String tmplatNm = "";

	/** 템플릿경로 */
	private String tmplatCours = "";

	/** 사용플래그 */
	private String bbsUseFlag = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 대상 아이디 */
	private String trgetId = "";

}
