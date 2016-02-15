package aramframework.com.uss.ion.mtg.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 회의실관리에 대한 Vo 클래스를 정의한다.
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

public class MtgPlaceManageVO extends SearchVO {

	// domain
	/** 회의실ID */
	private String mtgPlaceId;

	/** 회의실명 */
	private String mtgPlaceNm;

	/** 개방시작시간 */
	private String opnBeginTm;

	/** 개방종료시간 */
	private String opnEndTm;

	/** 수용가능인원 */
	private int aceptncPosblNmpr;

	/** 위치구분 */
	private String lcSe;

	/** 위치상세 */
	private String lcDetail;

	/** 첨부파일 */
	private String atchFileId;

	/** 대상 아이디 */
	private String trgetId = "";

	// helper
	/** 위치구분이름 */
	private String lcSeNm;
	
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
	 * @return the mtgPlaceNm
	 */
	public String getMtgPlaceNm() {
		return mtgPlaceNm;
	}
	/**
	 * @param mtgPlaceNm
	 *            the mtgPlaceNm to set
	 */
	public void setMtgPlaceNm(String mtgPlaceNm) {
		this.mtgPlaceNm = mtgPlaceNm;
	}

	/**
	 * @return the opnBeginTm
	 */
	public String getOpnBeginTm() {
		return opnBeginTm;
	}
	/**
	 * @param opnBeginTm
	 *            the opnBeginTm to set
	 */
	public void setOpnBeginTm(String opnBeginTm) {
		this.opnBeginTm = opnBeginTm;
	}

	/**
	 * @return the opnEndTm
	 */
	public String getOpnEndTm() {
		return opnEndTm;
	}
	/**
	 * @param opnEndTm
	 *            the opnEndTm to set
	 */
	public void setOpnEndTm(String opnEndTm) {
		this.opnEndTm = opnEndTm;
	}

	/**
	 * @return the aceptncPosblNmpr
	 */
	public int getAceptncPosblNmpr() {
		return aceptncPosblNmpr;
	}
	/**
	 * @param aceptncPosblNmpr
	 *            the aceptncPosblNmpr to set
	 */
	public void setAceptncPosblNmpr(int aceptncPosblNmpr) {
		this.aceptncPosblNmpr = aceptncPosblNmpr;
	}

	/**
	 * @return the lcSe
	 */
	public String getLcSe() {
		return lcSe;
	}
	/**
	 * @param lcSe
	 *            the lcSe to set
	 */
	public void setLcSe(String lcSe) {
		this.lcSe = lcSe;
	}

	/**
	 * @return the lcDetail
	 */
	public String getLcDetail() {
		return lcDetail;
	}
	/**
	 * @param lcDetail
	 *            the lcDetail to set
	 */
	public void setLcDetail(String lcDetail) {
		this.lcDetail = lcDetail;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	
	/**
	 * trgetId attribute를 리턴한다.
	 * 
	 * @return the trgetId
	 */
	public String getTrgetId() {
		return trgetId;
	}
	/**
	 * trgetId attribute 값을 설정한다.
	 * 
	 * @param trgetId
	 *            the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

	// helper
	/**
	 * @return the lcSeNm
	 */
	public String getLcSeNm() {
		return lcSeNm;
	}
	/**
	 * @param lcSeNm
	 *            the lcSeNm to set
	 */
	public void setLcSeNm(String lcSeNm) {
		this.lcSeNm = lcSeNm;
	}

}
