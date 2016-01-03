package aramframework.com.uss.ion.nws.service;

import aramframework.com.cmm.SearchVO;

/**
 * 뉴스정보를 처리하는 VO 클래스
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

public class NewsManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 대상 아이디 */
	private String trgetId = "";

	/** 뉴스 ID */
	private String newsId;

	/** 뉴스제목 */
	private String newsSj;

	/** 뉴스내용 */
	private String newsCn;

	/** 뉴스출처 */
	private String newsOrigin;

	/** 게시일자 */
	private String ntceDe;

	/** 첨부파일ID */
	private String atchFileId;

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

	/**
	 * newsId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNewsId() {
		return newsId;
	}
	/**
	 * newsId attribute 값을 설정한다.
	 * 
	 * @return newsId String
	 */
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	/**
	 * newsSj attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNewsSj() {
		return newsSj;
	}
	/**
	 * newsSj attribute 값을 설정한다.
	 * 
	 * @return newsSj String
	 */
	public void setNewsSj(String newsSj) {
		this.newsSj = newsSj;
	}

	/**
	 * newsCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNewsCn() {
		return newsCn;
	}
	/**
	 * newsCn attribute 값을 설정한다.
	 * 
	 * @return newsCn String
	 */
	public void setNewsCn(String newsCn) {
		this.newsCn = newsCn;
	}

	/**
	 * newsOrigin attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNewsOrigin() {
		return newsOrigin;
	}
	/**
	 * newsOrigin attribute 값을 설정한다.
	 * 
	 * @return newsOrigin String
	 */
	public void setNewsOrigin(String newsOrigin) {
		this.newsOrigin = newsOrigin;
	}

	/**
	 * ntceDe attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getNtceDe() {
		return ntceDe;
	}
	/**
	 * ntceDe attribute 값을 설정한다.
	 * 
	 * @return ntceDe String
	 */
	public void setNtceDe(String ntceDe) {
		this.ntceDe = ntceDe;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정한다.
	 * 
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

}
