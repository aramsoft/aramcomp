package aramframework.com.uss.ion.mtg.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 개요 - 회의실예약에 대한 Vo 클래스를 정의한다.
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

public class MtgPlaceResveVO extends SearchVO  {

	// domain
	/** 예약ID	 */
	private String resveId;

	/** 회의실코드	 */
	private String mtgPlaceId;

	/** 회의제목	 */
	private String mtgSj;

	/** 예약자ID	 */
	private String resveManId;

	/** 예약일자	 */
	private String resveDe;

	/** 예약시작시간	 */
	private String resveBeginTm;

	/** 예약종료시간	 */
	private String resveEndTm;

	/** 참석인원	 */
	private int atndncNmpr;

	/** 회의내용	 */
	private String mtgCn;

	// helper
	/** 출력 변수 4	 */
	private String resveManNm;

	/** 출력 변수 5	 */
	private String resevOrgnztNm;

	/** ID 변수	 */
	private String usidTemp;

	/** 대상 아이디 */
	private String trgetId = "";

	// domain
	/**
	 * @return the resveId
	 */
	public String getResveId() {
		return resveId;
	}
	/**
	 * @param resveId
	 *            the resveId to set
	 */
	public void setResveId(String resveId) {
		this.resveId = resveId;
	}

	/**
	 * @return the mtgPlaceId
	 */
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}
	/**
	 * @param mtgPlaceId
	 *            the mtgPlaceId to set
	 */
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}

	/**
	 * @return the mtgSj
	 */
	public String getMtgSj() {
		return mtgSj;
	}
	/**
	 * @param mtgSj
	 *            the mtgSj to set
	 */
	public void setMtgSj(String mtgSj) {
		this.mtgSj = mtgSj;
	}

	/**
	 * @return the resveManId
	 */
	public String getResveManId() {
		return resveManId;
	}

	/**
	 * @param resveManId
	 *            the resveManId to set
	 */
	public void setResveManId(String resveManId) {
		this.resveManId = resveManId;
	}

	/**
	 * @return the resveDe
	 */
	public String getResveDe() {
		return resveDe;
	}
	/**
	 * @param resveDe
	 *            the resveDe to set
	 */
	public void setResveDe(String resveDe) {
		this.resveDe = resveDe;
	}

	/**
	 * @return the resveBeginTm
	 */
	public String getResveBeginTm() {
		return resveBeginTm;
	}
	/**
	 * @param resveBeginTm
	 *            the resveBeginTm to set
	 */
	public void setResveBeginTm(String resveBeginTm) {
		this.resveBeginTm = resveBeginTm;
	}

	/**
	 * @return the resveEndTm
	 */
	public String getResveEndTm() {
		return resveEndTm;
	}
	/**
	 * @param resveEndTm
	 *            the resveEndTm to set
	 */
	public void setResveEndTm(String resveEndTm) {
		this.resveEndTm = resveEndTm;
	}

	/**
	 * @return the atndncNmpr
	 */
	public int getAtndncNmpr() {
		return atndncNmpr;
	}
	/**
	 * @param atndncNmpr
	 *            the atndncNmpr to set
	 */
	public void setAtndncNmpr(int atndncNmpr) {
		this.atndncNmpr = atndncNmpr;
	}

	/**
	 * @return the mtgCn
	 */
	public String getMtgCn() {
		return mtgCn;
	}
	/**
	 * @param mtgCn
	 *            the mtgCn to set
	 */
	public void setMtgCn(String mtgCn) {
		this.mtgCn = mtgCn;
	}

	// helper
	/**
	 * @return the resveManNm
	 */
	public String getResveManNm() {
		return resveManNm;
	}
	/**
	 * @param resveManNm
	 *            the resveManNm to set
	 */
	public void setResveManNm(String resveManNm) {
		this.resveManNm = resveManNm;
	}

	/**
	 * @return the resevOrgnztNm
	 */
	public String getResevOrgnztNm() {
		return resevOrgnztNm;
	}
	/**
	 * @param resevOrgnztNm
	 *            the resevOrgnztNm to set
	 */
	public void setResevOrgnztNm(String resevOrgnztNm) {
		this.resevOrgnztNm = resevOrgnztNm;
	}

	/**
	 * @return the usidTemp
	 */
	public String getUsidTemp() {
		return usidTemp;
	}
	/**
	 * @param usidTemp
	 *            the usidTemp to set
	 */
	public void setUsidTemp(String usidTemp) {
		this.usidTemp = usidTemp;
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

}
