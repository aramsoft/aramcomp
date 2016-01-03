package aramframework.com.uss.ion.mtg.service;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 회의실비품에 대한 Vo 클래스를 정의한다.
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

public class MtgPlaceFxtrsVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 회의실코드 */
	private String mtgPlaceId;

	/** 비품코드 */
	private String fxtrsCd;

	/** 비품명 */
	private String fxtrsNm;

	/** 제조사명 */
	private String makrNm;

	/** 가격 */
	private int price;

	/** 수량 */
	private int quantity;

	/**
	 * @return the mtgPlaceCd
	 */
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}
	/**
	 * @param mtgPlaceCd
	 *            the mtgPlaceCd to set
	 */
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}

	/**
	 * @return the fxtrsCd
	 */
	public String getFxtrsCd() {
		return fxtrsCd;
	}
	/**
	 * @param fxtrsCd
	 *            the fxtrsCd to set
	 */
	public void setFxtrsCd(String fxtrsCd) {
		this.fxtrsCd = fxtrsCd;
	}

	/**
	 * @return the fxtrsNm
	 */
	public String getFxtrsNm() {
		return fxtrsNm;
	}
	/**
	 * @param fxtrsNm
	 *            the fxtrsNm to set
	 */
	public void setFxtrsNm(String fxtrsNm) {
		this.fxtrsNm = fxtrsNm;
	}

	/**
	 * @return the makrNm
	 */
	public String getMakrNm() {
		return makrNm;
	}
	/**
	 * @param makrNm
	 *            the makrNm to set
	 */
	public void setMakrNm(String makrNm) {
		this.makrNm = makrNm;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
