package aramframework.com.uss.sam.cpy.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 저작권보호정책내용을 처리하는 VO 클래스
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

public class CpyrhtPrtcPolicyVO extends SearchVO {

	private static final long serialVersionUID = 1L;

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
