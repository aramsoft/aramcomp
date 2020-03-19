package aramframework.com.cmm.domain;

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

	/** content URL */
	private String contentUrl;

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
	 * contentUrl attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getContentUrl() {
		return contentUrl;
	}
	/**
	 * contentUrl attribute 값을 설정한다.
	 * 
	 * @param directUrl
	 *            String
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

}