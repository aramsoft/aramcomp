package aramframework.com.cmm.com.domain;

/**
 * 커뮤니티 메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MenuVO {

	/** 메뉴명 */
	private String menuNm;
	
	/** 메뉴한글명 */
	private String menuKnm;

	/** 메뉴번호 */
	private String menuPos;

	/** direct URL */
	private String directUrl;

	/** program URL */
	private String programUrl;

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
	 * menuKnm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMenuKnm() {
		return menuKnm;
	}
	/**
	 * menuNm attribute 값을 설정한다.
	 * 
	 * @param menuNm
	 *            String
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
	 *            String
	 */
	public void setMenuPos(String menuPos) {
		this.menuPos = menuPos;
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
	 * programUrl attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgramUrl() {
		return programUrl;
	}
	/**
	 * contentUrl attribute 값을 설정한다.
	 * 
	 * @param programUrl
	 *            String
	 */
	public void setProgramUrl(String programUrl) {
		this.programUrl = programUrl;
	}

	/**
	 * contentUrl attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getContentUrl() {
		if( directUrl != null && !"".equals(directUrl)) return directUrl;
		if( programUrl != null && !"".equals(programUrl)) return programUrl;
		return "";
	}

}