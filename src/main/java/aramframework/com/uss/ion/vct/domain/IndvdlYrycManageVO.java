package aramframework.com.uss.ion.vct.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 개인별 연차관리에 대한 model 클래스를 정의한다.
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

public class IndvdlYrycManageVO extends BaseVO {

	// domain
	/** 사용자ID */
	private String usid;

	/** 발생연도 */
	private String occrrncYear;

	/** 발생연차갯수 */
	private double occrncYrycCo;

	/** 사용연차갯수 */
	private double useYrycCo;

	/** 잔여연차갯수 */
	private double remndrYrycCo;

	// domain
	/**
	 * @return the usid
	 */
	public String getUsid() {
		return usid;
	}
	/**
	 * @param usid
	 *            the usid to set
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/**
	 * @return the occrrncYear
	 */
	public String getOccrrncYear() {
		return occrrncYear;
	}
	/**
	 * @param occrrncYear
	 *            the occrrncYear to set
	 */
	public void setOccrrncYear(String occrrncYear) {
		this.occrrncYear = occrrncYear;
	}

	/**
	 * @return the occrncYrycCo
	 */
	public double getOccrncYrycCo() {
		return occrncYrycCo;
	}
	/**
	 * @param occrncYrycCo
	 *            the occrncYrycCo to set
	 */
	public void setOccrncYrycCo(double occrncYrycCo) {
		this.occrncYrycCo = occrncYrycCo;
	}

	/**
	 * @return the useYrycCo
	 */
	public double getUseYrycCo() {
		return useYrycCo;
	}
	/**
	 * @param useYrycCo
	 *            the useYrycCo to set
	 */
	public void setUseYrycCo(double useYrycCo) {
		this.useYrycCo = useYrycCo;
	}

	/**
	 * @return the remndrYrycCo
	 */
	public double getRemndrYrycCo() {
		return remndrYrycCo;
	}
	/**
	 * @param remndrYrycCo
	 *            the remndrYrycCo to set
	 */
	public void setRemndrYrycCo(double remndrYrycCo) {
		this.remndrYrycCo = remndrYrycCo;
	}

}