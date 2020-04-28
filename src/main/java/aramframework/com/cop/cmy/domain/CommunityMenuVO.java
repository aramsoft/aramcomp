package aramframework.com.cop.cmy.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 커뮤니티 메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CommunityMenuVO extends BaseVO {

	// domain
	/** 대상 아이디 */
	private String trgetId;

	/** 메뉴명 */
	private String menuNm;

	/** 메뉴한글명 */
	private String menuKnm;

	/** 메뉴번호 */
	private String menuPos;

	/** 프로그램파일명 */
	private String progrmFileNm;

	/** 바로가기 URL */
	private String directUrl;

	/** 메뉴설명 */
	private String menuDc;

	/** 상위메뉴여부 */
	private String topMenuAt;

	/** 관리자메뉴여부 */
	private String mgrAt;

	/** 사용여부 */
	private String useAt;

	// helper
	/** 새메뉴명 */
	private String newMenuNm;
	
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
	 *            
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
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
	 *            
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * menuKnm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuKnm() {
		return menuKnm;
	}
	/**
	 * menuKnm attribute 값을 설정한다.
	 * 
	 * @param menuKnm
	 *            
	 */
	public void setMenuKnm(String menuKnm) {
		this.menuKnm = menuKnm;
	}

	/**
	 * menuPos attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuPos() {
		return menuPos;
	}
	/**
	 * menuPos attribute 값을 설정한다.
	 * 
	 * @param menuPos
	 *            
	 */
	public void setMenuPos(String menuPos) {
		this.menuPos = menuPos;
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

	// helper
	/**
	 * newMenuNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getNewMenuNm() {
		return newMenuNm;
	}
	/**
	 * newMenuNo attribute 값을 설정한다.
	 * 
	 * @param newMenuNo
	 *            String
	 */
	public void setNewMenuNm(String newMenuNm) {
		this.newMenuNm = newMenuNm;
	}

}