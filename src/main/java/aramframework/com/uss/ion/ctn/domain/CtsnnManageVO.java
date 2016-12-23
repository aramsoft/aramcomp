package aramframework.com.uss.ion.ctn.domain;

import aramframework.com.cmm.domain.BaseVO;

/**
 * 개요 - 경조관리에 대한 Vo 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
public class CtsnnManageVO extends BaseVO {

	// domain
	/** 경조ID */
	private String ctsnnId;

	/** 사용자ID */
	private String usid;

	/** 경조코드 */
	private String ctsnnCd;

	/** 신청일자 */
	private String reqstDe;

	/** 경조명 */
	private String ctsnnNm;

	/** 대상자명 */
	private String trgterNm;

	/** 생년월일 */
	private String brth;

	/** 발생일자 */
	private String occrrDe;

	/** 관계 */
	private String relate;

	/** 비고 */
	private String remark;

	/** 결재자ID */
	private String sanctnerId;

	/** 승인여부 */
	private String confmAt;

	/** 결재일시 */
	private String sanctnDt;

	/** 반려사유 */
	private String returnResn;

	/** 약식결재ID */
	private String infrmlSanctnId;

	// helper
	/** 신청자명 */
	private String usNm;

	/** 사용자 소속명 */
	private String orgnztNm;

	/** 경조코드명 */
	private String ctsnnCdNm;

	/** 가족관계명 */
	private String relateNm;

	/** 승인자명 */
	private String sanctnerNm;

	/** 승인자 소속명 */
	private String sanctnerOrgnztNm;

	// 추가 검색 조건
	/** 검색시작일자 */
	private String searchFromDate;

	/** 검색종료일자 */
	private String searchToDate;

	/** 검색 성명 */
	private String searchNm;

	/** 검색 진행구분 */
	private String searchConfmAt;

	// domain
	/**
	 * @return the ctsnnId
	 */
	public String getCtsnnId() {
		return ctsnnId;
	}
	/**
	 * @param ctsnnId
	 *            the ctsnnId to set
	 */
	public void setCtsnnId(String ctsnnId) {
		this.ctsnnId = ctsnnId;
	}

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
	 * @return the ctsnnCd
	 */
	public String getCtsnnCd() {
		return ctsnnCd;
	}
	/**
	 * @param ctsnnCd
	 *            the ctsnnCd to set
	 */
	public void setCtsnnCd(String ctsnnCd) {
		this.ctsnnCd = ctsnnCd;
	}

	/**
	 * @return the reqstDe
	 */
	public String getReqstDe() {
		return reqstDe;
	}
	/**
	 * @param reqstDe
	 *            the reqstDe to set
	 */
	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	/**
	 * @return the ctsnnNm
	 */
	public String getCtsnnNm() {
		return ctsnnNm;
	}
	/**
	 * @param ctsnnNm
	 *            the ctsnnNm to set
	 */
	public void setCtsnnNm(String ctsnnNm) {
		this.ctsnnNm = ctsnnNm;
	}

	/**
	 * @return the trgterNm
	 */
	public String getTrgterNm() {
		return trgterNm;
	}
	/**
	 * @param trgterNm
	 *            the trgterNm to set
	 */
	public void setTrgterNm(String trgterNm) {
		this.trgterNm = trgterNm;
	}

	/**
	 * @return the brth
	 */
	public String getBrth() {
		return brth;
	}
	/**
	 * @param brth
	 *            the brth to set
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * @return the occrrDe
	 */
	public String getOccrrDe() {
		return occrrDe;
	}
	/**
	 * @param occrrDe
	 *            the occrrDe to set
	 */
	public void setOccrrDe(String occrrDe) {
		this.occrrDe = occrrDe;
	}

	/**
	 * @return the relate
	 */
	public String getRelate() {
		return relate;
	}
	/**
	 * @param relate
	 *            the relate to set
	 */
	public void setRelate(String relate) {
		this.relate = relate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the sanctnerId
	 */
	public String getSanctnerId() {
		return sanctnerId;
	}
	/**
	 * @param sanctnerId
	 *            the sanctnerId to set
	 */
	public void setSanctnerId(String sanctnerId) {
		this.sanctnerId = sanctnerId;
	}

	/**
	 * @return the confmAt
	 */
	public String getConfmAt() {
		return confmAt;
	}
	/**
	 * @param confmAt
	 *            the confmAt to set
	 */
	public void setConfmAt(String confmAt) {
		this.confmAt = confmAt;
	}

	/**
	 * @return the sanctnDt
	 */
	public String getSanctnDt() {
		return sanctnDt;
	}
	/**
	 * @param sanctnDt
	 *            the sanctnDt to set
	 */
	public void setSanctnDt(String sanctnDt) {
		this.sanctnDt = sanctnDt;
	}

	/**
	 * @return the returnResn
	 */
	public String getReturnResn() {
		return returnResn;
	}
	/**
	 * @param returnResn
	 *            the returnResn to set
	 */
	public void setReturnResn(String returnResn) {
		this.returnResn = returnResn;
	}

	/**
	 * @return the infrmlSanctnId
	 */
	public String getInfrmlSanctnId() {
		return infrmlSanctnId;
	}
	/**
	 * @param infrmlSanctnId
	 *            the infrmlSanctnId to set
	 */
	public void setInfrmlSanctnId(String infrmlSanctnId) {
		this.infrmlSanctnId = infrmlSanctnId;
	}

	// helper
	/**
	 * @return the usNm
	 */
	public String getUsNm() {
		return usNm;
	}
	/**
	 * @param usNm
	 *            the usNm to set
	 */
	public void setUsNm(String usNm) {
		this.usNm = usNm;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	/**
	 * @return the ctsnnCdNm
	 */
	public String getCtsnnCdNm() {
		return ctsnnCdNm;
	}
	/**
	 * @param ctsnnCdNm
	 *            the ctsnnCdNm to set
	 */
	public void setCtsnnCdNm(String ctsnnCdNm) {
		this.ctsnnCdNm = ctsnnCdNm;
	}

	/**
	 * @return the relateNm
	 */
	public String getRelateNm() {
		return relateNm;
	}
	/**
	 * @param relateNm
	 *            the relateNm to set
	 */
	public void setRelateNm(String relateNm) {
		this.relateNm = relateNm;
	}

	/**
	 * @return the sanctnerNm
	 */
	public String getSanctnerNm() {
		return sanctnerNm;
	}
	/**
	 * @param sanctnerNm
	 *            the sanctnerNm to set
	 */
	public void setSanctnerNm(String sanctnerNm) {
		this.sanctnerNm = sanctnerNm;
	}

	/**
	 * @return the sanctnerOrgnztNm
	 */
	public String getSanctnerOrgnztNm() {
		return sanctnerOrgnztNm;
	}
	/**
	 * @param sanctnerOrgnztNm
	 *            the sanctnerOrgnztNm to set
	 */
	public void setSanctnerOrgnztNm(String sanctnerOrgnztNm) {
		this.sanctnerOrgnztNm = sanctnerOrgnztNm;
	}

	// 추가 검색 조건
	/**
	 * @return the searchFromDate
	 */
	public String getSearchFromDate() {
		return searchFromDate;
	}
	/**
	 * @param searchFromDate
	 *            the searchFromDate to set
	 */
	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}

	/**
	 * @return the searchToDate
	 */
	public String getSearchToDate() {
		return searchToDate;
	}
	/**
	 * @param searchToDate
	 *            the searchToDate to set
	 */
	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}

	/**
	 * @return the searchNm
	 */
	public String getSearchNm() {
		return searchNm;
	}
	/**
	 * @param searchNm
	 *            the searchNm to set
	 */
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}

	/**
	 * @return the searchConfmAt
	 */
	public String getSearchConfmAt() {
		return searchConfmAt;
	}
	/**
	 * @param searchConfmAt
	 *            the searchConfmAt to set
	 */
	public void setSearchConfmAt(String searchConfmAt) {
		this.searchConfmAt = searchConfmAt;
	}

}
