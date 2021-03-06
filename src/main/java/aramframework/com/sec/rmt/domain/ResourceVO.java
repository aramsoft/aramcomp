package aramframework.com.sec.rmt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 자원관리에 대한 model 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ResourceVO extends BaseVO {

	// domain
	/** 자원코드 */
	private String resourceCode;
	
	/** 자원명 */
	private String resourceNm;
	
	/** 자원패턴 */
	private String resourcePttrn;
	
	/** 자원설명 */
	private String resourceDc;
	
	/** 자원타입 */
	private String resourceTy;
	
	/** 자원Sort */
	private String resourceSort;
	
	/** 자원등록일시 */
	private String resourceCreatDe;
	
	// domain
	/**
	 * resourceCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceCode() {
		return resourceCode;
	}
	/**
	 * resourceCode attribute 값을 설정한다.
	 * 
	 * @param resourceCode
	 *            String
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**
	 * resourceNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceNm() {
		return resourceNm;
	}
	/**
	 * resourceNm attribute 값을 설정한다.
	 * 
	 * @param resourceNm
	 *            String
	 */
	public void setResourceNm(String resourceNm) {
		this.resourceNm = resourceNm;
	}

	/**
	 * resourcePttrn attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourcePttrn() {
		return resourcePttrn;
	}
	/**
	 * resourcePttrn attribute 값을 설정한다.
	 * 
	 * @param resourcePttrn
	 *            String
	 */
	public void setResourcePttrn(String resourcePttrn) {
		this.resourcePttrn = resourcePttrn;
	}

	/**
	 * resourceDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceDc() {
		return resourceDc;
	}
	/**
	 * resourceDc attribute 값을 설정한다.
	 * 
	 * @param resourceDc
	 *            String
	 */
	public void setResourceDc(String resourceDc) {
		this.resourceDc = resourceDc;
	}

	/**
	 * resourceTy attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceTy() {
		return resourceTy;
	}
	/**
	 * resourceTy attribute 값을 설정한다.
	 * 
	 * @param resourceTy
	 *            String
	 */
	public void setResourceTy(String resourceTy) {
		this.resourceTy = resourceTy;
	}

	/**
	 * resourceSort attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceSort() {
		return resourceSort;
	}
	/**
	 * resourceSort attribute 값을 설정한다.
	 * 
	 * @param resourceSort
	 *            String
	 */
	public void setResourceSort(String resourceSort) {
		this.resourceSort = resourceSort;
	}

	/**
	 * resourceCreatDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getResourceCreatDe() {
		return resourceCreatDe;
	}
	/**
	 * resourceCreatDe attribute 값을 설정한다.
	 * 
	 * @param resourceCreatDe
	 *            String
	 */
	public void setResourceCreatDe(String resourceCreatDe) {
		this.resourceCreatDe = resourceCreatDe;
	}

}