package aramframework.com.uss.sam.cpy.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 저작권보호정책내용을 처리하는 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CpyrhtPrtcPolicyVO extends BaseVO {

	// domain
	/** 저작권 ID */
	private String cpyrhtId;

	/** 저작권보호정책내용 */
	private String cpyrhtPrtcPolicyCn;

	/**
	 * cpyrhtId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getCpyrhtId() {
		return cpyrhtId;
	}
	/**
	 * cpyrhtId attribute 값을 설정한다.
	 * 
	 * @return cpyrhtId String
	 */
	public void setCpyrhtId(String cpyrhtId) {
		this.cpyrhtId = cpyrhtId;
	}

	/**
	 * cpyrhtPrtcPolicyCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getCpyrhtPrtcPolicyCn() {
		return cpyrhtPrtcPolicyCn;
	}
	/**
	 * cpyrhtPrtcPolicyCn attribute 값을 설정한다.
	 * 
	 * @return cpyrhtPrtcPolicyCn String
	 */
	public void setCpyrhtPrtcPolicyCn(String cpyrhtPrtcPolicyCn) {
		this.cpyrhtPrtcPolicyCn = cpyrhtPrtcPolicyCn;
	}

}
