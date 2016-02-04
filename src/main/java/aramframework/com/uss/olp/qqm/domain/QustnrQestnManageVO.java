package aramframework.com.uss.olp.qqm.domain;

import aramframework.com.cmm.domain.SearchVO;

/**
 * 설문문항 VO Class 구현
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

public class QustnrQestnManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	/** 검색모드설정 */
	private String searchMode = "";

	/** 설문제목 */
	private String qestnrSj = "";

	/** 설문문항 ID */
	private String qestnrQesitmId = "";

	/** 설문지 ID */
	private String qestnrId = "";

	/** 질문순번 */
	private String qestnSn = "";

	/** 질문유형코드 */
	private String qestnTyCode = "";

	/** 질문내용 */
	private String qestnCn = "";

	/** 초대선택건수 */
	private String mxmmChoiseCo = "";

	/**
	 * searchMode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getSearchMode() {
		return searchMode;
	}
	/**
	 * searchMode attribute 값을 설정한다.
	 * 
	 * @return searchMode String
	 */
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	/**
	 * qestnrSj attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrSj() {
		return qestnrSj;
	}
	/**
	 * qestnrSj attribute 값을 설정한다.
	 * 
	 * @return qestnrSj String
	 */
	public void setQestnrSj(String qestnrSj) {
		this.qestnrSj = qestnrSj;
	}

	/**
	 * qestnrQesitmId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrQesitmId() {
		return qestnrQesitmId;
	}
	/**
	 * qestnrQesitmId attribute 값을 설정한다.
	 * 
	 * @return qestnrQesitmId String
	 */
	public void setQestnrQesitmId(String qestnrQesitmId) {
		this.qestnrQesitmId = qestnrQesitmId;
	}

	/**
	 * qestnrId attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnrId() {
		return qestnrId;
	}
	/**
	 * qestnrId attribute 값을 설정한다.
	 * 
	 * @return qestnrId String
	 */
	public void setQestnrId(String qestnrId) {
		this.qestnrId = qestnrId;
	}

	/**
	 * qestnSn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnSn() {
		return qestnSn;
	}
	/**
	 * qestnSn attribute 값을 설정한다.
	 * 
	 * @return qestnSn String
	 */
	public void setQestnSn(String qestnSn) {
		this.qestnSn = qestnSn;
	}

	/**
	 * qestnTyCode attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnTyCode() {
		return qestnTyCode;
	}
	/**
	 * qestnTyCode attribute 값을 설정한다.
	 * 
	 * @return qestnTyCode String
	 */
	public void setQestnTyCode(String qestnTyCode) {
		this.qestnTyCode = qestnTyCode;
	}

	/**
	 * qestnCn attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getQestnCn() {
		return qestnCn;
	}
	/**
	 * qestnCn attribute 값을 설정한다.
	 * 
	 * @return qestnCn String
	 */
	public void setQestnCn(String qestnCn) {
		this.qestnCn = qestnCn;
	}

	/**
	 * mxmmChoiseCo attribute 를 리턴한다.
	 * 
	 * @return the String
	 */
	public String getMxmmChoiseCo() {
		return mxmmChoiseCo;
	}
	/**
	 * mxmmChoiseCo attribute 값을 설정한다.
	 * 
	 * @return mxmmChoiseCo String
	 */
	public void setMxmmChoiseCo(String mxmmChoiseCo) {
		this.mxmmChoiseCo = mxmmChoiseCo;
	}

}
