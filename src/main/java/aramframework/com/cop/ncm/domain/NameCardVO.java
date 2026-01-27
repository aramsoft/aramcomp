package aramframework.com.cop.ncm.domain;

import aramframework.cmm.domain.BaseVO;

/**
 * 명함정보 관리를 위한 VO 클래스
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class NameCardVO extends BaseVO {

	// domain
	/** 명함아이디 */
	private String ncrdId = "";

	/** 대상 아이디 */
	private String trgetId = "";

	/** 이름 */
	private String ncrdNm = "";

	/** 전화번호 */
	private String telNo = "";

	/** 국가번호 */
	private String nationNo = "";

	/** 지역번호 */
	private String areaNo = "";

	/** 중간전화번호 */
	private String middleTelNo = "";

	/** 끝전화번호 */
	private String endTelNo = "";

	/** 휴대폰번호 */
	private String mbtlNum = "";

	/** 식별번호 */
	private String idntfcNo = "";

	/** 중간휴대폰번호 */
	private String middleMbtlNum = "";

	/** 끝휴대폰번호 */
	private String endMbtlNum = "";

	/** 이메일주소 */
	private String emailAdres = "";

	/** 회사명 */
	private String cmpnyNm = "";

	/** 부서명 */
	private String deptNm = "";

	/** 주소 */
	private String adres = "";

	/** 상세주소 */
	private String detailAdres = "";

	/** 직위명 */
	private String ofcpsNm = "";

	/** 직급명 */
	private String clsfNm = "";

	/** 공개여부 */
	private String othbcScope = "";

	/** 비고 */
	private String remark = "";

	// helper
	/** 템플릿 구분 코드명 */
	private String tmplatSeCodeNm = "";

	/** 사용자 아이디 */
	private String emplyrId = "";

	// domain
	/**
	 * ncrdId attribute를 리턴한다.
	 * 
	 * @return the ncrdId
	 */
	public String getNcrdId() {
		return ncrdId;
	}
	/**
	 * ncrdId attribute 값을 설정한다.
	 * 
	 * @param ncrdId
	 *            the ncrdId to set
	 */
	public void setNcrdId(String ncrdId) {
		this.ncrdId = ncrdId;
	}

	/**
	 * trgetId attribute를 리턴한다.
	 * 
	 * @return the trgetId
	 */
	public String getTrgetId() {
		return trgetId;
	}
	/**
	 * trgetId attribute 값을 설정한다.
	 * 
	 * @param trgetId
	 *            the trgetId to set
	 */
	public void setTrgetId(String trgetId) {
		this.trgetId = trgetId;
	}

	/**
	 * ncrdNm attribute를 리턴한다.
	 * 
	 * @return the ncrdNm
	 */
	public String getNcrdNm() {
		return ncrdNm;
	}
	/**
	 * ncrdNm attribute 값을 설정한다.
	 * 
	 * @param ncrdNm
	 *            the ncrdNm to set
	 */
	public void setNcrdNm(String ncrdNm) {
		this.ncrdNm = ncrdNm;
	}

	/**
	 * telNo attribute를 리턴한다.
	 * 
	 * @return the telNo
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * telNo attribute 값을 설정한다.
	 * 
	 * @param telNo
	 *            the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * nationNo attribute를 리턴한다.
	 * 
	 * @return the nationNo
	 */
	public String getNationNo() {
		return nationNo;
	}
	/**
	 * nationNo attribute 값을 설정한다.
	 * 
	 * @param nationNo
	 *            the nationNo to set
	 */
	public void setNationNo(String nationNo) {
		this.nationNo = nationNo;
	}

	/**
	 * areaNo attribute를 리턴한다.
	 * 
	 * @return the areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * 
	 * @param areaNo
	 *            the areaNo to set
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelNo attribute를 리턴한다.
	 * 
	 * @return the middleTelNo
	 */
	public String getMiddleTelNo() {
		return middleTelNo;
	}
	/**
	 * middleTelNo attribute 값을 설정한다.
	 * 
	 * @param middleTelNo
	 *            the middleTelNo to set
	 */
	public void setMiddleTelNo(String middleTelNo) {
		this.middleTelNo = middleTelNo;
	}

	/**
	 * endTelNo attribute를 리턴한다.
	 * 
	 * @return the endTelNo
	 */
	public String getEndTelNo() {
		return endTelNo;
	}
	/**
	 * endTelNo attribute 값을 설정한다.
	 * 
	 * @param endTelNo
	 *            the endTelNo to set
	 */
	public void setEndTelNo(String endTelNo) {
		this.endTelNo = endTelNo;
	}

	/**
	 * mbtlNum attribute를 리턴한다.
	 * 
	 * @return the mbtlNum
	 */
	public String getMbtlNum() {
		return mbtlNum;
	}
	/**
	 * mbtlNum attribute 값을 설정한다.
	 * 
	 * @param mbtlNum
	 *            the mbtlNum to set
	 */
	public void setMbtlNum(String mbtlNum) {
		this.mbtlNum = mbtlNum;
	}

	/**
	 * idntfcNo attribute를 리턴한다.
	 * 
	 * @return the idntfcNo
	 */
	public String getIdntfcNo() {
		return idntfcNo;
	}
	/**
	 * idntfcNo attribute 값을 설정한다.
	 * 
	 * @param idntfcNo
	 *            the idntfcNo to set
	 */
	public void setIdntfcNo(String idntfcNo) {
		this.idntfcNo = idntfcNo;
	}

	/**
	 * middleMbtlNum attribute를 리턴한다.
	 * 
	 * @return the middleMbtlNum
	 */
	public String getMiddleMbtlNum() {
		return middleMbtlNum;
	}
	/**
	 * middleMbtlNum attribute 값을 설정한다.
	 * 
	 * @param middleMbtlNum
	 *            the middleMbtlNum to set
	 */
	public void setMiddleMbtlNum(String middleMbtlNum) {
		this.middleMbtlNum = middleMbtlNum;
	}

	/**
	 * endMbtlNum attribute를 리턴한다.
	 * 
	 * @return the endMbtlNum
	 */
	public String getEndMbtlNum() {
		return endMbtlNum;
	}
	/**
	 * endMbtlNum attribute 값을 설정한다.
	 * 
	 * @param endMbtlNum
	 *            the endMbtlNum to set
	 */
	public void setEndMbtlNum(String endMbtlNum) {
		this.endMbtlNum = endMbtlNum;
	}

	/**
	 * emailAdres attribute를 리턴한다.
	 * 
	 * @return the emailAdres
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정한다.
	 * 
	 * @param emailAdres
	 *            the emailAdres to set
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * cmpnyNm attribute를 리턴한다.
	 * 
	 * @return the cmpnyNm
	 */
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	/**
	 * cmpnyNm attribute 값을 설정한다.
	 * 
	 * @param cmpnyNm
	 *            the cmpnyNm to set
	 */
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}

	/**
	 * deptNm attribute를 리턴한다.
	 * 
	 * @return the deptNm
	 */
	public String getDeptNm() {
		return deptNm;
	}
	/**
	 * deptNm attribute 값을 설정한다.
	 * 
	 * @param deptNm
	 *            the deptNm to set
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	/**
	 * adres attribute를 리턴한다.
	 * 
	 * @return the adres
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * adres attribute 값을 설정한다.
	 * 
	 * @param adres
	 *            the adres to set
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * detailAdres attribute를 리턴한다.
	 * 
	 * @return the detailAdres
	 */
	public String getDetailAdres() {
		return detailAdres;
	}
	/**
	 * detailAdres attribute 값을 설정한다.
	 * 
	 * @param detailAdres
	 *            the detailAdres to set
	 */
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}

	/**
	 * ofcpsNm attribute를 리턴한다.
	 * 
	 * @return the ofcpsNm
	 */
	public String getOfcpsNm() {
		return ofcpsNm;
	}
	/**
	 * ofcpsNm attribute 값을 설정한다.
	 * 
	 * @param ofcpsNm
	 *            the ofcpsNm to set
	 */
	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}
	
	/**
	 * clsfNm attribute를 리턴한다.
	 * 
	 * @return the clsfNm
	 */
	public String getClsfNm() {
		return clsfNm;
	}
	/**
	 * clsfNm attribute 값을 설정한다.
	 * 
	 * @param clsfNm
	 *            the clsfNm to set
	 */
	public void setClsfNm(String clsfNm) {
		this.clsfNm = clsfNm;
	}

	/**
	 * othbcScope attribute를 리턴한다.
	 * 
	 * @return the othbcScope
	 */
	public String getOthbcScope() {
		return othbcScope;
	}
	/**
	 * othbcScope attribute 값을 설정한다.
	 * 
	 * @param othbcScope
	 *            the othbcScope to set
	 */
	public void setOthbcScope(String othbcScope) {
		this.othbcScope = othbcScope;
	}

	/**
	 * remark attribute를 리턴한다.
	 * 
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * remark attribute 값을 설정한다.
	 * 
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	// helper
	/**
	 * tmplatSeCodeNm attribute를 리턴한다.
	 * 
	 * @return the tmplatSeCodeNm
	 */
	public String getTmplatSeCodeNm() {
		return tmplatSeCodeNm;
	}
	/**
	 * tmplatSeCodeNm attribute 값을 설정한다.
	 * 
	 * @param tmplatSeCodeNm
	 *            the tmplatSeCodeNm to set
	 */
	public void setTmplatSeCodeNm(String tmplatSeCodeNm) {
		this.tmplatSeCodeNm = tmplatSeCodeNm;
	}

	/**
	 * emplyrId attribute를 리턴한다.
	 * 
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정한다.
	 * 
	 * @param emplyrId
	 *            the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

}
