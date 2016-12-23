package aramframework.com.sym.ccm.adc.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 행정코드 모델 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class AdministCodeVO extends BaseVO {

	// domain
	/** 행정구역구분 */
	private String administZoneSe = "";

	/** 행정구역코드 */
	private String administZoneCode = "";

	/** 사용여부 */
	private String useAt = "";

	/** 행정구역명 */
	private String administZoneNm = "";

	/** 상위행정구역코드 */
	private String upperAdministZoneCode = "";

	/** 생성일자 */
	private String creatDe = "";

	/** 폐기일자 */
	private String ablDe = "";

	// helper
	/** 상위행정구역명 */
	private String upperAdministZoneNm = "";

	// domain
	/**
	 * administZoneSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdministZoneSe() {
		return administZoneSe;
	}
	/**
	 * administZoneSe attribute 값을 설정한다.
	 * 
	 * @param administZoneSe
	 *            String
	 */
	public void setAdministZoneSe(String administZoneSe) {
		this.administZoneSe = administZoneSe;
	}

	/**
	 * administZoneCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdministZoneCode() {
		return administZoneCode;
	}
	/**
	 * administZoneCode attribute 값을 설정한다.
	 * 
	 * @param administZoneCode
	 *            String
	 */
	public void setAdministZoneCode(String administZoneCode) {
		this.administZoneCode = administZoneCode;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * administZoneNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAdministZoneNm() {
		return administZoneNm;
	}
	/**
	 * administZoneNm attribute 값을 설정한다.
	 * 
	 * @param administZoneNm
	 *            String
	 */
	public void setAdministZoneNm(String administZoneNm) {
		this.administZoneNm = administZoneNm;
	}

	/**
	 * upperAdministZoneCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUpperAdministZoneCode() {
		return upperAdministZoneCode;
	}
	/**
	 * upperAdministZoneCode attribute 값을 설정한다.
	 * 
	 * @param upperAdministZoneCode
	 *            String
	 */
	public void setUpperAdministZoneCode(String upperAdministZoneCode) {
		this.upperAdministZoneCode = upperAdministZoneCode;
	}

	/**
	 * creatDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCreatDe() {
		return creatDe;
	}
	/**
	 * creatDe attribute 값을 설정한다.
	 * 
	 * @param creatDe
	 *            String
	 */
	public void setCreatDe(String creatDe) {
		this.creatDe = creatDe;
	}

	/**
	 * ablDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAblDe() {
		return ablDe;
	}
	/**
	 * ablDe attribute 값을 설정한다.
	 * 
	 * @param ablDe
	 *            String
	 */
	public void setAblDe(String ablDe) {
		this.ablDe = ablDe;
	}

	// helper
	/**
	 * upperAdministZoneNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUpperAdministZoneNm() {
		return upperAdministZoneNm;
	}
	/**
	 * upperAdministZoneNm attribute 값을 설정한다.
	 * 
	 * @param upperAdministZoneNm
	 *            String
	 */
	public void setUpperAdministZoneNm(String upperAdministZoneNm) {
		this.upperAdministZoneNm = upperAdministZoneNm;
	}

}
