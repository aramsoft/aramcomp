package aramframework.com.uss.olh.faq.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * FAQ를 처리하는 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

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

	// domain
	/**
	 * faqId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getFaqId() {
		return faqId;
	}
	/**
	 * faqId attribute 값을 설정한다.
	 * 
	 * @return faqId String
	 */
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	/**
	 * qestnSj attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnSj() {
		return qestnSj;
	}
	/**
	 * qestnSj attribute 값을 설정한다.
	 * 
	 * @return qestnSj String
	 */
	public void setQestnSj(String qestnSj) {
		this.qestnSj = qestnSj;
	}

	/**
	 * qestnCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnCn() {
		return qestnCn;
	}
	/**
	 * qestnCn attribute 값을 설정한다.
	 * 
	 * @return qestnCn String
	 */
	public void setQestnCn(String qestnCn) {
		this.qestnCn = qestnCn;
	}

	/**
	 * answerCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAnswerCn() {
		return answerCn;
	}
	/**
	 * answerCn attribute 값을 설정한다.
	 * 
	 * @return answerCn String
	 */
	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}

	/**
	 * inqireCo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getInqireCo() {
		return inqireCo;
	}
	/**
	 * inqireCo attribute 값을 설정한다.
	 * 
	 * @return inqireCo String
	 */
	public void setInqireCo(String inqireCo) {
		this.inqireCo = inqireCo;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * trgetId attribute를 리턴한다.
	 * 
	 * @return the trgetId
	 */
	public String getTrgetId() {
		return trgetId;
	}
	/**
	 * trgetId attribute 값을 설정한다.
	 * 
	 * @param trgetId
	 *            the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

}
