package aramframework.com.ssi.syi.iis.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 연계기관 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class CntcInsttVO extends BaseVO  {

	// domain
	/** 기관ID */
	private String insttId = "";

	/** 기관명 */
	private String insttNm = "";

	// domain
	/**
	 * insttId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getInsttId() {
		return insttId;
	}
	/**
	 * insttId attribute 값을 설정한다.
	 * 
	 * @param insttId
	 *            String
	 */
	public void setInsttId(String insttId) {
		this.insttId = insttId;
	}

	/**
	 * insttNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getInsttNm() {
		return insttNm;
	}
	/**
	 * insttNm attribute 값을 설정한다.
	 * 
	 * @param insttNm
	 *            String
	 */
	public void setInsttNm(String insttNm) {
		this.insttNm = insttNm;
	}

}
