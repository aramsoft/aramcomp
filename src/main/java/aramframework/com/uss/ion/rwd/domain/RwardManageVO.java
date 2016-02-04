package aramframework.com.uss.ion.rwd.domain;

import aramframework.com.cmm.domain.SearchVO;

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

public class RwardManageVO extends SearchVO  {

	private static final long serialVersionUID = 1L;

	// 추가 검색 정보
	/** 검색시작일자 */
	private String searchFromDate;

	/** 검색종료일자 */
	private String searchToDate;

	/** 검색 성명	 */
	private String searchNm;

	/** 검색 진행구분 */
	private String searchConfmAt;

	// 도메인 정보
	/** 포상ID */
	private String rwardId;

	/** 포상자ID */
	private String rwardManId;

	/** 포상자명 */
	private String rwardManNm;

	/** 사용자 소속명 */
	private String orgnztNm;

	/** 포상코드 */
	private String rwardCd;

	/** 포상코드명 */
	private String rwardCdNm;

	/** 포상일자 */
	private String rwardDe;

	/** 포상명 */
	private String rwardNm;

	/** 공적내용 */
	private String pblenCn;

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

	/** 반려사유 */
	private String returnResn;

	/** 첨부파일ID */
	private String atchFileId;

	/** 약식결재ID */
	private String infrmlSanctnId;

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

	// 도메인 정보
	/**
	 * @return the rwardId
	 */
	public String getRwardId() {
		return rwardId;
	}
	/**
	 * @param rwardId
	 *            the rwardId to set
	 */
	public void setRwardId(String rwardId) {
		this.rwardId = rwardId;
	}

	/**
	 * @return the rwardManId
	 */
	public String getRwardManId() {
		return rwardManId;
	}
	/**
	 * @param rwardManId
	 *            the rwardManId to set
	 */
	public void setRwardManId(String rwardManId) {
		this.rwardManId = rwardManId;
	}

	/**
	 * @return the rwardManNm
	 */
	public String getRwardManNm() {
		return rwardManNm;
	}
	/**
	 * @param rwardManNm
	 *            the rwardManNm to set
	 */
	public void setRwardManNm(String rwardManNm) {
		this.rwardManNm = rwardManNm;
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
	 * @return the rwardCd
	 */
	public String getRwardCd() {
		return rwardCd;
	}
	/**
	 * @param rwardCd
	 *            the rwardCd to set
	 */
	public void setRwardCd(String rwardCd) {
		this.rwardCd = rwardCd;
	}

	/**
	 * @return the rwardCdNm
	 */
	public String getRwardCdNm() {
		return rwardCdNm;
	}
	/**
	 * @param rwardCdNm
	 *            the rwardCdNm to set
	 */
	public void setRwardCdNm(String rwardCdNm) {
		this.rwardCdNm = rwardCdNm;
	}

	/**
	 * @return the rwardDe
	 */
	public String getRwardDe() {
		return rwardDe;
	}
	/**
	 * @param rwardDe
	 *            the rwardDe to set
	 */
	public void setRwardDe(String rwardDe) {
		this.rwardDe = rwardDe;
	}

	/**
	 * @return the rwardNm
	 */
	public String getRwardNm() {
		return rwardNm;
	}
	/**
	 * @param rwardNm
	 *            the rwardNm to set
	 */
	public void setRwardNm(String rwardNm) {
		this.rwardNm = rwardNm;
	}

	/**
	 * @return the pblenCn
	 */
	public String getPblenCn() {
		return pblenCn;
	}
	/**
	 * @param pblenCn
	 *            the pblenCn to set
	 */
	public void setPblenCn(String pblenCn) {
		this.pblenCn = pblenCn;
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
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId
	 *            the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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

}
