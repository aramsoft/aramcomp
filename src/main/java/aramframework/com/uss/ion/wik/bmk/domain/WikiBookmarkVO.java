package aramframework.com.uss.ion.wik.bmk.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 위키북마크 Model and VO Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class WikiBookmarkVO extends BaseVO {

	// domain
	/** 위키 북마크 아이디 */
	private String wikiBkmkId;

	/** 사용자ID */
	private String usid;

	/** 북마크명 */
	private String wikiBkmkNm;

	// helper
	/** 사용자이름 */
	private String usnm;

	// domain
	/**
	 * @return the wikiBookMarkId
	 */
	public String getWikiBkmkId() {
		return wikiBkmkId;
	}
	/**
	 * @param wikiBookMarkId
	 *            the wikiBookMarkId to set
	 */
	public void setWikiBkmkId(String wikiBookmarkId) {
		this.wikiBkmkId = wikiBookmarkId;
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
	 * @return the bookMark
	 */
	public String getWikiBkmkNm() {
		return wikiBkmkNm;
	}
	/**
	 * @param bookMark
	 *            the bookMark to set
	 */
	public void setWikiBkmkNm(String bookMark) {
		this.wikiBkmkNm = bookMark;
	}

	// helper
	/**
	 * @return the usnm
	 */
	public String getUsnm() {
		return usnm;
	}
	/**
	 * @param usnm
	 *            the usnm to set
	 */
	public void setUsnm(String usnm) {
		this.usnm = usnm;
	}

}
