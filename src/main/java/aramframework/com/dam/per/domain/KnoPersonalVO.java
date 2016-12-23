package aramframework.com.dam.per.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 개인지식정보에 대한 Vo 클래스를 정의한다.
 *
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class KnoPersonalVO extends BaseVO {

	// domain
	/** 지식ID */
	private String knoId;
	
	/** 지식유형코드 */
	private String knoTypeCd;
	
	/** 소속조직ID */
	private String orgnztId;
	
	/** 사용자ID */
	private String emplyrId;
	
	/** 지식명 */
	private String knoNm;
	
	/** 지식내용 */
	private String knoCn;
	
	/** 개인지식공개여부 */
	private String othbcAt;
	
	/** 수집일자 */
	private String colYmd = "";
	
	/** 첨부파일ID */
	private String atchFileId;
	
	// helper
	/** 세션ID */
	private String uniqId;
	
	/** 소속조직명 */
	private String orgnztNm;
	
	/** 사용자명 */
	private String userNm;
	
	/** 지식유형명 */
	private String knoTypeNm;
	
	/** 등록자명 */
	private String regstNm;
	
	// domain
	/**
	 * @return the knoId
	 */
	public String getKnoId() {
		return knoId;
	}
	/**
	 * @param knoId
	 *            the knoId to set
	 */
	public void setKnoId(String knoId) {
		this.knoId = knoId;
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
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * @return the knoNm
	 */
	public String getKnoNm() {
		return knoNm;
	}
	/**
	 * @param knoNm
	 *            the knoNm to set
	 */
	public void setKnoNm(String knoNm) {
		this.knoNm = knoNm;
	}
	
	/**
	 * @return the knoCn
	 */
	public String getKnoCn() {
		return knoCn;
	}
	/**
	 * @param knoCn
	 *            the knoCn to set
	 */
	public void setKnoCn(String knoCn) {
		this.knoCn = knoCn;
	}

	/**
	 * @return the othbcAt
	 */
	public String getOthbcAt() {
		return othbcAt;
	}
	/**
	 * @param othbcAt
	 *            the othbcAt to set
	 */
	public void setOthbcAt(String othbcAt) {
		this.othbcAt = othbcAt;
	}

	/**
	 * @return the colYmd
	 */
	public String getColYmd() {
		return colYmd;
	}
	/**
	 * @param colYmd
	 *            the colYmd to set
	 */
	public void setColYmd(String colYmd) {
		this.colYmd = colYmd;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	// helper
	/**
	 * @return the uniqId
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * @param uniqId
	 *            the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
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
	 * @return the regstNm
	 */
	public String getRegstNm() {
		return regstNm;
	}
	/**
	 * @param regstNm
	 *            the regstNm to set
	 */
	public void setRegstNm(String regstNm) {
		this.regstNm = regstNm;
	}

}