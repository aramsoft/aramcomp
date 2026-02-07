package aramframework.com.cop.cmy.domain;

import java.util.List;

import aramframework.cmm.domain.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 커뮤니티 관리를 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommunityVO extends BaseVO {

	// domain
	/** 커뮤니티 아이디 */
	private String cmmntyId = "";

	/** 커뮤니티 명 */
	private String cmmntyNm = "";

	/** 커뮤니티 영문명 */
	private String cmmntyAlias = "";

	/** 커뮤니티 소개 */
	private String cmmntyIntrcn = "";

	/** 사용 여부 */
	private String useAt = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 회원인증 여부 */
	private String memberAt = "";

	/** 커뮤니티 로고 이미지 */
	private byte[] cmmntyLogoImage;

	/**  */
	private String additionalInfo;
	
	// helper
	/** 등록구분 코드명 */
	private String registSeCodeNm = "";

	/** 템플릿 이름 */
	private String tmplatNm = "";

	/** 템플릿 경로 */
	private String tmplatCours = "";

	/** 제공 URL */
	private String provdUrl = "";

	/* temp */
	private String emplyrId = "";
	
	/* for jsp only */
	private List<MenuVO> topMenuList = null;
	private List<MenuVO> mgrMenuList = null;
	private List<MenuVO> subMenuList = null;
	private String curMenuNm = "";

}
