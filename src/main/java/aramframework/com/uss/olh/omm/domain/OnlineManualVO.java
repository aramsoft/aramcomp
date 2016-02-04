package aramframework.com.uss.olh.omm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 온라인메뉴얼 VO Class 구현
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

public class OnlineManualVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 온라인메뉴얼 아이디 */
	private String onlineMnlId;

	/* 온라인메뉴얼 명 */
	private String onlineMnlNm;

	/** 온라인메뉴얼 구분코드 */
	private String onlineMnlSeCode;

	/** 온라인메뉴얼 정의 */
	private String onlineMnlDfn;

	/** 온라인메뉴얼 설명 */
	private String onlineMnlDc;

	/**
	 * onlineMnlId 리턴
	 * 
	 * @return the onlineMnlId
	 */
	public String getOnlineMnlId() {
		return onlineMnlId;
	}
	/**
	 * onlineMnlId 설정
	 * 
	 * @param onlineMnlId
	 *            the onlineMnlId to set
	 */
	public void setOnlineMnlId(String onlineMnlId) {
		this.onlineMnlId = onlineMnlId;
	}

	/**
	 * onlineMnlNm 리턴
	 * 
	 * @return the onlineMnlNm
	 */
	public String getOnlineMnlNm() {
		return onlineMnlNm;
	}
	/**
	 * onlineMnlNm 설정
	 * 
	 * @param onlineMnlNm
	 *            the onlineMnlNm to set
	 */
	public void setOnlineMnlNm(String onlineMnlNm) {
		this.onlineMnlNm = onlineMnlNm;
	}

	/**
	 * onlineMnlSeCode 리턴
	 * 
	 * @return the onlineMnlSeCode
	 */
	public String getOnlineMnlSeCode() {
		return onlineMnlSeCode;
	}
	/**
	 * onlineMnlSeCode 설정
	 * 
	 * @param onlineMnlSeCode
	 *            the onlineMnlSeCode to set
	 */
	public void setOnlineMnlSeCode(String onlineMnlSeCode) {
		this.onlineMnlSeCode = onlineMnlSeCode;
	}

	/**
	 * onlineMnlDfn 리턴
	 * 
	 * @return the onlineMnlDf
	 */
	public String getOnlineMnlDfn() {
		return onlineMnlDfn;
	}
	/**
	 * onlineMnlDfn 설정
	 * 
	 * @param onlineMnlDf
	 *            the onlineMnlDf to set
	 */
	public void setOnlineMnlDfn(String onlineMnlDfn) {
		this.onlineMnlDfn = onlineMnlDfn;
	}

	/**
	 * onlineMnlDc 리턴
	 * 
	 * @return the onlineMnlDc
	 */
	public String getOnlineMnlDc() {
		return onlineMnlDc;
	}
	/**
	 * onlineMnlDc 설정
	 * 
	 * @param onlineMnlDc
	 *            the onlineMnlDc to set
	 */
	public void setOnlineMnlDc(String onlineMnlDc) {
		this.onlineMnlDc = onlineMnlDc;
	}

}
