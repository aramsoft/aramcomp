package aramframework.com.uss.olp.qmc.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 설문관리 VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */

public class QustnrManageVO extends BaseVO  {

	// domain
	/** 설문지ID */
	private String qestnrId = "";

	/** 설문템플릿 */
	private String qestnrTmplatId = "";

	/** 설문제목 */
	private String qestnrSj = "";

	/** 설문목적 */
	private String qestnrPurps = "";

	/** 설문작성안내내용 */
	private String qestnrWritngGuidanceCn = "";

	/** 설문대상 */
	private String qestnrTrget = "";

	/** 설문시작일자 */
	private String qestnrBeginDe = "";

	/** 설문종료일자 */
	private String qestnrEndDe = "";

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 설문템플릿유형 */
	private String qestnrTmplatTy = "";

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
	 * qestnrTmplatId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatId() {
		return qestnrTmplatId;
	}
	/**
	 * qestnrTmplatId attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatId String
	 */
	public void setQestnrTmplatId(String qestnrTmplatId) {
		this.qestnrTmplatId = qestnrTmplatId;
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
	 * qestnrPurps attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrPurps() {
		return qestnrPurps;
	}
	/**
	 * qestnrPurps attribute 값을 설정한다.
	 * 
	 * @return qestnrPurps String
	 */
	public void setQestnrPurps(String qestnrPurps) {
		this.qestnrPurps = qestnrPurps;
	}

	/**
	 * qestnrWritngGuidanceCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrWritngGuidanceCn() {
		return qestnrWritngGuidanceCn;
	}
	/**
	 * qestnrWritngGuidanceCn attribute 값을 설정한다.
	 * 
	 * @return qestnrWritngGuidanceCn String
	 */
	public void setQestnrWritngGuidanceCn(String qestnrWritngGuidanceCn) {
		this.qestnrWritngGuidanceCn = qestnrWritngGuidanceCn;
	}

	/**
	 * qestnrTrget attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTrget() {
		return qestnrTrget;
	}
	/**
	 * qestnrTrget attribute 값을 설정한다.
	 * 
	 * @return qestnrTrget String
	 */
	public void setQestnrTrget(String qestnrTrget) {
		this.qestnrTrget = qestnrTrget;
	}

	/**
	 * qestnrBeginDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrBeginDe() {
		return qestnrBeginDe;
	}
	/**
	 * qestnrBeginDe attribute 값을 설정한다.
	 * 
	 * @return qestnrBeginDe String
	 */
	public void setQestnrBeginDe(String qestnrBeginDe) {
		this.qestnrBeginDe = qestnrBeginDe;
	}

	/**
	 * qestnrEndDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrEndDe() {
		return qestnrEndDe;
	}
	/**
	 * qestnrEndDe attribute 값을 설정한다.
	 * 
	 * @return qestnrEndDe String
	 */
	public void setQestnrEndDe(String qestnrEndDe) {
		this.qestnrEndDe = qestnrEndDe;
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

	// helper
	/**
	 * qestnrTmplatTy attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrTmplatTy() {
		return qestnrTmplatTy;
	}
	/**
	 * qestnrTmplatTy attribute 값을 설정한다.
	 * 
	 * @return qestnrTmplatTy String
	 */
	public void setQestnrTmplatTy(String qestnrTmplatTy) {
		this.qestnrTmplatTy = qestnrTmplatTy;
	}

}
