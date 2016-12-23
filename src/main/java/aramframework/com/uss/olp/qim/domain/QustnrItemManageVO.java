package aramframework.com.uss.olp.qim.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 설문항목관리 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class QustnrItemManageVO extends BaseVO  {

	// domain
	/** 설문지 아이디 */
	private String qestnrId = "";

	/** 설문문항 아이디 */
	private String qestnrQesitmId = "";

	/** 설문항목아이디 */
	private String qustnrIemId = "";

	/** 항목순번 */
	private String qustnrIemSn = "";

	/** 항목내용 */
	private String qustnrIemCn = "";

	/** 키타답변여부 */
	private String etcAnswerAt = "";

	// helper
	/** 질문내용 */
	private String qestnCn = "";

	/** 설문제목 */
	private String qestnrSj = "";

	/** 검색모드설정 */
	private String searchMode = "";

	// domain
	/**
	 * qestnrId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrId() {
		return qestnrId;
	}
	/**
	 * qestnrId attribute 값을 설정한다.
	 * 
	 * @return qestnrId String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * qestnrQesitmId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrQesitmId() {
		return qestnrQesitmId;
	}
	/**
	 * qestnrQesitmId attribute 값을 설정한다.
	 * 
	 * @return qestnrQesitmId String
	 */
	public void setQestnrQesitmId(String qestnrQesitmId) {
		this.qestnrQesitmId = qestnrQesitmId;
	}

	/**
	 * qustnrIemId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQustnrIemId() {
		return qustnrIemId;
	}
	/**
	 * qustnrIemId attribute 값을 설정한다.
	 * 
	 * @return qustnrIemId String
	 */
	public void setQustnrIemId(String qustnrIemId) {
		this.qustnrIemId = qustnrIemId;
	}

	/**
	 * qustnrIemSn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQustnrIemSn() {
		return qustnrIemSn;
	}
	/**
	 * qustnrIemSn attribute 값을 설정한다.
	 * 
	 * @return iemSn String
	 */
	public void setQustnrIemSn(String qustnrIemSn) {
		this.qustnrIemSn = qustnrIemSn;
	}

	/**
	 * qustnrIemCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQustnrIemCn() {
		return qustnrIemCn;
	}
	/**
	 * qustnrIemCn attribute 값을 설정한다.
	 * 
	 * @return qustnrIemCn String
	 */
	public void setQustnrIemCn(String qustnrIemCn) {
		this.qustnrIemCn = qustnrIemCn;
	}

	/**
	 * etcAnswerAt attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEtcAnswerAt() {
		return etcAnswerAt;
	}
	/**
	 * etcAnswerAt attribute 값을 설정한다.
	 * 
	 * @return etcAnswerAt String
	 */
	public void setEtcAnswerAt(String etcAnswerAt) {
		this.etcAnswerAt = etcAnswerAt;
	}

	// helper
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
	 * qestnrSj attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrSj() {
		return qestnrSj;
	}
	/**
	 * qestnrSj attribute 값을 설정한다.
	 * 
	 * @return qestnrSj String
	 */
	public void setQestnrSj(String qestnrSj) {
		this.qestnrSj = qestnrSj;
	}

	/**
	 * searchMode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * searchMode attribute 값을 설정한다.
	 * 
	 * @return searchMode String
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

}
