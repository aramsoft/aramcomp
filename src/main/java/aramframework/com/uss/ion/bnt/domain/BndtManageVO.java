package aramframework.com.uss.ion.bnt.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 당직관리에 대한 Vo 클래스를 정의한다.
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

public class BndtManageVO extends SearchVO   {

	private static final long serialVersionUID = 1L;

	/** 당직 year */
	private String year;

	/** 당직 month */
	private String month;

	/** 당직ID */
	private String bndtId;

	/** 당직 bndtUserNm */
	private String bndtUserNm;

	/** 당직 bndtOrgnztNm */
	private String bndtOrgnztNm;

	/** 당직일자 */
	private String bndtDe;

	/** 비고 */
	private String remark;

	/** 당직 bndtDiaryCnt */
	private int bndtDiaryCnt;

	// Excel upload시 사용
	/** 당직 tempBndtId */
	private String tempBndtId;

	/** 당직 tempBndtNm */
	private String tempBndtNm;

	/** 당직 tempOrgnztNm */
	private String tempOrgnztNm;

	/** 당직 dateWeek */
	private int dateWeek;

	/** 당직 tempBndtWeek */
	private String tempBndtWeek;

	/** 당직 tempCount */
	private int tempCount;

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
	 * @return the bndtUserNm
	 */
	public String getBndtUserNm() {
		return bndtUserNm;
	}
	/**
	 * @param bndtUserNm
	 *            the bndtUserNm to set
	 */
	public void setBndtUserNm(String bndtUserNm) {
		this.bndtUserNm = bndtUserNm;
	}

	/**
	 * @return the bndtOrgnztNm
	 */
	public String getBndtOrgnztNm() {
		return bndtOrgnztNm;
	}
	/**
	 * @param bndtOrgnztNm
	 *            the bndtOrgnztNm to set
	 */
	public void setBndtOrgnztNm(String bndtOrgnztNm) {
		this.bndtOrgnztNm =bndtOrgnztNm;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the bndtDiaryCnt
	 */
	public int getBndtDiaryCnt() {
		return bndtDiaryCnt;
	}
	/**
	 * @param bndtDiaryCnt
	 *            the bndtDiaryCnt to set
	 */
	public void setBndtDiaryCnt(int bndtDiaryCnt) {
		this.bndtDiaryCnt =bndtDiaryCnt;
	}

	/**
	 * @return the tempBndtId
	 */
	public String getTempBndtId() {
		return tempBndtId;
	}
	/**
	 * @param tempBndtId
	 *            the tempBndtId to set
	 */
	public void setTempBndtId(String tempBndtId) {
		this.tempBndtId = tempBndtId;
	}

	/**
	 * @return the tempBndtNm
	 */
	public String getTempBndtNm() {
		return tempBndtNm;
	}
	/**
	 * @param tempBndtNm
	 *            the tempBndtNm to set
	 */
	public void setTempBndtNm(String tempBndtNm) {
		this.tempBndtNm = tempBndtNm;
	}

	/**
	 * @return the tempOrgnztNm
	 */
	public String getTempOrgnztNm() {
		return tempOrgnztNm;
	}
	/**
	 * @param tempOrgnztNm
	 *            the tempOrgnztNm to set
	 */
	public void setTempOrgnztNm(String tempOrgnztNm) {
		this.tempOrgnztNm = tempOrgnztNm;
	}

	/**
	 * @return the dateWeek
	 */
	public int getDateWeek() {
		return dateWeek;
	}
	/**
	 * @param dateWeek
	 *            the dateWeek to set
	 */
	public void setDateWeek(int dateWeek) {
		this.dateWeek = dateWeek;
	}

	/**
	 * @return the tempBndtWeek
	 */
	public String getTempBndtWeek() {
		return tempBndtWeek;
	}
	/**
	 * @param tempBndtWeek
	 *            the tempBndtWeek to set
	 */
	public void setTempBndtWeek(String tempBndtWeek) {
		this.tempBndtWeek = tempBndtWeek;
	}

	/**
	 * @return the tempCount
	 */
	public int getTempCount() {
		return tempCount;
	}
	/**
	 * @param tempCount
	 *            the tempCount to set
	 */
	public void setTempCount(int tempCount) {
		this.tempCount = tempCount;
	}

}
