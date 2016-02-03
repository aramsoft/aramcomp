package aramframework.com.uss.ion.vct.domain;

import aramframework.com.cmm.SearchVO;

/**
 * 개요 - 포상관리에 대한 Vo 클래스를 정의한다.
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

public class VcatnManageVO extends SearchVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 신청자ID */
	private String applcntId;

	/** 신청자명 */
	private String applcntNm;

	/** orgnztNm */
	private String orgnztNm;

	/** 휴가구분 */
	private String vcatnSe;

	/** 휴가구분명 */
	private String vcatnSeNm;

	/** 시작일자 */
	private String bgnde;

	/** 종료일자 */
	private String endde;

	/** 신청일자 */
	private String reqstDe;

	/** 휴가사유 */
	private String vcatnResn;

	/** 발생연도 */
	private String occrrncYear;

	/** 정오구분 */
	private String noonSe;

	/** 결재자ID */
	private String sanctnerId;

	/** 승인자명 */
	private String sanctnerNm;

	/** 승인자 소속명 */
	private String sanctnerOrgnztNm;

	/** 승인여부 */
	private String confmAt;

	/** 결재일시 */
	private String sanctnDt;

	/** sanctnDtNm */
	private String sanctnDtNm;

	/** 반려사유 */
	private String returnResn;

	/** 약식결재ID */
	private String infrmlSanctnId;

	/** 사용자ID */
	private String usid;

	/** 사용자 소속명 */ 
	//* private String orgnztNm;

	/** 검색 연도 */
	private String searchYear;

	/** 검색 월 */
	private String searchMonth;

	/** 검색 성명 */
	private String searchNm;

	/** 검색 진행구분 */
	private String searchConfmAt;

	/**
	 * @return the sanctnDtNm
	 */
	public String getSanctnDtNm() {
		return sanctnDtNm;
	}
	/**
	 * @param sanctnDtNm
	 *            the sanctnDtNm to set
	 */
	public void setSanctnDtNm(String sanctnDtNm) {
		this.sanctnDtNm = sanctnDtNm;
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
	 * @return the applcntId
	 */
	public String getApplcntId() {
		return applcntId;
	}
	/**
	 * @param applcntId
	 *            the applcntId to set
	 */
	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	/**
	 * @return the vcatnSe
	 */
	public String getVcatnSe() {
		return vcatnSe;
	}
	/**
	 * @param vcatnSe
	 *            the vcatnSe to set
	 */
	public void setVcatnSe(String vcatnSe) {
		this.vcatnSe = vcatnSe;
	}

	/**
	 * @return the bgnde
	 */
	public String getBgnde() {
		return bgnde;
	}
	/**
	 * @param bgnde
	 *            the bgnde to set
	 */
	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}

	/**
	 * @return the endde
	 */
	public String getEndde() {
		return endde;
	}
	/**
	 * @param endde
	 *            the endde to set
	 */
	public void setEndde(String endde) {
		this.endde = endde;
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
	 * @return the vcatnResn
	 */
	public String getVcatnResn() {
		return vcatnResn;
	}
	/**
	 * @param vcatnResn
	 *            the vcatnResn to set
	 */
	public void setVcatnResn(String vcatnResn) {
		this.vcatnResn = vcatnResn;
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

	/**
	 * @return the noonSe
	 */
	public String getNoonSe() {
		return noonSe;
	}
	/**
	 * @param noonSe
	 *            the noonSe to set
	 */
	public void setNoonSe(String noonSe) {
		this.noonSe = noonSe;
	}

	/**
	 * @return the applcntNm
	 */
	public String getApplcntNm() {
		return applcntNm;
	}
	/**
	 * @param applcntNm
	 *            the applcntNm to set
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
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
	 * @return the vcatnSeNm
	 */
	public String getVcatnSeNm() {
		return vcatnSeNm;
	}
	/**
	 * @param vcatnSeNm
	 *            the vcatnSeNm to set
	 */
	public void setVcatnSeNm(String vcatnSeNm) {
		this.vcatnSeNm = vcatnSeNm;
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
	 * @return the orgnztNm
	 */ 
	//  public String getOrgnztNm() { 
	//		return orgnztNm; 
	//	}
	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 * 
	 */
	//  public void setOrgnztNm(String orgnztNm) { 
	//		this.orgnztNm = orgnztNm; 
	//	}

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

	/**
	 * @return the searchYear
	 */
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * @param searchYear
	 *            the searchYear to set
	 */
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	/**
	 * @return the searchMonth
	 */
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * @param searchMonth
	 *            the searchMonth to set
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
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
