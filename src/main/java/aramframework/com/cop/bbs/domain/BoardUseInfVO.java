package aramframework.com.cop.bbs.domain;

import aramframework.cmm.domain.BaseVO;
import aramframework.cmm.util.WebUtil;

/**
 * 게시판의 이용정보를 관리하기 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
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

	/** 제공 URL2 */
	private String provdUrl2 = "";
	
	// domain
	/**
	 * bbsId attribute를 리턴한다.
	 * 
	 * @return the bbsId
	 */
	public String getBbsId() {
		return bbsId;
	}
	/**
	 * bbsId attribute 값을 설정한다.
	 * 
	 * @param bbsId
	 *            the bbsId to set
	 */
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
		if(bbsId.length() != 0)
			this.setPathId(WebUtil.getPathId(bbsId));
	}

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
	 * useAt attribute를 리턴한다.
	 * 
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * registSeCode attribute를 리턴한다.
	 * 
	 * @return the registSeCode
	 */
	public String getRegistSeCode() {
		return registSeCode;
	}
	/**
	 * registSeCode attribute 값을 설정한다.
	 * 
	 * @param registSeCode
	 *            the registSeCode to set
	 */
	public void setRegistSeCode(String registSeCode) {
		this.registSeCode = registSeCode;
	}

	/**
	 * publicAt attribute를 리턴한다.
	 * 
	 * @return the publicAt
	 */
	public String getPublicAt() {
		return publicAt;
	}
	/**
	 * publicAt attribute 값을 설정한다.
	 * 
	 * @param publicAt
	 *            the publicAt to set
	 */
	public void setPublicAt(String publicAt) {
		this.publicAt = publicAt;
	}

	// helper
	/**
	 * bbsNm attribute를 리턴한다.
	 * 
	 * @return the bbsNm
	 */
	public String getBbsNm() {
		return bbsNm;
	}
	/**
	 * bbsNm attribute 값을 설정한다.
	 * 
	 * @param bbsNm
	 *            the bbsNm to set
	 */
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	/**
	 * bbsTyCode attribute를 리턴한다.
	 * 
	 * @return the bbsTyCode
	 */
	public String getBbsTyCode() {
		return bbsTyCode;
	}
	/**
	 * bbsTyCode attribute 값을 설정한다.
	 * 
	 * @param bbsTyCode
	 *            the bbsTyCode to set
	 */
	public void setBbsTyCode(String bbsTyCode) {
		this.bbsTyCode = bbsTyCode;
	}

	/**
	 * trgetType attribute를 리턴한다.
	 * 
	 * @return the trgetType
	 */
	public String getTrgetType() {
		return trgetType;
	}
	/**
	 * trgetType attribute 값을 설정한다.
	 * 
	 * @param trgetType
	 *            the trgetType to set
	 */
	public void setTrgetType(String trgetType) {
		this.trgetType = trgetType;
	}

	/**
	 * cmmntyId attribute를 리턴한다.
	 * 
	 * @return the cmmntyId
	 */
	public String getCmmntyId() {
		return cmmntyId;
	}
	/**
	 * cmmntyId attribute 값을 설정한다.
	 * 
	 * @param cmmntyId
	 *            the cmmntyId to set
	 */
	public void setCmmntyId(String cmmntyId) {
		this.cmmntyId = cmmntyId;
	}

	/**
	 * cmmntyNm attribute를 리턴한다.
	 * 
	 * @return the cmmntyNm
	 */
	public String getCmmntyNm() {
		return cmmntyNm;
	}
	/**
	 * cmmntyNm attribute 값을 설정한다.
	 * 
	 * @param cmmntyNm
	 *            the cmmntyNm to set
	 */
	public void setCmmntyNm(String cmmntyNm) {
		this.cmmntyNm = cmmntyNm;
	}

	/**
	 * registSeCodeNm attribute를 리턴한다.
	 * 
	 * @return the registSeCodeNm
	 */
	public String getRegistSeCodeNm() {
		return registSeCodeNm;
	}
	/**
	 * registSeCodeNm attribute 값을 설정한다.
	 * 
	 * @param registSeCodeNm
	 *            the registSeCodeNm to set
	 */
	public void setRegistSeCodeNm(String registSeCodeNm) {
		this.registSeCodeNm = registSeCodeNm;
	}

	/**
	 * provdUrl attribute를 리턴한다.
	 * 
	 * @return the provdUrl
	 */
	public String getProvdUrl() {
		return provdUrl;
	}
	/**
	 * provdUrl attribute 값을 설정한다.
	 * 
	 * @param provdUrl
	 *            the provdUrl to set
	 */
	public void setProvdUrl(String provdUrl) {
		this.provdUrl = provdUrl;
	}

	/**
	 * provdUrl2 attribute를 리턴한다.
	 * 
	 * @return the provdUrl2
	 */
	public String getProvdUrl2() {
		return provdUrl2;
	}
	/**
	 * provdUrl2 attribute 값을 설정한다.
	 * 
	 * @param provdUrl2
	 *            the provdUrl2 to set
	 */
	public void setProvdUrl2(String provdUrl2) {
		this.provdUrl2 = provdUrl2;
	}

}
