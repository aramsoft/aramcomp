package aramframework.com.cop.clb.service;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 동호회 사용자 정보를 관리하기 위한 VO 클래스
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

public class ClubUserVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 동호회 아이디 */
	private String clbId = "";

	/** 커뮤니티 아이디 */
	private String cmmntyId = "";

	/** 운영인여부 */
	private String oprtrAt = "";

	/** 탈퇴일 */
	private String secsnDe = "";

	/** 가입일 */
	private String srbDe = "";

	/** 사용여부 */
	private String useAt = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 사용자명 */
	private String emplyrNm = "";

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
	 * oprtrAt attribute를 리턴한다.
	 * 
	 * @return the oprtrAt
	 */
	public String getOprtrAt() {
		return oprtrAt;
	}
	/**
	 * oprtrAt attribute 값을 설정한다.
	 * 
	 * @param oprtrAt
	 *            the oprtrAt to set
	 */
	public void setOprtrAt(String oprtrAt) {
		this.oprtrAt = oprtrAt;
	}

	/**
	 * secsnDe attribute를 리턴한다.
	 * 
	 * @return the secsnDe
	 */
	public String getSecsnDe() {
		return secsnDe;
	}
	/**
	 * secsnDe attribute 값을 설정한다.
	 * 
	 * @param secsnDe
	 *            the secsnDe to set
	 */
	public void setSecsnDe(String secsnDe) {
		this.secsnDe = secsnDe;
	}

	/**
	 * srbDe attribute를 리턴한다.
	 * 
	 * @return the srbDe
	 */
	public String getSrbDe() {
		return srbDe;
	}
	/**
	 * srbDe attribute 값을 설정한다.
	 * 
	 * @param srbDe
	 *            the srbDe to set
	 */
	public void setSrbDe(String srbDe) {
		this.srbDe = srbDe;
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
	 * emplyrNm attribute를 리턴한다.
	 * 
	 * @return the emplyrNm
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * emplyrNm attribute 값을 설정한다.
	 * 
	 * @param emplyrNm
	 *            the emplyrNm to set
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
