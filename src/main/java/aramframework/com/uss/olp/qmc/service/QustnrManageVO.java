package aramframework.com.uss.olp.qmc.service;

import aramframework.com.cmm.SearchVO;

/**
 * 설문관리 VO Class 구현
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

public class QustnrManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	/** 설문지ID */
	private String qestnrId = "";

	/** 설문제목 */
	private String qestnrSj = "";

	/** 설문목적 */
	private String qestnrPurps = "";

	/** 설문작성안내내용 */
	private String qestnrWritngGuidanceCn = "";

	/** 설문시작일자 */
	private String qestnrBeginDe = "";

	/** 설문종료일자 */
	private String qestnrEndDe = "";

	/** 설문대상 */
	private String qestnrTrget = "";

	/** 설문시작일자 */
	private String qestnrTmplatId = "";

	/** 설문템플릿유형 */
	private String qestnrTmplatTy = "";

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
