package aramframework.com.cop.clb.service;


import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;
import aramframework.com.cmm.util.WebUtil;

/**
 * 동호회 속성정보를 관리하기 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

public class ClubVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 커뮤니티 아이디 */
	private String cmmntyId = "";

	/** 커뮤니티명 */
	private String cmmntyNm = "";

	/** 동호회 아이디 */
	private String clbId = "";

	/** 동호회 명 */
	private String clbNm = "";

	/** 동호회 소개 */
	private String clbIntrcn = "";

	/** 등록구분코드 */
	private String registSeCode;

	/** 등록구분 코드명 */
	private String registSeCodeNm = "";

	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 템플릿 명 */
	private String tmplatNm = "";

	/** 사용여부 */
	private String useAt = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 사용자명 */
	private String userNm = "";

	/** 게시판 아이디 */
	private String bbsId = "";

	/** 게시판명 */
	private String bbsNm = "";

	/** 제공 URL */
	private String provdUrl = "";

	/** 제공 URL2 */
	private String provdUrl2 = "";

	/**
	 * clbId attribute를 리턴한다.
	 * 
	 * @return the clbId
	 */
	public String getClbId() {
		return clbId;
	}
	/**
	 * clbId attribute 값을 설정한다.
	 * 
	 * @param clbId
	 *            the clbId to set
	 */
	public void setClbId(String clbId) {
		this.clbId = clbId;
		if(clbId.length() != 0)
			this.pathId = WebUtil.getPathId(clbId);
	}

	/**
	 * clbIntrcn attribute를 리턴한다.
	 * 
	 * @return the clbIntrcn
	 */
	public String getClbIntrcn() {
		return clbIntrcn;
	}
	/**
	 * clbIntrcn attribute 값을 설정한다.
	 * 
	 * @param clbIntrcn
	 *            the clbIntrcn to set
	 */
	public void setClbIntrcn(String clbIntrcn) {
		this.clbIntrcn = clbIntrcn;
	}

	/**
	 * clbNm attribute를 리턴한다.
	 * 
	 * @return the clbNm
	 */
	public String getClbNm() {
		return clbNm;
	}
	/**
	 * clbNm attribute 값을 설정한다.
	 * 
	 * @param clbNm
	 *            the clbNm to set
	 */
	public void setClbNm(String clbNm) {
		this.clbNm = clbNm;
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
	 * tmplatId attribute를 리턴한다.
	 * 
	 * @return the tmplatId
	 */
	public String getTmplatId() {
		return tmplatId;
	}
	/**
	 * tmplatId attribute 값을 설정한다.
	 * 
	 * @param tmplatId
	 *            the tmplatId to set
	 */
	public void setTmplatId(String tmplatId) {
		this.tmplatId = tmplatId;
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
	 * emplyrId attribute를 리턴한다.
	 * 
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정한다.
	 * 
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
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
	 * tmplatNm attribute를 리턴한다.
	 * 
	 * @return the tmplatNm
	 */
	public String getTmplatNm() {
		return tmplatNm;
	}
	/**
	 * tmplatNm attribute 값을 설정한다.
	 * 
	 * @param tmplatNm
	 *            the tmplatNm to set
	 */
	public void setTmplatNm(String tmplatNm) {
		this.tmplatNm = tmplatNm;
	}

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
	}

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
	 * userNm attribute를 리턴한다.
	 * 
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * 
	 * @param userNm
	 *            the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
