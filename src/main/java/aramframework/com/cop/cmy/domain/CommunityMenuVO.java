package aramframework.com.cop.cmy.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 커뮤니티 메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class CommunityMenuVO extends BaseVO {

	// domain
	/** 대상 아이디 */
	private String trgetId = "";

	/** 메뉴번호 */
	private int menuNo;

	/** 메뉴한글명 */
	private String menuNm;

	/** 프로그램파일명 */
	private String progrmFileNm;

	/** 메뉴설명 */
	private String menuDc;

	/** 사용여부 */
	private String useAt;

	/** 관리자메뉴여부 */
	private String mgrAt;

	/** 바로가기 URL */
	private String directUrl;

	/** 상위메뉴여부 */
	private String topMenuAt;

	/** 메뉴별명 */
	private String menuAlias;

	// helper
	/** 새메뉴번호 */
	private int newMenuNo;
	
	// domain
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
	 * menuNo attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getMenuNo() {
		return menuNo;
	}
	/**
	 * menuNo attribute 값을 설정한다.
	 * 
	 * @param menuNo
	 *            int
	 */
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * menuNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * menuNm attribute 값을 설정한다.
	 * 
	 * @param menuNm
	 *            String
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * progrmFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * 
	 * @param progrmFileNm
	 *            String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	/**
	 * menuDc attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuDc() {
		return menuDc;
	}
	/**
	 * menuDc attribute 값을 설정한다.
	 * 
	 * @param menuDc
	 *            String
	 */
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	/**
	 * useAt attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * mgrAt attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMgrAt() {
		return mgrAt;
	}
	/**
	 * mgrAt attribute 값을 설정한다.
	 * 
	 * @param mgrAt
	 *            String
	 */
	public void setMgrAt(String mgrAt) {
		this.mgrAt = mgrAt;
	}

	/**
	 * directUrl attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getDirectUrl() {
		return directUrl;
	}
	/**
	 * directUrl attribute 값을 설정한다.
	 * 
	 * @param directUrl
	 *            String
	 */
	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

	/**
	 * topMenuAt attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTopMenuAt() {
		return topMenuAt;
	}
	/**
	 * topMenuAt attribute 값을 설정한다.
	 * 
	 * @param topMenuAt
	 *            String
	 */
	public void setTopMenuAt(String topMenuAt) {
		this.topMenuAt = topMenuAt;
	}

	/**
	 * menuAlias attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuAlias() {
		return menuAlias;
	}
	/**
	 * menuAlias attribute 값을 설정한다.
	 * 
	 * @param menuAlias
	 *            String
	 */
	public void setMenuAlias(String menuAlias) {
		this.menuAlias = menuAlias;
	}

	// helper
	/**
	 * newMenuNo attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getNewMenuNo() {
		return newMenuNo;
	}
	/**
	 * newMenuNo attribute 값을 설정한다.
	 * 
	 * @param newMenuNo
	 *            int
	 */
	public void setNewMenuNo(int newMenuNo) {
		this.newMenuNo = newMenuNo;
	}

}