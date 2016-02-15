package aramframework.com.uss.umt.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 가입약관VO클래스로서가입약관확인시 비지니스로직 처리용 항목을 구성한다.
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

public class StplatVO extends BaseVO {

	/** 약관아이디 */
	private String useStplatId;

	/** 사용약관안내 */
	private String useStplatCn;

	/** 정보동의안내 */
	private String infoProvdAgreCn;

	/**
	 * useStplatId attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseStplatId() {
		return useStplatId;
	}

	/**
	 * useStplatId attribute 값을 설정한다.
	 * 
	 * @param useStplatId
	 *            String
	 */
	public void setUseStplatId(String useStplatId) {
		this.useStplatId = useStplatId;
	}

	/**
	 * useStplatCn attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getUseStplatCn() {
		return useStplatCn;
	}

	/**
	 * useStplatCn attribute 값을 설정한다.
	 * 
	 * @param useStplatCn
	 *            String
	 */
	public void setUseStplatCn(String useStplatCn) {
		this.useStplatCn = useStplatCn;
	}

	/**
	 * infoProvdAgreCn attribute 값을 리턴한다.
	 * 
	 * @return String
	 */
	public String getInfoProvdAgreCn() {
		return infoProvdAgreCn;
	}

	/**
	 * infoProvdAgreCn attribute 값을 설정한다.
	 * 
	 * @param infoProvdAgreCn
	 *            String
	 */
	public void setInfoProvdAgreCn(String infoProvdAgreCn) {
		this.infoProvdAgreCn = infoProvdAgreCn;
	}

}
