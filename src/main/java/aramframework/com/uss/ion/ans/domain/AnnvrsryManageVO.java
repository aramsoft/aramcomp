package aramframework.com.uss.ion.ans.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 기념일관리에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AnnvrsryManageVO extends BaseVO  {

	// domain
	/** 기념일ID */
	private String annId;

	/** 사용자ID */
	private String usid;

	/** 기념일구분 */
	private String annvrsrySe;

	/** 기념일명 */
	private String annvrsryNm;

	/** 기념일자 */
	private String annvrsryDe;

	/** 달력구분 */
	private String cldrSe;

	/** 알림설정 */
	private String annvrsrySetup;

	/** 알림시작일자 */
	private String annvrsryBeginDe;

	/** 메모 */
	private String memo;

	/** 반복구분 */
	private String reptitSe;

	// helper
	/** 사용자ID */
	private String usNm;

	/** 사용자조직 */
	private String orgnztNm;

	/** 기념일구분 */
	private String annvrsrySeNm;

	/** 기념일자 */
	private String annvrsryDeNm;

	/** 알림설정 */
	private String annvrsrySetupNm;
	
	// domain
	/**
	 * @return the annId
	 */
	public String getAnnId() {
		return annId;
	}
	/**
	 * @param annId
	 *            the annId to set
	 */
	public void setAnnId(String annId) {
		this.annId = annId;
	}

	/**
	 * @return the usid
	 */
	public String getUsid() {
		return usid;
	}
	/**
	 * @param usid
	 *            the usid to set
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/**
	 * @return the annvrsrySe
	 */
	public String getAnnvrsrySe() {
		return annvrsrySe;
	}
	/**
	 * @param annvrsrySe
	 *            the annvrsrySe to set
	 */
	public void setAnnvrsrySe(String annvrsrySe) {
		this.annvrsrySe = annvrsrySe;
	}

	/**
	 * @return the annvrsryNm
	 */
	public String getAnnvrsryNm() {
		return annvrsryNm;
	}
	/**
	 * @param annvrsryNm
	 *            the annvrsryNm to set
	 */
	public void setAnnvrsryNm(String annvrsryNm) {
		this.annvrsryNm = annvrsryNm;
	}

	/**
	 * @return the annvrsryDe
	 */
	public String getAnnvrsryDe() {
		return annvrsryDe;
	}
	/**
	 * @param annvrsryDe
	 *            the annvrsryDe to set
	 */
	public void setAnnvrsryDe(String annvrsryDe) {
		this.annvrsryDe = annvrsryDe;
	}

	/**
	 * @return the cldrSe
	 */
	public String getCldrSe() {
		return cldrSe;
	}
	/**
	 * @param cldrSe
	 *            the cldrSe to set
	 */
	public void setCldrSe(String cldrSe) {
		this.cldrSe = cldrSe;
	}

	/**
	 * @return the annvrsrySetup
	 */
	public String getAnnvrsrySetup() {
		return annvrsrySetup;
	}
	/**
	 * @param annvrsrySetup
	 *            the annvrsrySetup to set
	 */
	public void setAnnvrsrySetup(String annvrsrySetup) {
		this.annvrsrySetup = annvrsrySetup;
	}

	/**
	 * @return the annvrsryBeginDe
	 */
	public String getAnnvrsryBeginDe() {
		return annvrsryBeginDe;
	}
	/**
	 * @param annvrsryBeginDe
	 *            the annvrsryBeginDe to set
	 */
	public void setAnnvrsryBeginDe(String annvrsryBeginDe) {
		this.annvrsryBeginDe = annvrsryBeginDe;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the reptitSe
	 */
	public String getReptitSe() {
		return reptitSe;
	}
	/**
	 * @param reptitSe
	 *            the reptitSe to set
	 */
	public void setReptitSe(String reptitSe) {
		this.reptitSe = reptitSe;
	}

	// helper
	/**
	 * @return the usNm
	 */
	public String getUsNm() {
		return usNm;
	}
	/**
	 * @param usNm
	 *            the usNm to set
	 */
	public void setUsNm(String usNm) {
		this.usNm = usNm;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the annvrsrySeNm
	 */
	public String getAnnvrsrySeNm() {
		return annvrsrySeNm;
	}
	/**
	 * @param annvrsrySeNm
	 *            the annvrsrySeNm to set
	 */
	public void setAnnvrsrySeNm(String annvrsrySeNm) {
		this.annvrsrySeNm = annvrsrySeNm;
	}

	/**
	 * @return the annvrsryDeNm
	 */
	public String getAnnvrsryDeNm() {
		return annvrsryDeNm;
	}
	/**
	 * @param annvrsryDeNm
	 *            the annvrsryDeNm to set
	 */
	public void setAnnvrsryDeNm(String annvrsryDeNm) {
		this.annvrsryDeNm = annvrsryDeNm;
	}

	/**
	 * @return the annvrsrySetupNm
	 */
	public String getAnnvrsrySetupNm() {
		return annvrsrySetupNm;
	}
	/**
	 * @param annvrsrySetupNm
	 *            the annvrsrySetupNm to set
	 */
	public void setAnnvrsrySetupNm(String annvrsrySetupNm) {
		this.annvrsrySetupNm = annvrsrySetupNm;
	}

}
