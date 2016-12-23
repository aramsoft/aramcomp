package aramframework.com.uss.mpe.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 마이페이지에 대한 VO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class IndvdlPgeConfVO extends BaseVO {

	// domain
	/**	사용자아이디 */
	private String userId;
	
	/** 타이틀 바 색상 */
	private String titleBarColor;
	
	/** 정렬방식 */
	private String sortMthd;
	
	/** 정렬개수 */
	private int sortCnt;

	// helper
	/** 정렬별 라인 카운트 */
	private int sortLineCnt;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitleBarColor() {
		return titleBarColor;
	}
	public void setTitleBarColor(String titleBarColor) {
		this.titleBarColor = titleBarColor;
	}

	public String getSortMthd() {
		return sortMthd;
	}
	public void setSortMthd(String sortMthd) {
		this.sortMthd = sortMthd;
	}

	public int getSortCnt() {
		return sortCnt;
	}
	public void setSortCnt(int sortCnt) {
		this.sortCnt = sortCnt;
	}

	// helper
	public int getSortLineCnt() {
		return sortLineCnt;
	}
	public void setSortLineCnt(int sortLineCnt) {
		this.sortLineCnt = sortLineCnt;
	}
	
}