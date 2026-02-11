package aramframework.com.cop.bbs.domain;

import aramframework.cmm.domain.BaseVO;
import aramframework.cmm.util.WebUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시판의 이용정보를 관리하기 위한 VO 클래스
 * 
 * @since 2014.11.11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BoardUseInfVO extends BaseVO {

	// domin
	/** 게시판 아이디 */
	private String bbsId = "";

	/** 대상 아이디 */
	private String trgetId = "";

	/** 사용여부 */
	private String useAt = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 공개여부 */
	private String publicAt = "";

	// helper
	/** 게시판 명 */
	private String bbsNm = "";

	/** 게시판 유형코드 */
	private String bbsTyCode = "";

	/** 대상 구분 (커뮤니티, 동호회) */
	private String trgetType = "";

	/** 커뮤니티 아이디 */
	private String cmmntyId = "";

	/** 커뮤니티 명 */
	private String cmmntyNm = "";

	/** 등록구분 코드명 */
	private String registSeCodeNm = "";

	/** 제공 URL */
	private String provdUrl = "";

	/** 게시판 단축 아이디	 */
	public String getPathId() {
		return WebUtil.getPathId(this.bbsId);
	}
	
}
