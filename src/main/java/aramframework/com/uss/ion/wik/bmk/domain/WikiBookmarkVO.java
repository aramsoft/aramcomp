package aramframework.com.uss.ion.wik.bmk.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 위키북마크 Model and VO Class 구현
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

public class WikiBookmarkVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 위키 북마크 아이디 */
	private String wikiBkmkId;

	/** 사용자ID */
	private String usid;

	/** 사용자이름 */
	private String usnm;

	/** 북마크명 */
	private String wikiBkmkNm;

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

}
