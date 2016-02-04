package aramframework.com.dam.map.mat.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 지식맵(지식유형)에 대한 Vo 클래스를 정의한다.
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

public class MapMaterialVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	
	/** 지식유형코드 */
	private String knoTypeCd = "";
	
	/** 조직ID */
	private String orgnztId = "";
	
	/** 조직명 */
	private String orgnztNm = "";
	
	/** 전문가ID */
	private String speId = "";
	
	/** 지식유형명 */
	private String knoTypeNm = "";
	
	/** 분류일자 */
	private String clYmd = "";
	
	/** 지식URL */
	private String knoUrl = "";
	
	/**
	 * @return the knoTypeCd
	 */
	public String getKnoTypeCd() {
		return knoTypeCd;
	}
	/**
	 * @param knoTypeCd
	 *            the knoTypeCd to set
	 */
	public void setKnoTypeCd(String knoTypeCd) {
		this.knoTypeCd = knoTypeCd;
	}

	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}
	/**
	 * @param orgnztId
	 *            the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the speId
	 */
	public String getSpeId() {
		return speId;
	}
	/**
	 * @param speId
	 *            the speId to set
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}

	/**
	 * @return the knoTypeNm
	 */
	public String getKnoTypeNm() {
		return knoTypeNm;
	}
	/**
	 * @param knoTypeNm
	 *            the knoTypeNm to set
	 */
	public void setKnoTypeNm(String knoTypeNm) {
		this.knoTypeNm = knoTypeNm;
	}

	/**
	 * @return the clYmd
	 */
	public String getClYmd() {
		return clYmd;
	}
	/**
	 * @param clYmd
	 *            the clYmd to set
	 */
	public void setClYmd(String clYmd) {
		this.clYmd = clYmd;
	}

	/**
	 * @return the knoUrl
	 */
	public String getKnoUrl() {
		return knoUrl;
	}
	/**
	 * @param knoUrl
	 *            the knoUrl to set
	 */
	public void setKnoUrl(String knoUrl) {
		this.knoUrl = knoUrl;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}