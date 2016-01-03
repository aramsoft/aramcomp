package aramframework.com.sym.tbm.tbr.service;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 장애정보에 대한 Vo 클래스를 정의한다.
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

public class TroblReqstVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	// 추가 검색 조건

	/** 장애명 조회조건 */
	private String strTroblNm;
	
	/** 장애종류 조회조건 */
	private String strTroblKnd;
	
	/** 처리상태 조회조건 */
	private String strProcessSttus;

	// 도메인 정보
	
	/** 장애 ID */
	private String troblId;
	
	/** 장애 명 */
	private String troblNm;
	
	/** 장애 종류 */
	private String troblKnd;
	
	/** 장애 종류 명 */
	private String troblKndNm;
	
	/** 장애 설명 */
	private String troblDc;
	
	/** 장애 발생 시간 */
	private String troblOccrrncTime;
	
	/** 장애 요청자 명 */
	private String troblRqesterNm;
	
	/** 장애 요청 시간 */
	private String troblRequstTime;
	
	/** 장애 처리 결과 */
	private String troblProcessResult;
	
	/** 장애 처리자 명 */
	private String troblOpetrNm;
	
	/** 장애 처리 시간 */
	private String troblProcessTime;
	
	/** 처리 상태 */
	private String processSttus;
	
	/** 처리 상태명 */
	private String processSttusNm;
	
	/**
	 * @return the strTroblNm
	 */
	public String getStrTroblNm() {
		return strTroblNm;
	}
	/**
	 * @param strTroblNm
	 *            the strTroblNm to set
	 */
	public void setStrTroblNm(String strTroblNm) {
		this.strTroblNm = strTroblNm;
	}

	/**
	 * @return the strTroblKnd
	 */
	public String getStrTroblKnd() {
		return strTroblKnd;
	}
	/**
	 * @param strTroblKnd
	 *            the strTroblKnd to set
	 */
	public void setStrTroblKnd(String strTroblKnd) {
		this.strTroblKnd = strTroblKnd;
	}

	/**
	 * @return the strProcessSttus
	 */
	public String getStrProcessSttus() {
		return strProcessSttus;
	}
	/**
	 * @param strProcessSttus
	 *            the strProcessSttus to set
	 */
	public void setStrProcessSttus(String strProcessSttus) {
		this.strProcessSttus = strProcessSttus;
	}

	/**
	 * @return the troblId
	 */
	public String getTroblId() {
		return troblId;
	}
	/**
	 * @param troblId
	 *            the troblId to set
	 */
	public void setTroblId(String troblId) {
		this.troblId = troblId;
	}

	/**
	 * @return the troblNm
	 */
	public String getTroblNm() {
		return troblNm;
	}
	/**
	 * @param troblNm
	 *            the troblNm to set
	 */
	public void setTroblNm(String troblNm) {
		this.troblNm = troblNm;
	}

	/**
	 * @return the troblKnd
	 */
	public String getTroblKnd() {
		return troblKnd;
	}
	/**
	 * @param troblKnd
	 *            the troblKnd to set
	 */
	public void setTroblKnd(String troblKnd) {
		this.troblKnd = troblKnd;
	}

	/**
	 * @return the troblKndNm
	 */
	public String getTroblKndNm() {
		return troblKndNm;
	}
	/**
	 * @param troblKndNm
	 *            the troblKndNm to set
	 */
	public void setTroblKndNm(String troblKndNm) {
		this.troblKndNm = troblKndNm;
	}

	/**
	 * @return the troblDc
	 */
	public String getTroblDc() {
		return troblDc;
	}
	/**
	 * @param troblDc
	 *            the troblDc to set
	 */
	public void setTroblDc(String troblDc) {
		this.troblDc = troblDc;
	}

	/**
	 * @return the troblOccrrncTime
	 */
	public String getTroblOccrrncTime() {
		return troblOccrrncTime;
	}
	/**
	 * @param troblOccrrncTime
	 *            the troblOccrrncTime to set
	 */
	public void setTroblOccrrncTime(String troblOccrrncTime) {
		this.troblOccrrncTime = troblOccrrncTime;
	}

	/**
	 * @return the troblRqesterNm
	 */
	public String getTroblRqesterNm() {
		return troblRqesterNm;
	}
	/**
	 * @param troblRqesterNm
	 *            the troblRqesterNm to set
	 */
	public void setTroblRqesterNm(String troblRqesterNm) {
		this.troblRqesterNm = troblRqesterNm;
	}

	/**
	 * @return the troblRequstTime
	 */
	public String getTroblRequstTime() {
		return troblRequstTime;
	}
	/**
	 * @param troblRequstTime
	 *            the troblRequstTime to set
	 */
	public void setTroblRequstTime(String troblRequstTime) {
		this.troblRequstTime = troblRequstTime;
	}

	/**
	 * @return the troblProcessResult
	 */
	public String getTroblProcessResult() {
		return troblProcessResult;
	}
	/**
	 * @param troblProcessResult
	 *            the troblProcessResult to set
	 */
	public void setTroblProcessResult(String troblProcessResult) {
		this.troblProcessResult = troblProcessResult;
	}

	/**
	 * @return the troblOpetrNm
	 */
	public String getTroblOpetrNm() {
		return troblOpetrNm;
	}
	/**
	 * @param troblOpetrNm
	 *            the troblOpetrNm to set
	 */
	public void setTroblOpetrNm(String troblOpetrNm) {
		this.troblOpetrNm = troblOpetrNm;
	}

	/**
	 * @return the troblProcessTime
	 */
	public String getTroblProcessTime() {
		return troblProcessTime;
	}
	/**
	 * @param troblProcessTime
	 *            the troblProcessTime to set
	 */
	public void setTroblProcessTime(String troblProcessTime) {
		this.troblProcessTime = troblProcessTime;
	}

	/**
	 * @return the processSttus
	 */
	public String getProcessSttus() {
		return processSttus;
	}
	/**
	 * @param processSttus
	 *            the processSttus to set
	 */
	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}

	/**
	 * @return the processSttusNm
	 */
	public String getProcessSttusNm() {
		return processSttusNm;
	}
	/**
	 * @param processSttusNm
	 *            the processSttusNm to set
	 */
	public void setProcessSttusNm(String processSttusNm) {
		this.processSttusNm = processSttusNm;
	}

}