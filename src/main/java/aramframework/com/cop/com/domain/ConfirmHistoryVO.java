package aramframework.com.cop.com.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import aramframework.com.cmm.SearchVO;

/**
 * 승인정보를 관리하기 위한 VO 클래스
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

public class ConfirmHistoryVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	/** 승인일 */
	private String confmDe = "";

	/** 승인자 아이디 */
	private String confmerId = "";

	/** 확인구분코드 */
	private int confmNumber = 0;

	/** 승인요청자 아이디 */
	private String confmRqesterId = "";

	/** 승인상태코드 */
	private String confmSttusCode = "";

	/** 승인유형코드 */
	private String confmTyCode = "";

	/** 작업 아이디 */
	private String opertId = "";

	/** 작업유형코드 */
	private String opertTyCode = "";

	/** 대상업무 아이디 */
	private String trgetJobId = "";

	/** 대상업무유형코드 */
	private String trgetJobTyCode = "";

	/** 확인요청자명 */
	private String confmRqesterNm = "";

	/** 확인구분 코드명 */
	private String confmTyCodeNm = "";

	/** 확인상태 코드명 */
	private String confmSttusCodeNm = "";

	/** 대상유형구분 코드명 */
	private String trgetJobTyNm = "";

	/** 처리구분 코드명 */
	private String opertTyCodeNm = "";

	/**
	 * confmDe attribute를 리턴한다.
	 * 
	 * @return the confmDe
	 */
	public String getConfmDe() {
		return confmDe;
	}
	/**
	 * confmDe attribute 값을 설정한다.
	 * 
	 * @param confmDe
	 *            the confmDe to set
	 */
	public void setConfmDe(String confmDe) {
		this.confmDe = confmDe;
	}

	/**
	 * confmerId attribute를 리턴한다.
	 * 
	 * @return the confmerId
	 */
	public String getConfmerId() {
		return confmerId;
	}
	/**
	 * confmerId attribute 값을 설정한다.
	 * 
	 * @param confmerId
	 *            the confmerId to set
	 */
	public void setConfmerId(String confmerId) {
		this.confmerId = confmerId;
	}

	/**
	 * confmNumber attribute를 리턴한다.
	 * 
	 * @return the confmNumber
	 */
	public int getConfmNumber() {
		return confmNumber;
	}
	/**
	 * confmNumber attribute 값을 설정한다.
	 * 
	 * @param confmNumber
	 *            the confmNumber to set
	 */
	public void setConfmNumber(int confmNumber) {
		this.confmNumber = confmNumber;
	}

	/**
	 * confmRqesterId attribute를 리턴한다.
	 * 
	 * @return the confmRqesterId
	 */
	public String getConfmRqesterId() {
		return confmRqesterId;
	}
	/**
	 * confmRqesterId attribute 값을 설정한다.
	 * 
	 * @param confmRqesterId
	 *            the confmRqesterId to set
	 */
	public void setConfmRqesterId(String confmRqesterId) {
		this.confmRqesterId = confmRqesterId;
	}

	/**
	 * confmSttusCode attribute를 리턴한다.
	 * 
	 * @return the confmSttusCode
	 */
	public String getConfmSttusCode() {
		return confmSttusCode;
	}
	/**
	 * confmSttusCode attribute 값을 설정한다.
	 * 
	 * @param confmSttusCode
	 *            the confmSttusCode to set
	 */
	public void setConfmSttusCode(String confmSttusCode) {
		this.confmSttusCode = confmSttusCode;
	}

	/**
	 * confmTyCode attribute를 리턴한다.
	 * 
	 * @return the confmTyCode
	 */
	public String getConfmTyCode() {
		return confmTyCode;
	}
	/**
	 * confmTyCode attribute 값을 설정한다.
	 * 
	 * @param confmTyCode
	 *            the confmTyCode to set
	 */
	public void setConfmTyCode(String confmTyCode) {
		this.confmTyCode = confmTyCode;
	}

	/**
	 * opertId attribute를 리턴한다.
	 * 
	 * @return the opertId
	 */
	public String getOpertId() {
		return opertId;
	}
	/**
	 * opertId attribute 값을 설정한다.
	 * 
	 * @param opertId
	 *            the opertId to set
	 */
	public void setOpertId(String opertId) {
		this.opertId = opertId;
	}

	/**
	 * opertTyCode attribute를 리턴한다.
	 * 
	 * @return the opertTyCode
	 */
	public String getOpertTyCode() {
		return opertTyCode;
	}
	/**
	 * opertTyCode attribute 값을 설정한다.
	 * 
	 * @param opertTyCode
	 *            the opertTyCode to set
	 */
	public void setOpertTyCode(String opertTyCode) {
		this.opertTyCode = opertTyCode;
	}

	/**
	 * trgetJobId attribute를 리턴한다.
	 * 
	 * @return the trgetJobId
	 */
	public String getTrgetJobId() {
		return trgetJobId;
	}
	/**
	 * trgetJobId attribute 값을 설정한다.
	 * 
	 * @param trgetJobId
	 *            the trgetJobId to set
	 */
	public void setTrgetJobId(String trgetJobId) {
		this.trgetJobId = trgetJobId;
	}

	/**
	 * trgetJobTyCode attribute를 리턴한다.
	 * 
	 * @return the trgetJobTyCode
	 */
	public String getTrgetJobTyCode() {
		return trgetJobTyCode;
	}
	/**
	 * trgetJobTyCode attribute 값을 설정한다.
	 * 
	 * @param trgetJobTyCode
	 *            the trgetJobTyCode to set
	 */
	public void setTrgetJobTyCode(String trgetJobTyCode) {
		this.trgetJobTyCode = trgetJobTyCode;
	}

	/**
	 * confmRqesterNm attribute를 리턴한다.
	 * 
	 * @return the confmRqesterNm
	 */
	public String getConfmRqesterNm() {
		return confmRqesterNm;
	}
	/**
	 * confmRqesterNm attribute 값을 설정한다.
	 * 
	 * @param confmRqesterNm
	 *            the confmRqesterNm to set
	 */
	public void setConfmRqesterNm(String confmRqesterNm) {
		this.confmRqesterNm = confmRqesterNm;
	}

	/**
	 * confmTyCodeNm attribute를 리턴한다.
	 * 
	 * @return the confmTyCodeNm
	 */
	public String getConfmTyCodeNm() {
		return confmTyCodeNm;
	}
	/**
	 * confmTyCodeNm attribute 값을 설정한다.
	 * 
	 * @param confmTyCodeNm
	 *            the confmTyCodeNm to set
	 */
	public void setConfmTyCodeNm(String confmTyCodeNm) {
		this.confmTyCodeNm = confmTyCodeNm;
	}

	/**
	 * confmSttusCodeNm attribute를 리턴한다.
	 * 
	 * @return the confmSttusCodeNm
	 */
	public String getConfmSttusCodeNm() {
		return confmSttusCodeNm;
	}
	/**
	 * confmSttusCodeNm attribute 값을 설정한다.
	 * 
	 * @param confmSttusCodeNm
	 *            the confmSttusCodeNm to set
	 */
	public void setConfmSttusCodeNm(String confmSttusCodeNm) {
		this.confmSttusCodeNm = confmSttusCodeNm;
	}

	/**
	 * trgetJobTyNm attribute를 리턴한다.
	 * 
	 * @return the trgetJobTyNm
	 */
	public String getTrgetJobTyNm() {
		return trgetJobTyNm;
	}
	/**
	 * trgetJobTyNm attribute 값을 설정한다.
	 * 
	 * @param trgetJobTyNm
	 *            the trgetJobTyNm to set
	 */
	public void setTrgetJobTyNm(String trgetJobTyNm) {
		this.trgetJobTyNm = trgetJobTyNm;
	}

	/**
	 * opertTyCodeNm attribute를 리턴한다.
	 * 
	 * @return the opertTyCodeNm
	 */
	public String getOpertTyCodeNm() {
		return opertTyCodeNm;
	}
	/**
	 * opertTyCodeNm attribute 값을 설정한다.
	 * 
	 * @param opertTyCodeNm
	 *            the opertTyCodeNm to set
	 */
	public void setOpertTyCodeNm(String opertTyCodeNm) {
		this.opertTyCodeNm = opertTyCodeNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
