package aramframework.com.sym.prm.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 프로그램목록 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class ProgrmManageVO extends BaseVO {

	// domain
	/** 프로그램파일명 */
	private String progrmFileNm;
	
	/** 프로그램저장경로 */
	private String progrmStrePath;
	
	/** 프로그램한글명 */
	private String progrmKoreanNm;
	
	/** 프로그램설명 */
	private String progrmDc;
	
	/** URL */
	private String url;
	
	/** 커뮤니티 사용 여부 */
	private String cmmntyUseAt;

	// helper
	/** 커뮤니티 사용 여부 검색 */
	private String searchUseAt;

	// domain
	/**
	 * progrmFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * 
	 * @param progrmFileNm
	 *            String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	/**
	 * progrmStrePath attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmStrePath() {
		return progrmStrePath;
	}
	/**
	 * progrmStrePath attribute 값을 설정한다.
	 * 
	 * @param progrmStrePath
	 *            String
	 */
	public void setProgrmStrePath(String progrmStrePath) {
		this.progrmStrePath = progrmStrePath;
	}

	/**
	 * progrmKoreanNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmKoreanNm() {
		return progrmKoreanNm;
	}
	/**
	 * progrmKoreanNm attribute 값을 설정한다.
	 * 
	 * @param progrmKoreanNm
	 *            String
	 */
	public void setProgrmKoreanNm(String progrmKoreanNm) {
		this.progrmKoreanNm = progrmKoreanNm;
	}

	/**
	 * progrmDc attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getProgrmDc() {
		return progrmDc;
	}
	/**
	 * progrmDc attribute 값을 설정한다.
	 * 
	 * @param progrmDc
	 *            String
	 */
	public void setProgrmDc(String progrmDc) {
		this.progrmDc = progrmDc;
	}

	/**
	 * url attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getURL() {
		return url;
	}
	/**
	 * URL attribute 값을 설정한다.
	 * 
	 * @param URL
	 *            String
	 */
	public void setURL(String URL) {
		this.url = URL;
	}

	/**
	 * cmmntyUseAt attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCmmntyUseAt() {
		return cmmntyUseAt;
	}
	/**
	 * cmmntyUseAt attribute 값을 설정한다.
	 * 
	 * @param cmmntyUseAt
	 *            String
	 */
	public void setCmmntyUseAt(String cmmntyUseAt) {
		this.cmmntyUseAt = cmmntyUseAt;
	}

	// helper
	/**
	 * searchUseAt attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getSearchUseAt() {
		return searchUseAt;
	}
	/**
	 * searchUseAt attribute 값을 설정한다.
	 * 
	 * @param searchUseAt
	 *            String
	 */
	public void setSearchUseAt(String searchUseAt) {
		this.searchUseAt = searchUseAt;
	}
	
}