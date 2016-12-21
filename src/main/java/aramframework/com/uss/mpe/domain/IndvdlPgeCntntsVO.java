package aramframework.com.uss.mpe.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 마이페이지 컨텐츠에 대한 VO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class IndvdlPgeCntntsVO extends BaseVO {

	// domain
	/** 컨텐츠 아이디 */
	private String cntntsId;
	
	/** 컨텐츠 명 */
	private String cntntsNm;
	
	/** 컨텐츠 상세보기 URL */
	private String cntcUrl;
	
	/** 컨텐츠 사용 여부 */
	private String cntntsUseAt;

	/** 컨텐츠 URL */	
	private String cntntsLinkUrl;
	
	/** 컨텐츠 설명 */
	private String cntntsDc;
	
	// helper
	/** 사용자 아이디 */
	private String userId;
	
	// domain
	public String getCntntsId() {
		return cntntsId;
	}
	public void setCntntsId(String cntntsId) {
		this.cntntsId = cntntsId;
	}

	public String getCntntsNm() {
		return cntntsNm;
	}
	public void setCntntsNm(String cntntsNm) {
		this.cntntsNm = cntntsNm;
	}

	public String getCntcUrl() {
		return cntcUrl;
	}
	public void setCntcUrl(String cntcUrl) {
		this.cntcUrl = cntcUrl;
	}

	public String getCntntsUseAt() {
		return cntntsUseAt;
	}
	public void setCntntsUseAt(String cntntsUseAt) {
		this.cntntsUseAt = cntntsUseAt;
	}
	
	public String getCntntsLinkUrl() {
		return cntntsLinkUrl;
	}
	public void setCntntsLinkUrl(String cntntsLinkUrl) {
		this.cntntsLinkUrl = cntntsLinkUrl;
	}

	public String getCntntsDc() {
		return cntntsDc;
	}
	public void setCntntsDc(String cntntsDc) {
		this.cntntsDc = cntntsDc;
	}

	// helper
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}