package aramframework.com.uss.olp.qim.service;

import aramframework.com.cmm.SearchVO;

/**
 * 설문항목관리 VO Class 구현
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

public class QustnrItemManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 검색모드설정 */
	private String searchMode = "";

	/** 설문문항 아이디 */
	private String qestnrQesitmId = "";

	/** 질문내용 */
	private String qestnCn = "";

	/** 설문지 아이디 */
	private String qestnrId = "";

	/** 설문제목 */
	private String qestnrSj = "";

	/** 항목순번 */
	private String qustnrIemSn = "";

	/** 설문항목아이디 */
	private String qustnrIemId = "";

	/** 항목내용 */
	private String qustnrIemCn = "";

	/** 키타답변여부 */
	private String etcAnswerAt = "";

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

}
