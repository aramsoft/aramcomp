package aramframework.com.uss.ion.bnt.service;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 당직일지에 대한 Vo 클래스를 정의한다.
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

public class BndtDiaryVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 당직 year */
	private String year;

	/** 당직 month */
	private String month;

	/** 당직ID */
	private String bndtId;

	/** 당직일자 */
	private String bndtDe;

	/** 당직체크구분 */
	private String bndtCeckSe;

	/** 당직체크코드 */
	private String bndtCeckCd;

	/** 당직체크코드명 */
	private String bndtCeckCdNm;

	/** 당직점검상태 */
	private String chckSttus;

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

}
