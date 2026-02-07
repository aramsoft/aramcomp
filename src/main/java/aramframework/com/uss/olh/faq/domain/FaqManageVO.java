package aramframework.com.uss.olh.faq.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FAQ를 처리하는 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class FaqManageVO extends BaseVO  {

	// domain
	/** FAQ ID */
	private String faqId;

	/** 질문제목 */
	private String qestnSj;

	/** 질문내용 */
	private String qestnCn;

	/** 답변내용 */
	private String answerCn;

	/** 조회횟수 */
	private String inqireCo;

	/** 첨부파일ID */
	private String atchFileId;

	/** 대상 아이디 */
	private String trgetId = "";

}
