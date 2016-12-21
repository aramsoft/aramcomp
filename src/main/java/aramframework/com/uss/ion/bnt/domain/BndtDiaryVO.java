package aramframework.com.uss.ion.bnt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 당직일지에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class BndtDiaryVO extends BaseVO  {

	// domain
	/** 당직ID */
	private String bndtId;

	/** 당직일자 */
	private String bndtDe;

	/** 당직체크구분 */
	private String bndtCeckSe;

	/** 당직체크코드 */
	private String bndtCeckCd;

	/** 당직점검상태 */
	private String chckSttus;

	// helper
	/** 당직체크코드명 */
	private String bndtCeckCdNm;

	/** 당직 year */
	private String year;

	/** 당직 month */
	private String month;

	// domain
	/**
	 * @return the bndtId
	 */
	public String getBndtId() {
		return bndtId;
	}
	/**
	 * @param bndtId
	 *            the bndtId to set
	 */
	public void setBndtId(String bndtId) {
		this.bndtId = bndtId;
	}

	/**
	 * @return the bndtDe
	 */
	public String getBndtDe() {
		return bndtDe;
	}
	/**
	 * @param bndtDe
	 *            the bndtDe to set
	 */
	public void setBndtDe(String bndtDe) {
		this.bndtDe = bndtDe;
	}

	/**
	 * @return the bndtCeckSe
	 */
	public String getBndtCeckSe() {
		return bndtCeckSe;
	}
	/**
	 * @param bndtCeckSe
	 *            the bndtCeckSe to set
	 */
	public void setBndtCeckSe(String bndtCeckSe) {
		this.bndtCeckSe = bndtCeckSe;
	}

	/**
	 * @return the bndtCeckCd
	 */
	public String getBndtCeckCd() {
		return bndtCeckCd;
	}
	/**
	 * @param bndtCeckCd
	 *            the bndtCeckCd to set
	 */
	public void setBndtCeckCd(String bndtCeckCd) {
		this.bndtCeckCd = bndtCeckCd;
	}

	/**
	 * @return the chckSttus
	 */
	public String getChckSttus() {
		return chckSttus;
	}
	/**
	 * @param chckSttus
	 *            the chckSttus to set
	 */
	public void setChckSttus(String chckSttus) {
		this.chckSttus = chckSttus;
	}

	// helper
	/**
	 * @return the bndtCeckCdNm
	 */
	public String getBndtCeckCdNm() {
		return bndtCeckCdNm;
	}
	/**
	 * @param bndtCeckCdNm
	 *            the bndtCeckCdNm to set
	 */
	public void setBndtCeckCdNm(String bndtCeckCdNm) {
		this.bndtCeckCdNm = bndtCeckCdNm;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

}
