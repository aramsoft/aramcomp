package aramframework.com.uss.olh.wor.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 용어사전정보 VO 클래스
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

public class WordDicaryVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 용어ID */
	private String wordId;

	/** 용어명 */
	private String wordNm;

	/** 영문명 */
	private String engNm;

	/** 용어설명 */
	private String wordDc;

	/** 동의어 */
	private String synonm;

	/* 등록자명 */
	private String emplyrNm;

	/**
	 * wordId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWordId() {
		return wordId;
	}
	/**
	 * wordId attribute 값을 설정한다.
	 * 
	 * @return wordId String
	 */
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	/**
	 * wordNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWordNm() {
		return wordNm;
	}
	/**
	 * wordNm attribute 값을 설정한다.
	 * 
	 * @return wordNm String
	 */
	public void setWordNm(String wordNm) {
		this.wordNm = wordNm;
	}

	/**
	 * engNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEngNm() {
		return engNm;
	}
	/**
	 * engNm attribute 값을 설정한다.
	 * 
	 * @return engNm String
	 */
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}

	/**
	 * wordDc attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getWordDc() {
		return wordDc;
	}
	/**
	 * wordDc attribute 값을 설정한다.
	 * 
	 * @return wordDc String
	 */
	public void setWordDc(String wordDc) {
		this.wordDc = wordDc;
	}

	/**
	 * synonm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSynonm() {
		return synonm;
	}
	/**
	 * synonm attribute 값을 설정한다.
	 * 
	 * @return synonm String
	 */
	public void setSynonm(String synonm) {
		this.synonm = synonm;
	}

	/**
	 * emplyrNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}
	/**
	 * emplyrNm attribute 값을 설정한다.
	 * 
	 * @return emplyrNm String
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

}
