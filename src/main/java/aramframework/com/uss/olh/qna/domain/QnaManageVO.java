package aramframework.com.uss.olh.qna.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Q&A를 처리하는 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QnaManageVO extends BaseVO  {

	// domain
	/** QA ID */
	private String qaId;

	/** 질문제목 */
	private String qestnSj;

	/** 질문내용 */
	private String qestnCn;

	/** 작성일자 */
	private String writngDe;

	/** 조회횟수 */
	private String inqireCo;

	/** 이메일 주소 */
	private String emailAdres;

	/** 질의응답처리상태코드 */
	private String qnaProcessSttusCode;

	/** 작성자 명 */
	private String wrterNm;

	/** 답변내용 */
	private String answerCn;

	/** 작성비밀번호 */
	private String writngPassword;

	/** 답변일자 */
	private String answerDe;

	/** 이메일 답변여부 */
	private String emailAnswerAt;

	/** 지역번호 */
	private String areaNo;

	/** 중간전화번호 */
	private String middleTelno;

	/** 끝전화번호 */
	private String endTelno;

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 질의응답처리상태코드명 */
	private String qnaProcessSttusCodeNm;

	/** 답변자명 */
	private String emplyrNm;

	/** 사무실전화번호 */
	private String offmTelno;

	/** 답변자 EMAIL 주소 */
	private String aemailAdres;

	/** 부서명 */
	private String orgnztNm;

}
