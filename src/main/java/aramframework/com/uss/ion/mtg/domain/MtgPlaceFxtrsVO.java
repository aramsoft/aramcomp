package aramframework.com.uss.ion.mtg.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 회의실비품에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class MtgPlaceFxtrsVO extends BaseVO {

	// domain
	/** 회의실코드 */
	private String mtgPlaceId;

	/** 비품코드 */
	private String fxtrsCd;

	/** 수량 */
	private int quantity;

	// helper
	/** 비품명 */
	private String fxtrsNm;

	/** 제조사명 */
	private String makrNm;

	/** 가격 */
	private int price;

	// domain
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

	// helper
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

}
