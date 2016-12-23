package aramframework.com.uss.ion.bnt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 당직체크관리에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class BndtCeckManageVO extends BaseVO  {

	// domain
	/**
	 * 당직체크코드
	 */
	private String bndtCeckCd;

	/**
	 * 당직체크코드명
	 */
	private String bndtCeckCdNm;

	/**
	 * 당직체크구분
	 */
	private String bndtCeckSe;

	/**
	 * 사용여부
	 */
	private String useAt;

	// helper
	/**
	 * 당직체크리스트 Temp변수 1
	 */
	private String bndtCeckSeNm;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchBndtCeckSe;

	/**
	 * 당직체크리스트 당직체크코드 조회조건 변수
	 */
	private String searchBndtCeckCd;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchUseAt;

	// domain
	/**
	 * @return the bndtCeckCd
	 */
	public String getBndtCeckCd() {
		return bndtCeckCd;
	}
	/**
	 * @param bndtCeckCd
	 *            the bndtCeckCd to set
	 */
	public void setBndtCeckCd(String bndtCeckCd) {
		this.bndtCeckCd = bndtCeckCd;
	}

	/**
	 * @return the bndtCeckCdNm
	 */
	public String getBndtCeckCdNm() {
		return bndtCeckCdNm;
	}
	/**
	 * @param bndtCeckCdNm
	 *            the bndtCeckCdNm to set
	 */
	public void setBndtCeckCdNm(String bndtCeckCdNm) {
		this.bndtCeckCdNm = bndtCeckCdNm;
	}

	/**
	 * @return the bndtCeckSe
	 */
	public String getBndtCeckSe() {
		return bndtCeckSe;
	}
	/**
	 * @param bndtCeckSe
	 *            the bndtCeckSe to set
	 */
	public void setBndtCeckSe(String bndtCeckSe) {
		this.bndtCeckSe = bndtCeckSe;
	}

	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * @param useAt
	 *            the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	// helper
	/**
	 * @return the bndtCeckSeNm
	 */
	public String getBndtCeckSeNm() {
		return bndtCeckSeNm;
	}
	/**
	 * @param bndtCeckSeNm
	 *            the bndtCeckSeNm to set
	 */
	public void setBndtCeckSeNm(String bndtCeckSeNm) {
		this.bndtCeckSeNm = bndtCeckSeNm;
	}

	/**
	 * @return the searchUseAt
	 */
	public String getSearchUseAt() {
		return searchUseAt;
	}
	/**
	 * @param searchUseAt
	 *            the searchUseAt to set
	 */
	public void setSearchUseAt(String searchUseAt) {
		this.searchUseAt = searchUseAt;
	}

	/**
	 * @return the searchBndtCeckSe
	 */
	public String getSearchBndtCeckSe() {
		return searchBndtCeckSe;
	}
	/**
	 * @param searchBndtCeckSe
	 *            the searchBndtCeckSe to set
	 */
	public void setSearchBndtCeckSe(String searchBndtCeckSe) {
		this.searchBndtCeckSe = searchBndtCeckSe;
	}

	/**
	 * @return the searchBndtCeckCd
	 */
	public String getSearchBndtCeckCd() {
		return searchBndtCeckCd;
	}
	/**
	 * @param searchBndtCeckCd
	 *            the searchBndtCeckCd to set
	 */
	public void setSearchBndtCeckCd(String searchBndtCeckCd) {
		this.searchBndtCeckCd = searchBndtCeckCd;
	}

}
