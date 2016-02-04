package aramframework.com.uss.olh.awm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 행정전문용어사전관리 VO Class 구현
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

public class AdministrationWordVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	// 추가검색
	/** 초성검색 */
	private String choseongA;

	/** 초성검색 */
	private String choseongB;

	/** 초성검색 (구분) */
	private String choseongSe;
	
	// 도메인 정보
	/** 행정용어사전 아이디 */
	private String administWordId;

	/** 행정용어사전 명 */
	private String administWordNm;

	/** 행정용어사전 영문명 */
	private String administWordEngNm;

	/** 행정용어사전 약어 */
	private String administWordAbrv;

	/** 주제영역 */
	private String themaRelm;

	/** 용어구분 */
	private String wordDomn;

	/** 관련표준용어 */
	private String stdWord;

	/** 행정용어사전 정의 */
	private String administWordDf;

	/** 행정용어사전 설명 */
	private String administWordDc;

	// 추가 검색
	/**
	 * choseongA 리턴
	 * 
	 * @return the choseongA
	 */
	public String getChoseongA() {
		return choseongA;
	}
	/**
	 * choseongA 설정
	 * 
	 * @param choseongA
	 *            the choseongA to set
	 */
	public void setChoseongA(String choseongA) {
		this.choseongA = choseongA;
	}

	/**
	 * choseongB 리턴
	 * 
	 * @return the choseongB
	 */
	public String getChoseongB() {
		return choseongB;
	}
	/**
	 * choseongB 설정
	 * 
	 * @param choseongB
	 *            the choseongB to set
	 */
	public void setChoseongB(String choseongB) {
		this.choseongB = choseongB;
	}

	/**
	 * choseongSe 리턴
	 * 
	 * @return the choseongSe
	 */
	public String getChoseongSe() {
		return choseongSe;
	}
	/**
	 * choseongSe 설정
	 * 
	 * @param choseongSe
	 *            the choseongSe to set
	 */
	public void setChoseongSe(String choseongSe) {
		this.choseongSe = choseongSe;
	}
	
	// 도메인 정보
	/**
	 * administWordId 리턴
	 * 
	 * @return the administWordId
	 */
	public String getAdministWordId() {
		return administWordId;
	}
	/**
	 * administWordId 설정
	 * 
	 * @param administWordId
	 *            the administWordId to set
	 */
	public void setAdministWordId(String administWordId) {
		this.administWordId = administWordId;
	}

	/**
	 * administWordNm 리턴
	 * 
	 * @return the administWordNm
	 */
	public String getAdministWordNm() {
		return administWordNm;
	}
	/**
	 * administWordNm 설정
	 * 
	 * @param administWordNm
	 *            the administWordNm to set
	 */
	public void setAdministWordNm(String administWordNm) {
		this.administWordNm = administWordNm;
	}

	/**
	 * administWordEngNm 리턴
	 * 
	 * @return the administWordEngNm
	 */
	public String getAdministWordEngNm() {
		return administWordEngNm;
	}
	/**
	 * administWordEngNm 설정
	 * 
	 * @param administWordEngNm
	 *            the administWordEngNm to set
	 */
	public void setAdministWordEngNm(String administWordEngNm) {
		this.administWordEngNm = administWordEngNm;
	}

	/**
	 * administWordAbrv 리턴
	 * 
	 * @return the administWordAbrv
	 */
	public String getAdministWordAbrv() {
		return administWordAbrv;
	}
	/**
	 * administWordAbrv 설정
	 * 
	 * @param administWordAbrv
	 *            the administWordAbrv to set
	 */
	public void setAdministWordAbrv(String administWordAbrv) {
		this.administWordAbrv = administWordAbrv;
	}

	/**
	 * themaRelm 리턴
	 * 
	 * @return the themaRelm
	 */
	public String getThemaRelm() {
		return themaRelm;
	}
	/**
	 * themaRelm 설정
	 * 
	 * @param themaRelm
	 *            the themaRelm to set
	 */
	public void setThemaRelm(String themaRelm) {
		this.themaRelm = themaRelm;
	}

	/**
	 * wordDomn 리턴
	 * 
	 * @return the wordDomn
	 */
	public String getWordDomn() {
		return wordDomn;
	}
	/**
	 * wordDomn 설정
	 * 
	 * @param wordDomn
	 *            the wordDomn to set
	 */
	public void setWordDomn(String wordDomn) {
		this.wordDomn = wordDomn;
	}

	/**
	 * stdWord 리턴
	 * 
	 * @return the stdWord
	 */
	public String getStdWord() {
		return stdWord;
	}
	/**
	 * stdWord 설정
	 * 
	 * @param stdWord
	 *            the stdWord to set
	 */
	public void setStdWord(String stdWord) {
		this.stdWord = stdWord;
	}

	/**
	 * administWordDf 리턴
	 * 
	 * @return the administWordDf
	 */
	public String getAdministWordDf() {
		return administWordDf;
	}
	/**
	 * administWordDf 설정
	 * 
	 * @param administWordDf
	 *            the administWordDf to set
	 */
	public void setAdministWordDf(String administWordDf) {
		this.administWordDf = administWordDf;
	}

	/**
	 * administWordDc 리턴
	 * 
	 * @return the administWordDc
	 */
	public String getAdministWordDc() {
		return administWordDc;
	}
	/**
	 * administWordDc 설정
	 * 
	 * @param administWordDc
	 *            the administWordDc to set
	 */
	public void setAdministWordDc(String administWordDc) {
		this.administWordDc = administWordDc;
	}

}