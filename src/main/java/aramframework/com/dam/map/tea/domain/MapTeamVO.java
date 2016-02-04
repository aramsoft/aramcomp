package aramframework.com.dam.map.tea.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 지식맵(조직별)에 대한 Vo 클래스를 정의한다.
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

public class MapTeamVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 조직ID */
	private String orgnztId;
	
	/** 조직분류 */
	private String orgnztNm;
	
	/** 분류일자 */
	private String clYmd = "";
	
	/** 지식URL */
	private String knoUrl;
	
	public String getOrgnztId() {
		return orgnztId;
	}
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getClYmd() {
		return clYmd;
	}
	public void setClYmd(String clYmd) {
		this.clYmd = clYmd;
	}

	public String getKnoUrl() {
		return knoUrl;
	}
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