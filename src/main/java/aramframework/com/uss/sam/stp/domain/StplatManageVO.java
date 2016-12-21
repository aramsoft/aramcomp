package aramframework.com.uss.sam.stp.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 약관내용을 처리하는 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 */
public class StplatManageVO extends BaseVO {

	// domain
	/** 이용약관 ID */
	private String useStplatId;

	/** 이용약관명 */
	private String useStplatNm;

	/** 이용약관내용 */
	private String useStplatCn;

	/** 정보제공동의내용 */
	private String infoProvdAgreCn;

	/**
	 * useStplatId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getUseStplatId() {
		return useStplatId;
	}
	/**
	 * useStplatId attribute 값을 설정한다.
	 * 
	 * @return useStplatId String
	 */
	public void setUseStplatId(String useStplatId) {
		this.useStplatId = useStplatId;
	}

	/**
	 * useStplatNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getUseStplatNm() {
		return useStplatNm;
	}
	/**
	 * useStplatNm attribute 값을 설정한다.
	 * 
	 * @return useStplatNm String
	 */
	public void setUseStplatNm(String useStplatNm) {
		this.useStplatNm = useStplatNm;
	}

	/**
	 * useStplatCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getUseStplatCn() {
		return useStplatCn;
	}
	/**
	 * useStplatCn attribute 값을 설정한다.
	 * 
	 * @return useStplatCn String
	 */
	public void setUseStplatCn(String useStplatCn) {
		this.useStplatCn = useStplatCn;
	}

	/**
	 * infoProvdAgreCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getInfoProvdAgreCn() {
		return infoProvdAgreCn;
	}
	/**
	 * infoProvdAgreCn attribute 값을 설정한다.
	 * 
	 * @return infoProvdAgreCn String
	 */
	public void setInfoProvdAgreCn(String infoProvdAgreCn) {
		this.infoProvdAgreCn = infoProvdAgreCn;
	}

}
