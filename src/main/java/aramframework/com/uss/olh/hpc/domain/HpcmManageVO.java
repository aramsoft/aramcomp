package aramframework.com.uss.olh.hpc.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 도움말 처리를 위한 VO 클래스
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

public class HpcmManageVO extends BaseVO  {

	// domain
	/** 도움말 ID */
	private String hpcmId;

	/** 도움말구분코드 */
	private String hpcmSeCode;

	/** 도움말정의 */
	private String hpcmDfn;

	/** 도움말 설명 */
	private String hpcmDc;

	// helper
	/** 도움말구분코드명 */
	private String hpcmSeCodeNm;

	//domain
	/**
	 * hpcmId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getHpcmId() {
		return hpcmId;
	}
	/**
	 * hpcmId attribute 값을 설정한다.
	 * 
	 * @return hpcmId String
	 */
	public void setHpcmId(String hpcmId) {
		this.hpcmId = hpcmId;
	}

	/**
	 * hpcmSeCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getHpcmSeCode() {
		return hpcmSeCode;
	}
	/**
	 * hpcmSeCode attribute 값을 설정한다.
	 * 
	 * @return hpcmSeCode String
	 */
	public void setHpcmSeCode(String hpcmSeCode) {
		this.hpcmSeCode = hpcmSeCode;
	}

	/**
	 * hpcmDfn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getHpcmDfn() {
		return hpcmDfn;
	}
	/**
	 * hpcmDf attribute 값을 설정한다.
	 * 
	 * @return hpcmDf String
	 */
	public void setHpcmDfn(String hpcmDfn) {
		this.hpcmDfn = hpcmDfn;
	}

	/**
	 * hpcmDc attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getHpcmDc() {
		return hpcmDc;
	}
	/**
	 * hpcmDc attribute 값을 설정한다.
	 * 
	 * @return hpcmDc String
	 */
	public void setHpcmDc(String hpcmDc) {
		this.hpcmDc = hpcmDc;
	}

	// helper
	/**
	 * hpcmSeCodeNm attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getHpcmSeCodeNm() {
		return hpcmSeCodeNm;
	}
	/**
	 * hpcmSeCodeNm attribute 값을 설정한다.
	 * 
	 * @return hpcmSeCodeNm String
	 */
	public void setHpcmSeCodeNm(String hpcmSeCodeNm) {
		this.hpcmSeCodeNm = hpcmSeCodeNm;
	}

}
