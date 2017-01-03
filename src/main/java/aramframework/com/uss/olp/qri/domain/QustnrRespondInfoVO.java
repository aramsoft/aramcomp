package aramframework.com.uss.olp.qri.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 설문조사 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class QustnrRespondInfoVO extends BaseVO  {

	// domain
	/** 설문응답ID */
	private String qestnrQesrspnsId = "";

	/** 설문ID */
	private String qestnrId = "";

	/** 설문문항ID */
	private String qestnrQesitmId = "";

	/** 설문항목ID */
	private String qustnrIemId = "";

	/** 응답자답변내용 */
	private String respondAnswerCn = "";

	/** 기타답변내용 */
	private String etcAnswerCn = "";

	/** 응답자명 */
	private String respondNm = "";

	// helper
	/** 설문제목 */
	private String qestnrSj = "";

	/** 질문내용 */
	private String qestnCn = "";

	/** 질문유형 */
	private String qestnTyCode = "";

	/** 항목내용 */
	private String qustnrIemCn = "";

	/** 대상 아이디 */
	private String trgetId = "";

	/** 검색모드설정 */
	private String searchMode = "";

	// domain
	/**
	 * qestnrQesrspnsId attribute 를 리턴한다.
	 * 
	 * @return 	the qestnrQesrspnsId
	 */
	public String getQestnrQesrspnsId() {
		return qestnrQesrspnsId;
	}
	/**
	 * qestnrQesrspnsId attribute 값을 설정한다.
	 * 
	 * @param 	qestnrQesrspnsId String
	 */
	public void setQestnrQesrspnsId(String qestnrQesrspnsId) {
		this.qestnrQesrspnsId = qestnrQesrspnsId;
	}

	/**
	 * qestnrId attribute 를 리턴한다.
	 * 
	 * @return the qestnrId
	 */
	public String getQestnrId() {
		return qestnrId;
	}
	/**
	 * qestnrId attribute 값을 설정한다.
	 * 
	 * @param 	qestnrId 	String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * qestnrQesitmId attribute 를 리턴한다.
	 * 
	 * @return the qestnrQesitmId
	 */
	public String getQestnrQesitmId() {
		return qestnrQesitmId;
	}
	/**
	 * qestnrQesitmId attribute 값을 설정한다.
	 * 
	 * @param 	qestnrQesitmId 	String
	 */
	public void setQestnrQesitmId(String qestnrQesitmId) {
		this.qestnrQesitmId = qestnrQesitmId;
	}

	/**
	 * qustnrIemId attribute 를 리턴한다.
	 * 
	 * @return 	the qustnrIemId
	 */
	public String getQustnrIemId() {
		return qustnrIemId;
	}
	/**
	 * qustnrIemId attribute 값을 설정한다.
	 * 
	 * @param 	qustnrIemId 	String
	 */
	public void setQustnrIemId(String qustnrIemId) {
		this.qustnrIemId = qustnrIemId;
	}

	/**
	 * respondAnswerCn attribute 를 리턴한다.
	 * 
	 * @return 	the respondAnswerCn
	 */
	public String getRespondAnswerCn() {
		return respondAnswerCn;
	}
	/**
	 * respondAnswerCn attribute 값을 설정한다.
	 * 
	 * @param  respondAnswerCn String
	 */
	public void setRespondAnswerCn(String respondAnswerCn) {
		this.respondAnswerCn = respondAnswerCn;
	}

	/**
	 * etcAnswerCn attribute 를 리턴한다.
	 * 
	 * @return the etcAnswerCn
	 */
	public String getEtcAnswerCn() {
		return etcAnswerCn;
	}
	/**
	 * etcAnswerCn attribute 값을 설정한다.
	 * 
	 * @param etcAnswerCn String
	 */
	public void setEtcAnswerCn(String etcAnswerCn) {
		this.etcAnswerCn = etcAnswerCn;
	}

	/**
	 * respondNm attribute 를 리턴한다.
	 * 
	 * @return the respondNm
	 */
	public String getRespondNm() {
		return respondNm;
	}
	/**
	 * respondNm attribute 값을 설정한다.
	 * 
	 * @param respondNm String
	 */
	public void setRespondNm(String respondNm) {
		this.respondNm = respondNm;
	}

	// helper
	/**
	 * qestnrSj attribute 를 리턴한다.
	 * 
	 * @return the qestnrSj
	 */
	public String getQestnrSj() {
		return qestnrSj;
	}
	/**
	 * qestnrSj attribute 값을 설정한다.
	 * 
	 * @param qestnrSj String
	 */
	public void setQestnrSj(String qestnrSj) {
		this.qestnrSj = qestnrSj;
	}

	/**
	 * qestnCn attribute 를 리턴한다.
	 * 
	 * @return the qestnCn
	 */
	public String getQestnCn() {
		return qestnCn;
	}
	/**
	 * qestnCn attribute 값을 설정한다.
	 * 
	 * @param qestnCn String
	 */
	public void setQestnCn(String qestnCn) {
		this.qestnCn = qestnCn;
	}

	/**
	 * qestnTyCode attribute 를 리턴한다.
	 * 
	 * @return the qestnTyCode
	 */
	public String getQestnTyCode() {
		return qestnTyCode;
	}
	/**
	 * qestnTyCode attribute 값을 설정한다.
	 * 
	 * @param qestnTyCode String
	 */
	public void setQestnTyCode(String qestnTyCode) {
		this.qestnTyCode = qestnTyCode;
	}

	/**
	 * qustnrIemCn attribute 를 리턴한다.
	 * 
	 * @return the qustnrIemCn
	 */
	public String getQustnrIemCn() {
		return qustnrIemCn;
	}
	/**
	 * qustnrIemCn attribute 값을 설정한다.
	 * 
	 * @param qustnrIemCn String
	 */
	public void setQustnrIemCn(String qustnrIemCn) {
		this.qustnrIemCn = qustnrIemCn;
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
	 * @param trgetId	the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

	/**
	 * searchMode attribute 를 리턴한다.
	 * 
	 * @return the searchMode
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * searchMode attribute 값을 설정한다.
	 * 
	 * @param searchMode String
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

}
