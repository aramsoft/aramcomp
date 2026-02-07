package aramframework.com.cop.cmy.domain;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 커뮤니티 메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
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
	
}