package aramframework.com.sym.ccm.zip.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 우편번호 모델 클래스
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

public class ZipVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 우편번호 */
	private String zip = "";

	/** 일련번호 */
	private int sn = 0;

	/** 시도명 */
	private String ctprvnNm = "";

	/** 시군구명 */
	private String signguNm = "";

	/** 읍면동명 */
	private String emdNm = "";

	/** 리건물명 */
	private String liBuldNm = "";

    /** 번지동호 */
	private String lnbrDongHo = "";

	/**
	 * zip attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * zip attribute 값을 설정한다.
	 * 
	 * @param zip
	 *            String
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * sn attribute 를 리턴한다.
	 * 
	 * @return int
	 */
	public int getSn() {
		return sn;
	}
	/**
	 * sn attribute 값을 설정한다.
	 * 
	 * @param sn
	 *            int
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}

	/**
	 * ctprvnNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCtprvnNm() {
		return ctprvnNm;
	}
	/**
	 * ctprvnNm attribute 값을 설정한다.
	 * 
	 * @param ctprvnNm
	 *            String
	 */
	public void setCtprvnNm(String ctprvnNm) {
		this.ctprvnNm = ctprvnNm;
	}

	/**
	 * signguNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSignguNm() {
		return signguNm;
	}
	/**
	 * signguNm attribute 값을 설정한다.
	 * 
	 * @param signguNm
	 *            String
	 */
	public void setSignguNm(String signguNm) {
		this.signguNm = signguNm;
	}

	/**
	 * emdNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmdNm() {
		return emdNm;
	}
	/**
	 * emdNm attribute 값을 설정한다.
	 * 
	 * @param emdNm
	 *            String
	 */
	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}

	/**
	 * liBuldNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getLiBuldNm() {
		return liBuldNm;
	}
	/**
	 * liBuldNm attribute 값을 설정한다.
	 * 
	 * @param liBuldNm
	 *            String
	 */
	public void setLiBuldNm(String liBuldNm) {
		this.liBuldNm = liBuldNm;
	}

	/**
	 * lnbrDongHo attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getLnbrDongHo() {
		return lnbrDongHo;
	}
	/**
	 * lnbrDongHo attribute 값을 설정한다.
	 * 
	 * @param lnbrDongHo
	 *            String
	 */
	public void setLnbrDongHo(String lnbrDongHo) {
		this.lnbrDongHo = lnbrDongHo;
	}

}
