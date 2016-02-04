package aramframework.com.uss.olh.qna.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * Q&A를 처리하는 VO 클래스
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

public class QnaManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	// 도메인 정보
	/** QA ID */
	private String qaId;

	/** 질문제목 */
	private String qestnSj;

	/** 질문내용 */
	private String qestnCn;

	/** 작성비밀번호 */
	private String writngPassword;

	/** 지역번호 */
	private String areaNo;

	/** 중간전화번호 */
	private String middleTelno;

	/** 끝전화번호 */
	private String endTelno;

	/** 이메일 주소 */
	private String emailAdres;

	/** 이메일 답변여부 */
	private String emailAnswerAt;

	/** 작성자 명 */
	private String wrterNm;

	/** 작성일자 */
	private String writngDe;

	/** 조회횟수 */
	private String inqireCo;

	/** 질의응답처리상태코드 */
	private String qnaProcessSttusCode;

	/* 질의응답처리상태코드명 */
	private String qnaProcessSttusCodeNm;

	/** 답변내용 */
	private String answerCn;

	/** 답변일자 */
	private String answerDe;

	/* 답변자명 */
	private String emplyrNm;

	/* 사무실전화번호 */
	private String offmTelno;

	/* 답변자 EMAIL 주소 */
	private String aemailAdres;

	/* 부서명 */
	private String orgnztNm;

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

	/**
	 * qaId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQaId() {
		return qaId;
	}
	/**
	 * qaId attribute 값을 설정한다.
	 * 
	 * @return qaId String
	 */
	public void setQaId(String qaId) {
		this.qaId = qaId;
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
	 * writngPassword attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWritngPassword() {
		return writngPassword;
	}
	/**
	 * writngPassword attribute 값을 설정한다.
	 * 
	 * @return writngPassword String
	 */
	public void setWritngPassword(String writngPassword) {
		this.writngPassword = writngPassword;
	}

	/**
	 * areaNo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @return areaNo String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}
	/**
	 * middleTelno attribute 값을 설정한다.
	 * 
	 * @return middleTelno String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEndTelno() {
		return endTelno;
	}
	/**
	 * endTelno attribute 값을 설정한다.
	 * 
	 * @return endTelno String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	/**
	 * emailAdres attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * 
	 * @return emailAdres String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * emailAnswerAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEmailAnswerAt() {
		return emailAnswerAt;
	}
	/**
	 * emailAnswerAt attribute 값을 설정한다.
	 * 
	 * @return emailAnswerAt String
	 */
	public void setEmailAnswerAt(String emailAnswerAt) {
		this.emailAnswerAt = emailAnswerAt;
	}

	/**
	 * wrterNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWrterNm() {
		return wrterNm;
	}
	/**
	 * wrterNm attribute 값을 설정한다.
	 * 
	 * @return wrterNm String
	 */
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	/**
	 * writngDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWritngDe() {
		return writngDe;
	}
	/**
	 * writngDe attribute 값을 설정한다.
	 * 
	 * @return writngDe String
	 */
	public void setWritngDe(String writngDe) {
		this.writngDe = writngDe;
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
	 * qnaProcessSttusCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQnaProcessSttusCode() {
		return qnaProcessSttusCode;
	}
	/**
	 * qnaProcessSttusCode attribute 값을 설정한다.
	 * 
	 * @return qnaProcessSttusCode String
	 */
	public void setQnaProcessSttusCode(String qnaProcessSttusCode) {
		this.qnaProcessSttusCode = qnaProcessSttusCode;
	}

	/**
	 * qnaProcessSttusCodeNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQnaProcessSttusCodeNm() {
		return qnaProcessSttusCodeNm;
	}
	/**
	 * qnaProcessSttusCodeNm attribute 값을 설정한다.
	 * 
	 * @return qnaProcessSttusCodeNm String
	 */
	public void setQnaProcessSttusCodeNm(String qnaProcessSttusCodeNm) {
		this.qnaProcessSttusCodeNm = qnaProcessSttusCodeNm;
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
	 * answerDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAnswerDe() {
		return answerDe;
	}
	/**
	 * answerDe attribute 값을 설정한다.
	 * 
	 * @return answerDe String
	 */
	public void setAnswerDe(String answerDe) {
		this.answerDe = answerDe;
	}

	/**
	 * emplyrNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * emplyrNm attribute 값을 설정한다.
	 * 
	 * @return emplyrNm String
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

	/**
	 * offmTelno attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getOffmTelno() {
		return offmTelno;
	}
	/**
	 * offmTelno attribute 값을 설정한다.
	 * 
	 * @return offmTelno String
	 */
	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	/**
	 * aemailAdres attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAemailAdres() {
		return aemailAdres;
	}
	/**
	 * aemailAdres attribute 값을 설정한다.
	 * 
	 * @return aemailAdres String
	 */
	public void setAemailAdres(String aemailAdres) {
		this.aemailAdres = aemailAdres;
	}

	/**
	 * orgnztNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * orgnztNm attribute 값을 설정한다.
	 * 
	 * @return orgnztNm String
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

}
