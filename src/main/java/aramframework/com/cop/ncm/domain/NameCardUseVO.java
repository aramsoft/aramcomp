package aramframework.com.cop.ncm.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 명함사용자 정보를 관리하기 위한 모델 클래스
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

public class NameCardUseVO extends SearchVO {

	// domain
	/** 명함아이디 */
	private String ncrdId = "";

	/** 등록구분코드 */
	private String registSeCode = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	/** 사용여부 */
	private String useAt = "";

	/** 생성일시 */
	private String creatDt = "";

	// helper
	/** 사용자 명 */
	private String userNm = "";

	/** 명함 이름 */
	private String ncrdNm = "";

	/** 회사명 */
	private String cmpnyNm = "";

	/** 부서명 */
	private String deptNm = "";

	// domain
	/**
	 * ncrdId attribute를 리턴한다.
	 * 
	 * @return the ncrdId
	 */
	public String getNcrdId() {
		return ncrdId;
	}
	/**
	 * ncrdId attribute 값을 설정한다.
	 * 
	 * @param ncrdId
	 *            the ncrdId to set
	 */
	public void setNcrdId(String ncrdId) {
		this.ncrdId = ncrdId;
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
	 * creatDt attribute를 리턴한다.
	 * 
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
	}
	/**
	 * creatDt attribute 값을 설정한다.
	 * 
	 * @param creatDt
	 *            the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	// helper
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
	 * ncrdNm attribute를 리턴한다.
	 * 
	 * @return the ncrdNm
	 */
	public String getNcrdNm() {
		return ncrdNm;
	}
	/**
	 * ncrdNm attribute 값을 설정한다.
	 * 
	 * @param ncrdNm
	 *            the ncrdNm to set
	 */
	public void setNcrdNm(String ncrdNm) {
		this.ncrdNm = ncrdNm;
	}

	/**
	 * cmpnyNm attribute를 리턴한다.
	 * 
	 * @return the cmpnyNm
	 */
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	/**
	 * cmpnyNm attribute 값을 설정한다.
	 * 
	 * @param cmpnyNm
	 *            the cmpnyNm to set
	 */
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}

	/**
	 * deptNm attribute를 리턴한다.
	 * 
	 * @return the deptNm
	 */
	public String getDeptNm() {
		return deptNm;
	}
	/**
	 * deptNm attribute 값을 설정한다.
	 * 
	 * @param deptNm
	 *            the deptNm to set
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
