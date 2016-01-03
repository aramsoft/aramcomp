package aramframework.com.uss.ion.ulm.service;

import aramframework.com.cmm.SearchVO;

/**
 * 통합링크관리 VO Class 구현
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

public class UnityLinkVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 통합링크 아이디 */
	private String unityLinkId;

	/** 통합링크 그룹 */
	private String unityLinkSeCode;

	/** 통합링크 명 */
	private String unityLinkNm;

	/** 통합링크 URL */
	private String unityLinkUrl;

	/** 통합링크 설명 */
	private String unityLinkDc;

	/**
	 * unityLinkId 리턴
	 * 
	 * @return the unityLinkId
	 */
	public String getUnityLinkId() {
		return unityLinkId;
	}
	/**
	 * unityLinkId 설정
	 * 
	 * @param unityLinkId
	 *            the unityLinkId to set
	 */
	public void setUnityLinkId(String unityLinkId) {
		this.unityLinkId = unityLinkId;
	}

	/**
	 * unityLinkSeCode 리턴
	 * 
	 * @return the unityLinkSeCode
	 */
	public String getUnityLinkSeCode() {
		return unityLinkSeCode;
	}
	/**
	 * unityLinkSeCode 설정
	 * 
	 * @param unityLinkSeCode
	 *            the unityLinkSeCode to set
	 */
	public void setUnityLinkSeCode(String unityLinkSeCode) {
		this.unityLinkSeCode = unityLinkSeCode;
	}

	/**
	 * unityLinkNm 리턴
	 * 
	 * @return the unityLinkNm
	 */
	public String getUnityLinkNm() {
		return unityLinkNm;
	}
	/**
	 * unityLinkNm 설정
	 * 
	 * @param unityLinkNm
	 *            the unityLinkNm to set
	 */
	public void setUnityLinkNm(String unityLinkNm) {
		this.unityLinkNm = unityLinkNm;
	}

	/**
	 * unityLinkUrl 리턴
	 * 
	 * @return the unityLinkUrl
	 */
	public String getUnityLinkUrl() {
		return unityLinkUrl;
	}
	/**
	 * unityLinkUrl 설정
	 * 
	 * @param unityLinkUrl
	 *            the unityLinkUrl to set
	 */
	public void setUnityLinkUrl(String unityLinkUrl) {
		this.unityLinkUrl = unityLinkUrl;
	}

	/**
	 * unityLinkDc 리턴
	 * 
	 * @return the unityLinkDc
	 */
	public String getUnityLinkDc() {
		return unityLinkDc;
	}
	/**
	 * unityLinkDc 설정
	 * 
	 * @param unityLinkDc
	 *            the unityLinkDc to set
	 */
	public void setUnityLinkDc(String unityLinkDc) {
		this.unityLinkDc = unityLinkDc;
	}

}
