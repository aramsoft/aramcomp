package aramframework.com.uss.sam.ipm.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 개인정보보호정책 VO Class 구현
 * 
 * @since 2014.11.11
 * @version 1.0
 */
public class IndvdlInfoPolicyVO extends BaseVO {

	// domain
	/** 개인정보보호정책 아이디 */
	private String indvdlInfoId;

	/** 개인정보보호정책 명 */
	private String indvdlInfoNm;

	/** 개인정보보호정책 내용 */
	private String indvdlInfoDc;

	/** 개인정보보호정책 동의여부 */
	private String indvdlInfoYn;

	/**
	 * indvdlInfoId 리턴
	 * 
	 * @return the indvdlInfoId
	 */
	public String getIndvdlInfoId() {
		return indvdlInfoId;
	}
	/**
	 * indvdlInfoId 설정
	 * 
	 * @param indvdlInfoId
	 *            the indvdlInfoId to set
	 */
	public void setIndvdlInfoId(String indvdlInfoId) {
		this.indvdlInfoId = indvdlInfoId;
	}

	/**
	 * indvdlInfoNm 리턴
	 * 
	 * @return the indvdlInfoNm
	 */
	public String getIndvdlInfoNm() {
		return indvdlInfoNm;
	}
	/**
	 * indvdlInfoNm 설정
	 * 
	 * @param indvdlInfoNm
	 *            the indvdlInfoNm to set
	 */
	public void setIndvdlInfoNm(String indvdlInfoNm) {
		this.indvdlInfoNm = indvdlInfoNm;
	}

	/**
	 * indvdlInfoDc 리턴
	 * 
	 * @return the indvdlInfoDc
	 */
	public String getIndvdlInfoDc() {
		return indvdlInfoDc;
	}
	/**
	 * indvdlInfoDc 설정
	 * 
	 * @param indvdlInfoDc
	 *            the indvdlInfoDc to set
	 */
	public void setIndvdlInfoDc(String indvdlInfoDc) {
		this.indvdlInfoDc = indvdlInfoDc;
	}

	/**
	 * indvdlInfoYn 리턴
	 * 
	 * @return the indvdlInfoYn
	 */
	public String getIndvdlInfoYn() {
		return indvdlInfoYn;
	}
	/**
	 * indvdlInfoYn 설정
	 * 
	 * @param indvdlInfoYn
	 *            the indvdlInfoYn to set
	 */
	public void setIndvdlInfoYn(String indvdlInfoYn) {
		this.indvdlInfoYn = indvdlInfoYn;
	}

}
