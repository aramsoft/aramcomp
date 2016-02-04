package aramframework.com.uss.olp.cns.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 상담내용을 처리하는 VO 클래스
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

public class CnsltManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	// 도메인 VO
	/** 상담 ID */
	private String cnsltId;

	/** 상담제목 */
	private String cnsltSj;

	/** 상담내용 */
	private String cnsltCn;

	/** 공개여부 */
	private String othbcAt;

	/** 작성비밀번호 */
	private String writngPassword;

	/** 지역번호 */
	private String areaNo;

	/** 중간전화번호 */
	private String middleTelno;

	/** 끝전화번호 */
	private String endTelno;

	/** 처음휴대폰번호 */
	private String firstMoblphonNo;

	/** 중간휴대폰번호 */
	private String middleMbtlnum;

	/** 끝휴대폰번호 */
	private String endMbtlnum;

	/** 이메일 주소 */
	private String emailAdres;

	/** 이메일 답변여부 */
	private String emailAnswerAt;

	/** 첨부파일 ID */
	private String atchFileId;

	/** 작성자 명 */
	private String wrterNm;

	/** 작성일자 */
	private String writngDe;

	/** 조회횟수 */
	private String inqireCo;

	/** 질의응답처리상태코드 */
	private String qnaProcessSttusCode;

	/** 질의응답처리상태코드명 */
	private String qnaProcessSttusCodeNm;

	/** 조치내용 */
	private String managtCn;

	/** 조치일자 */
	private String managtDe;

	/** 답변자명 */
	private String emplyrNm;

	/** 사무실전화번호 */
	private String offmTelno;

	/** 답변자 EMAIL 주소 */
	private String aemailAdres;

	/** 부서명 */
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
	 * cnsltId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getCnsltId() {
		return cnsltId;
	}
	/**
	 * cnsltId attribute 값을 설정한다.
	 * 
	 * @return cnsltId String
	 */
	public void setCnsltId(String cnsltId) {
		this.cnsltId = cnsltId;
	}

	/**
	 * cnsltSj attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getCnsltSj() {
		return cnsltSj;
	}
	/**
	 * cnsltSj attribute 값을 설정한다.
	 * 
	 * @return cnsltSj String
	 */
	public void setCnsltSj(String cnsltSj) {
		this.cnsltSj = cnsltSj;
	}

	/**
	 * cnsltCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getCnsltCn() {
		return cnsltCn;
	}
	/**
	 * cnsltCn attribute 값을 설정한다.
	 * 
	 * @return cnsltCn String
	 */
	public void setCnsltCn(String cnsltCn) {
		this.cnsltCn = cnsltCn;
	}

	/**
	 * othbcAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getOthbcAt() {
		return othbcAt;
	}
	/**
	 * othbcAt attribute 값을 설정한다.
	 * 
	 * @return othbcAt String
	 */
	public void setOthbcAt(String othbcAt) {
		this.othbcAt = othbcAt;
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
	 * firstMoblphonNo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getFirstMoblphonNo() {
		return firstMoblphonNo;
	}
	/**
	 * firstMoblphonNo attribute 값을 설정한다.
	 * 
	 * @return firstMoblphonNo String
	 */
	public void setFirstMoblphonNo(String firstMoblphonNo) {
		this.firstMoblphonNo = firstMoblphonNo;
	}

	/**
	 * middleMbtlnum attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMiddleMbtlnum() {
		return middleMbtlnum;
	}
	/**
	 * middleMbtlnum attribute 값을 설정한다.
	 * 
	 * @return middleMbtlnum String
	 */
	public void setMiddleMbtlnum(String middleMbtlnum) {
		this.middleMbtlnum = middleMbtlnum;
	}

	/**
	 * endMbtlnum attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEndMbtlnum() {
		return endMbtlnum;
	}
	/**
	 * endMbtlnum attribute 값을 설정한다.
	 * 
	 * @return endMbtlnum String
	 */
	public void setEndMbtlnum(String endMbtlnum) {
		this.endMbtlnum = endMbtlnum;
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
	 * managtCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getManagtCn() {
		return managtCn;
	}
	/**
	 * managtCn attribute 값을 설정한다.
	 * 
	 * @return managtCn String
	 */
	public void setManagtCn(String managtCn) {
		this.managtCn = managtCn;
	}

	/**
	 * managtDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getManagtDe() {
		return managtDe;
	}
	/**
	 * managtDe attribute 값을 설정한다.
	 * 
	 * @return managtDe String
	 */
	public void setManagtDe(String managtDe) {
		this.managtDe = managtDe;
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
