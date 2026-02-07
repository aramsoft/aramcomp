package aramframework.com.cop.cmy.domain;

import lombok.Data;

/**
 * 커뮤니티 메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
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