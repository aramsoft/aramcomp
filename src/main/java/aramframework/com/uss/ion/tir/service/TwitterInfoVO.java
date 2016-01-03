package aramframework.com.uss.ion.tir.service;

import aramframework.com.cmm.SearchVO;

/**
 * 트위터수신 Model and VO Class 구현
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

public class TwitterInfoVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 트위터 아이디 */
	private String twitterId;

	/** 트위터 비밀번호 */
	private String twitterPw;

	/** 트위터 이름 */
	private String twitterName;

	/** 트위터 스크린명 */
	private String twitterScreenName;

	/** 트위터 URL */
	private String twitterURL;

	/** 트위터 텍스트 */
	private String twitterText;

	/** 트위터 등록일 */
	private String twitterCreatedAt;

	/** 트위터 프로파일 이미지명 */
	private String twitterProfileImageURL;

	/** 트위터 소스 */
	private String twitterSource;

	/**
	 * @return the twitterId
	 */
	public String getTwitterId() {
		return twitterId;
	}
	/**
	 * @param twitterId
	 *            the twitterId to set
	 */
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	/**
	 * @return the twitterPw
	 */
	public String getTwitterPw() {
		return twitterPw;
	}
	/**
	 * @param twitterPw
	 *            the twitterPw to set
	 */
	public void setTwitterPw(String twitterPw) {
		this.twitterPw = twitterPw;
	}

	/**
	 * @return the twitterName
	 */
	public String getTwitterName() {
		return twitterName;
	}
	/**
	 * @param twitterName
	 *            the twitterName to set
	 */
	public void setTwitterName(String twitterName) {
		this.twitterName = twitterName;
	}

	/**
	 * @return the twitterScreenName
	 */
	public String getTwitterScreenName() {
		return twitterScreenName;
	}

	/**
	 * @param twitterScreenName
	 *            the twitterScreenName to set
	 */
	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}
	/**
	 * @return the twitterURL
	 */
	public String getTwitterURL() {
		return twitterURL;
	}
	/**
	 * @param twitterURL
	 *            the twitterURL to set
	 */
	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}
	
	/**
	 * @return the twitterText
	 */
	public String getTwitterText() {
		return twitterText;
	}
	/**
	 * @param twitterText
	 *            the twitterText to set
	 */
	public void setTwitterText(String twitterText) {
		this.twitterText = twitterText;
	}

	/**
	 * @return the twitterCreatedAt
	 */
	public String getTwitterCreatedAt() {
		return twitterCreatedAt;
	}
	/**
	 * @param twitterCreatedAt
	 *            the twitterCreatedAt to set
	 */
	public void setTwitterCreatedAt(String twitterCreatedAt) {
		this.twitterCreatedAt = twitterCreatedAt;
	}

	/**
	 * @return the twitterProfileImageURL
	 */
	public String getTwitterProfileImageURL() {
		return twitterProfileImageURL;
	}
	/**
	 * @param twitterProfileImageURL
	 *            the twitterProfileImageURL to set
	 */
	public void setTwitterProfileImageURL(String twitterProfileImageURL) {
		this.twitterProfileImageURL = twitterProfileImageURL;
	}

	/**
	 * @return the twitterSource
	 */
	public String getTwitterSource() {
		return twitterSource;
	}
	/**
	 * @param twitterSource
	 *            the twitterSource to set
	 */
	public void setTwitterSource(String twitterSource) {
		this.twitterSource = twitterSource;
	}

}
