package aramframework.com.dam.spe.spe.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 지식전문가에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class KnoSpecialistVO extends BaseVO {

	/** 전문가ID	 */
	private String speId;
	
	/** 지식유형코드 */
	private String knoTypeCd;
	
	/** 승인유형코드 */
	private String appTypeCd;
	
	/** 전문가승인일 */
	private String speConfmDe = "";
	
	/** 전문가설명 */
	private String speExpCn;
	
	// helper
	/** 전문가명 */
	private String userNm;
	
	/** 소속조직ID */
	private String orgnztId;
	
	/** 소속조직명 */
	private String orgnztNm;
	
	/** 승인유형명 */
	private String appTypeNm;
	
	/** 지식유형명 */
	private String knoTypeNm;
	
	// domain
	/**
	 * @return the speId
	 */
	public String getSpeId() {
		return speId;
	}
	/**
	 * @param speId
	 *            the speId to set
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}

	/**
	 * @return the knoTypeCd
	 */
	public String getKnoTypeCd() {
		return knoTypeCd;
	}
	/**
	 * @param knoTypeCd
	 *            the knoTypeCd to set
	 */
	public void setKnoTypeCd(String knoTypeCd) {
		this.knoTypeCd = knoTypeCd;
	}

	/**
	 * @return the appTypeCd
	 */
	public String getAppTypeCd() {
		return appTypeCd;
	}
	/**
	 * @param appTypeCd
	 *            the appTypeCd to set
	 */
	public void setAppTypeCd(String appTypeCd) {
		this.appTypeCd = appTypeCd;
	}

	/**
	 * @return the speConfmDe
	 */
	public String getSpeConfmDe() {
		return speConfmDe;
	}
	/**
	 * @param speConfmDe
	 *            the speConfmDe to set
	 */
	public void setSpeConfmDe(String speConfmDe) {
		this.speConfmDe = speConfmDe;
	}

	/**
	 * @return the speExpCn
	 */
	public String getSpeExpCn() {
		return speExpCn;
	}
	/**
	 * @param speExpCn
	 *            the speExpCn to set
	 */
	public void setSpeExpCn(String speExpCn) {
		this.speExpCn = speExpCn;
	}

	// helper
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm
	 *            the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId
	 *            the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
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
	 * @return the knoTypeNm
	 */
	public String getKnoTypeNm() {
		return knoTypeNm;
	}
	/**
	 * @param knoTypeNm
	 *            the knoTypeNm to set
	 */
	public void setKnoTypeNm(String knoTypeNm) {
		this.knoTypeNm = knoTypeNm;
	}

	/**
	 * @return the appTypeNm
	 */
	public String getAppTypeNm() {
		return appTypeNm;
	}
	/**
	 * @param appTypeNm
	 *            the appTypeNm to set
	 */
	public void setAppTypeNm(String appTypeNm) {
		this.appTypeNm = appTypeNm;
	}

}