package aramframework.com.cop.tpl.domain;


import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 템플릿 정보 관리를 위한 VO 클래스
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

public class TemplateInfVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 템플릿 아이디 */
	private String tmplatId = "";

	/** 템플릿 명 */
	private String tmplatNm = "";

	/** 템플릿 경로 */
	private String tmplatCours = "";

	/** 탬플릿 구분코드 */
	private String tmplatSeCode = "";

	/** 템플릿 구분 코드명 */
	private String tmplatSeCodeNm = "";

	/** 구분 유형 */
	private String typeFlag = "";

	/** 사용여부 */
	private String useAt = "";

	/**
	 * tmplatCours attribute를 리턴한다.
	 * 
	 * @return the tmplatCours
	 */
	public String getTmplatCours() {
		return tmplatCours;
	}
	/**
	 * tmplatCours attribute 값을 설정한다.
	 * 
	 * @param tmplatCours
	 *            the tmplatCours to set
	 */
	public void setTmplatCours(String tmplatCours) {
		this.tmplatCours = tmplatCours;
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
	 * tmplatSeCode attribute를 리턴한다.
	 * 
	 * @return the tmplatSeCode
	 */
	public String getTmplatSeCode() {
		return tmplatSeCode;
	}
	/**
	 * tmplatSeCode attribute 값을 설정한다.
	 * 
	 * @param tmplatSeCode
	 *            the tmplatSeCode to set
	 */
	public void setTmplatSeCode(String tmplatSeCode) {
		this.tmplatSeCode = tmplatSeCode;
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
	 * tmplatSeCodeNm attribute를 리턴한다.
	 * 
	 * @return the tmplatSeCodeNm
	 */
	public String getTmplatSeCodeNm() {
		return tmplatSeCodeNm;
	}
	/**
	 * tmplatSeCodeNm attribute 값을 설정한다.
	 * 
	 * @param tmplatSeCodeNm
	 *            the tmplatSeCodeNm to set
	 */
	public void setTmplatSeCodeNm(String tmplatSeCodeNm) {
		this.tmplatSeCodeNm = tmplatSeCodeNm;
	}

	/**
	 * typeFlag attribute를 리턴한다.
	 * 
	 * @return the typeFlag
	 */
	public String getTypeFlag() {
		return typeFlag;
	}
	/**
	 * typeFlag attribute 값을 설정한다.
	 * 
	 * @param typeFlag
	 *            the typeFlag to set
	 */
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
